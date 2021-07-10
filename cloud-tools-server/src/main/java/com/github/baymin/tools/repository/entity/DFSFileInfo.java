package com.github.baymin.tools.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 文件信息
 *
 * @author Zongwei
 * @date 2020/4/13 23:20
 */
@Data
@Entity
@Table(name = "T_DFS_FILE_INFO")
public class DFSFileInfo implements Serializable {

    private static final long serialVersionUID = 5643680246285696678L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ORIGIN_FILE_NAME")
    private String originFileName;

    @Column(name = "ORIGIN_FILE_TYPE")
    private String originFileType;

    @Column(name = "ORIGIN_FILE_SIZE")
    private Long originFileSize;

    @Column(name = "DFS_TYPE")
    private String dfsType;

    @Column(name = "DFS_FILE_NAME")
    private String dfsFileName;

    @Column(name = "EXTENSIONS")
    private String extensions;

    @Column(name = "CREATED_AT")
    private Date createdAt;

}
