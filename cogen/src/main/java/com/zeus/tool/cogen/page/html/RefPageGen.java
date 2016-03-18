package com.zeus.tool.cogen.page.html;

import java.io.File;
import java.io.IOException;

import com.zeus.tool.cogen.Cogen;
import com.zeus.tool.cogen.FileUtil;
import com.zeus.tool.cogen.design.DesignEntity;
import com.zeus.tool.cogen.design.DesignField;
import com.zeus.tool.cogen.page.html.beyond.TableViews;

public class RefPageGen implements Cogen,TableViews{

	public static RefPageGen init() {
		return new RefPageGen();
	}
	@Override
	public void createClass(String rootPath, DesignEntity designEntity) {
		String filePath = rootPath + "statics/page/" + designEntity.moduleName + "/" + designEntity.instanceName + "-ref.html";
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				String all = FileUtil.fileContent("#instanceName#-ref.html");
				all = all.replace(TR_JS, choosen_tr_js);
				for (DesignField dField : designEntity.dFields) {
					all = GenFieldHelper.gen4ListField(all, dField);
					all = GenFieldHelper.gen4SearchField(all, dField);
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
		
	}

	@Override
	public void createOrUpdateClass(String rootPath, DesignEntity designEntity) {
		
	}

	@Override
	public void removeClass(String rootPath, DesignEntity designEntity) {
		
	}

	@Override
	public void removeField(String rootPath, DesignEntity designEntity, String fieldName) {
		
	}

}
