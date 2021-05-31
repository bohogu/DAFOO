package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;

public class MemberMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberMainAction!");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
	
		
		ActionForward forward = new ActionForward();
		forward.setPath("/member/member_main.jsp");
		
		return forward;
	}

}
