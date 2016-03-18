package com.zeus.tool.cogen.page.html.beyond;

/**
 * 针对新建页面和编辑页面的字段录入
 * @author xiazs
 */
public interface FormInputs {
	public String REF_JS = "<!--ref_js-->";  //script引入js占位索引
	public String BUILD_JS = "<!--build_js-->";    //构建输入框js占位索引
	public String INIT_JS = "<!--init_js-->";      //可选值初始化js占位索引
	public String DATA_INIT_JS = "<!--fill_data_js-->";  //原有值初始化js占位索引
	public String INPUT_REF_MODEL = "<!--input_ref_model-->";   //针对弹出的属性元素，隐藏model的占位索引
	public String REF_ELEMENT_LIST = "<!--ref_element_list-->";  //针对toMany属性元素，展现列表的占位索引
	public String FORM_GROUP = "<!--form_group-->";    //form表单一行的位置
	public String ONE_ITEM = "<!--one_item-->";        //form表单一个输入框的位置
	
	/*引入JS文件--------------开始-------------------*/
	public String ref_js_select = "\t<script src='../../assets/js/select2/select2.js'></script>\n" + REF_JS;
	public String ref_js_tagsinput = "\t<script src='../../assets/js/tagsinput/bootstrap-tagsinput.js'></script>\n" + REF_JS;
	public String ref_js_datepicker = 
			"\t<link href='../../datetimepicker/css/bootstrap-datetimepicker.css' rel='stylesheet' />\n" + 
			"\t<script src='../../datetimepicker/js/bootstrap-datetimepicker.js'></script>\n"  +
			"\t<script src='../../datetimepicker/js/bootstrap-datetimepicker.zh-CN.js'></script>\n"  + REF_JS;
	public String ref_js_timepicker = "\t<script src='../../assets/js/datetime/bootstrap-timepicker.js'></script>\n" + REF_JS;
	public String ref_js_textarea = "\t<script src='../../assets/js/textarea/jquery.autosize.js'></script>\n"+ REF_JS;
	public String ref_js_spinner = "\t<script src='../../assets/js/fuelux/spinner/fuelux.spinner.min.js'></script>\n"+ REF_JS;
	public String ref_js_colorpicker = "\t<script src='../../assets/js/colorpicker/jquery.minicolors.js'></script>\n"+ REF_JS;
	public String ref_js_ref = "\t<script src='../../assets/js/bootbox/bootbox.js'></script>\n" + REF_JS;
	/*引入JS文件--------------结束-------------------*/

	public String form_group = 
			"\t\t\t\t\t\t\t<div class='form-group'>\n" + 
			"<!--one_item-->\n" + 
			"\t\t\t\t\t\t\t</div>\n"  + FORM_GROUP;
	
	/*输入框--------------------开始------------------------*/
	public String input_ref_btn = 
			"\t\t\t\t\t\t\t<label for='#fieldLabel#' class='col-sm-2 control-label no-padding-right label-title '>#fieldLabel#</label>\n" +
			"\t\t\t\t\t\t\t<div class='col-sm-9'>\n" + 
			"\t\t\t\t\t\t\t<a class=\"btn btn-darkorange btn-sm\"  id=\"#fieldId#\">点击选择#fieldLabel#</a>  <span id='#fieldId#_span'></span>\n" + 
            "\t\t\t\t\t\t\t</div>\n";
	
	public String input_ref_model = "\t<div id='#fieldId#_model' style='display: none;'><iframe id='#fieldId#_iframe' " + 
			"frameborder='no' src='' style='width: 100%; height: 500px;'></iframe></div>\n" + INPUT_REF_MODEL;
	
	public String input_text = 
			"\t\t\t\t\t\t\t<label for='#fieldLabel#' class='col-sm-2 control-label no-padding-right label-title '>#fieldLabel#</label>\n" + 
			"\t\t\t\t\t\t\t<div class='col-sm-9'>\n" + 
            "\t\t\t\t\t\t\t	<span class='input-icon icon-right'>\n" + 
            "\t\t\t\t\t\t\t		<input type='text'  id='#fieldId#' class='form-control' name='#instanceName#.#fieldName#' placeholder='#fieldHolder#'>\n" + 
            "\t\t\t\t\t\t\t		<i class='fa #faIcon# #faColor# circular'></i>\n" + 
            "\t\t\t\t\t\t\t	</span>\n" + 
            "\t\t\t\t\t\t\t</div>\n";
	
