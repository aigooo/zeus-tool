package com.zeus.tool.cogen;

import com.zeus.tool.cogen.design.DesignEntity;

public interface Cogen {
	
	public static String UPDATE = "update";
	public static String CREATE = "create";
	public static String UPCRTE = "upcrte";
	public static String REMOVE = "remove";
	
	public void createClass(String rootPath,DesignEntity designEntity);
	
	public void updateClass(String rootPath,DesignEntity designEntity);
	
	public void createOrUpdateClass(String rootPath,DesignEntity designEntity);
	
	public void removeClass(String rootPath,DesignEntity designEntity);
	
	public void removeField(String rootPath,DesignEntity designEntity,String fieldName);
	
}
