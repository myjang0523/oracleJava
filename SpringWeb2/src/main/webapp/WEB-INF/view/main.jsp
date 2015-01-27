<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" 
	href="js/jquery.bxslider/jquery.bxslider.css">
<title>메인 페이지</title>
<script src="js/jquery-2.1.3.min.js"></script>
<script src="js/jquery.bxslider/jquery.bxslider.min.js"></script>
<script>
$(document).ready(function(){
	//bxslider표시설정
	$(".bxslider").bxSlider({
		mode: 'fade',	//horizontal:가로, vertical: 세로, fade: 페이드효과
		speed: 1000,  // 1초당 애니메이션이 이동
		auto: true,   // 자동재생(true: on, false: off)
		autoControls: true, //자동재생 콘트롤 표시( true: on, false: off)
		adaptiveHeight: true, // 높이 자동 조정
		captions: true  //true: title속성 표시, false: 비표시
		
	});
});

$(document).ready(function(){
	$("#searchBtn").click(function(){
		var nm = $("#name").val();
		$.ajax({
			timeout: 3000,   //3초 이내 결과가 안나오면 에러
			type: "POST",	//GET or POST
			url: "userSearch.json",  //접속할 URL
			data: {"name": nm},	//파라미터
			dataType: "json", 		//출력 데이터 타입(html, xml, json, text)
			success: function(data){	//성공(OK:200)시 처리
				//alert(data);
				//테이블로 표시
				//테이블 삭제
				$("#jsonOut > table").remove();
				var theTable = $("<table>");  //테이블 요소 작성
				//테이블에 제목행 추가
				theTable.append("<tr><td>이름</td><td>비번</td><td>아이디</td><td>이메일</td></tr>");		
				for (i=0; i<data.length; i++) {
					//테이블에 내용 추가
					theTable.append("<tr><td>" + data[i].name 
							+"</td><td>"+data[i].password 
							+ "</td><td>"+ data[i].userid 
							+ "</td><td>" + data[i].email + "</td></tr>");		
				}
				//이 테이블을 jsonOut에 붙인다
				theTable.appendTo("#jsonOut");
			},
			error: function(xhr, textStatus, errorThrown)
			{
				//에러 타입
				if (textStatus == "timeout") {
					alert("시간 초과!");
				} else {
					alert("textStatus: " + textStatus);
				}
				
				//에러 메시지
				//alert("errorThrown: " + errorThrown);
			}
			
		});
	});
});
</script>
<style>

</style>
</head>
<body>
	<h1 id="main">Main Page입니다.</h1>
	<h2 id="welcome"><font color="blue">
		${sessionScope.user.userid }님 환영합니다.
	</font></h2>
	<a href="logout.html"><font color="red">로그아웃</font></a>
	<a href="boardList.html"><font color="red">게시판목록</font></a>
	
	<div class="slider" style="width:200px">
		<ul class="bxslider">
			<li><img src="images/images1.jpg" title="이민정1"/></li>
			<li><img src="images/images2.jpg" title="이민정2"/></li>
			<li><img src="images/images3.jpg" title="이민정3"/></li>
		</ul>
	</div>
	
	<p>이름: <input type="text"  id="name" /></p>
	<p><button id="searchBtn">검색</button></p>
	<p><span id="jsonOut">여기에 결과가 출력</span></p>
	<p>2015. All right reserved</p>
</body>
</html>






