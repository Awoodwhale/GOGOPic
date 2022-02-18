package top.woodwhale.gogopic.presenter;

import top.woodwhale.gogopic.base.IBasePresenter;
import top.woodwhale.gogopic.view.IWatchComicsCallback;

public interface IWatchComicsPresenter extends IBasePresenter<IWatchComicsCallback> {
    void getBook(String bookId, int bookOrder, int bookPage);
    void getBookMorePage(String bookId, int bookOrder, int bookPage);
}
