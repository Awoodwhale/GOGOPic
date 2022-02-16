package top.woodwhale.gogopic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreferencesUtils {
    public final static String LOGIN_PREFERENCE_NAME = "GOGOPicLogin";
    public final static String SEARCH_PREFERENCE_NAME = "GOGOPicSearch";
    public final static String SEARCH_HISTORY = "GOGOPicSearchHistory";
    public final static String BAN_BOOKS = "GOGOPicBanBooks";
    // 保存搜索记录
    public static void saveSearchHistory(String inputText, Context context) {
        SharedPreferences sp = context.
                getSharedPreferences(SEARCH_PREFERENCE_NAME, Context.MODE_PRIVATE);
        if (TextUtils.isEmpty(inputText)) {
            return;
        }
        String longHistory = sp.getString(SEARCH_HISTORY, "");  //获取之前保存的历史记录
        String[] tmpHistory = longHistory.split(","); //逗号截取 保存在数组中
        List<String> historyList = new ArrayList<>(Arrays.asList(tmpHistory)); //将改数组转换成ArrayList
        SharedPreferences.Editor editor = sp.edit();
        if (historyList.size() > 0) {
            //1.移除之前重复添加的元素
            for (int i = 0; i < historyList.size(); i++) {
                if (inputText.equals(historyList.get(i))) {
                    historyList.remove(i);
                    break;
                }
            }
            historyList.add(0, inputText); //将新输入的文字添加集合的第0位也就是最前面
            if (historyList.size() > 20) {
                historyList.remove(historyList.size() - 1); // 最多保存20条搜索记录 删除最早搜索的那一项
            }
            //逗号拼接
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < historyList.size(); i++) {
                sb.append(historyList.get(i)).append(",");
            }
            //保存到sp
            editor.putString(SEARCH_HISTORY, sb.toString());
        } else {
            //之前未添加过
            editor.putString(SEARCH_HISTORY, inputText + ",");
        }
        editor.apply();
    }
    //获取搜索记录
    public static List<String> getSearchHistory(Context context){
        SharedPreferences sp = context.getSharedPreferences(SEARCH_PREFERENCE_NAME, Context.MODE_PRIVATE);
        String longHistory = sp.getString(SEARCH_HISTORY, "");
        String[] tmpHistory = longHistory.split(","); //split后长度为1有一个空串对象
        List<String> historyList = new ArrayList<>(Arrays.asList(tmpHistory));
        if (historyList.size() == 1 && historyList.get(0).equals("")) { //如果没有搜索记录，split之后第0位是个空串的情况下
            historyList.clear();  //清空集合，这个很关键
        }
        return historyList;
    }

    // 清除历史搜素
    public static void clearSearchHistory(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SEARCH_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    // 读取ban书目录
    public static List<String> getBanBooks(Context context) {
        SharedPreferences sp = context.getSharedPreferences(BAN_BOOKS, Context.MODE_PRIVATE);
        String banStr = sp.getString(BAN_BOOKS, null);
        if (banStr != null) {
            String[] banBooks = banStr.split(",");
            return new ArrayList<>(Arrays.asList(banBooks));
        }
        return new ArrayList<>();
    }

    // 编辑ban书目录
    public static void handleBanBooks(List<String> booksList,Context context) {
        SharedPreferences sp = context.getSharedPreferences(BAN_BOOKS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        // 进来就删除
        editor.clear();
        if (booksList.size() != 0) {
            StringBuilder sb = new StringBuilder();
            for (String s : booksList) {
                sb.append(s).append(",");
            }
            // 删除最后一位
            int oldLength = sb.length();
            sb.deleteCharAt(oldLength-1);
            editor.putString(BAN_BOOKS,sb.toString());
        }
        editor.apply();
    }
}
