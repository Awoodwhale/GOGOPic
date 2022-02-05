package top.woodwhale.gogopic.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.nukc.stateview.StateView;
import com.vondear.rxtool.RxTool;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.woodwhale.gogopic.R;

@SuppressLint("NonConstantResourceId")
public abstract class BaseFragment extends Fragment {

    private Unbinder mBind;
    private StateView mStateView;
    private FrameLayout mBaseContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = loadRootView(inflater, container);
        // 为啥要在这里绑定container？因为如果ButterKnife绑定早了，就会缺少显示层
        mBaseContainer = rootView.findViewById(R.id.base_container);
        mBaseContainer.addView(inflater.inflate(getRootViewResId(), container, false));

        initBind(rootView);
        initPresenter();
        initView(rootView);
        initEvent();
        loadData();
        return rootView;
    }

    // 所有的工具初始化绑定
    private void initBind(View rootView) {
        mBind = ButterKnife.bind(this,rootView);
        mStateView = StateView.inject(mBaseContainer);
        mStateView.setOnRetryClickListener(this::contentRetry);
        RxTool.init(rootView.getContext());
    }

    protected void initEvent() {}

    // 重试方法
    protected void contentRetry() {}


    /**
     * 默认返回的是base_fragment_layout，当然子类可以重写，自定义页面
     *
     * @param inflater
     * @param container
     * @return
     */
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_fragment_layout, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
        release();
    }

    protected abstract void initView(View rootView);

    protected abstract void initPresenter();

    protected void release() {
        // 释放资源
    }

    protected void loadData() {
        // 加载数据
    }

    /**
     * 所有子类必须得重写的方法，返回fragment的id
     *
     * @return
     */
    protected abstract int getRootViewResId();


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

}
