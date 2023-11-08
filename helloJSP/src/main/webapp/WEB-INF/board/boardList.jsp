<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="../layout/menu.jsp"></jsp:include>
<jsp:include page="../layout/header.jsp"></jsp:include>





<h3>게시판 목록.</h3>

<table class="table" border="1">
	<thead>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일자</th>

		</tr>
	<tbody>
		<c:forEach items="${list }" var="vo">
			<tr>
				<td>${vo.boardNo }</td>
				<td><a href="getBoard.do?bno=${vo.boardNo }">${vo.title }</a></td>
				<td>${vo.writer }</td>
				<td><fmt:formatDate value="${vo.writeDate }"
						pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
			</tr>

		</c:forEach>

	</tbody>

</table>
<p>
	<a href="boardForm.do">등록화면</a>
</p>
<jsp:include page="../layout/footer.jsp"></jsp:include>