package board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.db.BoardBean;
import board.db.BoardDAO;
import reply.db.ReplyBean;
import reply.db.ReplyDAO;

public class BoardContentAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int bnum = Integer.parseInt(request.getParameter("bnum"));

		BoardDAO bdao = new BoardDAO();
		ReplyDAO rdao = new ReplyDAO();
		
		bdao.hit(bnum);
		
		BoardBean bb = bdao.getBoard(bnum);
		List<ReplyBean> rb = rdao.getList(bnum);
		
		request.setAttribute("bb", bb);
		request.setAttribute("rb", rb);
		request.setAttribute("bgroup", bb.getBgroup());
		request.setAttribute("file", bb.getFile());
		request.setAttribute("rfile", bb.getRfile());
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/main.jsp?command=Board/content.jsp");
		return forward;
	}
}
