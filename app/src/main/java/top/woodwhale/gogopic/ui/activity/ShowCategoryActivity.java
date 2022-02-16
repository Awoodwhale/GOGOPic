package top.woodwhale.gogopic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.vondear.rxtool.view.RxToast;

import butterknife.BindView;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseActivity;
import top.woodwhale.gogopic.model.domain.ComicsCategory;
import top.woodwhale.gogopic.presenter.IComicsCategoryPresenter;
import top.woodwhale.gogopic.presenter.IComicsInfoPresenter;
import top.woodwhale.gogopic.ui.adapter.ComicsCategoryContentAdapter;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.PresenterManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.ICategoryInfoCallback;

@SuppressLint("NonConstantResourceId")
public class ShowCategoryActivity extends BaseActivity implements ICategoryInfoCallback, ComicsCategoryContentAdapter.OnListenComicsItemClickListener {

    @BindView(R.id.rv_category_content_list) RecyclerView mComicsCategoryContentListRv;
    @BindView(R.id.srl_comics_home_pager_refresh) RefreshLayout mRefreshLayout;
    @BindView(R.id.tb_category_toolbar) TitleBar mTitleBar;
    @BindView(R.id.iv_category_sort) ImageView mCategorySortIv;
    @BindView(R.id.iv_category_search) ImageView mCategorySearchIv;
    @BindView(R.id.tv_category_now_page) TextView mCategoryNowPageTv;
    @BindView(R.id.tv_category_page_select) TextView mCategoryPageSelectorTv;
    @BindView(R.id.sp_category_sort_spinner) Spinner mCategorySortSp;