	public String input_radio = 
			"\t\t\t\t\t\t\t<label for='#fieldLabel#' class='col-sm-2 control-label no-padding-right label-title '>#fieldLabel#</label>\n" + 
			"\t\t\t\t\t\t\t<div class='col-sm-9'>\n" + 
			"\t\t\t\t\t\t\t\t<div class='radio' id='#fieldId#'>\n" + 
			"\t\t\t\t\t\t\t\t</div>\n" + 
			"\t\t\t\t\t\t\t</div>\n";
	
	public String input_checkbox = 
			"\t\t\t\t\t\t\t<label for='#fieldLabel#' class='col-sm-2 control-label no-padding-right label-title '>#fieldLabel#</label>\n" + 
			"\t\t\t\t\t\t\t<div class='col-sm-9'>" + 
			"\t\t\t\t\t\t\t	<div class='checkbox' id='#fieldId#'>\n" + 
			"\t\t\t\t\t\t\t	</div>\n" + 
			"\t\t\t\t\t\t\t</div>\n";
	
	public String select_search = 
			"\t\t\t\t\t\t\t<label for='#fieldLabel#' class='col-sm-2 control-label no-padding-right label-title  '>#fieldLabel#</label>\n" + 
			"\t\t\t\t\t\t\t<div class='col-sm-9'>\n" + 
			"\t\t\t\t\t\t\t	<select  id='#fieldId#' name='#instanceName#.#fieldName#' style='width:100%;'>\n" + 
			"\t\t\t\t\t\t\t	</select>\n" + 
			"\t\t\t\t\t\t\t</div>\n";
	
	public String select_search_ref = 
			"\t\t\t\t\t\t\t<label for='#fieldLabel#' class='col-sm-2 control-label no-padding-right label-title  '>#fieldLabel#</label>\n" + 
			"\t\t\t\t\t\t\t<div class='col-sm-9'>\n" + 
			"\t\t\t\t\t\t\t	<select  id='#fieldId#' name='#refEName#' style='width:100%;'>\n" + 
			"\t\t\t\t\t\t\t	</select>\n" + 
			"\t\t\t\t\t\t\t</div>\n";
	
	public String select_multi = 
			"\t\t\t\t\t\t\t<label for='#fieldLabel#' class='col-sm-2 control-label no-padding-right label-title '>#fieldLabel#</label>\n" + 
			"\t\t\t\t\t\t\t<div class='col-sm-9'>\n" + 
			"\t\t\t\t\t\t\t	<select  id='#fieldId#' name='#instanceName#.#fieldName#'  multiple='multiple' style='width: 100%;;'>\n" + 
			"\t\t\t\t\t\t\t	</select>\n" + 
			"\t\t\t\t\t\t\t</div>\n";
	
	public String select_multi_ref = 
			"\t\t\t\t\t\t\t<label for='#fieldLabel#' class='col-sm-2 control-label no-padding-right label-title '>#fieldLabel#</label>\n" + 
			"\t\t\t\t\t\t\t<div class='col-sm-9'>\n" + 
			"\t\t\t\t\t\t\t	<select  id='#fieldId#' name='#refEName#' multiple='multiple' style='width: 100%;;'>\n" + 
			"\t\t\t\t\t\t\t	</select>\n" + 
			"\t\t\t\t\t\t\t</div>\n";
	
	public String datepicker = 
			"\t\t\t\t\t\t\t<label for='#fieldLabel#' class='col-sm-2 control-label no-padding-right label-title '>#fieldLabel#</label>\n" + 
			"\t\t\t\t\t\t\t<div class='col-sm-9'>\n" + 
			"\t\t\t\t\t\t\t	<div class='input-group'>\n" + 
			"\t\t\t\t\t\t\t		<input  class='form-control date-picker' id='#fieldId#' name='#instanceName#.#fieldName#' id='#fieldId#' type='text' data-date-format='#fieldFormate#'>\n" + 
	        "\t\t\t\t\t\t\t		<span class='input-group-addon'>\n"  + 
            "\t\t\t\t\t\t\t			<i class='fa fa-calendar'></i>\n"  + 
	        "\t\t\t\t\t\t\t		</span>\n" +
		    "\t\t\t\t\t\t\t	</div>\n" + 
		    "\t\t\t\t\t\t\t</div>\n";
	
