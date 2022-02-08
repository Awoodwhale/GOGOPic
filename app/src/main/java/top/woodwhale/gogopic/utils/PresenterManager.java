package top.woodwhale.gogopic.utils;

import top.woodwhale.gogopic.presenter.ICategoryPresenter;
import top.woodwhale.gogopic.presenter.IHomePresenter;
import top.woodwhale.gogopic.presenter.ILoginPresenter;
import top.woodwhale.gogopic.presenter.IMinePresenter;
import top.woodwhale.gogopic.presenter.impl.ComicsCategoryPresenterImpl;
import top.woodwhale.gogopic.presenter.impl.HomePresenterImpl;
import top.woodwhale.gogopic.presenter.impl.LoginPresenterImpl;
import top.woodwhale.gogopic.presenter.impl.MinePresenterImpl;

public class PresenterManager {
    private final IHomePresenter mHomePresenter;

    private final ILoginPresenter mLoginPresenter;

    private final IMinePresenter mMinePresenter;
    private final ComicsCategoryPresenterImpl mCategoryPresenter;

    public ILoginPresenter getLoginPresenter() {
        return mLoginPresenter;
    }
    public IMinePresenter getMinePresenter() {
        return mMinePresenter;
    }
    public IHomePresenter getHomePresenter() {
        return mHomePresenter;
    }
    public ICategoryPresenter getCategoryPresenter() {
        return mCategoryPresenter;
    }

    private PresenterManager() {
        mHomePresenter = new HomePresenterImpl();
        mLoginPresenter = new LoginPresenterImpl();
        mMinePresenter = new MinePresenterImpl();
        mCategoryPresenter = new ComicsCategoryPresenterImpl();
    }

    private static final PresenterManager instance = new PresenterManager();

    public static PresenterManager getInstance() {
        return instance;
    }

}
