package com.zeus.tool.cogen.spring;

import java.io.File;
import java.io.IOException;

import com.zeus.tool.cogen.Cogen;
import com.zeus.tool.cogen.FileUtil;
import com.zeus.tool.cogen.design.DesignEntity;

public class SpringGen implements Cogen,SpringProperties{

	public static SpringGen init(){
		return new SpringGen();
	}
	
	@Override
	public void createClass(String rootPath, DesignEntity designEntity) {
		String clazzPackage = designEntity.clazzPackage + ".service.impl";
        String filePath = rootPath + "src/main/java/" +clazzPackage.replaceAll("\\.", "/") + "/" +  designEntity.clazzName + "ServiceImpl.java";
        try {
            File file = new File(filePath);
            if(!file.exists()) {
                String all = FileUtil.fileContent("#clazzName#ServiceImpl.template");
                all = FileUtil.replacePlaceholders(all, designEntity);
                FileUtil.createFile(filePath, all);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        clazzPackage = designEntity.clazzPackage + ".service";
        filePath = rootPath + "src/main/java/" + clazzPackage.replaceAll("\\.", "/") + "/" + designEntity.clazzName + "Service.java";;
        try {
            File file = new File(filePath);
            if(!file.exists()) {
                String all = FileUtil.fileContent("#clazzName#Service.template");
                all = FileUtil.replacePlaceholders(all, designEntity);
                FileUtil.createFile(filePath, all);
            }
        } catch (IOException e) {
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
		createClass(rootPath,designEntity);
	}
}
