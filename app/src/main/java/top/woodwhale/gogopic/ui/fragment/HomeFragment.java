package top.woodwhale.gogopic.ui.fragment;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.model.domain.Categories;
import top.woodwhale.gogopic.presenter.IHomePresent;
import top.woodwhale.gogopic.presenter.impl.HomePresentImpl;
import top.woodwhale.gogopic.ui.adapter.HomeContentAdapter;
import top.woodwhale.gogopic.view.IHomeCallback;

@SuppressLint("NonConstantResourceId")
public class HomeFragment extends Fragment implements IHomeCallback {

    @BindView(R.id.base_container) protected FrameLayout mBaseContainer;
    @BindView(R.id.home_content_list) protected RecyclerView mHomeContent;
    private Unbinder mBind;
    private IHomePresent mHomePresent;
    private View mRootView;
    private HomeContentAdapter mHomeContentAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = loadRootView(inflater,container);
        mBind = ButterKnife.bind(this, mRootView);

        initView(mRootView);
        initPresenter();
        loadData();
        return mRootView;
    }

    private void initView(View rootView) {
        mHomeContent.setLayoutManager(new GridLayoutManager(getContext(),3));
        mHomeContent.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 9;
                outRect.bottom = 9;
            }
        });
        // 创建适配器
        mHomeContentAdapter = new HomeContentAdapter();
        // 设置适配器
        mHomeContent.setAdapter(mHomeContentAdapter);
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
        mHomeContentAdapter.setData(categories);
    }
}
