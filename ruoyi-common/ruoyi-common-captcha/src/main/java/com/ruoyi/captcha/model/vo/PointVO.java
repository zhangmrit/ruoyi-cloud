package com.ruoyi.captcha.model.vo;

/**
 * Created by raodeming on 2020/5/16.
 */
public class PointVO{
    private String secretKey;

    public int x;

    public int y;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PointVO(int x, int y, String secretKey) {
        this.secretKey = secretKey;
        this.x = x;
        this.y = y;
    }

    public PointVO() {
    }

    public PointVO(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
