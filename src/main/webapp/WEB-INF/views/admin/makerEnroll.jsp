<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/admin/makerEnroll.css">

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
</head>
<body>
 			<%@include file="../includes/admin/header.jsp" %>
                <div class="admin_content_wrap">
                    <div class="admin_content_subject"><span>제작사 등록</span></div>
                    <div class="admin_content_main">
                    	<form action="/admin/makerEnroll.do" method="post" id="enrollForm">
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>제작사 이름</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="makerName">
                    				<span id="warn_makerName">제작사 이름을 입력 해주세요.</span>
                    			</div>
                    		</div>
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>소속 국가</label>
                    			</div>
                    			<div class="form_section_content">
                    				<select name="nationId">
                    					<option value="none" selected>=== 선택 ===</option>
                    					<option value="01">국내</option>
                    					<option value="02">국외</option>
                    				</select>
                    				<span id="warn_nationId">소속 국가를 선택해주세요.</span>
                    			</div>
                    		</div>
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>제작사 소개</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="makerIntro" type="text">
                    				<span id="warn_makerIntro">제작사 소개를 입력 해주세요.</span>
                    			</div>
                    		</div>
                   		</form>
                   			<div class="btn_section">
                   				<button id="cancelBtn" class="btn">취 소</button>
	                    		<button id="enrollBtn" class="btn enroll_btn">등 록</button>
	                    	</div> 
                    </div>
                </div><%@include file="../includes/admin/footer.jsp" %>
 <script>
 
 /* 등록 버튼 */
 $("#enrollBtn").click(function(){    
     /* 검사 통과 유무 변수 */
     let nameCheck = false;            // 제작사 이름
     let nationCheck = false;        // 소속 국가
     let introCheck = false;            // 제작사 소개
  
     /* 입력값 변수 */
     let makerName = $('input[name=makerName]').val();        // 제작사 이름
     let nationId = $('select[name=nationId]').val();        // 소속 국가
     let makerIntro = $('input[name=makerIntro]').val();    // 제작사 소개

     /* 공란 경고 span태그 */
     let wMakerName = $('#warn_makerName');
     let wNationId = $('#warn_nationId');
     let wMakerIntro = $('#warn_makerIntro');
     
     /* 제작사 이름 공란 체크 */
     if(makerName ===''){
         wMakerName.css('display', 'block');
         nameCheck = false;
     } else{
         wMakerName.css('display', 'none');
         nameCheck = true;
     }
     
     /* 소속 국가 공란 체크 */
     if(nationId ==='none'){
         wNationId.css('display', 'block');
         nationCheck = false;
     } else{
         wNationId.css('display', 'none');
         nationCheck = true;
     }    
     
     /* 제작사 소개 공란 체크 */
     if(makerIntro ===''){
         wMakerIntro.css('display', 'block');
         introCheck = false;
     } else{
         wMakerIntro.css('display', 'none');
         introCheck = true;
     }    
     
     /* 최종 검사 */
     if(nameCheck && nationCheck && introCheck){
         $("#enrollForm").submit();    
     } else{
         return;
     }
     
 });
 
/* 취소 버튼 */
$("#cancelBtn").click(function(){
    location.href="/admin/makerManage"
});
 
</script>

</body>
</html>
