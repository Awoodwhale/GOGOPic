package top.woodwhale.gogopic.model.domain;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import top.woodwhale.gogopic.utils.UrlUtils;

public interface API {
    @POST(UrlUtils.LOGIN_URL)
    Call<AuthSignIn> getAuthSignIn(@HeaderMap Map<String, String> map, @Body RequestBody body);

    @GET(UrlUtils.CATEGORIES_URL)
    Call<Categories> getCategories(@HeaderMap Map<String, String> map);

    @GET(UrlUtils.COMICS_URL)
    Call<ResponseBody> getComics(@HeaderMap Map<String, String> headerMap, @QueryMap Map<String,String> queryMap);
}
