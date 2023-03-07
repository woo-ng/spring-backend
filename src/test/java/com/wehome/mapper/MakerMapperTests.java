package com.wehome.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wehome.model.MakerVO;
import com.wehome.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MakerMapperTests {
 
    @Autowired
    private MakerMapper mapper;
    
    
    
    /* 제작사 등록 테스트 */
    /*
    @Test
    public void makerEnroll() throws Exception{
        
        MakerVO maker = new MakerVO();
        
        maker.setNationId("01");
        maker.setMakerName("테스트");
        maker.setMakerIntro("테스트 소개");
        
        mapper.makerEnroll(maker);
        
    }    
    */
    
    /* 제작사 목록 테스트 */
    /*
    @Test
    public void makerGetListTest() throws Exception{
        
        Criteria cri = new Criteria(3,10);    // 3페이지 & 10개 행 표시
        cri.setKeyword("홍준");
        List<MakerVO> list = mapper.makerGetList(cri);
        
        for(int i = 0; i < list.size(); i++) {
            System.out.println("list" + i + ".........." + list.get(i));
        }
        
    }
    */
    
    /* 제작사 총 수 */
    /*
    @Test
    public void makerGetTotalTest() throws Exception{
    	
    	Criteria cri = new Criteria();
    	cri.setKeyword("난도");
    	
    	int total = mapper.makerGetTotal(cri);
    	
    	System.out.println("total..............." + total);
    }
    */
    
    /* 제작사 상세 페이지 */
	/*
    @Test
	public void makerGetDetailTest() {
		
		int makerId = 20;
		
		MakerVO maker = mapper.makerGetDetail(makerId);
		
		System.out.println("maker......." + maker);
		
	}
	*/
    
    
//	/* 제작사 정보 수정 */
//	@Test
//	public void authorModifyTest() {
//		
//		AuthorVO author = new AuthorVO();
//				
//		author.setAuthorId(1);
//		System.out.println("수정 전...................." + mapper.authorGetDetail(author.getAuthorId()));
//		
//		author.setAuthorName("수정");
//		author.setNationId("01");
//		author.setAuthorIntro("소개 수정 하였습니다.");
//		
//		mapper.authorModify(author);
//		System.out.println("수정 후...................." + mapper.authorGetDetail(author.getAuthorId()));
//		
//	}
    
    /* 제작사 정보 삭제 */
	@Test
	public void makerDeleteTest() {
		
		
		int makerId = 5;
		
		int result = mapper.makerDelete(makerId);
		
		if(result == 1) {
			System.out.println("삭제 성공");
		}
		
	}
}
