package co.yedam.reply.service;

import java.util.Date;

import lombok.Data;
@Data
public class ReplyVO {

	private int replyNo;
	private int boardNo;
	private String reply;
	private String replyer;
	private Date replyDate;
}
