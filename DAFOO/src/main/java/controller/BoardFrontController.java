package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.action.BoardContentAction;
import board.action.BoardDeleteAction;
import board.action.BoardListAction;
import board.action.BoardUpdateAction;
import board.action.BoardWriteAction;
import board.action.MainAction;
import board.action.BoardUpdate;

// @WebServlet("*.bo")
public class BoardFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();		
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		System.out.println(request.getParameter("bnum"));
		System.out.println("//"+command);
		
		
		if(command.equals("/BoardList.bo")){
			action = new BoardListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/BoardWrite.bo")){
			forward = new ActionForward();
			
			System.out.println("BoardWrite.bo:"+request.getParameter("bgroup"));
			forward.setPath("/main.jsp?command=Board/write.jsp?bgroup="+request.getParameter("bgroup"));
			
		} else if(command.equals("/BoardWriteAction.bo")){
			action = new BoardWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/BoardContent.bo")){
			action = new BoardContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/BoardUpdate.bo")){
			action = new BoardUpdate();
			try {
				forward = action.execute(request, response);
				forward.setPath("/main.jsp?command=Board/update.jsp?bgroup="+request.getParameter("bgroup"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/BoardUpdateAction.bo")){
			action = new BoardUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/BoardDeleteAction.bo")){
			action = new BoardDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/Main.bo")){
			// MainAction 
			action = new MainAction();
			try {
				forward = action.execute(request, response);
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
