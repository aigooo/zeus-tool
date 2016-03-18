package com.zeus.tool.cogen.example;

import java.util.Date;
import java.util.List;

import com.zeus.persist.annotation.Column;
import com.zeus.persist.annotation.FK;
import com.zeus.persist.annotation.FK.LazyType;
import com.zeus.persist.annotation.FK.RefType;
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
@Table(name="AMS_SOURCE_PLATFORM")
@MarkDelete
public class Platform {

    @SearchElement(multi=true,updn=true)
    @Column(name="pf_id",type = "number(7)",comment = "id")
    @PK(type = "native",params = "sequence#SEQ_Platform")
    private Long id;

    @SearchElement(like = true,filter=true)
    @Column(name="name2",type = "varchar(20)",comment = "平台名称")
    @ViewElement(label = "平台名称",title = "平台名称",viewType= ViewElement.ViewType.colorpicker,name=true)
    @InputElement(required=true,minLength=10,maxLength=50)
    String name1;
    
    @SearchElement(like = true)
    @Column(name="serverIP",type = "varchar(50)",comment = "服务器IP")
    @ViewElement(label = "服务器IP",title = "服务器IP",viewType= ViewElement.ViewType.input_text)
    @InputElement(required=true,minLength=10,maxLength=50)
    String serverIP;
    
    @SearchElement(like = true)
    @Column(name="name",type = "varchar(20)",comment = "平台名称")
    @ViewElement(label = "平台名称",title = "平台名称",viewType= ViewElement.ViewType.timepicker,name=true)
    @InputElement(required=true,minLength=10,maxLength=50)
    String name;

    @Column(name="serverPort",type = "varchar(6)",comment = "服务器端口")
    @ViewElement(label = "服务器端口",title = "服务器端口",viewType= ViewElement.ViewType.input_text)
    @InputElement(required=true,vType=InputElement.VType.NUMBER)
    @SearchElement(updn = true,filter=true)
    Integer serverPort;
    
    @SearchElement(updn = true,filter=true)
    @Column(name="createDate",type = "date",comment = "创建时间")
    @ViewElement(label = "创建时间",title = "创建时间",viewType= ViewElement.ViewType.datepicker,inlist=false)
    @InputElement
    Date createDate;

    @Column(name="icon",type = "varchar(20)",comment = "图标")
    @ViewElement(label = "图标",title = "图标",viewType= ViewElement.ViewType.input_text,inlist=false)
    String icon;
    
    @Column(name="password",type = "varchar(64)",comment = "密码")
    @ViewElement(label = "密码",title = "密码",viewType= ViewElement.ViewType.password,inlist=false)
    @InputElement(required=true)
    Integer password;

    @Column(name="indexUrl",type = "varchar(200)",comment = "主页")
    @ViewElement(label = "主页",title = "主页",viewType= ViewElement.ViewType.input_text,inlist=false)
    @InputElement(vType=InputElement.VType.URL, required = false)
    String indexUrl;
    
    @Column(name="indexUrl2",type = "varchar(200)",comment = "主页")
    @ViewElement(label = "主页",title = "主页",viewType= ViewElement.ViewType.textarea,inlist=false)
    @InputElement(vType=InputElement.VType.URL, required = false)
    String indexUrl2;

    @Column(name="isEnable",type = "number(1)",comment = "是否可用")
    @ViewElement(label = "是否可用",title = "是否可用",viewType= ViewElement.ViewType.input_checkbox,valuesForm=ViewElement.ValuesForm.MAP,valuesGetter="{1:'正常',0:'禁用'}")
    @InputElement(required=true)
    @SearchElement(filter=true)
    Boolean isEnable;
    
    @Column(name="isEnable2",type = "number(1)",comment = "是否可用")
    @ViewElement(label = "是否可用",title = "是否可用",viewType= ViewElement.ViewType.input_radio,valuesForm=ViewElement.ValuesForm.MAP,valuesGetter="{1:'正常',0:'禁用'}")
    @InputElement(required=true)
    Boolean isEnable2;
    
    @Column(name="pf_id",type = "number(1)")
    @FK(refColumn="pf_id",refType=RefType.one_to_many,lazy=LazyType.TRUE)
    @RefElement(cascadeProperty="name",label="菜单",refEName="",toManyInverse=true)
    List<Menu> menus;
}
