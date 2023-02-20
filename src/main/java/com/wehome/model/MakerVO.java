package com.wehome.model;

import java.util.Date;

import lombok.Data;

@Data
public class MakerVO {

	/* 제조사 아이디 */
    private int makerId;
    
    /* 제조사 이름 */
    private String makerName;
    
    /* 국가 id */
    private String nationId;
    
    /* 제조사 국가 */
    private String nationName;
    
    /* 제조사 소개 */
    private String makerIntro;
    
    /*등록 날짜*/
    private Date regDate;

	public void setNationId(String nationId) {
		this.nationId = nationId;
		if(nationId.equals("01")) {
            this.nationName = "국내";
        } else {
            this.nationName = "국외";
        }
	}

	/* 수정 날짜 */
    private Date updateDate;
}
