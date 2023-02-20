package com.wehome.mapper;

import java.util.List;

import com.wehome.model.GoodsVO;
import com.wehome.model.CateFilterDTO;
import com.wehome.model.CateVO;
import com.wehome.model.Criteria;

public interface GoodsMapper {
	
	/* 상품 검색 */
	public List<GoodsVO> getGoodsList(Criteria cri);
	
	/* 상품 총 갯수 */
	public int goodsGetTotal(Criteria cri);
	
	/* 제조사 id 리스트 요청 */
	public String[] getMakerIdList(String keyword);
	
	/* 국내 카테고리 리스트 */
	public List<CateVO> getCateCode1();
	
	/* 외국 카테고리 리스트 */
	public List<CateVO> getCateCode2();
	
	/* 검색 대상 카테고리 리스트 */
	public String[] getCateList(Criteria cri);
	
	/* 카테고리 정보(+검색대상 갯수) */
	public CateFilterDTO getCateInfo(Criteria cri);
	
	/* 상품 정보 */
	public GoodsVO getGoodsInfo(int goodsId);

}
