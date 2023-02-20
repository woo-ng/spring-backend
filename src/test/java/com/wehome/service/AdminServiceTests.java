package com.wehome.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wehome.model.AttachImageVO;
import com.wehome.model.GoodsVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminServiceTests {

	@Autowired
	private AdminService service;
	
	/* 상품 등록 & 상품 이미지 등록 테스트 */
	@Test
	public void goodsEnrollTEsts() {

		GoodsVO goods = new GoodsVO();
		// 상품 정보
		goods.setGoodsName("service 테스트");
		goods.setMakerId(3);
		goods.setPubleYear("2021-03-18");
		goods.setPublisher("출판사");
		goods.setCateCode("202001");
		goods.setGoodsPrice(20000);
		goods.setGoodsStock(300);
		goods.setGoodsDiscount(0.23);
		goods.setGoodsIntro("책 소개 ");
		goods.setGoodsContents("책 목차 ");

		// 이미지 정보
		List<AttachImageVO> imageList = new ArrayList<AttachImageVO>(); 
		
		AttachImageVO image1 = new AttachImageVO();
		AttachImageVO image2 = new AttachImageVO();
		
		image1.setFileName("test Image 1");
		image1.setUploadPath("test image 1");
		image1.setUuid("test1111");
		
		image2.setFileName("test Image 2");
		image2.setUploadPath("test image 2");
		image2.setUuid("test2222");
		
		imageList.add(image1);
		imageList.add(image2);
		
		goods.setImageList(imageList);        
		
		// bookEnroll() 메서드 호출
		service.goodsEnroll(goods);
		
		System.out.println("등록된 VO : " + goods);
		
		
	}
}
