package top.woodwhale.gogopic.presenter.impl;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import top.woodwhale.gogopic.model.API;
import top.woodwhale.gogopic.model.domain.ComicsComment;
import top.woodwhale.gogopic.presenter.IComicsCommentPresenter;
import top.woodwhale.gogopic.utils.HeaderUtils;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.RetrofitManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.IComicsCommentCallback;

public class ComicsCommentPresenterImpl implements IComicsCommentPresenter {
    private IComicsCommentCallback mCallback;

    @Override
    public void registerViewCallback(IComicsCommentCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IComicsCommentCallback callback) {
        this.mCallback = null;
    }

    @Override
    public void getComment(String bookID, int page) {
        String url = UrlUtils.getComicsCommentUrl(bookID,page);
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        Call<ComicsComment> task = api.getComicsComment(HeaderUtils.getHeaderMap(url, false), url);
        task.enqueue(new Callback<ComicsComment>() {
            @Override
            public void onResponse(@NonNull Call<ComicsComment> call, @NonNull Response<ComicsComment> response) {
                LogUtils.d(ComicsCommentPresenterImpl.this,"comment code --> "+response.code());
                if (response.code() == 200 && mCallback != null) {
                    ComicsComment body = response.body();
                    if (body == null ||
                            body.getData() == null ||
                            body.getData().getComments() == null ||
                            body.getData().getComments().getDocs().size() == 0) {
                        mCallback.onEmpty();
                    } else {
                        mCallback.onCommentLoaded(body);
                    }
                } else {
                    if (mCallback != null) {
                        mCallback.onNetworkError();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ComicsComment> call, @NonNull Throwable t) {
                LogUtils.d(ComicsCommentPresenterImpl.this,"comment error --> "+t.toString() );
                mCallback.onNetworkError();
            }
        });
    }

    @Override
    public void launchComment(String bookID, String comment) {
        String url = UrlUtils.getComicsCommentLaunchUrl(bookID);
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        String jsonBody = "{\"content\": \""+comment+"\"}";
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=UTF-8"),jsonBody);
        Call<ResponseBody> task = api.getComicsLaunchComment(HeaderUtils.getHeaderMap(url, true), url, body);
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                LogUtils.d(ComicsCommentPresenterImpl.this,"launch comment code --> " + response.code());
                if (response.code() == 200 && mCallback != null) {
                    ResponseBody resBody = response.body();
                    if (resBody != null) {
                        try {
                            String resString = resBody.string();
                            if (!TextUtils.isEmpty(resString) && resString.contains("success")) {
                                mCallback.onCommentLaunched(resString);
                            } else {
                                mCallback.onCommentLaunchedError();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        mCallback.onCommentLaunchedError();
                    }
                } else {
                    if (mCallback != null) {
                        mCallback.onCommentLaunchedError();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                LogUtils.e(ComicsCommentPresenterImpl.this,"launch comment error --> "+ t.toString());
                if (mCallback != null) {
                    mCallback.onCommentLaunchedError();
                }
            }
        });
    }
}
