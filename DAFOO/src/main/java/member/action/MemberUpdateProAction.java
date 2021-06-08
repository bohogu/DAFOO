package member.action;
import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;

import member.db.MemberBean;
import member.db.MemberDAO;
import member.exception.LoginException;

public class MemberUpdateProAction implements Action {

	@Override
public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
		
		ActionForward forward = new ActionForward();
		String id = (String)(req.getSession().getAttribute("id"));
		
		String nick = req.getParameter("nick");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		int  age =  (Integer.parseInt(req.getParameter("age")));
		int  height =  (Integer.parseInt(req.getParameter("height")));
		int  weight =  (Integer.parseInt(req.getParameter("weight")));
		String phone =req.getParameter("phone");
		String email = req.getParameter("email");
		String postcode = req.getParameter("postcode");
		String address = req.getParameter("address");
		String detailAddress = req.getParameter("detailAddress");
		String extraAddress = req.getParameter("extraAddress");
		
		MemberBean member = new MemberBean();
		
		member.setNick(nick);
		member.setPass(pass);
		member.setName(name);
		member.setAge(age);
		member.setHeight(height);
		member.setWeight(weight);
		member.setPhone(phone);
		member.setEmail(email);
		member.setPostcode(postcode);
		member.setAddress(address);
		member.setDetailAddress(detailAddress);
		member.setExtraAddress(extraAddress);
		member.setId(id);
		
		
		
		MemberDAO dao = new MemberDAO();
		dao.updateMember(member);
		
		forward.setPath("MemberMain.me");
		forward.setRedirect(true);
		return forward;
	}	
}