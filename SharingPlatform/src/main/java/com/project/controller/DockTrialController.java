package com.project.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.entity.TDemandEntity;
import com.project.exception.RRException;
import com.project.info.DockTrialInfo;
import com.project.info.loginUserInfo;
import com.project.service.DockTrialService;
import com.project.utils.R;
import com.project.utils.StringUtil;

@RestController
@RequestMapping("/dock")
public class DockTrialController extends  AbstractController{
	@Autowired
	private DockTrialService dockTrialService;
	
	@RequestMapping("/list")
	public DockTrialInfo trialInfoList(@RequestParam Map<String, Object> map, HttpSession session) {
		String token = map.get("token") == null ? "" : map.get("token").toString();
		if(StringUtil.isNull(token)){			
		}		
		map.put("state", "01");
		return dockTrialService.trialInfoList(map);
	}
	/**
	 * 提供方确认需求的查询
	 */

	@RequestMapping("/findTrial")
	public DockTrialInfo findTrial(@RequestParam Map<String, Object> map, HttpSession session) {
		String token = map.get("token") == null ? "" : map.get("token").toString();
		if(StringUtil.isNull(token)){			
		}		
		map.put("state", "02");
		return dockTrialService.findTrialPeople(map);
	}
	/**
	 * 提供方完成确认的查询(finishVerify)
	 */

	@RequestMapping("/finishVerify")
	public DockTrialInfo finishVerify(@RequestParam Map<String, Object> map, HttpSession session) {
		String token = map.get("token") == null ? "" : map.get("token").toString();
		if(StringUtil.isNull(token)){			
		}		
		map.put("state", "04");
		return dockTrialService.finishVerify(map);
	}
	/**
	 * 对接人编辑
	 */
	@RequestMapping("/edit")
	public R edit(@RequestParam Map<String, Object> params, HttpSession session){		
		String token = params.get("token") == null ? "" : params.get("token").toString();
		if(StringUtil.isNull(token)){
			return R.error("登录状态异常！");
		}		
		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
		String dealResult = params.get("dealResult") == null ? "" : params.get("dealResult").toString();
		if(StringUtil.isNull(dealResult)){
			return R.error("请选择处理结果！");
		}
		String backCause=params.get("backCause")==null ? "" : params.get("backCause").toString();
		if(dealResult.equals("2")){
			if(StringUtil.isNull(backCause)){				
				return R.error("请选择退回原因！");
			}		
		}	
		dockTrialService.dealTrial(params);		
		return R.ok();
	}
	/**
	 * 提供单位确认需求的处理
	 */
	@RequestMapping("/editDleal")
	public R editDleal(@RequestParam Map<String, Object> params, HttpSession session){	
		String token = params.get("token") == null ? "" : params.get("token").toString();	
		if(StringUtil.isNull(token)){
			return R.error("登录状态异常！");
		}		
		
		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
		String dealResult = params.get("dealResult") == null ? "" : params.get("dealResult").toString();
		if(StringUtil.isNull(dealResult)){
			return R.error("请选择处理结果！");
		}
		String dealReasonYes=params.get("dealReasonYes")==null ? "" : params.get("dealReasonYes").toString();
		String dealReasonNo=params.get("dealReasonNo")==null ? "" : params.get("dealReasonNo").toString();		
		if(StringUtil.isNull(dealReasonYes)&&StringUtil.isNull(dealReasonNo)){				
				return R.error("请选择处理原因！");
		}		
		dockTrialService.dealProvide(params);		
		return R.ok();
	}
	/**
	 * 提供单位完成需求的处理
	 */
	@RequestMapping("/finishDleal")
	public R finishDleal(@RequestParam Map<String, Object> params, HttpSession session){	
		String token = params.get("token") == null ? "" : params.get("token").toString();	
		if(StringUtil.isNull(token)){
			return R.error("登录状态异常！");
		}		

		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
		} catch (RRException e) {
			return R.error(e.getMsg());
		}			
		dockTrialService.finishDleal(params);
		return R.ok();
	}
	/**
	 * 上传文件
	 */
	@RequestMapping("/importD")
	public void importD(@RequestParam(value = "importDFile", required = false) MultipartFile file){	
		System.out.println(12);
		String fileName = file.getOriginalFilename();
		try {
			file.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
