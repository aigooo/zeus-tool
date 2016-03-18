package com.zeus.tool.cogen.annotation;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Input时的约束条件，与ViewElement配合使用
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InputElement {

    /**
     * 是否必须填写
     * @return
     */
    boolean required() default false;
    
    /**
     * 约定格式正则表达式
     * @return
     */
    String regex() default "";
    
    /**
     * 作为隐藏元素输入
     * @return
     */
    boolean hidden() default false;
    
    /**
     * 最大长度
     * @return
     */
    int maxLength() default Integer.MAX_VALUE;
    
    /**
     * 默认值
     * @return
     */
    String defaultValue() default "";
    
    /**
     * 最小长度
     * @return
     */
    int minLength() default 0;
    
    /**
     * @Desc 至少选中几个
     * @author xiazs
     * @createTime 2015年9月14日 下午5:29:17
     */
    int mincheck() default 0;
    
    VType vType() default VType.NONE;
    
    enum VType{
    	EMAIL,URL,DIGITS,NUMBER,NONE
    }
}
