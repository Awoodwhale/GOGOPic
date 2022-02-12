package top.woodwhale.gogopic.presenter.impl;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import top.woodwhale.gogopic.model.API;
import top.woodwhale.gogopic.model.domain.AuthSignIn;
import top.woodwhale.gogopic.presenter.ILoginPresenter;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.HeaderUtils;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.RetrofitManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.ILoginCallback;

public class LoginPresenterImpl implements ILoginPresenter {
    private ILoginCallback mCallback;

    @Override
    public void getAuthenticate(String account, String password) {
        if (mCallback != null) {
            mCallback.onLoading();
        }
        // 登录
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        String contentJSON = "{\"email\":\""+account+"\",\"password\":\""+password+"\"}";
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=UTF-8"),contentJSON);
        Call<AuthSignIn> task = api.getAuthSignIn(HeaderUtils.getHeaderMap(UrlUtils.LOGIN_URL,true),body);
        task.enqueue(new Callback<AuthSignIn>() {
            @Override
            public void onResponse(@NonNull Call<AuthSignIn> call, @NonNull Response<AuthSignIn> response) {
                LogUtils.d(LoginPresenterImpl.this,"login code --> " + response.code());
                if (response.code() == 200 && mCallback != null) {
                    AuthSignIn authSignIn = response.body();
                    if (authSignIn == null || authSignIn.getData() == null) {
                        mCallback.onEmpty();
                    } else {
                        Constants.LOGIN_TOKEN = authSignIn.getData().getToken();
                        mCallback.onAuthenticateLoaded(authSignIn);
                        // 如果成功登录，就一并处理签到
                    }
                } else {
                    String errBody = null;
                    try {
                        if (response.errorBody() != null) {
                            errBody = response.errorBody().string();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (errBody != null && response.code() == 400 && errBody.contains("invalid")) {
                        LogUtils.d(LoginPresenterImpl.this,"账号或者密码错误！");
                        mCallback.onAccountOrPasswordError();
                        return;
                    }
                    if (mCallback != null) {
                        mCallback.onNetworkError();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthSignIn> call, @NonNull Throwable t) {
                if (mCallback != null) {
                    mCallback.onNetworkError();
                }
                LogUtils.e(LoginPresenterImpl.this,"请求错误！" + t.toString());
            }
        });
    }

    @Override
    public void registerViewCallback(ILoginCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void unregisterViewCallback(ILoginCallback callback) {
        this.mCallback = null;
    }

}
