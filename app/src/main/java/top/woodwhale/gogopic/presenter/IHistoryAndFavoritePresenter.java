package top.woodwhale.gogopic.presenter;

import top.woodwhale.gogopic.base.IBasePresenter;
import top.woodwhale.gogopic.view.IHistoryAndFavoriteCallback;

public interface IHistoryAndFavoritePresenter extends IBasePresenter<IHistoryAndFavoriteCallback> {
    void getHistoryContent();

    void getFavoriteContent(int page);
    void getFavoriteMoreContent();
}
