package com.ruoyi.system.oss;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.ruoyi.common.exception.file.OssException;
import com.ruoyi.common.utils.file.FileUploadUtils;

import org.apache.commons.io.IOUtils;

/**
 * 腾讯云存储
 */
public class LocalStorageService extends CloudStorageService {
    String uploadPath;

    public LocalStorageService(CloudStorageConfig config) {
        this.config = config;
        // 初始化
        init();
    }

    private void init() {
        uploadPath = config.getLocalUploadDir();
        if(!uploadPath.endsWith("/"))
            uploadPath += "/";
    }

    @Override
    public String upload(byte[] data, String path) {
        try {
            File file = new File(uploadPath + path);

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
             
            FileOutputStream fos = new FileOutputStream(file);
            if (null != fos) {
                fos.write(data, 0, data.length);
                fos.flush();
                fos.close();
            }
            
        } catch (Exception e) {
            throw new OssException("文件上传失败，" + e.getMessage());
        }
        return "/" + path;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        } catch (IOException e) {
            throw new OssException("上传文件失败");
        }
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getQcloudPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getQcloudPrefix(), suffix));
    }
}
