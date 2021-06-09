package member.db;



import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.db.MemberDAO;
import member.db.MemberBean;
import member.exception.LoginException;


public class MemberDAO {

		private Connection getConnection() throws Exception{
			Connection con = null;
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/dafoo");
			con = ds.getConnection();
			return con;
		}
		
	// ==========================================================================
		
		//-----------------------------------------------------------------------------------------------------------
		public void freeResource(Connection con, PreparedStatement pstmt){
			if (con != null){ try { con.close();}catch(Exception err) {err.printStackTrace();} }
			if (pstmt != null){ try { pstmt.close();}catch(Exception err){err.printStackTrace();}}
		}
		
		//-----------------------------------------------------------------------------------------------------------
		public void freeResource(Connection con, PreparedStatement pstmt, ResultSet rs){
			if (con != null){ try { con.close();}catch(Exception err) {err.printStackTrace();} }
			if (pstmt != null){ try { pstmt.close();}catch(Exception err){err.printStackTrace();}}
			if (rs != null){try { rs.close();}catch(Exception err) {err.printStackTrace();} }
		}
						
						
					
						
		//관리자 회원정보 모두 가져오기
		public List<MemberBean> selectAllMember(){
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
	        String sql="SELECT * FROM pmember";
	        List<MemberBean> list = new ArrayList<MemberBean>();
	        try {
	            System.out.println("DB에 접근하였습니다");
	            con = getConnection();
	            pstmt = con.prepareStatement(sql);
	            rs = pstmt.executeQuery(sql);  
	            while(rs.next()){
	            	MemberBean mem = new MemberBean();
	                mem.setMnum(rs.getInt("mnum"));
	                mem.setId(rs.getString("id"));
	                mem.setPass(rs.getString("pass"));
	                mem.setName(rs.getString("name"));
	                mem.setEmail(rs.getString("email"));
	                mem.setPhone(rs.getString("phone"));
	                mem.setHeight(rs.getInt("height"));
	                mem.setWeight(rs.getInt("weight"));
	                
	                list.add(mem);
	            }
	        } catch (Exception e) {e.printStackTrace();
	        
	        }finally{
	        	freeResource(con, pstmt);
	        }
	        return list;
	    }
		
		
		
		
		//관리자 삭제
		public int deleteMember(int mnum) throws Exception{
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
	        String sql="DELETE FROM member where num=?";
	        int delete = 0;
	        try {
	        	con = getConnection();
	        	pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, mnum);
	            delete = pstmt.executeUpdate();
	            
	        }catch(SQLException e){e.printStackTrace();
	        }finally{
	        	freeResource(con, pstmt);
	        }
	        return delete;
	    }


		
		
		
	// 회원 등록 작업
		public boolean insertArticle(MemberBean memberBean) {
			System.out.println("MemberDAO - insertArticle()");
		
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = "";
			int result = 0;
			try {
				
				con= getConnection();
			sql = "INSERT INTO pmember(id, nick, pass, name, age, gender, height,weight, phone,email, postcode,address,detailaddress,extraaddress)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			// memberBean 객체로부터 데이터를 꺼내서 쿼리문 ? 대체
			
			
			pstmt.setString(1, memberBean.getId());
			pstmt.setString(2, memberBean.getNick());
			pstmt.setString(3, memberBean.getPass());
			pstmt.setString(4, memberBean.getName());
			pstmt.setInt(5, memberBean.getAge());
			pstmt.setString(6, memberBean.getGender());
			pstmt.setInt(7, memberBean.getHeight());
			pstmt.setInt(8, memberBean.getWeight());
			pstmt.setString(9, memberBean.getPhone());
			pstmt.setString(10, memberBean.getEmail());
			pstmt.setString(11, memberBean.getPostcode());
			pstmt.setString(12, memberBean.getAddress());
			pstmt.setString(13, memberBean.getDetailAddress());
			pstmt.setString(14, memberBean.getExtraAddress());
			
			result = pstmt.executeUpdate();
			if(result != 0){
				return true;
				
			}
			} catch (Exception e) {
				System.out.println("insertArticle() 오류! - " + e.getMessage());
				e.printStackTrace();
				
			}finally {
				freeResource(con, pstmt);
			
			}return false; 
			
		}
		 
		
			
	// 회원정보 조회
	public MemberBean selectArticle(String id) {
		System.out.println("MemberDAO - selectArticle()");
		
		MemberBean article = null;
		String sql = "SELECT * FROM pmember WHERE id=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				article = new MemberBean();
				article.setMnum(rs.getInt(1));
				article.setId(rs.getString(2));
				article.setNick(rs.getString(3));
				article.setPass(rs.getString(4));
				article.setName(rs.getString(5));
				article.setAge(rs.getInt(6));
				article.setGender(rs.getString(7));
				article.setHeight(rs.getInt(8));
				article.setWeight(rs.getInt(9));
				article.setPhone(rs.getString(10));
				article.setEmail(rs.getString(11));
				article.setPostcode(rs.getString(12));
				article.setAddress(rs.getString(13));
				article.setDetailAddress(rs.getString(14));
				article.setExtraAddress(rs.getString(15));
				
			}
			
		} catch (Exception e) {
			System.out.println("selectArticle() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			freeResource(con, pstmt, rs);
		}
		return article;
	}
	
	
	public boolean selectLoginMember(String id, String pass) throws LoginException {
		System.out.println("MemberDAO - selectLoginMember()");
		
		boolean isMember = false;
		
		Connection con = null;
		String sql="";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con= getConnection();
			sql = "SELECT pass FROM pmember WHERE id=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (pass.equals(rs.getString("pass"))) {
					isMember = true;
					
				} else {
					throw new LoginException("패스워드 오류!");
					
				}
			} else {
				throw new LoginException("아이디 없음!");
				
			}
			
		} catch (Exception e) {
			System.out.println("selectLoginMember() 오류! - " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			freeResource(con, pstmt, rs);
		}
		return isMember;
	}
	
	
	// 회원 삭제 작업
	public boolean deleteMember(String id) {
		System.out.println("MemberDAO - deleteMember()");
		
		int deleteCount = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con= getConnection();
			String sql = "DELETE FROM pmember WHERE id=?";
			pstmt = con.prepareStatement (sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("/member/deleteMember() 오류! - " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			freeResource(con, pstmt, rs);
		}
		
		 if (deleteCount > 0) 
			 return true; 
		 else 
			 return false; 
	}
	
	
	// 회원 정보 업데이트 작업
	public void updateMember (MemberBean memberBean) {
		System.out.println("MemberDAO - updateMember()");
		
		
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		
		
		try {
			con = getConnection();
			String sql = "UPDATE pmember "
					+ "SET nick=?, pass=?, name=?,age=?,height=?,weight=?,phone=?, email=? , "
					+ "postcode=?, address=?, detailAddress=?, extraAddress=? WHERE id=?";
					
			
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, memberBean.getNick());
			pstmt.setString(2, memberBean.getPass());
			pstmt.setString(3, memberBean.getName());
			pstmt.setInt(4, memberBean.getAge());
			pstmt.setInt(5, memberBean.getHeight());
			pstmt.setInt(6, memberBean.getWeight());
			pstmt.setString(7, memberBean.getPhone());
			pstmt.setString(8, memberBean.getEmail());
			pstmt.setString(9, memberBean.getPostcode());
			pstmt.setString(10, memberBean.getAddress());
			pstmt.setString(11, memberBean.getDetailAddress());
			pstmt.setString(12, memberBean.getExtraAddress());
			pstmt.setString(13, memberBean.getId());
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("/member/updateMember() 오류! - " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			freeResource(con, pstmt, rs);
		}
	}


	// 전체 회원 정보 담기
	public ArrayList<MemberBean> selectArticleList(int page, int limit) {
		System.out.println("MemberDAO - selectArticleList()");
		
		ArrayList<MemberBean> articleList = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 조회를 시작할 레코드(행) 번호 계산
		int startRow = (page - 1) * limit;
		
		try {
			// 회원 조회
			con= getConnection();
			String sql = "SELECT * FROM pmember LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			// ArrayList 객체 생성(while문 위에서 생성 필수!)
			articleList = new ArrayList<MemberBean>();
			
			while(rs.next()) {
				// 1개 게시물 정보를 저장할 BoardBean 객체 생성 및 데이터 저장
				MemberBean article = new MemberBean();
			
				
				article.setId(rs.getString(1));
				article.setNick(rs.getString(2));
				article.setPass(rs.getString(3));
				article.setName(rs.getString(4));
				article.setAge(rs.getInt(5));
				article.setGender(rs.getString(6));
				article.setHeight(rs.getInt(7));
				article.setWeight(rs.getInt(8));
				article.setPhone(rs.getString(9));
				article.setEmail(rs.getString(10));
				article.setPostcode(rs.getString(11));
				article.setAddress(rs.getString(12));
				article.setDetailAddress(rs.getString(13));
				article.setExtraAddress(rs.getString(14));
				
				// 1개 회원을 전체 회원 저장 객체(ArrayList)에 추가
				articleList.add(article);
			}
			
		} catch (Exception e) {
			System.out.println("selectArticleList() 오류! - " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			freeResource(con, pstmt, rs);
		}
		return articleList;
	}

	// 회원 가입시 아이디중복 확인
	public boolean checkArticle(String id) {
		System.out.println("MemberDAO - checkArticle()");
		
		boolean chekcIdResult = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con= getConnection();
			String sql = "SELECT id FROM pmember WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				chekcIdResult  = true;
			}
			
		} catch (Exception e) {
			System.out.println("checkArticle() 오류! - " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			freeResource(con, pstmt, rs);
		}
		
		return chekcIdResult ;
	}

}
