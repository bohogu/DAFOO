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
		
		request.setCharacterEncoding("utf-8");
		
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		
		BoardBean bb = new BoardBean();
		
		bb.setNick(request.getParameter("nick"));
		bb.setTitle(request.getParameter("title"));
		bb.setContent(request.getParameter("content"));
		bb.setFile(request.getParameter("file"));
		bb.setBnum(Integer.parseInt(request.getParameter("bnum")));
		bb.getBgroup();
		
		BoardDAO bdao = new BoardDAO();
		
		bdao.update(bb);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./BoardContent.bo?bnum="+bnum);
		return forward;
	}
}




