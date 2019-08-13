package com.ruoyi.system.oss;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.ruoyi.common.exception.file.OssException;

/**
 * 腾讯云存储
 */
public class QcloudCloudStorageService extends CloudStorageService
{
    private COSClient client;

    public QcloudCloudStorageService(CloudStorageConfig config)
    {
        this.config = config;
        // 初始化
        init();
    }

    private void init()
    {
        COSCredentials credentials = new BasicCOSCredentials(config.getQcloudSecretId(), config.getQcloudSecretKey());
        // 设置bucket所在的区域，最新sdk不再支持简写，请填写完整
        Region region = new Region(config.getQcloudRegion());
        ClientConfig clientConfig = new ClientConfig(region);
        client = new COSClient(credentials, clientConfig);
    }

    @Override
    public String upload(byte[] data, String path)
    {
        try
        {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            // 从输入流上传必须制定content length, 否则http客户端可能会缓存所有数据，存在内存OOM的情况
            objectMetadata.setContentLength(data.length);
            PutObjectRequest putObjectRequest = new PutObjectRequest(config.getQcloudBucketName(), path,
                    new ByteArrayInputStream(data), objectMetadata);
            // 设置存储类型, 默认是标准(Standard), 低频(standard_ia)
            // putObjectRequest.setStorageClass(StorageClass.Standard_IA);
            client.putObject(putObjectRequest);
        }
        catch (Exception e)
        {
            throw new OssException("文件上传失败，" + e.getMessage());
        }
        return config.getQcloudDomain() +"/"+ path;
    }

    @Override
    public String upload(InputStream inputStream, String path)
    {
        try
        {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        }
        catch (IOException e)
        {
            throw new OssException("上传文件失败");
        }
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix)
    {
        return upload(data, getPath(config.getQcloudPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix)
    {
        return upload(inputStream, getPath(config.getQcloudPrefix(), suffix));
    }
}
