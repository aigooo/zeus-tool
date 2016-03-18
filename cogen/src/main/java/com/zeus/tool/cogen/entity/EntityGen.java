package com.zeus.tool.cogen.entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zeus.common.util.JsonUtil;
import com.zeus.common.util.TypeUtil;
import com.zeus.tool.cogen.Cogen;
import com.zeus.tool.cogen.FileUtil;
import com.zeus.tool.cogen.design.DesignEntity;
import com.zeus.tool.cogen.design.DesignField;

public class EntityGen implements Cogen,EntityProperties{

	public static EntityGen init(){
		JsonUtil.toJSONString("");
		return new EntityGen();
	}
	
	@Override
	public void createClass(String rootPath,DesignEntity designEntity) {
		String clazzPackage = designEntity.clazzPackage + ".entity";
		String filePath = rootPath +"src/main/java/"+ clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + ".java";
		File file = new File(filePath);
		if (!file.exists()) { //不存在的情况下创建，存在的情况下do nothing
			List<String> entityImports = new ArrayList<>();
			
			//处理实体对象实现的接口，默认加上IdModel接口
			StringBuilder entityImplements = new StringBuilder().append("implements IdModel<").append(designEntity.idType).append(">");
			StringBuilder entityImport = new StringBuilder();
			
			entityImports.add("com.zeus.persist.model.IdModel");
			if (designEntity.markDelete) {
				entityImplements.append(",MarkDelete");
				entityImports.add("com.zeus.persist.model.MarkDelete");
			}
			try {
				String all = FileUtil.fileContent("#clazzName#.template");
				for(DesignField dfield : designEntity.dFields){
					all = addField(all,dfield,entityImports);
				}
				
				//定义字段增加完成后，额外添加两个字段
				all = addField(all,"order","String",null,entityImports);
				all = addField(all,"condition","String",null,entityImports);
				
				if (designEntity.markDelete) {
					all = all.replace(ATTRIBUTES, deleteAttribute);
					all = all.replace(GETTERANDSETTER, deleteGetterAndSetter);
				}
				
				for (String ei : entityImports) {
					if (!entityImport.toString().contains(ei)&&!ei.contains(clazzPackage))
						entityImport.append("import ").append(ei).append(";\n");
				}
				
				//替换掉implements部分和imports部分
				all = all.replace(IMPLEMENTS, entityImplements.toString()).replace(IMPORTS, entityImport.toString());
				all = FileUtil.replacePlaceholders(all, designEntity);
				
				//创建实体类文件
				FileUtil.createFile(rootPath + "src/main/java/" + clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + ".java",all);
			} catch (IOException e) {
				System.out.println("在创建{" + designEntity.clazzPackage + "." + designEntity.clazzName + "}时，读取模板文件失败！");
				return;
			}
		}
	}

	@Override
	public void updateClass(String rootPath,DesignEntity designEntity) {
		// 增加字段，不改变原有内容；变更字段类型，删除原有字段；不删除字段
		String clazzPackage = designEntity.clazzPackage + ".entity";
		String filePath = rootPath + "src/main/java/" + clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + ".java";
		File file = new File(filePath);
		if (file.exists()) { //存在的情况下更新，不存在的情况下do nothing
			try {
				String all = FileUtil.fileContent(file);
				List<String> entityImports = new ArrayList<>();
				StringBuilder entityImport = new StringBuilder();
				for(DesignField dfield : designEntity.dFields){
					String fieldName = dfield.fieldName,fieldType = dealWithFieldType(dfield.fullFieldType,entityImports);
					
					String nameKey  = " " + fieldName + ";";
					
					String attrKey = onlyAttribute.replace("#fieldName#", fieldName).replace("#fieldType#", fieldType);
					if(!all.contains(nameKey)){   //不存在该名称的字段
						all = addField(all,dfield,entityImports);
					}else if(all.contains(nameKey)&&!all.contains(attrKey)){  //存在该名称的字段,不存在属性的字段，字段类型变更
						all = removeField(all,dfield,entityImports);
						all = addField(all,dfield,entityImports);
					}
				}
				
				//替换掉implements部分和imports部分
				all = all.replace(IMPORTS, entityImport.toString());
				all = FileUtil.replacePlaceholders(all, designEntity);
				
				//创建实体类文件
				FileUtil.createFile(rootPath + "src/main/java/" + clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + ".java",all);
				
			}catch (IOException e) {
				System.out.println("在更新{" + designEntity.clazzPackage + "." + designEntity.clazzName + "}时，读取源文件失败！");
				return;
			}
		}
	}
	
