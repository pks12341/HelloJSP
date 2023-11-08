<%@page import="java.util.ArrayList"%>
<%@page import="co.yedam.board.service.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
	
<%@include file="../layout/menu.jsp"%>
<%@include file="../layout/header.jsp"%>


<c:forEach var="i" begin="1" end="10" step="2"> <!-- step 은 증감치 -->
	<p>${i }</p>
</c:forEach>

<table class="table">
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>연락처</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${memberList }" var ="member">

	   <tr>
			<td>${member.mid }</td>
			<td>${member.name }</td>
			<td>${member.phone }</td>
		</tr>
		</c:forEach>
		
	</tbody>
</table>


<%@include file="../layout/footer.jsp"%>