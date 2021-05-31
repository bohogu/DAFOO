package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.db.BoardBean;
import board.db.BoardDAO;

public class BoardUpdate implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request.getParameter("bnum"));
		System.out.println("boardUpdate execute()");
		// int num  파라미터 가져오기
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		// BoardDAO bdao 객체 생성
		BoardDAO bdao = new BoardDAO();
		// BoardBean bb = 메서드호출 getBoard(num)
		BoardBean bb = bdao.getBoard(bnum);
		// 저장  bb, pageNum
		request.setAttribute("bb", bb);
		// 이동 ./board/update.jsp
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/main.jsp?command=Board/update.jsp");
		return forward;
	}
}
