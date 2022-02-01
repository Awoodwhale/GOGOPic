package top.woodwhale.gogopic.utils;

import java.util.HashMap;
import java.util.Map;

public class HeaderUtils {

    public static Map<String, String> getHeaderMap(String urlPart, boolean isPost) {
        // 每次访问都需要更新这两个header
        Constants.TIMESTAMP = String.valueOf(System.currentTimeMillis() / 1000);
        Constants.SIGNATURE = AlgorithmUtils.getSignature(urlPart, isPost);
        Map<String,String> map = new HashMap<>();
        map.put("api-key",Constants.API_KEY);
        map.put("accept","application/vnd.picacomic.com.v1+json");
        map.put("app-channel","1");
        map.put("time",Constants.TIMESTAMP);
        map.put("nonce",Constants.NONCE);
        map.put("signature",Constants.SIGNATURE);
        map.put("app-version","2.2.1.3.3.4");
        map.put("app-uuid", "defaultUuid");
        map.put("app-platform","android");
        map.put("app-build-version","45");
        map.put("User-Agent","okhttp/3.8.1");
        if (Constants.LOGIN_TOKEN != null) {
            map.put("authorization", Constants.LOGIN_TOKEN);
        }
        map.put("image-quality","original");
        return map;
    }
}
