package com.kametwu.dm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.kametwu.dm.bo.Column;
import com.kametwu.dm.bo.Table;
import com.kametwu.dm.util.RandomUtil;

@Service
public class MockerService {
	private static final int DETAULT_PREVIEW_SIZE = 100;

	public String getChineseName() {
		String surname = RandomUtil.selectOneCode("SURNAME").getItemName();
		String name = RandomUtil.selectOneCode("SURNAME").getItemName();
		return surname + name;
	}
	
	public String getCompanyName() {
		String name = RandomUtil.selectOneCode("COMPANY2019TOP500").getItemName();
		return name;
	}
	
	public String getCountry() {
		String name = RandomUtil.selectOneCode("COUNTRY").getItemName();
		return name;
	}

	public double getAmount(int minAmount, int maxAmount) {
		return RandomUtil.getDouble(minAmount, maxAmount, 2);
	}

	public String getIDCard(String birthday) {
		String AdmindivCode = RandomUtil.selectOneCode("ADMINDIVCODE").getItemCode();
		String birthdayStr = new DateTime(birthday).toString("yyyyMMdd");
		String checkCode = String.valueOf(RandomUtil.getInteger(1111, 9999));
		String idcard = AdmindivCode.concat(birthdayStr).concat(checkCode);
		return idcard;
	}

	public String getDateForAge(int minAge, int maxAge) {
		DateTime now = new DateTime();
		String minDate = now.minusYears(maxAge).toString("yyyyMMdd");
		String maxDate = now.minusYears(minAge).toString("yyyyMMdd");
		return RandomUtil.getDate(minDate, maxDate);
	}

	public static void main(String[] args) {
		MockerService ms = new MockerService();
		for (int i = 0; i < 100; i++) {
			System.out.println(ms.getDateForAge(10, 80));
//			System.out.println(random.nextInt());
		}
	}

	public List<Map<String, Object>> getPreviewData(Table tab) {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		
		for(int i = 0; i < DETAULT_PREVIEW_SIZE; i++) {
			Map<String, Object> colVal = new HashMap<String, Object>();
			for(Column col : tab.getColumns()) {
				Object value = null;
				if("VARCHAR2".equals(col.getColumnType())) {
					value = RandomUtil.getString(col.getColumnLength() < 6 ? col.getColumnLength() : 1l, Math.min(col.getColumnLength(), 10l));
				}else if("NUMBER".equals(col.getColumnType())) {
					if(col.getColumnScale() != null && col.getColumnScale() != 0) {
						value = RandomUtil.getDouble(1, Math.min(Math.pow(10, col.getColumnLength()-col.getColumnScale()), 100000), col.getColumnScale());
					}else {
						value = RandomUtil.getInteger(1, 10000);
					}
				}else if("DATE".equals(col.getColumnType())) {
					value = RandomUtil.getDate();
				}else if("TIMESTAMP(6)".equals(col.getColumnType())) {
					value = RandomUtil.getDateTime();
				}
				colVal.put(col.getColumnName(), value);
			}
			data.add(colVal);
		}
		
		return data;
	}

}
