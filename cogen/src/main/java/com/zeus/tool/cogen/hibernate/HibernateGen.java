package com.zeus.tool.cogen.hibernate;

import java.io.File;
import java.io.IOException;

import com.zeus.common.util.TypeUtil;
import com.zeus.tool.cogen.Cogen;
import com.zeus.tool.cogen.FileUtil;
import com.zeus.tool.cogen.design.DesignEntity;
import com.zeus.tool.cogen.design.DesignField;

public class HibernateGen implements HibernatePorperties,Cogen {

	public static HibernateGen init(){
		return new HibernateGen();
	}
	@Override
	public void createClass(String rootPath, DesignEntity designEntity) {
		String clazzPackage = designEntity.clazzPackage + ".dao.impl";
		String filePath = rootPath +"src/main/java/"+ clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + "DaoImpl.java";
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				String all = FileUtil.fileContent("#clazzName#DaoImpl.hibernate.template");
				for (DesignField dField : designEntity.dFields) {
					String fieldName = dField.fieldName;
					String getter = dField.getter;
					String upGetter = dField.upGetter;
					String dnGetter = dField.dnGetter;
					String fieldClass = dField.fieldType;
					String objectNotNull = "true";

					if (dField.searchProperties != null) {
						//TODO 查询one-to-many or many-to-many
						if (dField.columnProperties.fk) {
							if (dField.columnProperties.refType.getValue().contains("-to-one")){
								String preFieldName = TypeUtil.attributeToGetter(fieldName);
								fieldName = dField.fieldName + ".id";
								getter = preFieldName + "()." + TypeUtil.attributeToGetter("id");
								upGetter = preFieldName + "()."
										+ TypeUtil.attributeToGetter(TypeUtil.attributeToUp("id"));
								upGetter = preFieldName + "()."
										+ TypeUtil.attributeToGetter(TypeUtil.attributeToDown("id"));
								try {
									Class<?> fClazz = Class.forName(dField.designGenericFieldType);
									fieldClass = fClazz.getDeclaredField("id").getType().getSimpleName();
								} catch (NoSuchFieldException | SecurityException e) {
									e.printStackTrace();
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								}
								objectNotNull = "#instanceName#." + preFieldName + "()!=null";
							}
						}
						if (fieldClass.equals("String")) {
							if (dField.searchProperties.like) {
								all = all.replaceAll(SEARCH_BLOCK, search_block_string_like);
							} else {
								all = all.replaceAll(SEARCH_BLOCK, search_block_string_equal);
							}
							if (dField.searchProperties.multi) {
								all = all.replaceAll(SEARCH_BLOCK, search_block_in);
							}
							if (dField.searchProperties.updn) {
								all = all.replaceAll(SEARCH_BLOCK, search_block_string_rang);
							}
						} else {
							all = all.replaceAll(SEARCH_BLOCK, search_block_other_equal);
							if (dField.searchProperties.multi) {
								all = all.replaceAll(SEARCH_BLOCK, search_block_in);
							}
							if (dField.searchProperties.updn) {
								all = all.replaceAll(SEARCH_BLOCK, search_block_other_rang);
							}
						}
						all = all.replaceAll(GETTER, getter).replaceAll(UPGETTER, upGetter)
								.replaceAll(DNGETTER, dnGetter).replaceAll(FIELDCLASS, fieldClass)
								.replaceAll(FIELDNAME, fieldName).replaceAll(OBJECT_NOT_NULL, objectNotNull);
					}
				}
				all = FileUtil.replacePlaceholders(all, designEntity);
				FileUtil.createFile(filePath, all);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateClass(String rootPath, DesignEntity designEntity) {
		String clazzPackage = designEntity.clazzPackage + ".dao.impl";
		String filePath = rootPath + clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + "DaoImpl.java";
		try {
			File file = new File(filePath);
			String all = "";
			if (!file.exists()) {
				all = FileUtil.fileContent("#clazzName#DaoImpl.hibernate.template");
				for (DesignField dField : designEntity.dFields) {
					String fieldName = dField.fieldName;
					String getter = dField.getter;
					String upGetter = dField.upGetter;
					String dnGetter = dField.dnGetter;
					String fieldClass = dField.fieldType;
					String objectNotNull = "true";

					if (dField.searchProperties != null) {
						if (dField.columnProperties.fk) {
							if (dField.columnProperties.refType.getValue().contains("-to-one")){
								String preFieldName = TypeUtil.attributeToGetter(fieldName);
								fieldName = dField.fieldName + ".id";
								getter = preFieldName + "()." + TypeUtil.attributeToGetter("id");
								upGetter = preFieldName + "()."
										+ TypeUtil.attributeToGetter(TypeUtil.attributeToUp("id"));
								upGetter = preFieldName + "()."
										+ TypeUtil.attributeToGetter(TypeUtil.attributeToDown("id"));
								try {
									Class<?> fClazz = Class.forName(dField.designGenericFieldType);
									fieldClass = fClazz.getDeclaredField("id").getType().getSimpleName();
								} catch (NoSuchFieldException | SecurityException e) {
									e.printStackTrace();
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								}
								objectNotNull = "#instanceName#." + preFieldName + "()!=null";
							}
						}
						if (!dField.columnProperties.refType.getValue().contains("-to-many")) {
							if (fieldClass.equals("String")) {
								if (dField.searchProperties.like) {
									if (!all.contains("t." + fieldName + " like "))
										all = all.replaceAll(SEARCH_BLOCK, search_block_string_like);
								} else {
									if (!all.contains("t." + fieldName + " = "))
										all = all.replaceAll(SEARCH_BLOCK, search_block_string_equal);
								}
								if (dField.searchProperties.multi) {
									if (!all.contains("t." + fieldName + " in "))
										all = all.replaceAll(SEARCH_BLOCK, search_block_in);
								}
								if (dField.searchProperties.updn) {
									if (!all.contains("t." + fieldName + " >= "))
										all = all.replaceAll(SEARCH_BLOCK, search_block_string_rang);
								}
							} else {
								if (!all.contains("t." + fieldName + " = "))
									all = all.replaceAll(SEARCH_BLOCK, search_block_other_equal);
								if (dField.searchProperties.multi) {
									if (!all.contains("t." + fieldName + " in "))
										all = all.replaceAll(SEARCH_BLOCK, search_block_in);
								}
								if (dField.searchProperties.updn) {
									if (!all.contains("t." + fieldName + " >= "))
										all = all.replaceAll(SEARCH_BLOCK, search_block_other_rang);
								}
							}
							all = all.replaceAll(GETTER, getter).replaceAll(UPGETTER, upGetter)
									.replaceAll(DNGETTER, dnGetter).replaceAll(FIELDCLASS, fieldClass)
									.replaceAll(FIELDNAME, fieldName).replaceAll(OBJECT_NOT_NULL, objectNotNull);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createOrUpdateClass(String rootPath, DesignEntity designEntity) {
		String clazzPackage = designEntity.clazzPackage + ".dao.impl";
		String filePath = rootPath + "src/main/java/" + clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + "DaoImpl.java";
		File file = new File(filePath);
		if (file.exists()) {
			updateClass(rootPath,designEntity);
		}else{
			createClass(rootPath,designEntity);
		}
	}

	@Override
	public void removeClass(String rootPath, DesignEntity designEntity) {
		String clazzPackage = designEntity.clazzPackage + ".dao.impl";
		String filePath = rootPath + "src/main/java/" + clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + "DaoImpl.java";
		new File(filePath).deleteOnExit();
	}

	@Override
	public void removeField(String rootPath, DesignEntity designEntity, String fieldName) {
		// TODO Auto-generated method stub
	}
}
