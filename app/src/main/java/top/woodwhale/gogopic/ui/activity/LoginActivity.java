package top.woodwhale.gogopic.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mBind = ButterKnife.bind(this);

        initView();
        initPresenter();
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
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mAccountEt.getText()) && !TextUtils.isEmpty(mPasswordEt.getText())) {
                    mAccount = String.valueOf(mAccountEt.getText());
                    mPassword = String.valueOf(mPasswordEt.getText());
                    loadData();
                } else {
                    Toast.makeText(LoginActivity.this, "非法输入账号和密码!请重试!", Toast.LENGTH_SHORT).show();
                }
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
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onAuthenticateLoaded(AuthSignIn authSignIn) {
        if (Constants.LOGIN_TOKEN != null) {
            LogUtils.d(this,"content --> "+Constants.LOGIN_TOKEN);
            Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
            // 登录成功就将登录页面跳转到主页
            Intent intent = new Intent(this,MainActivity.class);
            this.startActivity(intent);
            this.finish();
        }

    }

    private void release() {
        if (mLoginPresenter != null) {
            mLoginPresenter.unregisterViewCallback(this);
        }
    }
}
