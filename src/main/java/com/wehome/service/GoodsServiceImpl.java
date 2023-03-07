package com.wehome.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wehome.mapper.AdminMapper;
import com.wehome.mapper.AttachMapper;
import com.wehome.mapper.GoodsMapper;
import com.wehome.model.AttachImageVO;
import com.wehome.model.GoodsVO;
import com.wehome.model.CateFilterDTO;
import com.wehome.model.CateVO;
import com.wehome.model.Criteria;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private AdminMapper adminMapper;
	
	/* 상품 검색 */
	@Override
	public List<GoodsVO> getGoodsList(Criteria cri) {
		
		log.info("getGoodsList().......");
		
		String type = cri.getType();
		String[] typeArr = type.split("");
		String[] makerArr = goodsMapper.getMakerIdList(cri.getKeyword());
		
		
		if(type.equals("A") || type.equals("AC") || type.equals("AT") || type.equals("ACT")) {
			if(makerArr.length == 0) {
				return new ArrayList();
			}
		}
		
		for(String t : typeArr) {
			if(t.equals("A")) {
				cri.setMakerArr(makerArr);
			}
		}	
		
		List<GoodsVO> list = goodsMapper.getGoodsList(cri);
		
		list.forEach(goods -> {
			
			int goodsId = goods.getGoodsId();
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(goodsId);
			
			goods.setImageList(imageList);
			
		});
		
		return list;
	}
	/* 사품 총 갯수 */
	@Override
	public int goodsGetTotal(Criteria cri) {
		
		log.info("goodsGetTotal().......");
		
		return goodsMapper.goodsGetTotal(cri);
		
	}
	
	/* 국내 카테고리 리스트 */
	@Override
	public List<CateVO> getCateCode1() {
		
		log.info("getCateCode1().........");
		
		return goodsMapper.getCateCode1();
	}

	/* 외국 카테고리 리스트 */
	@Override
	public List<CateVO> getCateCode2() {
		
		log.info("getCateCode2().........");
		
		return goodsMapper.getCateCode2();
	}
	
	/* 검색결과 카테고리 필터 정보 */
	@Override
	public List<CateFilterDTO> getCateInfoList(Criteria cri) {
		
		List<CateFilterDTO> filterInfoList = new ArrayList<CateFilterDTO>();
		
		String[] typeArr = cri.getType().split("");
		String [] makerArr;
		
		for(String type : typeArr) {
			if(type.equals("A")){
				makerArr = goodsMapper.getMakerIdList(cri.getKeyword());
				if(makerArr.length == 0) {
					return filterInfoList;
				}
				cri.setMakerArr(makerArr);
			}
		}
		
		String[] cateList = goodsMapper.getCateList(cri);
		
		String tempCateCode = cri.getCateCode();
		
		for(String cateCode : cateList) {
			cri.setCateCode(cateCode);
			CateFilterDTO filterInfo = goodsMapper.getCateInfo(cri);
			filterInfoList.add(filterInfo);
		}
		
		cri.setCateCode(tempCateCode);
		
		return filterInfoList;
		
	}
	
	/* 상품 정보 */
	@Override
	public GoodsVO getGoodsInfo(int goodsId) {
		
		GoodsVO goodsInfo = goodsMapper.getGoodsInfo(goodsId);
		goodsInfo.setImageList(adminMapper.getAttachInfo(goodsId));
		
		return goodsInfo;
	}

}
