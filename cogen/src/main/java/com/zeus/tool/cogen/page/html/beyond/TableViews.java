package com.zeus.tool.cogen.page.html.beyond;

/**
 * 针对列表页面和显示的页面的字段展示
 * @author xiazs
 */
public interface TableViews {

	public String TH = "<!--th-->";
	
	public String th = "\t\t\t\t\t\t\t\t\t<th class='table-title'>#fieldLabel#</th>\n" + TH;

	public String TD_JS = "/*td_js*/";
	
	public String REF_TD_JS = "";
	
	public String TR_JS = "/*tr_js*/";

	public String tr_js = "\t\t\t\t\tvar tr = \"<tr>\"+\n" + 
							TD_JS + "\n" +
							"\t\t\t\t\t\t\"<td><a class='btn btn-success btn-sm' href='javascript:redirect(\\\"#viewPageUrl#\\\",{\\\"#instanceName#.id\\\":\"+list[i].id+\"})'>查看</a>\"+\n"+ 
							"\t\t\t\t\t\t\"&nbsp;&nbsp;<a class='btn btn-info btn-sm' href='javascript:redirect(\\\"#editPageUrl#\\\",{\\\"#instanceName#.id\\\":\" + list[i].id+\"})'>编辑</a>\"+\n"+ 
							"\t\t\t\t\t\t\"&nbsp;&nbsp;<a class='btn btn-danger btn-sm' href='javascript:remove({\\\"#instanceName#.id\\\":\" + list[i].id+\"})'>删除</a></td>\"+\n"+  
							"\t\t\t\t\t\t\"</tr>\";\n" + 
							"\t\t\t\t\t$(\"##instanceName#-data\").append(tr);";
	
	//针对编辑页面，查看页面显示 to many字段的情况
	public String ref_list_tr_js = "\t\t\t\t\tvar tr = \"<tr>\"+\n" + 
			TD_JS + "\n" +
			"\t\t\t\t\t\t\"<td><a class='btn btn-info btn-sm' href='javascript:removeRel(\\\"#editPageUrl#?#instanceName#.id=\"+list[i].id+\"\\\")'>仅删除关系</a>\"+\n"+ 
			"\t\t\t\t\t\t\"&nbsp;&nbsp;<a class='btn btn-danger btn-sm' href='javascript:remove({\\\"#instanceName#.id\\\":\" + list[i].id+\"})'>删除</a></td>\"+\n"+  
			"\t\t\t\t\t\t\"</tr>\";\n" + 
			"\t\t\t\t\t$(\"##instanceName#-data\").append(tr);";
	
	public String choosen_tr_js = "\t\t\t\t\tvar tr = \"<tr>\"+\n" + 
							"\t\t\t\t\t\t\"<td style='text-align:center'>\"+\n" + 
							"\t\t\t\t\t\t\"<label class='single-ref' style='display:none'><input name='choosen' class='colored-blue row-check' type='radio' value='\"+list[i].id+\"$$\"+list[i].#nameProperty#+\"'><span class='text'>&nbsp;</span></label>\"+\n" +
							"\t\t\t\t\t\t\"<label class='multi-ref' style='display:none'><input name='choosen' class='colored-blue row-check' type='checkbox' value='\"+list[i].id+\"$$\"+list[i].#nameProperty#+\"'><span class='text'>&nbsp;</span></label>\"+\n" + 
							"\t\t\t\t\t\t\"</td>\"+\n" +
							TD_JS + 
							"\n\t\t\t\t\t\t\"<td><a class='btn btn-success btn-sm' href='javascript:redirect(\\\"#viewPageUrl#\\\",{\\\"#instanceName#.id\\\":\"+list[i].id+\"})'>查看</a>\"+\n"
							+ "\t\t\t\t\t\t\"</tr>\";\n" + 
							"\t\t\t\t\t$(\"##instanceName#-data\").append(tr);";
	
	public String td_js = "\t\t\t\t\t\t\"<td>\"+list[i].#fieldName#+\"</td>\"+\n" + TD_JS;
	
	//针对to one 应用字段在列表中的展示，展示对象名称，并附加到对象查看页面的连接
	public String ref_to_one_td_js = "\t\t\t\t\t\t\"<td><a href='javaScript:redirect(\\\"#refFieldViewPageUrl#\\\",{\\\"#fieldName#.id\\\":\"+(list[i].#fieldName#!=undefined&&list[i].#fieldName#!=null?list[i].#fieldName#.id:'')+\"})'>\"+(list[i].#fieldName#!=undefined&&list[i].#fieldName#!=null?list[i].#fieldName#.#cascadeProperty#:'')+\"</a></td>\"+\n" + TD_JS;
	
