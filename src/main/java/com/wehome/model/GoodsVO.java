package com.wehome.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class GoodsVO {

	/* 상품 id */
	private int goodsId;
	
	/* 상품 이름 */
	private String goodsName;
	
	/* 제조사 id */
	private int makerId;
	
	/* 제조사 이름 */
	private String makerName;
	
	/* 출시일 */
	private String publeYear;
	
	/* 유통사 */
	private String publisher;
	
	/* 카테고리 코드 */
	private String cateCode;
	
	/* 카테고리 이름 */
	private String cateName;
	
	/* 상품 가격 */
	private int goodsPrice;
	
	/* 상품 재고 */
	private int goodsStock;
	
	/* 상품 할인률(백분율) */
	private double goodsDiscount;
	
	/* 상품 소개 */
	private String goodsIntro;
	
	/* 상품 목차 */
	private String goodsContents;
	
	/* 등록 날짜 */
	private Date regDate;
	
	/* 수정 날짜 */
	private Date updateDate;
	
	/* 이미지 정보 */
	private List<AttachImageVO> imageList;
}
