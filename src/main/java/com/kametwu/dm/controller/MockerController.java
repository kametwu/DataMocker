package com.kametwu.dm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kametwu.dm.bo.Table;
import com.kametwu.dm.common.M;
import com.kametwu.dm.common.R;
import com.kametwu.dm.service.MockerService;
import com.kametwu.dm.util.RandomUtil;

@RestController
@RequestMapping("/mock")
public class MockerController {
	private static final Log log = LogFactory.getLog(MockerController.class);

	@Autowired MockerService mockerService;
	
	@PostMapping("/common")
	public R getCommonData(@RequestBody Table tab) {
		return R.ok().addData(mockerService.getPreviewData(tab));
	}
	
	@GetMapping("/custList")
	public R getCustList(@RequestParam("length") int length) {
		log.info("Generate cust data running...");
		List<Map<String, Object>> custList = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < length; i++) {
			Map<String, Object> cust = new HashMap<String, Object>();
//			cust.put("ADDRESS_MODE", RandomUtil.getInteger(1000, 9999)); //地址模式
			cust.put("AREA_CODE", RandomUtil.getInteger(1000, 9999)); //所属区域
			cust.put("ASSIGN_FLAG", RandomUtil.getInteger(0, 1)); //分配标志
			cust.put("BLOC_FLAG", RandomUtil.getInteger(0, 1)); //集团客户标志
			cust.put("BRANCH_CODE", RandomUtil.getInteger(1000, 9999)); //所属分行
			cust.put("CANCEL_DATE", RandomUtil.getDate("2000-01-01", "2019-12-31")); //清户时间
			cust.put("CANCEL_FLAG", RandomUtil.getYesOrNo()); //是否清户
			cust.put("CDB_INDUSTRY", RandomUtil.getInteger(1000, 9999)); //开行行业代码
			cust.put("CN_SHORT_NAME", mockerService.getCompanyName()); //客户简称
			cust.put("COUNTRY_CODE", mockerService.getCountry()); //所在国家
			cust.put("CREATE_ORG", RandomUtil.getInteger(1000, 9999)); //创建机构
			cust.put("CREATE_TIME", RandomUtil.getDateTime()); //创建时间
			cust.put("CREATOR", mockerService.getChineseName()); //创建人
			cust.put("CREDIT_LEVEL", RandomUtil.getInteger(1000, 9999)); //客户信用等级
			cust.put("CUST_CATEG", RandomUtil.getInteger(1000, 9999)); //客户主要类别
			cust.put("CUST_DETAIL_TYPE", RandomUtil.getInteger(1000, 9999)); //客户明细类别
			cust.put("CUST_LEVEL", RandomUtil.getInteger(1000, 9999)); //客户等级分类
			cust.put("CUST_NAME", RandomUtil.getInteger(1000, 9999)); //客户全称
			cust.put("CUST_NO", RandomUtil.getInteger(1000, 9999)); //客户号
			cust.put("CUST_STAGE", RandomUtil.getInteger(1000, 9999)); //客户阶段
			cust.put("CUST_STATUS", RandomUtil.getInteger(1000, 9999)); //客户状态
			cust.put("CUST_TYPE", RandomUtil.getInteger(1000, 9999)); //客户类别
			cust.put("CUST_TYPE_CBRC", RandomUtil.getInteger(1000, 9999)); //客户类型
			cust.put("DATA_SYN_FLAG", RandomUtil.getInteger(1000, 9999)); //数据同步状态
			cust.put("ECONOMIC_SECTOR", RandomUtil.getInteger(1000, 9999)); //企业出资人经济成份
			cust.put("ECONOMY_KIND", RandomUtil.getInteger(1000, 9999)); //经济性质
			cust.put("EC_CUST_NO", RandomUtil.getInteger(1000, 9999)); //核心客户号
			cust.put("EC_CUST_TYPE", RandomUtil.getInteger(1000, 9999)); //核心客户种类
			cust.put("EC_STATUS", RandomUtil.getInteger(1000, 9999)); //核心状态
			cust.put("EC_TRAN_STATUS", RandomUtil.getInteger(1000, 9999)); //核心交易状态
			cust.put("ENTERPRISE_LEVEL", RandomUtil.getInteger(1000, 9999)); //企业级别
			cust.put("ENTERPRISE_SCOPE", RandomUtil.getInteger(1000, 9999)); //企业规模
			cust.put("EN_FULL_NAME", RandomUtil.getInteger(1000, 9999)); //英文全称
			cust.put("EN_SHORT_NAME", RandomUtil.getInteger(1000, 9999)); //英文简称
			cust.put("ETL_TIME", RandomUtil.getInteger(1000, 9999)); //ETL日期
			cust.put("FIRST_LOAN_DATE", RandomUtil.getInteger(1000, 9999)); //客户首合同日期
			cust.put("FN_ORG_TYPE", RandomUtil.getInteger(1000, 9999)); //金融机构类型
			cust.put("GMJJ_BMFL_FLAG", RandomUtil.getInteger(1000, 9999)); //国民经济部门分类
			cust.put("IMPORT_DATE", RandomUtil.getInteger(1000, 9999)); //重大客户认定时间
			cust.put("IMPORT_FLAG", RandomUtil.getInteger(1000, 9999)); //是否重大客户
			cust.put("INDUSTRY_TYPE", RandomUtil.getInteger(1000, 9999)); //所属行业
			cust.put("INDUSTRY_TYPE1", RandomUtil.getInteger(1000, 9999)); //行业补充分类
			cust.put("INEXPORT_FLAG", RandomUtil.getInteger(1000, 9999)); //进出口权标志
			cust.put("INLAND_FLAG", RandomUtil.getInteger(1000, 9999)); //境内外标志
			cust.put("INNER_CUST_FLAG", RandomUtil.getInteger(1000, 9999)); //是否内部客户
			cust.put("INVEST_TYPE", RandomUtil.getInteger(1000, 9999)); //投融资平台类型
			cust.put("IS_GK_LQ_KQQY", RandomUtil.getInteger(1000, 9999)); //是否为国有工矿、林区、垦区企业
			cust.put("IS_STUDENT_LOAN", RandomUtil.getInteger(1000, 9999)); //是否助学贷款
			cust.put("JKR_TYPE", RandomUtil.getInteger(1000, 9999)); //借款人分类（是否属于利率优惠范围）
			cust.put("LEGAL_PERSON", RandomUtil.getInteger(1000, 9999)); //法人代表
			cust.put("LEGAL_PS_ID_NO", RandomUtil.getInteger(1000, 9999)); //法人证件号码
			cust.put("LEGAL_PS_ID_TYPE", RandomUtil.getInteger(1000, 9999)); //法人证件种类
			cust.put("LISTINGCORP_FLAG", RandomUtil.getInteger(1000, 9999)); //是否上市企业
			cust.put("MAIN_FLAG", RandomUtil.getInteger(1000, 9999)); //主客户标志
			cust.put("MANAGER_A", RandomUtil.getInteger(1000, 9999)); //客户经理A
			cust.put("MANAGER_B", RandomUtil.getInteger(1000, 9999)); //客户经理B
			cust.put("MIDENT_FLAG", RandomUtil.getInteger(1000, 9999)); //是否中小企业
			cust.put("MODULE_AUTH", RandomUtil.getInteger(1000, 9999)); //业务模块授权情况
			cust.put("OPEN_DATE", RandomUtil.getInteger(1000, 9999)); //开户日期
			cust.put("OPEN_ORG", RandomUtil.getInteger(1000, 9999)); //开户机构
			cust.put("ORG_CODE", RandomUtil.getInteger(1000, 9999)); //组织机构代码
			cust.put("ORG_ID", RandomUtil.getInteger(1000, 9999)); //所属机构
			cust.put("ORG_TYPE", RandomUtil.getInteger(1000, 9999)); //组织机构类型
			cust.put("PHQ_TRANS_BUSI_JKR", RandomUtil.getInteger(1000, 9999)); //是否为棚户区改造业务借款人
			cust.put("PLAT_JKR_TYPE", RandomUtil.getInteger(1000, 9999)); //平台类借款人类型
			cust.put("PRINT_LANGUAGE", RandomUtil.getInteger(1000, 9999)); //打印语言
			cust.put("PRIORITY_DATE", RandomUtil.getInteger(1000, 9999)); //优先等级认定时间
			cust.put("REGION_CODE", RandomUtil.getInteger(1000, 9999)); //所在地区
			cust.put("RELA_FLAG", RandomUtil.getInteger(1000, 9999)); //关联客户标志
			cust.put("RETIRE_REASON", RandomUtil.getInteger(1000, 9999)); //废止原因
			cust.put("RETIRE_TIME", RandomUtil.getInteger(1000, 9999)); //废止日期
			cust.put("RETIRE_USER", RandomUtil.getInteger(1000, 9999)); //废止人
			cust.put("SETUP_DATE", RandomUtil.getInteger(1000, 9999)); //成立时间
			cust.put("SOURCE_ID", RandomUtil.getInteger(1000, 9999)); //客户信息源
			cust.put("SUPPLY_RELA", RandomUtil.getInteger(1000, 9999)); //客我供求关系
			cust.put("TAX_FLAG", RandomUtil.getInteger(1000, 9999)); //是否纳税
			cust.put("TEMP_FLAG", RandomUtil.getInteger(1000, 9999)); //是否临时客户
			cust.put("TEMP_NO", RandomUtil.getInteger(1000, 9999)); //临时编号
			cust.put("UPDATE_ORG", RandomUtil.getInteger(1000, 9999)); //更新机构
			cust.put("UPDATE_TIME", RandomUtil.getDateTime(cust.get("CREATE_TIME").toString().substring(0, 10), new DateTime().toString())); //更新日期
			cust.put("UPDATE_USER", mockerService.getChineseName()); //更新人
			cust.put("WORK_RELA", RandomUtil.getInteger(1000, 9999)); //客我工作关系
			cust.put("YEAR_PRIORITY", RandomUtil.getInteger(1000, 9999)); //客户年度优先等级
			custList.add(cust);
		}
		log.info("Generate data done.");
		return R.ok().addData(custList);
	}

	@GetMapping("/acctList")
	public R getAcctList(@RequestParam("length") int length) {
		log.info("Generate acct data running...");
		List<Map<String, Object>> acctList = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < length; i++) {
			Map<String, Object> acct = new HashMap<String, Object>();
			acct.put("chineseName", mockerService.getChineseName());
			acct.put("amount", mockerService.getAmount(100, 100000));
			acct.put("accountNo", RandomUtil.getLong(6222000000000000l, 6222999999999999l));
			acct.put("birthday", mockerService.getDateForAge(18, 60));
			acct.put("idcard", mockerService.getIDCard(acct.get("birthday").toString()));
			acct.put("openDate", RandomUtil.getDate("2019-01-01", "2020-06-30"));
			
			acct.put("ACCT_CLOSE_APPR_STATUS", RandomUtil.getInteger(1000, 9999)); //账户销户批准状态
			acct.put("ACCT_CLOSE_DATE", RandomUtil.getInteger(1000, 9999)); //销户日期
			acct.put("ACCT_CLOSE_OFFICER", RandomUtil.getInteger(1000, 9999)); //销户柜员
			acct.put("ACCT_CLOSE_REASON", RandomUtil.getInteger(1000, 9999)); //销户原因
			acct.put("ACCT_DESC", RandomUtil.getInteger(1000, 9999)); //账户描述
			acct.put("ACCT_NAME", RandomUtil.getInteger(1000, 9999)); //账户中文名
			acct.put("ACCT_NATURE", RandomUtil.getInteger(1000, 9999)); //账户属性
			acct.put("ACCT_NO", RandomUtil.getInteger(1000, 9999)); //账号
			acct.put("ACCT_OPEN_DATE", RandomUtil.getInteger(1000, 9999)); //开户日期
			acct.put("ACCT_STATUS", RandomUtil.getInteger(1000, 9999)); //账户状态
			acct.put("ACCT_TYPE", RandomUtil.getInteger(1000, 9999)); //账户类型
			acct.put("ACTUAL_BAL", RandomUtil.getInteger(1000, 9999)); //可用余额
			acct.put("ACTUAL_OR_LEDGER_BAL", RandomUtil.getInteger(1000, 9999)); //按可用余额、账面余额计息标志
			acct.put("AGG_BAL", RandomUtil.getInteger(1000, 9999)); //平均余额？累计余额
			acct.put("AIO_ACCT_TYPE", RandomUtil.getInteger(1000, 9999)); //AIO账户类型
			acct.put("AIO_INTERNAL_KEY", RandomUtil.getInteger(1000, 9999)); //AIO账户主键
			acct.put("ALT_ACCT_NAME", RandomUtil.getInteger(1000, 9999)); //账户英文名
			acct.put("APPROVAL_STATUS", RandomUtil.getInteger(1000, 9999)); //批准状态
			acct.put("ATA_ACCT", RandomUtil.getInteger(1000, 9999)); //自动转账账号
			acct.put("BAL_TYPE", RandomUtil.getInteger(1000, 9999)); //余额类型
			acct.put("BASE_ACCT_NO", RandomUtil.getInteger(1000, 9999)); //主账户账号
			acct.put("BASE_INTERNAL_KEY", RandomUtil.getInteger(1000, 9999)); //主账户账户主键
			acct.put("BRANCH", RandomUtil.getInteger(1000, 9999)); //开户行
			acct.put("CALC_BAL", RandomUtil.getInteger(1000, 9999)); //计算余额
			acct.put("CATEGORY_TYPE", RandomUtil.getInteger(1000, 9999)); //客户分类
			acct.put("CB_CODE", RandomUtil.getInteger(1000, 9999)); //央行代码
			acct.put("CCY", RandomUtil.getInteger(1000, 9999)); //账户币种
			acct.put("CERTIFICATE_NO", RandomUtil.getInteger(1000, 9999)); //凭证号
			acct.put("CERT_STATUS", RandomUtil.getInteger(1000, 9999)); //凭证状态
			acct.put("CLIENT_IND", RandomUtil.getInteger(1000, 9999)); //内部客户标志
			acct.put("CLIENT_NO", RandomUtil.getInteger(1000, 9999)); //客户号
			acct.put("CLIENT_SHORT", RandomUtil.getInteger(1000, 9999)); //客户简称
			acct.put("CLIENT_TYPE", RandomUtil.getInteger(1000, 9999)); //客户类型
			acct.put("COMPENSE_ACCT_TYPE", RandomUtil.getInteger(1000, 9999)); //利率补偿标志
			acct.put("COMPENSE_CTRL_ACCT", RandomUtil.getInteger(1000, 9999)); //利率补偿主账户
			acct.put("DEL_FLAG", RandomUtil.getInteger(1000, 9999)); //删除标志
			acct.put("DEPOSIT_TYPE", RandomUtil.getInteger(1000, 9999)); //存款类型
			acct.put("DIRECT_DEBIT", RandomUtil.getInteger(1000, 9999)); //是否允许直接借记功能
			acct.put("DORMANT_DATE", RandomUtil.getInteger(1000, 9999)); //转不动户日期
			acct.put("FREE_CHEQUES", RandomUtil.getInteger(1000, 9999)); //免手续费支票张数
			acct.put("GLOBAL_ID", RandomUtil.getInteger(1000, 9999)); //证件号码
			acct.put("GLOBAL_ID_TYPE", RandomUtil.getInteger(1000, 9999)); //证件类型
			acct.put("INTERNAL_KEY", RandomUtil.getInteger(1000, 9999)); //账户主键
			acct.put("INT_STMT", RandomUtil.getInteger(1000, 9999)); //利息对账单打印标志
			acct.put("LAST_ACCT_STATUS", RandomUtil.getInteger(1000, 9999)); //上次动户账户状态
			acct.put("LAST_BAL_UPDATE", RandomUtil.getInteger(1000, 9999)); //上次余额变动日期
			acct.put("LAST_CERT_NO", RandomUtil.getInteger(1000, 9999)); //上一凭证号码
			acct.put("LAST_CHANGE_DATE", RandomUtil.getInteger(1000, 9999)); //上次动户日期
			acct.put("LAST_CHANGE_OFFICER", RandomUtil.getInteger(1000, 9999)); //上次动户柜员编号
			acct.put("LAST_PBK_LINE", RandomUtil.getInteger(1000, 9999)); //上次打印纪录数
			acct.put("LAST_PBK_UPD_BAL", RandomUtil.getInteger(1000, 9999)); //上次凭证打印余额
			acct.put("LAST_PBK_UPD_DATE", RandomUtil.getInteger(1000, 9999)); //上次凭证打印日期
			acct.put("LAST_RUN_DATE", RandomUtil.getInteger(1000, 9999)); //上次运行日期
			acct.put("LAST_TRAN_DATE", RandomUtil.getInteger(1000, 9999)); //上次清算日期
			acct.put("LEAD_ACCT_FLAG", RandomUtil.getInteger(1000, 9999)); //是否主币种账户
			acct.put("LEAD_ACCT_INTERNAL_KEY", RandomUtil.getInteger(1000, 9999)); //主币种账户主键
			acct.put("LEAD_CCY", RandomUtil.getInteger(1000, 9999)); //主币种
			acct.put("LEDGER_BAL", RandomUtil.getInteger(1000, 9999)); //账户余额
			acct.put("MAIN_ACCT_TYPE", RandomUtil.getInteger(1000, 9999)); //主账户类型
			acct.put("MAIN_TBL_KEY", RandomUtil.getInteger(1000, 9999)); //账户序号
			acct.put("MULTI_BAL_TYPE", RandomUtil.getInteger(1000, 9999)); //是否多余额
			acct.put("MULTI_CCY", RandomUtil.getInteger(1000, 9999)); //是否多币种
			acct.put("OD_FACILITY", RandomUtil.getInteger(1000, 9999)); //账户透支标志
			acct.put("OFFICER_ID", RandomUtil.getInteger(1000, 9999)); //客户经理ID
			acct.put("OLD_ACCT_OPEN_DATE", RandomUtil.getInteger(1000, 9999)); //开户日期
			acct.put("OPEN_TRAN_DATE", RandomUtil.getInteger(1000, 9999)); //开户清算日期
			acct.put("OWNERSHIP_TYPE", RandomUtil.getInteger(1000, 9999)); //归属种类
			acct.put("PREV_DAY_ACTUAL_BAL", RandomUtil.getInteger(1000, 9999)); //上次动户可用余额
			acct.put("PREV_DAY_AGG_BAL", RandomUtil.getInteger(1000, 9999)); //上次动户平均余额
			acct.put("PREV_DAY_CALC_BAL", RandomUtil.getInteger(1000, 9999)); //上次动户计算余额
			acct.put("PREV_DAY_LEDGER_BAL", RandomUtil.getInteger(1000, 9999)); //上次动户账户余额
			acct.put("PRINT_STMT", RandomUtil.getInteger(1000, 9999)); //对账单打印标志
			acct.put("PROFIT_CENTRE", RandomUtil.getInteger(1000, 9999)); //利润中心
			acct.put("RESTRAINT_PRESENT", RandomUtil.getInteger(1000, 9999)); //账户限制标志
			acct.put("STMT_PBK", RandomUtil.getInteger(1000, 9999)); //对账单/存折标志
			acct.put("TOLERANCE_AMT", RandomUtil.getInteger(1000, 9999)); //可支出总额
			acct.put("TOTAL_AUTH_OD", RandomUtil.getInteger(1000, 9999)); //授信额度
			acct.put("TOTAL_FLOATS_AMT", RandomUtil.getInteger(1000, 9999)); //未结算支票金额
			acct.put("TOTAL_PLEDGED_AMT", RandomUtil.getInteger(1000, 9999)); //冻结金额
			acct.put("TRAN_ALLOWED", RandomUtil.getInteger(1000, 9999)); //交易允许标志
			acct.put("USER_ID", RandomUtil.getInteger(1000, 9999)); //用户ID
			acct.put("VERIFICATION_OFFICER", RandomUtil.getInteger(1000, 9999)); //复核柜员
			acct.put("WS_ID", RandomUtil.getInteger(1000, 9999)); //终端ID
			acctList.add(acct);
		}
		log.info("Generate acct data done.");
		return R.ok().addData(acctList);
	}
	
	@GetMapping("/tranList")
	public R getTranList(@RequestParam("length") int length) {
		log.info("Generate tran data running...");
		List<Map<String, Object>> tranList = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < length; i++) {
			Map<String, Object> tran = new HashMap<String, Object>();
			tran.put("ACCOUNT_BRANCH", RandomUtil.getInteger(1000, 9999)); //帐户所在行
			tran.put("ACCT_DESC", RandomUtil.getInteger(1000, 9999)); //原帐户描述
			tran.put("ACCT_NO", RandomUtil.getInteger(1000, 9999)); //帐号
			tran.put("ACTUAL_BAL_AMT", RandomUtil.getInteger(1000, 9999)); //实际余额
			tran.put("ANSWERING_BANK_CORRESPONDENT", RandomUtil.getInteger(1000, 9999)); //央行开户行
			tran.put("ANSWERING_BANK_NO", RandomUtil.getInteger(1000, 9999)); //对方行行号
			tran.put("APPROVAL_DATE", RandomUtil.getInteger(1000, 9999)); //批准日期
			tran.put("APPROVING_OFFICER", RandomUtil.getInteger(1000, 9999)); //批准柜员
			tran.put("AUTO_DR_OTH_BAL_TYPE", RandomUtil.getInteger(1000, 9999)); //对方自动借记余额类型
			tran.put("BAL_TYPE", RandomUtil.getInteger(1000, 9999)); //余额类型
			tran.put("BANK_CODE", RandomUtil.getInteger(1000, 9999)); //银行代码
			tran.put("BANK_SEQ_NO", RandomUtil.getInteger(1000, 9999)); //前缀
			tran.put("BASE_ACCT_NO", RandomUtil.getInteger(1000, 9999)); //主帐号
			tran.put("BORROWING_LENDING_RATE", RandomUtil.getInteger(1000, 9999)); //贷款利率
			tran.put("BRANCH", RandomUtil.getInteger(1000, 9999)); //分行
			tran.put("BT_OFFICER_ID", RandomUtil.getInteger(1000, 9999)); //Teller柜员ID
			tran.put("CASH_ITEM", RandomUtil.getInteger(1000, 9999)); //现金项目
			tran.put("CCY", RandomUtil.getInteger(1000, 9999)); //币种
			tran.put("CD_CASH_FLOW_FLAG", RandomUtil.getInteger(1000, 9999)); //现金流更新标志
			tran.put("CR_DR_MAINT_IND", RandomUtil.getInteger(1000, 9999)); //借贷标志
			tran.put("EFFECT_DATE", RandomUtil.getInteger(1000, 9999)); //生效日期
			tran.put("FLOAT_DAYS", RandomUtil.getInteger(1000, 9999)); //在途天数
			tran.put("FORMAT", RandomUtil.getInteger(1000, 9999)); //格式
			tran.put("GL_TYPE", RandomUtil.getInteger(1000, 9999)); //帐户类型
			tran.put("INITIAL_TRAN_REF_NO", RandomUtil.getInteger(1000, 9999)); //初始交易编号
			tran.put("INTERNAL_KEY", RandomUtil.getInteger(1000, 9999)); //内在标识
			tran.put("INWD_SVC_SEQ", RandomUtil.getInteger(1000, 9999)); //提入收费序号
			tran.put("LAST_PROCESS_DATE", RandomUtil.getInteger(1000, 9999)); //上一处理日期
			tran.put("LAST_PROCESS_ERR", RandomUtil.getInteger(1000, 9999)); //上一处理错误代码
			tran.put("MESSAGE_FOR_INTRA_BANK", RandomUtil.getInteger(1000, 9999)); //复核柜员
			tran.put("MSG_CODE_OTHER", RandomUtil.getInteger(1000, 9999)); //总帐类型
			tran.put("MSG_CODE_OUR", RandomUtil.getInteger(1000, 9999)); //复核日期
			tran.put("NARRATIVE", RandomUtil.getInteger(1000, 9999)); //描述
			tran.put("NOSTRO_BRANCH", RandomUtil.getInteger(1000, 9999)); //NOstro分行
			tran.put("OFFICER_ID", RandomUtil.getInteger(1000, 9999)); //柜员ID
			tran.put("ORIG_ACCT_DESC", RandomUtil.getInteger(1000, 9999)); //跨权限复核柜员
			tran.put("ORIG_CMT_CODE", RandomUtil.getInteger(1000, 9999)); //帐户名称
			tran.put("ORIG_SETTLEMENT_DATE", RandomUtil.getInteger(1000, 9999)); //原结算日期
			tran.put("ORIG_TRAN_REF_NO", RandomUtil.getInteger(1000, 9999)); //原交易参考号
			tran.put("OTH_ACCT_BANK_CODE", RandomUtil.getInteger(1000, 9999)); //第三方帐户所在行代码
			tran.put("OTH_ACCT_CCY", RandomUtil.getInteger(1000, 9999)); //对付帐户币种
			tran.put("OTH_ACCT_DESC", RandomUtil.getInteger(1000, 9999)); //第三方帐户名称
			tran.put("OTH_ACCT_DESC2", RandomUtil.getInteger(1000, 9999)); //第三方帐户名称
			tran.put("OTH_ACCT_NO", RandomUtil.getInteger(1000, 9999)); //第三方帐号
			tran.put("OTH_ACCT_TYPE", RandomUtil.getInteger(1000, 9999)); //对方帐号类型
			tran.put("OTH_AMT", RandomUtil.getInteger(1000, 9999)); //轻控金额
			tran.put("OTH_BANK_CODE", RandomUtil.getInteger(1000, 9999)); //第三方银行代码
			tran.put("OTH_BASE_ACCT_NO", RandomUtil.getInteger(1000, 9999)); //对方主帐号
			tran.put("OTH_REFERENCE", RandomUtil.getInteger(1000, 9999)); //其他参考号
			tran.put("OTH_SPEC_CODE", RandomUtil.getInteger(1000, 9999)); //其他特殊代码
			tran.put("OTH_VALUE_DATE", RandomUtil.getInteger(1000, 9999)); //对方起息日
			tran.put("OVERRIDE_APPROVING_OFFICER", RandomUtil.getInteger(1000, 9999)); //复核柜员
			tran.put("OVERRIDE_OFFICER", RandomUtil.getInteger(1000, 9999)); //跨权限柜员
			tran.put("PBK_UPD_FLAG", RandomUtil.getInteger(1000, 9999)); //对帐单/存书更改标志
			tran.put("POST_DATE", RandomUtil.getInteger(1000, 9999)); //邮寄日期
			tran.put("PREFIX", RandomUtil.getInteger(1000, 9999)); //前缀
			tran.put("PREVIOUS_BAL_AMT", RandomUtil.getInteger(1000, 9999)); //期初余额
			tran.put("PRIMARY_SEQ_NO", RandomUtil.getInteger(1000, 9999)); //原交易序号
			tran.put("PRINT_CNT", RandomUtil.getInteger(1000, 9999)); //打印次数
			tran.put("PROFIT_CENTRE", RandomUtil.getInteger(1000, 9999)); //利润中心
			tran.put("PURPOSE_CODE", RandomUtil.getInteger(1000, 9999)); //用途代码
			tran.put("RATE_TYPE", RandomUtil.getInteger(1000, 9999)); //利率类型
			tran.put("RECEIPT_NO", RandomUtil.getInteger(1000, 9999)); //回单号
			tran.put("REFERENCE", RandomUtil.getInteger(1000, 9999)); //参考号
			tran.put("REFERENCE_BANK", RandomUtil.getInteger(1000, 9999)); //参考行
			tran.put("REFERENCE_BRANCH", RandomUtil.getInteger(1000, 9999)); //参考分行
			tran.put("REFERENCE_TYPE", RandomUtil.getInteger(1000, 9999)); //参考类型
			tran.put("REVERSAL_DATE", RandomUtil.getInteger(1000, 9999)); //冲正日期
			tran.put("REVERSAL_TRAN_TYPE", RandomUtil.getInteger(1000, 9999)); //冲正交易类型
			tran.put("SEQ_NO", RandomUtil.getInteger(1000, 9999)); //交易序号
			tran.put("SERV_CHARGE", RandomUtil.getInteger(1000, 9999)); //服务费
			tran.put("SESSION_ID", RandomUtil.getInteger(1000, 9999)); //场次
			tran.put("SORT_PRIORITY", RandomUtil.getInteger(1000, 9999)); //优先级
			tran.put("SOURCE_MODULE", RandomUtil.getInteger(1000, 9999)); //来源模块
			tran.put("SOURCE_REF_NO", RandomUtil.getInteger(1000, 9999)); //来源参考号
			tran.put("SOURCE_REF_TYPE", RandomUtil.getInteger(1000, 9999)); //来源参考类型
			tran.put("SOURCE_TYPE", RandomUtil.getInteger(1000, 9999)); //来源类型
			tran.put("SPEC_CODE", RandomUtil.getInteger(1000, 9999)); //特殊代码
			tran.put("STATUS_INFO", RandomUtil.getInteger(1000, 9999)); //状态信息
			tran.put("STMT_DATE", RandomUtil.getInteger(1000, 9999)); //对帐日期
			tran.put("TENOR", RandomUtil.getInteger(1000, 9999)); //复核柜员
			tran.put("TERMINAL_ID", RandomUtil.getInteger(1000, 9999)); //终端标识
			tran.put("TFR_INTERNAL_KEY", RandomUtil.getInteger(1000, 9999)); //转帐内部标识
			tran.put("TFR_SEQ_NO", RandomUtil.getInteger(1000, 9999)); //转帐交易号
			tran.put("TIME_STAMP", RandomUtil.getInteger(1000, 9999)); //时间戳
			tran.put("TRACE_ID", RandomUtil.getInteger(1000, 9999)); //跟踪ID
			tran.put("TRAN_AMT", RandomUtil.getInteger(1000, 9999)); //交易金额
			tran.put("TRAN_CATEGORY", RandomUtil.getInteger(1000, 9999)); //银行交易序号
			tran.put("TRAN_DATE", RandomUtil.getInteger(1000, 9999)); //交易日期
			tran.put("TRAN_DATE_TIME", RandomUtil.getInteger(1000, 9999)); //交易时间
			tran.put("TRAN_DESC", RandomUtil.getInteger(1000, 9999)); //交易描述
			tran.put("TRAN_ENTRY_DATE", RandomUtil.getInteger(1000, 9999)); //转帐录入日期
			tran.put("TRAN_REF_NO", RandomUtil.getInteger(1000, 9999)); //交易参考号
			tran.put("TRAN_TYPE", RandomUtil.getInteger(1000, 9999)); //交易类型
			tran.put("TRN", RandomUtil.getInteger(1000, 9999)); //转帐标识
			tran.put("VALUE_DATE", RandomUtil.getInteger(1000, 9999)); //起息日期
			tran.put("VERIFICATION_DATE", RandomUtil.getInteger(1000, 9999)); //复核日期
			tran.put("VERIFICATION_OFFICER", RandomUtil.getInteger(1000, 9999)); //复核柜员
			tranList.add(tran);
		}
		log.info("Generate tran data done.");
		return R.ok().addData(tranList);
	}
	
	@GetMapping("/test")
	public R test(@RequestParam("code") int code) {
		if(code == 0) {
			return R.ok();
		}else {
			return R.err(code, M.getMsg(code));
		}
	}
}
