package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.action.BoardDeleteAction;
import food.action.foodAddAction;
import food.action.foodDeleteAction;

public class FoodFrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");
        actionDo(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");
        actionDo(request, response);
    }
    
    private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("actionDo");
        
        
//        	uri 	= /DAFOO/*.fd
        String uri = request.getRequestURI();
//    		conPath = /DAFOO       
        String conPath = request.getContextPath();
//    		command = /*.fd      
        String command = uri.substring(conPath.length());
        	
        ActionForward forward = null;
        Action action = null;
        
        if(command.equals("/Diet/foodAdd.fd")){
        	action = new foodAddAction();
        	try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(command.equals("/Diet/foodDelete.fd")){
        	action = new foodDeleteAction();
        	try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(command.equals("/select.do")){
            System.out.println("select");
            System.out.println("----------------");
        }else if(command.equals("/delete.do")){
            System.out.println("delete");
            System.out.println("----------------");
        }
//        이동
  		if(forward!=null){
  			if(forward.isRedirect()){//true
  				response.sendRedirect(forward.getPath());
  			}else{//false
  				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
  				dispatcher.forward(request, response);
  			}
  		}
    }
}
