package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.db.BoardDAO;

public class BoardDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		int bgroup = Integer.parseInt(request.getParameter("bgroup"));
		
		BoardDAO bdao=new BoardDAO();
		
		bdao.delete(bnum);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/BoardList.bo?bgroup="+bgroup);
		return forward;
	}
}
