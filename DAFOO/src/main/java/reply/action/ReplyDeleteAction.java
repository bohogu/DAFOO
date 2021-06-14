package reply.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import reply.db.ReplyDAO;

public class ReplyDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("////del");
		
		int rnum = Integer.parseInt(request.getParameter("rnum"));
		
		ReplyDAO rdao = new ReplyDAO();
		
		rdao.delete(rnum);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("#");
		return forward;
	}
}
