package com.kametwu.dm.bo;

public class Column {

	private String tableName;
	private String columnName;
	private String columnComment;
	private String columnType;
	private Long columnLength;
	private Integer columnScale;
	private String primaryKey;
	private String nullable;
	private String defaultValue;
	private String indexName;
	private Integer columnSort;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public Long getColumnLength() {
		return columnLength;
	}

	public void setColumnLength(Long columnLength) {
		this.columnLength = columnLength;
	}

	public Integer getColumnScale() {
		return columnScale;
	}

	public void setColumnScale(Integer columnScale) {
		this.columnScale = columnScale;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public Integer getColumnSort() {
		return columnSort;
	}

	public void setColumnSort(Integer columnSort) {
		this.columnSort = columnSort;
	}

}
