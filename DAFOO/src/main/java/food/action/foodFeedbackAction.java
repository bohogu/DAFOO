package food.action;

import java.io.PrintWriter;


import java.sql.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import food.db.foodBean;
import food.db.foodDAO;
import member.db.MemberBean;

public class foodFeedbackAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
									HttpServletResponse response) throws Exception {
		//	서블릿 잘 도착 했는지 확인
		System.out.println("foodFeedbackAction 도착!");
		
		// 	session에 아이디 값 가져오기
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("id");
		
		//	활동지수 값 가져오기
		int activityChoice = Integer.parseInt(request.getParameter("activityChoice")); 
		//	활동지수 값 확인
		System.out.println(activityChoice);
		
		//	DAO객체 생성
		foodDAO fDAO = new foodDAO();		
		//	bmi를 구하기 (매개변수는 회원 아이디 값으로 한다)
		MemberBean mBean = fDAO.foodMemberBean(userId);
		double height = mBean.getHeight(); //	키
		double weight = mBean.getWeight(); //	몸무게
		double age = mBean.getAge(); //	나이
		double bmiHeight = height*0.01; //	bmi를 구하기 위한 키*0.01
//		남자) 66.47 + (13.75 * 체중) + (5*키)-(6.76*나이)
//		여자) 665.1 + (9.56 * 체중) + (1.85*키)-(4.68*나이)
		
		
		//	bmi( 키*키 / 몸무게 ) 구하기
		double bmi = weight/(bmiHeight*bmiHeight);
 		//	표준체중( (자신의 키 - 100) * 0.9) 구하기
		double sdWeight = (height-100)*0.9;
		//	표준bmi 구하기
		double sdBmi = sdWeight/(bmiHeight*bmiHeight);
		//	하루 권장 칼로리 = 표준체중(kg) * 활동지수
		double dailyCal = sdWeight*activityChoice;
		//	하루 권장 단백질 = 몸무게
		double dailyProtein = weight;
		
		//	기초대사량 구하기
		double maleRmr = 66.47 + (13.75*weight) + (5*height) - (6.76*age);
		double femaleRmr = 665.1 + (9.56 * weight) + (1.85*height)-(4.68*age);
				
		
		//	구한 공식들을 각각 reuqest에 저장
		request.setAttribute("Bmi", bmi);
		request.setAttribute("SdBmi", sdBmi);
		request.setAttribute("SdWeight", sdWeight);
		request.setAttribute("DailyCal", dailyCal);
		request.setAttribute("DailyProtein", dailyProtein);
		request.setAttribute("MaleRmr", maleRmr);
		request.setAttribute("FemaleRmr", femaleRmr);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/main.jsp?command=Diet/FoodMain.jsp");		
//true	forward.setPath("/DAFOO/Diet/FoodMain.jsp");
		
		
		return forward;
	}
	
}
