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
public class ShowCategoryActivity extends BaseActivity implements ICategoryInfoCallback,
        ComicsCategoryContentAdapter.OnListenComicsItemClickListener {

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
    private boolean mFirstSelect = true;

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
        // ?????????
        mCategoryPresenter = PresenterManager.getInstance().getCategoryPresenter();
        mCategoryPresenter.registerViewCallback(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initEvent() {
        // ??????????????????
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableAutoLoadMore(false);
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (mCategoryPresenter != null) {
                if (mThisCategoryNowPage == mThisCategoryPages) {
                    onLoadMoreEmpty();
                } else {
                    // ?????????????????????????????????
                    if (mFromSearch) {
                        mCategoryPresenter.getSearchLoadMoreCategoryComics(mSearchKeywords,UrlUtils.getComicsSortWay(mNowSortWay));
                    } else {
                        mCategoryPresenter.getLoadMoreCategoryComics(mCategoryTitle,UrlUtils.getComicsSortWay(mNowSortWay));
                    }
                }
            }
        });
        // Rv????????????
        mComicsCategoryContentAdapter = new ComicsCategoryContentAdapter();
        mComicsCategoryContentListRv.setAdapter(mComicsCategoryContentAdapter);
        // ???????????????Rv???item????????????
        mComicsCategoryContentAdapter.registerOnListenComicsItemClickListener(this);
        // ??????
        mComicsCategoryContentListRv.setLayoutManager(new LinearLayoutManager(this));
        mComicsCategoryContentListRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        // ???????????????
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                finish();
            }
        });

        mCategorySortIv.setOnClickListener(v -> {
            LogUtils.d(ShowCategoryActivity.this,"??????");
            String[] titles = Constants.BANNED_CATEGORISE_LISTS.toArray(new String[0]);
            AlertDialog.Builder listDialog = new AlertDialog.Builder(ShowCategoryActivity.this);
            listDialog.setTitle("????????????");
            listDialog.setItems(titles, (dialog, which) -> {
                if (!titles[which].equals(mCategoryTitle)) {
                    // ????????????????????????keyword??????????????????title,??????mFromSearch??????false
                    mSearchKeywords = null;
                    mFromSearch = false;
                    mCategoryTitle = titles[which];
                    mTitleBar.setTitle(mCategoryTitle);
                    mNowSortWay = 0;    // ??????????????????
                    mFromSort = true;
                    mCategorySortSp.setSelection(mNowSortWay,false);
                    mCategoryPresenter.getChangedCategoryComics(mCategoryTitle);
                }
            });
            listDialog.setNegativeButton("??????",null);
            listDialog.show();
        });

        mCategorySearchIv.setOnClickListener(v -> {
            LogUtils.d(ShowCategoryActivity.this, "??????");
            Intent intent = new Intent(ShowCategoryActivity.this,SearchActivity.class);
            startActivity(intent);
        });

        mCategoryPageSelectorTv.setOnClickListener(v -> {
            LogUtils.d(ShowCategoryActivity.this,"????????????");
            EditText editText = new EditText(ShowCategoryActivity.this);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            AlertDialog.Builder editDialog = new AlertDialog.Builder(ShowCategoryActivity.this);
            editDialog.setTitle("???????????????").setView(editText);
            editDialog.setPositiveButton("??????", (dialog, which) -> {
                String page = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(page)) {
                    int tmpPage = Integer.parseInt(page);
                    if (tmpPage < 1 || tmpPage > mThisCategoryPages) {
                        RxToast.warning("?????????????????????!");
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
            editDialog.setNegativeButton("??????",null);
            editDialog.show();
        });

        mCategorySortSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mFirstSelect) {
                    mFirstSelect = false;
                } else {
                    mNowSortWay = position;
                    String[] sortWay = getResources().getStringArray(R.array.sort);
                    LogUtils.d(ShowCategoryActivity.this,"sort --> " + sortWay[position]);
                    LogUtils.d(ShowCategoryActivity.this,"mFromSearch --> " + mFromSearch);
                    if (mFromSearch) {
                        if (mFirstSearch) {
                            mFirstSearch = false;
                            LogUtils.d(ShowCategoryActivity.this,"??????????????????????????????");
                            return;
                        }
                        mCategoryPresenter.getSearchSortedCategoryComics(mSearchKeywords,UrlUtils.getComicsSortWay(mNowSortWay));
                    } else {
                        if (mFromSort) {
                            mFromSort = false;
                            LogUtils.d(ShowCategoryActivity.this,"?????????????????????");
                            return;
                        }
                        mCategoryPresenter.getSortedCategoryComics(mCategoryTitle, UrlUtils.getComicsSortWay(mNowSortWay));
                    }
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
        // ????????????????????????????????????????????????
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
        mCategoryNowPageTv.setText("?????????:"+mThisCategoryNowPage+" / "+mThisCategoryPages+"???");
        showSuccess();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onLoadMoreSuccess(ComicsCategory body, int nowPage) {
        mComicsCategoryContentAdapter.addData(body.getData().getComics().getDocs());
        mRefreshLayout.finishLoadMore();
        mThisCategoryPages = body.getData().getComics().getPages();
        mThisCategoryNowPage = nowPage - 1;
        mCategoryNowPageTv.setText("?????????:"+ mThisCategoryNowPage +" / "+mThisCategoryPages+"???");
    }

    @Override
    public void onLoadMoreError() {
        RxToast.warning("????????????,?????????!");
        mRefreshLayout.finishLoadMore(500,false,false);
    }

    @Override
    public void onLoadMoreEmpty() {
        RxToast.info("?????????????????????!");
        mRefreshLayout.finishLoadMore(false);
    }

    // ?????????????????????
    @SuppressLint("SetTextI18n")
    @Override
    public void onSearchSuccess(ComicsCategory body, int nowPage, String keywords) {
        LogUtils.d(this,"onSearchSuccess...");
        refreshRv();
        // ???????????????
        mSearchKeywords = keywords;
        // ?????????????????????
        mCategoryTitle = null;
        // ????????????
        mFromSearch = true;
        mFirstSearch = true;
        mCategorySortSp.setSelection(3,false);
        // ??????????????????
        mTitleBar.setTitle(mSearchKeywords);
        // ????????????
        mComicsCategoryContentAdapter.setData(body.getData().getComics().getDocs());
        mThisCategoryPages = body.getData().getComics().getPages();
        mThisCategoryNowPage = nowPage - 1;
        LogUtils.d(this,"nowPage --> " + mThisCategoryNowPage);
        mCategoryNowPageTv.setText("?????????:"+mThisCategoryNowPage+" / "+mThisCategoryPages+"???");
        showSuccess();
    }

    // ???rv?????????????????????????????????????????????
    private void refreshRv() {
        int childCount = mComicsCategoryContentListRv.getChildCount();
        if (childCount > 0) {
            mComicsCategoryContentListRv.removeAllViews();
        }
    }

    // rv???item???????????????
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

    // ????????????
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
