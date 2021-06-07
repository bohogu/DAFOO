package Diary.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//DB작업할 클래스 
public class calendarDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//new FileDAO()객체 생성시 기본생성자가 호출되어서 DB연결을맺은 Conneciton객체 얻는 생성자
	public calendarDAO() {
		try{
			//1. Driver.class드라이버 에 대한 경로를 문자열로 전달해
			//   동적으로 new Driver()객체 생성후 DriverManager클래스에 저장(등록)
			//   요약 : 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			//2. DriverManager클래스의 getConnection메소드 호출시..
			//   접속할 DB 정보를 전달해  DB와 연결을 맺은  Connection객체 얻기
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jspbeginner", 
					                            "root", 
					                            "1234"); 
		}catch(Exception e){
			System.out.println("Connection객체 얻기 실패(DB연결실패)" + e);
		}
	}//생성자 끝
	
	//서버경로에 실제로 업로드된 파일명과 원본 파일명을  FILE DB내부의 file테이블에 INSERT
	public int upload(String fileName,String fileRealName){
										//마지막 0은 파일업로드시 업로드하는 파일의 다운로드한 횟수를 말함
		String sql = "INSERT INTO FILE VALUES(?,?,0);";
		
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, fileName);//서버에 업로드할 파일의 원본파일명
				pstmt.setString(2, fileRealName);//실제 서버경로에 업로드되어 있는 실제 파일명 
				
				return pstmt.executeUpdate();//성공하면 1을 반환 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //실패하면 -1 반환
	}
	
	
}//FileDAO클래스 끝












