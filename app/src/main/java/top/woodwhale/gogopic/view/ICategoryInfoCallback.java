package top.woodwhale.gogopic.view;

import top.woodwhale.gogopic.base.IBaseCallback;
import top.woodwhale.gogopic.model.domain.ComicsCategory;

public interface ICategoryInfoCallback extends IBaseCallback {
    void onCategoryLoaded(ComicsCategory body, int nowPage);
    void onLoadMoreSuccess(ComicsCategory body, int nowPage);
    void onLoadMoreError();
    void onLoadMoreEmpty();
    void onSearchSuccess(ComicsCategory body, int nowPage, String keywords);
}
