package top.woodwhale.gogopic.presenter.impl;

import androidx.annotation.NonNull;

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
                if (response.code() == 200) {
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
                    mCallback.onNetworkError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ComicsComment> call, @NonNull Throwable t) {
                LogUtils.d(ComicsCommentPresenterImpl.this,"comment error --> "+t.toString() );
                mCallback.onNetworkError();
            }
        });
    }
}
