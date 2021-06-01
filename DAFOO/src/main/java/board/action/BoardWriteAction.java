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
		
		request.setCharacterEncoding("utf-8");
		
		BoardBean bb = new BoardBean();
		int bgroup = Integer.parseInt(request.getParameter("bgroup"));
		System.out.println("BoardWriteAction:"+request.getParameter("bgroup"));
		
		if(bb.getNick() == null) {
			bb.setNick("pork");
		} else {
			bb.setNick("nick");
		}
		
		bb.setTitle(request.getParameter("boardTitle"));
		bb.setContent(request.getParameter("boardContent"));
		bb.setFile(request.getParameter("boardFile"));
		bb.setBgroup(bgroup); 
		
		BoardDAO bdao= new BoardDAO();
		
		bdao.write(bb);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./BoardList.bo?bgroup="+bgroup);
		return forward;
	}
}
