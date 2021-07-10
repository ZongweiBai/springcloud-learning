package com.github.baymin.tools.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.baymin.tools.config.DFSProperties;
import com.github.baymin.tools.payload.DFSFilePayload;
import com.github.baymin.tools.repository.DfsRepository;
import com.github.baymin.tools.repository.FileInfoRepository;
import com.github.baymin.tools.repository.entity.DFSFileInfo;
import com.github.baymin.tools.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.Objects;

/**
 * 文件业务操作接口实现类
 *
 * @author Zongwei
 * @date 2020/4/13 23:18
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private DFSProperties dfsProperties;

    @Autowired
    private DfsRepository dfsRepository;

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public DFSFileInfo uploadFile(MultipartFile filePart) {
        try (InputStream fileIs = filePart.getInputStream();) {
            DFSFilePayload filePayload = dfsRepository.uploadObject(filePart.getOriginalFilename(), fileIs);

            DFSFileInfo dfsFileInfo = new DFSFileInfo();
            dfsFileInfo.setOriginFileName(filePart.getOriginalFilename());
            dfsFileInfo.setDfsType(dfsProperties.getStore());
            dfsFileInfo.setOriginFileSize(filePart.getSize());
            dfsFileInfo.setOriginFileType(filePart.getContentType());
            dfsFileInfo.setDfsFileName(filePayload.getDfsFileId());
            dfsFileInfo.setExtensions(objectMapper.writeValueAsString(filePayload.getDfsFileExtensions()));
            dfsFileInfo.setCreatedAt(new Date());

            fileInfoRepository.save(dfsFileInfo);
            return dfsFileInfo;
        } catch (Exception e) {
            throw new RuntimeException("上传文件到DFS失败", e);
        }
    }

    @Override
    public DFSFileInfo getFileById(String fileId) {
        return fileInfoRepository.findById(Long.parseLong(fileId)).orElseThrow(() -> new RuntimeException("文件不存在"));
    }

    @Override
    public void deleteById(String fileId) {
        DFSFileInfo DFSFileInfo = this.getFileById(fileId);
        if (Objects.isNull(DFSFileInfo)) {
            log.warn("文件不存在:{}", fileId);
            return;
        }

        dfsRepository.deleteObject(DFSFileInfo.getDfsFileName());
        fileInfoRepository.deleteById(Long.parseLong(fileId));
    }

    @Override
    public String downloadFile(String dfsBucket, String dfsFileName) {
        return dfsRepository.downloadObject(dfsBucket, dfsFileName);
    }

    @Override
    public InputStream getFileInputSteamByFileInfo(DFSFileInfo fileInfo) {
        return dfsRepository.getObjectStream(fileInfo);
    }
}
