package top.woodwhale.gogopic.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.vondear.rxtool.view.RxToast;

import butterknife.BindView;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseActivity;
import top.woodwhale.gogopic.model.domain.AuthSignIn;
import top.woodwhale.gogopic.presenter.ILoginPresenter;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.PresenterManager;
import top.woodwhale.gogopic.view.ILoginCallback;

public class TestActivity extends BaseActivity implements ILoginCallback {

    @BindView(R.id.et_account) public EditText mAccountEt;
    @BindView(R.id.et_pwd) public EditText mPasswordEt;
    @BindView(R.id.bt_login) public Button mLoginBtn;
    private String mAccount;
    private String mPassword;
    private ILoginPresenter mLoginPresenter;

    @Override
    protected void initPresenter() {
        mLoginPresenter = PresenterManager.getInstance().getLoginPresenter();
        mLoginPresenter.registerViewCallback(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {
        // 设置登录按钮点击事件
        mLoginBtn.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(mAccountEt.getText()) && !TextUtils.isEmpty(mPasswordEt.getText())) {
                mAccount = String.valueOf(mAccountEt.getText());
                mPassword = String.valueOf(mPasswordEt.getText());
                loadData();
            } else {
                RxToast.warning("用户名和密码不能为空噢!");
            }
        });
    }

    private void loadData() {
        // 登录
        if (mLoginPresenter != null) {
            mLoginPresenter.getAuthenticate(mAccount,mPassword);
        }
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
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
            LogUtils.d(this,"content --> "+Constants.LOGIN_TOKEN);
            RxToast.success("登录成功!");
            showSuccess();
            // 登录成功就将登录页面跳转到主页
            Intent intent = new Intent(this,MainActivity.class);
            this.startActivity(intent);
            this.finish();
        }
    }

    @Override
    public void onAccountOrPasswordError() {
        RxToast.error("用户名或密码错误!");
    }

    @Override
    protected void contentRetry() {
        loadData();
    }
}
