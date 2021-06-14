package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.db.BoardBean;
import board.db.BoardDAO;
import board.db.pageDTO;

public class BoardListAction2 implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int bgroup = Integer.parseInt(request.getParameter("bgroup"));
		
		System.out.println(bgroup);
		pageDTO page = new pageDTO();
		
		int nowPage = page.getNowPage();
		int nowBlock = page.getNowBlock();
				
		BoardDAO bdao = new BoardDAO();
		
		ArrayList<BoardBean> v = bdao.getList(bgroup);
		
		System.out.println(v.size());
		page.setNumPerPage(page.getNumPerPage());
		page.setTotalRecord(v.size());
		page.setTotalPage((int)Math.ceil((double)page.getTotalRecord() / page.getNumPerPage()));
		page.setTotalBlock((int)Math.ceil((double)page.getTotalPage() / page.getPagePerBlock()));
		
		System.out.println(page.getTotalPage());
		if(request.getParameter("nowPage")!=null){
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
			page.setNowPage(nowPage);
		}

		if(request.getParameter("nowBlock")!=null){
			nowBlock = Integer.parseInt(request.getParameter("nowBlock"));
			page.setNowBlock(nowBlock);
			
		}
		
		page.setBeginPerPage(page.getNowPage()*page.getNumPerPage());
		
		
		
		request.setAttribute("v", v);
		request.setAttribute("bgroup", bgroup);
		request.setAttribute("page", page);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/main.jsp?command=Board/board2.jsp&bgroup="+bgroup+"&nowBlock="+nowBlock+"&nowPage="+nowPage);
		return forward;
	}
}
