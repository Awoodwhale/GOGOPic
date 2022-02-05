package top.woodwhale.gogopic.presenter.impl;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import top.woodwhale.gogopic.model.domain.API;
import top.woodwhale.gogopic.model.domain.Categories;
import top.woodwhale.gogopic.presenter.IHomePresenter;
import top.woodwhale.gogopic.utils.HeaderUtils;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.RetrofitManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.IHomeCallback;

public class HomePresenterImpl implements IHomePresenter {
    private IHomeCallback mCallback;

    @Override
    public void registerViewCallback(IHomeCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IHomeCallback callback) {
        this.mCallback = null;
    }

    @Override
    public void getCategories() {
        if (mCallback != null) {
            mCallback.onLoading();
        }
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        Call<Categories> task = api.getCategories(HeaderUtils.getHeaderMap(UrlUtils.CATEGORIES_URL,false));
        task.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@NonNull Call<Categories> call, @NonNull Response<Categories> response) {
                LogUtils.d(HomePresenterImpl.this,UrlUtils.CATEGORIES_URL +" code --> " + response.code());
                if (response.code() == 200 && mCallback != null) {
                    Categories categories = response.body();
                    if (categories == null || categories.getData().getCategories().size() == 0) {
                        mCallback.onEmpty();
                    } else {
                        // 成功就回调处理
                        mCallback.onCategoriesLoaded(handleCategories(categories));
                    }
                } else {
                    if (mCallback != null) {
                        mCallback.onNetworkError();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Categories> call, @NonNull Throwable t) {
                if (mCallback != null) {
                    mCallback.onNetworkError();
                }
                LogUtils.e(HomePresenterImpl.this,"首页请求错误！" + t.toString());
            }
        });
    }

    // 将有用的信息抽取出来进行回调
    private List<Categories.DataBean.CategoriesBean> handleCategories(Categories categories) {
        List<Categories.DataBean.CategoriesBean> tmpList = new ArrayList<>();
        List<Categories.DataBean.CategoriesBean> categoriesBeanList = categories.getData().getCategories();
        for (Categories.DataBean.CategoriesBean categoriesBean : categoriesBeanList) {
            Categories.DataBean.CategoriesBean.ThumbBean thumb = categoriesBean.getThumb();
            String imgPath = thumb.getFileServer()+"/static/"+thumb.getPath();
            if (imgPath.contains(".picacomic.")) {
                tmpList.add(categoriesBean);
            }
        }
        return tmpList;
    }
}
