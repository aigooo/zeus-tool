package com.zeus.tool.cogen.page.html;

public interface UrlProperties {
	
	public String ADD_URL = "#addUrl#";
	public String addUrl = "/#instanceName#/add";
	
	//指向对象Add页面的连接
	public String ADD_PAGE_URL = "#addPageUrl#";
	public String addPageUrl = "/page/" + "#module#/" + "#instanceName#-add.html";
	
	public String EDIT_URL = "#editUrl#";
	public String editUrl = "/#instanceName#/edit";
	
	//指向对象Edit页面的连接
	public String EDIT_PAGE_URL = "#editPageUrl#";
	public String editPageUrl = "/page/" + "#module#/" + "#instanceName#-edit.html";
	
	public String REMOVE_URL = "#removeUrl#";
	public String removeUrl = "/#instanceName#/remove";
	
	//指向对象Page页面的连接
	public String PAGE_URL = "#pageUrl#";
	public String pageUrl = "/#instanceName#/page";
	
	public String LIST_URL = "#listUrl#";
	public String listUrl = "/#instanceName#/list";
	
	//指向对象List页面的连接
	public String LIST_PAGE_URL = "#listPageUrl#";
	public String listPageUrl = "/page/" + "#module#/" + "#instanceName#-list.html";
	
	public String VIEW_URL = "#viewUrl#";
	public String viewUrl = "/#instanceName#/view";
	
	//指向对象view页面的连接
	public String VIEW_PAGE_URL = "#viewPageUrl#";
	public String viewPageUrl = "/page/" + "#module#/" + "#instanceName#-view.html";
	
	//指向对象中引用字段内部引用list页面的连接
	public String REF_FIELD_INREF_PAGE_URL = "#refFieldInListPageUrl#";
	public String refFieldInListPageUrl = "/page/" + "#fieldModule#/" + "#fieldInstanceName#-inref.html";
	
//	//指向对象中引用字段内部引用list的连接
//	public String REF_FIELD_LIST_URL = "#refFieldListUrl#";
//	public String fieldListUrl = "/#instanceName#/flist";
	
	//针对Select_Ref时的引用用数据获取
	public String REF_FIELD_LIST_URL = "#refFieldListUrl#";
	public String refFieldListUrl = "/#fieldInstanceName#/list";
	
	//针对分页取数据时的引用用数据获取
	public String REF_FIELD_REF_PAGE_URL = "#refFieldRefPageUrl#";
	public String refFieldRefPageUrl = "/page/" + "#fieldModule#/" + "#fieldInstanceName#-ref.html";
	
	//指向对象中引用字段list页面的连接
	public String REF_FIELD_LIST_PAGE_URL = "#refFieldListPageUrl#";
	public String refFieldListPageUrl = "/page/" + "#fieldModule#/" + "#fieldInstanceName#-list.html";
	
	//指向对象中引用字段add页面的连接
	public String REF_FIELD_ADD_PAGE_URL = "#refFieldAddPageUrl#";
	public String refFieldAddPageUrl = "/page/" + "#fieldModule#/" + "#fieldInstanceName#-add.html";
	
	public String REF_FIELD_VIEW_PAGE_URL = "#refFieldViewPageUrl#";
	public String refFieldViewPageUrl = "/page/" + "#fieldModule#/" + "#fieldInstanceName#-view.html";
	
	//请求数据字段翻译的连接
	public String DICT_URL = "#dictUrl#";
	public String dictUrl = "/dictionaryItem/list";

}