	@Override
	public void createOrUpdateClass(String rootPath,DesignEntity designEntity) {
		String clazzPackage = designEntity.clazzPackage + ".entity";
		String filePath = rootPath + "src/main/java/" + clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + ".java";
		File file = new File(filePath);
		if (file.exists()) {
			updateClass(rootPath,designEntity);
		}else{
			createClass(rootPath,designEntity);
		}
	}

	@Override
	public void removeClass(String rootPath,DesignEntity designEntity) {
		String clazzPackage = designEntity.clazzPackage + ".entity";
		String filePath = rootPath + "src/main/java/" + clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + ".java";
		new File(filePath).deleteOnExit();
	}
	
	@Override
	public void removeField(String rootPath, DesignEntity designEntity, String fieldName) {
		// 增加字段，不改变原有内容；变更字段类型，删除原有字段；不删除字段
		String clazzPackage = designEntity.clazzPackage + ".entity";
		String filePath = rootPath + "src/main/java/" + clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + ".java";
		File file = new File(filePath);
		if (file.exists()) { //存在的情况下更新，不存在的情况下do nothing
			try {
				String all = FileUtil.fileContent(file);
				for(DesignField dfield : designEntity.dFields){
					if(dfield.fieldName.equals(fieldName)){
						all = removeField(all,dfield,new ArrayList<>());
					}
				}
				//创建实体类文件
				FileUtil.createFile(rootPath + "src/main/java/" + clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + ".java",all);
			}catch (IOException e) {
				System.out.println("在更新{" + designEntity.clazzPackage + "." + designEntity.clazzName + "}时，读取源文件失败！");
				return;
			}
		}
	}
	
	private String addRemoteField(String all,String fieldName,String fieldType,DesignField dfield,List<String> entityImports){
		all = all.replace(ATTRIBUTES, attribute.replace("#fieldName#", fieldName).replace("#fieldType#", fieldType));
		all = all.replace(GETTERANDSETTER, remoteGetterAndSetter.replace("#getter#", TypeUtil.attributeToGetter(fieldName))
				.replace("#setter#", TypeUtil.attributeToSetter(fieldName))
				.replaceAll("#fieldName#", fieldName).replaceAll("#fieldType#", fieldType));
		return all;
	}

	private String addField(String all,String fieldName,String fieldType,DesignField dfield,List<String> entityImports){
		if(dfield!=null&&dfield.refProperties!=null&&dfield.refProperties.toManyInverse){
			all = all.replace(ATTRIBUTES, ("\t@JSONField(serialize = false)\n"+attribute).replace("#fieldName#", fieldName).replace("#fieldType#", fieldType));
			entityImports.add("com.alibaba.fastjson.annotation.JSONField");
		}else{
			all = all.replace(ATTRIBUTES, attribute.replace("#fieldName#", fieldName).replace("#fieldType#", fieldType));
		}
		all = all.replace(GETTERANDSETTER, getterAndSetter.replace("#getter#", TypeUtil.attributeToGetter(fieldName))
				.replace("#setter#", TypeUtil.attributeToSetter(fieldName))
				.replaceAll("#fieldName#", fieldName).replaceAll("#fieldType#", fieldType));
		return all;
	}
	
	private String removeField(String all,String fieldName,String fieldType){
		all = all.replace(onlyAttribute.replace("#fieldName#", fieldName).replace("#fieldType#", fieldType), "");
		all = all.replace(onlyGetterAndSetter.replace("#getter#", TypeUtil.attributeToGetter(fieldName))
				.replace("#setter#", TypeUtil.attributeToSetter(fieldName))
				.replaceAll("#fieldName#", fieldName).replaceAll("#fieldType#", fieldType),"");
		return all;
	}
	
