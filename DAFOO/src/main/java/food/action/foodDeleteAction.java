package food.action;

import java.io.PrintWriter;
import java.sql.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import food.db.foodBean;
import food.db.foodDAO;

public class foodDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
									HttpServletResponse response) throws Exception {
		System.out.println("foodDeleteAction 도착!");
		
		int fNum = Integer.parseInt(request.getParameter("fnum"));			//	검색창 음식 이름
		
		foodDAO fDAO = new foodDAO();		//	DAO객체 생성
		fDAO.deleteFood(fNum);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/main.jsp?command=Diet/FoodMain.jsp");
		return forward;
		
	}
	
}
