package top.woodwhale.gogopic.ui.fragment;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseFragment;
import top.woodwhale.gogopic.model.domain.Categories;
import top.woodwhale.gogopic.presenter.IHomePresent;
import top.woodwhale.gogopic.presenter.impl.HomePresentImpl;
import top.woodwhale.gogopic.ui.adapter.HomeContentAdapter;
import top.woodwhale.gogopic.view.IHomeCallback;

@SuppressLint("NonConstantResourceId")
public class HomeFragment extends BaseFragment implements IHomeCallback {

    @BindView(R.id.home_content_list) protected RecyclerView mHomeContent;
    private IHomePresent mHomePresent;
    private HomeContentAdapter mHomeContentAdapter;

    @Override
    protected void initView(View rootView) {
        // 设置布局管理器
        mHomeContent.setLayoutManager(new GridLayoutManager(getContext(),3));
        // 设置间距
        mHomeContent.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 5;
                outRect.bottom = 5;
                outRect.left= 5;
                outRect.right = 5;
            }
        });
        // 创建适配器
        mHomeContentAdapter = new HomeContentAdapter();
        // 设置适配器
        mHomeContent.setAdapter(mHomeContentAdapter);
    }

    @Override
    protected void initPresenter() {
        mHomePresent = new HomePresentImpl();
        mHomePresent.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        mHomePresent.getCategories();
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onNetworkError() {
        setupState(State.ERROR);
    }

    @Override
    public void onLoading() {
        setupState(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setupState(State.EMPTY);
    }

    @Override
    public void onCategoriesLoaded(List<Categories.DataBean.CategoriesBean> categories) {
        if (mHomeContentAdapter != null) {
            setupState(State.SUCCESS);
            mHomeContentAdapter.setData(categories);
        }
    }

    @Override
    protected void release() {
        // 最后需要释放资源
        if (mHomePresent != null) {
            mHomePresent.unregisterViewCallback(this);
        }
    }

    // 如果网络错误，点击重试，那么重新加载数据
    @Override
    protected void onNetworkRetryClick() {
        loadData();
    }
}
