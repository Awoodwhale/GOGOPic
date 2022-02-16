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
import top.woodwhale.gogopic.utils.SharedPreferencesUtils;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.IHomeCallback;

@SuppressLint("NonConstantResourceId")
public class HomeFragment extends BaseFragment implements IHomeCallback, HomeContentAdapter.OnListenItemClickListener {

    @BindView(R.id.home_content_list) protected RecyclerView mHomeContent;

    private IHomePresenter mHomePresent;
    private HomeContentAdapter mHomeContentAdapter;

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
                outRect.top = 3;
                outRect.bottom = 3;
                outRect.left= 3;
                outRect.right = 3;
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
    public void loadData() {
        if (mHomePresent != null) {
            mHomePresent.getCategories();
        }
    }

    @Override
    public void onCategoriesLoaded(Categories categories) {
        // 设置所有标题
        ArrayList<String> allTitlesList = new ArrayList<>();
        for (Categories.DataBean.CategoriesBean category : categories.getData().getCategories()) {
            Categories.DataBean.CategoriesBean.ThumbBean thumb = category.getThumb();
            String imgPath = UrlUtils.getAddStaticPicPathUrl(thumb.getFileServer(),thumb.getPath());
            if (imgPath.contains(".picacomic.") ) {
                allTitlesList.add(category.getTitle());
            }
        }
        Constants.CATEGORISE_LISTS = allTitlesList;
        // 处理ban书逻辑
        if (mHomeContentAdapter != null) {
            List<Categories.DataBean.CategoriesBean> categoriesBeans = handleCategories(categories);
            ArrayList<String> bannedTitleList = new ArrayList<>();
            mHomeContentAdapter.setData(categoriesBeans);
            for (Categories.DataBean.CategoriesBean category : categoriesBeans) {
                bannedTitleList.add(category.getTitle());
            }
            Constants.BANNED_CATEGORISE_LISTS = bannedTitleList;
            showSuccess();
        }
    }

    // 将有用的信息抽取出来
    private List<Categories.DataBean.CategoriesBean> handleCategories(Categories categories) {
        List<Categories.DataBean.CategoriesBean> tmpList = new ArrayList<>();
        List<Categories.DataBean.CategoriesBean> categoriesBeanList = categories.getData().getCategories();
        List<String> banBooks = SharedPreferencesUtils.getBanBooks(requireContext());
        for (Categories.DataBean.CategoriesBean categoriesBean : categoriesBeanList) {
            Categories.DataBean.CategoriesBean.ThumbBean thumb = categoriesBean.getThumb();
            String imgPath = UrlUtils.getAddStaticPicPathUrl(thumb.getFileServer(),thumb.getPath());
            String title = categoriesBean.getTitle();
            if (imgPath.contains(".picacomic.") && !banBooks.contains(title)) {
                tmpList.add(categoriesBean);
            }
        }
        return tmpList;
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
