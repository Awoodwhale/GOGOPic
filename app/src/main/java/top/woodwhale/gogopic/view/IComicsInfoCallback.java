package top.woodwhale.gogopic.view;

import top.woodwhale.gogopic.base.IBaseCallback;
import top.woodwhale.gogopic.model.domain.ComicsMain;
import top.woodwhale.gogopic.model.domain.LikeOrFavorite;

public interface IComicsInfoCallback extends IBaseCallback {
    void onComicsInfoLoaded(ComicsMain body);
    void onComicsLikeLoaded(LikeOrFavorite body);
    void onComicsLikeError();
    void onComicsLikeEmpty();
    void onComicsFavoriteLoaded(LikeOrFavorite body);
    void onComicsFavoriteError();
    void onComicsFavoriteEmpty();
}
