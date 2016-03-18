package com.zeus.tool.cogen.page.html;

import com.zeus.tool.cogen.annotation.ViewElement.ViewType;
import com.zeus.tool.cogen.design.DesignField;
import com.zeus.tool.cogen.page.html.beyond.BeyondAdminProperties;
import com.zeus.tool.cogen.page.html.beyond.InitProperties;
import com.zeus.tool.cogen.page.html.beyond.TableViews;

/**
 * 负责生成各种字段的页面显示、脚本等，具体排版由各个页面自由排版
 * 
 * @author xiazs
 */
public class GenFieldHelper implements BeyondAdminProperties, UrlProperties , TableViews{

	static {
		InitProperties.init();
	}
	
	/**
	 * 搜索字段的排版
	 * @param all
	 * @param dField
	 * @param element
	 * @param elementRefJs
	 * @param elementBuildJs
	 * @param elementInitJs
	 * @return
	 */
	public static String addOneElement2Filter(String all, DesignField dField, String element, 
			String elementRefJs, String elementBuildJs,String elementInitJs) {
		if(element!=null){
			if(!all.contains(INPUT_4_FILTER)) all = all.replace(TR_4_FILTER, tr_4_filter);
			if(!all.contains("rowspan='100'")) all = all.replace(BTN_4_FILTER, btn_4_filter);
			
			all = all.replaceFirst(INPUT_4_FILTER, replaceElementHolder(element,dField));
			if(elementRefJs!=null)
				all = all.replace(REF_JS, replaceElementHolder(elementRefJs,dField));
			if(elementBuildJs!=null)
				all = all.replace(BUILD_JS, replaceElementHolder(elementBuildJs,dField));
			if(elementInitJs!=null)
				all = all.replace(INIT_JS, replaceElementHolder(elementInitJs,dField));
		}
		return all;
	}
	
	/**
	 * 增加显示列表数据，TODO 后续添加字段的翻译
	 * @param all
	 * @param dField
	 * @param td
	 * @param tdjs
	 * @return
	 */
	public static String addOneListElement(String all, DesignField dField, String th,String tdjs){
		return all.replace(TH, replaceElementHolder(th,dField)).replace(TD_JS, replaceElementHolder(tdjs,dField));
	}
	
	public static String addOneViewElement(String all, DesignField dField, String tdView,String tdViewJs){
		return all.replace(TD_VIEW, replaceElementHolder(tdView,dField)).replace(TD_VIEW_JS, replaceElementHolder(tdViewJs,dField));
	}

	/**
	 * 针对普通字段的输入的排版
	 * @param all
	 * @param dField
	 * @param element
	 * @param elementRefJs
	 * @param elementBuildJs
	 * @param elementInitJs
	 * @param elementDataInitJs
	 * @return
	 */
	public static String addOneElement(String all, DesignField dField, String element, String elementRefJs,
			String elementBuildJs, String elementInitJs, String elementDataInitJs) {
		if (element != null) {
			if (!all.contains(ONE_ITEM))  all = all.replace(FORM_GROUP, form_group);
			
			all = all.replaceFirst(ONE_ITEM, replaceElementHolder(element, dField));
			if (elementRefJs != null && !all.contains(elementRefJs))
				all = all.replace(REF_JS, replaceElementHolder(elementRefJs, dField));
			if (elementBuildJs != null)
				all = all.replace(BUILD_JS, replaceElementHolder(elementBuildJs, dField));
			if (elementInitJs != null)
				all = all.replace(INIT_JS, replaceElementHolder(elementInitJs, dField));
			if (elementDataInitJs != null)
				all = all.replace(DATA_INIT_JS, replaceElementHolder(elementDataInitJs, dField));
		}
		return all;
	}
	
