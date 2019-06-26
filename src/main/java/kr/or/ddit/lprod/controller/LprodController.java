package kr.or.ddit.lprod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.prod.service.IProdService;

@RequestMapping("/lprod")
@Controller
public class LprodController {

	private static final Logger logger = LoggerFactory.getLogger(LprodController.class);
	
	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	@Resource(name="prodService")
	private IProdService prodService;
	
	@RequestMapping("/pagingList")
	public String lprodList(PageVO pageVo, Model model) {
		Map<String, Object> resultMap = lprodService.lprodPagingList(pageVo);
		
		List<LprodVO> lprodList = (List<LprodVO>) resultMap.get("lprodList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		model.addAttribute("lprodList", lprodList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);
		
		return "lprod/lprodPagingList";
	}
	

	@RequestMapping("/lprod")
	public String lprod(String lprod_gu, Model model) {
		String prod_lgu = lprod_gu;
		List<ProdVO> prodList = prodService.prodList(prod_lgu); 
		
		model.addAttribute("prodList", prodList);
		
		return "lprod/lprod";
	}
}
