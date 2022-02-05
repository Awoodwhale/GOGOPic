package top.woodwhale.gogopic.presenter.impl;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import top.woodwhale.gogopic.model.domain.API;
import top.woodwhale.gogopic.model.domain.UserInfo;
import top.woodwhale.gogopic.presenter.IMinePresenter;
import top.woodwhale.gogopic.utils.HeaderUtils;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.RetrofitManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.IMineCallback;

public class MinePresenterImpl implements IMinePresenter {
    private IMineCallback mCallback;

    @Override
    public void registerViewCallback(IMineCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IMineCallback callback) {
        this.mCallback = null;
    }

    @Override
    public void getUserInfo() {
        // 刚进入需要显示加载
        if (mCallback != null) {
            mCallback.onLoading();
        }
        // 登录
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        Call<UserInfo> task = api.getUserInfo(HeaderUtils.getHeaderMap(UrlUtils.USER_INFO_URL, false));
        task.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(@NonNull Call<UserInfo> call, @NonNull Response<UserInfo> response) {
                LogUtils.d(MinePresenterImpl.this,"mine code --> " + response.code());
                if (response.code() == 200 && mCallback != null) {
                    UserInfo body = response.body();
                    if (body != null) {
                        // 数据回调到MineFragment
                        mCallback.onUserInfoLoaded(body);
                    }else {
                        mCallback.onEmpty();
                    }
                }else {
                    if (mCallback != null) {
                        mCallback.onNetworkError();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserInfo> call, @NonNull Throwable t) {
                LogUtils.e(MinePresenterImpl.this,"个人中心请求错误 --> "+t.toString());
                if (mCallback != null) {
                    mCallback.onNetworkError();
                }
            }
        });
    }

}
