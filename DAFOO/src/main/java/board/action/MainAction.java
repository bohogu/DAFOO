package board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.db.BoardBean;
import board.db.BoardDAO;

public class MainAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MainAction execute()");
		// 디비 객체 생성 BoardDAO  bdao
		BoardDAO bdao=new BoardDAO();
		
		String pageNum="1";
		List<BoardBean> boardList=null;

		request.setAttribute("boardList", boardList);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./main/main.jsp");
		return forward;
	}
}
