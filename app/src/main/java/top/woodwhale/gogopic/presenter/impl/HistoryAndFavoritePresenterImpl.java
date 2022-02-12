package top.woodwhale.gogopic.presenter.impl;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import top.woodwhale.gogopic.model.API;
import top.woodwhale.gogopic.model.domain.ComicsCategory;
import top.woodwhale.gogopic.presenter.IHistoryAndFavoritePresenter;
import top.woodwhale.gogopic.utils.HeaderUtils;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.RetrofitManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.IHistoryAndFavoriteCallback;

public class HistoryAndFavoritePresenterImpl implements IHistoryAndFavoritePresenter {
    private IHistoryAndFavoriteCallback mCallback;
    int mNowPage = 1;

    @Override
    public void registerViewCallback(IHistoryAndFavoriteCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IHistoryAndFavoriteCallback callback) {
        this.mCallback = null;
    }

    @Override
    public void getHistoryContent() {
        // 获取历史从sp中读取，最多存储了50条记录
    }

    @Override
    public void getFavoriteContent(int page) {
        mNowPage = page;
        String url = UrlUtils.getFavoriteComicsInfoUrl(mNowPage);
        getFavoriteContentNetworkMethod(url,false);
    }

    @Override
    public void getFavoriteMoreContent() {
        String url = UrlUtils.getFavoriteComicsInfoUrl(mNowPage);
        getFavoriteContentNetworkMethod(url,true);

    }

    private void getFavoriteContentNetworkMethod(String url,boolean isLoadMore) {
        // 从网络获取收藏
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        Call<ComicsCategory> task = api.getFavoriteComics(HeaderUtils.getHeaderMap(url, false), url);
        task.enqueue(new Callback<ComicsCategory>() {
            @Override
            public void onResponse(@NonNull Call<ComicsCategory> call, @NonNull Response<ComicsCategory> response) {
                LogUtils.d(HistoryAndFavoritePresenterImpl.this,"history content code --> " + response.code());
                if (response.code() == 200) {
                    ComicsCategory body = response.body();
                    if (body == null || body.getData() == null ||
                            body.getData().getComics() == null ||
                            body.getData().getComics().getDocs() == null||
                            body.getData().getComics().getDocs().size() == 0) {
                        if (isLoadMore) {
                            mCallback.onFavoriteMoreContentEmpty();
                        } else {
                            mCallback.onEmpty();
                        }
                    } else {
                        mNowPage ++;
                        if (isLoadMore) {
                            mCallback.onFavoriteMoreContentLoaded(body,mNowPage);
                        } else {
                            mCallback.onFavoriteContentLoaded(body,mNowPage);
                        }
                    }
                } else {
                    if (isLoadMore) {
                        mCallback.onFavoriteMoreContentError();
                    } else {
                        mCallback.onNetworkError();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ComicsCategory> call, @NonNull Throwable t) {
                LogUtils.d(HistoryAndFavoritePresenterImpl.this,"history error --> " + t.toString());
                if (isLoadMore) {
                    mCallback.onFavoriteMoreContentError();
                } else {
                    mCallback.onNetworkError();
                }
            }
        });
    }
}
