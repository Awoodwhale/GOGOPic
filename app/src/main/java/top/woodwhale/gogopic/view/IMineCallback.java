package top.woodwhale.gogopic.view;

import top.woodwhale.gogopic.base.IBaseCallback;
import top.woodwhale.gogopic.model.domain.UserInfo;

public interface IMineCallback extends IBaseCallback {
    void onUserInfoLoaded(UserInfo body);
}
