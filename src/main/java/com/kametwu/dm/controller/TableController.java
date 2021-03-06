package com.kametwu.dm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kametwu.dm.bo.DataSource;
import com.kametwu.dm.common.R;
import com.kametwu.dm.service.TableService;
import com.kametwu.dm.vo.TableSaveVO;

@RestController
@RequestMapping("/table")
public class TableController {
	
	@Autowired TableService tableService;
	
	@GetMapping("/list")
	public R getTableList(DataSource ds) {
		try {
			return R.ok().addData(tableService.getTableList(ds));
		} catch (Exception e) {
			e.printStackTrace();
			return R.err(30112, e.getMessage(), e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/save")
	public R saveTableData(@RequestBody TableSaveVO tsvo) {
		try {
			int count = tableService.saveMockData(tsvo.getDataSource(), tsvo.getTable());
			return R.ok().addData(count);
		} catch (Exception e) {
			e.printStackTrace();
			return R.err(20001, e.getMessage());
		}
	}
	
	@GetMapping("testDataSource")
	public R testDataSource(DataSource ds) {
		String retString = tableService.testDataSource(ds);
		if(retString == null) {
			return R.ok().addData(10000);
		}else {
			return R.ok(retString).addData(10001);
		}
	}

}
