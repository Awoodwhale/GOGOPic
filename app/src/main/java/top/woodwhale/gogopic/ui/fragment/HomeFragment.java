package top.woodwhale.gogopic.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseFragment;
import top.woodwhale.gogopic.model.domain.Categories;
import top.woodwhale.gogopic.presenter.IComicsCategoryPresenter;
import top.woodwhale.gogopic.presenter.IHomePresenter;
import top.woodwhale.gogopic.ui.activity.ShowCategoryActivity;
import top.woodwhale.gogopic.ui.adapter.HomeContentAdapter;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.PresenterManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.IHomeCallback;

@SuppressLint("NonConstantResourceId")
public class HomeFragment extends BaseFragment implements IHomeCallback, HomeContentAdapter.OnListenItemClickListener {

    @BindView(R.id.home_content_list) protected RecyclerView mHomeContent;

    private IHomePresenter mHomePresent;
    private HomeContentAdapter mHomeContentAdapter;
    private ArrayList<String> mTitlesList = new ArrayList<>();

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootView) {
        // 设置布局管理器
        mHomeContent.setLayoutManager(new GridLayoutManager(getContext(),3));
        // 添加分割线
        // 设置间距
        mHomeContent.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 6;
                outRect.bottom = 6;
                outRect.left= 6;
                outRect.right = 6;
            }
        });
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initPresenter() {
        // 创建逻辑层
        mHomePresent = PresenterManager.getInstance().getHomePresenter();
        mHomePresent.registerViewCallback(this);
        // 创建适配器逻辑
        mHomeContentAdapter = new HomeContentAdapter();
        // 设置适配器逻辑
        mHomeContent.setAdapter(mHomeContentAdapter);
        mHomeContentAdapter.registerOnListenItemClickListener(this);
    }

    @Override
    public void onItemClick(Categories.DataBean.CategoriesBean data) {
        LogUtils.d(this,"onItemClick...");
        IComicsCategoryPresenter categoryPresenter = PresenterManager.getInstance().getCategoryPresenter();
        String title = data.getTitle().trim();
        String url = UrlUtils.getComicsCategoryInfoUrl("1",title);
        if (url != null) {
            categoryPresenter.getCategoryComics(title,"ua",1);
            Intent intent = new Intent(requireContext(),ShowCategoryActivity.class);
            intent.putExtra(Constants.CATEGORY_TITLE_KEY,title);
            startActivity(intent);
        }
    }

    @Override
    protected void loadData() {
        if (mHomePresent != null) {
            mHomePresent.getCategories();
        }
    }

    @Override
    public void onCategoriesLoaded(List<Categories.DataBean.CategoriesBean> categories) {
        if (mHomeContentAdapter != null) {
            mHomeContentAdapter.setData(categories);
            for (Categories.DataBean.CategoriesBean category : categories) {
                mTitlesList.add(category.getTitle());
            }
            Constants.CATEGORISE_LISTS = mTitlesList;
            showSuccess();
        }
    }

    // 重试就是重新加载
    @Override
    protected void contentRetry() {
        loadData();
    }

    @Override
    protected void release() {
        // 最后需要释放资源
        if (mHomePresent != null) {
            mHomePresent.unregisterViewCallback(this);
        }
        if (mHomeContentAdapter != null) {
            mHomeContentAdapter.unregisterOnListenItemClickListener(this);
        }
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


}
