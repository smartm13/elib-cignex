package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import controller.DAO.Books;

/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class searchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BooksDTO BooksDO;
	@Resource(name="jdbc/library_system")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			BooksDO = new BooksDTO(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String name = request.getParameter("search");
		System.out.println(name);
		Books book = new Books();
		
		System.out.println(request.getParameter("param"));
		int i1 ;
		if (name.equals("name")){
			String name1 = request.getParameter("param");
			book.setName(name1);
			System.out.println(book);
		}
		else if(name.equals("isbn")){
			String isbn = request.getParameter("param");
			 i1 = Integer.parseInt(isbn);
			 book.setIsbn(i1);	
		}
		PrintWriter out = response.getWriter();
	    List<?> A = new ArrayList<>();
		Connection conn = null;
		try {
			A = BooksDO.searchMatching(conn, book);
			for(int i =0 ;i<A.size();i++)
			{
				out.println(A.get(i));
			}
			}		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
