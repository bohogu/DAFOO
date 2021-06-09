	package reply.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import board.db.BoardBean;

public class ReplyDAO {
	
	DataSource dataSource;
	
	public ReplyDAO() {
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/dafoo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ReplyBean> getList(int bnum) {
		ArrayList<ReplyBean> replyList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select * from reply where bnum = ? order by rnum desc";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bnum);
			rs = pstmt.executeQuery();
			replyList = new ArrayList<ReplyBean>();
			while(rs.next()) {
				ReplyBean reply = new ReplyBean();
				reply.setNick(rs.getString("nick"));
				reply.setBnum(rs.getInt("bnum"));
				reply.setRnum(rs.getInt("rnum"));
				reply.setContent(rs.getString("content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				reply.setDate(rs.getString("date").substring(0, 11));
				replyList.add(reply);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} return replyList;
	}
	
	public int write(ReplyBean rb) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "insert into reply(nick,bnum,content,date) values(?,?,?,now())";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, rb.getNick());
			pstmt.setInt(2, rb.getBnum());
			pstmt.setString(3, rb.getContent());
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} return -1;
	}
	
	public int delete(int rnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete from reply where rnum = ?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, rnum);
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} return -1;
	}
}