	public String input_tags = 
			"\t\t\t\t\t\t\t<label for='#fieldLabel#' class='col-sm-2 control-label no-padding-right label-title '>#fieldLabel#</label>\n" + 
			"\t\t\t\t\t\t\t<div>\n"+
            "\t\t\t\t\t\t\t	<input  type='text' name='#instanceName#.#fieldName#' data-role='tagsinput' placeholder='#fieldHolder#' />\n"+
            "\t\t\t\t\t\t\t</div>\n" ;
	
	public String timepicker = 
			"\t\t\t\t\t\t\t<label for='#fieldLabel#' class='col-sm-2 control-label no-padding-right label-title '>#fieldLabel#</label>\n" + 
			"\t\t\t\t\t\t\t<div class='col-sm-9'>\n" + 
			"\t\t\t\t\t\t\t	<div class='input-group'>\n" + 
            "\t\t\t\t\t\t\t		<input  class='form-control' id='#fieldId#' name='#instanceName#.#fieldName#' type='text'>\n" + 
            "\t\t\t\t\t\t\t		<span class='input-group-addon'>\n" + 
            "\t\t\t\t\t\t\t			<i class='fa fa-clock-o'></i>\n" + 
            "\t\t\t\t\t\t\t		</span>\n" + 
            "\t\t\t\t\t\t\t	</div>\n" + 
            "\t\t\t\t\t\t\t</div>\n";
	
	public String textarea = 
			"\t\t\t\t\t\t\t<label for='#fieldLabel#' class='col-sm-2 control-label no-padding-right label-title '>#fieldLabel#</label>\n" + 
			"\t\t\t\t\t\t\t<div class='col-sm-9'>\n" + 
			"\t\t\t\t\t\t\t	<textarea  name='#instanceName#.#fieldName#' class='form-control' id='#fieldId#' placeholder='#fieldHolder#'></textarea>\n" + 
            "\t\t\t\t\t\t\t</div>\n";
	
	public String spinner = 
			"\t\t\t\t\t\t\t<label for='#fieldLabel#' class='col-sm-2 control-label no-padding-right label-title '>#fieldLabel#</label>\n" + 
			"\t\t\t\t\t\t\t<div class='spinner'>\n" + 
            "\t\t\t\t\t\t\t	<input type='text'  name='#instanceName#.#fieldName#' class='spinner-input form-control'>\n" + 
            "\t\t\t\t\t\t\t	<div class='spinner-buttons	btn-group btn-group-vertical'>\n" + 
            "\t\t\t\t\t\t\t		<button type='button' class='btn spinner-up blue'>\n" + 
            "\t\t\t\t\t\t\t			<i class='fa fa-chevron-up'></i>\n" + 
            "\t\t\t\t\t\t\t		</button>\n" + 
            "\t\t\t\t\t\t\t		<button type='button' class='btn spinner-down danger'>\n" + 
            "\t\t\t\t\t\t\t			<i class='fa fa-chevron-down'></i>\n" + 
            "\t\t\t\t\t\t\t		</button>\n" + 
            "\t\t\t\t\t\t\t	</div>\n" + 
            "\t\t\t\t\t\t\t</div>\n";
	
	public String colorpicker = 
			"\t\t\t\t\t\t\t<label for='#fieldLabel#' class='col-sm-2 control-label no-padding-right label-title '>#fieldLabel#</label>\n" + 
			"\t\t\t\t\t\t\t<div class='col-sm-9'>\n" + 
            "\t\t\t\t\t\t\t	<input type='text'  id='text-field' class='form-control colorpicker' value='#70c24a'>\n" + 
			"\t\t\t\t\t\t\t</div>\n";
	
	public String slider = "";
	
	public String treepicker = "";
	
	public String file = "";
	
	public String picture = "";
	/*输入框--------------------结束------------------------*/
	
	/*构建输入框JS--------------------开始------------------------*/
	
	public String select_search_build_js = "\t\t\t$('##fieldId#').select2();\n" + BUILD_JS;
	
	public String select_multi_build_js = "\t\t\t$('##fieldId#').select2({placeholder: '#fieldHolder#',allowClear: true});\n" + BUILD_JS;
	
