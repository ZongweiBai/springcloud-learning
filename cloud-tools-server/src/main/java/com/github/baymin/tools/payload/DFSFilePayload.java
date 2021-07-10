package com.github.baymin.tools.payload;

import lombok.Data;

import java.util.Map;

/**
 * @author BaiZongwei
 * @date 2021/5/17 11:32
 */
@Data
public class DFSFilePayload {

    /**
     * 分布式文件ID
     */
    private String dfsFileId;

    /**
     * 扩展信息
     */
    private Map<String, Object> dfsFileExtensions;
}
