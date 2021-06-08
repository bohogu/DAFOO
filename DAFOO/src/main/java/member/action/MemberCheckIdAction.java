package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import member.db.MemberDAO;




public class MemberCheckIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("member/MemberCheckIdAction");
		
		String id = request.getParameter("id");
		
		MemberDAO dao = new MemberDAO(); 
		
	boolean chekcIdResult = dao.checkArticle(id);

		
		String checkId = null;
		
		if (chekcIdResult) {
			checkId = "아이디 중복";
			
		} else {
			checkId = "사용 가능";
			
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/member/checkId.jsp?checkId=" + checkId);
		
		return forward;
	}

}