	public String datepicker_build_js = 
			"\t\t\t$('.date-picker').datetimepicker({\n" + 
			"\t\t\t	language:  'zh-CN',\n" + 
			"\t\t\t	weekStart: 1,\n" + 
			"\t\t\t	todayBtn:  1,\n" + 
			"\t\t\t	autoclose: 1,\n" + 
			"\t\t\t	todayHighlight: 1,\n" + 
			"\t\t\t	startView: 2,\n" + 
			"\t\t\t	minView: 2,\n" + 
			"\t\t\t	forceParse: 0\n" + 
			"\t\t\t});\n" + BUILD_JS;
	
	public String timepicker_build_js = "\t\t\t$('##fieldId#').timepicker();\n" + BUILD_JS;
	
	public String textarea_build_js = "\t\t\t$('##fieldId#').autosize({ append: '\\n' });\n"  + BUILD_JS;
	
	public String spinner_build_js = "\t\t\t$('.spinner').spinner();\n"  + BUILD_JS;
	
	public String colorpicker_build_js = 
			"\t\t\t$('.colorpicker').each(function () {\n" + 
            "\t\t\t	$(this).minicolors({\n" + 
            "\t\t\t		control: $(this).attr('data-control') || 'hue',\n" + 
            "\t\t\t		defaultValue: $(this).attr('data-defaultValue') || '',\n" + 
            "\t\t\t		inline: $(this).attr('data-inline') === 'true',\n" + 
            "\t\t\t		letterCase: $(this).attr('data-letterCase') || 'lowercase',\n" + 
            "\t\t\t		opacity: $(this).attr('data-opacity'),\n" + 
            "\t\t\t		position: $(this).attr('data-position') || 'bottom left',\n" + 
            "\t\t\t		change: function (hex, opacity) {\n" + 
            "\t\t\t			if (!hex) return;\n" + 
    		"\t\t\t			if (opacity) hex += ', ' + opacity;\n" + 
            "\t\t\t			try {" + 
            "\t\t\t				console.log(hex);\n" + 
            "\t\t\t			} catch (e) { }\n" + 
            "\t\t\t		},\n" + 
            "\t\t\t		theme: 'bootstrap'\n" + 
            "\t\t\t	});\n" + 
	        "\t\t\t});\n"  + BUILD_JS;
	
	public String input_ref_build_js=
			"\t\t\tvar #fieldId#_iframe = document.getElementById('#fieldId#_iframe');\n" + 
			"\t\t\t$(\"##fieldId#\").on('click', function () { \n" + 
			"\t\t\t	$(#fieldId#_iframe).attr('src','#refFieldRefPageUrl#');\n" + 
            "\t\t\t	bootbox.dialog({ \n" + 
            "\t\t\t		message: #fieldId#_iframe, \n" + 
            "\t\t\t		title: \"请选择\", \n" + 
            "\t\t\t		className: \"modal-darkorange\", \n" + 
            "\t\t\t		buttons: { \n" + 
            "\t\t\t			success: { \n" + 
            "\t\t\t				label: \"确定\", \n" + 
            "\t\t\t				className: \"btn-blue\", \n" + 
            "\t\t\t				callback: function () { \n" + 
            "\t\t\t					sure('#fieldId#','#refEName#')"+
            "\t\t\t				} \n" + 
            "\t\t\t			}, \n" + 
            "\t\t\t			\"关闭\": { \n" + 
            "\t\t\t				className: \"btn-danger\", \n" + 
            "\t\t\t				callback: function () { } \n" + 
            "\t\t\t			}\n" + 
            "\t\t\t		}\n" + 
            "\t\t\t	});\n" + 
            "\t\t\t});\n" + BUILD_JS;
	
