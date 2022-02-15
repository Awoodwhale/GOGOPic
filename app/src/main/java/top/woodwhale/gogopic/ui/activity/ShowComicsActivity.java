package top.woodwhale.gogopic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.vondear.rxtool.view.RxToast;

import java.util.List;

import butterknife.BindView;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseActivity;
import top.woodwhale.gogopic.model.domain.ComicsMain;
import top.woodwhale.gogopic.model.domain.LikeOrFavorite;
import top.woodwhale.gogopic.presenter.IComicsCategoryPresenter;
import top.woodwhale.gogopic.presenter.IComicsInfoPresenter;
import top.woodwhale.gogopic.ui.adapter.Vp2FragmentAdapter;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.PresenterManager;
import top.woodwhale.gogopic.view.IComicsInfoCallback;

// 查看漫画详情的activity
@SuppressLint("NonConstantResourceId")
public class ShowComicsActivity extends BaseActivity implements IComicsInfoCallback {

    @BindView(R.id.iv_comics_category_front_cover) ImageView mFrontCoverIv;
    @BindView(R.id.tv_comics_category_title) TextView mTitleTv;
    @BindView(R.id.iv_comics_category_author) TextView mAuthorTv;
    @BindView(R.id.tv_comics_category_classification) TextView mClassificationTv;
    @BindView(R.id.tv_comics_category_is_it_over) TextView mIsOverTv;
    @BindView(R.id.tv_comics_category_love_count) TextView mLoveCountTv;
    @BindView(R.id.tv_comics_category_pagination) TextView mPaginationTv;
    @BindView(R.id.tv_comics_category_watch_count) TextView mWatchCountTv;
    @BindView(R.id.tcl_comics_tag) TagContainerLayout mComicsTagsTcl;
    @BindView(R.id.tb_comics_main_info_title) TitleBar mTitleBar;
    @BindView(R.id.iv_comics_click_like) ImageView mClickLikeIv;
    @BindView(R.id.iv_comics_click_collect) ImageView mClickCollectIv;
    @BindView(R.id.tv_comics_description) TextView mComicsDescriptionTv;
    @BindView(R.id.tv_comics_creator) TextView mComicsCreatorTv;
    @BindView(R.id.tv_comics_chineseTeam) TextView mComicsChineseTeam;
    @BindView(R.id.tl_comics_tabs) TabLayout mComicsTabsTb;
    @BindView(R.id.vp2_comics_chapter_or_comment) ViewPager2 mComicsChapterOrCommentVp2;

