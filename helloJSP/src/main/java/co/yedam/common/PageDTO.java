package co.yedam.common;

public class PageDTO {
	int total; // 전체 건수
	int currentPage; // 현재페이지
	boolean next, prev; // 이전, 이후
	int startPage, endPage;
	int boardNo; // 어떤 글에 대한 페이지 정보인지

	// 현재페이지가 13페이지이면 <이전페이지 11p 13p 20p 다음페이지>나오도록. 258건이면 52page까지 나오도록
	public PageDTO(int boardNo, int total, int currentPage) {
		this.currentPage = currentPage;
		
		int realEnd = (int) Math.ceil(total / 5.0);
		
		this.boardNo = boardNo;
		this.total = total;

		this.endPage = (int)Math.ceil(currentPage / 10.0) * 10;// 올림
		this.startPage = this.endPage -9;
		
		this.endPage = this.endPage > realEnd ? realEnd : this.endPage;
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd; //마지막페이지보다 작으면 이후 페이지가 있다는 뜻
	}

}
