package board.db;

public class pageDTO {

	private int totalRecord = 0; //게시판에 저장된 전체 글갯수 [2]로가서 구해라~
	private int numPerPage = 5; //한 페이지당 표시할 게시글 수
	private int pagePerBlock = 3; //한 블럭당 묶여질 페이지번호 갯수
	
	// 게시판 하단부분  | 이전3개 < 1 2 3 > 다음3개 |
	
	private int totalPage = 0; //전체 페이지 수 [4]로가서 구해라
	private int totalBlock = 0; //전체 블록 수  [9]
	private int nowPage = 0; //현재 보여진 페이지 번호 [7]
	private int nowBlock =0; //현재 보여진 페이지 번호가 속한 블록 번호 [8]
	private int beginPerPage = 0;//각 페이지마다 글목록에 나타나는 시작 글번호 저장 [10]
	
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public int getPagePerBlock() {
		return pagePerBlock;
	}
	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalBlock() {
		return totalBlock;
	}
	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getNowBlock() {
		return nowBlock;
	}
	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}
	public int getBeginPerPage() {
		return beginPerPage;
	}
	public void setBeginPerPage(int beginPerPage) {
		this.beginPerPage = beginPerPage;
	}
	
	
	
	
}

