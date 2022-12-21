/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package twitter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static twitter.twitterCtrl.getSHA;
import static twitter.twitterCtrl.toHexString;


/**
 *
 * @author TomD
 */
public class loginCtrl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        
        //Get action from the form
        //Action dictates what will run below
        String action = request.getParameter("action");
        
         //Login 
         if(action == null)
         {             
            //This is where we are going JSP page
            String url = "/login.jsp";
            //Send to view to be displayed
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
         else if (action.equalsIgnoreCase("login")) {
            
            //Get parameters
            String username = request.getParameter("username");
            String password = request.getParameter("password");           
            
            //Check data validation
            if (username == null || password == null || username == "" || password == "") {
                
                String error = "username or password is missing";
                request.setAttribute("error", error);
                String url = "/error.jsp";
                //Send to view to be displayed
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

            try {
                //https://www.geeksforgeeks.org/sah-256-hash-in-java/
                String hashedPassword = toHexString(getSHA(password));
                       
                //Create a user object don't care about id set to zero
                User user = new User(0,username,hashedPassword);
                
                //Try login
                if(userModel.login(user))
                {
                   //If you have logged in set a session attribute
                   //Session connection to the server
                   HttpSession session = request.getSession();                   
                   //Use username as session attribute 
                   //Presence of attribute means logged in ok
                   
                    //Ask model for the data
                    User usersNew = userModel.getUser(username);   
                   
                   
                   
                     
                     session.setAttribute("id", usersNew.getId());
                     session.setAttribute("username", usersNew.getUsername()); 
                   
                   
                   
                    
                   //If logged in redirect
                //   response.sendRedirect("Twitter");
                   response.sendRedirect("mainMenuCtrl");
                  
                }
                else{
                   String error = "Invalid username or password";
                   
                   //Set that they were logged in ok.                   
                   request.setAttribute("error", error);
                   String url = "/error.jsp";        
                   //Send to view to be displayed
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                }

            } catch (Exception ex) {           
                exceptionPage(ex, request, response);      
            }
        }       
       //Register ACCOUNT 
        else if (action.equalsIgnoreCase("register")) {
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            //Check data validation
          if (username == null || password == null || username == "" || password == "")  {
                String error = "username or password missing";
                request.setAttribute("error", error);
                
                String url = "/error.jsp";

                //Send to view to be displayed
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            //If not empty or null go here
            try {
                //https://www.geeksforgeeks.org/sah-256-hash-in-java/
                String hashedPassword = toHexString(getSHA(password));

                //Make a user don't care about id make zero it will be autopopulated
                User user = new User(0, username, hashedPassword);
                //Add a user ot the user model
                userModel.addUser(user);
                
                  //Session connection to the server
                   HttpSession session = request.getSession();
                   //Use username as session attribute 
                   //Presence of attribute means logged in ok
                   session.setAttribute("username", username);
                
                //Bounce them back to our page to list users out.
                response.sendRedirect("mainMenuCtrl");

            } catch (Exception ex) {
                exceptionPage(ex, request, response);
            }
        }
         
    }
    
    private void exceptionPage(Exception ex, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = ex.toString();
        request.setAttribute("error", error);
        String url = "/error.jsp";
        
        //Send to view to be displayed
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    public static boolean ensureUserIsLoggedIn(HttpServletRequest request)
    {
        //Get Session and session attribute username
        HttpSession session = request.getSession();       
        String sessionUserName = (String)session.getAttribute("username");
        //Not Null is true
        return sessionUserName != null;       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
