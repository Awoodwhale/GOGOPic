package top.woodwhale.gogopic.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class AlgorithmUtils {
    private static String HmacSHA256(byte[] data, byte[] key) {
        SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA256");
            Objects.requireNonNull(mac).init(signingKey);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return byte2hex(Objects.requireNonNull(mac).doFinal(data));
    }

    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString();
    }

    public static String getSignature(String urlPart, boolean isPost) {
        String operator_url = urlPart + Constants.TIMESTAMP + Constants.NONCE ;
        operator_url += isPost ? "POST"  : "GET";
        operator_url += Constants.API_KEY;
        operator_url = operator_url.toLowerCase();
        return AlgorithmUtils.HmacSHA256(operator_url.getBytes(), Constants.KEY.getBytes());
    }
}
