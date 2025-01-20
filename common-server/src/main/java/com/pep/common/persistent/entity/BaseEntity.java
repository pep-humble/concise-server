package com.pep.common.persistent.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 表基础类
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
@MappedSuperclass
public abstract class BaseEntity<S extends BaseEntity<S>> {

    /**
     * 自增编号
     * <p>
     * guid就是mysql数据库默认的uuid()
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID_Generator")
    @GenericGenerator(name = "UUID_Generator", strategy = "uuid2")
    private String id;

    /**
     * 自增序号
     */
    @Column(name = "Sequence")
    private Long sequence;

    /**
     * 创建者编号
     */
    @Column(name = "Creator")
    private String creator;

    /**
     * 首次创建时间
     */
    @Column(name = "CreateTime")
    private Long createTime;

    /**
     * 最后修改者
     */
    @Column(name = "Modifier")
    private String modifier;

    /**
     * 最后修改时间
     */
    @Column(name = "ModifyTime")
    private Long modifyTime;

    /**
     * 是否逻辑删除
     */
    @Column(name = "Deleted")
    private boolean deleted;

    /**
     * 设置唯一编号
     *
     * @param id 唯一编号
     * @return 返回自身
     */
    public S setId(String id) {
        this.id = id;
        return this.getSelf();
    }

    /**
     * 设置自增序号
     *
     * @param sequence 自增序号
     * @return 返回自身
     */
    public S setSequence(Long sequence) {
        this.sequence = sequence;
        return this.getSelf();
    }

    /**
     * 设置首次创建者编号
     *
     * @param creator 首次创建者编号
     * @return 返回自身
     */
    public S setCreator(String creator) {
        this.creator = creator;
        return this.getSelf();
    }

    /**
     * 设置首次创建时间
     *
     * @param createTime 首次创建时间
     * @return 返回自身
     */
    public S setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this.getSelf();
    }

    /**
     * 设置最后修改人编号
     *
     * @param modifier 最后修改人编号
     * @return 返回自身
     */
    public S setModifier(String modifier) {
        this.modifier = modifier;
        return this.getSelf();
    }

    /**
     * 设置最后修改时间
     *
     * @param modifyTime 最后修改时间
     * @return 返回自身
     */
    public S setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
        return this.getSelf();
    }

    /**
     * 返回自身
     *
     * @return 自身
     */
    public abstract S getSelf();

}
