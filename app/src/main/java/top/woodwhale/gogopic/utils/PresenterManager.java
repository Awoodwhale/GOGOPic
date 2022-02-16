package top.woodwhale.gogopic.utils;

import top.woodwhale.gogopic.presenter.IComicsCategoryPresenter;
import top.woodwhale.gogopic.presenter.IComicsCommentPresenter;
import top.woodwhale.gogopic.presenter.IComicsInfoPresenter;
import top.woodwhale.gogopic.presenter.IEpsChapterPresenter;
import top.woodwhale.gogopic.presenter.IHistoryAndFavoritePresenter;
import top.woodwhale.gogopic.presenter.IHomePresenter;
import top.woodwhale.gogopic.presenter.IKeywordsPresenter;
import top.woodwhale.gogopic.presenter.ILoginPresenter;
import top.woodwhale.gogopic.presenter.IMinePresenter;
import top.woodwhale.gogopic.presenter.impl.ComicsCategoryPresenterImpl;
import top.woodwhale.gogopic.presenter.impl.ComicsCommentPresenterImpl;
import top.woodwhale.gogopic.presenter.impl.ComicsInfoPresenterImpl;
import top.woodwhale.gogopic.presenter.impl.EpsChapterPresenterImpl;
import top.woodwhale.gogopic.presenter.impl.HistoryAndFavoritePresenterImpl;
import top.woodwhale.gogopic.presenter.impl.HomePresenterImpl;
import top.woodwhale.gogopic.presenter.impl.KeywordsPresenterImpl;
import top.woodwhale.gogopic.presenter.impl.LoginPresenterImpl;
import top.woodwhale.gogopic.presenter.impl.MinePresenterImpl;

public class PresenterManager {
    private final IHomePresenter mHomePresenter;
    private final ILoginPresenter mLoginPresenter;
    private final IMinePresenter mMinePresenter;
    private final IComicsCategoryPresenter mCategoryPresenter;
    private final IComicsInfoPresenter mComicsInfoPresenter;
    private final IKeywordsPresenter mKeywordsPresenter;
    private final IHistoryAndFavoritePresenter mHistoryAndFavoritePresenter;
    private final IEpsChapterPresenter mEpsChapterPresenter;
    private final IComicsCommentPresenter mComicsCommentPresenter;

    public ILoginPresenter getLoginPresenter() {
        return mLoginPresenter;
    }
    public IMinePresenter getMinePresenter() {
        return mMinePresenter;
    }
    public IHomePresenter getHomePresenter() {
        return mHomePresenter;
    }
    public IComicsCategoryPresenter getCategoryPresenter() {
        return mCategoryPresenter;
    }
    public IComicsInfoPresenter getComicsInfoPresenter() {
        return mComicsInfoPresenter;
    }
    public IKeywordsPresenter getKeywordsPresenter() {
        return mKeywordsPresenter;
    }
    public IHistoryAndFavoritePresenter getHistoryAndFavoritePresenter() {
        return mHistoryAndFavoritePresenter;
    }
    public IEpsChapterPresenter getEpsChapterPresenter() {
        return mEpsChapterPresenter;
    }
    public IComicsCommentPresenter getComicsCommentPresenter() {
        return mComicsCommentPresenter;
    }

    private PresenterManager() {
        mHomePresenter = new HomePresenterImpl();
        mLoginPresenter = new LoginPresenterImpl();
        mMinePresenter = new MinePresenterImpl();
        mCategoryPresenter = new ComicsCategoryPresenterImpl();
        mComicsInfoPresenter = new ComicsInfoPresenterImpl();
        mKeywordsPresenter = new KeywordsPresenterImpl();
        mHistoryAndFavoritePresenter = new HistoryAndFavoritePresenterImpl();
        mEpsChapterPresenter = new EpsChapterPresenterImpl();
        mComicsCommentPresenter = new ComicsCommentPresenterImpl();
    }

    private static final PresenterManager instance = new PresenterManager();

    public static PresenterManager getInstance() {
        return instance;
    }

}
