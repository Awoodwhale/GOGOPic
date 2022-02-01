package top.woodwhale.gogopic.presenter;

import top.woodwhale.gogopic.base.IBasePresenter;
import top.woodwhale.gogopic.view.IHomeCallback;

public interface IHomePresent extends IBasePresenter<IHomeCallback> {
    void getCategories();
}
