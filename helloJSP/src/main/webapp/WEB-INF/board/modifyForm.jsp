<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../layout/menu.jsp"></jsp:include>
<jsp:include page="../layout/header.jsp"></jsp:include>

	
	<h3>게시글 수정화면</h3>
	<form action="modifyBoard.do" method="post">
		<input type="hidden" name="bno" value = "${vo.boardNo }">
		<table class = "table" border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${vo.title }"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"
					value="${vo.writer }"></td>
			</tr>
			<tr>
				<td colspan="2"><textarea cols="31" rows="6" name="content">${vo.content }</textarea></td>
			</tr>
			<tr>
				<th>파일명</th>
				<td><img src="images/${vo.image }" width="80px">
			</tr>

			<tr>
				<td colspan="2" align="center"><input class="btn btn-primary" type="submit" value="수정">
					<input class = "btn btn-warning" type="reset" value="초기화"></td>
			</tr>
		</table>

	</form>
<jsp:include page="../layout/footer.jsp"></jsp:include>