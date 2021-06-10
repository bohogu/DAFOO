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

public class foodAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
									HttpServletResponse response) throws Exception {
		System.out.println("foodAddAction 도착!");
		
		String searchFood = request.getParameter("searchFood");			//	검색창 음식 이름
		String startDate = request.getParameter("startDate");			//	날짜 xxxx-dd-yy
		int servInput = Integer.parseInt(request.getParameter("servInput"));			//	인분
		String calPrint = request.getParameter("calPrint");				//	칼로리
		String carboPrint = request.getParameter("carboPrint");			//	탄수화물
		String proteinPrint = request.getParameter("proteinPrint");		//	단백질
		String fatPrint = request.getParameter("fatPrint");				//	지방
		int hTime = Integer.parseInt(request.getParameter("hTime"));	//	아침,점심,저녁
		
		int ajaxDate = 1;
		
		System.out.println(hTime);
		
		foodDAO fDAO = new foodDAO();		//	DAO객체 생성
//		fcount 조회		count 값 가져옴	(회원아이디, 날짜 , 아점저)
		int fcount = fDAO.selectFoodCount(1, startDate, hTime);	
		
//		영양소 값들이 모두0일 경우(DB에 해당 음식이 없을 경우) 추가 기능을 지원하지 않는다.
		if(calPrint=="0" && carboPrint=="0" && proteinPrint=="0" && fatPrint=="0"
			|| calPrint.equals("0") && carboPrint.equals("0") && proteinPrint.equals("0") && fatPrint.equals("0")) {
		}else {
			if(fcount==0) {
//				fcount가 0이면 인서트문을 실행
				fcount = 1;
				fDAO.addInsertFood(1, startDate, hTime, searchFood, fcount, servInput);
			}else if(fcount<=4){
//				fcount가 4이하면 fcount에 1을 더하는 업데이트문 실행
				fDAO.addUpdateFood(1, startDate, hTime, searchFood, servInput, fcount);
			}else if(fcount>=5){
				System.out.println("더 이상 식단을 추가 할 수가 없습니다!");
			}
		}
		
		
		
		request.setAttribute("StartDate", startDate);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/main.jsp?command=Diet/FoodMain.jsp");
		
		
		return forward;
		
	}
	
}
