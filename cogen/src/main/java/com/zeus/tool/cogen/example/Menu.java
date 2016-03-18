package com.zeus.tool.cogen.example;

import com.zeus.persist.annotation.Column;
import com.zeus.persist.annotation.FK;
import com.zeus.persist.annotation.PK;
import com.zeus.persist.annotation.Table;
import com.zeus.tool.cogen.annotation.HibernateSupport;
import com.zeus.tool.cogen.annotation.InputElement;
import com.zeus.tool.cogen.annotation.MarkDelete;
import com.zeus.tool.cogen.annotation.RefElement;
import com.zeus.tool.cogen.annotation.SearchElement;
import com.zeus.tool.cogen.annotation.SpringSupport;
import com.zeus.tool.cogen.annotation.StrutsSupport;
import com.zeus.tool.cogen.annotation.ViewElement;

@com.zeus.tool.cogen.annotation.Package(name="com.zeus.app.ams.resource")
@SpringSupport
@HibernateSupport
@StrutsSupport
@Table(name="AMS_RESOURCE_MENU")
@MarkDelete
public class Menu {

    @Column(name="m_id",type = "number(7)",comment = "id")
    @PK(type = "identity",params = "")
    @SearchElement(updn=true,multi=true)
    Long id;
    
    @SearchElement(like = true,filter=true)
    @Column(name="name",type = "varchar(20)",comment = "菜单名称")
    @ViewElement(label = "菜单名称",title = "菜单名称",viewType= ViewElement.ViewType.input_text,name=true)
    @InputElement(required=true,minLength=10,maxLength=50)
    String name;
    
    @SearchElement(like = true,filter=true)
    @Column(name="url",type = "varchar(20)",comment = "菜单连接")
    @ViewElement(label = "菜单连接",title = "菜单连接",viewType= ViewElement.ViewType.input_text)
    @InputElement(required=true,minLength=10,maxLength=50)
    String url;
    
    @SearchElement(like = true,filter=true)
    @Column(name="type",type = "varchar(20)",comment = "菜单类型")
    @ViewElement(label = "菜单类型",title = "菜单类型",viewType= ViewElement.ViewType.input_radio,valuesForm=ViewElement.ValuesForm.MAP,valuesGetter="{1:'菜单包',0:'菜单目录'}")
    @InputElement(required=true)
    String type;
    
    @Column(name="isEnable",type = "number(1)",comment = "是否可用")
    @ViewElement(label = "是否可用",title = "是否可用",viewType= ViewElement.ViewType.select_search,valuesForm=ViewElement.ValuesForm.MAP,valuesGetter="{1:'正常',0:'禁用'}")
    @InputElement(required=true)
    Boolean isEnable;
    
    @SearchElement()
    @Column(name="parent_m_id",type = "number(7)",comment = "上级菜单")
    @InputElement(required=true)
    @FK(refType = FK.RefType.many_to_one,refColumn = "m_id")
    @RefElement(label="上级菜单",refEName="menu.parentMenu.id")
    Menu parentMenu;

    @SearchElement()
    @Column(name="pf_id",type = "number(7)",comment = "平台ID")
    @FK(refType = FK.RefType.many_to_one,refColumn = "pf_id")
    @RefElement(multi=false,title="所属平台",label="所属平台",refEName = "menu.platform.id",select=true)
    @InputElement(required=true)
    Platform platform;

}
