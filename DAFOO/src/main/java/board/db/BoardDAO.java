package board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	DataSource dataSource;
	
	public BoardDAO() {
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/dafoo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int write(String nick, String title, String content, String file, String rfile, int bgroup) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "insert into board(nick,title,content,date,file,rfile,bgroup) values(?,?,?,now(),?,?,?)";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, nick);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setString(4, file);
			pstmt.setString(5, rfile);
			pstmt.setInt(6, bgroup);
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
	
	public BoardBean getBoard(int bnum) {
		BoardBean board = new BoardBean();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select * from board where bnum = ?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setNick(rs.getString("nick"));
				board.setBnum(rs.getInt("bnum"));
				board.setTitle(rs.getString("title").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				board.setContent(rs.getString("content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				board.setDate(rs.getString("date").substring(0, 11));
				board.setHit(rs.getInt("hit"));
				board.setFile(rs.getString("file"));
				board.setRfile(rs.getString("rfile"));
				board.setBgroup(rs.getInt("bgroup"));
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
		} return board;
	}
	
	public ArrayList<BoardBean> getList(int bgroup) {
		ArrayList<BoardBean> boardList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select * from board where bgroup = ? order by bnum desc";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bgroup);
			rs = pstmt.executeQuery();
			boardList = new ArrayList<BoardBean>();
			while(rs.next()) {
				BoardBean board = new BoardBean();
				board.setNick(rs.getString("nick"));
				board.setBnum(rs.getInt("bnum"));
				board.setTitle(rs.getString("title").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				board.setContent(rs.getString("content").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				board.setDate(rs.getString("date").substring(0, 11));
				board.setHit(rs.getInt("hit"));
				board.setFile(rs.getString("file"));
				board.setRfile(rs.getString("rfile"));
				board.setBgroup(rs.getInt("bgroup"));
				boardList.add(board);
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
		} return boardList;
	}
	
	public int hit(int bnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE board SET hit = hit + 1 WHERE bnum = ?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bnum);
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
	
	public String getFile(int bnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select file from board where bnum = ?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString("file");
			} return "";
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
		} return "";
	}
	
	public String getRealFile(int bnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select rfile from board where bnum = ?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString("rfile");
			} return "";
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
		} return "";
	}
	
	public int update(String title, String content, String file, String rfile, int bnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "update board SET title = ?, content = ?, file = ?, rfile = ? where bnum = ?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, file);
			pstmt.setString(4, rfile);
			pstmt.setInt(5, bnum);
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
	
	public int delete(int bnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "delete from board where bnum = ?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bnum);
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
	public int getCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "select count(*) from board";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();
			
			return rs.getInt("count");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}
}
