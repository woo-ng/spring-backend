package com.wehome.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wehome.mapper.MakerMapper;
import com.wehome.model.MakerVO;
import com.wehome.model.Criteria;

@Service
public class MakerServiceImpl implements MakerService{
	
	private static final Logger log = LoggerFactory.getLogger(MakerServiceImpl.class);
	
	@Autowired
    MakerMapper makerMapper;
	
	@Override
    public void makerEnroll(MakerVO maker) throws Exception {
        
		makerMapper.makerEnroll(maker);
        
    }

	@Override
	public List<MakerVO> makerGetList(Criteria cri) throws Exception {
		
		log.info("(service)makerGetList()............" + cri);
		
		return makerMapper.makerGetList(cri);
	}

	@Override
	public int makerGetTotal(Criteria cri) throws Exception {
		log.info("(service)makerGetTotal()......." + cri);
        return makerMapper.makerGetTotal(cri);
	}
	
	/* 제작사 상세 페이지 */
	@Override
	public MakerVO makerGetDetail(int makerId) throws Exception {
		log.info("makerGetDetail........" + makerId);
		return makerMapper.makerGetDetail(makerId);
	}
	
	/* 제작사 정보 수정 */
	@Override
	public int makerModify(MakerVO maker) throws Exception {
		log.info("(service) makerModify........." + maker);
		return makerMapper.makerModify(maker);
	}
	
	/* 제작사 정보 삭제 */
	@Override
	public int makerDelete(int makerId) {
		
		log.info("makerDelete..........");
		
		return makerMapper.makerDelete(makerId);
	}
}
