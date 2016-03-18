package com.zeus.tool.cogen;

import java.util.Arrays;

import com.zeus.tool.cogen.design.DesignEntity;
import com.zeus.tool.cogen.entity.EntityGen;
import com.zeus.tool.cogen.hibernate.HibernateConfigGen;
import com.zeus.tool.cogen.hibernate.HibernateDaoGen;
import com.zeus.tool.cogen.hibernate.HibernateGen;
import com.zeus.tool.cogen.page.html.AddPageGen;
import com.zeus.tool.cogen.page.html.EditPageGen;
import com.zeus.tool.cogen.page.html.InRefPageGen;
import com.zeus.tool.cogen.page.html.ListPageGen;
import com.zeus.tool.cogen.page.html.RefPageGen;
import com.zeus.tool.cogen.page.html.ViewPageGen;
import com.zeus.tool.cogen.restful.RestfulActionGen;
import com.zeus.tool.cogen.spring.SpringGen;

public class GenUtil {

	static String projectRootPath;

	public static void init(String projectRootPath) {
		GenUtil.projectRootPath = projectRootPath;
	}

	public static void gen4AddPage(String action, Class<?>... clazzes) {
		switch (action) {
			case Cogen.CREATE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					AddPageGen.init().createClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.UPDATE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					AddPageGen.init().updateClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.UPCRTE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					AddPageGen.init().createOrUpdateClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.REMOVE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					AddPageGen.init().removeClass(projectRootPath, dEntity);
				});
				break;
			default:
				break;
		}
	}
	
	public static void gen4ListPage(String action, Class<?>... clazzes) {
		switch (action) {
			case Cogen.CREATE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					ListPageGen.init().createClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.UPDATE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					ListPageGen.init().updateClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.UPCRTE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					ListPageGen.init().createOrUpdateClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.REMOVE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					ListPageGen.init().removeClass(projectRootPath, dEntity);
				});
				break;
			default:
				break;
		}
	}
	
	public static void gen4EditPage(String action, Class<?>... clazzes) {
		switch (action) {
			case Cogen.CREATE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					EditPageGen.init().createClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.UPDATE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					EditPageGen.init().updateClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.UPCRTE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					EditPageGen.init().createOrUpdateClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.REMOVE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					EditPageGen.init().removeClass(projectRootPath, dEntity);
				});
				break;
			default:
				break;
		}
	}
	
	public static void gen4ViewPage(String action, Class<?>... clazzes) {
		switch (action) {
			case Cogen.CREATE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					ViewPageGen.init().createClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.UPDATE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					ViewPageGen.init().updateClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.UPCRTE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					ViewPageGen.init().createOrUpdateClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.REMOVE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					ViewPageGen.init().removeClass(projectRootPath, dEntity);
				});
				break;
			default:
				break;
		}
	}
	
	public static void gen4RefPage(String action, Class<?>... clazzes) {
		switch (action) {
			case Cogen.CREATE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					RefPageGen.init().createClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.UPDATE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					RefPageGen.init().updateClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.UPCRTE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					RefPageGen.init().createOrUpdateClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.REMOVE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					RefPageGen.init().removeClass(projectRootPath, dEntity);
				});
				break;
			default:
				break;
		}
	}
	
	public static void gen4InRefPage(String action, Class<?>... clazzes) {
		switch (action) {
			case Cogen.CREATE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					InRefPageGen.init().createClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.UPDATE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					InRefPageGen.init().updateClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.UPCRTE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					InRefPageGen.init().createOrUpdateClass(projectRootPath, dEntity);
				});
				break;
			case Cogen.REMOVE:
				Arrays.asList(clazzes).stream().forEach(clazz -> {
					DesignEntity dEntity = DesignEntity.resolve(clazz);
					InRefPageGen.init().removeClass(projectRootPath, dEntity);
				});
				break;
			default:
				break;
		}
	}

	public static void gen4Entity(String action, Class<?>... clazzes) {
		switch (action) {
		case Cogen.CREATE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				EntityGen.init().createClass(projectRootPath, dEntity);
			});
			break;
		case Cogen.UPDATE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				EntityGen.init().updateClass(projectRootPath, dEntity);
			});
			break;
		case Cogen.UPCRTE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				EntityGen.init().createOrUpdateClass(projectRootPath, dEntity);
			});
			break;
		case Cogen.REMOVE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				EntityGen.init().removeClass(projectRootPath, dEntity);
			});
			break;
		default:
			break;
		}
	}
	
	public static void gen4Action(String action, Class<?>... clazzes) {
		switch (action) {
		case Cogen.CREATE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				RestfulActionGen.init().createClass(projectRootPath, dEntity);
			});
			break;
		case Cogen.UPDATE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				RestfulActionGen.init().updateClass(projectRootPath, dEntity);
			});
			break;
		case Cogen.UPCRTE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				RestfulActionGen.init().createOrUpdateClass(projectRootPath, dEntity);
			});
			break;
		case Cogen.REMOVE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				RestfulActionGen.init().removeClass(projectRootPath, dEntity);
			});
			break;
		default:
			break;
		}
	}
	
	public static void gen4Service(String action, Class<?>... clazzes) {
		switch (action) {
		case Cogen.CREATE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				SpringGen.init().createClass(projectRootPath, dEntity);
			});
			break;
		case Cogen.UPDATE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				SpringGen.init().updateClass(projectRootPath, dEntity);
			});
			break;
		case Cogen.UPCRTE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				SpringGen.init().createOrUpdateClass(projectRootPath, dEntity);
			});
			break;
		case Cogen.REMOVE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				SpringGen.init().removeClass(projectRootPath, dEntity);
			});
			break;
		default:
			break;
		}
	}
	
	public static void gen4HibernateDao(String action,Class<?>... clazzes){
		switch (action) {
		case Cogen.CREATE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				HibernateGen.init().createClass(projectRootPath, dEntity);
				HibernateDaoGen.init().createClass(projectRootPath, dEntity);
				HibernateConfigGen.init().createClass(projectRootPath, dEntity);
			});
			break;
		case Cogen.UPDATE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				HibernateGen.init().updateClass(projectRootPath, dEntity);
				HibernateDaoGen.init().updateClass(projectRootPath, dEntity);
				HibernateConfigGen.init().updateClass(projectRootPath, dEntity);
			});
			break;
		case Cogen.UPCRTE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				HibernateGen.init().createOrUpdateClass(projectRootPath, dEntity);
				HibernateDaoGen.init().updateClass(projectRootPath, dEntity);
				HibernateConfigGen.init().updateClass(projectRootPath, dEntity);
			});
			break;
		case Cogen.REMOVE:
			Arrays.asList(clazzes).stream().forEach(clazz -> {
				DesignEntity dEntity = DesignEntity.resolve(clazz);
				HibernateGen.init().removeClass(projectRootPath, dEntity);
				HibernateDaoGen.init().updateClass(projectRootPath, dEntity);
				HibernateConfigGen.init().updateClass(projectRootPath, dEntity);
			});
			break;
		default:
			break;
		}
	}

}
