package com.appspiration.service;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Utility {

	public static boolean isNotNull(String txt) {
		return txt != null && txt.trim().length() >= 0 ? true : false;
	}

	public static String constructJSON(JSONArray ja) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("threads", ja);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString();
	}

	public static String constructJSON(String tag, boolean status) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString();
	}

	public static JSONObject constructJSONThread(String title, String content, String time, String category, String op, int votes) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("title", title);
			obj.put("content", content);
			obj.put("time", time);
			obj.put("category", category);
			obj.put("op", op);
			obj.put("votes", votes);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj;
	}

	public static String constructJSON(String tag, boolean status,String err_msg) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			obj.put("error_msg", err_msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString(); 
	}

}
