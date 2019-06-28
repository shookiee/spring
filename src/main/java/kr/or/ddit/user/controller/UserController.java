package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.model.UserVOValidator;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.PartUtil;

@RequestMapping("/user")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userService")
	private IUserService userService;
	
	
	/**
	* Method : userList
	* 작성자 : PC23
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 사용자 전체 리스트
	*/
	@RequestMapping("/list")
	public String userList(Model model) {
		model.addAttribute("userList", userService.userList());
		
		return "user/userList";
	}
	
	
	@RequestMapping("/userListExcel")
	public String userListExcel(Model model, String filename) {
		
		List<String> header = new ArrayList<String>();
		header.add("사용자 ID");
		header.add("사용자명");
		header.add("사용자 별명");
		header.add("주소1");
		header.add("주소2");
		header.add("우편번호");
		header.add("생일");
		
		model.addAttribute("filename", filename);
		model.addAttribute("header", header);
		model.addAttribute("data", userService.userList());
		
		return "userExcelView";
	}
	
	
	/**
	* Method : userPaginglist
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 페이징 리스트
	*/
	@RequestMapping("/pagingList")
//	public String userPaginglist(@RequestParam(name="page", defaultValue="1") int page, 
//														@RequestParam(name="pageSize", defaultValue="10") int pageSize,
//														Model model) {
//
//		logger.debug("page: {}", page);
//		logger.debug("pageSize: {}", pageSize);
//		
//		PageVO pageVo = new PageVO(page, pageSize);
	public String userPagingList(PageVO pageVo, Model model) {	
		
		logger.debug("pageVo : {}", pageVo);
		
		Map<String, Object> resultMap = userService.userPagingList(pageVo); 
		
		List<UserVO> userList = (List<UserVO>) resultMap.get("userList");
		int paginationSize = (Integer) resultMap.get("paginationSize");

		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);
		
		return "tiles.userPagingList";
	}
	
	
	/**
	* Method : user
	* 작성자 : PC23
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 상세 조회
	*/
	@RequestMapping("/user")
	public String user(String userId, Model model) {
		
		UserVO userVo = userService.getUser(userId);
		model.addAttribute("userVo", userVo);
		
		return "user/user";
	}
	
	
	/**
	* Method : userJason
	* 작성자 : PC23
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 정보 json 응답 
	*/
	@RequestMapping("/userJason")
	public String userJason(String userId, Model model) {
		
		UserVO userVo = userService.getUser(userId);
		model.addAttribute("userVo", userVo);
		
		return "jsonView";		// bean name
	}
	
	
	/**
	* Method : userForm
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 등록 화면
	*/
	@RequestMapping(path = "/form", method = RequestMethod.GET)
	public String userForm() {
		return "user/userForm";
	}

	
	/**
	* Method : userForm
	* 작성자 : PC23
	* 변경이력 :
	* @param userVo
	* @param file
	* @param model
	* @return
	* Method 설명 : 사용자 등록
	*/
//	@RequestMapping(path ="/form", method=RequestMethod.POST)
	public String userForm(UserVO userVo, BindingResult result, MultipartFile profile, Model model)  { // BindingResult는 무조건 value객체 뒤에!
		
		new UserVOValidator().validate(userVo, result);
		if(result.hasErrors()) {
			return "user/userForm";
		}
		
		String viewName = "";
		
		UserVO dbUser = userService.getUser(userVo.getUserId());
		
		if(dbUser == null) {
		
			if(profile.getSize() > 0) {
				String filename= profile.getOriginalFilename();
				String ext = PartUtil.getExt(filename);
				
				String uploadPath = PartUtil.getUploadPath();
				String filePath = uploadPath + File.separator  + UUID.randomUUID().toString() + ext;
				userVo.setPath(filePath);
				userVo.setFileName(filename);
				
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			
			}
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			int insertCnt = userService.insertUser(userVo);
			
			if(insertCnt ==1)
				viewName = "redirect:/user/pagingList";
			} else {
				model.addAttribute("msg", "이미 존재하는 사용자 입니다.");
				viewName = userForm();
			}
		
		return viewName;
	}
	
	
	/**
	* Method : userFormJsr
	* 작성자 : PC23
	* 변경이력 :
	* @param userVo
	* @param file
	* @param model
	* @return
	* Method 설명 : 사용자 등록
	*/
	@RequestMapping(path ="/form", method=RequestMethod.POST)
	public String userFormJsr(@Valid UserVO userVo, BindingResult result, MultipartFile profile, Model model)  { // BindingResult는 무조건 value객체 뒤에!
	
		if(result.hasErrors()) {
			return "user/userForm";
		}
		
		String viewName = "";
		
		UserVO dbUser = userService.getUser(userVo.getUserId());
		
		if(dbUser == null) {
		
			if(profile.getSize() > 0) {
				String filename= profile.getOriginalFilename();
				String ext = PartUtil.getExt(filename);
				
				String uploadPath = PartUtil.getUploadPath();
				String filePath = uploadPath + File.separator  + UUID.randomUUID().toString() + ext;
				userVo.setPath(filePath);
				userVo.setFileName(filename);
				
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			
			}
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			int insertCnt = userService.insertUser(userVo);
			
			if(insertCnt ==1)
				viewName = "redirect:/user/pagingList";
			} else {
				model.addAttribute("msg", "이미 존재하는 사용자 입니다.");
				viewName = userForm();
			}
		
		return viewName;
	}
	
	
	/**
	* Method : profile
	* 작성자 : PC23
	* 변경이력 :
	* @param userId
	* @param request
	* @param response
	* @throws IOException
	* Method 설명 : 사용자 사진 응답 생성
	*/
	@RequestMapping("/profile")
	public String profile(String userId, Model model) throws IOException {		
    	// 사용자 정보(path)를 조회
		UserVO userVo = userService.getUser(userId);
		model.addAttribute("userVo", userVo);
		
		return "profileView";
	}
	
	
	/**
	* Method : userModify
	* 작성자 : PC23
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 수정 화면 요청
	*/
	@RequestMapping(path="/modify", method=RequestMethod.GET)
	public String userModify(String userId, Model model) {
		model.addAttribute("userVo", userService.getUser(userId));
		return "user/userModify";
	}
	
	
	@RequestMapping(path="/modify", method=RequestMethod.POST)
	public String userModify(UserVO userVo, MultipartFile profile, HttpSession session, Model model,
													RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		// 추후 ajax 요청으로 분리
		//	userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		
		if(profile.getSize() > 0) {
			String fileName = profile.getOriginalFilename();
			String ext = PartUtil.getExt(fileName);
			
			String uploadPath = PartUtil.getUploadPath();
			String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
			
			userVo.setPath(filePath);
			userVo.setFileName(fileName);
			
			profile.transferTo(new File(filePath));
		}
		
		int updateCnt = userService.updateUser(userVo);
		
		if(updateCnt == 1 ) {
//			session.setAttribute("msg", "수정 되었습니다");
			redirectAttributes.addFlashAttribute("msg", "수정 되었습니다");
			redirectAttributes.addAttribute("userId", userVo.getUserId());	// 파라미터를 자동으로 붙여준다
			return"redirect:/user/user"; 
		} else {
			return userModify(userVo.getUserId(), model);
		}
		
	}
	
	
}
 