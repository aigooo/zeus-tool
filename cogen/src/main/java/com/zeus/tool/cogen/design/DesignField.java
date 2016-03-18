package com.zeus.tool.cogen.design;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zeus.common.util.TypeUtil;
import com.zeus.persist.annotation.Column;
import com.zeus.persist.annotation.FK;
import com.zeus.persist.annotation.FK.LazyType;
import com.zeus.persist.annotation.FK.RefType;
import com.zeus.persist.annotation.PK;
import com.zeus.tool.cogen.annotation.InputElement;
import com.zeus.tool.cogen.annotation.RefElement;
import com.zeus.tool.cogen.annotation.RemoteElement;
import com.zeus.tool.cogen.annotation.SearchElement;
import com.zeus.tool.cogen.annotation.ViewElement;

public class DesignField {
	
	public String label;
	public String title;
	public String fieldName;
	public String upFieldName;
	public String dnFieldName;
	public String fieldType;    //fieldType简写 如Long,List<Menu>
	public String fullFieldType;    //fieldType全写，如java.lang.Long,java.util.List<com.zeus.app.simp.resource.Menu>
	public String genericFieldType;   //针对Collection类泛型，取泛型内部实体最终类型
	public String designGenericFieldType;   //针对Collection类泛型，取泛型内部实体最终类型，取设计类型
	public Class<?> fieldClass;   //fieldType的类型，对于集合类型，已List,Map等表示
	public String getter;
	public String setter;
	public String upGetter;
	public String dnGetter;
	public String upSetter;
	public String dnSetter;
	public String moduleName;
	
	public ViewProperties viewProperties;
	public InputProperties inputProperties;
	public RefProperties refProperties;
	public RemoteProperties remoteProperties;
	public SearchProperties searchProperties;
	public ColumnProperties columnProperties;
	public String faIcon;
	public String faColor;
	public String fieldId;
	public String fieldFormate;
	public String fieldInstanceName;
	
