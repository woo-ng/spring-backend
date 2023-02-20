package com.wehome.service;

import java.util.List;

import com.wehome.model.GoodsVO;
import com.wehome.model.CateFilterDTO;
import com.wehome.model.CateVO;
import com.wehome.model.Criteria;

public interface GoodsService {
	
	/* 상품 검색 */
	public List<GoodsVO> getGoodsList(Criteria cri);
	
	/* 상품 총 갯수 */
	public int goodsGetTotal(Criteria cri);
	
	/* 국내 카테고리 리스트 */
	public List<CateVO> getCateCode1();
	
	/* 외국 카테고리 리스트 */
	public List<CateVO> getCateCode2();
	
	/* 검색결과 카테고리 필터 정보 */
	public List<CateFilterDTO> getCateInfoList(Criteria cri);
	
	/* 상품 정보 */
	public GoodsVO getGoodsInfo(int goodsId);

}
