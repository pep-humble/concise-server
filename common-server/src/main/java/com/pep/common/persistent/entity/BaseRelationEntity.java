package com.pep.common.persistent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 关联表基础类
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BaseRelationEntity extends BaseEntity<BaseRelationEntity> {

    /**
     * 主表编号
     */
    private String masterId;

    /**
     * 从表编号
     */
    private String slaveId;

    @Override
    public BaseRelationEntity getSelf() {
        return this;
    }
}
