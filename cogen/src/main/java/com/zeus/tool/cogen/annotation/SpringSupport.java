package com.zeus.tool.cogen.annotation;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SpringSupport {
	
    String version() default "";
    
    Integration[] integrations() default {};
    
    enum Integration{
        Hibernate,Struts,Mybatis,SpringMVC
    }
    
}
