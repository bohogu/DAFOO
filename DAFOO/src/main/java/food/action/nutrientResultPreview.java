package food.action;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import food.db.foodBean;
import food.db.foodDAO;

@WebServlet("/nutrientRsultPreview")
public class nutrientResultPreview extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet nutrientRsultPreview 실행!");
		execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost nutrientRsultPreview 실행!");
		execute(request, response);
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		//	회원넘버 받아옴
		int MNUM = Integer.parseInt(request.getParameter("mNum"));
		//	날짜 받아옴
		String StartDate = request.getParameter("startDate");
		//	아점저 받아옴
		int fTime = Integer.parseInt(request.getParameter("Ftime"));
		//	DAO생성
		foodDAO fDAO = new foodDAO();
		//	BEAN생성
		foodBean fBean = new foodBean();
		
		JSONArray cell = new JSONArray(); // JSONObject list 를 넣을 JSONArray
		String data = null; // 데이터를 꺼내올 BeanVO 객체 생성
		
		ArrayList arrayResult = fDAO.FoodResultPreview(1, StartDate, fTime);
		
		// 총 1회 제공량
		Double sizeResult = 0.0;
		// 총 칼로리
		Double calResult = 0.0;
		// 총 탄수화물
		Double carboResult = 0.0;
		// 총 단백질
		Double proteinResult = 0.0;
		// 총 지방 
		Double fatResult = 0.0;
		
		//fcount를 이용한 반복 size
		int Size = fDAO.selectFoodCount(MNUM, StartDate,fTime);
		
		
		for(int i=1; i<=Size; i++) {
			ArrayList arrayNutrient = fDAO.searchFoodAndServings(i, i, MNUM, StartDate, fTime);
			
			sizeResult += Double.parseDouble((String)arrayNutrient.get(2));			//  제공량
			calResult += Double.parseDouble((String)arrayNutrient.get(3));			//  칼로리
			carboResult += Double.parseDouble((String)arrayNutrient.get(4));		//  탄수화물
			proteinResult += Double.parseDouble((String)arrayNutrient.get(5));		// 	단백질
			fatResult += Double.parseDouble((String)arrayNutrient.get(6));			// 	지방
		}
		
//		배열에 담아 제이슨으로 변환을 위해서 다시 스트링 타입으로 변환

		ArrayList arrayResult2 = new ArrayList();
		
		arrayResult2.add(Double.toString(sizeResult));
		arrayResult2.add(Double.toString(calResult));
		arrayResult2.add(Double.toString(carboResult));
		arrayResult2.add(Double.toString(proteinResult));
		arrayResult2.add(Double.toString(fatResult));
		
		for(int i=0; i<arrayResult2.size(); i++) { 
			data = (String) arrayResult2.get(i);
			JSONObject obj = new JSONObject(); 
			obj.put( "AddNutrient" , data); 
			cell.add(obj); 
		}
		
		out.print(cell);
		out.flush();
		out.close();
		
	}
}