	/**
	 * 针对弹出类引用字段的的输入的排版
	 * @param all
	 * @param dField
	 * @param element
	 * @param elementRefJs
	 * @param elementModel
	 * @param elementBuildJs
	 * @param elementDataInitJs
	 * @return
	 */
	public static String addOneRefElement(String all, DesignField dField,String element,String elementRefJs,
			String elementModel,String elementBuildJs,String elementDataInitJs){
		if (element != null) {
			all = all.replaceFirst(ONE_ITEM, replaceElementHolder(element, dField));
			
			if (elementRefJs != null && !all.contains(elementRefJs))
				all = all.replace(REF_JS, replaceElementHolder(elementRefJs, dField));
			if (elementModel!=null)
				all = all.replace(INPUT_REF_MODEL, replaceElementHolder(elementModel, dField));
			if (elementBuildJs != null)
				all = all.replace(BUILD_JS, replaceElementHolder(elementBuildJs, dField));
			if (elementDataInitJs != null)
				all = all.replace(DATA_INIT_JS, replaceElementHolder(elementDataInitJs, dField));
		}
		return all;
	}

	/**
	 * 生成输入字段，包含js文件的引入，输入框的初始化，输入框可选择的初始化
	 * @param dfield
	 * @return
	 */
	public static String gen4InputField(String all, DesignField dField) {
		if (dField.inputProperties != null) {
			dField.fieldId = "#instanceName#_" + dField.fieldName;
			if (dField.viewProperties != null) {
				String fieldViewType = dField.viewProperties.viewType.toString();
				String fieldValueFrom = String.valueOf(dField.viewProperties.valuesForm).toLowerCase();
				all = addOneElement(all, dField, elements_input.get(fieldViewType), elements_ref_js.get(fieldViewType),
						elements_build_js.get(fieldViewType),
						elements_init_js.get(fieldViewType + "_" + fieldValueFrom) != null
								? elements_init_js.get(fieldViewType + "_" + fieldValueFrom)
								: elements_init_js.get(fieldViewType),null);
			} else if(dField.refProperties != null){
				if (dField.refProperties.toManyInverse) {   // 关系的创建不由该实体管理，啥也不做
				} else {
					if (dField.refProperties.select) {  // 区分是外部弹开还是内部list
						String fieldViewType = dField.refProperties.multi?"select_multi_ref":"select_ref";
						all = addOneElement(all, dField, elements_input.get(fieldViewType), elements_ref_js.get(fieldViewType),
								elements_build_js.get(fieldViewType),elements_init_js.get(fieldViewType),null);
					} else {
						String fieldViewType = dField.refProperties.multi?"input_ref_multi":"input_ref";
						all = addOneRefElement(all, dField, elements_input.get(fieldViewType),elements_ref_js.get(fieldViewType),
								elements_ref_model.get(fieldViewType),elements_build_js.get(fieldViewType),null);
					}
				}
			} else if(dField.remoteProperties!=null){ //TODO 远程字段的四种情况
				String fieldViewType = dField.remoteProperties.multi?"input_ref_multi":"input_ref";
				all = addOneRefElement(all, dField, elements_input.get(fieldViewType),elements_ref_js.get(fieldViewType),
						elements_ref_model.get(fieldViewType),elements_build_js.get(fieldViewType),null);
			}
		}
		return all;
	}

