package top.woodwhale.gogopic.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseFragment;
import top.woodwhale.gogopic.model.domain.ComicsChapter;
import top.woodwhale.gogopic.presenter.IEpsChapterPresenter;
import top.woodwhale.gogopic.ui.adapter.ComicsChaptersAdapter;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.PresenterManager;
import top.woodwhale.gogopic.view.IEpsChapterCallback;

@SuppressLint("NonConstantResourceId")
public class ChapterFragment extends BaseFragment implements IEpsChapterCallback {

    @BindView(R.id.rv_eps_chapter) RecyclerView mEspChapterRv;
    private IEpsChapterPresenter mEpsChapterPresenter;
    private String mComicsID;
    private ComicsChaptersAdapter mComicsChaptersAdapter;

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
        // 创建rv布局
        mEspChapterRv.setLayoutManager(new GridLayoutManager(requireContext(),3));
        // 创建rv适配器
        mComicsChaptersAdapter = new ComicsChaptersAdapter();
        mEspChapterRv.setAdapter(mComicsChaptersAdapter);
    }

    @Override
    protected void initPresenter() {
        mEpsChapterPresenter = PresenterManager.getInstance().getEpsChapterPresenter();
        mEpsChapterPresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        mEpsChapterPresenter.getEpsChapter(mComicsID,1);
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
        int pages = body.getData().getEps().getPages();
        if (pages > 1) {
            // 因为是逆序输出，所以先去处理第二页以上
            handleMoreChapter();
        }
        List<ComicsChapter.DataBean.EpsBean.DocsBean> docs = body.getData().getEps().getDocs();
        mComicsChaptersAdapter.setData(docs);
        showSuccess();
    }

    private void handleMoreChapter() {

    }

    @Override
    protected void release() {
        if (mEpsChapterPresenter != null) {
            mEpsChapterPresenter.unregisterViewCallback(this);
        }
    }
}
