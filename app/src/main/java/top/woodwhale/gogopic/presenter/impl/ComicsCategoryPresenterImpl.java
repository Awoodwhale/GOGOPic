package top.woodwhale.gogopic.presenter.impl;

import androidx.annotation.NonNull;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import top.woodwhale.gogopic.model.API;
import top.woodwhale.gogopic.model.domain.ComicsCategory;
import top.woodwhale.gogopic.presenter.IComicsCategoryPresenter;
import top.woodwhale.gogopic.utils.HeaderUtils;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.RetrofitManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.ICategoryInfoCallback;

public class ComicsCategoryPresenterImpl implements IComicsCategoryPresenter {
    private ICategoryInfoCallback mCallback;
    // -1是初始状态，0是加载中，1是成功，2是失败，3是空
    private static int mState = -1;
    private ComicsCategory mBody;
    // 当前页数
    private static int mNowPage;
    private ComicsCategory mSearchCategories;
    private String mKeywords;

    @Override
    public void getCategoryComics(String title, String sortWay, int page) {
        mNowPage = page;
        String url = UrlUtils.getComicsCategoryInfoUrl(String.valueOf(page),title,sortWay);
        LogUtils.d(this,"url --> " + url);
        firstGetCategoryContent(url,false);
    }


    @Override
    public void getLoadMoreCategoryComics(String title, String sortWay) {
        String url = UrlUtils.getComicsCategoryInfoUrl(String.valueOf(mNowPage),title,sortWay);
        LogUtils.d(this,"url --> " + url);
        firstGetCategoryContent(url,true);
    }

    @Override
    public void getSortedCategoryComics(String title,String sortWay) {
        // 选择新排序从第一页开始
        mNowPage = 1;
        String url = UrlUtils.getComicsCategoryInfoUrl(String.valueOf(mNowPage),title,sortWay);
        firstGetCategoryContent(url,false);
    }

    @Override
    public void getPageJumpCategoryComics(String title, String sortWay, int page) {
        mNowPage = page;
        String url = UrlUtils.getComicsCategoryInfoUrl(String.valueOf(mNowPage), title, sortWay);
        LogUtils.d(this,"page jump url --> " + url);
        firstGetCategoryContent(url,false);
    }

    @Override
    public void getChangedCategoryComics(String title) {
        mNowPage = 1;
        String url = UrlUtils.getComicsCategoryInfoUrl(String.valueOf(mNowPage),title);
        firstGetCategoryContent(url,false);
    }

    // 搜素信息
    @Override
    public void getSearchCategoryComics(String keywords) {
        mNowPage = 1;
        String url = UrlUtils.getSearchUrl(String.valueOf(mNowPage));
        String sortWay = "dd";  // 最新
        String json = "{\"keyword\":\""+keywords+"\",\"sort\":\""+sortWay+"\"}";
        LogUtils.d(this,"start search!");
        searchGetCategoryContent(keywords, url, json,0);
    }

    // 搜索排序

    @Override
    public void getSearchSortedCategoryComics(String keywords, String sortWay) {
        // 选择新排序从第一页开始
        mNowPage = 1;
        String url = UrlUtils.getSearchUrl(String.valueOf(mNowPage));
        String postJSON = "{\"keyword\":\""+keywords+"\",\"sort\":\""+sortWay+"\"}";
        searchGetCategoryContent(keywords,url,postJSON,2);
    }

    // 搜索加载更多
    @Override
    public void getSearchLoadMoreCategoryComics(String keyword, String sortWay) {
        String url = UrlUtils.getSearchUrl(String.valueOf(mNowPage));
        String postJSON = "{\"keyword\":\""+keyword+"\",\"sort\":\""+sortWay+"\"}";
        searchGetCategoryContent(keyword,url,postJSON,1);
    }

    // 搜索跳转页面
    @Override
    public void getSearchPageJumpCategoryComics(String keyword, String sortWay, int page) {
        mNowPage = page;
        String url = UrlUtils.getSearchUrl(String.valueOf(mNowPage));
        String postJSON = "{\"keyword\":\""+keyword+"\",\"sort\":\""+sortWay+"\"}";
        searchGetCategoryContent(keyword,url,postJSON,2);
    }

