package com.kametwu.dm.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class R extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	private static final String VAR_CODE = "code";
	private static final String VAR_MSG = "msg";
	private static final String VAR_DTLMSG = "detailMsg";
	private static final String VAR_DATA = "data";
	private static final String VAR_DICT = "dict";
//	private static final String VAR_PAGE = "page";

	private R() {
		super(8);
	}

	private R(int code, String msg) {
		this.put(VAR_CODE, code);
		this.put(VAR_MSG, msg);
		this.put(VAR_DTLMSG, null);
		this.put(VAR_DATA, null);
	}

	public static R ok() {
		return new R(0, "sucess");
	}

	public static R ok(String msg) {
		return new R(0, msg);
	}

	public static R err(int code, String msg) {
		return new R(code, msg);
	}

	public static R err(int code, String msg, String detailMsg) {
		return new R(code, msg).put(VAR_DTLMSG, detailMsg);
	}

	public <T> R addData(T data) {
		return this.put(VAR_DATA, data);
	}

	public R addDict(Map<String, Map<String, String>> codeMap) {
		if (codeMap == null) return this;
		return this.put(VAR_DICT, codeMap);
	}

	@Override @Deprecated
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

}
