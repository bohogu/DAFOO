package reply.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

}
