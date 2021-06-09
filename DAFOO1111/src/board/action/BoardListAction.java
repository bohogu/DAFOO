package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.db.BoardBean;
import board.db.BoardDAO;

public class BoardListAction implements Action{
	int bgroup;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardListAction execute()");

		bgroup = Integer.parseInt(request.getParameter("bgroup"));
		BoardDAO bdao = new BoardDAO();
		
		ArrayList<BoardBean> v = bdao.getList(bgroup);
		request.setAttribute("v", v);
		request.setAttribute("bgroup", bgroup);


		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/main.jsp?command=Board/board.jsp&bgroup="+bgroup);
		return forward;
	}
}
