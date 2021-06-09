package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.db.BoardBean;
import board.db.BoardDAO;

public class BoardUpdateAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardUpdateAction execute()");
		//한글처리
		request.setCharacterEncoding("utf-8");
		
		BoardBean bb = new BoardBean();
		
		bb.setNick(request.getParameter("nick"));
		bb.setTitle(request.getParameter("title"));
		bb.setContent(request.getParameter("content"));
		bb.setFile(request.getParameter("file"));
		bb.setBnum(Integer.parseInt(request.getParameter("bnum")));
		// BoardDAO bdao 객체 생성
		BoardDAO bdao = new BoardDAO();
		// int check  =메서드호출  updateBoard(bb)
		bdao.update(bb);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./BoardList1.bo");
		return forward;
	}
}




