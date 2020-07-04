package com.kametwu.dm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kametwu.dm.cache.CacheManager;
import com.kametwu.dm.common.R;

@RestController
@RequestMapping("/code")
public class CodeController {
	
	@GetMapping("/typeCode/list")
	public R findTypeCodeList() {
		return R.ok().addData(CacheManager.getTypeCodeList());
	}
	
	@GetMapping("/list/{typeCode}")
	public R findCodeListByType(@PathVariable String typeCode) {
		return R.ok().addData(CacheManager.findCodeListByType(typeCode));
	}
	
	@GetMapping("/map/{typeCode}")
	public R findCodeMapByType(@PathVariable String typeCode) {
		return R.ok().addData(CacheManager.findCodeMapByType(typeCode));
	}
	
	@GetMapping("/map/simple/{typeCode}")
	public R findSimpleCodeListByType(@PathVariable String typeCode) {
		return R.ok().addData(CacheManager.findSimpleCodeMapByType(typeCode));
	}

}
