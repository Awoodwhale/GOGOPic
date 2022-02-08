package top.woodwhale.gogopic.ui.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.vondear.rxtool.view.RxToast;

import java.util.List;

import butterknife.BindView;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseActivity;
import top.woodwhale.gogopic.model.domain.ComicsCategory;
import top.woodwhale.gogopic.presenter.ICategoryPresenter;
import top.woodwhale.gogopic.ui.adapter.ComicsCategoryContentAdapter;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.PresenterManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.ICategoryCallback;

@SuppressLint("NonConstantResourceId")
public class ShowCategoryActivity extends BaseActivity implements ICategoryCallback {

    @BindView(R.id.rv_category_content_list) RecyclerView mComicsCategoryContentListRv;
    @BindView(R.id.srl_comics_home_pager_refresh) RefreshLayout mRefreshLayout;
    @BindView(R.id.tb_category_toolbar) TitleBar mTitleBar;
    @BindView(R.id.iv_category_sort) ImageView mCategorySortIv;
    @BindView(R.id.iv_category_search) ImageView mCategorySearchIv;
    @BindView(R.id.tv_category_now_page) TextView mCategoryNowPageTv;
    @BindView(R.id.tv_category_page_select) TextView mCategoryPageSelectorTv;
    @BindView(R.id.sp_category_sort_spinner) Spinner mCategorySortSp;

    private ICategoryPresenter mCategoryPresenter;
    private String mCategoryUrl;
    private ComicsCategoryContentAdapter mComicsCategoryContentAdapter;
    private String mCategoryTitle;
    private int mNowSortWay;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_show_category;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mCategoryTitle = intent.getStringExtra(Constants.CATEGORY_TITLE_KEY);
        mCategoryUrl = intent.getStringExtra(Constants.CATEGORY_URL_KEY);
        mTitleBar.setTitle(mCategoryTitle);
    }

    @Override
    protected void initPresenter() {
        // 逻辑层
        mCategoryPresenter = PresenterManager.getInstance().getCategoryPresenter();
        mCategoryPresenter.registerViewCallback(this);
    }

    @Override
    protected void initEvent() {
        // 下拉加载更多
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableAutoLoadMore(false);
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mCategoryPresenter != null) {
                    mCategoryPresenter.getLoadMoreCategoryComics(mCategoryTitle,UrlUtils.getComicsSortWay(mNowSortWay));
                }
            }
        });
        // Rv的适配器
        mComicsCategoryContentAdapter = new ComicsCategoryContentAdapter();
        mComicsCategoryContentListRv.setAdapter(mComicsCategoryContentAdapter);
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
        // TODO：设置跳转分类事件
        mCategorySortIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d(ShowCategoryActivity.this,"跳转");
            }
        });
        // TODO：设置搜索事件
        mCategorySearchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d(ShowCategoryActivity.this,"搜索");
            }
        });
        // TODO:设置页数跳转事件
        mCategoryPageSelectorTv.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                LogUtils.d(ShowCategoryActivity.this,"页面跳转");
                final EditText editText = new EditText(ShowCategoryActivity.this);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                AlertDialog.Builder editDialog = new AlertDialog.Builder(ShowCategoryActivity.this);
                editDialog.setTitle("请输入页数").setView(editText);
                editDialog.setPositiveButton("跳转", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String page = editText.getText().toString().trim();
                        if (!TextUtils.isEmpty(page)) {
                            int tmpPage = Integer.parseInt(page);
                            if (tmpPage < 1) {
                                RxToast.warning("请输入有效页数!");
                                return;
                            }
                            mCategoryPresenter.getPageJumpCategoryComics(mCategoryTitle,
                                    UrlUtils.getComicsSortWay(mNowSortWay),
                                    tmpPage);
                            mCategoryNowPageTv.setText("已加载:"+tmpPage+"页");
                        }
                    }
                });
                editDialog.setNegativeButton("取消",null);
                editDialog.show();
            }
        });
        // TODO:设置排序选择事件
        mCategorySortSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mNowSortWay = position;
                String[] sortWay = getResources().getStringArray(R.array.sort);
                LogUtils.d(ShowCategoryActivity.this,"sort --> " + sortWay[position]);
                mCategoryNowPageTv.setText("已加载:1页");
                mCategoryPresenter.getSortedCategoryComics(mCategoryTitle, UrlUtils.getComicsSortWay(mNowSortWay));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        mCategoryPresenter.getCategoryComics(mCategoryUrl);
    }

    @Override
    public void onCategoryLoaded(ComicsCategory body) {
        List<ComicsCategory.DataBean.ComicsBean.DocsBean> docs = body.getData().getComics().getDocs();
        if (docs != null) {
            String string = body.toString();
            LogUtils.d(this,"content --> " + string);
            mComicsCategoryContentAdapter.setData(docs);
            showSuccess();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onLoadMoreSuccess(ComicsCategory body, int nowPage) {
        List<ComicsCategory.DataBean.ComicsBean.DocsBean> docs = body.getData().getComics().getDocs();
        if (docs != null) {
            mComicsCategoryContentAdapter.addData(docs);
            mRefreshLayout.finishLoadMore();
            mCategoryNowPageTv.setText("已加载:"+ (nowPage - 1) +"页");
        }
    }

    @Override
    public void onLoadMoreError() {
        mRefreshLayout.finishLoadMore(500,false,false);
        RxToast.warning("网络异常,请重试!");
    }

    @Override
    public void onLoadMoreEmpty() {
        mRefreshLayout.finishLoadMoreWithNoMoreData();
        RxToast.info("您已经看到头啦!");
    }

    @Override
    protected void release() {
        if (mCategoryPresenter != null) {
            mCategoryPresenter.unregisterViewCallback(this);
        }
    }
}
