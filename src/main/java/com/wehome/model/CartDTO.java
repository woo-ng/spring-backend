package com.wehome.model;

import lombok.Data;

@Data
public class CartDTO {

	private int cartId;

	private String memberId;

	private int goodsId;

	private int goodsCount;

	// book

	private String goodsName;

	private int goodsPrice;

	private double goodsDiscount;

	// 추가
	private int salePrice;

	private int totalPrice;

	private int point;

	private int totalPoint;

	public void initSaleTotal() {
		this.salePrice = (int) (this.goodsPrice * (1 - this.goodsDiscount));
		this.totalPrice = this.salePrice * this.goodsCount;
		this.point = (int) (Math.floor(this.salePrice * 0.05));
		this.totalPoint = this.point * this.goodsCount;
	}
}
