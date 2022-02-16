package top.woodwhale.gogopic.view;

import top.woodwhale.gogopic.base.IBaseCallback;
import top.woodwhale.gogopic.model.domain.Categories;

public interface IHomeCallback extends IBaseCallback {
    void onCategoriesLoaded(Categories categories);
}
