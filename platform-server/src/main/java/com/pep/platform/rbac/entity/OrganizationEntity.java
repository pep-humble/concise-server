package com.pep.platform.rbac.entity;

import com.pep.common.persistent.entity.BaseEntity;
import com.pep.platform.tree.entity.BaseTreeIdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 组织机构实体
 *
 * @author gang.liu
 */
@Data
@Entity
@Table(name = "CP_RBAC_Organization")
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OrganizationEntity extends BaseEntity<OrganizationEntity> implements BaseTreeIdEntity<OrganizationEntity> {

    /**
     * 树形结构编号
     */
    @Column(name = "TreeID")
    private String treeId;

    /**
     * 树形结构父类编号
     */
    @Column(name = "ParentTreeID")
    private String parentTreeId;

    /**
     * 组织机构名称
     */
    @Column(name = "OrganizationName")
    private String organizationName;

    /**
     * 组织节点顺序
     */
    @Column(name = "`Index`")
    private int index;

    /**
     * 是否是叶子节点
     */
    @Column(name = "Leaf")
    private boolean leaf;

    /**
     * 额外信息
     */
    @Column(name = "Additional")
    private String additional;

    /**
     * 返回自身
     *
     * @return 自身
     */
    @Override
    public OrganizationEntity getSelf() {
        return this;
    }
}
