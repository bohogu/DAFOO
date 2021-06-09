package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.db.BoardBean;
import board.db.BoardDAO;

public class BoardWriteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardWriteAction execute()");
		//한글처리
		request.setCharacterEncoding("utf-8");
		
		BoardBean bb=new BoardBean();
		int bgroup = Integer.parseInt(request.getParameter("bgroup"));
		System.out.println("BoardWriteAction:"+request.getParameter("bgroup"));
		
		bb.setNick("stew");
		System.out.println(request.getParameter("nick"));
		System.out.println(request.getParameter("boardTitle"));
		System.out.println(request.getParameter("boardContent"));
		System.out.println(request.getParameter("boardFile"));
		System.out.println(request.getParameter("bgroup"));
		bb.setTitle(request.getParameter("boardTitle"));
		bb.setContent(request.getParameter("boardContent"));
		bb.setFile(request.getParameter("boardFile"));
		bb.setBgroup(bgroup); 
		// 객체 생성 BoardDAO bdao
		BoardDAO bdao= new BoardDAO();
		//  insertBoard(bb)메서드 호출
		bdao.write(bb);
		// 이동  ./BoardList.bo
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./BoardList.bo?bgroup="+bgroup);
		return forward;
	}
}
