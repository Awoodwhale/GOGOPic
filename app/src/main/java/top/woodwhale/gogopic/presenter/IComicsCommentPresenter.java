package top.woodwhale.gogopic.presenter;

import top.woodwhale.gogopic.base.IBasePresenter;
import top.woodwhale.gogopic.view.IComicsCommentCallback;

public interface IComicsCommentPresenter extends IBasePresenter<IComicsCommentCallback> {
    void getComment(String bookID, int page);
}
