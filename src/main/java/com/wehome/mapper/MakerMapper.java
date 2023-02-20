package com.wehome.mapper;

import java.util.List;

import com.wehome.model.MakerVO;
import com.wehome.model.Criteria;

public interface MakerMapper {

	/* 제조사 등록 */
    public void makerEnroll(MakerVO maker);
    
    /* 제조사 목록 */
    public List<MakerVO> makerGetList(Criteria cri);
    
    /* 제조사 총 수 */
    public int makerGetTotal(Criteria cri);
    
    /* 제조사 상세 */
	public MakerVO makerGetDetail(int makerId);
	
	/* 제조사 정보 수정 */
	public int makerModify(MakerVO maker);
	
	/* 제조사 정보 삭제 */
	public int makerDelete(int makerId);
}