	/**
	 * 生成编辑字段，在生成输入字段的基础上，增加字段已有值的初始化JS脚本
	 * @param dfield
	 * @return
	 */
	public static String gen4EditField(String all,DesignField dField) {
		if (dField.inputProperties != null) {
			dField.fieldId = "#instanceName#_" + dField.fieldName;
			if (dField.viewProperties != null) {
				String fieldViewType = dField.viewProperties.viewType.toString();
				String fieldValueFrom = String.valueOf(dField.viewProperties.valuesForm).toLowerCase();
				all = addOneElement(all, dField, elements_input.get(fieldViewType), elements_ref_js.get(fieldViewType),
						elements_build_js.get(fieldViewType),
						elements_init_js.get(fieldViewType + "_" + fieldValueFrom) != null
								? elements_init_js.get(fieldViewType + "_" + fieldValueFrom)
								: elements_init_js.get(fieldViewType),elements_data_init_js.get(fieldViewType));
			} else if(dField.refProperties != null){
				if (dField.refProperties.toManyInverse) {   // 关系的创建不由该实体管理，啥也不做
				} else {
					if (dField.refProperties.select) {  // 区分是外部弹开还是内部list
						String fieldViewType = dField.refProperties.multi?"select_multi_ref":"select_ref";
						all = addOneElement(all, dField, elements_input.get(fieldViewType), elements_ref_js.get(fieldViewType),
								elements_build_js.get(fieldViewType),elements_init_js.get(fieldViewType),elements_data_init_js.get(fieldViewType));
					} else {
						String fieldViewType = dField.refProperties.multi?"input_ref_multi":"input_ref";
						all = addOneRefElement(all, dField, elements_input.get(fieldViewType),elements_ref_js.get(fieldViewType),
								elements_ref_model.get(fieldViewType),elements_build_js.get(fieldViewType),elements_data_init_js.get(fieldViewType));
					}
				}
			} else if(dField.remoteProperties!=null){ //TODO 远程字段的四种情况
				String fieldViewType = dField.remoteProperties.multi?"input_ref_multi":"input_ref";
				all = addOneRefElement(all, dField, elements_input.get(fieldViewType),elements_ref_js.get(fieldViewType),
						elements_ref_model.get(fieldViewType),elements_build_js.get(fieldViewType),elements_data_init_js.get(fieldViewType));
			}
		}else if(dField.inputProperties==null&&dField.refProperties!=null){
			if(dField.refProperties.toManyInverse){
				all = all.replaceAll(REF_ELEMENT_LIST, replaceElementHolder(ref_element_list,dField))
						.replace(DATA_INIT_JS, replaceElementHolder(ref_element_data_js,dField));
			}
		}
		return all;
	}

	/**
	 * 生成搜索框的输入字段，包含js文件的引入，输入框的初始话，输入框可选择的初始化
	 * 仅当搜索字段为updn时，与输入字段有区别
	 * @param dfield
	 * @return
	 */
	public static String gen4SearchField(String all,DesignField dField) {
		if(dField.searchProperties!=null&&dField.searchProperties.filter){
			dField.fieldId = "#instanceName#_" + dField.fieldName;
			if(!dField.searchProperties.updn){
				if (dField.viewProperties != null) {
					String fieldViewType = dField.viewProperties.viewType.toString();
					String fieldValueFrom = String.valueOf(dField.viewProperties.valuesForm).toLowerCase();
					all = addOneElement2Filter(all, dField, elements_input.get(fieldViewType), elements_ref_js.get(fieldViewType),
							elements_build_js.get(fieldViewType),
							elements_init_js.get(fieldViewType + "_" + fieldValueFrom) != null
									? elements_init_js.get(fieldViewType + "_" + fieldValueFrom)
									: elements_init_js.get(fieldViewType));
				} else if(dField.refProperties != null){
					if (dField.refProperties.toManyInverse) {   // 关系的创建不由该实体管理，啥也不做
					} else {
						if (dField.refProperties.select) {  // 区分是外部弹开还是内部list
							String fieldViewType = dField.refProperties.multi?"select_multi_ref":"select_ref";
							all = addOneElement2Filter(all, dField, elements_input.get(fieldViewType), elements_ref_js.get(fieldViewType),
									elements_build_js.get(fieldViewType),elements_init_js.get(fieldViewType));
						} else {
							String fieldViewType = dField.refProperties.multi?"input_ref_multi":"input_ref";
							all = addOneElement2Filter(all, dField, elements_input.get(fieldViewType),elements_ref_js.get(fieldViewType),
									elements_ref_model.get(fieldViewType),elements_build_js.get(fieldViewType));
						}
					}
				} else if(dField.remoteProperties!=null){ //TODO 远程字段的四种情况
					String fieldViewType = dField.refProperties.multi?"input_ref_multi":"input_ref";
					all = addOneElement2Filter(all, dField, elements_input.get(fieldViewType),elements_ref_js.get(fieldViewType),
							elements_ref_model.get(fieldViewType),elements_build_js.get(fieldViewType));
				}
			} else{  //TODO 搜索范围选择框的生成 ,仅对input_text与datepicker有效
				if (dField.viewProperties != null &&(dField.viewProperties.viewType==ViewType.input_text
						||dField.viewProperties.viewType==ViewType.datepicker)) {
					String fieldViewType = dField.viewProperties.viewType.toString();
					String fieldValueFrom = String.valueOf(dField.viewProperties.valuesForm).toLowerCase();
					all = addOneElement2Filter(all, dField, elements_input.get(fieldViewType), elements_ref_js.get(fieldViewType),
							elements_build_js.get(fieldViewType),
							elements_init_js.get(fieldViewType + "_" + fieldValueFrom) != null
									? elements_init_js.get(fieldViewType + "_" + fieldValueFrom)
									: elements_init_js.get(fieldViewType));
				} 
			}
		}
		return all;
	}

