package com.pep.platform.tree.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.util.Optional;

/**
 * 基础树形
 *
 * @author gang.liu
 */
@MappedSuperclass
public interface BaseTreeIdEntity<S extends BaseTreeIdEntity<S>> {

    /**
     * 获取树形编号
     *
     * @return 树形编号
     */
    String getTreeId();

    /**
     * 设置树形编号
     *
     * @param treeId 树形编号
     * @return 自身
     */
    S setTreeId(String treeId);

    /**
     * 返回自身
     *
     * @return 自身
     */
    S getSelf();

    /**
     * 获取树类型
     *
     * @return 树类型
     */
    default String getTreeType() {
        return Optional.ofNullable(getSelf().getClass().getAnnotation(Table.class))
                .map(Table::name)
                .orElse(StringUtils.EMPTY);
    }
}
