package top.woodwhale.gogopic.presenter;

import top.woodwhale.gogopic.base.IBasePresenter;
import top.woodwhale.gogopic.view.IKeywordsCallback;

public interface IKeywordsPresenter extends IBasePresenter<IKeywordsCallback> {
    void getKeywords();

}
