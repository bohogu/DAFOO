package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import member.db.MemberDAO;
import member.exception.LoginException;



public class MemberDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("member/MemberDeleteProAction!");
		
		ActionForward forward = null;
		
		String pass = request.getParameter("pass");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		try {
			MemberDAO dao = new MemberDAO();
			boolean isMember = dao.selectLoginMember(id, pass);
			
			if (isMember) {
			
				boolean isDeleteSuccess = dao.deleteMember(id);
				
				if (isDeleteSuccess) {
					session.removeAttribute("id");
					
					forward = new ActionForward();
					forward.setPath("./member/login.jsp");
					forward.setRedirect(true);

					
				} else {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('삭제 실패!')");
					out.println("history.back()");
					out.println("</script>");
					
				}
				
				
			}
		} catch (LoginException e) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('" + e.getMessage() +"')"); // 실패 메세지 출력
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>");
			
		} 
		
		
		return forward;
	}

}
