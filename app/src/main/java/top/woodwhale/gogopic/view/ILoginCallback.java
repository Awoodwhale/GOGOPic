package top.woodwhale.gogopic.view;

import top.woodwhale.gogopic.model.domain.AuthSignIn;
import top.woodwhale.gogopic.base.IBaseCallback;

public interface ILoginCallback extends IBaseCallback {
    void onAuthenticateLoaded(AuthSignIn authSignIn);
    void onAccountOrPasswordError();
}
