package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.db.BoardBean;
import board.db.BoardDAO;

public class BoardUpdate implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		
		BoardDAO bdao = new BoardDAO();
		
		BoardBean bb = bdao.getBoard(bnum);
		
		request.setAttribute("bb", bb);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/main.jsp?command=Board/update.jsp");
		return forward;
	}
}
