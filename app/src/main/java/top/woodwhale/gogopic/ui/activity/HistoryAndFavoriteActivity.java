package top.woodwhale.gogopic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.vondear.rxtool.view.RxToast;

import butterknife.BindView;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseActivity;
import top.woodwhale.gogopic.model.domain.ComicsCategory;
import top.woodwhale.gogopic.presenter.IComicsInfoPresenter;
import top.woodwhale.gogopic.presenter.IHistoryAndFavoritePresenter;
import top.woodwhale.gogopic.ui.adapter.HistoryAndFavoriteContentAdapter;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.PresenterManager;
import top.woodwhale.gogopic.view.IHistoryAndFavoriteCallback;

@SuppressLint("NonConstantResourceId")
public class HistoryAndFavoriteActivity extends BaseActivity implements IHistoryAndFavoriteCallback, HistoryAndFavoriteContentAdapter.OnListenComicsItemClickListener {

    @BindView(R.id.tv_category_now_page) TextView mNowPageTv;
    @BindView(R.id.tb_history_and_favorite_toolbar) TitleBar mTitleBar;
    @BindView(R.id.rv_history_and_favorite_content_list) RecyclerView mHistoryAndFavoriteContentListRv;
    @BindView(R.id.srl_history_and_favorite_refresh) SmartRefreshLayout mHistoryAndFavoriteRefreshSrl;
    private boolean mIsFavoriteFrom;
    private IHistoryAndFavoritePresenter mHistoryAndFavoritePresenter;
    private HistoryAndFavoriteContentAdapter mHistoryAndFavoriteContentAdapter;
    private int mNowPage;
    private int mNowPages;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_history_and_favorite;
    }

    @Override
    protected void initView() {
        onLoading();
        Intent intent = getIntent();
        mIsFavoriteFrom = intent.getBooleanExtra(Constants.HISTORY_OR_FAVORITE_KEY, false);
        mTitleBar.setTitle(mIsFavoriteFrom ? "我的收藏" : "浏览历史");
        // 返回按钮
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                finish();
            }
        });
        // 设置rv适配器
        mHistoryAndFavoriteContentAdapter = new HistoryAndFavoriteContentAdapter();
        mHistoryAndFavoriteContentListRv.setAdapter(mHistoryAndFavoriteContentAdapter);
        // 设置rv布局
        mHistoryAndFavoriteContentListRv.setLayoutManager(new LinearLayoutManager(this));
        // rv适配器的逻辑层
        mHistoryAndFavoriteContentAdapter.registerOnListenComicsItemClickListener(this);
    }

    @Override
    protected void initPresenter() {
        // 逻辑层
        mHistoryAndFavoritePresenter = PresenterManager.getInstance().getHistoryAndFavoritePresenter();
        mHistoryAndFavoritePresenter.registerViewCallback(this);
    }

    @Override
    protected void initEvent() {
        // 刚过来就查询历史或者收藏
        if (mIsFavoriteFrom) {
            mHistoryAndFavoritePresenter.getFavoriteContent(1);
        } else {
            mHistoryAndFavoritePresenter.getHistoryContent();
        }
        mHistoryAndFavoriteRefreshSrl.setEnableRefresh(false);
        mHistoryAndFavoriteRefreshSrl.setEnableAutoLoadMore(false);
        mHistoryAndFavoriteRefreshSrl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mHistoryAndFavoritePresenter != null) {
                    mHistoryAndFavoritePresenter.getFavoriteMoreContent();
                }
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
    public void onHistoryContentLoaded() {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onFavoriteContentLoaded(ComicsCategory body, int page) {
        mHistoryAndFavoriteContentAdapter.setData(body.getData().getComics().getDocs());
        mNowPage = page-1;
        mNowPages = body.getData().getComics().getPages();
        mNowPageTv.setText("已加载:"+mNowPage+" / "+mNowPages+"页");
        showSuccess();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onFavoriteMoreContentLoaded(ComicsCategory body, int page) {
        mHistoryAndFavoriteContentAdapter.addData(body.getData().getComics().getDocs());
        mNowPage = page-1;
        mNowPages = body.getData().getComics().getPages();
        mNowPageTv.setText("已加载:"+mNowPage+" / "+mNowPages+"页");
        mHistoryAndFavoriteRefreshSrl.finishLoadMore(true);
    }

    @Override
    public void onFavoriteMoreContentEmpty() {
        RxToast.info("您已经看到头啦!");
        mHistoryAndFavoriteRefreshSrl.finishLoadMore(false);
    }

    @Override
    public void onFavoriteMoreContentError() {
        RxToast.warning("网络异常,请重试!");
        mHistoryAndFavoriteRefreshSrl.finishLoadMore(500,false,false);
    }

    @Override
    public void onItemClick(ComicsCategory.DataBean.ComicsBean.DocsBean data) {
        // item点击事件
        String comicsID = (data.getId1() == null || data.getId1().equals("")) ? data.getId2() : data.getId1();
        LogUtils.d(this,"title --> "+data.getTitle()+"id --> " + comicsID);
        Intent intent = new Intent(this,ShowComicsActivity.class);
        intent.putExtra(Constants.CATEGORY_ID_KEY,comicsID);
        IComicsInfoPresenter comicsInfoPresenter = PresenterManager.getInstance().getComicsInfoPresenter();
        comicsInfoPresenter.getComicsInfo(comicsID);
        startActivity(intent);
    }

    @Override
    protected void release() {
        if (mHistoryAndFavoritePresenter != null) {
            mHistoryAndFavoritePresenter.unregisterViewCallback(this);
        }
        if (mHistoryAndFavoriteContentAdapter != null) {
            mHistoryAndFavoriteContentAdapter.unregisterOnListenComicsItemClickListener(this);
        }
    }
}