	public static DesignField resolve(Field field){
		
		DesignField dField = new DesignField();
		dField.fieldName = field.getName();
		dField.fieldId = "#instanceName#_" + dField.fieldName;
		dField.upFieldName = TypeUtil.attributeToUp(dField.fieldName);
		dField.dnFieldName = TypeUtil.attributeToDown(dField.fieldName);
		dField.getter = TypeUtil.attributeToGetter(dField.fieldName);
		dField.setter = TypeUtil.attributeToSetter(dField.fieldName);
		dField.upGetter = TypeUtil.attributeToGetter(dField.upFieldName);
		dField.dnGetter = TypeUtil.attributeToGetter(dField.dnFieldName);
		dField.upSetter = TypeUtil.attributeToSetter(dField.upFieldName);
		dField.dnSetter = TypeUtil.attributeToSetter(dField.dnFieldName);
		dField.fieldType = getFieldType(field.getGenericType().getTypeName());
		dField.fullFieldType = getFullFieldType(field.getGenericType().getTypeName());
		dField.genericFieldType = getGenericFieldType(field.getGenericType().getTypeName());
		dField.designGenericFieldType = getDesignGenericFieldType(field.getGenericType().getTypeName());
		
		try {
			Class<?> clazz = Class.forName(dField.designGenericFieldType);
			dField.fieldInstanceName = TypeUtil.toInstanceName(clazz.getSimpleName());
			
			String pazkage = clazz.getAnnotation(com.zeus.tool.cogen.annotation.Package.class)!=null
					?clazz.getAnnotation(com.zeus.tool.cogen.annotation.Package.class).name():"";
			dField.moduleName = pazkage.substring(pazkage.lastIndexOf(".") + 1, pazkage.length());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		dField.fieldClass = field.getType();
		
        SearchElement searchElement = field.getAnnotation(SearchElement.class);
        ViewElement viewElement = field.getAnnotation(ViewElement.class);
        RefElement refElement = field.getAnnotation(RefElement.class);
        RemoteElement remoteElement = field.getAnnotation(RemoteElement.class);
        InputElement inputElement = field.getAnnotation(InputElement.class);
        
        if(searchElement!=null){
        	dField.searchProperties = dField.new SearchProperties();
        	dField.searchProperties.like = searchElement.like();
        	dField.searchProperties.multi = searchElement.multi();
        	dField.searchProperties.updn = searchElement.updn();
        	dField.searchProperties.property = searchElement.property();
        	dField.searchProperties.filter = searchElement.filter();
        }
        
		if(viewElement!=null){
			dField.viewProperties = dField.new ViewProperties();   
			dField.viewProperties.name = viewElement.name();
			dField.viewProperties.inlist = viewElement.inlist();
			dField.viewProperties.valuesForm = viewElement.valuesForm();
			dField.viewProperties.viewType = viewElement.viewType();
			dField.label = viewElement.label();
			dField.title = viewElement.title();
			dField.faIcon = "fa-pencil";
			
			dField.viewProperties.valuesGetter = viewElement.valuesGetter()!=null?viewElement.valuesGetter():"";
			dField.viewProperties.valuesRef = viewElement.valuesRef()!=null?viewElement.valuesRef():"";
			
		}else if(refElement!=null){
			dField.refProperties = dField.new RefProperties();
			dField.refProperties.cascadeProperty = StringUtils.isNotBlank(refElement.cascadeProperty())?
					refElement.cascadeProperty():"id";
			dField.label = refElement.label();
			dField.title = refElement.title();
			dField.refProperties.multi = refElement.multi();
			dField.refProperties.refEName = refElement.refEName();
			dField.refProperties.select = refElement.select();
			dField.refProperties.toManyInverse = refElement.toManyInverse();
		}else if(remoteElement!=null){
			dField.remoteProperties = dField.new RemoteProperties();
			dField.remoteProperties.cascadeProperty = StringUtils.isNotBlank(remoteElement.cascadeProperty())?
					remoteElement.cascadeProperty():"id";
			dField.label = remoteElement.label();
			dField.title = remoteElement.title();
			dField.remoteProperties.multi = remoteElement.multi();
			dField.remoteProperties.select = remoteElement.select();
			dField.remoteProperties.valueUrl = remoteElement.valuesUrl();
		}
		
		if(inputElement!=null){
			dField.inputProperties = dField.new InputProperties();
			dField.inputProperties.maxLength = inputElement.maxLength();
			dField.inputProperties.mincheck = inputElement.mincheck();
			dField.inputProperties.minLength = inputElement.minLength();
			dField.inputProperties.regex = inputElement.regex()!=null?inputElement.regex():"";
			dField.inputProperties.required = inputElement.required();
			dField.inputProperties.validateType = inputElement.vType();
			dField.inputProperties.hidden = inputElement.hidden();
			dField.inputProperties.defaultValue = inputElement.defaultValue();
		}
		
		Column column = field.getAnnotation(Column.class);
        
        FK fk = field.getAnnotation(FK.class);
        PK pk = field.getAnnotation(PK.class);
        
        if(column!=null){
        	
        	dField.columnProperties = dField.new ColumnProperties();
        	
        	dField.columnProperties.comment = column.comment();
        	dField.columnProperties.type = column.type();
        	dField.columnProperties.name = column.name();
        	
        	if(pk!=null){
            	dField.columnProperties.pk = Boolean.TRUE;
            	dField.columnProperties.generate = pk.type();
            	dField.columnProperties.params = pk.params();
            } else if(fk!=null){
            	dField.columnProperties.fk = Boolean.TRUE;
            	dField.columnProperties.refColumn = fk.refColumn();
            	dField.columnProperties.refType = fk.refType();
            	dField.columnProperties.lazy = fk.lazy();
            	dField.columnProperties.store = fk.store();
            }
        }
        
        return dField;
	}
	
	public static List<DesignField> resolve(Class<?> clazz){
		
		Field[] fields= clazz.getDeclaredFields();
		List<DesignField> dFields = new ArrayList<>();
		
		for(Field field:fields){
            dFields.add(resolve(field));
        }
		return dFields;
	}
	
	public static DesignField getByFieldName(List<DesignField> dFields,String name){
		for(DesignField field:dFields){
			if(field.fieldName.equals(name)){
				return field;
			}
		}
		return null;
	}
	
	private static String getFullFieldType(String type){
		
		List<String> all = new ArrayList<>();
		String[] lv1s = type.split("<");
		for (String lv1 : lv1s) {
			String[] lv2s = lv1.split(",");
			for (String lv2 : lv2s) {
				String[] lv3s = lv2.split(">");
				for (String lv3 : lv3s) {
					all.add(lv3.trim());
				}
			}
		}
		for (String one : all) {
			if (one.length() == 1) {
				continue;
			}
			
			if(one.startsWith("com.zeus.")||one.startsWith("com.ibookstar.")){
				try {
					Class<?> clazz = Class.forName(one);
					com.zeus.tool.cogen.annotation.Package clazzPackage = 
							clazz.getAnnotation(com.zeus.tool.cogen.annotation.Package.class);
					if(clazzPackage!=null&&StringUtils.isNotBlank(clazzPackage.name())){
						String newOne = clazzPackage.name() + ".entity." + clazz.getSimpleName();
						type = type.replaceAll(one, newOne);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return type;
	}
	
	private static String getGenericFieldType(String type) {
		if(type.contains(",")){
			//包含Map复合类型，不处理
			return "";
		}else{
			if(type.contains("<")){
				return getGenericFieldType(type.substring(type.indexOf("<")+1, type.lastIndexOf(">")));
			}else{
				if(type.startsWith("com.zeus.")||type.startsWith("com.ibookstar.")){
					try {
						Class<?> clazz = Class.forName(type);
						com.zeus.tool.cogen.annotation.Package clazzPackage = 
								clazz.getAnnotation(com.zeus.tool.cogen.annotation.Package.class);
						if(clazzPackage!=null&&StringUtils.isNotBlank(clazzPackage.name())){
							String newOne = clazzPackage.name() + ".entity." + clazz.getSimpleName();
							type = type.replaceAll(type, newOne);
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				return type;
			}
		}
	}
	
	private static String getDesignGenericFieldType(String type) {
		if(type.contains(",")){
			//包含Map复合类型，不处理
			return "";
		}else{
			if(type.contains("<")){
				return getDesignGenericFieldType(type.substring(type.indexOf("<")+1, type.lastIndexOf(">")));
			}else{
				return type;
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(DesignField.getGenericFieldType("List<Map<Long,com.zeus.tool.example.Menu>>"));
	}
	
	private static String getFieldType(String type) {
		List<String> all = new ArrayList<>();
		String[] lv1s = type.split("<");
		for (String lv1 : lv1s) {
			String[] lv2s = lv1.split(",");
			for (String lv2 : lv2s) {
				String[] lv3s = lv2.split(">");
				for (String lv3 : lv3s) {
					all.add(lv3.trim());
				}
			}
		}
		for (String one : all) {
			if (one.length() == 1) {
				continue;
			}
			String shortOne = one.substring(one.lastIndexOf(".") + 1, one.length());
			type = type.replaceAll(one, shortOne);
		}
		return type;
	}
	
	
	public class ViewProperties{
		
		public ViewElement.ViewType viewType;    //显示控件类型
	    
		public boolean name = false;    //以此Field来表示该对象的基本属性，在任何场合都显示该属性
	    
		public boolean inlist = false;   //以此Element来表示该对象的显示名称
	    
		public ViewElement.ValuesForm valuesForm = ViewElement.ValuesForm.NONE;     //该控件可选值来源
	    
		public String valuesGetter;    //该控件可选择来源表达时，可以是SQL，Map的key
	    
		public String valuesRef;
	    
		public String valuesKey;       //该控件的key指向可选择对象的field名称
	    
		public String valuesValue;      //该控件value指向可选择对象的field名称
	    
	}
	
	public class InputProperties{
		
		public boolean required = false;   //是否必须填写
		
		public String regex;  //约定格式正则表达式
		
		public int maxLength = Integer.MAX_VALUE;  //最大长度
	    
		public int minLength = 0;    //最小长度
	    
		public int mincheck = 0;   //针对多选，约束至少选中几个
	    
		public InputElement.VType validateType = InputElement.VType.NONE;    //输入类型验证
		
		public boolean hidden = false;
		
		public String defaultValue;
	}
	
	/**
	 * 与ViewProperties地位一致，选其一使用即可
	 */
	public class RefProperties{
		
		public boolean multi = false;  //关联字段是否允许多选
		
		public String refEName;    //提交到页面时的名称
		
		public boolean select = true;
		
		public String cascadeProperty = "";     //针对对象字段，向下取值
		
		public boolean toManyInverse = false;   //针对创建关系有一方创建，维护关系由另一方维护的情况
		
	}
	
	/**
	 * 与ViewProperties地位一致，选其一使用即可
	 */
	public class RemoteProperties{
		
		public boolean multi = false;  //关联字段是否允许多选
		
		public String refEName;    //提交到页面时的名称
		
		public boolean select = true;
		
		public String cascadeProperty = "";     //针对对象字段，向下取值
		
		public String valueUrl = "";
		
	}
	
	public class SearchProperties{
		
		public boolean multi = false;   //是否是多选，精确
	    
		public boolean like = false;    //是否是模糊查询
	    
		public boolean updn = false;   //是否是范围查询
		
		public String property = "";
		
		public boolean filter = false;
	}
	
	public class ColumnProperties{
		public String name;
		public String comment = "";
		public String type;
		public boolean index = false;
	    
		public boolean fk = false;
		public String refColumn;
		public RefType refType = RefType.many_to_many;
		public LazyType lazy = LazyType.No_Proxy;
		public String store;         //关联关系存放位置
	    
		public boolean pk = false;
		public String generate;
		public String params = "";
	}
}
