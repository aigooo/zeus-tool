package com.zeus.tool.cogen.page.html.beyond;

import java.util.HashMap;
import java.util.Map;

public interface BeyondAdminProperties extends FormInputs{

	public Map<String,String> elements_input = new HashMap<>();  //input块
	
	public Map<String,String> elements_ref_js = new HashMap<>(); //javaScript文件引入
	
	public Map<String,String> elements_build_js = new HashMap<>();   //input控件显示初始化javaScript
	
	public Map<String,String> elements_init_js = new HashMap<>();  //input控件数据初始化javaScript
	
	public Map<String,String> elements_data_init_js = new HashMap<>();  //input控件数据初始化javaScript
	
	public Map<String,String> elements_ref_model = new HashMap<>();  //input块引用块
	
}
