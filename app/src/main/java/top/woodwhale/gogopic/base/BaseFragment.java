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

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.utils.LogUtils;

@SuppressLint("NonConstantResourceId")
public abstract class BaseFragment extends Fragment {

    private State currentState = State.NONE;
    private Unbinder mBind;
    private FrameLayout mBaseContainer;
    private View mLoadingView;
    private View mSuccessView;
    private View mErrorView;
    private View mEmptyView;

    public enum State {
        NONE, LOADING, SUCCESS, ERROR, EMPTY;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = loadRootView(inflater, container);
        // 为啥要在这里绑定container？因为如果ButterKnife绑定早了，就会缺少显示层
        mBaseContainer = rootView.findViewById(R.id.base_container);
        loadStatesView(inflater, container);
        mBind = ButterKnife.bind(this, rootView);
        /*
         1. 初始化视图
         2. 构建逻辑层
         3. 加载数据，到逻辑层回调处理
         */
        initView(rootView);
        initPresenter();
        loadData();

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
        release();
    }

    /**
     * 点击 网络错误 重试的监听
     */
    @OnClick(R.id.network_error_tips)
    public void retryNet() {
        // 点击重新加载内容
        LogUtils.d(this, "retry");
        onNetworkRetryClick();
    }


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

    /**
     * 加载各种状态的view
     *
     * @param inflater
     * @param container
     */
    private void loadStatesView(LayoutInflater inflater, ViewGroup container) {
        // 成功的view
        mSuccessView = loadSuccessView(inflater, container);
        mBaseContainer.addView(mSuccessView);
        // loading view
        mLoadingView = loadLoadingView(inflater, container);
        mBaseContainer.addView(mLoadingView);
        // error view
        mErrorView = loadErrorView(inflater, container);
        mBaseContainer.addView(mErrorView);
        // empty view
        mEmptyView = loadEmptyView(inflater, container);
        mBaseContainer.addView(mEmptyView);

        // 一开始都得是GONE
        setupState(State.NONE);
    }

    /**
     * 子类通过这个方法来切换页面
     *
     * @param state
     */
    public void setupState(State state) {
        this.currentState = state;
        mSuccessView.setVisibility(currentState == State.SUCCESS ? View.VISIBLE : View.GONE);
        mLoadingView.setVisibility(currentState == State.LOADING ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(currentState == State.ERROR ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(currentState == State.EMPTY ? View.VISIBLE : View.GONE);
    }

    /**
     * 空页面
     *
     * @param inflater
     * @param container
     * @return
     */
    protected View loadEmptyView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }

    /**
     * 错误页面
     *
     * @param inflater
     * @param container
     * @return
     */
    protected View loadErrorView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_error, container, false);
    }

    /**
     * 加载loading界面
     *
     * @param inflater
     * @param container
     * @return
     */
    protected View loadLoadingView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    protected View loadSuccessView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(getRootViewResId(), container, false);
    }

    protected void initView(View rootView) {
        // 初始化组件
    }

    protected void release() {
        // 释放资源
    }

    protected void initPresenter() {
        // 创建presenter

    }

    protected void loadData() {
        // 加载数据
    }

    /**
     * 如果子类fragment需要设置网络错误的点击重新加载，就重写该方法
     */
    protected void onNetworkRetryClick() {

    }

    /**
     * 所有子类必须得重写的方法，返回fragment的id
     *
     * @return
     */
    protected abstract int getRootViewResId();
}
