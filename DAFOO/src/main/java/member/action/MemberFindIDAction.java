package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import member.db.MemberBean;
import member.db.MemberDAO;

public class MemberFindIDAction  implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("member/MemberFindIDAction!");
		
		request.setCharacterEncoding("utf-8");
		
		String email = request.getParameter("email");
		if(email == null || email.trim().equals("")) {
			email = "0";
		}
		System.out.println(" 넘어온 이메일 " + email);
		
		MemberDAO dao = new MemberDAO();
		MemberBean mb = new MemberBean();
		
		int check = dao.emailCheck(email);
		
		if(check == 1) {
			mb = dao.getId(email);
		}
		else {

		}String id = mb.getId();
		
		if(id == null || id.trim().equals("")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('등록되지 않은 이메일 입니다.')");
				out.println("location.href = './MemberFindID.me'");
				out.println("</script>");
				out.flush();
		}
		else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('아이디는 "+ id +" 입니다.')");
				out.println("location.href = './MemberLoginPro.me?id=" + id+"'");
				out.println("</script>");
				out.flush();
		}
		
		return null;
	
	}

}
