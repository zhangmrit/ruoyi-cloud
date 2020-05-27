/*
 *Copyright © 2018 anji-plus
 *安吉加加信息技术有限公司
 *http://www.anji-plus.com
 *All rights reserved.
 */
package com.ruoyi.captcha.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.captcha.model.common.RepCodeEnum;
import com.ruoyi.captcha.model.common.ResponseModel;
import com.ruoyi.captcha.model.vo.CaptchaVO;
import com.ruoyi.captcha.model.vo.PointVO;
import com.ruoyi.captcha.util.AESUtil;
import com.ruoyi.captcha.util.ImageUtils;
import com.ruoyi.captcha.util.RandomUtils;
import com.ruoyi.captcha.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * 点选文字验证码
 *
 * Created by raodeming on 2019/12/25.
 */
@Component(value = "clickWordCaptchaService")
public class ClickWordCaptchaServiceImpl extends AbstractCaptchaservice {

    private static Logger logger = LoggerFactory.getLogger(ClickWordCaptchaServiceImpl.class);

    @Value("${captcha.water.mark:'我的水印'}")
    private String waterMark;

    @Value("${captcha.water.font:'宋体'}")
    private String waterMarkFont;

    @Value("${captcha.font.type:'宋体'}")
    private String fontType;

    private static Boolean captchaAesStatus;

    @Value("${captcha.aes.status:true}")
    public void setCaptchaAesStatus(Boolean captchaAesStatus) {
        ClickWordCaptchaServiceImpl.captchaAesStatus = captchaAesStatus;
    }

    @Override
    public ResponseModel get(CaptchaVO captchaVO) {
        BufferedImage bufferedImage = ImageUtils.getPicClick();
        CaptchaVO imageData = getImageData(bufferedImage);
        if (imageData == null
                || StringUtils.isBlank(imageData.getOriginalImageBase64())) {
            return ResponseModel.errorMsg(RepCodeEnum.API_CAPTCHA_ERROR);
        }
        return ResponseModel.successData(imageData);
    }

    @Override
    public ResponseModel check(CaptchaVO captchaVO) {
        //取坐标信息
        String codeKey = String.format(REDIS_CAPTCHA_KEY, captchaVO.getToken());
        if (!captchaCacheService.exists(codeKey)) {
            return ResponseModel.errorMsg(RepCodeEnum.API_CAPTCHA_INVALID);
        }
        String s = captchaCacheService.get(codeKey);
        //验证码只用一次，即刻失效
        captchaCacheService.delete(codeKey);
        List<PointVO> point = null;
        List<PointVO> point1 = null;
        String pointJson = null;
        /**
         * [
         *             {
         *                 "x": 85.0,
         *                 "y": 34.0
         *             },
         *             {
         *                 "x": 129.0,
         *                 "y": 56.0
         *             },
         *             {
         *                 "x": 233.0,
         *                 "y": 27.0
         *             }
         * ]
         */
        try {
            point = JSONObject.parseArray(s, PointVO.class);
            //aes解密
            pointJson = decrypt(captchaVO.getPointJson(), point.get(0).getSecretKey());
            point1 = JSONObject.parseArray(pointJson, PointVO.class);
        } catch (Exception e) {
            logger.error("验证码坐标解析失败", e);
            return ResponseModel.errorMsg(e.getMessage());
        }
        for (int i = 0; i < point.size(); i++) {
            if (point.get(i).x-HAN_ZI_SIZE > point1.get(i).x
                    || point1.get(i).x > point.get(i).x+HAN_ZI_SIZE
                    || point.get(i).y-HAN_ZI_SIZE > point1.get(i).y
                    || point1.get(i).y > point.get(i).y+HAN_ZI_SIZE) {
                return ResponseModel.errorMsg(RepCodeEnum.API_CAPTCHA_COORDINATE_ERROR);
            }
        }
        //校验成功，将信息存入缓存
        String secretKey = point.get(0).getSecretKey();
        String value = null;
        try {
            value = AESUtil.aesEncrypt(captchaVO.getToken().concat("---").concat(pointJson), secretKey);
        } catch (Exception e) {
            logger.error("AES加密失败", e);
            return ResponseModel.errorMsg(e.getMessage());
        }
        String secondKey = String.format(REDIS_SECOND_CAPTCHA_KEY, value);
        captchaCacheService.set(secondKey, captchaVO.getToken(), EXPIRESIN_THREE);
        captchaVO.setResult(true);
        return ResponseModel.successData(captchaVO);
    }

