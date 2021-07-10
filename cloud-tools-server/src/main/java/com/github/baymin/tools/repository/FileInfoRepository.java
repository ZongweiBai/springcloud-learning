package com.github.baymin.tools.repository;

import com.github.baymin.tools.repository.entity.DFSFileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 数据库访问层
 *
 * @author Zongwei
 * @date 2020/4/13 23:28
 */
@Repository
public interface FileInfoRepository extends JpaRepository<DFSFileInfo, Long> {
}
