package com.zeus.tool.cogen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Desc 远程引用对象，从其他系统引入数据，不与ViewElement联合使用
 * @createTime 2015年9月14日 下午2:07:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RemoteElement {

	boolean multi() default false;
	
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
    
    String valuesUrl();
}
