package action;

public class ActionForward {
	
	private String path=null; // 포워딩 할 View 페이지 URL 을 저장
	private boolean redirect=false; // 포워딩 방식 저장
	// true : Redirect 방식, false : Dispatcher 방식
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
}
