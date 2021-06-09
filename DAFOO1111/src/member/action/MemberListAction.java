package member.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import member.db.MemberBean;
import member.db.MemberDAO;

public class MemberListAction implements Action{
					
				
	        @Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        	MemberDAO dao = new MemberDAO();
		        List<MemberBean> list = dao.selectAllMember();
		        System.out.println("123213");
		        request.setAttribute("memList", list);
				ActionForward forward = new ActionForward();
				forward.setPath("/member/member_list.jsp");
				return forward;
	        }
			
	    
	}


