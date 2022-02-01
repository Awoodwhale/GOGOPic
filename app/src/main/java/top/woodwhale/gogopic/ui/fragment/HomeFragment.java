package top.woodwhale.gogopic.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.model.domain.Categories;
import top.woodwhale.gogopic.presenter.IHomePresent;
import top.woodwhale.gogopic.presenter.impl.HomePresentImpl;
import top.woodwhale.gogopic.view.IHomeCallback;

@SuppressLint("NonConstantResourceId")
public class HomeFragment extends Fragment implements IHomeCallback {

    @BindView(R.id.base_container) protected FrameLayout mBaseContainer;
    @BindView(R.id.bt_test) protected Button mButton;
    private Unbinder mBind;
    private IHomePresent mHomePresent;

    @OnClick({R.id.bt_test})
    public void test(View v) {
        if (v.getId() == R.id.bt_test) {
            loadData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = loadRootView(inflater,container);
        mBind = ButterKnife.bind(this, rootView);

        initView(rootView);
        initPresenter();
        loadData();
        return rootView;
    }

    private void initView(View rootView) {
    }

    private void initPresenter() {
        mHomePresent = new HomePresentImpl();
        mHomePresent.registerViewCallback(this);
    }

    private void loadData() {
        mHomePresent.getCategories();
    }

    private View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_fragment_layout, container, false);
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
    public void onCategoriesLoaded(Categories categories) {

    }
}
