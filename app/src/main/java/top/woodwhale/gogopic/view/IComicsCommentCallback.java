package top.woodwhale.gogopic.view;

import top.woodwhale.gogopic.base.IBaseCallback;
import top.woodwhale.gogopic.model.domain.ComicsComment;

public interface IComicsCommentCallback extends IBaseCallback {
    void onCommentLoaded(ComicsComment body);
    void onCommentLaunched(String body);
    void onCommentLaunchedError();
}
