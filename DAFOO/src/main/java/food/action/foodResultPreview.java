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

@WebServlet("/foodResultPreView")
public class foodResultPreview extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		//	인분 받아옴
		int ftime = Integer.parseInt(request.getParameter("fTime"));
		//	DAO생성
		foodDAO fDAO = new foodDAO();
		
		JSONArray cell = new JSONArray(); // JSONObject list 를 넣을 JSONArray
		String data = null; // 데이터를 꺼내올 BeanVO 객체 생성
		
		ArrayList arrayResult = fDAO.FoodResultPreview(1, StartDate, ftime);
//		System.out.println(arrayResult); 6월 4일 실행 = [오브세이 우주사탕 A, 아, 도라에몽 솜사탕1, null, null, 1, 1, 1, 1, 1]
		
		for(int i=0; i<arrayResult.size(); i++) { // 루프를 돌려 list에 담긴 데이터를 BeanVO에 주입
			data = (String)arrayResult.get(i); // 이제 data 에는 객체들이 차례로 담겼음
			JSONObject obj = new JSONObject(); // 다시 한번 JSONObject로 감싸기 위해 객체 선언
			obj.put( "breakfast" , data); // obj에 객체의 데이터를 꺼내 차례로 담는다
			cell.add(obj); // 아까 만들어진 cell Array객체에 VO담은 객체를 주입
		}
		
		response.setHeader("content-type", "application/json");
		PrintWriter pw = response.getWriter(); 
		out.print(cell);
		out.flush();
		out.close();
		
	}
}

