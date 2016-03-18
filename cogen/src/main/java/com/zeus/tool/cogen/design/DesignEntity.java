package com.zeus.tool.cogen.design;

import java.util.List;

import com.zeus.persist.annotation.Table;
import com.zeus.persist.util.MapUtil;
import com.zeus.tool.cogen.annotation.HibernateSupport;
import com.zeus.tool.cogen.annotation.JDBCSupport;
import com.zeus.tool.cogen.annotation.MarkDelete;
import com.zeus.tool.cogen.annotation.MybatisSupport;
import com.zeus.tool.cogen.annotation.SpringMVCSupport;
import com.zeus.tool.cogen.annotation.SpringSupport;
import com.zeus.tool.cogen.annotation.StrutsSupport;

public class DesignEntity {

	public Class<?> clazz;
	public String clazzPackage;
	public String moduleName;
	public String clazzName;
	public String instanceName;
	public String tableName;
	public String idType;
	public Boolean markDelete;
	public String nameProperty;

	public List<DesignField> dFields;

	public Boolean springMVCSupport;
	public Boolean strutsSupport;
	public Boolean springSupport;
	public Boolean mybatisSupport;
	public Boolean hibernateSupport;
	public Boolean jdbcSupport;

	private DesignEntity() {
	}

	/**
	 * 默认的解析方法，通过注解的方式来解析
	 * @param clazz
	 * @return
	 */
	public static DesignEntity resolve(Class<?> clazz) {
		DesignEntity dEntity = new DesignEntity();

		dEntity.clazzPackage = clazz.getAnnotation(com.zeus.tool.cogen.annotation.Package.class) != null
				? clazz.getAnnotation(com.zeus.tool.cogen.annotation.Package.class).name() : "";
		dEntity.moduleName = dEntity.clazzPackage.substring(dEntity.clazzPackage.lastIndexOf(".") + 1,
				dEntity.clazzPackage.length());
		dEntity.clazzName = clazz.getSimpleName();
		dEntity.instanceName = ("" + dEntity.clazzName.charAt(0)).toLowerCase()
				+ dEntity.clazzName.substring(1, dEntity.clazzName.length());
		dEntity.tableName = clazz.getAnnotation(Table.class) != null ? clazz.getAnnotation(Table.class).name()
				: MapUtil.objectToTable(clazz);
		dEntity.idType = "Long";
		try {
			dEntity.idType = clazz.getDeclaredField("id").getType().getName();
		} catch (NoSuchFieldException e) {
			try {
				dEntity.idType = clazz.getDeclaredField("code").getType().getName();
			} catch (NoSuchFieldException | SecurityException e1) {
				e1.printStackTrace();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		dEntity.dFields = DesignField.resolve(clazz);
		for(DesignField dField:dEntity.dFields){
			if(dField.viewProperties!=null&&dField.viewProperties.name){
				dEntity.nameProperty = dField.fieldName;
				break;
			}
		}
		dEntity.markDelete = clazz.getAnnotation(MarkDelete.class) != null;

		dEntity.springMVCSupport = clazz.getAnnotation(SpringMVCSupport.class) != null;
		dEntity.strutsSupport = clazz.getAnnotation(StrutsSupport.class) != null;
		dEntity.springSupport = clazz.getAnnotation(SpringSupport.class) != null;
		dEntity.mybatisSupport = clazz.getAnnotation(MybatisSupport.class) != null;
		dEntity.hibernateSupport = clazz.getAnnotation(HibernateSupport.class) != null;
		dEntity.jdbcSupport = clazz.getAnnotation(JDBCSupport.class) != null;

		return dEntity;
	}
}
