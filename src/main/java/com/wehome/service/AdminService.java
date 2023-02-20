package com.wehome.service;

import java.util.List;

import com.wehome.model.AttachImageVO;
import com.wehome.model.GoodsVO;
import com.wehome.model.CateVO;
import com.wehome.model.Criteria;

public interface AdminService {

	/* 상품 등록 */
	public void goodsEnroll(GoodsVO goods);
	
	/* 카테고리 리스트 */
	public List<CateVO> cateList();
	
	/* 상품 리스트 */
	public List<GoodsVO> goodsGetList(Criteria cri);
	
	/* 상품 총 개수 */
	public int goodsGetTotal(Criteria cri);
	
	/* 상품 조회 페이지 */
	public GoodsVO goodsGetDetail(int goodsId);
	
	/* 상품 수정 */
	public int goodsModify(GoodsVO vo);
	
	/* 상품 정보 삭제 */
	public int goodsDelete(int goodsId);
	
	/* 지정 상품 이미지 정보 얻기 */
	public List<AttachImageVO> getAttachInfo(int goodsId);
}
