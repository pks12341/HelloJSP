<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/menu.jsp"%>
<%@include file="../layout/header.jsp"%>

	<%
	BoardVO vo = (BoardVO) request.getAttribute("bno"); /* 임포트 하는법 : 밑줄 우측에서 컨+스 */
	System.out.println(vo);
	%>
	<h3>상세 화면(조회화면)</h3>
	<form action="modifyForm.do" name="myForm">
		<input type="hidden" name="bno" value="<%=vo.getBoardNo()%>">
		<table border="1" class = "table">
			<tr>
				<th>글번호</th>
				<td><%=vo.getBoardNo()%></td>
				<th>작성일시</th>
				<td><%=vo.getWriteDate()%></td>
			</tr>
			<tr>
				<th>글제목</th>
				<td colspan="3"><%=vo.getTitle()%></td>
			</tr>
			<tr>
				<td colspan="4"><textarea rows="5" cols="40"><%=vo.getContent()%></textarea></td>
			</tr>
			<tr>
				<th>이미지</th>
				<td colspan="3">
				
				<%if(vo.getImage()!=null){%>
			
				<img width="80px" src="images/<%=vo.getImage()%>"></td>
					<%}else{ %>
					<td></td>
					<%} %>
					
					
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=vo.getWriter()%></td>
				<th>조회수</th>
				<td><%=vo.getViewCnt()%></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<%if(logId != null && logId.equals(vo.getWriter())){ %> <!-- 로그인 아이디랑 책 작성자랑 같으면 수정삭제가능 아니면 버튼 비활성화 -->
				 <input  type="submit" value="수정" class="btn btn-primary">
				 <input class = "btn btn-warning" type="button" value="삭제">
				 <%}else{ %>
				  <input disabled type="submit" value="수정">
				  <input disabled type="button" value="삭제">
				 <%} %>
				 </td>
			</tr>
		</table>
	</form>
	
	<!--  <p><a href="boardList.do">목록으로</a></p>-->
	
	<script>
		document.querySelector('input[type =button]').addEventListener('click',
				function(e) {
					document.forms.myForm.action = 'removeForm.do'
					document.forms.myForm.submit();
				})
	</script>

<%@include file="../layout/footer.jsp"%>