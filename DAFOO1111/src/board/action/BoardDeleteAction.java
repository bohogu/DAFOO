package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.db.BoardDAO;

public class BoardDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardDeleteAction  execute()");
		
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		
		BoardDAO bdao=new BoardDAO();
		
		bdao.delete(bnum);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./BoardList1.bo");
		return forward;
	}
}
