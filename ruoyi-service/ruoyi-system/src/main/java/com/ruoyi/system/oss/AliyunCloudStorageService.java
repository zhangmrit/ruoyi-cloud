package com.ruoyi.system.oss;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ruoyi.common.exception.file.OssException;

/**
 * 阿里云存储
 */
public class AliyunCloudStorageService extends CloudStorageService
{
    private OSS client;

    public AliyunCloudStorageService(CloudStorageConfig config)
    {
        this.config = config;
        // 初始化
        init();
    }

    private void init()
    {
        client = new OSSClientBuilder().build(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
                config.getAliyunAccessKeySecret());
    }

    @Override
    public String upload(byte[] data, String path)
    {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path)
    {
        try
        {
            client.putObject(config.getAliyunBucketName(), path, inputStream);
        }
        catch (Exception e)
        {
            throw new OssException("上传文件失败，请检查配置信息");
        }
        return config.getAliyunDomain() + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix)
    {
        return upload(data, getPath(config.getAliyunPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix)
    {
        return upload(inputStream, getPath(config.getAliyunPrefix(), suffix));
    }
}
