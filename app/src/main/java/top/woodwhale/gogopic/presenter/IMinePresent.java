package top.woodwhale.gogopic.presenter;

import top.woodwhale.gogopic.base.IBasePresenter;
import top.woodwhale.gogopic.view.IMineCallback;

public interface IMinePresent extends IBasePresenter<IMineCallback> {
    /**
     * 获取个人信息
     */
    void getUserInfo();
}
