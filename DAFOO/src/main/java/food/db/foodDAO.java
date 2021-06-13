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

import org.json.simple.JSONObject;

import member.db.MemberBean;

import org.json.simple.JSONArray;


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
			ds = (DataSource)envContext.lookup("jdbc/dafoo");
	
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

			String sql = "select food_name from food where food_name  like ? order by food_idx desc ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				String fname = rs.getString("food_name");
				list.add(fname);
			}
		}catch (Exception e){
			System.out.println("listDiet SQL문 실행 오류 : " + e);
		}finally {
			ResouceClose();
		}
		return list;
	}//(end)검색 자동완성(AutoComplete) 
	
	// 검색창옆에  음식영양소들을 뿌려주기 위한 셀렉트문
		public foodBean FoodNutrientResult(String foodName) {
			
			foodBean fbean = new foodBean();
			try{
				con = ds.getConnection();
				
				String sql = "select * from food where food_name = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, foodName);		
				rs = pstmt.executeQuery();
				
				
				if(rs.next()) {
					fbean.setFood_idx(rs.getInt(1));		//	음식 순서
					fbean.setFood_name(rs.getString(2));	//	음식 이름
					fbean.setFood_size(rs.getString(3));	//	음식 인분
					fbean.setCal(rs.getString(4));			//	음식 칼로리
					fbean.setCarbo(rs.getString(5));		//	음식 탄수화물
					fbean.setProtein(rs.getString(6));		//	음식 단백질
					fbean.setFat(rs.getString(7));			//	음식 지방
				}
			}catch(Exception e){
				System.out.println("FoodNutrientResult SQL문 실행 오류 : " + e);
			}finally {
				ResouceClose();
			}
			return fbean;
			
		}// (end)검색창옆에  음식영양소들을 뿌려주기 위한 셀렉트문 
		
	
	//	[식단 추가 메소드]
	//	count값 불러오기
	public int selectFoodCount(int mnum, String startDate, int fTime) {
		int fCount = 0;
		try{
			con = ds.getConnection();
			foodBean fBean = new foodBean();
			
			String sql = "select fcount from dafoo where mnum=? and fdate=? and ftime=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1,  mnum);	
			pstmt.setString(2, startDate);		
			pstmt.setInt(3, fTime);	
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				fCount = rs.getInt(1);
			}
			
		}catch(Exception e){
			System.out.println("addFood 실행 오류 : " + e);
		}finally {
			ResouceClose();
		}
		return fCount;
	}//	(끝)	count값 불러오기
	
	// 음식 추가!
	public void addInsertFood(int mnum, String startDate, int hTime, String searchFood, int fcount, int servInput) {
		try{
			con = ds.getConnection();
			foodBean fBean = new foodBean();
			
			String sql = "insert into dafoo(mnum, fdate, ftime, fname1, fserving1, fcount)"
						+ "			 values(?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1,  mnum);	
			pstmt.setString(2,  startDate);	
			pstmt.setInt(3, hTime);		
			pstmt.setString(4, searchFood);	
			pstmt.setInt(5, servInput);	
			pstmt.setInt(6, fcount);	
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("addInsertFood 실행 오류 : " + e);
		}finally {
			ResouceClose();
		}
	}//	(끝) 음식 추가!
	
	// 음식 업데이트!
		public int addUpdateFood(int mnum, String startDate, int hTime, String searchFood,int serving,int fcount) {
			fcount +=1;
			try{
				con = ds.getConnection();
				foodBean fBean = new foodBean();
				
				String sql = "update dafoo set fname?=?, fserving?=?, fcount=fcount+1 where mnum=? and fdate=? and ftime=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1,  fcount);	
				pstmt.setString(2,  searchFood);	
				pstmt.setInt(3,  fcount);	
				pstmt.setInt(4, serving);	
				pstmt.setInt(5, mnum);	
				pstmt.setString(6, startDate);	
				pstmt.setInt(7, hTime);	
				
				pstmt.executeUpdate();
				
			}catch(Exception e){
				System.out.println("addUpdateFood 실행 오류 : " + e);
			}finally {
				ResouceClose();
			}
			return fcount;
		}//	(끝) 음식 업데이트!
		
		// 음식 결과 미리보는 출력문 
		public ArrayList<String> FoodResultPreview(int mNum, String startDate,int ftime) {
			
			ArrayList<String> FoodResultPreview = new ArrayList<String>();
			
			try{
				con = ds.getConnection();
				String sql = "select fnum, fname1, fname2, fname3, fname4, fname5, "
						+ "fserving1, fserving2, fserving3, fserving4, fserving5 "
						+ "from dafoo where mnum=? and fdate=? and ftime=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, mNum);	
				pstmt.setString(2, startDate);	
				pstmt.setInt(3, ftime);
				
				rs = pstmt.executeQuery();

				while(rs.next()){
					FoodResultPreview.add(rs.getString("fnum"));
					FoodResultPreview.add(rs.getString("fname1"));
					FoodResultPreview.add(rs.getString("fname2"));
					FoodResultPreview.add(rs.getString("fname3"));
					FoodResultPreview.add(rs.getString("fname4"));
					FoodResultPreview.add(rs.getString("fname5"));
					FoodResultPreview.add(rs.getString("fserving1"));
					FoodResultPreview.add(rs.getString("fserving2"));
					FoodResultPreview.add(rs.getString("fserving3"));
					FoodResultPreview.add(rs.getString("fserving4"));
					FoodResultPreview.add(rs.getString("fserving5"));
				}
			}catch(Exception e){
				System.out.println("FoodResultPreview : " + e);
			}finally {
				ResouceClose();
			}
			return FoodResultPreview; 
		}// (끝) 음식 결과 미리보는 출력문 
		

		// 음식과 해당 영양소 검색
		public ArrayList searchFoodAndServings(int fservingNum,int fnameNum, int mNum, String fDate, int fTime) {
			
			ArrayList searchFoodAndServings = new ArrayList();
			
			try{
				con = ds.getConnection();
				String sql = "select d.fserving?, f.food_name, f.food_size, f.cal, f.carbo, f.protein, f.fat "
						+ "from food f JOIN dafoo d "
						+ "ON f.food_name = d.fname? and  d.mnum=? and d.fdate=? and d.ftime=?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, fservingNum);	
				pstmt.setInt(2, fnameNum);	
				pstmt.setInt(3, mNum);	
				pstmt.setString(4, fDate);	
				pstmt.setInt(5, fTime);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					searchFoodAndServings.add(rs.getString(1));
					searchFoodAndServings.add(rs.getString("food_name"));
					searchFoodAndServings.add(rs.getString("food_size"));
					searchFoodAndServings.add(rs.getString("cal"));
					searchFoodAndServings.add(rs.getString("carbo"));
					searchFoodAndServings.add(rs.getString("protein"));
					searchFoodAndServings.add(rs.getString("fat"));
				}
			}catch(Exception e){
				System.out.println("FoodResultPreview : " + e);
			}finally {
				ResouceClose();
			}
			return searchFoodAndServings; 
		}// (끝) 음식과 해당 영양소 검색
		
		// 음식 삭제!
		public void deleteFood(int fnum) {
			try{
				con = ds.getConnection();
				
				String sql = "delete from dafoo where fnum=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1,  fnum);	
				
				pstmt.executeUpdate();
				
			}catch(Exception e){
				System.out.println("deleteFood 실행 오류 : " + e);
			}finally {
				ResouceClose();
			}
		}//	(끝) 음식 삭제!
		
		//	음식 검색창에 DB에 저장된 음식을 검색 하였는지 확인하기 위한 과정
		public int CheckSearchFood(String searchFood) {
			//	조회된 음식 값이 없다면 0
			int result = 0;
			
			try{
				con = ds.getConnection();
				String sql = "select food_name "
						+ 	 "from food "
						+ 	 "where food_name = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, searchFood);	
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					//조회된 음식 값이 존재 한다면 1
					result = 1;
				}
			}catch(Exception e){
				System.out.println("FoodResultPreview : " + e);
			}finally {
				ResouceClose();
			}
			return result; 
		}
		//	(끝)음식 검색창에 DB에 저장된 음식을 검색 하였는지 확인하기 위한 과정
		
		//bmi지수와 표준체중을 구하기위한 MemberBean 생성
		public MemberBean foodMemberBean(String userId) {
			
			MemberBean mBean = null;
			
			try {
				
				con = ds.getConnection();
				String sql = "select height, weight, age FROM pmember WHERE id=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, userId);	
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					mBean = new MemberBean();
					
					mBean.setHeight(rs.getInt(1));
					mBean.setWeight(rs.getInt(2));
					mBean.setAge(rs.getInt(3));
				}
			}catch(Exception e){
				System.out.println("foodMemberBean : " + e);
			}finally {
				ResouceClose();
			}
			return mBean; 
		}//	(끝)bmi지수와 표준체중을 구하기위한 MemberBean 생성
}
	
	
	
	
	

