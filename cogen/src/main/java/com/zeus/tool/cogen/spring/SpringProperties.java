package com.zeus.tool.cogen.spring;

public interface SpringProperties {
	
	public final static String BEAN = "<!-- bean -->";

    public final static String SPRING_MVC = "<!-- spring mvc -->";
    public final static String SPRING_STRUTS = "<!-- spring struts -->";
    public final static String SPRING_SERVICE = "<!-- spring service -->";
    public final static String SPRING_HIBERNATE = "<!-- spring hibernate -->";
    public final static String SPRING_MYBATIS = "<!-- spring mybatis -->";
    public final static String BEAN_ACTION_PROPERTY = "<!-- bean action property -->";
    public final static String BEAN_SERVICE_PROPERTY = "<!-- bean service property -->";
    public final static String BEAN_DAO_PROPERTY = "<!-- bean dao property -->";
    
    public final static String IMPORT = "<!-- import -->";

    public final static String bean =
            "\t\t<!-- spring struts -->\n" +
            "\t\t<!-- spring mvc -->\n" +
            "\t\t<!-- spring service -->\n" +
            "\t\t<!-- spring hibernate -->\n" +
            "\t\t<!-- spring mybatis -->\n\n" +BEAN;

    public final static String bean_struts =
            "<bean id=\"#instanceName#Action\" class=\"#package#.action.#clazzName#Action\" scope=\"prototype\">\n" +
                    "\t\t\t<property name=\"#instanceName#Service\" ref=\"#instanceName#Service\"/>\n" +
                    "\t\t\t<!-- bean action property -->\n" +
                    "\t\t</bean>";

    public final static String bean_service =
            "<bean id=\"#instanceName#Service\" class=\"#package#.service.impl.#clazzName#ServiceImpl\">\n" +
                    "\t\t\t<property name=\"#instanceName#Dao\" ref=\"#instanceName#Dao\"/>\n" +
                    "\t\t\t<!-- bean service property -->\n" +
                    "\t\t</bean>";

    public final static String bean_hibernate =
            "<bean id=\"#instanceName#Dao\" class=\"#package#.dao.impl.#clazzName#DaoImpl\">\n" +
                    "\t\t\t<property name=\"sessionFactory\" ref=\"sessionFactory\"/>\n" +
                    "\t\t\t<!-- bean dao property -->\n" +
                    "\t\t</bean>";

}
