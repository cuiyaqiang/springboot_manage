package com.guye.sun.managent.pojo.dto;


import com.guye.sun.managent.pojo.po.Operate;

/**
 * @author Levin
 */
public class OperateDto extends Operate {

    private static final long serialVersionUID = -3513365887221708852L;

    /**
     * 菜单名称
     */
    private String menuName;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }


}
