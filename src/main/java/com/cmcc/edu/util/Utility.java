package com.cmcc.edu.util;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cmcc.edu.util.pagehelper.PageInfo;

public class Utility<T> {
	
	public static final Boolean DEVELOPED = Boolean.parseBoolean(Utility.getConfigProperty("DEVELOPED"));
	
	private static ResourceBundle resources=null;
	public static String HOST = Utility.getConfigProperty("host") == null?"http://localhost:8080/ems_new/":Utility.getConfigProperty("host");
	
	public static String getConfigProperty(String key) {
		if(checkResources()){    	
    		try{
	    		return resources.getString(key);	
    		}catch(Exception e){
    			
    		}
    	}
	    return null;
	}
	
	private static boolean checkResources(){
    	if(resources==null)
    		getBundle();
    	return (resources!=null);
	}
	
	private static void getBundle(){
        try {
            resources = ResourceBundle.getBundle("system", Locale.getDefault());
        } catch (MissingResourceException mre) {
           
        }
    }

	
	public static String createJsonStr(Map<String, Object> jsonMap) {
		if (jsonMap == null) {
			return null;
		}
		JSONObject json = new JSONObject();
		json.putAll(jsonMap);
		String ret = json.toString();
		json.clear();
		json=null;
		return ret;
	}
	
	public static String createJsonStr(List<?> jsonList) {

		if (jsonList == null)
			return null;

		JSONArray json = new JSONArray();

		int i = 0;
		for (; i < jsonList.size(); i++)
			json.add(i, jsonList.get(i));
		String ret = json.toString();
		json.clear();
		json=null;
		return ret;
	}
	
	public static String createJsonStr(PageInfo<?> jsonList) {

		return JSONObject.fromObject(jsonList).toString();
	}
	
	public static String createJsonString(List<?> jsonList) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < jsonList.size(); i++){
			sb.append(jsonList.get(i)+",");
			}
		String ret  = sb.substring(0, sb.length()-1);
		ret = ret+"]";
		
		return ret;
	}

}
