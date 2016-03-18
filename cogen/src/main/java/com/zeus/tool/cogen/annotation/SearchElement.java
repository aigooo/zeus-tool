package com.zeus.tool.cogen.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Desc 与ViewElement一起使用，约定页面的搜索方式
 * @author xiazs
 * @createTime 2015年9月14日 下午2:07:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SearchElement {

    /**
     * @Desc 是否是多选，精确
     * @author xiazs
     * @createTime 2015年9月14日 下午2:08:23
     */
    boolean multi() default false;
    
    /**
     * @Desc 是否是模糊查询
     * @author xiazs
     * @createTime 2015年9月14日 下午2:10:04
     */
    boolean like() default false;
    
    /**
     * @Desc 是否是范围查询
     * @author xiazs
     * @createTime 2015年9月14日 下午2:10:25
     */
    boolean updn() default false;
    
    /**
     * @Desc 若是List<对象>类型的搜索字段，指定可用于搜索的属性名称
     * @return
     */
    String property() default "";
    
    /**
     * 是否在页面上显示为查询条件
     * @return
     */
    boolean filter() default false;
}
