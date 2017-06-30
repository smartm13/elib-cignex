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
@WebServlet("/bookController")
public class bookController extends HttpServlet {
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
    public bookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			String control = request.getParameter("c");		

				
					if (control.equals("ADD")) {
						addBook(request, response);
					}
					if (control.equals("search")) {
						searchBook(request, response);
					}
					if (control.equals("edit")) {
						updateBook(request, response);
					}
					if (control.equals("delete")) {
						searchBook(request, response);
					}
					
				}
				catch (Exception exc) {
					throw new ServletException(exc);
			
			}
			
			
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	private void searchBook(HttpServletRequest request, HttpServletResponse response)throws Exception {
			
		String name = request.getParameter("search");
		Books book = new Books();
		
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
	private void addBook(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String name = request.getParameter("name1");
		String isbn = request.getParameter("isbn");
		String price= request.getParameter("price");
		String pid= request.getParameter("pid");
		int i1 = Integer.parseInt(isbn);
		int i2 = Integer.parseInt(price);
		int i3 = Integer.parseInt(pid);
		
		Books book = new Books();
		
		     book.setName(name);
			 book.setIsbn(i1);
			 book.setPrice(i2);
			 book.setPid(i3);
		PrintWriter out = response.getWriter();
		Connection conn = null;
		try {
			BooksDO.create(conn, book);
			out.println("Added Succesfully");
			}		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
private void updateBook(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String name = request.getParameter("name1");
		String isbn = request.getParameter("isbn");
		String price= request.getParameter("price");
		int i1 = Integer.parseInt(isbn);
		int i2 = Integer.parseInt(price);
		
		Books book = new Books();
		
		     book.setName(name);
			 book.setIsbn(i1);
			 book.setPrice(i2);
	
		PrintWriter out = response.getWriter();
		Connection conn = null;
		try {
			BooksDO.save(conn, book);
			out.println("Added Succesfully");
			}		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}