    private IComicsCategoryPresenter mCategoryPresenter;
    private ComicsCategoryContentAdapter mComicsCategoryContentAdapter;
    private String mCategoryTitle;
    private int mNowSortWay;
    private int mThisCategoryPages;
    private int mThisCategoryNowPage = 1;
    private boolean mFromSearch = false;
    private String mSearchKeywords;
    private boolean mFirstSearch = false;
    private boolean mFromSort = false;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_show_category;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mCategoryTitle = intent.getStringExtra(Constants.CATEGORY_TITLE_KEY);
        if (mCategoryTitle != null) {
            mTitleBar.setTitle(mCategoryTitle);
        }
    }

    @Override
    protected void initPresenter() {
        // 逻辑层
        mCategoryPresenter = PresenterManager.getInstance().getCategoryPresenter();
        mCategoryPresenter.registerViewCallback(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initEvent() {
        // 下拉加载更多
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableAutoLoadMore(false);
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (mCategoryPresenter != null) {
                if (mThisCategoryNowPage == mThisCategoryPages) {
                    onLoadMoreEmpty();
                } else {
                    // 看看是不是从搜索过来的
                    if (mFromSearch) {
                        mCategoryPresenter.getSearchLoadMoreCategoryComics(mSearchKeywords,UrlUtils.getComicsSortWay(mNowSortWay));
                    } else {
                        mCategoryPresenter.getLoadMoreCategoryComics(mCategoryTitle,UrlUtils.getComicsSortWay(mNowSortWay));
                    }
                }
            }
        });
        // Rv的适配器
        mComicsCategoryContentAdapter = new ComicsCategoryContentAdapter();
        mComicsCategoryContentListRv.setAdapter(mComicsCategoryContentAdapter);
        // 适配器设置Rv中item的监听器
        mComicsCategoryContentAdapter.registerOnListenComicsItemClickListener(this);
        // 布局
        mComicsCategoryContentListRv.setLayoutManager(new LinearLayoutManager(this));
        mComicsCategoryContentListRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        // 标题栏返回
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                finish();
            }
        });

        mCategorySortIv.setOnClickListener(v -> {
            LogUtils.d(ShowCategoryActivity.this,"跳转");
            String[] titles = Constants.CATEGORISE_LISTS.toArray(new String[0]);
            AlertDialog.Builder listDialog = new AlertDialog.Builder(ShowCategoryActivity.this);
            listDialog.setTitle("选择分类");
            listDialog.setItems(titles, (dialog, which) -> {
                if (!titles[which].equals(mCategoryTitle)) {
                    // 一旦选择了分类，keyword就置空，改为title,并且mFromSearch成为false
                    mSearchKeywords = null;
                    mFromSearch = false;

                    mCategoryTitle = titles[which];
                    mTitleBar.setTitle(mCategoryTitle);
                    mNowSortWay = 0;    // 改回默认排序
                    mFromSort = true;
                    mCategorySortSp.setSelection(mNowSortWay,false);
                    mCategoryPresenter.getChangedCategoryComics(mCategoryTitle);
                }
            });
            listDialog.setNegativeButton("取消",null);
            listDialog.show();
        });

        mCategorySearchIv.setOnClickListener(v -> {
            LogUtils.d(ShowCategoryActivity.this, "搜索");
            Intent intent = new Intent(ShowCategoryActivity.this,SearchActivity.class);
            startActivity(intent);
        });

        mCategoryPageSelectorTv.setOnClickListener(v -> {
            LogUtils.d(ShowCategoryActivity.this,"页面跳转");
            EditText editText = new EditText(ShowCategoryActivity.this);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            AlertDialog.Builder editDialog = new AlertDialog.Builder(ShowCategoryActivity.this);
            editDialog.setTitle("请输入页数").setView(editText);
            editDialog.setPositiveButton("跳转", (dialog, which) -> {
                String page = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(page)) {
                    int tmpPage = Integer.parseInt(page);
                    if (tmpPage < 1 || tmpPage > mThisCategoryPages) {
                        RxToast.warning("请输入有效页数!");
                        return;
                    }
                    if (mFromSearch) {
                        mCategoryPresenter.getSearchPageJumpCategoryComics(mSearchKeywords,
                                UrlUtils.getComicsSortWay(mNowSortWay),
                                tmpPage);
                    } else {
                        mCategoryPresenter.getPageJumpCategoryComics(mCategoryTitle,
                                UrlUtils.getComicsSortWay(mNowSortWay),
                                tmpPage);
                    }
                }
            });
            editDialog.setNegativeButton("取消",null);
            editDialog.show();
        });

        // 在创建监听之前，一定要设置false，不然会自动调用一次
        mCategorySortSp.setSelection(0,false);
        mCategorySortSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mNowSortWay = position;
                String[] sortWay = getResources().getStringArray(R.array.sort);
                LogUtils.d(ShowCategoryActivity.this,"sort --> " + sortWay[position]);
                if (mFromSearch) {
                    if (mFirstSearch) {
                        mFirstSearch = false;
                        LogUtils.d(ShowCategoryActivity.this,"第一次来到搜索不触发");
                        return;
                    }
                    mCategoryPresenter.getSearchSortedCategoryComics(mSearchKeywords,UrlUtils.getComicsSortWay(mNowSortWay));
                } else {
                    if (mFromSort) {
                        mFromSort = false;
                        LogUtils.d(ShowCategoryActivity.this,"更改分类不触发");
                        return;
                    }
                    mCategoryPresenter.getSortedCategoryComics(mCategoryTitle, UrlUtils.getComicsSortWay(mNowSortWay));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                LogUtils.d(ShowCategoryActivity.this,"onNothingSelected...");
            }
        });
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

    @Override
    protected void contentRetry() {
        // 如果网络错误，那么我们就重新获取
        if (!mFromSearch) {
            mCategoryPresenter.getCategoryComics(mCategoryTitle,
                    UrlUtils.getComicsSortWay(mNowSortWay),mThisCategoryNowPage);
        } else {
            mCategoryPresenter.getSearchCategoryComics(mSearchKeywords);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCategoryLoaded(ComicsCategory body, int nowPage) {
        refreshRv();
        mFromSort = false;
        mComicsCategoryContentAdapter.setData(body.getData().getComics().getDocs());
        mThisCategoryPages = body.getData().getComics().getPages();
        mThisCategoryNowPage = nowPage - 1;
        LogUtils.d(this,"nowPage --> " + mThisCategoryNowPage);
        mCategoryNowPageTv.setText("已加载:"+mThisCategoryNowPage+" / "+mThisCategoryPages+"页");
        showSuccess();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onLoadMoreSuccess(ComicsCategory body, int nowPage) {
        mComicsCategoryContentAdapter.addData(body.getData().getComics().getDocs());
        mRefreshLayout.finishLoadMore();
        mThisCategoryPages = body.getData().getComics().getPages();
        mThisCategoryNowPage = nowPage - 1;
        mCategoryNowPageTv.setText("已加载:"+ mThisCategoryNowPage +" / "+mThisCategoryPages+"页");
    }

    @Override
    public void onLoadMoreError() {
        RxToast.warning("网络异常,请重试!");
        mRefreshLayout.finishLoadMore(500,false,false);
    }

    @Override
    public void onLoadMoreEmpty() {
        RxToast.info("您已经看到头啦!");
        mRefreshLayout.finishLoadMore(false);
    }

    // 搜索的成功回调
    @SuppressLint("SetTextI18n")
    @Override
    public void onSearchSuccess(ComicsCategory body, int nowPage, String keywords) {
        refreshRv();
        // 设置关键字
        mSearchKeywords = keywords;
        // 原来的标题置空
        mCategoryTitle = null;
        // 最新排序
        mFromSearch = true;
        mFirstSearch = true;
        mCategorySortSp.setSelection(3,false);
        // 设置搜索标题
        mTitleBar.setTitle(mSearchKeywords);
        // 数据传输
        mComicsCategoryContentAdapter.setData(body.getData().getComics().getDocs());
        mThisCategoryPages = body.getData().getComics().getPages();
        mThisCategoryNowPage = nowPage - 1;
        LogUtils.d(this,"nowPage --> " + mThisCategoryNowPage);
        mCategoryNowPageTv.setText("已加载:"+mThisCategoryNowPage+" / "+mThisCategoryPages+"页");
        showSuccess();
    }

    // 将rv中的孩子删除，从新开始渲染加载
    private void refreshRv() {
        int childCount = mComicsCategoryContentListRv.getChildCount();
        if (childCount > 0) {
            mComicsCategoryContentListRv.removeAllViews();
        }
    }

    // rv中item的点击事件
    @Override
    public void onItemClick(ComicsCategory.DataBean.ComicsBean.DocsBean data) {
        String comicsID = (data.getId1() == null || data.getId1().equals("")) ? data.getId2() : data.getId1();
        LogUtils.d(this,"title --> "+data.getTitle()+"id --> " + comicsID);
        Intent intent = new Intent(this,ShowComicsActivity.class);
        intent.putExtra(Constants.CATEGORY_ID_KEY,comicsID);
        IComicsInfoPresenter comicsInfoPresenter = PresenterManager.getInstance().getComicsInfoPresenter();
        comicsInfoPresenter.getComicsInfo(comicsID);
        startActivity(intent);
    }

    // 释放资源
    @Override
    protected void release() {
        if (mCategoryPresenter != null) {
            mCategoryPresenter.unregisterViewCallback(this);
        }
        if (mComicsCategoryContentAdapter != null) {
            mComicsCategoryContentAdapter.unregisterOnListenComicsItemClickListener(this);
        }
    }


}
