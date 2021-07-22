<%@page import="dao.MemberDao"%>
<%@page import="domain.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 수정 페이지</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/default.css">
<%	
	Member member = (Member)session.getAttribute("member");
	if(member == null ) {
%>
<script>
	alert('로그인이 필요한 페이지입니다. \n로그인 후 사용해주세요.');
	location.href = "<%=request.getContextPath()%>/loginForm.jsp";
</script>
</head>
<%	
	} else {
%>
<style>
</style>
<script>
</script>
</head>
<body>
	<%@ include file="/WEB-INF/frame/header.jsp"%>
	<%@ include file="/WEB-INF/frame/nav.jsp"%>
	<h1 id="list_header">정보 수정 페이지</h1>
	<hr>
	<h3>수정할 정보를 입력해주세요</h3>
	<form action="editMember.jsp" method="post">
		<table border=1>
			<tr>		
				<th>아이디</th>
				<td><input type="text" name="id" value="<%=member.getId()%>" required="required"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pw" value="<%=member.getPw()%>" required="required"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" value="<%=member.getName()%>" required="required"></td>
			</tr>
			<tr>
				<td><td>
				<td>
					<input type="submit" value="수정">
					<input type="reset">
				</td>
			</tr>
		</table>
	</form>
</body>
<%
	}
%>
</html>