	/**
	 * 生成列表字段，包含列表字段的翻译工作
	 * @param dfield
	 * @return
	 */
	public static String gen4ListField(String all,DesignField dField) {
		if (dField.viewProperties != null && dField.viewProperties.inlist) {
			all = addOneListElement(all,dField,th,td_js);
		}else if (dField.refProperties!=null){
			if(dField.columnProperties.refType.getValue().contains("-to-many")){
				all = addOneListElement(all,dField,th,ref_to_many_td_js);
			}else{
				all = addOneListElement(all,dField,th,ref_to_one_td_js);
			}
		}
		return all;
	}
	
	/**
	 * 生成显示字段
	 * 
	 * @param dfield
	 * @return
	 */
	public static String gen4ViewField(String all,DesignField dField) {
		if (dField.viewProperties != null) {
			all = addOneViewElement(all,dField,td_view,td_view_js);
		}else if (dField.refProperties!=null){
		}
		return all;
	}

	/**
	 * 替换各个字段里的占位符
	 * @param elementHolder
	 * @param dField
	 * @return
	 */
	private static String replaceElementHolder(String elementHolder, DesignField dField) {
		return replaceFieldUrl(elementHolder,dField).replaceAll("#fieldLabel#", dField.label==null?"":dField.label)
				.replaceAll("#fieldTitle#", dField.title==null?"":dField.title)
				.replaceAll("#fieldHolder#", dField.title==null?"":dField.title)
				.replaceAll("#fieldName#", dField.fieldName==null?"":dField.fieldName)
				.replaceAll("#faIcon#", dField.faIcon==null?"":dField.faIcon)
				.replaceAll("#faColor#", dField.faColor==null?"":dField.faColor)
				.replaceAll("#fieldId#", dField.fieldId==null?"":dField.fieldId)
				.replaceAll("#fieldModule#", dField.moduleName)
				.replaceAll("#fieldInstanceName#", dField.fieldInstanceName)
				.replaceAll("#fieldFormate#", dField.fieldFormate==null?"yyyy-mm-dd":dField.fieldFormate)
				.replaceAll("#valueGetter#", dField.viewProperties!=null?dField.viewProperties.valuesGetter:"")
				.replaceAll("#refEName#",(dField.refProperties!=null&&dField.refProperties.refEName!=null)?dField.refProperties.refEName:"")
				.replaceAll("#cascadeProperty#", (dField.refProperties!=null&&dField.refProperties.cascadeProperty!=null)?dField.refProperties.cascadeProperty:"")
				.replaceAll("#fieldDefaultValue#", dField.inputProperties!=null?dField.inputProperties.defaultValue:"");
	}
	
	public static String replaceFieldUrl(String elementHolder, DesignField dField){
		return elementHolder.replaceAll(REF_FIELD_INREF_PAGE_URL, refFieldInListPageUrl)
							.replaceAll(REF_FIELD_LIST_URL, refFieldListUrl)
							.replaceAll(REF_FIELD_REF_PAGE_URL, refFieldRefPageUrl)
							.replaceAll(REF_FIELD_LIST_PAGE_URL, refFieldListPageUrl)
							.replaceAll(REF_FIELD_ADD_PAGE_URL, refFieldAddPageUrl)
							.replaceAll(REF_FIELD_VIEW_PAGE_URL, refFieldViewPageUrl);
	}
}
