package board.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import action.ActionForward;
import board.db.BoardDAO;

public class BoardUpdateAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		MultipartRequest multi = null;
		int fileMaxSize = 10 * 1024 * 1024;
		String savePath = request.getRealPath("/upload").replaceAll("\\\\", "/");
		try {
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		BoardDAO bdao = new BoardDAO();
		
		String title = multi.getParameter("boardTitle");
		String content = multi.getParameter("boardContent");
		int bnum = Integer.parseInt(multi.getParameter("bnum"));
		String file = "";
		String rfile = "";
		File bfile = multi.getFile("boardFile");
		if(bfile != null) {
/*			String ext = bfile.getName().substring(bfile.getName().lastIndexOf(".") + 1);
			if(ext.equals("jpg") || ext.equals("png") || ext.equals("gif") || ext.equals("jpeg")) {*/
			file = multi.getOriginalFileName("boardFile");
			rfile = bfile.getName();
/*			}*/
		}
		
		System.out.println(multi.getParameter("bnum"));
		System.out.println(multi.getParameter("boardTitle"));
		System.out.println(multi.getParameter("boardContent"));
		System.out.println(multi.getParameter("boardFile"));
		
		bdao.update(title, content, file, rfile, bnum);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./BoardContent.bo?bnum="+bnum);
		return forward;
	}
}




