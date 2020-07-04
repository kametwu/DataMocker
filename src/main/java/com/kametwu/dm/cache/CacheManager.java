package com.kametwu.dm.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import com.kametwu.dm.bo.Code;
import com.kametwu.dm.common.M;
import com.kametwu.dm.service.CodeService;

@Component
public class CacheManager {
	private static final Log logger = LogFactory.getLog(CacheManager.class);
	private static Map<String, List<Code>> CodeMap = new Hashtable<String, List<Code>>();
	private static Map<String, String> typeCodeMap = new Hashtable<String, String>();
	
	@Autowired CodeService codeService;
	
	@PostConstruct
	public void initCode() {
		logger.info("code init begin...");
		
		List<Code> codeList = codeService.findAll();
		
		codeList.forEach(code -> {
			List<Code> cl = CodeMap.get(code.getTypeCode());
			if(cl == null) {
				List<Code> newCL = new ArrayList<Code>();
				newCL.add(code);
				CodeMap.put(code.getTypeCode(), newCL);
				typeCodeMap.put(code.getTypeCode(), code.getTypeName());
			}else {
				cl.add(code);
			}
		});
		logger.info("code init end.");
	}
	
	@PostConstruct
	private void initMessage() {
		String msgProps = "message.properties";
		logger.info(String.format("Load properties file: %s", msgProps));
		try {
			EncodedResource encodedResource = new EncodedResource(new ClassPathResource(msgProps), "UTF-8");
			Properties props = PropertiesLoaderUtils.loadProperties(encodedResource);
			Pattern pattern = Pattern.compile("\\{\\{(-?\\d{5})\\}\\}(.+)");
			for(Entry<Object, Object> p : props.entrySet()) {
				String key = p.getKey().toString();
				String val = p.getValue().toString();
				Matcher m = pattern.matcher(val);
				if(m.find()) {
					int code = Integer.parseInt(m.group(1));
					if(M.containsCode(code)) logger.warn(String.format("%s: The Code \"%s\" duplicated.", msgProps, code));
					String msg = m.group(2);
					M.add(key, code, msg);
				}else {
					if(M.containsKey(key)) logger.warn(String.format("%s: The Key \"%s\" duplicated.", msgProps, key));
					M.add(key, null, val);
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		logger.info(String.format("Properties file %s loaded.", msgProps));
	}

	public static List<Code> findCodeListByType(String typeCode) {
		return CodeMap.get(typeCode);
	}

	public static Map<String, Code> findCodeMapByType(String typeCode) {
		List<Code> codeList = CodeMap.get(typeCode);
		Map<String, Code> codeMap = new HashMap<String, Code>();
		for(Code code : codeList) {
			codeMap.put(code.getItemCode(), code);
		}
		return codeMap;
	}

	public static Map<String, String> getTypeCodeList() {
		return typeCodeMap;
	}

	public static Map<String, String> findSimpleCodeMapByType(String typeCode) {
		List<Code> codeList = CodeMap.get(typeCode);
		Map<String, String> codeMap = new HashMap<String, String>();
		for(Code code : codeList) {
			codeMap.put(code.getItemCode(), code.getItemName());
		}
		return codeMap;
	}
	
}
