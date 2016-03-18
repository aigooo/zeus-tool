package com.zeus.tool.cogen.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Desc 引用其他对象，不与ViewElement联合使用
 * @createTime 2015年9月14日 下午2:07:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RefElement {
	
	boolean multi() default false;
	
	String refEName();
	
    String label();
    
    String title() default "";
    
    /**
     * 该引用字段是否以select方式来显示
     * @return
     */
    boolean select() default true;
    
	/**
	 * @Desc 级联的name字段
	 * @author xiazs
	 * @createTime 2015年9月14日 下午2:14:36
	 */
    String cascadeProperty() default "";
    
    /**
     * 针对one-to-many字段，控制权交实体one一方
     * @return
     */
    boolean toManyInverse() default false; 
	
}
