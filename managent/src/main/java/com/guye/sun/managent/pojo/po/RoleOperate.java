package com.guye.sun.managent.pojo.po;

import com.guye.sun.managent.pojo.RecordEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sys_role_operate")
public class RoleOperate extends RecordEntity {
    /**
     * 角色ID
     */
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 操作表ID
     */
    @Id
    @Column(name = "operate_id")
    private Integer operateId;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取操作表ID
     *
     * @return operate_id - 操作表ID
     */
    public Integer getOperateId() {
        return operateId;
    }

    /**
     * 设置操作表ID
     *
     * @param operateId 操作表ID
     */
    public void setOperateId(Integer operateId) {
        this.operateId = operateId;
    }

    /**
     * 获取创建时间
     *
     * @return gmt_create - 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     *
     * @param gmtCreate 创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取最后修改时间
     *
     * @return gmt_modified - 最后修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置最后修改时间
     *
     * @param gmtModified 最后修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public RoleOperate(){}
    public RoleOperate(Integer roleId){
        super();
        this.roleId = roleId;
    }
    public RoleOperate(Integer roleId, Integer operateId){
        super();
        this.roleId = roleId;
        this.operateId = operateId;
    }
}