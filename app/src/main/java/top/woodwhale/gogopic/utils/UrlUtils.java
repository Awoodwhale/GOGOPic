package top.woodwhale.gogopic.utils;

public class UrlUtils {
    // 各个部分的urlPart
    public static final String LOGIN_URL = "auth/sign-in";
    public static final String CATEGORIES_URL = "categories";
    public static final String COMICS_URL = "comics";
    public static final String KEYWORDS_URL = "keywords";
    public static final String USER_INFO_URL = "users/profile";

    public static String BOOK_INFO_URL(String bookID) {
        return "comics/"+bookID;
    }

    public static String BOOK_PAGE_INFO_URL(String bookID,String page) {
        return "comics/"+bookID+"/eps?page="+page;
    }

    public static String BOOK_CONTENT_INFO_URL(String bookID, String espID, String page) {
        return "comics/"+bookID+"/order/"+espID+"/pages?page="+page;
    }

    public static String RECOMMEND_INFO_URL(String bookID) {
        return "comics/"+bookID+"/recommendation";
    }

    public static String SEARCH_URL(String page) {
        return "comics/advanced-search?page="+page;
    }

}
