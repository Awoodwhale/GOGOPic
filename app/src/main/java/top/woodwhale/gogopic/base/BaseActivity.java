package top.woodwhale.gogopic.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.nukc.stateview.StateView;
import com.vondear.rxtool.RxTool;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.woodwhale.gogopic.ui.animation.FlipAnimatorProvider;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mBind;
    private StateView mStateView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getViewResId());
        initBind();
        initView();
        initPresent();
        initEvent();
        loadDate();
    }

    // 所有的工具初始化绑定
    private void initBind() {
        mBind = ButterKnife.bind(this);
        mStateView = StateView.inject(this);
        mStateView.setAnimatorProvider(new FlipAnimatorProvider());
        mStateView.setOnRetryClickListener(this::contentRetry);
        RxTool.init(this);
    }

    protected void loadDate() {}

    // 错误后的点击重试方法
    protected void contentRetry() {}

    protected void showLoading() {
        mStateView.showLoading();
    }

    protected void showError() {
        mStateView.showRetry();
    }

    protected void showSuccess() {
        mStateView.showContent();
    }

    protected void showEmpty() {
        mStateView.showEmpty();
    }

    // 初始化视图
    protected abstract void initView();
    // 构建逻辑层
    protected abstract void initPresent();
    // 各种事件处理
    protected abstract void initEvent();
    // 获取resID
    public abstract int getViewResId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
        release();
    }

    // 如果需要释放资源，就使用
    protected void release() { }
}