    @Override
    public ResponseModel verification(String verify) {
        return null;
    }


    private CaptchaVO getImageData(BufferedImage backgroundImage) {
        CaptchaVO dataVO = new CaptchaVO();
        List<String> wordList = new ArrayList<String>();
        List<PointVO> pointList = new ArrayList<PointVO>();

        Graphics backgroundGraphics = backgroundImage.getGraphics();
        int width = backgroundImage.getWidth();
        int height = backgroundImage.getHeight();

        Font font = new Font(fontType, Font.BOLD, HAN_ZI_SIZE);
        int wordCount = getWordTotalCount();
        //定义随机1到arr.length某一个字不参与校验
        int num = RandomUtils.getRandomInt(1, wordCount);
        Set<String> currentWords = new HashSet<String>();
        String secretKey = null;
        if (captchaAesStatus) {
            secretKey = AESUtil.getKey();
        }
        for (int i = 0; i < wordCount; i++) {
            String word;
            do {
                word = RandomUtils.getRandomHan(HAN_ZI);
                currentWords.add(word);
            } while (!currentWords.contains(word));

            //随机字体坐标
            PointVO point = randomWordPoint(width, height, i, wordCount);
            point.setSecretKey(secretKey);
            //随机字体颜色
            if (isFontColorRandom()){
                backgroundGraphics.setColor(new Color(RandomUtils.getRandomInt(1,255),RandomUtils.getRandomInt(1,255),RandomUtils.getRandomInt(1,255)));
            } else {
                backgroundGraphics.setColor(Color.BLACK);
            }
            //设置角度
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.rotate(Math.toRadians(RandomUtils.getRandomInt(-45, 45)), 0, 0);
            Font rotatedFont = font.deriveFont(affineTransform);
            backgroundGraphics.setFont(rotatedFont);
            backgroundGraphics.drawString(word, (int)point.getX(), (int)point.getY());

            if ((num-1) != i) {
                wordList.add(word);
                pointList.add(point);
            }
        }


        Font watermark = new Font(waterMarkFont, Font.BOLD, HAN_ZI_SIZE/2);
        backgroundGraphics.setFont(watermark);
        backgroundGraphics.setColor(Color.white);
        backgroundGraphics.drawString(waterMark, width-((HAN_ZI_SIZE/2)*(waterMark.length()))-5, height-(HAN_ZI_SIZE/2)+7);

        //创建合并图片
        BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics combinedGraphics = combinedImage.getGraphics();
        combinedGraphics.drawImage(backgroundImage, 0, 0, null);

        dataVO.setOriginalImageBase64(ImageUtils.getImageToBase64Str(backgroundImage).replaceAll("\r|\n", ""));
        //pointList信息不传到前端，只做后端check校验
        //dataVO.setPointList(pointList);
        dataVO.setWordList(wordList);
        dataVO.setToken(RandomUtils.getUUID());
        dataVO.setSecretKey(secretKey);
        //将坐标信息存入redis中
        String codeKey = String.format(REDIS_CAPTCHA_KEY, dataVO.getToken());
        captchaCacheService.set(codeKey, JSONObject.toJSONString(pointList), EXPIRESIN_SECONDS);
//        base64StrToImage(getImageToBase64Str(backgroundImage), "D:\\点击.png");
        return dataVO;
    }

    /**
     * 随机字体循环排序下标
     * @param imageWidth 图片宽度
     * @param imageHeight 图片高度
     * @param wordSortIndex  字体循环排序下标(i)
     * @param wordCount  字数量
     * @return
     */
    private static PointVO randomWordPoint(int imageWidth, int imageHeight, int wordSortIndex, int wordCount) {
        int avgWidth = imageWidth / (wordCount+1);
        int x, y;
        if (avgWidth < HAN_ZI_SIZE_HALF){
            x = RandomUtils.getRandomInt(1+HAN_ZI_SIZE_HALF, imageWidth);
        } else {
            if (wordSortIndex == 0) {
                x = RandomUtils.getRandomInt(1+HAN_ZI_SIZE_HALF, avgWidth * (wordSortIndex+1) - HAN_ZI_SIZE_HALF );
            }else {
                x = RandomUtils.getRandomInt(avgWidth * wordSortIndex + HAN_ZI_SIZE_HALF, avgWidth * (wordSortIndex+1) -HAN_ZI_SIZE_HALF );
            }
        }
        y = RandomUtils.getRandomInt(HAN_ZI_SIZE, imageHeight - HAN_ZI_SIZE);
        return new PointVO(x, y, null);
    }


}
