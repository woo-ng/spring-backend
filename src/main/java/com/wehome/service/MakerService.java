package com.wehome.service;

import java.util.List;

import com.wehome.model.MakerVO;
import com.wehome.model.Criteria;

public interface MakerService {

	/* 제작사 등록 */
    public void makerEnroll(MakerVO maker) throws Exception;
    
    /* 제작사 목록 */
    public List<MakerVO> makerGetList(Criteria cri) throws Exception;
    
    /* 제작사 총 수 */
    public int makerGetTotal(Criteria cri) throws Exception;
    
    /* 제조사 상세 페이지 */
	public MakerVO makerGetDetail(int makerId) throws Exception;
	
	/* 제조사 정보 수정 */
	public int makerModify(MakerVO maker) throws Exception;
	
	/* 제조사 정보 삭제 */
	public int makerDelete(int makerId);
}
