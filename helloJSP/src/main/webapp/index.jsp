
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첫페이지 입니다.(index.jsp)</title>
</head>
<body>
	<%
	response.sendRedirect("boardList.do"); /* hellojsp를 실행해보면 <-때문에 boardList가 첫페이지로 실행된다 */
	%>

</body>
</html>