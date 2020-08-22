package com.kametwu.dm.vo;

import com.kametwu.dm.bo.DataSource;
import com.kametwu.dm.bo.Table;

public class TableSaveVO {
	private DataSource dataSource;
	private Table table;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

}
