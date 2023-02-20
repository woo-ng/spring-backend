package com.wehome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wehome.mapper.AdminMapper;
import com.wehome.model.AttachImageVO;
import com.wehome.model.GoodsVO;
import com.wehome.model.CateVO;
import com.wehome.model.Criteria;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;	
	
	/* 상품 등록 */
	@Transactional
	@Override
	public void goodsEnroll(GoodsVO goods) {
		
		log.info("(srevice)goodsEnroll........");
		
		adminMapper.goodsEnroll(goods);
		
		if(goods.getImageList() == null || goods.getImageList().size() <= 0) {
			return;
		}
		
		goods.getImageList().forEach(attach ->{
			
			attach.setGoodsId(goods.getGoodsId());
			adminMapper.imageEnroll(attach);
			
		});
	}
	
	/* 카테고리 리스트 */
	@Override
	public List<CateVO> cateList() {
		
		log.info("(service)cateList........");
		
		return adminMapper.cateList();
	}
	
	/* 상품 리스트 */
	@Override
	public List<GoodsVO> goodsGetList(Criteria cri) {
		log.info("goodsGetTotalList()..........");
		return adminMapper.goodsGetList(cri);
	}

	/* 상품 총 갯수 */
	public int goodsGetTotal(Criteria cri) {
		log.info("goodsGetTotal().........");
		return adminMapper.goodsGetTotal(cri);
	}
	
	/* 상품 조회 페이지 */
	@Override
	public GoodsVO goodsGetDetail(int goodsId) {
		
		log.info("(service)goodsGetDetail......." + goodsId);
		
		return adminMapper.goodsGetDetail(goodsId);
	}
	
	/* 상품 정보 수정 */
	@Transactional
	@Override
	public int goodsModify(GoodsVO vo) {
		
		int result = adminMapper.goodsModify(vo);
		
		if(result == 1 && vo.getImageList() != null && vo.getImageList().size() > 0) {
			
			adminMapper.deleteImageAll(vo.getGoodsId());
			
			vo.getImageList().forEach(attach -> {
				
				attach.setGoodsId(vo.getGoodsId());
				adminMapper.imageEnroll(attach);
				
			});
			
		}
		
		return result;
		
	}
	
	/* 상품 정보 삭제 */
	@Override
	@Transactional
	public int goodsDelete(int goodsId) {

		log.info("goodsDelete..........");
		
		adminMapper.deleteImageAll(goodsId);
		
		return adminMapper.goodsDelete(goodsId);
	}
	
	/* 지정 상품 이미지 정보 얻기 */
	@Override
	public List<AttachImageVO> getAttachInfo(int goodsId) {
		
		log.info("getAttachInfo........");
		
		return adminMapper.getAttachInfo(goodsId);
	}
}
