package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.DAO.Books;
import controller.BooksDTO;
import controller.publisherDTO;
/**
 * Servlet implementation class BookInfoController
 */
@WebServlet("/BookInfo")
public class BookInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("bid")!=null){
			Books myb=new Books();
			try{myb.setBid(Integer.parseInt(request.getParameter("bid")));}
			catch (NumberFormatException e){response.getWriter().append("Invalid book Id given.");return;}
			try {
				List<?> fbooks=(new BooksDTO()).searchMatching(null,myb);
				if(fbooks.size()==0)response.getWriter().append("No book found for Id given.");
				else {
					myb=(Books) fbooks.get(0);
					request.setAttribute("book", myb);
					request.setAttribute("pub_name", (new publisherDTO()).getPublisher( Integer.toString(myb.getPid()) ).getName() );
					request.getRequestDispatcher("bookInfo.jsp").forward(request, response);
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		else response.getWriter().append("No book Id given.");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
