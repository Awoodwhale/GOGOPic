package top.woodwhale.gogopic.presenter;

import top.woodwhale.gogopic.base.IBasePresenter;
import top.woodwhale.gogopic.view.IHomeCallback;

public interface IHomePresenter extends IBasePresenter<IHomeCallback> {
    /**
     * 获取主页分类
     */
    void getCategories();
}
