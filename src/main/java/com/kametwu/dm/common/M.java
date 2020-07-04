package com.kametwu.dm.common;

import java.util.HashMap;
import java.util.Map;

public class M {
	private static Map<String, Message> keyMsgMap = new HashMap<String, Message>();
	private static Map<Integer, Message> codeMsgMap = new HashMap<Integer, Message>();
	
	private M() {}
	
	public static void add(String key, Integer code, String msg) {
		Message m = new Message(key, code, msg);
		keyMsgMap.put(key, m);
		if(code != null) codeMsgMap.put(code, m);
	}
	
	public static boolean containsKey(String key) {
		return keyMsgMap.containsKey(key);
	}
	
	public static boolean containsCode(int code) {
		return codeMsgMap.containsKey(code);
	}
	
	public static String getMsg(String key) {
		return keyMsgMap.containsKey(key) ? keyMsgMap.get(key).getMsg() : String.format("Key \"%s\" not found.", key);
	}
	
	public static String getMsg(int code) {
		return codeMsgMap.containsKey(code) ? codeMsgMap.get(code).getMsg() : String.format("Code \"%d\" not found.", code);
	}

	static class Message {
		private String key;
		private Integer code;
		private String msg;
		
		public Message(String key, Integer code, String msg) {
			this.key = key;
			this.code = code;
			this.msg = msg;
		}
		
		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		@Override
		public String toString() {
			return "Message [key=" + key + ", code=" + code + ", msg=" + msg + "]";
		}
		
	}
}