	public String input_ref_multi_build_js = 
			"\t\t\tvar #fieldId#_iframe = document.getElementById('#fieldId#_iframe');\n" + 
			"\t\t\t$(\"##fieldId#\").on('click', function () { \n" + 
			"\t\t\t	$(#fieldId#_iframe).attr('src','#refFieldRefPageUrl#?multi=1');\n" + 
            "\t\t\t	bootbox.dialog({ \n" + 
            "\t\t\t		message: #fieldId#_iframe, \n" + 
            "\t\t\t		title: \"请选择\", \n" + 
            "\t\t\t		className: \"modal-darkorange\", \n" + 
            "\t\t\t		buttons: { \n" + 
            "\t\t\t			success: { \n" + 
            "\t\t\t				label: \"确定\", \n" + 
            "\t\t\t				className: \"btn-blue\", \n" + 
            "\t\t\t				callback: function () { \n" + 
            "\t\t\t					sure('#fieldId#','#refEName#')"+
            "\t\t\t				} \n" + 
            "\t\t\t			}, \n" + 
            "\t\t\t			\"关闭\": { \n" + 
            "\t\t\t				className: \"btn-danger\", \n" + 
            "\t\t\t				callback: function () { } \n" + 
            "\t\t\t			}\n" + 
            "\t\t\t		}\n" + 
            "\t\t\t	});\n" + 
            "\t\t\t});\n" + BUILD_JS;
	/*构建输入框JS--------------------结束------------------------*/
	
	
	/*可选值初始化JS--------------------开始------------------------*/
	public String input_radio_init_js_map = 
			"\t\t\tvar #fieldId#_map = eval('('+\"#valueGetter#\"+')');\n" + 
			"\t\t\tfor(var key in #fieldId#_map){\n" + 
			"\t\t\t\tvar oneRadio = \"<label>\";\n " + 
			"\t\t\t\tif(key=='#fieldDefaultValue#')\n " + 
			"\t\t\t\t	oneRadio = oneRadio + \"<input name='#instanceName#.#fieldName#' checked='checked' class='colored-blue' type='radio' value='#optionValue#'>\";\n"+
			"\t\t\t\telse\n " + 
			"\t\t\t\t	oneRadio = oneRadio + \"<input name='#instanceName#.#fieldName#' class='colored-blue' type='radio' value='#optionValue#'>\";\n"+
			"\t\t\t\toneRadio = oneRadio + \"<span class='text'>#optionText# </span></label>\";\n" + 
			"\t\t\t\t$('##fieldId#').append(oneRadio.replace('#optionValue#',key).replace('#optionText#',#fieldId#_map[key]));\n" + 
			"\t\t\t}\n" + INIT_JS;
	
	public String input_checkbox_init_js_map = 
			"\t\t\tvar #fieldId#_map = eval('('+\"#valueGetter#\"+')');\n" + 
			"\t\t\tfor(var key in #fieldId#_map){\n" + 
			"\t\t\t\tvar oneCheckbox = \"<label>\";\n " + 
			"\t\t\t\tif(key=='#fieldDefaultValue#')\n " + 
			"\t\t\t\t	oneCheckbox = oneCheckbox + \"<input name='#instanceName#.#fieldName#' checked='checked' class='colored-blue' type='checkbox' value='#optionValue#'>\";\n"+
			"\t\t\t\telse\n " + 
			"\t\t\t\t	oneCheckbox = oneCheckbox + \"<input name='#instanceName#.#fieldName#' class='colored-blue' type='checkbox' value='#optionValue#'>\";\n"+
			"\t\t\t\toneCheckbox = oneCheckbox + \"<span class='text'>#optionText# </span></label>\";\n" + 
			"\t\t\t\t$('##fieldId#').append(oneCheckbox.replace('#optionValue#',key).replace('#optionText#',#fieldId#_map[key]));\n" + 
			"\t\t\t}\n" + INIT_JS;
	
	public String select_search_init_js_map = 
			"\t\t\tvar #fieldId#_map = eval('('+\"#valueGetter#\"+')');\n" + 
			"\t\t\tfor(var key in #fieldId#_map)\n" + 
			"\t\t\t\t$('##fieldId#').append('<option value='+ key +'>'+#fieldId#_map[key]+'</option>');\n" +
			"\t\t\t$('##fieldId#').val('#fieldDefaultValue#').trigger('change');\n " + INIT_JS;
	
