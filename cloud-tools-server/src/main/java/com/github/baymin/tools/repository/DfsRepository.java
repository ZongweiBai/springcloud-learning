package com.github.baymin.tools.repository;

import com.github.baymin.tools.payload.DFSFilePayload;
import com.github.baymin.tools.repository.entity.DFSFileInfo;

import java.io.InputStream;

/**
 * 分布式文件访问
 */
public interface DfsRepository {

    /**
     * 上传到文件服务器
     *
     * @param filePath 文件路径
     * @return ObjectWriteResponse
     */
    DFSFilePayload uploadObject(String filePath);

    /**
     * 上传流到文件服务器
     *
     * @param fileName    文件名
     * @param inputStream 文件流
     * @return ObjectWriteResponse
     */
    DFSFilePayload uploadObject(String fileName, InputStream inputStream);

    /**
     * 删除文件
     *
     * @param fileName dfs下的文件名
     */
    void deleteObject(String fileName);

    /**
     * 下载文件，返回绝对路径
     */
    String downloadObject(String bucket, String fileName);

    InputStream getObjectStream(DFSFileInfo fileInfo);
}
