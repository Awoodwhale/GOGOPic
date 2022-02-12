package top.woodwhale.gogopic.presenter.impl;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import top.woodwhale.gogopic.model.API;
import top.woodwhale.gogopic.model.domain.ComicsMain;
import top.woodwhale.gogopic.model.domain.LikeOrFavorite;
import top.woodwhale.gogopic.presenter.IComicsInfoPresenter;
import top.woodwhale.gogopic.utils.HeaderUtils;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.RetrofitManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.IComicsInfoCallback;

public class ComicsInfoPresenterImpl implements IComicsInfoPresenter {
    private IComicsInfoCallback mCallback;
    // -1是初始状态，0是加载中，1是成功，2是失败，3是空
    private static int mState = -1;
    private ComicsMain mBody;

    @Override
    public void registerViewCallback(IComicsInfoCallback callback) {
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
    public void unregisterViewCallback(IComicsInfoCallback callback) {
        this.mCallback = null;
    }

    @Override
    public void getComicsInfo(String comicsID) {
        String url = UrlUtils.getComicsInfoUrl(comicsID);
        LogUtils.d(this,"url --> " + url);
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        Call<ComicsMain> task = api.getComicsInfo(HeaderUtils.getHeaderMap(url, false), url);
        task.enqueue(new Callback<ComicsMain>() {
            @Override
            public void onResponse(@NonNull Call<ComicsMain> call, @NonNull Response<ComicsMain> response) {
                LogUtils.d(ComicsInfoPresenterImpl.this,"comicsInfo code --> " + response.code());
                if (response.code() == 200) {
                    mBody = response.body();
                    if (mBody == null ||
                            mBody.getData() == null ||
                            mBody.getData().getComic() == null ||
                            mBody.getData().getComic().getCategories() == null||
                            mBody.getData().getComic().getCategories().size() == 0) {
                        onEmpty();
                    } else {
                        onSuccess();
                    }
                } else {
                     onError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ComicsMain> call, @NonNull Throwable t) {
                LogUtils.d(ComicsInfoPresenterImpl.this,"comicsInfo error --> " + t.toString());
                onError();
            }
        });
    }

    @Override
    public void getComicsLike(String comicsID) {
        String url = UrlUtils.getComicsLikeUrl(comicsID);
        LogUtils.d(this,"like url --> " + url);
        getFavoriteOrLikeContentMethod(url,true);
    }

    @Override
    public void getComicsFavorite(String comicsID) {
        String url = UrlUtils.getComicsFavoriteUrl(comicsID);
        LogUtils.d(this,"favorite url --> " + url);
        getFavoriteOrLikeContentMethod(url,false);
    }

    private void getFavoriteOrLikeContentMethod(String url, boolean isLike) {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        Call<LikeOrFavorite> task = api.getComicsLike(HeaderUtils.getHeaderMap(url, true), url);
        task.enqueue(new Callback<LikeOrFavorite>() {
            @Override
            public void onResponse(@NonNull Call<LikeOrFavorite> call, @NonNull Response<LikeOrFavorite> response) {
                LogUtils.d(ComicsInfoPresenterImpl.this,"likeOrFavorite code --> " + response.code());
                if (response.code() == 200) {
                    LikeOrFavorite body = response.body();
                    if (body == null ||
                            body.getData() == null ||
                            body.getData().getAction() == null) {
                        if (isLike) {
                            mCallback.onComicsLikeEmpty();
                        } else {
                            mCallback.onComicsFavoriteEmpty();
                        }
                    } else {
                        if (isLike) {
                            mCallback.onComicsLikeLoaded(body);
                        } else {
                            mCallback.onComicsFavoriteLoaded(body);
                        }
                    }
                } else {
                    if (isLike) {
                        mCallback.onComicsLikeError();
                    } else {
                        mCallback.onComicsFavoriteError();
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<LikeOrFavorite> call, @NonNull Throwable t) {
                LogUtils.d(ComicsInfoPresenterImpl.this,"likeOrFavorite error --> " + t.toString());
                if (isLike) {
                    mCallback.onComicsLikeError();
                } else {
                    mCallback.onComicsFavoriteError();
                }
            }
        });
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
            mCallback.onComicsInfoLoaded(mBody);
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
