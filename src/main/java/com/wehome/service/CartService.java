package com.wehome.service;

import java.util.List;

import com.wehome.model.CartDTO;

public interface CartService {

	/* 장바구니 추가 */
	public int addCart(CartDTO cart);
	
	/* 장바구니 정보 리스트 */
	public List<CartDTO> getCartList(String memberId);
}
