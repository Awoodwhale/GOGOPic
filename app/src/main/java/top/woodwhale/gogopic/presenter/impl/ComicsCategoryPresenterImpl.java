package top.woodwhale.gogopic.presenter.impl;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import top.woodwhale.gogopic.model.domain.API;
import top.woodwhale.gogopic.model.domain.ComicsCategory;
import top.woodwhale.gogopic.presenter.ICategoryPresenter;
import top.woodwhale.gogopic.utils.HeaderUtils;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.RetrofitManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.ICategoryCallback;

public class ComicsCategoryPresenterImpl implements ICategoryPresenter {
    private ICategoryCallback mCallback;
    // -1是初始状态，0是加载中，1是成功，2是失败，3是空
    private static int mState = -1;
    private ComicsCategory mBody;
    // 当前页数
    private static int nowPage;

    /**
     * 第一次加载
     * @param url
     */
    @Override
    public void getCategoryComics(String url) {
        nowPage = 1;
        // 进入就载入
        LogUtils.d(this,"url --> " + url);
        firstGetCategoryContent(url);
    }

    /**
     * 加载更多
     * @param title
     */
    @Override
    public void getLoadMoreCategoryComics(String title, String sortWay) {
        String url = UrlUtils.getComicsCategoryInfoUrl(String.valueOf(nowPage),title,sortWay);
        LogUtils.d(this,"url --> " + url);
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        Call<ComicsCategory> task = api.getComics(HeaderUtils.getHeaderMap(url, false), url);
        task.enqueue(new Callback<ComicsCategory>() {
            @Override
            public void onResponse(@NonNull Call<ComicsCategory> call, @NonNull Response<ComicsCategory> response) {
                LogUtils.d(ComicsCategoryPresenterImpl.this,"ComicsCategory code --> " + response.code());
                if (response.code() == 200) {
                    ComicsCategory body = response.body();
                    if (body == null || body.getData() == null) {
                        mCallback.onLoadMoreEmpty();
                    } else {
                        // 访问成功，当前页数++
                        nowPage ++;
                        mCallback.onLoadMoreSuccess(body,nowPage);
                    }
                } else {
                    mCallback.onLoadMoreError();
                }
            }
            @Override
            public void onFailure(@NonNull Call<ComicsCategory> call, @NonNull Throwable t) {
                LogUtils.d(ComicsCategoryPresenterImpl.this,"error --> " + t.toString());
                mCallback.onLoadMoreError();
            }
        });
    }

    @Override
    public void getSortedCategoryComics(String title,String sortWay) {
        // 选择新排序从第一页开始
        nowPage = 1;
        String url = UrlUtils.getComicsCategoryInfoUrl(String.valueOf(nowPage),title,sortWay);
        firstGetCategoryContent(url);
    }

    @Override
    public void getPageJumpCategoryComics(String title, String sortWay, int page) {
        nowPage = page;
        String url = UrlUtils.getComicsCategoryInfoUrl(String.valueOf(nowPage), title, sortWay);
        LogUtils.d(this,"page jump url --> " + url);
        firstGetCategoryContent(url);
    }

    // 第一次获取分类第一页信息
    private void firstGetCategoryContent(String url) {
        onLoading();
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        Call<ComicsCategory> task = api.getComics(HeaderUtils.getHeaderMap(url, false), url);
        task.enqueue(new Callback<ComicsCategory>() {
            @Override
            public void onResponse(@NonNull Call<ComicsCategory> call, @NonNull Response<ComicsCategory> response) {
                LogUtils.d(ComicsCategoryPresenterImpl.this, "ComicsCategory code --> " + response.code());
                if (response.code() == 200) {
                    mBody = response.body();
                    if (mBody == null || mBody.getData() == null) {
                        onEmpty();
                    } else {
                        nowPage ++;
                        onSuccess();
                    }
                } else {
                    onError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ComicsCategory> call, @NonNull Throwable t) {
                LogUtils.d(ComicsCategoryPresenterImpl.this, "error --> " + t.toString());
                onError();
            }
        });
    }

    @Override
    public void registerViewCallback(ICategoryCallback callback) {
        this.mCallback = callback;
        if (mState != -1) {
            switch (mState) {
                case 0:
                    onLoading();
                    break;
                case 1:
                    onSuccess();
                    break;
                case 2:
                    onError();
                    break;
                case 3:
                    onEmpty();
                    break;
            }
        }
    }

    @Override
    public void unregisterViewCallback(ICategoryCallback callback) {
        this.mCallback = null;
    }

    private void onLoading() {
        if (mCallback != null) {
            mCallback.onLoading();
        } else {
            mState = 0;
        }
    }

    private void onSuccess() {
        if (mCallback != null) {
            mCallback.onCategoryLoaded(mBody);
        } else {
            mState = 1;
        }
    }

    private void onError() {
        if (mCallback != null) {
            mCallback.onNetworkError();
        } else {
            mState = 2;
        }
    }

    private void onEmpty() {
        if (mCallback != null) {
            mCallback.onEmpty();
        } else {
            mState = 3;
        }
    }
}