    private String mComicsID;
    private IComicsInfoPresenter mComicsInfoPresenter;
    private boolean mIsCollect;
    private boolean mIsLiked;
    private boolean mCanTouchLike = true;
    private boolean mCanTouchFavorite = true;
    private int mCommentsCount;
    private Vp2FragmentAdapter mVp2FragmentAdapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_show_comics;
    }

    @Override
    protected void initView() {
        // 一进来就加载
        onLoading();
        Intent intent = getIntent();
        mComicsID = intent.getStringExtra(Constants.CATEGORY_ID_KEY);
        LogUtils.d(this,"id --> " + mComicsID);
    }

    @Override
    protected void initPresenter() {
        mComicsInfoPresenter = PresenterManager.getInstance().getComicsInfoPresenter();
        mComicsInfoPresenter.registerViewCallback(this);
    }

    @Override
    protected void initEvent() {
        // 返回退出
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                finish();
            }
        });
        // 喜欢的点击事件
        mClickLikeIv.setOnClickListener(v -> {
            if (mCanTouchLike && mComicsInfoPresenter != null) {
                mComicsInfoPresenter.getComicsLike(mComicsID);
                mClickLikeIv.setImageResource(R.mipmap.refresh_icon);
                mCanTouchLike = false;
            }
        });
        // 收藏的点击事件
        mClickCollectIv.setOnClickListener(v -> {
            if (mCanTouchFavorite && mComicsInfoPresenter != null) {
                mComicsInfoPresenter.getComicsFavorite(mComicsID);
                mClickCollectIv.setImageResource(R.mipmap.refresh_icon);
                mCanTouchFavorite = false;
            }
        });
        // 设置点击tag事件
        mComicsTagsTcl.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                doSearchTagsOrAuthor(text);
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

        // 设置点击作者事件
        mAuthorTv.setOnClickListener(v -> doSearchTagsOrAuthor(mAuthorTv.getText().toString()));
    }

    // 搜索跳转
    private void doSearchTagsOrAuthor(String text) {
        IComicsCategoryPresenter categoryPresenter = PresenterManager.getInstance().getCategoryPresenter();
        categoryPresenter.getSearchCategoryComics(text);
        Intent intent = new Intent(ShowComicsActivity.this,ShowCategoryActivity.class);
        startActivity(intent);
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
    public void onComicsInfoLoaded(ComicsMain body) {
        ComicsMain.DataBean.ComicBean comic = body.getData().getComic();
        mCommentsCount = body.getData().getComic().getCommentsCount();
        handleLoadView(comic);
        showSuccess();
    }

    @Override
    public void onComicsLikeLoaded(LikeOrFavorite body) {
        String action = body.getData().getAction();
        if (action.equals("like")) {
            // 如果是进行了点赞，将爱心改为实心
            mClickLikeIv.setImageResource(R.mipmap.like);
            mIsLiked = true;
        } else {
            // 取消点赞
            mClickLikeIv.setImageResource(R.mipmap.not_like);
            mIsLiked = false;
        }
        mCanTouchLike = true;
    }

    @Override
    public void onComicsLikeError() {
        mClickLikeIv.setImageResource(mIsLiked ? R.mipmap.like : R.mipmap.not_like);
        mCanTouchLike = true;
        RxToast.warning("网络错误!请重试");
    }

    @Override
    public void onComicsLikeEmpty() {
        mClickLikeIv.setImageResource(mIsLiked ? R.mipmap.like : R.mipmap.not_like);
        mCanTouchLike = true;
        RxToast.warning("服务器请求为空!请重试");
    }

    @Override
    public void onComicsFavoriteLoaded(LikeOrFavorite body) {
        String action = body.getData().getAction();
        if (action.equals("favourite")) {
            // 如果是进行了点赞，将爱心改为实心
            mClickCollectIv.setImageResource(R.mipmap.collect);
            mIsCollect = true;
        } else {
            // 取消点赞
            mClickCollectIv.setImageResource(R.mipmap.not_collect);
            mIsCollect = false;
        }
        mCanTouchFavorite = true;
    }

    @Override
    public void onComicsFavoriteError() {
        mClickCollectIv.setImageResource(mIsCollect ? R.mipmap.collect : R.mipmap.not_collect);
        mCanTouchFavorite = true;
        RxToast.warning("网络错误!请重试");
    }

    @Override
    public void onComicsFavoriteEmpty() {
        mClickCollectIv.setImageResource(mIsCollect ? R.mipmap.collect : R.mipmap.not_collect);
        mCanTouchFavorite = true;
        RxToast.warning("服务器请求为空!请重试");
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    private void handleLoadView(ComicsMain.DataBean.ComicBean comic) {
        // 处理fragment
        handleFragment();
        // 设置标题
        mTitleBar.setTitle(comic.getTitle());
        // 设置是否喜欢和是否收藏
        mIsCollect = comic.isIsFavourite();
        mIsLiked = comic.isIsLiked();
        mClickCollectIv.setImageResource(mIsCollect ? R.mipmap.collect : R.mipmap.not_collect);
        mClickLikeIv.setImageResource(mIsLiked ? R.mipmap.like : R.mipmap.not_like);
        // 设置漫画描述
        mComicsDescriptionTv.setText(comic.getDescription());
        // 设置漫画上传者
        String name = comic.getCreator().getName();
        String uploadTime = " (" +(comic.getUpdatedAt().split("\\.")[0]).
                replace("T", " ")+")";
        mComicsCreatorTv.setText(name + uploadTime);
        // 设置汉化组
        mComicsChineseTeam.setText(comic.getChineseTeam());
        // 设置tags
        mComicsTagsTcl.setTags(comic.getTags());
        // 喜欢人数
        int likesCount = comic.getLikesCount();
        mLoveCountTv.setText(likesCount+"");
        // 观看人数
        int watchCount = comic.getViewsCount();
        mWatchCountTv.setText(watchCount+"");
        // 作者
        String author = comic.getAuthor();
        mAuthorTv.setText(author);
        // 标题
        String title = comic.getTitle();
        mTitleTv.setText(title);
        // 分类
        List<String> categories = comic.getCategories();
        StringBuilder classifications = new StringBuilder();
        for (String category : categories) {
            classifications.append(category).append(" ");
        }
        mClassificationTv.setText(classifications.toString());
        // 是否完结
        mIsOverTv.setText(comic.isFinished() ? "完结" : "连载");
        mIsOverTv.setBackground(getDrawable(R.drawable.shape_tab_bg));
        // 分页
        int epsCount = comic.getEpsCount();
        int pagesCount = comic.getPagesCount();
        mPaginationTv.setText(epsCount+" E / "+pagesCount+" P");
        // 设置封面
        ComicsMain.DataBean.ComicBean.ThumbBean thumb = comic.getThumb();
        String fileServer = thumb.getFileServer();
        String path = thumb.getPath();
        String url = fileServer + "/static/" + path;
        Glide.with(this)
                .load(url)
                .thumbnail(0.1f)
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.fail)
                .into(mFrontCoverIv);
    }

    // 处理vp2和tabLayout
    private void handleFragment() {
        mVp2FragmentAdapter = new Vp2FragmentAdapter(this);
        mComicsChapterOrCommentVp2.setAdapter(mVp2FragmentAdapter);
        TabLayoutMediator tab = new TabLayoutMediator(mComicsTabsTb, mComicsChapterOrCommentVp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("章节");
                        break;
                    case 1:
                        tab.setText("评论("+mCommentsCount+")");
                        break;
                }
            }
        });
        tab.attach();
    }

    @Override
    protected void release() {
        if (mComicsInfoPresenter != null) {
            mComicsInfoPresenter.unregisterViewCallback(this);
        }
    }
}
