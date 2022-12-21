/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package twitter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TomD
 */
public class viewAUserTweetsCtrl extends HttpServlet {

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
            throws ServletException, IOException 
    {        
        response.setContentType("text/html;charset=UTF-8");
 
        
        
        //Get Action from the form
        //Action dictates what will run below
        String action = request.getParameter("action");
         
        HttpSession session = request.getSession();
            
        //Get username and id from logged in user              
    //    Integer user_id = (Integer) session.getAttribute("id");
            
            
        /*
         Run the appropriate action
         Always list the users first
        */
        if (action == null) {
            action = "listUsersAndTweets";
        }
        //List Users
        if (action.equalsIgnoreCase("listUsersAndTweets")) {
            
            //Ask model for the data
            ArrayList<User> users = userModel.getUsers();            
            //Add to the request
            request.setAttribute("users", users);           
            
            //Ask Model for Follows
        //    ArrayList<Follow> follows = followingModel.getFollowsForUser(user_id);
        //    request.setAttribute("follows",follows);  
            
            
            String url = "/viewUserTweets.jsp";
            //Send to view to be displayed
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }        
        //List selected users tweets
        else if (action.equalsIgnoreCase("listTweets")) {
  
            //Get User ID from submission
            String userToSeeTweetId = request.getParameter("user_id");            
           
            //Check data validation
            if (userToSeeTweetId == null || userToSeeTweetId == "") {
                
                String error = "Missing value for Id";
                request.setAttribute("error", error);
                String url = "/error4.jsp";
               
                //Send to view to be displayed
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            try
            {                
                
            //Ask model for the data
            ArrayList<Tweet> tweets= tweetModel.getUserTweets(Integer.parseInt(userToSeeTweetId));
            //Add to the request
            request.setAttribute("tweets", tweets);           
            
            //Ask model for the data
            ArrayList<User> users = userModel.getUsers();            
            //Add to the request
            request.setAttribute("users", users);    
            
            
            //This is where we are going JSP page
            String url = "/viewUserTweets.jsp";
            
            
            
            //Send to view to be displayed
            getServletContext().getRequestDispatcher(url).forward(request, response);
            
            
            
            }catch(Exception ex){
               // exceptionPage(ex, request,response);
            }
            
        }  
        
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
