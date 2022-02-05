package top.woodwhale.gogopic.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.nukc.stateview.StateView;
import com.vondear.rxtool.RxTool;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.woodwhale.gogopic.R;

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mBind;
    private StateView mStateView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        initBind();
        initPresenter();
        initView();
        initEvent();
    }

    // 初始化绑定组件
    protected void initBind() {
        mBind = ButterKnife.bind(this);
        RxTool.init(this);
        mStateView = StateView.inject(this);
        mStateView.setEmptyResource(R.layout.fragment_empty);
        mStateView.setRetryResource(R.layout.fragment_error);
        mStateView.setOnRetryClickListener(this::contentRetry);
    }

    // 初始化视图
    protected abstract void initView();

    // 初始化逻辑层
    protected abstract void initPresenter();
    // 初始化事件
    protected void initEvent() {}

    // 如果需要就重写
    protected void contentRetry() {}

    // 获取layout
    protected abstract int getLayoutResID();

    // 销毁时记得release资源
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
        release();
    }

    // 销毁资源
    protected void release() {}

    // 显示加载中
    protected void showLoading() {
        if (mStateView != null) {
            mStateView.showLoading();
        }
    }

    // 显示错误
    protected void showError() {
        if (mStateView != null) {
            mStateView.showRetry();
        }
    }

    // 显示成功
    protected void showSuccess() {
        if (mStateView != null) {
            mStateView.showContent();
        }
    }

    // 显示查询结果为空
    protected void showEmpty() {
        if (mStateView != null) {
            mStateView.showEmpty();
        }
    }
}
