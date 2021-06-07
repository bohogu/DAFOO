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

@WebServlet("/foodNutrient")
public class foodNutrientAction extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet foodNutrientAction 실행!");
		execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost foodNutrientAction 실행!");
		execute(request, response);
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String searchValue = request.getParameter("searchValue");
		foodDAO fDAO = new foodDAO();
		foodBean fBean = fDAO.FoodNutrientResult(searchValue);
		
		String json = "[{\"gSize\":\"" + fBean.getFood_size() + "\",\"gCal\":\"" + fBean.getCal() + "\",\"gCarbo\":\"" + fBean.getCarbo() + "\",\"gFat\":\"" + fBean.getFat() + 
				"\",\"gProtein\":\"" + fBean.getProtein() + "\"}]";
		
		response.setHeader("content-type", "application/json");
		System.out.println("json 출력문 : "+json);
		out.print(json);
		out.flush();
		out.close();
	}
}

