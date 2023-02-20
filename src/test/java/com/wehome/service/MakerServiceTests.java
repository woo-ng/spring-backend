package com.wehome.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wehome.model.MakerVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MakerServiceTests {
    
    /*AuthoreService 의존성 주입*/
    @Autowired
    private MakerService service;
    
    @Test
    public void authorEnrollTest() throws Exception {
 
        MakerVO maker = new MakerVO();
        
        maker.setNationId("01");
        maker.setMakerName("테스트");
        maker.setMakerIntro("테스트 소개");
        
        service.makerEnroll(maker);
        
    }
 
}