	//针对to many 应用字段在列表中的展示，展示两个按钮
	//TODO 管理后续不做跳转处理
	public String ref_to_many_td_js = 
						"\t\t\t\t\t\t\"<td class='blue'>\"+\n"+ 
						"\t\t\t\t\t\t\"<a class='btn ref-link btn-sm' href='javaScript:redirect(\\\"#refFieldListPageUrl#\\\",{\\\"#fieldInstanceName#.#instanceName#.id\\\":\"+list[i].id+\"})'><i class='fa fa-list-ol'></i>管理</a>&nbsp;&nbsp;&nbsp;\"+\n"+ 
						"\t\t\t\t\t\t\"<a class='btn ref-link btn-sm' href='javaScript:redirect(\\\"#refFieldAddPageUrl#\\\",{\\\"#fieldInstanceName#.#instanceName#.id\\\":\"+list[i].id+\"})'><i class='fa fa-external-link'></i>新增</a>\"+\n"+ 
						"\t\t\t\t\t\t\"</td>\"+\n" + TD_JS;
	
	public String INPUT_4_FILTER = "<!--input_4_filter-->";
	
	public String BTN_4_FILTER = "<!--btn_4_filter-->";
	
	public String btn_4_filter = "\t\t\t\t\t\t\t<td rowspan='100'>\n" + 
									"\t\t\t\t\t\t\t	<div class=\"buttons-preview\">\n" + 
						            	"\t\t\t\t\t\t\t		<a class=\"btn btn-success\" onclick='filter(1);'>过滤</a>\n" +
						                "\t\t\t\t\t\t\t		<a class=\"btn btn-info\" onclick='redirect(\"#addPageUrl#\");'>新建</a>\n" +
						            "\t\t\t\t\t\t\t	</div>\n" +
								"\t\t\t\t\t\t\t</td>";
	
	public String TR_4_FILTER = "<!--tr_4_filter-->";
	public String tr_4_filter = "\t\t\t\t\t\t\t<tr>\n"  +
								"\t\t\t\t\t\t\t	<td style='width: 85%'>\n"  +
									"\t\t\t\t\t\t\t	<div class='form-group col-sm-6'>\n" + INPUT_4_FILTER +
									"\t\t\t\t\t\t\t	</div>\n"+ 
									"\t\t\t\t\t\t\t	<div class='form-group col-sm-6'>\n" + INPUT_4_FILTER +
									"\t\t\t\t\t\t\t	</div>\n"+
									"\t\t\t\t\t\t\t	</td>\n"  +
									BTN_4_FILTER + 
								"\n\t\t\t\t\t\t\t</tr>\n" + TR_4_FILTER;
	
	public String TD_VIEW = "<!--td_view-->";
	public String td_view = "<div class='form-group'>"  +
							"<label for='#fieldName#' class='col-sm-3 control-label no-padding-right label-title'>#fieldLabel#</label> "  +
							"<div class='col-sm-9'> "  +
								"<span id='#fieldId#' class='col-sm-10 label-content'></span>"  +
							"</div>"  +
							"</div>\n" + TD_VIEW;
	
	public String TD_VIEW_JS = "<!--td_view_js-->";
	public String td_view_js = "\t\t\t\t\t\t$('##fieldId#').text(data.#fieldName#!=undefined?data.#fieldName#:'');\n" + TD_VIEW_JS;
	public String td_view_ref_js = "\t\t\t\t\t\t$('##fieldId#').text(data.#fieldName#!=undefined?data.#fieldName#.#cascadeProperty#:'');\n" + TD_VIEW_JS;
	public String td_view_ref_multi_js = 
			"\t\t\t\t\t\tvar #fieldId#_text;\n" + 
			"\t\t\t\t\t\tfor(var i=0;i<data.#fieldName#.length;i++)"+
			"\t\t\t\t\t\t	#fieldId#_text = #fieldId#_text + data.#fieldName#[i].#cascadeProperty# + '     ';\n" + 
			"\t\t\t\t\t\t$('##fieldId#').text(#fieldId#_text);\n" + TD_VIEW_JS;
}
