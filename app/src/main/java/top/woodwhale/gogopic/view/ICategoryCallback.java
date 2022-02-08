package top.woodwhale.gogopic.view;

import top.woodwhale.gogopic.base.IBaseCallback;
import top.woodwhale.gogopic.model.domain.ComicsCategory;

public interface ICategoryCallback extends IBaseCallback {
    void onCategoryLoaded(ComicsCategory body);

    void onLoadMoreSuccess(ComicsCategory body, int nowPage);
    void onLoadMoreError();
    void onLoadMoreEmpty();
}
