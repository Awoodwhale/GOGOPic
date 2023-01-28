package top.woodwhale.gogopic.ui.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vondear.rxtool.view.RxToast;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseActivity;
import top.woodwhale.gogopic.model.domain.ComicsBook;
import top.woodwhale.gogopic.model.domain.ComicsComment;
import top.woodwhale.gogopic.model.domain.ComicsMain;
import top.woodwhale.gogopic.model.domain.LikeOrFavorite;
import top.woodwhale.gogopic.presenter.IComicsCommentPresenter;
import top.woodwhale.gogopic.presenter.IComicsInfoPresenter;
import top.woodwhale.gogopic.presenter.IWatchComicsPresenter;
import top.woodwhale.gogopic.ui.adapter.ComicsBookAdapter;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.PresenterManager;
import top.woodwhale.gogopic.view.IComicsCommentCallback;
import top.woodwhale.gogopic.view.IComicsInfoCallback;
import top.woodwhale.gogopic.view.IWatchComicsCallback;

@SuppressLint("NonConstantResourceId")
public class WatchComicsActivity extends BaseActivity implements IWatchComicsCallback, IComicsInfoCallback, IComicsCommentCallback {
    // 动画时间
    private static final int UI_ANIMATION_DELAY = 200;
    // 显示或者隐藏状态栏
    private boolean mVisible;

    @BindView(R.id.top_layout)
    LinearLayout topLayout;
    @BindView(R.id.bottom_layout)
    LinearLayout bottomLayout;
    @BindView(R.id.small_status)
    LinearLayout smallStatus;
    @BindView(R.id.toogle)
    TextView middleText;
    @BindView(R.id.rv_watch_comics_content_list)
    RecyclerView mComicsContentListRv;
    @BindView(R.id.bottom_seekBar)
    SeekBar mBottomSeekBar;
    @BindView(R.id.comic_pic_state)
    TextView mComicsPicStateTv;
    @BindView(R.id.comic_pic_state_top)
    TextView mComicsPicStateTopTv;
    @BindView(R.id.bottom_seekBar_help)
    RelativeLayout bottom_seekBar_help;
    @BindView(R.id.seekBar_help_now)
    TextView seekBar_help_now;
    @BindView(R.id.seekBar_help_num)
    TextView seekBar_help_num;
    @BindView(R.id.comic_name_bottom)
    TextView mNowChapterTv;
    @BindView(R.id.battery)
    TextView battery;
    @BindView(R.id.time)
    TextView mTimeTv;
    @BindView(R.id.back_btn)
    Button mBackBtn;
    @BindView(R.id.ll_comics_collect)
    LinearLayout mClickCollectLayout;
    @BindView(R.id.iv_comics_collect)
    ImageView mClickCollectIv;
    @BindView(R.id.like_click)
    LinearLayout mClickLikeLayout;
    @BindView(R.id.iv_comics_like)
    ImageView mClickLikeIv;
    @BindView(R.id.bt_comics_comment)
    Button mCommentBtn;
    @BindView(R.id.preHUA)
    TextView prePageBtn;
    @BindView(R.id.nextHUA)
    TextView nextPageBtn;

    private boolean mFirstLoad = true;
    private String mBookId;
    private IWatchComicsPresenter mWatchComicsPresenter;
    private int mBookOrder;
    private ComicsBookAdapter mComicsBookAdapter;
    private int mAllPages = 1;
    private int adapterNowPos = 1;
    private int mTotalPage;
    private boolean mIsCollect;
    private boolean mIsLiked;
    private boolean mCanTouchLike = true;
    private boolean mCanTouchFavorite = true;
    private BatteryLevelReceiver mBatteryLevelReceiver;
    private IComicsInfoPresenter mComicsInfoPresenter;
    private IComicsCommentPresenter mComicsCommentPresenter;
    private int mBookChaptersSize;

