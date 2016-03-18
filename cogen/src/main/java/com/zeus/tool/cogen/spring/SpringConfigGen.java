package com.zeus.tool.cogen.spring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

import com.zeus.tool.cogen.FileUtil;
import com.zeus.tool.cogen.design.DesignEntity;

public class SpringConfigGen implements SpringProperties {
	
	private static DesignEntity dEntity;
	
	public static void doGenerate(String version,String rootPath,Class<?> clazz){
		doGenerate(version,rootPath,DesignEntity.resolve(clazz));
    }
	
	public static void doGenerate(String version,String rootPath,DesignEntity entity){
		dEntity = entity;
        generateConfig(rootPath + "src/main/resources/");
        generateSpringConfig(rootPath + "src/main/resources/");
    }

	@SuppressWarnings("resource")
	public static void generateSpringConfig(String rootPath) {
		try {
			String springConfigPath = rootPath + "applicationContext.xml";
			String moduleConfig = "spring-" + dEntity.moduleName + ".xml";

			File file = new File(springConfigPath);

			String line, all = "";
			if (!file.exists()) {
				InputStream is = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("applicationContext.xml.template");
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				while ((line = br.readLine()) != null) {
					all = all + line + "\n";
				}
				if (!all.contains(moduleConfig)) {
					String spring_module = "<import resource=\"classpath:config/spring/" + moduleConfig + "\"/>\n"
							+ IMPORT;
					all = all.replaceAll(IMPORT, spring_module);
				}
				all = FileUtil.replacePlaceholders(all, dEntity);
				FileUtil.createFile(springConfigPath, all);

			} else {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(new FileInputStream(springConfigPath), "UTF-8"));
				while ((line = br.readLine()) != null) {
					all = all + line + "\n";
				}
				if (!all.contains(moduleConfig)) {
					String spring_module = "<import resource=\"classpath:config/spring/" + moduleConfig + "\"/>\n"
							+ IMPORT;
					all = all.replaceAll(IMPORT, spring_module);
				}
				all = FileUtil.replacePlaceholders(all, dEntity);
				FileUtil.createFile(springConfigPath, all);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static void generateConfig(String rootPath) {
		try {
			String springConfigPath = rootPath + "config/spring/spring-" + dEntity.moduleName + ".xml";
			String instanceName = dEntity.instanceName;

			File file = new File(springConfigPath);
			String line, all = "";
			if (!file.exists()) { // 如果文件不存在，新建文件并配置文件
				InputStream is = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("spring-#module#.xml.template");
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				while ((line = br.readLine()) != null) {
					all = all + line + "\n";
				}

				all = all.replaceAll(BEAN, bean);
				all = generateServiceConfig(all);

				if (dEntity.strutsSupport)
					all = generateStrutsActionConfig(all);

				if (dEntity.hibernateSupport)
					all = generateHibernateDaoConfig(all);

				all = FileUtil.replacePlaceholders(all, dEntity);
				FileUtil.createFile(springConfigPath, all);
			} else {// 如果文件存在，判断文件中是否有该class的配置
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				while ((line = br.readLine()) != null) {
					all = all + line + "\n";
				}

				if (!all.contains("id=\"" + instanceName + "Service\"")) {
					all = all.replaceAll(BEAN, bean);
				}

				all = generateServiceConfig(all);

				if (dEntity.strutsSupport)
					all = generateStrutsActionConfig(all);

				if (dEntity.hibernateSupport)
					all = generateHibernateDaoConfig(all);
				
				all = FileUtil.replacePlaceholders(all, dEntity);
				FileUtil.createFile(springConfigPath, all);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String generateStrutsActionConfig(String all) {
		String instanceName = dEntity.instanceName;
		String actionClassName = dEntity.clazzPackage + ".action." + dEntity.clazzName + "Action";

		if (all.contains("id=\"" + instanceName + "Action\"")) {
			String oldConfig, newConfig;
			oldConfig = all.substring(all.indexOf("<bean id=\"" + instanceName + "Action\""), all.length());
			oldConfig = oldConfig.substring(0, oldConfig.indexOf("</bean>") + 7);
			try {
				Class<?> actionClass = Class.forName(actionClassName);
				if (actionClass != null) {
					Field[] fields = actionClass.getFields();
					newConfig = oldConfig;
					for (Field field : fields) {
						if (field.getName().endsWith("Service") || field.getName().endsWith("Dao")) {
							if (!oldConfig.contains("<property value=\"" + field.getName() + "\"")) {
								String property = "<property value=\"" + field.getName() + "\" ref=\"" + field.getName()
										+ "\">\n\t\t\t" + BEAN_ACTION_PROPERTY;
								newConfig = newConfig.replaceAll(BEAN_ACTION_PROPERTY, property);
							}
						}
					}
					all = all.replaceAll(oldConfig, newConfig);
				}
			} catch (ClassNotFoundException e) {
			}
		} else {
			all = all.replaceAll(SPRING_STRUTS, bean_struts);
		}
		return all;
	}

	private static String generateServiceConfig(String all) {
		String instanceName = dEntity.instanceName;
		String serviceClassName = dEntity.clazzPackage + ".service.impl." + dEntity.clazzName + "ServiceImpl";

		if (all.contains("id=\"" + instanceName + "Service\"")) {
			String oldConfig, newConfig;
			oldConfig = all.substring(all.indexOf("<bean id=\"" + instanceName + "Service\""), all.length());
			oldConfig = oldConfig.substring(0, oldConfig.indexOf("</bean>") + 7);
			try {
				Class<?> actionClass = Class.forName(serviceClassName);
				if (actionClass != null) {
					Field[] fields = actionClass.getDeclaredFields();
					newConfig = oldConfig;
					for (Field field : fields) {
						if (field.getName().endsWith("Service") || field.getName().endsWith("Dao")) {
							if (!oldConfig.contains("<property name=\"" + field.getName() + "\"")) {
								String property = "<property name=\"" + field.getName() + "\" ref=\"" + field.getName()
										+ "\"/>\n\t\t\t" + BEAN_SERVICE_PROPERTY;
								newConfig = newConfig.replaceAll(BEAN_SERVICE_PROPERTY, property);
							}
						}
					}
					all = all.replaceAll(oldConfig, newConfig);
				}
			} catch (ClassNotFoundException e) {

			}
		} else {
			all = all.replaceAll(SPRING_SERVICE, bean_service);
		}
		return all;
	}

	private static String generateHibernateDaoConfig(String all) {
		String instanceName = dEntity.instanceName;
		String daoClassName = dEntity.clazzPackage + ".dao.impl." + dEntity.clazzName + "DaoImpl";

		if (all.contains("id=\"" + instanceName + "Dao\"")) {
			String oldConfig, newConfig;
			oldConfig = all.substring(all.indexOf("<bean id=\"" + instanceName + "Dao\""), all.length());
			oldConfig = oldConfig.substring(0, oldConfig.indexOf("</bean>") + 7);
			try {
				Class<?> actionClass = Class.forName(daoClassName);
				if (actionClass != null) {
					Field[] fields = actionClass.getFields();
					newConfig = oldConfig;
					for (Field field : fields) {
						if (field.getName().endsWith("Dao")) {
							if (!oldConfig.contains("<property name=\"" + field.getName() + "\"")) {
								String property = "<property name=\"" + field.getName() + "\" ref=\"" + field.getName()
										+ "\">\n\t\t\t" + BEAN_DAO_PROPERTY;
								newConfig = newConfig.replaceAll(BEAN_DAO_PROPERTY, property);
							}
						}
					}
					all = all.replaceAll(oldConfig, newConfig);
				}
			} catch (ClassNotFoundException e) {
			}
		} else {
			all = all.replaceAll(SPRING_HIBERNATE, bean_hibernate);
		}
		return all;
	}

}
