package top.woodwhale.gogopic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.vondear.rxtool.view.RxToast;

import java.nio.charset.StandardCharsets;

import butterknife.BindView;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseActivity;
import top.woodwhale.gogopic.model.domain.AuthSignIn;
import top.woodwhale.gogopic.presenter.ILoginPresenter;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.PresenterManager;
import top.woodwhale.gogopic.view.ILoginCallback;

@SuppressLint("NonConstantResourceId")
public class LoginActivity extends BaseActivity implements ILoginCallback {

    @BindView(R.id.et_account) public EditText mAccountEt;
    @BindView(R.id.et_pwd) public EditText mPasswordEt;
    @BindView(R.id.bt_login) public Button mLoginBtn;
    private ILoginPresenter mLoginPresenter;
    private String mAccount;
    private String mPassword;
    private boolean mIsLoggedIn = false;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        // 开始加载就查询是否有记录
        checkIsLoggedIn();
        // 如果可以从SharedPreferences中读取登录信息，那么就自动登录
        if (mAccount != null && mPassword != null) {
            loadData();
        }
    }

    @Override
    protected void initPresenter() {
        mLoginPresenter = PresenterManager.getInstance().getLoginPresenter();
        mLoginPresenter.registerViewCallback(this);
    }

    @Override
    protected void initEvent() {
        // 设置登录按钮点击事件
        mLoginBtn.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(mAccountEt.getText()) && !TextUtils.isEmpty(mPasswordEt.getText())) {
                mAccount = String.valueOf(mAccountEt.getText());
                mPassword = String.valueOf(mPasswordEt.getText());
                InputMethodManager imm = ( InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(mPasswordEt.getWindowToken(), 0 );
                }
                loadData();
            } else {
                RxToast.warning("用户名和密码不能为空噢!");
            }
        });
    }

    protected void loadData() {
        // 登录
        if (mLoginPresenter != null) {
            mLoginPresenter.getAuthenticate(mAccount,mPassword);
        }
    }

    @Override
    protected void contentRetry() {
        loadData();
    }

    @Override
    public void onNetworkError() {
        showError();
    }

    @Override
    public void onLoading() {
        showLoading();
    }

    @Override
    public void onEmpty() {
        showEmpty();
    }

    @Override
    public void onAuthenticateLoaded(AuthSignIn authSignIn) {
        if (Constants.LOGIN_TOKEN != null) {
            if (!mIsLoggedIn) {
                // 如果是第一次登录，就写入
                write2SharedPreferences(mAccount,mPassword);
            }
            LogUtils.d(this,"content --> "+Constants.LOGIN_TOKEN);
            RxToast.success("登录成功!");
            showSuccess();
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
        RxToast.error("账户或密码错误！");
        mAccountEt.setText("");
        mPasswordEt.setText("");
    }

    protected void release() {
        if (mLoginPresenter != null) {
            mLoginPresenter.unregisterViewCallback(this);
        }
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
}
