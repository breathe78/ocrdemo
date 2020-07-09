package com.example.ocrdemo.entity;


public class EmployeeRequest {

    /*员工OCR*/
    private String imgStr;
    /**
     * 正面（人像） front，反面（国辉） back
     */
    private String side;

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
