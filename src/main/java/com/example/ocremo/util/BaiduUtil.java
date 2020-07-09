package com.example.ocrdemo.util;

import com.alibaba.fastjson.JSONObject;

import java.net.URLEncoder;
import java.util.Map;

public class BaiduUtil {
    /**
     * 身份证OCR
     *
     * @param accessToken
     * @param imageStr
     * @param side
     * @return
     * @throws Exception
     */
    public static String discernIdCard(String accessToken, String imageStr, String side) throws Exception {
        String bankcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?access_token=" + accessToken;
        String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imageStr, "UTF-8");
        params += "&id_card_side=" + side;
        String result = HttpUtils.sendPost(bankcardIdentificate, params, null);
        Map<String, Object> map = JSONObject.parseObject(result, Map.class);
        if (map.get("error_code") != null) {
            return null;
        }
        if (map.get("image_status").toString().equals("normal") || map.get("image_status").toString().equals("reversed_side")) {
            return JSONObject.toJSONString(map.get("words_result"));
        } else {
            return JSONObject.toJSONString(map.get("image_status"));
        }
    }

    /**
     * 获取百度TOken
     *
     * @return
     */
    public static String accessToken(String akey, String skey) {
        String url = String.format("https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=%s&client_secret=%s"
                , akey, skey);
        String result = HttpUtils.sendPost(url, null, null);
        Map<String, String> map = JSONObject.parseObject(result, Map.class);
        String accessToken = map.get("access_token");
        return accessToken;
    }

}