	public String input_radio_init_js_dictionary = 
			"\t\t\t$.ajax({\n" + 
			"\t\t\t	type: 'get',\n" + 
			"\t\t\t	url: '#dictUrl#',\n" + 
			"\t\t\t	data: {'dictionaryItem.dictionaryIndex.code':'#valueGetter#'},\n" + 
			"\t\t\t	success: function(result) {\n" + 
			"\t\t\t		if(result.code=='0'){\n" + 
			"\t\t\t			for(var i=0;i<result.data.length;i++){ \n" + 
			"\t\t\t				$('##fieldId#').append(\"<label><input name='#instanceName#.#fieldName#'  class='colored-blue' type='radio' value='\"+result.data[i].code+\"'><span class='text'>\"+result.data[i].name +\" </span></label>\");"+
			"\t\t\t			}\n" + 
			"\t\t\t			$('##fieldId#').val('#fieldDefaultValue#');\n " + 
			"\t\t\t		}else{\n" + 
			"\t\t\t			Notify(result.message, 'top-right', '5000', 'yellow', 'fa-check', true);\n" + 
			"\t\t\t		}\n" + 
			"\t\t\t	},\n" +
			"\t\t\t	async:false\n" + 
			"\t\t\t});\n" + INIT_JS;
	
	public String input_checkbox_init_js_dictionary = 
			"\t\t\t$.ajax({\n" + 
			"\t\t\t	type: 'get',\n" + 
			"\t\t\t	url: '#dictUrl#',\n" + 
			"\t\t\t	data: {'dictionaryItem.dictionaryIndex.code':'#valueGetter#'},\n" + 
			"\t\t\t	success: function(result) {\n" + 
			"\t\t\t		if(result.code=='0'){\n" + 
			"\t\t\t			for(var i=0;i<result.data.length;i++){ \n" + 
			"\t\t\t				$('##fieldId#').append(\"<label><input name='#instanceName#.#fieldName#'  class='colored-blue' type='checkbox' value='\"+result.data[i].code+\"'><span class='text'>\"+result.data[i].name +\" </span></label>\");\n"+
			"\t\t\t			}\n" + 
			"\t\t\t			$('##fieldId#').val('#fieldDefaultValue#');\n " + 
			"\t\t\t		}else{\n" + 
			"\t\t\t			Notify(result.message, 'top-right', '5000', 'yellow', 'fa-check', true);\n" + 
			"\t\t\t		}\n" + 
			"\t\t\t	},\n" +
			"\t\t\t	async:false\n" + 
			"\t\t\t});\n" + INIT_JS;
	
	public String select_search_init_js_dictionary = 
			"\t\t\t$.ajax({\n" + 
			"\t\t\t	type: 'get',\n" + 
			"\t\t\t	url: '#dictUrl#',\n" + 
			"\t\t\t	data: {'dictionaryItem.dictionaryIndex.code':'#valueGetter#'},\n" + 
			"\t\t\t	success: function(result) {\n" + 
			"\t\t\t		if(result.code=='0'){\n" + 
			"\t\t\t			for(var i=0;i<result.data.length;i++){ \n" + 
			"\t\t\t				$('##fieldId#').append('<option value='+ result.data[i].code +'>'+result.data[i].name+'</option>');\n"+
			"\t\t\t			}\n" + 
			"\t\t\t			$('##fieldId#').val('#fieldDefaultValue#');\n " + 
			"\t\t\t		}else{\n" + 
			"\t\t\t			Notify(result.message, 'top-right', '5000', 'yellow', 'fa-check', true);\n" + 
			"\t\t\t		}\n" + 
			"\t\t\t	},\n" +
			"\t\t\t	async:false\n" + 
			"\t\t\t});\n" + INIT_JS;
	
	public String select_search_ref_init_js = 
			"\t\t\t$.ajax({\n" + 
			"\t\t\t	type: 'get',\n" + 
			"\t\t\t	url: '#refFieldListUrl#',\n" + 
			"\t\t\t	data: {},\n" + 
			"\t\t\t	success: function(result) {\n" + 
			"\t\t\t		if(result.code=='0'){\n" + 
			"\t\t\t			for(var i=0;i<result.data.length;i++){ \n" + 
			"\t\t\t				$('##fieldId#').append('<option value='+ result.data[i].id +'>'+result.data[i].#cascadeProperty#+'</option>');\n"+
			"\t\t\t			}\n" + 
			"\t\t\t			$('##fieldId#').val('#fieldDefaultValue#');\n " + 
			"\t\t\t		}else{\n" + 
			"\t\t\t			Notify(result.message, 'top-right', '5000', 'yellow', 'fa-check', true);\n" + 
			"\t\t\t		}\n" + 
			"\t\t\t	},\n" +
			"\t\t\t	async:false\n" + 
			"\t\t\t});\n" + INIT_JS;
	
