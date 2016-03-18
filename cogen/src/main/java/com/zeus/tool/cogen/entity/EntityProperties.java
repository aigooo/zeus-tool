package com.zeus.tool.cogen.entity;

public interface EntityProperties {

	public final static String IMPORTS = "//import";
	public final static String IMPLEMENTS = "//implement";
	public final static String ATTRIBUTES = "//attribute";
	public final static String GETTERANDSETTER = "//getterAndSetter";
	
	public final static String attribute = "\tprivate #fieldType# #fieldName#;\n" + ATTRIBUTES;
	public final static String onlyAttribute = "\tprivate #fieldType# #fieldName#;\n";
	
	public final static String getterAndSetter = 
			"\tpublic #fieldType# #getter#(){\n" + 
			"\t\treturn #fieldName#;\n" + 
			"\t}\n" + 
			"\tpublic void #setter#(#fieldType# #fieldName#){\n" + 
			"\t\tthis.#fieldName# = #fieldName#;\n" + 
			"\t}\n" + GETTERANDSETTER;
	
	public final static String complexSetterAndSetter = 
			"\tpublic #fieldType# #getter#(){\n" + 
			"\t\tString fieldIds = \"\";\n" + 
			"\t\tfor(#fieldType# m:#fieldName#s){\n" + 
			"\t\t	fieldIds = fieldIds + m.getId() + \",\";\n" + 
			"\t\t}\n" + 
			"\t\tif(fieldIds.length()>0){\n" + 
			"\t\t	fieldIds = fieldIds.substring(0, fieldIds.length()-1);\n" + 
			"\t\t	if(#fieldName#==null){\n" + 
			"\t\t		#fieldName# = new #fieldType#();\n" + 
			"\t\t		#fieldName#.setIds(fieldIds);\n" + 
			"\t\t	}\n" + 
			"\t\t}\n" + 
			"\t\treturn #fieldName#;\n" + 
			"\t}\n" + 
			"\tpublic void #setter#(#fieldType# #fieldName#){\n" + 
			"\t\tthis.#fieldName# = #fieldName#;\n" + 
			"\t\tif(StringUtils.isNotBlank(#fieldName#.getIds())){\n" + 
			"\t\t	String[] fieldIds = #fieldName#.getIds().split(\",\");\n" + 
			"\t\t	#fieldName#s = new ArrayList<>();\n" + 
			"\t\t	for(String fieldId:fieldIds){\n" + 
			"\t\t		#fieldType# m = new #fieldType#();\n" + 
			"\t\t		m.setId(Long.parseLong(fieldId));\n" + 
			"\t\t		#fieldName#s.add(m);\n" + 
			"\t\t	}\n" + 
			"\t\t}\n" + 
			"\t}\n" + GETTERANDSETTER;
	
	public final static String remoteGetterAndSetter = 
			"\tpublic #fieldType# #getter#(){\n" + 
			"\t\tthis.#fieldName# = JsonUtil.parseObject(#fieldName#Json,#fieldType#.class);\n" +
			"\t\treturn this.#fieldName#;\n" + 
			"\t}\n" + 
			"\tpublic void #setter#(#fieldType# #fieldName#){\n" + 
			"\t\tthis.#fieldName#Json = JsonUtil.toJSONString(#fieldName#);\n" + 
			"\t\tthis.#fieldName# = #fieldName#;\n" + 
			"\t}\n" + GETTERANDSETTER;
	
	public final static String onlyGetterAndSetter = 
			"\tpublic #fieldType# #getter#(){\n" + 
			"\t\treturn #fieldName#;\n" + 
			"\t}\n" + 
			"\tpublic void #setter#(#fieldType# #fieldName#){\n" + 
			"\t\tthis.#fieldName# = #fieldName#;\n" + 
			"\t}\n";
	
	public final static String deleteAttribute = "\tprivate Boolean isDelete = Boolean.FALSE;\n" + ATTRIBUTES;
	
	public final static String deleteGetterAndSetter = 
			"\tpublic Boolean getIsDelete(){\n" + 
			"\t\treturn isDelete;\n" + 
			"\t}\n" + 
			"\tpublic void setIsDelete(Boolean isDelete){\n" + 
			"\t\tif(isDelete!=null)\n" + 
			"\t\t\tthis.isDelete=isDelete;\n" + 
			"\t\telse this.isDelete=Boolean.FALSE;\n" + 
			"\t}\n" + GETTERANDSETTER ;
}
