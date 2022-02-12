package top.woodwhale.gogopic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;

import com.vondear.rxtool.view.RxToast;

import java.util.List;

import butterknife.BindView;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseActivity;
import top.woodwhale.gogopic.model.domain.Keywords;
import top.woodwhale.gogopic.presenter.IComicsCategoryPresenter;
import top.woodwhale.gogopic.presenter.IKeywordsPresenter;
import top.woodwhale.gogopic.utils.PresenterManager;
import top.woodwhale.gogopic.utils.SharedPreferencesUtils;
import top.woodwhale.gogopic.view.IKeywordsCallback;

@SuppressLint("NonConstantResourceId")
public class SearchActivity extends BaseActivity implements IKeywordsCallback {

    @BindView(R.id.tcl_people_search) TagContainerLayout mPeopleSearchTagContainerLayout;
    @BindView(R.id.tcl_history_search) TagContainerLayout mHistorySearchTagContainerLayout;
    @BindView(R.id.sv_search_view) SearchView mSearchView;
    @BindView(R.id.tv_clear_history_search) TextView mClearHistorySearchTv;
    @BindView(R.id.tv_search_cancel) TextView mCancelSearchTv;
    private IKeywordsPresenter mSearchPresenter;
    private boolean mEnableClear = false;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        // 每次开始设置
        List<String> searchHistory = SharedPreferencesUtils.getSearchHistory(this);
        if (searchHistory.size() != 0) {
            mHistorySearchTagContainerLayout.setTags(searchHistory);
            // 允许清空历史
            mEnableClear = true;
        }
    }

    @Override
    protected void initPresenter() {
        // 关键词逻辑层
        mSearchPresenter = PresenterManager.getInstance().getKeywordsPresenter();
        mSearchPresenter.registerViewCallback(this);
    }

    @Override
    protected void initEvent() {
        // 取消就退出当前界面
        mCancelSearchTv.setOnClickListener(v -> finish());
        // 获取热搜词
        mSearchPresenter.getKeywords();
        // 搜素框监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query)) {
                    searchQuery(query);
                } else {
                    RxToast.warning("请输入关键字搜索噢!");
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        // 清除历史记录监听
        mClearHistorySearchTv.setOnClickListener(v -> {
            if (mEnableClear) {
                SharedPreferencesUtils.clearSearchHistory(SearchActivity.this);
                // 清空之后要将显示也清空
                mHistorySearchTagContainerLayout.setTags();
                mEnableClear = false;
            } else {
                RxToast.info("历史记录为空!");
            }
        });

        // 点击历史记录监听
        mHistorySearchTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                searchQuery(text);
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onSelectedTagDrag(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });
        // 热搜点击监听
        mPeopleSearchTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                searchQuery(text);
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onSelectedTagDrag(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });
    }

    // 搜索
    private void searchQuery(String query) {
        // 先将搜索记录存起来
        SharedPreferencesUtils.saveSearchHistory(query,this);
        // 跳转到categoryComics页面，跳转之前就开始在逻辑层网络请求
        IComicsCategoryPresenter categoryPresenter = PresenterManager.getInstance().getCategoryPresenter();
        categoryPresenter.getSearchCategoryComics(query);
        Intent intent = new Intent(this,ShowCategoryActivity.class);
        startActivity(intent);
        finish();
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
        showSuccess();
    }

    @Override
    protected void contentRetry() {
        mSearchPresenter.getKeywords();
    }

    @Override
    public void onKeywordsLoaded(Keywords keywords) {
        List<String> keywordsList = keywords.getData().getKeywords();
        mPeopleSearchTagContainerLayout.setTags(keywordsList);
        showSuccess();
    }

    @Override
    protected void release() {
        if (mSearchPresenter != null) {
            mSearchPresenter.unregisterViewCallback(this);
        }
    }
}