    // 搜索提取的方法
    private void searchGetCategoryContent(String keywords, String url, String json, int searchWay) {
        LogUtils.d(this,"searchGetCategoryContent...");
        if (searchWay != 1) {
            onLoading();
        }
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        RequestBody jsonBody = RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), json);
        Call<ComicsCategory> task = api.getSearchCategoryComics(HeaderUtils.getHeaderMap(url, true), url, jsonBody);
        task.enqueue(new Callback<ComicsCategory>() {
            @Override
            public void onResponse(@NonNull Call<ComicsCategory> call, @NonNull Response<ComicsCategory> response) {
                LogUtils.d(ComicsCategoryPresenterImpl.this,"search content code --> " + response.code());
                if (response.code() == 200) {
                    mSearchCategories = response.body();
                    if (mSearchCategories == null || mSearchCategories.getData() == null ||
                            mSearchCategories.getData().getComics() == null ||
                            mSearchCategories.getData().getComics().getDocs() == null||
                            mSearchCategories.getData().getComics().getDocs().size() == 0) {
                        if (searchWay == 1) {
                            onLoadMoreEmpty();
                        } else {
                            onEmpty();
                        }
                    } else {
                        mNowPage++;
                        if (searchWay == 0) {   // 0是搜索成功
                            mKeywords = keywords;
                            onSearchSuccess();
                        } else if (searchWay == 1) {    // 1是加载更多成功
                            onLoadMoreSuccess();
                        } else {    // 其他就是页面跳转成功或者排序成功
                            onCategoryLoaded();
                        }
                    }
                } else {
                    onError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ComicsCategory> call, @NonNull Throwable t) {
                LogUtils.d(ComicsCategoryPresenterImpl.this,"search error --> "+t.toString());
                onError();
            }
        });
    }

    // 获取内容的抽取方法
    private void firstGetCategoryContent(String url, boolean isLoadMore) {
        if (!isLoadMore) {
            onLoading();
        }
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        Call<ComicsCategory> task = api.getCategoryComics(HeaderUtils.getHeaderMap(url, false), url);
        task.enqueue(new Callback<ComicsCategory>() {
            @Override
            public void onResponse(@NonNull Call<ComicsCategory> call, @NonNull Response<ComicsCategory> response) {
                LogUtils.d(ComicsCategoryPresenterImpl.this, "ComicsCategory code --> " + response.code());
                if (response.code() == 200) {
                    mBody = response.body();
                    if (mBody == null || mBody.getData() == null ||
                            mBody.getData().getComics() == null ||
                            mBody.getData().getComics().getDocs() == null||
                            mBody.getData().getComics().getDocs().size() == 0) {
                        if (! isLoadMore) {
                            onEmpty();
                        } else {
                            mCallback.onLoadMoreEmpty();
                        }
                    } else {
                        mNowPage++;
                        if (! isLoadMore) {
                            onSuccess();
                        } else {
                            mCallback.onLoadMoreSuccess(mBody, mNowPage);
                        }
                    }
                } else {
                    if (! isLoadMore) {
                        onError();
                    }else {
                        mCallback.onLoadMoreError();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ComicsCategory> call, @NonNull Throwable t) {
                LogUtils.d(ComicsCategoryPresenterImpl.this, "error --> " + t.toString());
                if (! isLoadMore) {
                    onError();
                } else {
                    mCallback.onLoadMoreError();
                }
            }
        });
    }

    @Override
    public void registerViewCallback(ICategoryInfoCallback callback) {
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
                case 4:
                    onLoadMoreEmpty();
                    break;
                case 5:
                    onSearchSuccess();
                    break;
                case 6:
                    onLoadMoreSuccess();
                    break;
                case 7:
                    onCategoryLoaded();
                    break;
            }
        }
    }

    @Override
    public void unregisterViewCallback(ICategoryInfoCallback callback) {
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
            mCallback.onCategoryLoaded(mBody, mNowPage);
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

    private void onLoadMoreEmpty() {
        if (mCallback != null) {
            mCallback.onLoadMoreEmpty();
        } else {
            mState = 4;
        }
    }

    private void onSearchSuccess() {
        if (mCallback != null) {
            mCallback.onSearchSuccess(mSearchCategories, mNowPage, mKeywords);
        } else {
            mState = 5;
        }
    }

    private void onLoadMoreSuccess() {
        if (mCallback != null) {
            mCallback.onLoadMoreSuccess(mSearchCategories,mNowPage);
        } else {
            mState = 6;
        }
    }

    private void onCategoryLoaded() {
        if (mCallback != null) {
            mCallback.onCategoryLoaded(mSearchCategories,mNowPage);
        } else {
            mState = 7;
        }
    }

}
