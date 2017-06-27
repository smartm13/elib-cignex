package controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import controller.DAO.login;

/**
 * Servlet implementation class r_get
 */
@WebServlet("/r_get")
public class r_get extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	

	private studentDTO studentDTO;
	
	@Resource(name="jdbc/library_system")
	private DataSource dataSource;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			studentDTO = new studentDTO(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
    public r_get() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String email=request.getParameter("em");
		String pass=request.getParameter("pw");
		String fname=request.getParameter("fn");
		
		
		login login1 = new login(0, fname, email,pass);
		try {
			studentDTO.addStudent(login1);
			System.out.println("Success");
			
			
			
		} catch (Exception e) {
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
