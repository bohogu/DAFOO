package member.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import member.db.MemberBean;
import member.db.MemberDAO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("member/MemberInfoAction!");
		
		// session의 id값 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println("id : " + id);
		
		MemberDAO dao = new MemberDAO();
		MemberBean article = dao.selectArticle(id);
		
		request.setAttribute("article", article);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/member/member_info.jsp");
		
		return forward;
	}

}