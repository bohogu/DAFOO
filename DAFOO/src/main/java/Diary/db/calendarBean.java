package Diary.db;

import java.sql.Date;

import javax.naming.Context;

public class calendarBean {

	private int	unq;
	private String userid;		
	private Date pdate; 
	private String title;
	private Context text;
	
	String fileName;//업로드(다운로드)한 파일의 원본이름 저장할 용도의 변수 
	String fileRealName;//업로드(다운로드)한 파일의 실제이름을 저장할 용도의 변수 
	int downloadCount;//다운로드한 횟수 저장할 용도의 변수 
	
	public calendarBean() {
		
	}
	
	
	//인스턴스 생성시 위 fileName,fileRealName, downloadCount인스턴스변수의 값을 초기화 시킬 생성자
	public calendarBean(String fileName, String fileRealName, int downloadCount) {
		this.fileName = fileName;
		this.fileRealName = fileRealName;
		this.downloadCount = downloadCount;
	}
	
	
	
	public int getUnq() {
		return unq;
	}
	public void setUnq(int unq) {
		this.unq = unq;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Context getText() {
		return text;
	}
	public void setText(Context text) {
		this.text = text;
	}


	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	
	
	
}
