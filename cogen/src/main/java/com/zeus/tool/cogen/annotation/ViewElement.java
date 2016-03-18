package com.zeus.tool.cogen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 显示空间
 * @author xiazs
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ViewElement {

	/**
	 * Element的显示名称
	 * @return
	 */
    String label();
    
    /**
     * Element的提示
     * @return
     */
    String title() default "";
    
    ViewType viewType();
    
    /**
     * @Desc 以此Element来表示该对象的常用属性（在list中显示该属性），在任何场合都显示该属性
     * @author xiazs
     * @createTime 2015年9月17日 下午4:30:07
     */
    boolean inlist() default true;
    
    /**
     * @Desc  以此Element来表示该对象的显示名称
     * @author xiazs
     * @createTime 2015年9月18日 上午11:01:52
     */
    boolean name() default false;

    /**
     * 如果是可选值，可选值来源
     * @return
     */
    ValuesForm valuesForm() default ValuesForm.NONE;
    
    /**
     * 获取值的key
     * @return
     */
    String valuesGetter() default "";
    
    /**
     * 提交到后台的名称
     * @return
     */
    String valuesRef() default "";


    public enum ViewType{
    	input_text,password,input_radio,input_checkbox,input_tags,select_search,
    	select_multi,datepicker,timepicker,textarea,spinner,colorpicker,slider,treepicker,
    	file,picture
    }

    public enum ValuesForm{
        DICTIONARY,MAP,ENUM,NONE
    }
}