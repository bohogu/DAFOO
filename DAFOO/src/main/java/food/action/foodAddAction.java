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
		int checkComplete = 2;	// foodAddAction을 처리하고 나서 FoodMain에 alert을 띄우기 위한 변수
		int ajaxDate = 1;
		
		System.out.println(hTime);
		
		foodDAO fDAO = new foodDAO();		//	DAO객체 생성
		
		// 음식 검색창에 DB에 저장된 음식을 검색 하였는지 확인하기 위한 과정
		int fCheck = fDAO.CheckSearchFood(searchFood);
		// 조회된 음식 값이 없다면(true)
		if(fCheck == 0) {
			checkComplete = 1;
			// FoodMain으로 날짜 값을 보내기
			request.setAttribute("StartDate", startDate);
			// FoodMain으로 추가가 정상적으로 처리 되었다는 알림창을 띄우게 하기 위한 값을 보내기 위한 과정
			request.setAttribute("CheckComplete", checkComplete);
			System.out.println(searchFood + " 존재하지 않는 음식 값입니다");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/main.jsp?command=Diet/FoodMain.jsp");		
			//true	forward.setPath("/DAFOO/Diet/FoodMain.jsp");
			System.out.println("정상적으로 돌아옴");
			return forward;
		}else {
			
			
			
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
			
			
			// FoodMain으로 날짜값을 보낸다
			request.setAttribute("StartDate", startDate);
			// FoodMain으로 추가가 정상적으로 처리 되었다는 알림창을 띄우게 하기 위한 값을 보내기 위한 과정
			
			request.setAttribute("CheckComplete", checkComplete);
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/main.jsp?command=Diet/FoodMain.jsp");		
	//true	forward.setPath("/DAFOO/Diet/FoodMain.jsp");
			return forward;
		}
	}
	
}
