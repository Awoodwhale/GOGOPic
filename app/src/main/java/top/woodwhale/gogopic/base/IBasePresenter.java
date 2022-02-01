package top.woodwhale.gogopic.base;

public interface IBasePresenter<T> {
    /**
     * 注册UI通知接口
     * @param callback
     */
    void registerViewCallback(T callback);

    /**
     * 取消注册UI通知接口
     * @param callback
     */
    void unregisterViewCallback(T callback);
}
