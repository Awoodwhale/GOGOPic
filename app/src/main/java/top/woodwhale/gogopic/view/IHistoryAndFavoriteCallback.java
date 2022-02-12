package top.woodwhale.gogopic.view;

import top.woodwhale.gogopic.base.IBaseCallback;
import top.woodwhale.gogopic.model.domain.ComicsCategory;

public interface IHistoryAndFavoriteCallback extends IBaseCallback {
    // 历史记录回调
    void onHistoryContentLoaded();
    // 收藏回调
    void onFavoriteContentLoaded(ComicsCategory body,int page);
    // 更多收藏
    void onFavoriteMoreContentLoaded(ComicsCategory body, int page);
    // 更多为空
    void onFavoriteMoreContentEmpty();
    // 更多没网
    void onFavoriteMoreContentError();
}
