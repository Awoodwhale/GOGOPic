package top.woodwhale.gogopic.model;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;
import top.woodwhale.gogopic.model.domain.AuthSignIn;
import top.woodwhale.gogopic.model.domain.Categories;
import top.woodwhale.gogopic.model.domain.ComicsCategory;
import top.woodwhale.gogopic.model.domain.ComicsChapter;
import top.woodwhale.gogopic.model.domain.ComicsComment;
import top.woodwhale.gogopic.model.domain.ComicsMain;
import top.woodwhale.gogopic.model.domain.Keywords;
import top.woodwhale.gogopic.model.domain.LikeOrFavorite;
import top.woodwhale.gogopic.model.domain.UserInfo;
import top.woodwhale.gogopic.utils.UrlUtils;

public interface API {
    // 登录
    @POST(UrlUtils.LOGIN_URL)
    Call<AuthSignIn> getAuthSignIn(@HeaderMap Map<String, String> map, @Body RequestBody body);

    // 首页分类
    @GET(UrlUtils.CATEGORIES_URL)
    Call<Categories> getCategories(@HeaderMap Map<String, String> map);

    // 分类详情
    @GET
    Call<ComicsCategory> getCategoryComics(@HeaderMap Map<String, String> headerMap, @Url String url);

    // 个人信息
    @GET(UrlUtils.USER_INFO_URL)
    Call<UserInfo> getUserInfo(@HeaderMap Map<String, String> headerMap);

    // 热搜
    @GET(UrlUtils.KEYWORDS_URL)
    Call<Keywords> getKeywordsContent(@HeaderMap Map<String, String> headerMap);

    // 搜索关键字
    @POST
    Call<ComicsCategory> getSearchCategoryComics(@HeaderMap Map<String, String> headerMap,
                                               @Url String url, @Body RequestBody body);

    // 我的收藏
    @GET
    Call<ComicsCategory> getFavoriteComics(@HeaderMap Map<String, String> headerMap,@Url String url);

    // 获取漫画详情
    @GET
    Call<ComicsMain> getComicsInfo(@HeaderMap Map<String, String> headerMap, @Url String url);

    // 点赞漫画
    @POST
    Call<LikeOrFavorite> getComicsLike(@HeaderMap Map<String, String> headerMap, @Url String url);

    // 收藏漫画
    @POST
    Call<LikeOrFavorite> getComicsFavorite(@HeaderMap Map<String, String> headerMap,@Url String url);

    // 获取漫画章节
    @GET
    Call<ComicsChapter> getEpsChapter(@HeaderMap Map<String, String> headerMap, @Url String url);

    // 获取漫画评论
    @GET
    Call<ComicsComment> getComicsComment(@HeaderMap Map<String, String> headerMap, @Url String url);
}
