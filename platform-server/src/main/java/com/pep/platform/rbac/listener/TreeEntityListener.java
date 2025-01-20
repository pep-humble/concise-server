package com.pep.platform.rbac.listener;

import com.pep.platform.tree.entity.BaseTreeIdEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.PrePersist;
import java.util.Optional;

/**
 * 树形编号生成器,适配jpa持久监听器
 *
 * @author gang.liu
 */
@Slf4j
public class TreeEntityListener {

    @PrePersist
    public void prePersistTreeId(BaseTreeIdEntity<?> employeeEntity) {
        Optional
                .ofNullable(employeeEntity)
                .ifPresent(entity -> {
                    String treeId = entity.getTreeId();
                    if (StringUtils.isBlank(treeId)) {
                        // 生成一个新的树形id
                        entity.setTreeId("0001");
                    }
                });
    }
}
