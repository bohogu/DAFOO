package food.db;

import java.sql.Connection;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.sf.json.JSONArray;


public class foodDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource ds;
	private ResultSet rs;
	
	public foodDAO() {
		try{
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");		
			/*커넥션풀 얻기*/
			ds = (DataSource)envContext.lookup("jdbc/jspbeginner");
	
		}catch(Exception e){
			System.out.println("커넥션풀(DataSource)얻기 실패 : " + e);
		}	
	}
	
	//자원해제
	public void ResouceClose(){	
		try {
			if(pstmt != null){
				pstmt.close();
			}
			if(rs!= null){
				rs.close();
			}
			if(con != null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//(end)자원해제 
	
	//검색 자동완성(AutoComplete)
	public ArrayList<String> listDiet(String typing){
		
		ArrayList<String> list = new ArrayList<String>();
		try{
			con = ds.getConnection();
			String keyword = typing + "%";

			String sql = "select fname from food where fname  like ? order by fidx desc ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				String fname = rs.getString("fname");
				list.add(fname);
			}
		}catch (Exception e){
			System.out.println("SQL문 실행 오류 : " + e);
		}finally {
			ResouceClose();
		}
		return list;
	}//(end)검색 자동완성(AutoComplete) 
	
	// 검색창옆에  음식영양소들을 뿌려주기 위한 셀렉트문
		public int FoodNutrientResult(String foodName) {
			
			int result = 0;
			foodBean fbean = new foodBean();
			try{
				con = ds.getConnection();
				String sql = "select * from food where fname = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, foodName);		
				rs = pstmt.executeQuery();
				
					fbean.setFidx(rs.getInt(1));		//	음식 순서
					fbean.setFname(rs.getString(2));	//	음식 이름
					fbean.setFsize(rs.getString(3));	//	음식 인분
					fbean.setCal(rs.getString(4));		//	음식 칼로리
					fbean.setCarbo(rs.getString(5));	//	음식 탄수화물
					fbean.setProtein(rs.getString(6));	//	음식 단백질
					fbean.setFat(rs.getString(7));		//	음식 지방
					
				result = 1;
			}catch(Exception e){
				System.out.println("SQL문 실행 오류 : " + e);
			}finally {
				ResouceClose();
			}
			return result; //	result값이 1이면 true, 0이면 false

		}// (end)검색창옆에  음식영양소들을 뿌려주기 위한 셀렉트문 
		
	//식단 추가후 지정된 날짜와 아침점심저녁별 식단결과를 뿌려주기 위한 셀렉트문
	public ArrayList<foodBean> FoodAddResult(Date date, int memberNum,  int ftime) {
		
		ArrayList<foodBean> foodAddResult = new ArrayList<foodBean>();
			foodBean bean = null;
			
			try{
				con = ds.getConnection();
				String sql = "select * from foodManagement where member_num = ? and fdate = ? and ftime = ? order by num desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setDate(1,  date);	
				pstmt.setInt(2, memberNum);		
				pstmt.setInt(3, ftime);	
				
				rs = pstmt.executeQuery();

				while(rs.next()){
					bean = new foodBean();
					bean.setFnum(rs.getInt(1));
					bean.setFmnum(rs.getInt(2));
					bean.setFdate(rs.getDate(3));
					bean.setFtime(rs.getInt(4));
					bean.setFname1(rs.getString(5));
					bean.setFname2(rs.getString(6));
					bean.setFname3(rs.getString(7));
					bean.setFname4(rs.getString(8));
					bean.setFname5(rs.getString(9));
					bean.setFserving1(rs.getInt(10));
					bean.setFserving2(rs.getInt(11));
					bean.setFserving3(rs.getInt(12));
					bean.setFserving4(rs.getInt(13));
					bean.setFserving5(rs.getInt(14));
					
					foodAddResult.add(bean);	
				}
			}catch(Exception e){
				System.out.println("SQL문 실행 오류 : " + e);
			}finally {
				ResouceClose();
			}
			return foodAddResult; 
	}//(end)식단 추가후 지정된 날짜와 아침점심저녁별 식단결과를 뿌려주기 위한 셀렉트문
	
	//식단 추가 메소드
	//bean에 저장된 값들을 매개변수로 불러와 DB에 해당 내용을 추가한다.
	public void addFood(foodBean fBean){
		int num = 0;
		try{
			con = ds.getConnection();
			
			// insert작업 전 변수 num에 작성순서대로 1씩 증가시키는 작업
			String sql = "select max(fnum) from foodManagement";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt(1)+1;
			}else{
				num = 1;
			}
			
//true일 경우 insert문 실행 false일 경우 update문 실행
			//fname과 fserving값 뒤에 반복문i값을 넣고 조회를 했을때 값이 존재한다면
			//
			for(int i=1; i<=5; i++){
				sql = "select fname+? fserving+? from foodManagerment "
						+ "where fmnum = ? and fdate = ? ftime =?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, i);	
				pstmt.setInt(2, i);	
				pstmt.setInt(3, fBean.getFmnum());	
				pstmt.setDate(4, fBean.getFdate());	
				pstmt.setInt(5, fBean.getFtime());	
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					
				}else{
					//	fname1값이 존재하지 않을 경우 
					//  insert작업
					if(i==1){
						sql = "insert into foodManagement(fnum, fmnum, fdate, ftime, "
								+ "fname1, fserving1) values(?,?,?,?,?,?)";
						
						pstmt = con.prepareStatement(sql);
						
						pstmt.setInt(1, num);
						pstmt.setInt(2, fBean.getFmnum());
						pstmt.setDate(3, fBean.getFdate());
						pstmt.setInt(4, fBean.getFtime());
						pstmt.setString(5, fBean.getFname1());
						pstmt.setInt(6, fBean.getFserving1());
						
						pstmt.executeUpdate();
					}
				}
			}
			
				
		}catch(Exception e){
			System.out.println("SQL문 실행 오류 : " + e);
		}finally {
			ResouceClose();
		}
	}
	
	
	
	
	
	
	
	
	
}
