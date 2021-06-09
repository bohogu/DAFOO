package food.db;

import java.sql.Date;

public class foodBean {
	
	//음식 테이블
	private int food_idx;		//	테이블 순서
	private String food_name;	//	음식이름
	private String food_size; 	//	인분
	private String cal; 		//	칼로리
	private String carbo;  	 	//	탄수화물
	private String protein; 	//	단백질
	private String fat;			//	지방
	
	//식단추가 테이블
	private int fnum;			//	테이블 순서
	private int mnum;			//	회원넘버
	private Date fdate;			//	날짜
	private int ftime;			//	아침,점심,저녁
	private String fname1;		//		음식1
	private String fname2;		//	음식2
	private String fname3;		//	음식3
	private String fname4;		//	음식4
	private String fname5;		//	음식5
	private int fserving1;		//		인분1
	private int fserving2;		//	인분2	
	private int fserving3;		//	인분3	
	private int fserving4;		//	인분4	
	private int fserving5;		//	인분5		
	private int fcount;			//	카운트(음식 추가)		
	
	
//   --------------------음식-------------------------------	
	
	public int getFood_idx() {
		return food_idx;
	}
	public void setFood_idx(int food_idx) {
		this.food_idx = food_idx;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public String getFood_size() {
		return food_size;
	}
	public void setFood_size(String food_size) {
		this.food_size = food_size;
	}
	public String getCal() {
		return cal;
	}
	public void setCal(String cal) {
		this.cal = cal;
	}
	public String getCarbo() {
		return carbo;
	}
	public void setCarbo(String carbo) {
		this.carbo = carbo;
	}
	public String getProtein() {
		return protein;
	}
	public void setProtein(String protein) {
		this.protein = protein;
	}
	public String getFat() {
		return fat;
	}
	public void setFat(String fat) {
		this.fat = fat;
	
	
//--------------------식단추가-------------------------------
	
	
	}
	public int getFnum() {
		return fnum;
	}
	public void setFnum(int fnum) {
		this.fnum = fnum;
	}
	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	public Date getFdate() {
		return fdate;
	}
	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}
	public int getFtime() {
		return ftime;
	}
	public void setFtime(int ftime) {
		this.ftime = ftime;
	}
	public String getFname1() {
		return fname1;
	}
	public void setFname1(String fname1) {
		this.fname1 = fname1;
	}
	public String getFname2() {
		return fname2;
	}
	public void setFname2(String fname2) {
		this.fname2 = fname2;
	}
	public String getFname3() {
		return fname3;
	}
	public void setFname3(String fname3) {
		this.fname3 = fname3;
	}
	public String getFname4() {
		return fname4;
	}
	public void setFname4(String fname4) {
		this.fname4 = fname4;
	}
	public String getFname5() {
		return fname5;
	}
	public void setFname5(String fname5) {
		this.fname5 = fname5;
	}
	public int getFserving1() {
		return fserving1;
	}
	public void setFserving1(int fserving1) {
		this.fserving1 = fserving1;
	}
	public int getFserving2() {
		return fserving2;
	}
	public void setFserving2(int fserving2) {
		this.fserving2 = fserving2;
	}
	public int getFserving3() {
		return fserving3;
	}
	public void setFserving3(int fserving3) {
		this.fserving3 = fserving3;
	}
	public int getFserving4() {
		return fserving4;
	}
	public void setFserving4(int fserving4) {
		this.fserving4 = fserving4;
	}
	public int getFserving5() {
		return fserving5;
	}
	public void setFserving5(int fserving5) {
		this.fserving5 = fserving5;
	}
	public int getFcount() {
		return fcount;
	}
	public void setFcount(int fcount) {
		this.fcount = fcount;
	}
	
	
}
