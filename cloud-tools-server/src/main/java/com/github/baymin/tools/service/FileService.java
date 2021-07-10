package com.github.baymin.tools.service;

import com.github.baymin.tools.repository.entity.DFSFileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 文件业务操作接口
 *
 * @author Zongwei
 * @date 2020/4/13 23:16
 */
public interface FileService {

    DFSFileInfo getFileById(String fileId);

    DFSFileInfo uploadFile(MultipartFile filePart);

    void deleteById(String fileId);

    String downloadFile(String dfsBucket, String dfsFileName);

    InputStream getFileInputSteamByFileInfo(DFSFileInfo fileInfo);
}
