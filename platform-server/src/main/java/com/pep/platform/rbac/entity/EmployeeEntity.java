package com.pep.platform.rbac.entity;

import com.pep.common.persistent.entity.BaseEntity;
import com.pep.platform.rbac.enums.GenderEnum;
import com.pep.platform.rbac.listener.TreeEntityListener;
import com.pep.platform.tree.BaseTreeIdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * 员工实体
 *
 * @author gang.liu
 */
@Data
@Entity
@Table(name = "CP_RBAC_Employee")
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = TreeEntityListener.class)
public class EmployeeEntity extends BaseEntity<EmployeeEntity> implements BaseTreeIdEntity<EmployeeEntity> {

    /**
     * 登录账户
     */
    @Column(name = "Account")
    private String account;

    /**
     * 登录密码
     */
    @Column(name = "Password")
    private String password;

    /**
     * 昵称
     */
    @Column(name = "Nickname")
    private String nickname;

    /**
     * 邮箱
     */
    @Column(name = "Email")
    private String email;

    /**
     * 手机号
     */
    @Column(name = "Phone")
    private String phone;

    /**
     * 性别
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "Gender")
    private GenderEnum gender;

    /**
     * 头像地址
     */
    @Column(name = "Avatar")
    private String avatar;

    /**
     * 树形结构编号
     */
    @Column(name = "TreeID")
    private String treeId;

    @Override
    public EmployeeEntity getSelf() {
        return this;
    }
}
