package com.wehome.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wehome.mapper.GoodsMapper;
import com.wehome.model.Criteria;
import com.wehome.model.GoodsVO;
import com.wehome.service.GoodsService;

@RestController
public class JSONController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsMapper goodsMapper;
	
	@GetMapping("/self.json")
	public HashMap<String, Object> self() {
		List<GoodsVO> goods = goodsMapper.getGoodsList(new Criteria(1, 20));
		HashMap<String, Object> map = new HashMap<>();
		map.put("productions", goods);
		return map;
	}
}
