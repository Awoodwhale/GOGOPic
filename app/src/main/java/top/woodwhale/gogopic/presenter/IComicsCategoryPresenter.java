package top.woodwhale.gogopic.presenter;

import top.woodwhale.gogopic.base.IBasePresenter;
import top.woodwhale.gogopic.view.ICategoryInfoCallback;

public interface IComicsCategoryPresenter extends IBasePresenter<ICategoryInfoCallback> {
    // 第一次获取默认数据
    void getCategoryComics(String title, String sortWay, int page);
    // 加载更多
    void getLoadMoreCategoryComics(String title, String sortWay);
    // 获取排序后的数据
    void getSortedCategoryComics(String title, String sortWay);
    // 跳转页数
    void getPageJumpCategoryComics(String title, String sortWay, int page);
    // 更换类别
    void getChangedCategoryComics(String title);
    // 搜素
    void getSearchCategoryComics(String keywords);
    // 搜索分类
    void getSearchSortedCategoryComics(String keywords,String sortWay);
    // 搜索加载更多
    void getSearchLoadMoreCategoryComics(String keyword, String sortWay);
    // 搜索页数跳转
    void getSearchPageJumpCategoryComics(String keyword, String sortWay, int page);
}
