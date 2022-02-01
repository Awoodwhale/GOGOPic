package top.woodwhale.gogopic.base;

public interface IBaseCallback {
    /**
     * 网络错误
     */
    void onNetworkError();

    /**
     * 加载中
     */
    void onLoading();

    /**
     * 加载内容为空
     */
    void onEmpty();
}