	public String select_search_ref_remote_init_js = 
			"\t\t\t$.ajax({\n" + 
			"\t\t\t	type: 'get',\n" + 
			"\t\t\t	url: '#valuesUrl#',\n" + 
			"\t\t\t	data: {},\n" + 
			"\t\t\t	success: function(result) {\n" + 
			"\t\t\t		if(result.code=='0'){\n" + 
			"\t\t\t			for(var i=0;i<result.data.length;i++){ \n" + 
			"\t\t\t				$('##fieldId#').append('<option value='+ result.data[i].id +'>'+result.data[i].#cascadeProperty#+'</option>');\n"+
			"\t\t\t			}\n" + 
			"\t\t\t			$('##fieldId#').val('#fieldDefaultValue#');\n " + 
			"\t\t\t		}else{\n" + 
			"\t\t\t			Notify(result.message, 'top-right', '5000', 'yellow', 'fa-check', true);\n" + 
			"\t\t\t		}\n" + 
			"\t\t\t	}\n" + 
			"\t\t\t});\n" + INIT_JS;
	/*可选值初始化JS--------------------结束------------------------*/
	
	/*原有值的填充JS--------------------开始------------------------*/
	public String input_text_data_js =  "\t\t\t\t\t\t$('##fieldId#').val(data.#fieldName#);\n" + DATA_INIT_JS;
	
	public String input_radio_data_js = 
			"\t\t\t\t\t\t$('input[name=\"#instanceName#.#fieldName#\"]').each(function(){\n"+ 
			"\t\t\t\t\t\t	if($(this).val()==(\"\" + data.#fieldName#))\n"+ 
			"\t\t\t\t\t\t		$(this).attr('checked',true)\n" + 
			"\t\t\t\t\t\t});\n" + DATA_INIT_JS;
	
	public String input_checkbox_data_js = 
			"\t\t\t\t\t\t$('input[name=\"#instanceName#.#fieldName#\"]').each(function(){\n"+ 
			"\t\t\t\t\t\t	if($(this).val()==(\"\" + data.#fieldName#))\n"+ 
			"\t\t\t\t\t\t		$(this).attr('checked',true)\n" + 
			"\t\t\t\t\t\t});\n" + DATA_INIT_JS;
	
	public String select_search_data_js = 
			"\t\t\t\t\t\t$('##fieldId#').val(''+data.#fieldName#).trigger('change');\n " + DATA_INIT_JS;
	
	public String select_multi_data_js = 
			"\t\t\t\t\t\tif(data.#fieldName#!=undefined&&data.#fieldName#!=null){\n " +
			"\t\t\t\t\t\t	var #fieldId#_values = data.#fieldName#.split(',');\n " +
			"\t\t\t\t\t\t}\n " +
			"\t\t\t\t\t\t$('##fieldId#').val(#fieldId#_values).trigger('change');\n " + DATA_INIT_JS;
	
	public String select_search_ref_data_js = 
			"\t\t\t\t\t\tvar #fieldId#_value = data.#fieldName#!=undefined?(''+data.#fieldName#.id):'';\n " +
			"\t\t\t\t\t\t$('##fieldId#').val(#fieldId#_value).trigger('change');\n " + DATA_INIT_JS;
	
	public String select_multi_ref_data_js = 
			"\t\t\t\t\t\tif(data.#fieldName#!=undefined&&data.#fieldName#!=null){\n " +
			"\t\t\t\t\t\t	var #fieldId#_values = [];\n " +
			"\t\t\t\t\t\t	for(var i=0;i<data.#fieldName#.length;i++){\n " +
			"\t\t\t\t\t\t		#fieldId#_values.push(''+ data.#fieldName#[i].id);\n " +
			"\t\t\t\t\t\t	}\n " +
			"\t\t\t\t\t\t}\n " +
			"\t\t\t\t\t\t$('##fieldId#').val(#fieldId#_values).trigger('change');\n " + DATA_INIT_JS;
	
	public String datepicker_data_js =  "\t\t\t\t\t\t$('##fieldId#').val(data.#fieldName#);\n" + DATA_INIT_JS;

	public String timepicker_data_js =  "\t\t\t\t\t\t$('##fieldId#').val(data.#fieldName#);\n" + DATA_INIT_JS;
	
