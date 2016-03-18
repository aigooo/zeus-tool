package com.zeus.tool.cogen.restful;

import java.io.File;

import com.zeus.tool.cogen.Cogen;
import com.zeus.tool.cogen.FileUtil;
import com.zeus.tool.cogen.design.DesignEntity;

public class RestfulActionGen implements Cogen{
	
	public static RestfulActionGen init(){
		return new RestfulActionGen();
	}
	@Override
	public void createClass(String rootPath, DesignEntity designEntity) {
		String clazzPackage = designEntity.clazzPackage + ".action";
		String filePath = rootPath + "src/main/java/" + clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + "Action.java";
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				String all = FileUtil.fileContent("#clazzName#Action.template");
				all = FileUtil.replacePlaceholders(all, designEntity);
				FileUtil.createFile(filePath, all);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateClass(String rootPath, DesignEntity designEntity) {
		createClass(rootPath,designEntity);
	}

	@Override
	public void createOrUpdateClass(String rootPath, DesignEntity designEntity) {
		createClass(rootPath,designEntity);
	}

	@Override
	public void removeClass(String rootPath, DesignEntity designEntity) {
		createClass(rootPath,designEntity);
		
	}

	@Override
	public void removeField(String rootPath, DesignEntity designEntity, String fieldName) {
	}
}
