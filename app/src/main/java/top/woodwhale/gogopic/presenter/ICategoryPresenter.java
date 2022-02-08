package top.woodwhale.gogopic.presenter;

import top.woodwhale.gogopic.base.IBasePresenter;
import top.woodwhale.gogopic.view.ICategoryCallback;

public interface ICategoryPresenter extends IBasePresenter<ICategoryCallback> {
    // 第一次获取默认数据
    void getCategoryComics(String url);
    // 加载更多
    void getLoadMoreCategoryComics(String title, String sortWay);
    // 获取排序后的数据
    void getSortedCategoryComics(String title, String sortWay);
    // 跳转页数
    void getPageJumpCategoryComics(String title, String sortWay, int page);
}