	public String textarea_data_js =  "\t\t\t\t\t\t$('##fieldId#').val(data.#fieldName#);\n" + DATA_INIT_JS;
	
	public String input_ref_data_js = 
			"\t\t\t\t\t\tvar #fieldId#_a = \"<span><a  href='#this\' id='#fieldId#_\"+data.#fieldName#.id + \"' \"+ \n" + 
        	"\t\t\t\t\t\t	\"onclick='removeItem(this)' data-toggle='modal'><i class='glyphicon glyphicon-remove danger'></i></a>\" + \n" + 
        	"\t\t\t\t\t\t	\"<input type='hidden' name='#refEName#' value='\"+data.#fieldName#.id+\"' class='\"+	data.#fieldName#.id +\"$$\"+ data.#fieldName#.#cascadeProperty#+\"'/>\"+ \n" + 
        	"\t\t\t\t\t\t	\"&nbsp;&nbsp;\"+data.#fieldName#.#cascadeProperty# + \"</span>\"; \n" + 
        	"\t\t\t\t\t\t$('##fieldId#_span').append(#fieldId#_a);\n" + DATA_INIT_JS;
	
	public String input_ref_multi_data_js = 
			"\t\t\t\t\t\tif(data.#fieldName#!=undefined&&data.#fieldName#!=null)" + 
			"\t\t\t\t\t\tfor(var i=0;i<data.#fieldName#.length;i++){" + 
			"\t\t\t\t\t\t	var #fieldId#_a = \"<span><a  href='#this\' id='#fieldId#_\"+data.#fieldName#[i].id + \"' \"+ \n" + 
        	"\t\t\t\t\t\t		\"onclick='removeItem(this)' data-toggle='modal'><i class='glyphicon glyphicon-remove danger'></i></a>\" + \n" + 
        	"\t\t\t\t\t\t		\"<input type='hidden' name='#refEName#' value='\"+data.#fieldName#[i].id+\"' class='\"+	data.#fieldName#[i].id +\"$$\"+ data.#fieldName#[i].#cascadeProperty#+\"'/>\"+ \n" + 
        	"\t\t\t\t\t\t		\"&nbsp;&nbsp;\"+data.#fieldName#[i].#cascadeProperty# + \"</span>\"; \n" + 
        	"\t\t\t\t\t\t	$('##fieldId#_span').append(#fieldId#_a);\n" +
        	"\t\t\t\t\t\t}\n" + DATA_INIT_JS;
	
	/*原有值的填充JS--------------------结束------------------------*/
	
	/*特殊类型，针对-to-many的many方字段的展示列表------------------开始--------------------*/
	public String ref_element_list = 
			"\t\t\t<div class=\"row\">\n"+
			"\t\t\t	<div class=\"col-lg-12 col-sm-12 col-xs-12\">\n"+
			"\t\t\t		<div class=\"well with-header\">\n"+
			"\t\t\t			<div class=\"header bordered-blue\">\n"+
			"\t\t\t				管理#fieldLabel#\n"+
			"\t\t\t			</div>\n"+
			"\t\t\t			<div id='#fieldId#_model'>\n"+ 
			"\t\t\t				<iframe id='#fieldId#_iframe' frameborder='no' src='' style='width: 100%; height: auto;'></iframe>\n" + 
			"\t\t\t			</div>\n" + 
			"\t\t\t		</div>\n" + 
			"\t\t\t	</div>\n" + 
			"\t\t\t</div>\n" +  REF_ELEMENT_LIST;
	
	public String ref_element_data_js = 
			"\t\t\tvar #fieldId#_iframe = document.getElementById('#fieldId#_iframe');\n" +
			"\t\t\t$(#fieldId#_iframe).attr('src','#refFieldInListPageUrl#?fieldName=#fieldName#&instanceName=#instanceName#&#instanceName#.id='+ $('##instanceName#_id').val());\n" +
			"\t\t\t$(#fieldId#_iframe).bind('load',function(){\n" +
			"\t\t\t	$(#fieldId#_iframe).height(#fieldId#_iframe.contentDocument.body.scrollHeight);\n" +
			"\t\t\t});\n" + DATA_INIT_JS;
	/*特殊类型，针对-to-many的many方字段的展示列表------------------结束--------------------*/
}
