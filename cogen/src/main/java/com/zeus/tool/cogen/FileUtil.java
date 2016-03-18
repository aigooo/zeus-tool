package com.zeus.tool.cogen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.zeus.tool.cogen.design.DesignEntity;
import com.zeus.tool.cogen.page.html.UrlProperties;

public class FileUtil implements UrlProperties{

    public static final String CLASS_NAME = "#clazzName#";
    public static final String INSTANCE_NAME = "#instanceName#";
    public static final String TABLE_NAME = "#tableName#";
    public static final String PACKAGE = "#package#";
    public static final String ID_TYPE = "#idType#";
    public static final String MODULE = "#module#";
    public static final String NAME_PROPERTY = "#nameProperty#";

    public static void createFile(String filePath,String content){
        File file = new File(filePath);
        try {
            if(!file.exists()){
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes(Charset.forName("UTF-8")));
            fos.flush();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String findLine(String all,String... keywords){
    	String[] lines = all.split("\n");
    	for(String line : lines){
    		Boolean isThis = Boolean.TRUE;
    		for(String keyword:keywords){
    			if(!line.contains(keyword)){
    				isThis = Boolean.FALSE;
    				break;
    			}
    		}
    		if(isThis){
    			return line;
    		}
    	}
    	return "";
    }
    
    public static String fileContent(String filePath) throws IOException{
    	String all="",line = "";
    	InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		while ((line = br.readLine()) != null) {
			all = all + line + "\n";
		}
		return all;
    }
    
    public static String fileContent(File file) throws IOException{
    	String all="",line = "";
    	InputStream is = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		while ((line = br.readLine()) != null) {
			all = all + line + "\n";
		}
		br.close();
		return all;
    }

    public static String replacePlaceholders(String src,Class<?> clazz){
        return replacePlaceholders(src,DesignEntity.resolve(clazz));
    }
    
    public static String replacePlaceholders(String src,DesignEntity entity){
        src = replaceGlobalUrl(src).replaceAll(CLASS_NAME,entity.clazzName)
                .replaceAll(INSTANCE_NAME, entity.instanceName)
                .replaceAll(TABLE_NAME, entity.tableName)
                .replaceAll(PACKAGE, entity.clazzPackage)
                .replaceAll(ID_TYPE, entity.idType)
                .replaceAll(MODULE, entity.moduleName)
                .replaceAll(NAME_PROPERTY, entity.nameProperty!=null?entity.nameProperty:"name");
        return src;
    }
    
	public static String replaceGlobalUrl(String all) {
		return all.replaceAll(ADD_URL, addUrl).replaceAll(EDIT_URL, editUrl)
				.replaceAll(REMOVE_URL, removeUrl).replaceAll(LIST_URL, listUrl)
				.replaceAll(PAGE_URL, pageUrl).replaceAll(VIEW_URL, viewUrl)
				.replaceAll(ADD_PAGE_URL, addPageUrl).replaceAll(EDIT_PAGE_URL, editPageUrl)
				.replaceAll(LIST_PAGE_URL, listPageUrl).replaceAll(VIEW_PAGE_URL, viewPageUrl)
				.replaceAll(DICT_URL, dictUrl);
	}
}
