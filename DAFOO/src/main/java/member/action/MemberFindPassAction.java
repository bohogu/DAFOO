package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import member.db.MemberBean;
import member.db.MemberDAO;

public class MemberFindPassAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("member/MemberFindPassAction!");
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		if(id == null || id.trim().equals("")) {
			id ="0";
		}
		System.out.println( "넘어온 아이디 " + id);
		
		MemberDAO dao = new MemberDAO();
		MemberBean mb = new MemberBean();
		
		int check = dao.IDCheck(id);
		
		if(check == 1) {
			mb = dao.getPass(id);
		}
		else {
			
		}
		String pass = mb.getPass();
		
		if(pass == null || pass.trim().equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록되지 않은 아이디 입니다.')");
			out.println("location.href = './MemberFindPass.me'");
			out.println("</script>");
			out.flush();
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호는 " + pass +" 입니다.')");
			out.println("location.href = './MemberLoginPro.me?pass=" +pass+"'");
			out.println("</script>");
			out.flush();
		}
		
		return null;
	
	}

}