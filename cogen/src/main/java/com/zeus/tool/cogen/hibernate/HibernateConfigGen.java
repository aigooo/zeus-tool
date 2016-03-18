package com.zeus.tool.cogen.hibernate;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;

import com.zeus.persist.annotation.FK;
import com.zeus.tool.cogen.Cogen;
import com.zeus.tool.cogen.FileUtil;
import com.zeus.tool.cogen.design.DesignEntity;
import com.zeus.tool.cogen.design.DesignField;

public class HibernateConfigGen implements HibernatePorperties,Cogen{

	public static HibernateConfigGen init(){
		return new HibernateConfigGen();
	}
	@Override
	public void createClass(String rootPath, DesignEntity designEntity) {
		String hibernateConfigPath = rootPath + "src/main/resources/" + "config/hibernate/" + designEntity.clazzName + ".hbm.xml";
		File file = new File(hibernateConfigPath);
		try {
			if (!file.exists()) {
				String all = FileUtil.fileContent("#clazzName#.hbm.xml.template");
				for (DesignField dField : designEntity.dFields) {
					if (dField.columnProperties != null) {
						if (dField.columnProperties.pk) {
							all = all.replaceAll(ID_BLOCK, idConfig);

							String params = dField.columnProperties.params;
							if (StringUtils.isNotBlank(params)) {
								params = param.replaceAll(PARAM_KEY, params.split("#")[0]).replaceAll(PARAM_VALUE, params.split("#")[1]);
							}
							all = all.replace("#generator#", dField.columnProperties.generate).replaceAll(PARAM,
									params != null ? params : "");
						} else if (dField.columnProperties.fk) {
							String fieldLazy = dField.columnProperties.lazy.getValue();
							if (dField.columnProperties.refType == FK.RefType.many_to_one) {
								all = all.replaceAll(PROPERTY_BLOCK, many_to_one);
							}

							if (dField.columnProperties.refType == FK.RefType.one_to_many) {
								all = all.replaceAll(PROPERTY_BLOCK, one_to_many);
							}

							if (dField.columnProperties.refType == FK.RefType.many_to_many) {
								if (StringUtils.isNotBlank(dField.genericFieldType)) {
									all = all.replaceAll(PROPERTY_BLOCK, many_to_many);
									if(StringUtils.isBlank(dField.columnProperties.name)){
										Class<?> fClazz = Class.forName(dField.designGenericFieldType);
										dField.columnProperties.name = DesignField.resolve(fClazz.getDeclaredField("id")).columnProperties.name;
									}
								} else {
									all = all.replaceAll(PROPERTY_BLOCK, ERROR_PROPERTY);
								}
							}
							all = all.replaceAll("#fieldLazy#", fieldLazy);
						} else if (dField.remoteProperties!=null){
							dField.fieldName = dField.fieldName + "Json";
							all = all.replaceAll(PROPERTY_BLOCK, property);
							all = all.replaceAll(PROPERTY_TYPE, "");
						} else {
							all = all.replaceAll(PROPERTY_BLOCK, property);
							if (dField.fullFieldType.startsWith("java.lang")) {
								all = all.replaceAll(PROPERTY_TYPE, "");
							} else if (dField.fullFieldType.startsWith("java.")) {
								all = all.replaceAll(PROPERTY_TYPE, property_type);
							} else {
								all = all.replaceAll(PROPERTY_TYPE, property_class);
							}
						}
						all = all.replaceAll("#fieldName#", dField.fieldName)
								.replaceAll("#fieldColumn#", dField.columnProperties.name)
								.replaceAll("#fieldType#", dField.fullFieldType)
								.replace(ID_COLUMN, DesignField.getByFieldName(designEntity.dFields, "id").columnProperties.name)
								.replace("#genericFieldType#", dField.genericFieldType)
								.replace(STORE, dField.columnProperties.store != null ? dField.columnProperties.store : "");
					}
				}
				if (designEntity.markDelete) {
					all = all.replaceAll(PROPERTY_BLOCK, porperty_isDelete);
				}
				all = FileUtil.replacePlaceholders(all, designEntity);
				FileUtil.createFile(hibernateConfigPath, all);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateClass(String rootPath, DesignEntity designEntity) {
		//增加字段
		String hibernateConfigPath = "config/hibernate/" + designEntity.clazzName + ".hbm.xml";
		File file = new File(hibernateConfigPath);
		try {
			if (file.exists()) {
				InputStream is = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(hibernateConfigPath);
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String line, fileName, all = "";
				while ((line = br.readLine()) != null) {
					all = all + line + "\n";
				}
				for (DesignField dField : designEntity.dFields) {
					if (!all.contains("<property name=\"" + dField.fieldName)) {
						if (dField.columnProperties.pk) {
							all = all.replaceAll(ID_BLOCK, idConfig);
							String params = dField.columnProperties.params;
							if (params != null) {
								String paramKey = params.split("#")[0];
								String paramValue = params.split("#")[1];
								params = param.replaceAll(PARAM_KEY, paramKey).replaceAll(PARAM_VALUE, paramValue);
							}
							all = all.replaceAll(PARAM, params != null ? params : "");
						} else if (dField.columnProperties.fk) {
							String fieldLazy = dField.columnProperties.lazy.getValue();
							if (dField.columnProperties.refType == FK.RefType.many_to_one) {
								all = all.replaceAll(PROPERTY_BLOCK, many_to_one);
							}
	
							if (dField.columnProperties.refType == FK.RefType.one_to_many) {
								all = all.replaceAll(PROPERTY_BLOCK, one_to_many);
							}
							all = all.replaceAll("#fieldLazy#", fieldLazy);
						} else {
							all = all.replaceAll(PROPERTY_BLOCK, property);
							if (dField.fullFieldType.startsWith("java.lang")) {
								all = all.replaceAll(PROPERTY_TYPE, "");
							} else if (dField.fullFieldType.startsWith("java.")) {
								all = all.replaceAll(PROPERTY_TYPE, property_type);
							} else {
								all = all.replaceAll(PROPERTY_TYPE, property_class);
							}
						}
						all = all.replaceAll("#fieldName#", dField.fieldName)
								.replaceAll("#fieldColumn#", dField.columnProperties.name)
								.replaceAll("#fieldType#", dField.fullFieldType);
					}
				}
				if (designEntity.markDelete) {
					all = all.replaceAll(PROPERTY_BLOCK, porperty_isDelete);
				}
				all = FileUtil.replacePlaceholders(all, designEntity);
				fileName = designEntity.clazzName + ".hbm.xml";
				FileUtil.createFile(rootPath + "config/hibernate/" + fileName, all);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createOrUpdateClass(String rootPath, DesignEntity designEntity) {
		String hibernateConfigPath = "config/hibernate/" + designEntity.clazzName + ".hbm.xml";
		File file = new File(hibernateConfigPath);
		if (!file.exists()) {
		}
	}

	@Override
	public void removeClass(String rootPath, DesignEntity designEntity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeField(String rootPath, DesignEntity designEntity, String fieldName) {
		// TODO Auto-generated method stub
	}

}
