package com.guye.sun.managent.pojo.dto;


import com.guye.sun.managent.pojo.po.Manager;

public class ManagerDto extends Manager {

    private static final long serialVersionUID = 5314093955506271430L;

    private String roleName;
    private String roleCode;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

}
