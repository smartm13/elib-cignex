package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import controller.studentDTO;
import controller.DAO.login;

/**
 * Servlet implementation class get
 */
@WebServlet("/loginController")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
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
    public loginController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String email=request.getParameter("em");
		String pass=request.getParameter("pw");
		
		PrintWriter out = response.getWriter();
		HttpSession userSession=request.getSession();
		userSession.setAttribute("loggedUser", email);	
		
		login login1;
		
		try {
			
			login1 = studentDTO.getUser(email, pass);
			if(login1 == null )
			{
				out.println("Your email 0R password is wrong ");
			}
			else
			{
				out.print("/Dashboard");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		
		
	}

}
