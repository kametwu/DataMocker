package com.kametwu.dm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kametwu.dm.bo.Code;
import com.kametwu.dm.mapper.CodeMapper;

@Service
public class CodeService {
	
	@Autowired CodeMapper codeMapper;
	
	public List<Code> findAll() {
		return codeMapper.selectList(null);
	}

}
