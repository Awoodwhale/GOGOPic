package top.woodwhale.gogopic.view;

import top.woodwhale.gogopic.base.IBaseCallback;
import top.woodwhale.gogopic.model.domain.ComicsBook;

public interface IWatchComicsCallback extends IBaseCallback {
    void onBookLoaded(ComicsBook.DataBean.PagesBean pages);
    void onBookLoadedMorePage(ComicsBook.DataBean.PagesBean pages);
}
