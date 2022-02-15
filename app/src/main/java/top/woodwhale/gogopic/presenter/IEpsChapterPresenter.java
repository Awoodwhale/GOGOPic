package top.woodwhale.gogopic.presenter;

import top.woodwhale.gogopic.base.IBasePresenter;
import top.woodwhale.gogopic.view.IEpsChapterCallback;

public interface IEpsChapterPresenter extends IBasePresenter<IEpsChapterCallback> {
    void getEpsChapter(String bookID, int page, boolean isLoadMore);
}
