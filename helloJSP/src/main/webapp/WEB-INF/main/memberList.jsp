<%@page import="java.util.ArrayList"%>
<%@page import="co.yedam.board.service.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/menu.jsp"%>
<%@include file="../layout/header.jsp"%>

<table class="table">
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>연락처</th>
		</tr>
	</thead>
	<tbody>
		<% 
	ArrayList<MemberVO> list = (ArrayList<MemberVO>) request.getAttribute("memberList");
		System.out.println("리스트출력");
		System.out.println(list);
	for (MemberVO vo : list) {%>
		<tr>
			<td><%=vo.getMid() %></td>
			<td><%=vo.getName() %></td>
			<td><%=vo.getPhone() %></td>

		</tr>
		<% } %>
	</tbody>
</table>


<%@include file="../layout/footer.jsp"%>