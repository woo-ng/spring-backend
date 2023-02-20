package com.wehome.controller;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wehome.model.AttachImageVO;
import com.wehome.model.MakerVO;
import com.wehome.model.GoodsVO;
import com.wehome.model.Criteria;
import com.wehome.model.PageDTO;
import com.wehome.service.AdminService;
import com.wehome.service.MakerService;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/admin")
public class AdminController {
 
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    
    @Autowired
    private MakerService makerService;
    
    @Autowired
	private AdminService adminService;
    
    /* 관리자 메인 페이지 이동 */
    @RequestMapping(value="main", method = RequestMethod.GET)
    public void adminMainGET() throws Exception{
        logger.info("관리자 페이지 이동");
    }
    
    /* 상품 관리(상품목록) 페이지 접속 */
	@RequestMapping(value = "goodsManage", method = RequestMethod.GET)
	public void goodsManageGET(Criteria cri, Model model) throws Exception{
	
        logger.info("상품 관리 페이지 접속");
        
        /* 상품 리스트 데이터 */
		List list = adminService.goodsGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		/* 페이지 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.goodsGetTotal(cri)));
    }
    
    /* 상품 등록 페이지 접속 */
    @RequestMapping(value = "goodsEnroll", method = RequestMethod.GET)
    public void goodsEnrollGET(Model model) throws Exception{
    	
        logger.info("상품 등록 페이지 접속");
        
        ObjectMapper objm = new ObjectMapper();
        
        List list = adminService.cateList();
        
        String cateList = objm.writeValueAsString(list);
        
        model.addAttribute("cateList", cateList);
        
        logger.info("변경 전.........." + list);
		logger.info("변경 후.........." + cateList);
    }
    
    /* 제조사 등록 페이지 접속 */
    @RequestMapping(value = "makerEnroll", method = RequestMethod.GET)
    public void makerEnrollGET() throws Exception{
        logger.info("제작사 등록 페이지 접속");
    }
    
    /* 제작사 관리 페이지 접속 */
    @RequestMapping(value = "makerManage", method = RequestMethod.GET)
    public void makerManageGET(Criteria cri, Model model) throws Exception{
        logger.info("제작사 관리 페이지 접속...................." + cri);
        
        /* 제작사 목록 출력 데이터 */
        List list = makerService.makerGetList(cri);

        if(!list.isEmpty()) {
			model.addAttribute("list",list);	// 작가 존재 경우
		} else {
			model.addAttribute("listCheck", "empty");	// 작가 존재하지 않을 경우
		}
        
        /* 페이지 이동 인터페이스 데이터 */
        int total = makerService.makerGetTotal(cri);
        
        PageDTO pageMaker = new PageDTO(cri, total);
        
        model.addAttribute("pageMaker", pageMaker);
    }
    
    /* 상품 정보 수정 */
	@PostMapping("/goodsModify")
	public String goodsModifyPOST(GoodsVO vo, RedirectAttributes rttr) {
		
		logger.info("goodsModifyPOST.........." + vo);
		
		int result = adminService.goodsModify(vo);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/goodsManage";		
		
	}
	
	/* 상품 정보 삭제 */
	@PostMapping("/goodsDelete")
	public String goodsDeletePOST(int goodsId, RedirectAttributes rttr) {
		
		logger.info("goodsDeletePOST..........");
		
		List<AttachImageVO> fileList = adminService.getAttachInfo(goodsId);
		
		if(fileList != null) {
			
			List<Path> pathList = new ArrayList();
			
			fileList.forEach(vo ->{
				
				// 원본 이미지
				Path path = Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
				pathList.add(path);
				
				// 섬네일 이미지
				path = Paths.get("C:\\upload", vo.getUploadPath(), "s_" + vo.getUuid()+"_" + vo.getFileName());
				pathList.add(path);
				
			});
			
			pathList.forEach(path ->{
				path.toFile().delete();
			});
			
		}
		
		int result = adminService.goodsDelete(goodsId);
		
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/goodsManage";
		
	}
    
    /* 제조사 등록 */
    @RequestMapping(value="makerEnroll.do", method = RequestMethod.POST)
    public String makerEnrollPOST(MakerVO maker, RedirectAttributes rttr) throws Exception{
 
        logger.info("makerEnroll :" +  maker);
        
        makerService.makerEnroll(maker);      // 작가 등록 쿼리 수행
        
        rttr.addFlashAttribute("enroll_result", maker.getMakerName());
        
        return "redirect:/admin/makerManage";
        
    }
    
    /* 제조사 상세 페이지 */
	@GetMapping({"/makerDetail", "/makerModify"})
	public void makerGetInfoGET(int makerId, Criteria cri, Model model) throws Exception {
		
		logger.info("makerDetail......." + makerId);
		
		/* 작가 관리 페이지 정보 */
		model.addAttribute("cri", cri);
		
		/* 선택 작가 정보 */
		model.addAttribute("makerInfo", makerService.makerGetDetail(makerId));
		
	}
	
	/* 제작사 정보 수정 */
	@PostMapping("/makerModify")
	public String makerModifyPOST(MakerVO maker, RedirectAttributes rttr) throws Exception{
		
		logger.info("makerModifyPOST......." + maker);
		
		int result = makerService.makerModify(maker);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/makerManage";
		
	}
	
	/* 제작사 정보 삭제 */
	@PostMapping("/makerDelete")
	public String makerDeletePOST(int makerId, RedirectAttributes rttr) {
		
		logger.info("makerDeletePOST..........");
		
		int result = 0;
		
		try {
			
			result = makerService.makerDelete(makerId);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			result = 2;
			rttr.addFlashAttribute("delete_result", result);
			
			return "redirect:/admin/makerManage";
			
		}
		
		
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/makerManage";
		
	}
	
	/* 상품 등록 */
	@PostMapping("/goodsEnroll")
	public String goodsEnrollPOST(GoodsVO goods, RedirectAttributes rttr) {
		
		logger.info("goodsEnrollPOST......" + goods);
		
		adminService.goodsEnroll(goods);
		
		rttr.addFlashAttribute("enroll_result", goods.getGoodsName());
		
		return "redirect:/admin/goodsManage";
	}
	
	/* 작가 검색 팝업창 */
	@GetMapping("/makerPop")
	public void makerPopGET(Criteria cri, Model model) throws Exception{
		
		logger.info("makerPopGET.......");
		
		cri.setAmount(5);
		
		/* 게시물 출력 데이터 */
		List list = makerService.makerGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list",list);	// 작가 존재 경우
		} else {
			model.addAttribute("listCheck", "empty");	// 작가 존재하지 않을 경우
		}
		
		
		/* 페이지 이동 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, makerService.makerGetTotal(cri)));
	
	}
	
	/* 첨부 파일 업로드 */
	@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {
		
		logger.info("uploadAjaxActionPOST..........");

		/* 이미지 파일 체크 */
		for(MultipartFile multipartFile: uploadFile) {
			
			File checkfile = new File(multipartFile.getOriginalFilename());
			String type = null;
			
			try {
				type = Files.probeContentType(checkfile.toPath());
				logger.info("MIME TYPE : " + type);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(!type.startsWith("image")) {
				
				List<AttachImageVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
				
			}
			
		}// for
		
		/* 날짜 폴더 경로 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		String datePath = str.replace("-", File.separator);
		
		String uploadFolder = "C:\\upload";
		
		/* 폴더 생성 */
		File uploadPath = new File(uploadFolder , datePath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		/* 이미저 정보 담는 객체 */
		List<AttachImageVO> list = new ArrayList();
		
		// 향상된 for
				for(MultipartFile multipartFile : uploadFile) {
					/* 이미지 정보 객체 */
					AttachImageVO vo = new AttachImageVO();
					
					/* 파일 이름 */
					String uploadFileName = multipartFile.getOriginalFilename();
					vo.setFileName(uploadFileName);
					vo.setUploadPath(datePath);
					
					/* uuid 적용 파일 이름 */
					String uuid = UUID.randomUUID().toString();
					vo.setUuid(uuid);
					
					uploadFileName = uuid + "_" + uploadFileName;
					
					/* 파일 위치, 파일 이름을 합친 File 객체 */
					File saveFile = new File(uploadPath, uploadFileName);
					
					/* 파일 저장 */
					try {
						
						multipartFile.transferTo(saveFile);
						
//						/* 썸네일 생성(ImageIO) */
//						File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
//						
//						BufferedImage bo_image = ImageIO.read(saveFile);
//						
//							/* 비율 */
//							double ratio = 3;
//							/*넓이 높이*/
//							int width = (int) (bo_image.getWidth() / ratio);
//							int height = (int) (bo_image.getHeight() / ratio);						
//						
//						BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//										
//						Graphics2D graphic = bt_image.createGraphics();
//						
//						graphic.drawImage(bo_image, 0, 0,width,height, null);
//							
//						ImageIO.write(bt_image, "jpg", thumbnailFile);
						
						/* 방법 2 */
						File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);	
						
						BufferedImage bo_image = ImageIO.read(saveFile);

							//비율 
							double ratio = 3;
							//넓이 높이
							int width = (int) (bo_image.getWidth() / ratio);
							int height = (int) (bo_image.getHeight() / ratio);					
						
						
						Thumbnails.of(saveFile)
				        .size(width, height)
				        .toFile(thumbnailFile);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					list.add(vo);
				} // for 종료
				
				ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);
				
				return result;
	}
	
	/* 상품 조회 페이지 */
	@GetMapping({"/goodsDetail", "/goodsModify"})
	public void goodsGetInfoGET(int goodsId, Criteria cri, Model model) throws JsonProcessingException {
		
		logger.info("goodsGetInfo()........." + goodsId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		/* 카테고리 리스트 데이터 */
		model.addAttribute("cateList", mapper.writeValueAsString(adminService.cateList()));
		
		/* 목록 페이지 조건 정보 */
		model.addAttribute("cri", cri);
		
		/* 조회 페이지 정보 */
		model.addAttribute("goodsInfo", adminService.goodsGetDetail(goodsId));
		
	}
	
	/* 이미지 파일 삭제 */
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName){
		
		logger.info("deleteFile........" + fileName);
		
		File file = null;
		
		try {
			/* 썸네일 파일 삭제 */
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			/* 원본 파일 삭제 */
			String originFileName = file.getAbsolutePath().replace("s_", "");
			
			logger.info("originFileName : " + originFileName);
			
			file = new File(originFileName);
			
			file.delete();
			
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
			
		} // catch
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
		
	}
}
