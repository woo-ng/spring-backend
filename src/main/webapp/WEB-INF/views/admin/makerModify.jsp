<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/admin/makerModify.css">

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<body>
				<%@include file="../includes/admin/header.jsp" %>
                <div class="admin_content_wrap">
                    <div class="admin_content_subject"><span>제작사 상세</span></div>
                    <div class="admin_content_main">
                    	<form id="modifyForm" action="/admin/makerModify" method="post">
	                   		<div class="form_section">
	                   			<div class="form_section_title">
	                   				<label>제작사 번호</label>
	                   			</div>
	                   			<div class="form_section_content">
	                   				<input class="input_block" name="makerId" readonly="readonly" value="<c:out value='${makerInfo.makerId }'></c:out>">
	                   			</div>
	                   		</div>                    
	                   		<div class="form_section">
	                   			<div class="form_section_title">
	                   				<label>제작사 이름</label>
	                   			</div>
	                   			<div class="form_section_content">
	                   				<input name="makerName" value="<c:out value='${makerInfo.makerName }'></c:out>" >
	                   				<span id="warn_makerName">제작사 이름을 입력 해주세요.</span>
	                   			</div>
	                   		</div>
	                   		<div class="form_section">
	                   			<div class="form_section_title">
	                   				<label>소속 국가</label>
	                   			</div>
	                   			<div class="form_section_content">
	                   				<select name="nationId" >
	                   					<option value="none" disabled="disabled">=== 선택 ===</option>
	                   					<option value="01" <c:out value=" ${makerInfo.nationId eq '01' ?'selected':''}"/>>국내</option>
	                   					<option value="02" <c:out value=" ${makerInfo.nationId eq '02' ?'selected':''}"/>>국외</option>
	                   				</select>
	                   			</div>
	                   		</div>
	                   		<div class="form_section">
	                   			<div class="form_section_title">
	                   				<label>제작사소개</label>
	                   			</div>
	                   			<div class="form_section_content">
	                   				<textarea name="makerIntro" ><c:out value='${makerInfo.makerIntro }'/></textarea>
	                   				<span id="warn_makerIntro">제작사 소개를 입력 해주세요.</span>
	                   			</div>
	                   		</div>
	                   		<div class="form_section">
	                   			<div class="form_section_title">
	                   				<label>등록 날짜</label>
	                   			</div>
	                   			<div class="form_section_content">
	                   				<input class="input_block" type="text" readonly="readonly" value="<fmt:formatDate value="${makerInfo.regDate}" pattern="yyyy-MM-dd"/>">
	                   			</div>
	                   		</div>
	                   		<div class="form_section">
	                   			<div class="form_section_title">
	                   				<label>수정 날짜</label>
	                   			</div>
	                   			<div class="form_section_content">
	                   				<input class="input_block" type="text" readonly="readonly" value="<fmt:formatDate value="${makerInfo.updateDate}" pattern="yyyy-MM-dd"/>">
	                   			</div>
	                   		</div>
	                 		<div class="btn_section">
	                   			<button id="cancelBtn" class="btn">취소</button>
		                    	<button id="modifyBtn" class="btn modify_btn">수 정</button>
		                    	<button id="deleteBtn" class="btn delete_btn">삭 제</button>
		                    </div> 
	                    </form>
                    </div>                    
                </div>
                
                <form id="moveForm" method="get">
                	<input type="hidden" name="makerId" value='<c:out value="${makerInfo.makerId }"/>'>
                	<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }"/>'>
                	<input type="hidden" name="amount" value='<c:out value="${cri.amount }"/>' >
                	<input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>
                </form>
				<%@include file="../includes/admin/footer.jsp" %>

<script>

let moveForm = $("#moveForm");
let modifyForm = $("#modifyForm");




/* 제작사 상세 페이지 이동 버튼 */
$("#cancelBtn").on("click", function(e){
	
	e.preventDefault();
			
	moveForm.attr("action", "/admin/makerDetail")
	moveForm.submit();
	
});

/* 삭제 버튼 */
$("#deleteBtn").on("click", function(e){
	e.preventDefault();
	moveForm.find("input").remove();
	moveForm.append('<input type="hidden" name="makerId" value="${makerInfo.makerId}">');
	moveForm.attr("action", "/admin/makerDelete");
	moveForm.attr("method", "post");
	moveForm.submit();
});


/* 제작사 수정 버튼 작동 및 유효성 검사 */
$("#modifyBtn").on("click", function(e){

	let makerName = $(".form_section_content input[name='makerName']").val();
	let makerIntro = $(".form_section_content textarea").val();		

	let	nameCk = false;
	let introCk = false;		
	
	e.preventDefault();
	
	if(!makerName){
		$("#warn_makerName").css("display", "block");
	} else {
		$("#warn_makerName").css("display", "none");
		nameCk = true;
	}
	if(!makerIntro){
		$("#warn_makerIntro").css("display", "block");
	} else {
		$("#warn_makerIntro").css("display", "none");
		introCk = true;
	}

	
	if(nameCk && introCk ){
		modifyForm.submit();	
	} else {
		return false;
	}
	
	
});

</script>

</body>
</html>