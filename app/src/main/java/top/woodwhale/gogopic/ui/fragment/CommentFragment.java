package top.woodwhale.gogopic.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vondear.rxtool.view.RxToast;

import butterknife.BindView;
import butterknife.OnClick;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseFragment;
import top.woodwhale.gogopic.model.domain.ComicsComment;
import top.woodwhale.gogopic.presenter.IComicsCommentPresenter;
import top.woodwhale.gogopic.ui.adapter.ComicsCommentAdapter;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.PresenterManager;
import top.woodwhale.gogopic.view.IComicsCommentCallback;

@SuppressLint("NonConstantResourceId")
public class CommentFragment extends BaseFragment implements IComicsCommentCallback {

    @BindView(R.id.bt_comics_launch_comment)
    Button mLaunchCommentBtn;
    @BindView(R.id.bt_comics_goto_page)
    Button mGotoPageBtn;
    @BindView(R.id.rv_comics_comment_content_list)
    RecyclerView mCommentContentListRv;
    @BindView(R.id.bt_comics_before_comment)
    Button mBeforeBtn;
    @BindView(R.id.bt_comics_next_comment)
    Button mNextBtn;
    @BindView(R.id.tv_comics_comment_pages)
    TextView mPagesTv;
    private ComicsCommentAdapter mComicsCommentAdapter;
    private IComicsCommentPresenter mComicsCommentPresenter;
    private String mComicsID;
    private int mNowPage = 1;
    private int mPages;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void initView(View rootView) {
        onLoading();
        Intent intent = requireActivity().getIntent();
        mComicsID = intent.getStringExtra(Constants.CATEGORY_ID_KEY);
        LogUtils.d(this, "id --> " + mComicsID);
        // Rv适配器
        mComicsCommentAdapter = new ComicsCommentAdapter();
        mCommentContentListRv.setAdapter(mComicsCommentAdapter);
        // Rv布局
        mCommentContentListRv.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    protected void initPresenter() {
        mComicsCommentPresenter = PresenterManager.getInstance().getComicsCommentPresenter();
        mComicsCommentPresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        mComicsCommentPresenter.getComment(mComicsID, mNowPage);
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onCommentLoaded(ComicsComment body) {
        mComicsCommentAdapter.setData(body);
        mPages = body.getData().getComments().getPages();
        mNextBtn.setVisibility(mNowPage == mPages ? View.GONE : View.VISIBLE);
        mBeforeBtn.setVisibility(mNowPage > 1 ? View.VISIBLE : View.GONE);
        mPagesTv.setText(mNowPage + " / " + mPages + " 页");
        handleRefreshPage();
        showSuccess();
    }

    @Override
    public void onCommentLaunched(String body) {
        RxToast.info("评论成功!");
        mComicsCommentPresenter.getComment(mComicsID, 1);
    }

    @Override
    public void onCommentLaunchedError() {
        RxToast.error("评论异常!请检测网络或者账号等级!");
        showSuccess();
    }

    /**
     * 监听事件，处理自动跳转
     */
    private void handleRefreshPage() {
        NestedScrollView tmp = requireActivity().findViewById(R.id.nsv_comics_container);
        LinearLayout tmpLayout = requireActivity().findViewById(R.id.ll_comics_header_container);
        tmpLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int measuredHeight = tmpLayout.getMeasuredHeight();
                if (measuredHeight != 0) {
                    tmp.scrollTo(0, measuredHeight);
                    tmpLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    // 处理点击事件
    @OnClick({R.id.bt_comics_before_comment,
            R.id.bt_comics_next_comment,
            R.id.bt_comics_launch_comment,
            R.id.bt_comics_goto_page})
    public void handleClickPage(View v) {
        switch (v.getId()) {
            case R.id.bt_comics_before_comment:
                onLoading();
                handleBeforePage();
                break;
            case R.id.bt_comics_next_comment:
                onLoading();
                handleNextPage();
                break;
            case R.id.bt_comics_launch_comment:
                handleLaunchComment();
                break;
            case R.id.bt_comics_goto_page:
                handleGotoPage();
                break;
        }
    }

    /**
     * 处理评论页面跳转
     */
    private void handleGotoPage() {
        EditText editText = new EditText(requireContext());
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog.Builder editDialog = new AlertDialog.Builder(requireContext());
        editDialog.setTitle("选择页数").setView(editText);
        editDialog.setPositiveButton("确定", (dialog, which) -> {
            String page = editText.getText().toString().trim();
            if (!TextUtils.isEmpty(page)) {
                int toPage = Integer.parseInt(page);
                if (toPage >= 1 && toPage <= mPages) {
                    onLoading();
                    mNowPage = toPage;
                    mComicsCommentPresenter.getComment(mComicsID, mNowPage);
                } else {
                    RxToast.warning("请输入有效页数!");
                }
            }
        });
        editDialog.setNegativeButton("取消", null);
        editDialog.show();
    }

    /**
     * 处理发布评论
     */
    private void handleLaunchComment() {
        EditText editText = new EditText(requireContext());
        AlertDialog.Builder editDialog = new AlertDialog.Builder(requireContext());
        editDialog.setTitle("发表评论").setView(editText);
        editDialog.setPositiveButton("确定", (dialog, which) -> {
            String comment = editText.getText().toString().trim();
            if (!TextUtils.isEmpty(comment)) {
                onLoading();
                mComicsCommentPresenter.launchComment(mComicsID, comment);
            } else {
                RxToast.warning("请输入有效内容!");
            }
        });
        editDialog.setNegativeButton("取消", null);
        editDialog.show();
    }

    /**
     * 上一页
     */
    private void handleBeforePage() {
        mComicsCommentPresenter.getComment(mComicsID, --mNowPage);
    }

    /**
     * 下一页
     */
    private void handleNextPage() {
        mComicsCommentPresenter.getComment(mComicsID, ++mNowPage);
    }

    @Override
    public void onResume() {
        super.onResume();
        setProperHeightOfView();
    }

    private void setProperHeightOfView() {
        View layoutView = requireView().findViewById(R.id.ll_comics_comment_container);
        if (layoutView != null) {
            ViewGroup.LayoutParams layoutParams = layoutView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                layoutView.requestLayout();
            }
        }
    }
}