	private String addField(String all,DesignField dfield,List<String> entityImports){
		String fieldName = dfield.fieldName,fieldType = dealWithFieldType(dfield.fullFieldType,entityImports);
		
		//如果是Remote字段
		if (dfield.remoteProperties!=null) {
			dealWithFieldType("com.zeus.common.util.JsonUtil",entityImports);
			all = addField(all,dfield.fieldName + "Json","String",dfield,entityImports);
			all = addRemoteField(all,fieldName,fieldType,dfield,entityImports);
		}else{
			//加入本字段
			all = addField(all,fieldName,fieldType,dfield,entityImports);
		}
		
		//如果该dfield是关联的对象，且关联类型是* to many类型，默认对多以属性以s结尾，增加一个属性单类型，如存在List<T> tags，则增加一个属性T tag
		if(dfield.columnProperties!=null&&dfield.columnProperties.fk&&dfield.columnProperties.refType.getValue().contains("-to-many")){
			fieldName = dfield.fieldName.substring(0, dfield.fieldName.length()-1);
			fieldType = dealWithFieldType(dfield.genericFieldType,entityImports);
			all = addComplexField(all,fieldName,fieldType,dfield,entityImports);
		}
		
		//如果是搜索字段
		if (dfield.searchProperties != null) {
			if (dfield.searchProperties.updn) { // searchElement支持范围  dnFieldName and upFieldName
				all = addField(all,dfield.dnFieldName,dfield.fieldType,dfield,entityImports);
				all = addField(all,dfield.upFieldName,dfield.fieldType,dfield,entityImports);
			}
			if (dfield.searchProperties.multi) {
				all = addField(all,dfield.fieldName + "s","String",dfield,entityImports);
			}
		}
		
		return all;
	}
	
	private String addComplexField(String all,String fieldName,String fieldType,DesignField dfield,List<String> entityImports){
		all = all.replace(ATTRIBUTES, ("\t@JSONField(serialize = false)\n" + attribute).replace("#fieldName#", fieldName).replace("#fieldType#", fieldType));
		all = all.replace(GETTERANDSETTER, complexSetterAndSetter.replace("#getter#", TypeUtil.attributeToGetter(fieldName))
				.replace("#setter#", TypeUtil.attributeToSetter(fieldName))
				.replaceAll("#fieldName#", fieldName).replaceAll("#fieldType#", fieldType));
		entityImports.add("org.apache.commons.lang.StringUtils");
		entityImports.add("java.util.ArrayList");
		entityImports.add("com.alibaba.fastjson.annotation.JSONField");
		return all;
	}

	private String removeField(String all, DesignField dfield,List<String> entityImports) {
		String fieldName = dfield.fieldName;
		String attrLine = FileUtil.findLine(all,"private"," " + fieldName+";");
		String fieldType = attrLine.replace("private ", "").replace(fieldName + ";", "").trim();
		//加入本字段
		all = removeField(all,fieldName,fieldType);
		all = removeField(all,dfield.upFieldName,fieldType);
		all = removeField(all,dfield.dnFieldName,fieldType);
		
		
		fieldName = dfield.genericFieldType.contains(".")?
					dfield.genericFieldType.substring(dfield.genericFieldType.lastIndexOf(".")+1):dfield.genericFieldType;
		fieldName = (""+fieldName.charAt(0)).toLowerCase() + fieldName.substring(1);
		fieldType = dealWithFieldType(dfield.genericFieldType,entityImports);
		all = removeField(all,fieldName,fieldType);
		
		all = removeField(all,dfield.fieldName + "Choosen","List<"+dfield.fieldType +">");
		
		return all;
	}
	
	private static String dealWithFieldType(String type, List<String> entityImports) {
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
			addToImport(one, entityImports);
			String shortOne = one.substring(one.lastIndexOf(".") + 1, one.length());
			type = type.replaceAll(one, shortOne);
		}
		return type;
	}

	private static void addToImport(String type, List<String> entityImports) {
		if (!type.startsWith("java.lang") && !"int,short,long,double,float".contains(type)) {

			if (StringUtils.isNotBlank(type) && !entityImports.contains(type)) {
				entityImports.add(type);
			}
		}
	}

}
