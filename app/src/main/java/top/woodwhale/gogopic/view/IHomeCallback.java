package top.woodwhale.gogopic.view;

import java.util.List;

import top.woodwhale.gogopic.base.IBaseCallback;
import top.woodwhale.gogopic.model.domain.Categories;

public interface IHomeCallback extends IBaseCallback {
    void onCategoriesLoaded(List<Categories.DataBean.CategoriesBean> categories);
}
