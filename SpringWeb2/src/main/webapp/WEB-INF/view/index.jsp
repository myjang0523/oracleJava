<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp" %>     
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Index입니다...</title>
<script src="js/jquery-2.1.3.min.js"></script>
<script>

</script>
</head>
<body>
	<h1>Hello World!</h1>
	<h2>현재시간: ${now}</h2>
	<table border="1" >
		<form:form modelAttribute="loginForm">
			<tr>
				<td>아이디:</td>
				<td><form:input path="userid"/></td>
			</tr>
			<tr>
				<td>비밀번호:</td>
				<td><form:password path="password" showPassword="true"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="로그인" /></td>
				<td><input type="button" value="회원가입" onclick="location.href = 'signUp.html'"/></td>
			</tr>
		</form:form>
	</table>
	
</body>
</html>







