package top.woodwhale.gogopic.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlUtils {
    // 各个部分的urlPart
    public static final String LOGIN_URL = "auth/sign-in";
    public static final String CATEGORIES_URL = "categories";
    public static final String COMICS_URL = "comics";
    public static final String KEYWORDS_URL = "keywords";
    public static final String USER_INFO_URL = "users/profile";
    public static String getComicsInfoUrl(String bookID) {
        return "comics/"+bookID;
    }
    public static String getSearchUrl(String page) {
        return "comics/advanced-search?page="+page;
    }
    public static String getComicsCategoryInfoUrl(String page, String title, String sort) {
        String url = null;
        try {
            url = UrlUtils.COMICS_URL
                    +"?page="+page
                    +"&c="+ URLEncoder.encode(title,"utf-8")
                    +"&s="+sort;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static String getComicsCategoryInfoUrl(String page, String title) {
        return getComicsCategoryInfoUrl(page,title,"ua");
    }
    public static String getAddStaticPicPathUrl(String fileServer, String path) {
        return fileServer + "/static/" + path;
    }
    public static String getComicsSortWay(int pos) {
        String sortWay;
        if (pos == 0) {
            sortWay = "ua";
        } else if (pos == 1) {
            sortWay = "ld";
        } else if (pos == 2) {
            sortWay = "vd";
        } else if (pos == 3) {
            sortWay = "dd";
        } else {
            sortWay = "da";
        }
        return sortWay;
    }
    public static String getFavoriteComicsInfoUrl(int page) {
        return "users/favourite?s=dd&page="+page;
    }

    public static String getComicsLikeUrl(String bookID) {
        return "comics/"+bookID+"/like";
    }

    public static String getComicsFavoriteUrl(String bookID) {
        return "comics/"+bookID+"/favourite";
    }

    public static String getEpsChapterUrl(String bookID, int page) {
        return "comics/"+bookID+"/eps?page="+page;
    }

    public static String getComicsCommentUrl(String bookID, int page) {
        return "comics/"+bookID+"/comments?page="+page;
    }
}
