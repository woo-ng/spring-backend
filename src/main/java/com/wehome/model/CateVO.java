package com.wehome.model;

import lombok.Data;

@Data
public class CateVO {
	
	/* 카테고리 등급 */
	private int tier;
	
	/* 카테고리 이름 */
	private String cateName;
	
	/* 카테고리 넘버 */
	private int cateCode;
	
	/* 상위 카테고리 */
	private String cateParent;	
}
