package co.yedam.board.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.board.service.BoardVO;
import co.yedam.common.DataSource;

public class BoardDAO {
	// 목록, 단건조회 , 등록 , 수정 , 삭제:
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	DataSource ds = DataSource.getInstance();

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<BoardVO> selectList() {
		sql = "select * from board order by board_no";
		conn = ds.getConnection();
		List<BoardVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setContent(rs.getString("content"));
				vo.setImage(rs.getString("image"));
				vo.setLastUpdate(rs.getDate("last_update"));
				vo.setTitle(rs.getString("title"));
				vo.setViewCnt(rs.getInt("view_cnt"));
				vo.setWriteDate(rs.getDate("write_Date"));
				vo.setWriter(rs.getString("writer"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public BoardVO select(int boardNo) {
		sql = "select * from board where board_no=?";
		conn = ds.getConnection();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			if (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setContent(rs.getString("content"));
				vo.setImage(rs.getString("image"));
				vo.setLastUpdate(rs.getDate("last_update"));
				vo.setTitle(rs.getString("title"));
				vo.setViewCnt(rs.getInt("view_cnt"));
				vo.setWriteDate(rs.getDate("write_Date"));
				vo.setWriter(rs.getString("writer"));
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	public int insert(BoardVO vo) {
		sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, WRITER)" + "VALUES(seq_board.nextval, ?,?,?)";
		conn = ds.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getWriter());
			int r = psmt.executeUpdate();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 0;
	}

	public int update(BoardVO vo) {
		sql = "UPDATE BOARD SET TITLE=?, CONTENT=?, " + "IMAGE=nvl(?,image), LAST_UPDATE=SYSDATE" + "WHERE BOARD_NO=?";
		conn = ds.getConnection();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getWriter());
			psmt.setInt(4, vo.getBoardNo());

			int r = psmt.executeUpdate();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 0;
	}

	public int delete(int boardNo) {
		sql = "delete from board where board_no=?";
		conn = ds.getConnection();

		try {

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			int r = psmt.executeUpdate();
			return r;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return 0;

	}

	// 조회수 증가...
	public int updateCnt(int boardNo) {
		sql = "update board set view_cnt=view_cnt+1 where board_no=?";
		conn = ds.getConnection();

		try {

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			int r = psmt.executeUpdate();
			return r;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return 0;

	}

}
