package reply.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import reply.db.ReplyBean;
import reply.db.ReplyDAO;

public class ReplyDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("////del");
		
		int rnum = Integer.parseInt(request.getParameter("rnum"));
		System.out.println(request.getParameter("rnum"));
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		System.out.println(request.getParameter("bnum"));
		
		ReplyBean rb = new ReplyBean();
		ReplyDAO rdao = new ReplyDAO();
		
		rb.setBnum(Integer.parseInt(request.getParameter("bnum")));
		
		rdao.delete(rnum);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./BoardContent.bo?bnum="+bnum);
		return forward;
	}
}
