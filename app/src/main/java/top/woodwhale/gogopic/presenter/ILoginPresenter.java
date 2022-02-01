package top.woodwhale.gogopic.presenter;

import top.woodwhale.gogopic.base.IBasePresenter;
import top.woodwhale.gogopic.view.ILoginCallback;

public interface ILoginPresenter extends IBasePresenter<ILoginCallback> {
    /**
     * 登录获取Authenticate
     * @param account 账户
     * @param password 密码
     */
    void getAuthenticate(String account, String password);
}
