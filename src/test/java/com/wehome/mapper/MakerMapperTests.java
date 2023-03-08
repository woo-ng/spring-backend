package com.wehome.mapper;

import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wehome.model.MakerVO;
import com.wehome.model.Criteria;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MakerMapperTests {
 
    @Autowired
    private MakerMapper mapper;

    private int makerId;

    /* 제작사 등록 테스트 */
    @Before
    public void makerEnrollTest() throws Exception{
        
        MakerVO maker = new MakerVO();
        
        maker.setNationId("01");
        maker.setMakerName("테스트");
        maker.setMakerIntro("테스트 소개");
        
        mapper.makerEnroll(maker);
        makerId = maker.getMakerId();
    }
    
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
    
    
	/* 제작사 정보 수정 */
	@Test
	public void makerModifyTest() {
		MakerVO maker = new MakerVO();

		maker.setMakerId(makerId);

		maker.setMakerName("수정");
		maker.setNationId("01");
		maker.setMakerIntro("소개 수정 하였습니다.");

		mapper.makerModify(maker);
		assertEquals("수정", maker.getMakerName());
	}
    
    /* 제작사 정보 삭제 */
	@After
	public void makerDeleteTest() throws Exception {
		int result = mapper.makerDelete(makerId);
        assertEquals(1, result);
	}
}
