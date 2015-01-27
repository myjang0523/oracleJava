<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<script src="js/jquery-2.1.3.min.js"></script>
<script>

$(document).ready(function(){
    $("#userid").blur(function(){
       var ui=$("#userid").val();
       $.ajax({
                        // 3초 이내 결과가 나오지 않으면 에러
            type:"POST",                 //GET or POST
          url:"userSearchId.json",        //접속할 URL
          data:{"userId": ui},            //파라미터
          dataType:"json",            //출력 데이터 타입(html,xml,json,text)
          success: function(data){    //성공{ok:200}시 처리
              
              var userid = $("#userid");
                 //alert(userid);
              var valid = $("<img src='images/valid.png'>");
              var invalid = $("<img src='images/invalid.png'>");
             
                 if( userid.val().length < 5 || userid.val().length > 20){
                  $("#dupmsg").text("아이디가 짧거나 너무 깁니다..");
                  $("userid").focus();
              }else if( data.length == 0 ){
                  $("#dupmsg").css("color","green").text("사용가능한  아이디 입니다.");
                  $("#dupimg").html(valid);
                  $("#password").focus();
                 
              }else{
                  $("#dupmsg").css("color","red").text("사용 불가능한  아이디 입니다.");
                  $("#dupimg").html(invalid);
                  userid.focus();
              }
              
          }
         
       });
    });
 });

</script>

<title>회원 가입</title>
</head>
<body>
	<h1>회원 가입</h1>
	<p>회원 가입 해주시기 바랍니다.</p>
	<table>
		<form:form modelAttribute="signUpForm">
			<tr><td colspan="2">
<form:errors path="*" element="div"/>
			
			</td></tr>
			<tr><td>
				<form:label path="userid">아이디</form:label>
			</td><td>
				<form:input path="userid" size="20"/>
				<span id="dupmsg"></span>
				<span id="dupimg"></span>
			</td></tr>
			<tr><td>
				<form:label path="password">패스워드</form:label>
			</td><td>
				<form:password path="password" size="20"/>
			</td></tr>
			<tr><td>
				<form:label path="name">이름</form:label>
			</td><td>
				<form:input path="name" size="20"/>
			</td></tr>
			<tr><td>
				<form:label path="email">이메일</form:label>
			</td><td>
				<form:input path="email" size="20"/>
			</td></tr>
			<tr><td colspan="2">
				<input type="submit" value="가입"/>
			</td></tr>
		</form:form>
	</table>
</body>
</html>






