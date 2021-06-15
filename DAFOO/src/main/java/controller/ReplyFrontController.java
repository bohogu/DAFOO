package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import reply.action.ReplyDeleteAction;
import reply.action.ReplyWriteAction;

// @WebServlet("*.re")
public class ReplyFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();		
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/ReplyWrite.re")){
			action = new ReplyWriteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/ReplyDelete.re")){
			action = new ReplyDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//이동
		if(forward!=null){
			if(forward.isRedirect()){//true
				response.sendRedirect(forward.getPath());
			}else{//false
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
