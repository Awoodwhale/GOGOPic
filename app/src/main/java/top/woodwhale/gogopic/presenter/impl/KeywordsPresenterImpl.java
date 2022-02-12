package top.woodwhale.gogopic.presenter.impl;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import top.woodwhale.gogopic.model.API;
import top.woodwhale.gogopic.model.domain.Keywords;
import top.woodwhale.gogopic.presenter.IKeywordsPresenter;
import top.woodwhale.gogopic.utils.HeaderUtils;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.RetrofitManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.IKeywordsCallback;

public class KeywordsPresenterImpl implements IKeywordsPresenter {
    private IKeywordsCallback mCallback = null;
    private Keywords mKeywords;

    @Override
    public void getKeywords() {
        onLoading();
        // 登录
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        Call<Keywords> task = api.getKeywordsContent(HeaderUtils.getHeaderMap(UrlUtils.KEYWORDS_URL, false));
        task.enqueue(new Callback<Keywords>() {
            @Override
            public void onResponse(@NonNull Call<Keywords> call, @NonNull Response<Keywords> response) {
                LogUtils.d(KeywordsPresenterImpl.this,"keywords code --> "+response.code());
                if (response.code() == 200) {
                    mKeywords = response.body();
                    if (mKeywords == null || mKeywords.getData().getKeywords().size() == 0) {
                        onEmpty();
                    } else {
                        onSuccess();
                    }
                } else {
                    onError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Keywords> call, @NonNull Throwable t) {
                LogUtils.d(KeywordsPresenterImpl.this,"keywords error --> " + t.toString());
                onError();
            }
        });
    }

    @Override
    public void registerViewCallback(IKeywordsCallback callback) {
        this.mCallback = callback;

    }

    @Override
    public void unregisterViewCallback(IKeywordsCallback callback) {
        this.mCallback = null;
    }


    private void onLoading() {
        if (mCallback != null) {
            mCallback.onLoading();
        }
    }

    private void onSuccess() {
        if (mCallback != null) {
            mCallback.onKeywordsLoaded(mKeywords);
        }
    }

    private void onError() {
        if (mCallback != null) {
            mCallback.onNetworkError();
        }
    }

    private void onEmpty() {
        if (mCallback != null) {
            mCallback.onEmpty();
        }
    }
}
