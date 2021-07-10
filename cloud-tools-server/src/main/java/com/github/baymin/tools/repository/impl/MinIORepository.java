package com.github.baymin.tools.repository.impl;

import com.github.baymin.tools.config.DFSProperties;
import com.github.baymin.tools.payload.DFSFilePayload;
import com.github.baymin.tools.repository.DfsRepository;
import com.github.baymin.tools.repository.entity.DFSFileInfo;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.IdGenerator;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MinIORepository implements DfsRepository {

    private static final String TEMP_FILE_PATH = "D:/tmp/";

    @Autowired
    private DFSProperties dfsProperties;

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public DFSFilePayload uploadObject(String filePath) {
        String dfsFileName = generateMinIOObject(filePath);
        try {
            ObjectWriteResponse response = minioClient.uploadObject(UploadObjectArgs.builder()
                    .bucket(dfsProperties.getMinio().getBucketName())
                    .filename(filePath)
                    .object(dfsFileName)
                    .build());
            return convertResponse(response);
        } catch (Exception e) {
            throw new RuntimeException("上传文件失败", e);
        }
    }

    @Override
    public DFSFilePayload uploadObject(String fileName, InputStream inputStream) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String dfsFileName = generateMinIOObject(fileName);
        try {
            ObjectWriteResponse response = minioClient.putObject(PutObjectArgs.builder()
                    .bucket(dfsProperties.getMinio().getBucketName())
                    .stream(inputStream, -1, 10485760)
                    .object(dfsFileName)
                    .build());
            stopWatch.stop();
            log.info("上传附件成功，耗时：{}毫秒", stopWatch.getTotalTimeMillis());
            return convertResponse(response);
        } catch (Exception e) {
            throw new RuntimeException("上传文件流失败", e);
        }
    }

    @Override
    public void deleteObject(String fileName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(dfsProperties.getMinio().getBucketName())
                    .object(fileName)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException("删除文件失败", e);
        }
    }

    @Override
    public String downloadObject(String bucket, String fileName) {
        try {
            File directory = new File(TEMP_FILE_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String localFilePath = TEMP_FILE_PATH + fileName;
            minioClient.downloadObject(DownloadObjectArgs.builder()
                    .bucket(bucket)
                    .object(fileName)
                    .filename(TEMP_FILE_PATH + fileName)
                    .build());
            return localFilePath;
        } catch (Exception e) {
            throw new RuntimeException("下载文件失败", e);
        }
    }

    @Override
    public InputStream getObjectStream(DFSFileInfo fileInfo) {
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(dfsProperties.getMinio().getBucketName())
                    .object(fileInfo.getDfsFileName())
                    .build());
        } catch (Exception e) {
            throw new RuntimeException("获取文件流失败", e);
        }
    }

    /**
     * 根据文件名生成minIO中的文件名
     *
     * @param fileName 原始文件名
     * @return String
     */
    private String generateMinIOObject(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        return idGenerator.generateId() + suffix;
    }

    private DFSFilePayload convertResponse(ObjectWriteResponse response) {
        DFSFilePayload filePayload = new DFSFilePayload();
        filePayload.setDfsFileId(response.object());

        Map<String, Object> extensions = new HashMap<>();
        extensions.put("etag", response.etag());
        extensions.put("bucket", response.bucket());
        extensions.put("versionId", response.versionId());
        extensions.put("region", response.region());
        filePayload.setDfsFileExtensions(extensions);
        return filePayload;
    }

}
