package webapp.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webapp.beans.UserBeans;
import webapp.model.UserModel;
import webapp.utility.DataUtility;
import webapp.utility.ServletUtility;
/**
 * Servlet implementation class RegistrationCTL
 */
@WebServlet(name = "RegistrationCTL", urlPatterns = {"/RegistrationCTL"})
public class RegistrationCTL extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationCTL() {
        super();
        // TODO Auto-generated constructor stub
    }
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    response.getWriter().append("Served at: ").append(request.getContextPath());
    //ServletUtility.forward("jsp/registration.jsp", request, response);
    request.getRequestDispatcher("jsp/registration.jsp").forward(request, response);
    //response.sendRedirect("jsp/registration.jsp");
  }
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    
    UserBeans user = new UserBeans();
    user.setFirstName(request.getParameter("firstName"));
    user.setLastName(request.getParameter("lastName"));
    user.setLogin(request.getParameter("login"));
    user.setPassword(request.getParameter("password"));
    user.setDob(DataUtility.getDate(request.getParameter("dob")));
    user.setMobileNo(request.getParameter("mobile"));
    
    long i = UserModel.addUser(user);
    if(i>0) {
      ServletUtility.setSuccessMessage("User register sucessfully", request);
      
    }else {
      ServletUtility.setErrorMessage("Not insterted", request);
    }
    request.getRequestDispatcher("jsp/registration.jsp").forward(request, response);
  }
}