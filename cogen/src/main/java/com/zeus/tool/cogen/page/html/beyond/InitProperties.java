package com.zeus.tool.cogen.page.html.beyond;

public class InitProperties implements BeyondAdminProperties{

	public static void init(){
		
		elements_input.put("input_ref", input_ref_btn);
		elements_input.put("input_ref_multi", input_ref_btn);
		elements_input.put("input_text", input_text);
		elements_input.put("input_radio", input_radio);
		elements_input.put("input_checkbox", input_checkbox);
		elements_input.put("select_search", select_search);
		elements_input.put("select_multi", select_multi);
		elements_input.put("input_tags", input_tags);
		elements_input.put("datepicker", datepicker);
		elements_input.put("timepicker", timepicker);
		elements_input.put("textarea", textarea);
		elements_input.put("colorpicker", colorpicker);
		elements_input.put("spinner", spinner);
		elements_input.put("slider", slider);
		elements_input.put("treepicker", treepicker);
		elements_input.put("select_multi_ref",select_multi_ref);
		elements_input.put("select_ref",select_search_ref);
		
		elements_ref_model.put("input_ref", input_ref_model);
		
		elements_ref_js.put("input_ref", ref_js_ref);
		elements_ref_js.put("select_search", ref_js_select);
		elements_ref_js.put("select_multi", ref_js_select);
		elements_ref_js.put("input_tags", ref_js_tagsinput);
		elements_ref_js.put("datepicker", ref_js_datepicker);
		elements_ref_js.put("timepicker", ref_js_timepicker);
		elements_ref_js.put("textarea", ref_js_textarea);
		elements_ref_js.put("spinner", ref_js_spinner);
		elements_ref_js.put("colorpicker", ref_js_colorpicker);
		elements_ref_js.put("select_multi_ref",ref_js_select);
		elements_ref_js.put("select_ref",ref_js_select);
		
		elements_build_js.put("select_search", select_search_build_js);
		elements_build_js.put("select_multi", select_multi_build_js);
		elements_build_js.put("select_ref",select_search_build_js);
		elements_build_js.put("select_multi_ref",select_multi_build_js);
		elements_build_js.put("datepicker", datepicker_build_js);
		elements_build_js.put("timepicker", timepicker_build_js);
		elements_build_js.put("textarea", textarea_build_js);
		elements_build_js.put("spinner", spinner_build_js);
		elements_build_js.put("colorpicker", colorpicker_build_js);
		elements_build_js.put("input_ref", input_ref_build_js);
		elements_build_js.put("input_ref_multi", input_ref_multi_build_js);
		
		elements_init_js.put("select_search_map", select_search_init_js_map);
		elements_init_js.put("select_multi_map", select_search_init_js_map);
		elements_init_js.put("input_radio_map", input_radio_init_js_map);
		elements_init_js.put("input_checkbox_map", input_checkbox_init_js_map);
		elements_init_js.put("select_search_dictionary", select_search_init_js_dictionary);
		elements_init_js.put("select_multi_dictionary", select_search_init_js_dictionary);
		elements_init_js.put("input_radio_dictionary", input_radio_init_js_dictionary);
		elements_init_js.put("input_checkbox_dictionary", input_checkbox_init_js_dictionary);
		elements_init_js.put("select_ref",select_search_ref_init_js);
		elements_init_js.put("select_multi_ref",select_search_ref_init_js);
		elements_init_js.put("select_remote_ref",select_search_ref_remote_init_js);
		elements_init_js.put("select_multi_remote_ref",select_search_ref_remote_init_js);
		
		elements_data_init_js.put("input_text", input_text_data_js);
		elements_data_init_js.put("input_radio", input_radio_data_js);
		elements_data_init_js.put("input_checkbox", input_checkbox_data_js);
		elements_data_init_js.put("input_ref", input_ref_data_js);
		elements_data_init_js.put("input_ref_multi", input_ref_multi_data_js);
		elements_data_init_js.put("select_search", select_search_data_js);
		elements_data_init_js.put("select_multi", select_multi_data_js);
		elements_data_init_js.put("select_ref", select_search_ref_data_js);
		elements_data_init_js.put("select_multi_ref", select_multi_ref_data_js);
		elements_data_init_js.put("datepicker", datepicker_data_js);
		elements_data_init_js.put("timepicker", timepicker_data_js);
		elements_data_init_js.put("textarea", textarea_data_js);
	}
}
