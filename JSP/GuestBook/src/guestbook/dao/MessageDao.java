package guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import guestbook.model.Message;

public class MessageDao {

	// 싱글톤
	private MessageDao() {
	}

	static private MessageDao dao = new MessageDao();

	public static MessageDao getInstance() {
		return dao;
	}

	// 데이터 저장
	public int insertMessage(Connection conn, Message message) throws SQLException {

		int resultCnt = 0;

		PreparedStatement pstmt = null;
		try {

			String sql = "insert into guestbook_message values (message_id_seq.nextVal,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, message.getUname());
			pstmt.setString(2, message.getPw());
			pstmt.setString(3, message.getMessage());

			resultCnt = pstmt.executeUpdate();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

		}

		return resultCnt;
	}

	// 데이터 출력
	public List<Message> selectMesseageList(Connection conn, int startRow, int endRow) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// list 객체 생성
		List<Message> list = new ArrayList<>();

		try {

			String sql = "select message_id, guest_name, password, message " + "from ("
					+ "select  rownum rnum,message_id, guest_name, password, message " + "	from("
					+ "		select * from guestbook_message m order by m.message_id desc " + "	) where rownum<=?"
					+ ") where rnum>=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Message message = new Message(rs.getInt("message_id"), rs.getString("guest_name"),
						rs.getString("password"), rs.getString("message"));
							
				list.add(message);
			}

		} finally {

			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

		}

		return list;
	}

	// 페이지 Count
	public int selectTotalCount(Connection conn) throws SQLException {

		int resultCnt = 0;

		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.createStatement();

			String sql = "select count(*) from guestbook_message";

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				resultCnt = rs.getInt(1);
			}

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}

		return resultCnt;
	}

	// 데이터 삭제를 위한 검색
	public Message selectMessage(Connection conn, int mid) throws SQLException {

		Message message = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "select * from guestbook_message where message_id=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, mid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				message = new Message(rs.getInt("message_id"), rs.getString("guest_name"), rs.getString("password"),
						rs.getString("message"));
			}

		} finally {

			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

		}

		return message;
	}

	public int deleteMessage(Connection conn, int mid) throws SQLException {

		int resultCnt = 0;

		PreparedStatement pstmt = null;

		String sql = "delete from guestbook_message where message_id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mid);

			resultCnt = pstmt.executeUpdate();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
		return resultCnt;
	}

}
