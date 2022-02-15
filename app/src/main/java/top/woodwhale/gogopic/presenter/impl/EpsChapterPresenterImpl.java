package top.woodwhale.gogopic.presenter.impl;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import top.woodwhale.gogopic.model.API;
import top.woodwhale.gogopic.model.domain.ComicsChapter;
import top.woodwhale.gogopic.presenter.IEpsChapterPresenter;
import top.woodwhale.gogopic.utils.HeaderUtils;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.RetrofitManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.IEpsChapterCallback;

public class EpsChapterPresenterImpl implements IEpsChapterPresenter {
    private IEpsChapterCallback mCallback;

    @Override
    public void registerViewCallback(IEpsChapterCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IEpsChapterCallback callback) {
        this.mCallback = null;
    }


    @Override
    public void getEpsChapter(String bookID, int page, boolean isLoadMore) {
        String url = UrlUtils.getEpsChapterUrl(bookID,page);
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        Call<ComicsChapter> task = api.getEpsChapter(HeaderUtils.getHeaderMap(url, false), url);
        task.enqueue(new Callback<ComicsChapter>() {
            @Override
            public void onResponse(@NonNull Call<ComicsChapter> call, @NonNull Response<ComicsChapter> response) {
                LogUtils.d(EpsChapterPresenterImpl.this,"eps code --> " + response.code());
                if (response.code() == 200 && mCallback != null) {
                    ComicsChapter body = response.body();
                    if (body == null ||
                            body.getData() == null ||
                            body.getData().getEps() == null ||
                            body.getData().getEps().getDocs().size() == 0) {
                        mCallback.onEmpty();
                    } else {
                        if (isLoadMore) {
                            mCallback.onEpsChapterLoadedMore(body);
                        } else {
                            mCallback.onEpsChapterLoaded(body);
                        }
                    }
                } else {
                    if (mCallback != null) {
                        mCallback.onNetworkError();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ComicsChapter> call, @NonNull Throwable t) {
                LogUtils.e(EpsChapterPresenterImpl.this,"eps error --> " + t.toString());
                if (mCallback != null) {
                    mCallback.onNetworkError();
                }
            }
        });
    }
}
