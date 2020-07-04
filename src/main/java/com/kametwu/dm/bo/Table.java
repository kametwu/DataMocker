package com.kametwu.dm.bo;

import java.util.List;

public class Table {

	private String dbSchema;
	private String tableName;
	private String tableComment;
	private List<Column> columns;

	public String getDbSchema() {
		return dbSchema;
	}

	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return "Table [dbSchema=" + dbSchema + ", tableName=" + tableName + ", tableComment=" + tableComment
				+ ", columns=" + columns.size() + "]";
	}

}
