package top.woodwhale.gogopic.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseFragment;
import top.woodwhale.gogopic.model.domain.ComicsChapter;
import top.woodwhale.gogopic.presenter.IEpsChapterPresenter;
import top.woodwhale.gogopic.ui.activity.WatchComicsActivity;
import top.woodwhale.gogopic.ui.adapter.ComicsChaptersAdapter;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.PresenterManager;
import top.woodwhale.gogopic.view.IEpsChapterCallback;

@SuppressLint("NonConstantResourceId")
public class ChapterFragment extends BaseFragment implements IEpsChapterCallback, ComicsChaptersAdapter.OnListenChapterButtonClickListener {

    @BindView(R.id.rv_eps_chapter) RecyclerView mEspChapterRv;
    private IEpsChapterPresenter mEpsChapterPresenter;
    private String mComicsID;
    private ComicsChaptersAdapter mComicsChaptersAdapter;
    private int mPages;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_chapter;
    }

    @Override
    protected void initView(View rootView) {
        onLoading();
        Intent intent = requireActivity().getIntent();
        mComicsID = intent.getStringExtra(Constants.CATEGORY_ID_KEY);
        LogUtils.d(this,"id --> " + mComicsID);
        // 创建rv适配器
        mComicsChaptersAdapter = new ComicsChaptersAdapter();
        mEspChapterRv.setAdapter(mComicsChaptersAdapter);
        // 接口回调
        mComicsChaptersAdapter.registerOnListenChapterButtonClickListener(this);
        // 创建rv布局
        mEspChapterRv.setLayoutManager(new GridLayoutManager(requireContext(),3));
    }

    @Override
    protected void initPresenter() {
        mEpsChapterPresenter = PresenterManager.getInstance().getEpsChapterPresenter();
        mEpsChapterPresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        mEpsChapterPresenter.getEpsChapter(mComicsID,1,false);
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
    public void onEpsChapterLoaded(ComicsChapter body) {

        mPages = body.getData().getEps().getPages();
        if (mPages > 1) {
            // 因为是逆序输出，所以先去处理第二页以上
            handleMoreChapter();
        }
        List<ComicsChapter.DataBean.EpsBean.DocsBean> docs = body.getData().getEps().getDocs();
        mComicsChaptersAdapter.setData(docs);
        showSuccess();
    }

    @Override
    public void onEpsChapterLoadedMore(ComicsChapter moreBody) {
        List<ComicsChapter.DataBean.EpsBean.DocsBean> docs = moreBody.getData().getEps().getDocs();
        mComicsChaptersAdapter.addData(docs);
    }

    /**
     * 处理更多页数加载
     */
    private void handleMoreChapter() {
        LogUtils.d(this,"handleMoreChapter..." + " page --> " + mPages);
        for (int i = mPages; i >= 2 ; i--) {
            LogUtils.d(this,"page --> " + i);
            mEpsChapterPresenter.getEpsChapter(mComicsID,i,true);
        }
    }

    @Override
    protected void release() {
        if (mEpsChapterPresenter != null) {
            mEpsChapterPresenter.unregisterViewCallback(this);
        }
        if (mComicsChaptersAdapter != null) {
            mComicsChaptersAdapter.unregisterOnListenChapterButtonClickListener();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setProperHeightOfView();
    }
    private void setProperHeightOfView() {
        View layoutView = requireView().findViewById(R.id.ll_comics_chapter_container);
        if (layoutView != null) {
            ViewGroup.LayoutParams layoutParams = layoutView.getLayoutParams();
            if (layoutParams!=null) {
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                layoutView.requestLayout();
            }
        }
    }

    @Override
    public void onButtonClick(int order) {
        LogUtils.d(this,"order --> " +order);
        Intent intent = new Intent(requireContext(), WatchComicsActivity.class);
        intent.putExtra(Constants.COMICS_BOOK_ID_KEY,mComicsID);
        intent.putExtra(Constants.COMICS_BOOK_ORDER_KEY,order);
        requireActivity().startActivity(intent);
    }
}