    @Override
    public void onComicsInfoLoaded(ComicsMain body) {

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

    @Override
    public void onCommentLoaded(ComicsComment body) {
        // 不需要加载评论
    }

    @Override
    public void onCommentLaunched(String body) {
        // 评论成功吐司一下
        RxToast.info("评论成功!");
    }

    @Override
    public void onCommentLaunchedError() {
        // 评论失败也提示一下
        RxToast.error("网络错误!评论失败!");
    }

    private class BatteryLevelReceiver extends BroadcastReceiver {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            battery.setText(state + "%");
        }
    }

    private TimeReceiver mTimeReceiver;

    private class TimeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            mTimeTv.setText(getSystemTime());
        }
    }


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_watch_comics;
    }

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void initView() {
        // 处理广播事件
        handleReceiver();
        // 进入就加载
        onLoading();
        Intent intent = getIntent();
        // 本子id
        mBookId = intent.getStringExtra(Constants.COMICS_BOOK_ID_KEY);
        // 本子章节
        mBookOrder = intent.getIntExtra(Constants.COMICS_BOOK_ORDER_KEY, 1);
        // 章节数目
        mBookChaptersSize = intent.getIntExtra(Constants.COMICS_BOOK_CHAPTERS_SIZE, 1);
        // 设置时间
        mTimeTv.setText(getSystemTime());
        // 设置章节
        mNowChapterTv.setText("第" + mBookOrder + "话");
        // 创建rv适配器
        mComicsBookAdapter = new ComicsBookAdapter();
        mComicsContentListRv.setAdapter(mComicsBookAdapter);
        // 创建rv布局
        mComicsContentListRv.setLayoutManager(new LinearLayoutManager(this));
        // 设置Rv和控制面板的视图冲突

//        middleText.setOnTouchListener((v, event) -> {
//            switch(event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    Sy = event.getY();
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    Ey = event.getY();
//                    float res = (Ey - Sy);
//                    if(Math.abs(res) > 2) {
//                        // 让RecyclerView开始滑动
//                        mComicsContentListRv.scrollBy(mComicsContentListRv.getScrollX(),
//                                mComicsContentListRv.getScrollY()-(int)res);
//                        // 更新开始位置
//                        Sy = event.getY();
//                    }
//                    break;
//            }
//            return false;
//        });
    }

    private void handleReceiver() {
        // 电量广播注册
        IntentFilter batteryIntentFilter = new IntentFilter();
        batteryIntentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        mBatteryLevelReceiver = new BatteryLevelReceiver();
        this.registerReceiver(mBatteryLevelReceiver, batteryIntentFilter);
        // 动态时间广播
        IntentFilter timeIntentFilter = new IntentFilter();
        timeIntentFilter.addAction(Intent.ACTION_TIME_TICK);
        mTimeReceiver = new TimeReceiver();
        this.registerReceiver(mTimeReceiver, timeIntentFilter);
    }

    @Override
    protected void initPresenter() {
        mWatchComicsPresenter = PresenterManager.getInstance().getWatchComicsPresenter();
        mWatchComicsPresenter.registerViewCallback(this);
        mComicsInfoPresenter = PresenterManager.getInstance().getComicsInfoPresenter();
        mComicsInfoPresenter.registerViewCallback(this);
        mComicsCommentPresenter = PresenterManager.getInstance().getComicsCommentPresenter();
        mComicsCommentPresenter.registerViewCallback(this);
    }

    @Override
    protected void initEvent() {
        // 进入就加载第一页的数据
        mWatchComicsPresenter.getBook(mBookId, mBookOrder, 1);
        // 设置Rv滑动监听
        mComicsContentListRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager l = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 不等于的时候才是有效改变
                if (l != null) {
                    if (mFirstLoad || (Math.abs(dy) > 0 && adapterNowPos != l.findFirstVisibleItemPosition())) {
                        if (mFirstLoad) {
                            mFirstLoad = false;
                        }
                        adapterNowPos = l.findFirstVisibleItemPosition();
                        // 设置seekbar
                        mBottomSeekBar.setMax(mTotalPage - 1);
                        mBottomSeekBar.setProgress(adapterNowPos);
                        // 设置文字
                        setPicText();
                    }
                }
            }
        });

        // 设置seekbar滑动监听
        mBottomSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 更新辅助面板
                if (bottom_seekBar_help.getVisibility() == View.VISIBLE) {
                    seekBar_help_now.setText(progress + 1 + "");
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 显示辅助面板
                bottom_seekBar_help.setVisibility(View.VISIBLE);
                seekBar_help_num.setText(seekBar.getMax() + 1 + "");//设置总数

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 关闭辅助面板
                bottom_seekBar_help.setVisibility(View.GONE);
                int progress = seekBar.getProgress();
                LogUtils.i(WatchComicsActivity.this, "onProgressChanged: 选择了，第" + (progress + 1));
                // 设置图片状态
                adapterNowPos = progress;
                setPicText();
                // 不能平稳滑动，否者联动出错
                mComicsContentListRv.scrollToPosition(adapterNowPos);
            }
        });

        // 左上角返回监听
        mBackBtn.setOnClickListener(v -> finish());

        // 底部栏收藏监听
        mClickCollectLayout.setOnClickListener(v -> {
            if (mCanTouchFavorite && mComicsInfoPresenter != null) {
                mComicsInfoPresenter.getComicsFavorite(mBookId);
                mClickCollectIv.setImageResource(R.mipmap.refresh_icon);
                mCanTouchFavorite = false;
            }
        });

        // 底部栏喜欢监听
        mClickLikeLayout.setOnClickListener(v -> {
            if (mCanTouchLike && mComicsInfoPresenter != null) {
                mComicsInfoPresenter.getComicsLike(mBookId);
                mClickLikeIv.setImageResource(R.mipmap.refresh_icon);
                mCanTouchLike = false;
            }
        });

        // 设置顶部栏右侧评论监听
        mCommentBtn.setOnClickListener(v -> {
            EditText editText = new EditText(WatchComicsActivity.this);
            AlertDialog.Builder editDialog = new AlertDialog.Builder(WatchComicsActivity.this);
            editDialog.setTitle("发表评论").setView(editText);
            editDialog.setPositiveButton("确定", (dialog, which) -> {
                String comment = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(comment)) {
                    mComicsCommentPresenter.launchComment(mBookId, comment);
                } else {
                    RxToast.warning("请输入有效内容!");
                }
            });
            editDialog.setNegativeButton("取消", null);
            editDialog.show();
        });

        // 设置上一章按钮点击监听
        prePageBtn.setOnClickListener(v -> {
            LogUtils.d(WatchComicsActivity.this, "总章节长度 --> " + mBookChaptersSize + " 当前章节 --> " + mBookOrder);
            if (mBookOrder <= 1) {
                RxToast.warning("没有上一话咯！");
                return;
            }
            Intent intent = new Intent(getApplicationContext(), WatchComicsActivity.class);
            intent.putExtra(Constants.COMICS_BOOK_ID_KEY, mBookId);
            intent.putExtra(Constants.COMICS_BOOK_ORDER_KEY, mBookOrder - 1);
            intent.putExtra(Constants.COMICS_BOOK_CHAPTERS_SIZE, mBookChaptersSize);
            finish();
            startActivity(intent);
        });
        // 设置下一章点击按钮监听
        nextPageBtn.setOnClickListener(v -> {
            LogUtils.d(WatchComicsActivity.this, "总章节长度 --> " + mBookChaptersSize + " 当前章节 --> " + mBookOrder);
            if (mBookOrder >= mBookChaptersSize) {
                RxToast.warning("没有下一话咯！");
                return;
            }
            Intent intent = new Intent(getApplicationContext(), WatchComicsActivity.class);
            intent.putExtra(Constants.COMICS_BOOK_ID_KEY, mBookId);
            intent.putExtra(Constants.COMICS_BOOK_ORDER_KEY, mBookOrder + 1);
            intent.putExtra(Constants.COMICS_BOOK_CHAPTERS_SIZE, mBookChaptersSize);
            finish();
            startActivity(intent);
        });
    }

    // 设置右下角底部显示
    private void setPicText() {
        String s = (adapterNowPos + 1) + "/" + mTotalPage + "页";
        mComicsPicStateTv.setText(s);
        mComicsPicStateTopTv.setText(s);
    }

    @OnClick({R.id.toogle})
    protected void handleOnClick(View v) {
        if (v.getId() == R.id.toogle) {
            handlePanel();
        }
    }

    private void showPanel() {
        LogUtils.d(this, "显示");
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f,
                Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF,
                0.0f);
        TranslateAnimation translateAnimation1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f,
                Animation.RELATIVE_TO_SELF,
                0.0f,
                Animation.RELATIVE_TO_SELF,
                -1.0f,
                Animation.RELATIVE_TO_SELF,
                0.0f);
        translateAnimation.setDuration(UI_ANIMATION_DELAY);
        translateAnimation1.setDuration(UI_ANIMATION_DELAY);
        topLayout.setAnimation(translateAnimation1);
        bottomLayout.setAnimation(translateAnimation);
        // 这里通过改变可见性来播放动画
        topLayout.setVisibility(View.VISIBLE);
        // 这里通过改变可见性来播放动画
        bottomLayout.setVisibility(View.VISIBLE);
        // 底部状态栏消失
        smallStatus.setVisibility(View.GONE);

    }

    /**
     * 隐藏上下栏
     */
    private void hidePanel() {
        LogUtils.d(this, "隐藏");
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f,
                Animation.RELATIVE_TO_SELF,
                0.0f,
                Animation.RELATIVE_TO_SELF,
                0.0f,
                Animation.RELATIVE_TO_SELF,
                -1.0f);
        TranslateAnimation translateAnimation1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f,
                Animation.RELATIVE_TO_SELF,
                0.0f,
                Animation.RELATIVE_TO_SELF,
                0.0f,
                Animation.RELATIVE_TO_SELF,
                1.0f);
        translateAnimation.setDuration(UI_ANIMATION_DELAY);
        translateAnimation1.setDuration(UI_ANIMATION_DELAY);
        topLayout.setAnimation(translateAnimation);
        bottomLayout.setAnimation(translateAnimation1);
        // 这里通过改变可见性来播放动画
        topLayout.setVisibility(View.INVISIBLE);
        // 这里通过改变可见性来播放动画
        bottomLayout.setVisibility(View.INVISIBLE);
        // 底部状态栏显示
        smallStatus.setVisibility(View.VISIBLE);
    }

    // 处理点击显示和隐藏
    private void handlePanel() {
        if (mVisible) {
            hidePanel();
            mVisible = false;
        } else {
            showPanel();
            mVisible = true;
        }
    }

    // 获取当前时间
    private String getSystemTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        String d = hour >= 12 ? "下午" : "上午";
        String h = hour >= 10 ? String.valueOf(hour) : "0" + hour;
        String m = min >= 10 ? String.valueOf(min) : "0" + min;
        return d + h + ":" + m;
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
    public void onBookLoaded(ComicsBook.DataBean.PagesBean pagesBean) {
        mTotalPage = pagesBean.getTotal();
        LogUtils.d(this, "total page --> " + mTotalPage);
        mAllPages = pagesBean.getPages();
        // 先将第一页的存入适配器
        mComicsBookAdapter.setData(pagesBean.getDocs());
        // 如果有很多页，就再去加载后面的页数
        if (mAllPages > 1) {
            handleLoadMorePage();
        }
        showSuccess();
    }

    @Override
    public void onBookLoadedMorePage(ComicsBook.DataBean.PagesBean pages) {
        mComicsBookAdapter.addData(pages.getDocs());
    }

    // 加载更多页数
    private void handleLoadMorePage() {
        // 从第二页开始加载
        for (int page = 2; page <= mAllPages; page++) {
            mWatchComicsPresenter.getBookMorePage(mBookId, mBookOrder, page);
        }
    }

    @Override
    protected void release() {
        if (mWatchComicsPresenter != null) {
            mWatchComicsPresenter.unregisterViewCallback(this);
        }
        if (mBatteryLevelReceiver != null) {
            this.unregisterReceiver(mBatteryLevelReceiver);
        }
        if (mTimeReceiver != null) {
            this.unregisterReceiver(mTimeReceiver);
        }
    }
}
