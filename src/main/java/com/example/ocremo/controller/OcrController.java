package com.example.ocremo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.ocrdemo.entity.EmployeeRequest;
import com.example.ocrdemo.util.BaiduUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/card")
public class OcrController {

    /**
     * 身份证OCR
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/check")
    public String discernIdCard(EmployeeRequest request) throws Exception {
        //图片加密字节。
        String imgStr = this.getImageStr("E:\\图\\身份证.jpg");   //换成你自己的图片地址
        //获取百度token。
        String baiduToken = this.getBaiduAccessToken();
        //获取身份证件，正反面。
        String side = request.getSide();
        String str = BaiduUtil.discernIdCard(baiduToken, imgStr, side);
        Map<String, Object> map = JSONObject.parseObject(str, Map.class);
        //以下可以操作map
        return str;
    }

    /**
     * 获取百度TOken
     *
     * @return
     */
    private String getBaiduAccessToken() {
        //accessToken = KungeekBaiduUtil.accessToken("Ef3HOGb9GzW5iakw5xLfg9Gb","7hpGaynnCgUkeDPlnnbG34IxEkBxoP1h");
        //用于连接百度
        String accessToken = BaiduUtil.accessToken("Ef3HOGb9GzW5iakw5xLfg9Gb", "7hpGaynnCgUkeDPlnnbG34IxEkBxoP1h");
        return accessToken;
    }

    public String getImageStr(String filePath) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(filePath);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

}
