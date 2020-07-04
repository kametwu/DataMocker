package com.kametwu.dm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.kametwu.dm.bo.Code;
import com.kametwu.dm.bo.Column;
import com.kametwu.dm.bo.DataSource;
import com.kametwu.dm.bo.Table;
import com.kametwu.dm.cache.CacheManager;

@Service
public class TableService {

	@Autowired JdbcTemplate jdbcTemplate;

	public List<Table> getTableList(DataSource ds) throws Exception {
		List<Table> tabList = new ArrayList<Table>();
		Map<String, Table> tabMap = new HashMap<String, Table>();
		// 创建数据源连接
		DriverManagerDataSource dmd = new DriverManagerDataSource();
		dmd.setDriverClassName(ds.getDriver());
		dmd.setUrl(ds.getUrl());
		dmd.setUsername(ds.getUsername());
		dmd.setPassword(ds.getPassword());
		jdbcTemplate.setDataSource(dmd);
		
		// 从JDBC URL中拿到数据库类型，利用数据库类型在字典里查出元数据的查询SQL
		String sql = null;
		Pattern pattern = Pattern.compile("jdbc:(.+?):.+");
		Matcher matcher = pattern.matcher(ds.getUrl());
		if(matcher.matches()) {
			String dbType = matcher.group(1);
			Map<String, Code> codeMap = CacheManager.findCodeMapByType("DBTYPE");
			if(codeMap.containsKey(dbType)) {
				sql = codeMap.get(dbType).getAttr5();
			}else {
				throw new Exception(String.format("Unrecognized database type: %s", dbType));
			}
			if(sql == null || sql.trim().equals("")) {
				throw new Exception(String.format("No metadata sql for \"%s\"", dbType));
			}
		}else {
			throw new Exception(String.format("Database type not found: %s", ds.getUrl()));
		}
		
		// 获取表和字段的信息
		List<Map<String, Object>> colList = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> col : colList) {
			// 表信息
			String dbSchema = str(col.get("DB_SCHEMA"));
			String tabName = str(col.get("TABLE_NAME"));
			String tableKey = String.format("%s.%s", dbSchema, tabName);
			if(!tabMap.containsKey(tableKey)) {
				Table table = new Table();
				table.setDbSchema(dbSchema);
				table.setTableName(tabName);
				table.setTableComment(str(col.get("TABLE_COMMENT")));
				table.setColumns(new ArrayList<Column>());
				tabMap.put(tableKey, table);
			}
			// 列信息
			Column column = new Column();
			column.setTableName(tabName);
			column.setColumnName(str(col.get("COLUMN_NAME")));
			column.setColumnComment(str(col.get("COLUMN_COMMENT")));
			column.setColumnType(str(col.get("COLUMN_TYPE")));
			column.setColumnLength(long2(col.get("COLUMN_LENGTH")));
			column.setColumnScale(int2(col.get("COLUMN_SCALE")));
			column.setPrimaryKey(str(col.get("PRIMARY_KEY")));
			column.setNullable(str(col.get("NULLABLE")));
			column.setDefaultValue(str(col.get("DEFAULT_VALUE")));
			column.setIndexName(str(col.get("INDEX_NAME")));
			column.setColumnSort(int2(col.get("COLUMN_SORT")));
			tabMap.get(tableKey).getColumns().add(column);
		}
		
		for(Entry<String, Table> t : tabMap.entrySet()) {
			tabList.add(t.getValue());
		}
		
		return tabList;
	}

	private String str(Object obj) {
		if(obj == null) return null;
		return obj.toString();
	}

	private Integer int2(Object obj) {
		if(obj == null) return null;
		return Integer.parseInt(obj.toString());
	}

	private Long long2(Object obj) {
		if(obj == null) return null;
		return Long.parseLong(obj.toString());
	}

	public String testDataSource(DataSource ds) {
		// 创建数据源连接
		try {
			DriverManagerDataSource dmd = new DriverManagerDataSource();
			dmd.setDriverClassName(ds.getDriver());
			dmd.setUrl(ds.getUrl());
			dmd.setUsername(ds.getUsername());
			dmd.setPassword(ds.getPassword());
			jdbcTemplate.setDataSource(dmd);
			jdbcTemplate.getDataSource().getConnection().close();
		}catch(Exception e) {
			return e.getMessage();
		}
		return null;
	}
}
