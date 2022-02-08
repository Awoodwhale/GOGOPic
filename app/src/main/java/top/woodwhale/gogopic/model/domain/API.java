package top.woodwhale.gogopic.model.domain;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;
import top.woodwhale.gogopic.utils.UrlUtils;

public interface API {
    @POST(UrlUtils.LOGIN_URL)
    Call<AuthSignIn> getAuthSignIn(@HeaderMap Map<String, String> map, @Body RequestBody body);

    @GET(UrlUtils.CATEGORIES_URL)
    Call<Categories> getCategories(@HeaderMap Map<String, String> map);

    @GET
    Call<ComicsCategory> getComics(@HeaderMap Map<String, String> headerMap, @Url String url);

    @GET(UrlUtils.USER_INFO_URL)
    Call<UserInfo> getUserInfo(@HeaderMap Map<String, String> headerMap);

    @POST(UrlUtils.CHECK_IN_URL)
    Call<ResponseBody> getCheckInInfo(@HeaderMap Map<String, String> headerMap);
}
