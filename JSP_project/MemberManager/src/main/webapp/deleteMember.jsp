<%@page import="dao.MemberDao"%>
<%@page import="util.ConnectionProvider"%>
<%@page import="java.sql.Connection"%>
<%@page import="domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member member = (Member)session.getAttribute("member");
	int resultCnt = 0;
	Connection con = null;
	con = ConnectionProvider.getConnection();
	resultCnt = MemberDao.getInstance().deleteMember(con, member.getIdx());

	if(resultCnt>0){
		%>
		<script>
			alert('삭제되었습니다.');
			location.href = 'logout.jsp';
		</script>
		<%
	} else {
		%>
		<script>
			alert("권한이 없습니다.");
			history.go(-1);
		</script>
		<%
	}
%>