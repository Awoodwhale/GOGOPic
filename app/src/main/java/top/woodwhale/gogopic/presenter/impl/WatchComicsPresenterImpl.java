package top.woodwhale.gogopic.presenter.impl;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import top.woodwhale.gogopic.model.API;
import top.woodwhale.gogopic.model.domain.ComicsBook;
import top.woodwhale.gogopic.presenter.IWatchComicsPresenter;
import top.woodwhale.gogopic.utils.HeaderUtils;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.RetrofitManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.IWatchComicsCallback;

public class WatchComicsPresenterImpl implements IWatchComicsPresenter {
    private IWatchComicsCallback mCallback;

    @Override
    public void registerViewCallback(IWatchComicsCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IWatchComicsCallback callback) {
        this.mCallback = null;
    }

    @Override
    public void getBook(String bookId, int bookOrder, int bookPage) {
        getBook(bookId,bookOrder,bookPage,false);
    }

    @Override
    public void getBookMorePage(String bookId, int bookOrder, int bookPage) {
        getBook(bookId,bookOrder,bookPage,true);
    }

    private void getBook(String bookId, int bookOrder, int bookPage, boolean isLoadMore) {
        Call<ComicsBook> task = getComicsBookCallTask(bookId, bookOrder, bookPage);
        task.enqueue(new Callback<ComicsBook>() {
            @Override
            public void onResponse(@NonNull Call<ComicsBook> call, @NonNull Response<ComicsBook> response) {
                LogUtils.d(WatchComicsPresenterImpl.this,"book content code --> "+ response.code());
                if (response.code() == 200 && mCallback != null) {
                    ComicsBook body = response.body();
                    if (body == null ||
                            body.getData() == null ||
                            body.getData().getPages() == null ||
                            body.getData().getPages().getDocs().size() == 0) {
                        mCallback.onEmpty();
                    } else {
                        if (isLoadMore) {
                            mCallback.onBookLoadedMorePage(body.getData().getPages());
                        } else {
                            mCallback.onBookLoaded(body.getData().getPages());
                        }
                    }
                } else {
                    if (mCallback != null) {
                        mCallback.onNetworkError();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ComicsBook> call, @NonNull Throwable t) {
                LogUtils.d(WatchComicsPresenterImpl.this,"book error --> " + t.toString());
                if (mCallback != null) {
                    mCallback.onNetworkError();
                }
            }
        });
    }

    private Call<ComicsBook> getComicsBookCallTask(String bookId, int bookOrder, int bookPage) {
        String url = UrlUtils.getComicsBookUrl(bookId, bookOrder, bookPage);
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        return api.getComicsBook(HeaderUtils.getHeaderMap(url, false), url);
    }
}
