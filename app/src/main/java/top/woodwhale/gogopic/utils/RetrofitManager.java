package top.woodwhale.gogopic.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final RetrofitManager instance = new RetrofitManager();
    private final Retrofit mRetrofit;

    private RetrofitManager() {
        // 创建一个retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    public static RetrofitManager getInstance() {
        return instance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

}
