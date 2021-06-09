//package board;
//
//import java.io.File;
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
//
//import board.db.BoardDAO;
//import board.db.BoardBean;
//
//public class BoardUpdateServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		MultipartRequest multi = null;
//		int fileMaxSize = 10 * 1024 * 1024;
//		String savePath = request.getRealPath("/upload").replaceAll("\\\\", "/");
//		try {
//			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
//		} catch(Exception e) {
//			request.getSession().setAttribute("messageType", "오류 메시지");
////			request.getSession().setAttribute("messageContent", "파일 크기는 10MB를 넘을 수 없습니다");
//			response.sendRedirect("index.jsp");
//			return;
//		}
//		String userID = multi.getParameter("userID");
//		HttpSession session = request.getSession();
//		if(!userID.equals((String) session.getAttribute("userID"))) {
//			session.setAttribute("messageType", "오류 메시지");
//			session.setAttribute("messageContent", "접근권한이 없습니다");
//			response.sendRedirect("index.jsp");
//			return;
//		}
//		String boardID = multi.getParameter("boardID");
//		if(boardID == null || boardID.equals("")) {
//			session.setAttribute("messageType", "오류 메시지");
//			session.setAttribute("messageContent", "접근권한이 없습니다");
//			response.sendRedirect("index.jsp");
//			return;
//		}
//		BoardDAO boardDAO = new BoardDAO();
//		BoardBean board = boardDAO.getBoard(boardID);
//		if(!userID.equals(board.getUserID())) {
//			session.setAttribute("messageType", "오류 메시지");
//			session.setAttribute("messageContent", "접근권한이 없습니다");
//			response.sendRedirect("index.jsp");
//			return;
//		}
//		String boardTitle = multi.getParameter("boardTitle");
//		String boardContent = multi.getParameter("boardContent");
//		if(boardTitle == null || boardTitle.equals("") || boardContent == null || boardContent.equals("")) {
//			session.setAttribute("messageType", "오류 메시지");
//			session.setAttribute("messageContent", "입력하지 않은 사항이 있습니다");
//			response.sendRedirect("boardWrite.jsp");
//			return;
//		}
//		String boardFile = "";
//		String boardRealFile = "";
//		File file = multi.getFile("boardFile");
//		if(file != null) {
//			boardFile = multi.getOriginalFileName("boardFile");
//			boardRealFile = file.getName();
//			String prev = boardDAO.getRealFile(boardID);
//			File prevFile = new File(savePath + "/" + prev);
//			if(prevFile.exists()) {
//				prevFile.delete();
//			}
//		} else {
//			boardFile = boardDAO.getFile(boardID);
//			boardRealFile = boardDAO.getRealFile(boardID);
//		}
//		boardDAO.update(boardID, boardTitle, boardContent, boardFile, boardRealFile);
//			session.setAttribute("messageType", "성공 메시지");
//			session.setAttribute("messageContent", "게시물 수정완료");
//			response.sendRedirect("boardView.jsp");
//			return;
//	}
//}
