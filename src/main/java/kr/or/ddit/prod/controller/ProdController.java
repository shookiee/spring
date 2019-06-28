package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.prod.service.IProdService;

@RequestMapping("/prod")
@Controller
public class ProdController {
	
	@Resource(name="prodService")
	private IProdService prodService;
	
	
	@RequestMapping("/pagingList")
	public String lprodList(PageVO pageVo, Model model) {
		Map<String, Object> resultMap = prodService.prodPagingList(pageVo);
		
		List<ProdVO> prodList = (List<ProdVO>) resultMap.get("prodList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		model.addAttribute("prodList", prodList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);
		
		return "prod/prodPagingList";
	}
	
	
	@RequestMapping("/prod")
	public String prod(String prod_id, Model model) {
		ProdVO prodVo = prodService.getProd(prod_id);
		String prod_name = prodVo.getProd_name();
		
		model.addAttribute("prod_name", prod_name);
		
		return "prod/prod";
	}
}