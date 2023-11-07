<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
#list span {
	margin: 8px;
}
</style>
<%@include file="../layout/menu.jsp"%>
<%@include file="../layout/header.jsp"%>

<%
BoardVO vo = (BoardVO) request.getAttribute("bno"); /* 임포트 하는법 : 밑줄 우측에서 컨+스 */
System.out.println(vo);
%>
<h3>상세 화면(조회화면)</h3>
<form action="modifyForm.do" name="myForm">
	<input type="hidden" name="bno" value="<%=vo.getBoardNo()%>">
	<table border="1" class="table">
		<tr>
			<th>글번호</th>
			<td class="boardNo"><%=vo.getBoardNo()%></td>
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
				<%
				if (vo.getImage() != null) {
				%> <img width="80px"
				src="images/<%=vo.getImage()%>">
			</td>
			<%
			} else {
			%>
			<td></td>
			<%
			}
			%>


		</tr>
		<tr>
			<th>작성자</th>
			<td><%=vo.getWriter()%></td>
			<th>조회수</th>
			<td><%=vo.getViewCnt()%></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<%
				if (logId != null && logId.equals(vo.getWriter())) {
				%> <!-- 로그인 아이디랑 책 작성자랑 같으면 수정삭제가능 아니면 버튼 비활성화 -->
				<input type="submit" value="수정" class="btn btn-primary"> <input
				class="btn btn-warning" type="button" value="삭제"> <%
 } else {
 %>
				<input disabled type="submit" value="수정"> <input disabled
				type="button" value="삭제"> <%
 }
 %>
			</td>
		</tr>
	</table>
</form>
<h3>댓글등록</h3>
<table class="table">
	<tr>
		<th>댓글내용</th>
		<td><input type="text" id="content"></td>
		<td><button id="addReply">댓글등록</button></td>
	</tr>

</table>

<h3>댓글목록</h3>
<ul id="list">
	<li style="display: none;" id="template"><span>00</span><b>첫번째글입니다.</b><span>user01</span><span>2023-10-10</span><button id = "delReply">삭제</button></li>

</ul>

<!--  <p><a href="boardList.do">목록으로</a></p>-->

<script>
		document.querySelector('input[type =button]').addEventListener('click',
				function(e) {
					document.forms.myForm.action = 'removeForm.do'
					document.forms.myForm.submit();
				});
		
		//댓글목록
		let bno = "<%=vo.getBoardNo()%>";
		let writer = "<%=logId%>";
			
		bno = document.querySelector('.boardNo').innerHTML;
		console.log(bno);
		fetch('replyList.do?bno='+bno)
		.then(resolve => resolve.json())
		.then(result => {
			console.log(result);
			result.forEach(reply => {
				let li = makeRow(reply);
				//ul>li 생성..
				document.querySelector('#list').append(li);
				
			})
		})
		.catch(err => console.log(err));
		
		// 댓글등록버튼에 대한 이벤트
		document.querySelector('#addReply').addEventListener('click',function(e){
			let reply = document.querySelector('#content').value;
			console.log(writer);
			if(!bno || writer =='null'  || !reply){
				alert("값을 확인하세요.");
				return;
			}
			
			//ajax.bno/writer/reply => 전달.
			fetch('addReply.do',{
				method : 'post',
				headers : {'Content-Type' : 'application/x-www-form-urlencoded'},
			body : 'bno='+bno+'&reply='+reply+'&replyer='+writer
			})
			.then(resolve => resolve.json())
			.then(result => {
				if (result.retCode =='OK') {
					document.querySelector('#list').append(makeRow(result.vo));
				}else{
					aler('Error.')
				}
					
			})
		})
		
		function makeRow(reply){
			let temp = document.querySelector('#template').cloneNode(true);
			temp.style.display = 'block';
			console.log(temp);
			temp.querySelector('span:nth-of-type(1)').innerHTML=reply.replyNo;
			temp.querySelector('b').innerHTML=reply.reply;
			temp.querySelector('span:nth-of-type(2)').innerHTML=reply.replyer;
			temp.querySelector('span:nth-of-type(3)').innerHTML=reply.replyDate;
			
			temp.querySelector('#delReply').addEventListener('click' , function(e){ 
				fetch('delReply.do?rno=' + reply.replyNo)
				.then(resolve => resolve.json())
				.then(result => {
					console.log(result);
					if(result.retCode == 'OK'){
						alert('삭제성공');
						temp.remove();
					}else {
						alert('삭제실패');
					}
				})
				
			})
			return temp;
		}
	
	</script>

<%@include file="../layout/footer.jsp"%>