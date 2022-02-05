package top.woodwhale.gogopic.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.nio.charset.StandardCharsets;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.model.domain.AuthSignIn;
import top.woodwhale.gogopic.presenter.ILoginPresenter;
import top.woodwhale.gogopic.presenter.impl.LoginPresenterImpl;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.view.ILoginCallback;

@SuppressLint("NonConstantResourceId")
public class LoginActivity extends Activity implements ILoginCallback {

    @BindView(R.id.et_account) public EditText mAccountEt;
    @BindView(R.id.et_pwd) public EditText mPasswordEt;
    @BindView(R.id.bt_login) public Button mLoginBtn;
    private ILoginPresenter mLoginPresenter;
    private String mAccount;
    private String mPassword;
    private Unbinder mBind;
    private boolean mIsLoggedIn = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mBind = ButterKnife.bind(this);
        checkIsLoggedIn();
        initPresenter();
        initView();
    }

    private void checkIsLoggedIn() {
        SharedPreferences settingInfo = this.getSharedPreferences("GOGOPicLogin", MODE_PRIVATE);
        mAccount = settingInfo.getString(Constants.USERNAME_KEY,null);
        mPassword = settingInfo.getString(Constants.PASSWORD_KEY,null);
        if (mAccount != null && mPassword != null) {
            mIsLoggedIn = true;
            mAccount = new String(Base64.decode(mAccount, Base64.DEFAULT));
            mPassword = new String(Base64.decode(mPassword, Base64.DEFAULT));
            mAccountEt.setEnabled(false);
            mPasswordEt.setEnabled(false);
            mLoginBtn.setEnabled(false);
        }
    }

    private void write2SharedPreferences(String account, String password) {
        SharedPreferences settingInfo = this.getSharedPreferences("GOGOPicLogin", MODE_PRIVATE);
        SharedPreferences.Editor edit = settingInfo.edit();
        edit.putString(Constants.USERNAME_KEY,
                Base64.encodeToString(account.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT));
        edit.putString(Constants.PASSWORD_KEY,
                Base64.encodeToString(password.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT));
        edit.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
        release();
    }

    private void initView() {
        // 如果可以从SharedPreferences中读取登录信息，那么就自动登录
        if (mAccount != null && mPassword != null) {
            loadData();
            return;
        }
        mLoginBtn.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(mAccountEt.getText()) && !TextUtils.isEmpty(mPasswordEt.getText())) {
                mAccount = String.valueOf(mAccountEt.getText());
                mPassword = String.valueOf(mPasswordEt.getText());
                loadData();
            } else {
                Toast.makeText(LoginActivity.this, "非法输入账号和密码!请重试!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void initPresenter() {
        mLoginPresenter = new LoginPresenterImpl();
        mLoginPresenter.registerViewCallback(this);
    }

    private void loadData() {

        // 登录
        if (mLoginPresenter != null) {
            mLoginPresenter.getAuthenticate(mAccount,mPassword);
        }
    }

    @Override
    public void onNetworkError() {
        Toast.makeText(this, "网络错误！请重试！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoading() {
        Toast.makeText(this, "登陆中...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmpty() {
        Toast.makeText(this, "服务器返回数据为空！请重试！", Toast.LENGTH_SHORT).show();
    }

    /**
     * 请求成功的回调
     * @param authSignIn
     */
    @Override
    public void onAuthenticateLoaded(AuthSignIn authSignIn) {
        if (Constants.LOGIN_TOKEN != null) {
            if (!mIsLoggedIn) {
                // 如果是第一次登录，就写入
                write2SharedPreferences(mAccount,mPassword);
            }
            LogUtils.d(this,"content --> "+Constants.LOGIN_TOKEN);
            Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
            // 登录成功就将登录页面跳转到主页
            Intent intent = new Intent(this,MainActivity.class);
            this.startActivity(intent);
            this.finish();
        }
    }

    /**
     * 密码错误的回调
     */
    @Override
    public void onAccountOrPasswordError() {
        Toast.makeText(this, "账号或密码错误！", Toast.LENGTH_SHORT).show();
        mAccountEt.setText("");
        mPasswordEt.setText("");
    }

    private void release() {
        if (mLoginPresenter != null) {
            mLoginPresenter.unregisterViewCallback(this);
        }
    }
}
