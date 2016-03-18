package com.zeus.tool.cogen.hibernate;

public interface HibernatePorperties {

	public String ID_BLOCK = "<!--#idBlock#-->";
    public String PROPERTY_BLOCK = "<!--#propertyBlock#-->";
    public String PARAM = "#PARAM#";
    public String PARAM_KEY = "#paramKey#";
    public String PARAM_VALUE = "#paramValue#";
    public String PROPERTY_TYPE = "#PROPERTY_TYPE#";
    public String ID_COLUMN = "#idColumn#";
    public String FIELD_ID_COLUMN = "#fieldIdColumn#";
    public String STORE = "#store#";
    public String ERROR_PROPERTY = "<!--#fieldName# 无法配置，请查证-->\n" + PROPERTY_BLOCK;
    public String SEARCH_BLOCK = "//#searchBlock#";
    public String GETTER = "#getter#";
    public String UPGETTER = "#upgetter#";
    public String DNGETTER = "#dngetter#";
    public String FIELDCLASS = "#fieldClass#";
    public String FIELDNAME= "#fieldName#";
    public String OBJECT_NOT_NULL = "#objectNotNull#";

    public String many_to_one =
            "\t\t<many-to-one name=\"#fieldName#\" class=\"#fieldType#\" column=\"#fieldColumn#\" lazy=\"#fieldLazy#\"/>\n" +
                    PROPERTY_BLOCK;
    
    public String one_to_many =
            "\t\t<bag name=\"#fieldName#\" lazy=\"#fieldLazy#\" inverse=\"true\">\n" +
            "\t    \t<key column=\"#fieldColumn#\"/>\n" +
            "\t    \t<one-to-many class=\"#genericFieldType#\"/>\n" +
            "\t    </bag>\n" +
            		PROPERTY_BLOCK;
    
    public String many_to_many = 
    		"\t\t<bag name=\"#fieldName#\" table =\"#store#\">\n" + 
			    "\t\t\t<key column=\"#idColumn#\"/>\n" + 
			    "\t\t\t<many-to-many column=\"#fieldColumn#\" class=\"#genericFieldType#\"/>\n" + 
			"\t\t</bag>\n" + 
					PROPERTY_BLOCK;

    public String property = "\t\t<property name=\"#fieldName#\" column=\"#fieldColumn#\" #PROPERTY_TYPE#/>\n" +
            PROPERTY_BLOCK;

    public String property_type = "type=\"#fieldType#\"";
    public String property_class = "class=\"#fieldType#\"";

    public String idConfig =
            "\t\t<id name=\"id\" type=\"#idType#\">\n" +
            "\t\t\t<column name=\"#fieldColumn#\"/>\n" +
            "\t\t\t<generator class=\"#generator#\" >\n" +
            "\t\t\t\t#PARAM#\n" +
            "\t\t\t</generator>\n" +
            "\t\t</id>";

    public String param =
            "<param name=\"#paramKey#\">#paramValue#</param>";
    
    public String porperty_isDelete = "\t\t<property name=\"isDelete\" column=\"isDelete\"/>\n" +
            PROPERTY_BLOCK;
    
    public String search_block_string_equal =
            "\t\t\tif(#objectNotNull#&&StringUtils.isNotBlank(#instanceName#.#getter#())){\n" +
                    "\t\t\t\tqueryCondition.append(\" AND t.#fieldName# = ?\");\n" +
            		"\t\t\t\tqueryCondition.param(#instanceName#.#getter#());\n" + 
                    "\t\t\t}\n" + SEARCH_BLOCK;

    public String search_block_string_like =
            "\t\t\tif(#objectNotNull#&&StringUtils.isNotBlank(#instanceName#.#getter#())){\n" +
                    "\t\t\t\tqueryCondition.append(\" AND t.#fieldName# like ?\");\n" +
                    "\t\t\t\tqueryCondition.param(\"%\"+#instanceName#.#getter#()+\"%\");\n" + 
                    "\t\t\t}\n" + SEARCH_BLOCK;

    public String search_block_in =
            "\t\t\tif(#objectNotNull#&&StringUtils.isNotBlank(#instanceName#.#getter#s())){\n" +
                    "\t\t\t\tqueryCondition.append(\" AND t.#fieldName# in (?) \");\n" +
                    "\t\t\t\tqueryCondition.param(#instanceName#.#getter#());\n" + 
                    "\t\t\t}\n"+ SEARCH_BLOCK;

    public String search_block_string_rang =
            "\t\t\tif(#objectNotNull#&&StringUtils.isNotBlank(#instanceName#.#upgetter#())){\n" +
                    "\t\t\t\tqueryCondition.append(\" and t.#fieldName# < ?\");\n"+ 
                    "\t\t\t\tqueryCondition.param(#instanceName#.#upgetter#());\n" + 
                    "\t\t\t}\n" +
                    "\t\t\tif(StringUtils.isNotBlank(#instanceName#.#dngetter#())){\n" +
                    "\t\t\t\tqueryCondition.append(\" and t.#fieldName# >= ?\");\n" + 
                    "\t\t\t\tqueryCondition.param(#instanceName#.#dngetter#());\n" + 
                    "\t\t\t}\n"
                    + SEARCH_BLOCK;

    public String search_block_other_equal =
            "\t\t\tif(#objectNotNull#&&#instanceName#.#getter#()!=null){\n" +
                    "\t\t\t\tqueryCondition.append(\" AND t.#fieldName# = ?\");\n" +
                    "\t\t\t\tqueryCondition.param(#instanceName#.#getter#());\n" + 
                    "\t\t\t}\n"+ SEARCH_BLOCK;

    public String search_block_other_rang =
            "\t\t\tif(#objectNotNull#&&#instanceName#.#upgetter#()!=null){\n" +
                    "\t\t\t\tqueryCondition.append(\" and t.#fieldName# < ?\");\n"+ 
                    "\t\t\t\tqueryCondition.param(#instanceName#.#upgetter#());\n" + 
                    "\t\t\t}\n" +
                    "\t\t\tif(#objectNotNull#&&#instanceName#.#dngetter#()!=null){\n" +
                    "\t\t\t\tqueryCondition.append(\" and t.#fieldName# >= ?\");\n"+ 
                    "\t\t\t\tqueryCondition.param(#instanceName#.#dngetter#());\n" + 
                    "\t\t\t}\n"
                    + SEARCH_BLOCK;
}
