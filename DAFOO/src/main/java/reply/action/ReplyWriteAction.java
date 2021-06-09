package reply.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import reply.db.ReplyBean;
import reply.db.ReplyDAO;

public class ReplyWriteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		request.getParameter("nick");
		
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		
		ReplyBean rb = new ReplyBean();
		ReplyDAO rdao = new ReplyDAO();
		
		rb.setContent(request.getParameter("content"));
		rb.setBnum(Integer.parseInt(request.getParameter("bnum")));
		rb.setNick(request.getParameter("nick"));
		
		
		rdao.write(rb);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./BoardContent.bo?bnum="+bnum);
		return forward;
	}
}