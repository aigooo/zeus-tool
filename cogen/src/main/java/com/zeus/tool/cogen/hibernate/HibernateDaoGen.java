package com.zeus.tool.cogen.hibernate;

import java.io.File;
import java.io.IOException;

import com.zeus.tool.cogen.Cogen;
import com.zeus.tool.cogen.FileUtil;
import com.zeus.tool.cogen.design.DesignEntity;

public class HibernateDaoGen implements Cogen,HibernatePorperties{
	
	public static HibernateDaoGen init(){
		return new HibernateDaoGen();
	}

	@Override
	public void createClass(String rootPath, DesignEntity designEntity) {
		String clazzPackage = designEntity.clazzPackage + ".dao";
		String filePath = rootPath + "src/main/java/" +  clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + "Dao.java";
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				String all = FileUtil.fileContent("#clazzName#Dao.hibernate.template");
				all = FileUtil.replacePlaceholders(all, designEntity);
				FileUtil.createFile(filePath, all);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateClass(String rootPath, DesignEntity designEntity) {
	}

	@Override
	public void createOrUpdateClass(String rootPath, DesignEntity designEntity) {
		createClass(rootPath,designEntity);
	}

	@Override
	public void removeClass(String rootPath, DesignEntity designEntity) {
		String clazzPackage = designEntity.clazzPackage + ".dao";
		String filePath = rootPath + "src/main/java/" + clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + ".java";
		new File(filePath).deleteOnExit();
	}

	@Override
	public void removeField(String rootPath, DesignEntity designEntity, String fieldName) {
	}
}
