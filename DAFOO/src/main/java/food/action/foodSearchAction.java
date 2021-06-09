package food.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import food.db.foodBean;
import food.db.foodDAO;
@WebServlet("/foodJson")
public class foodSearchAction extends HttpServlet {
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("doGet foodSearchAction 실행!");
		execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("doPost foodSearchAction 실행!");
		execute(request, response);
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");

		// foodMain.jsp에서 Ajax로 넘어온 데이터 값
		String searchValue = request.getParameter("searchValue");
		JSONArray arrayObj=new JSONArray();
		JSONObject jsonObj = null;

		
		System.out.println(searchValue);
		foodDAO fDAO = new foodDAO();

		// searchList에 검색창에 입력한 값으로 시작하는 배열 값들을 받아옴
//		ArrayList<foodBean> resultlist = new ArrayList<foodBean>();
		ArrayList infoList = (ArrayList) fDAO.listDiet(searchValue);
		JSONObject jsonObject = new JSONObject(); // JSONObject 생성
		JSONArray cell = new JSONArray(); // JSONObject list 를 넣을 JSONArray
		String data = null; // 데이터를 꺼내올 BeanVO 객체 생성
		System.out.println(infoList);
//		수정후 ( 해당 글자로 시작하는 5개의 단어만 출력 )
		if(infoList.size()>5){
			for(int i=0; i<5; i++) { // 루프를 돌려 list에 담긴 데이터를 BeanVO에 주입
				data = (String)infoList.get(i); // 이제 data 에는 객체들이 차례로 담겼음
				JSONObject obj = new JSONObject(); // 다시 한번 JSONObject로 감싸기 위해 객체 선언
				obj.put( "foodName" , data); // obj에 객체의 데이터를 꺼내 차례로 담는다
				cell.add(obj);} // 아까 만들어진 cell Array객체에 VO담은 객체를 주입
			}else{
				for(int i=0; i<infoList.size(); i++) { // 루프를 돌려 list에 담긴 데이터를 BeanVO에 주입
					data = (String)infoList.get(i); // 이제 data 에는 객체들이 차례로 담겼음
					JSONObject obj = new JSONObject(); // 다시 한번 JSONObject로 감싸기 위해 객체 선언
					obj.put( "foodName" , data); // obj에 객체의 데이터를 꺼내 차례로 담는다
					cell.add(obj); // 아까 만들어진 cell Array객체에 VO담은 객체를 주입
			}
		}
		
//		jsonObject.put("rows", cell); // 마지막으로 JSON객체에 JSONArray 객체를 넣으면 끝!
		
//			{"rows":[{"foodName":"고형규"},{"foodName":"고등치"},{"foodName":"고등어"},{"foodName":"고등어"}]}
		//	cell = [{"foodName":"고형규"},{"foodName":"고등치"},{"foodName":"고등어"},{"foodName":"고등어"}]
		PrintWriter pw = response.getWriter(); 
		
		pw.print(cell); 
		pw.flush();
		pw.close();
	}
}
