package top.woodwhale.gogopic.presenter;

import top.woodwhale.gogopic.base.IBasePresenter;
import top.woodwhale.gogopic.view.IComicsInfoCallback;

public interface IComicsInfoPresenter extends IBasePresenter<IComicsInfoCallback> {
    void getComicsInfo(String comicsID);
    void getComicsLike(String comicsID);
    void getComicsFavorite(String comicsID);
}
