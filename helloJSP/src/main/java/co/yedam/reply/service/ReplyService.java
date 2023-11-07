package co.yedam.reply.service;

import java.util.List;

public interface ReplyService {

	//목록 단건 추가 수정 삭제
	
	public List<ReplyVO> replyList(int boardNo);
	public ReplyVO getReply(int replyNo);
	public boolean addReply(ReplyVO vo);
	public boolean editReply(ReplyVO vo);
	public boolean delReply(int replyNo);
}
