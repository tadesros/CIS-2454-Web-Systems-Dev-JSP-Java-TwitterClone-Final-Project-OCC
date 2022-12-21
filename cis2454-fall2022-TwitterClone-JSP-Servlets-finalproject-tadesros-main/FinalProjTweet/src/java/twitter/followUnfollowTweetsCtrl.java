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
import static twitter.twitterCtrl.getSHA;
import static twitter.twitterCtrl.toHexString;

/**
 * @author TomD
 */
public class followUnfollowTweetsCtrl extends HttpServlet {

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
        
        
            //Get Action from the form
            //Action dictates what will run below
            String action = request.getParameter("action");          
            
       
           HttpSession session = request.getSession();
            
            //Get username and id from logged in user              
             Integer user_id = (Integer) session.getAttribute("id");
            
            
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
            ArrayList<Follow> follows = followingModel.getFollowsForUser(user_id);
            request.setAttribute("follows",follows);  
            
            
            String url = "/followUnfollowTweets.jsp";
            //Send to view to be displayed
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } 
            
        //Check action from viewUserTweet
        //Follow User
        else if (action.equalsIgnoreCase("followUser")) {            
            
            String user_id_to_follow = request.getParameter("user_id");         

            //Check data validation
            if (user_id_to_follow== null || user_id_to_follow == "") {
                
                String error = "User Id to follow is missing";
                request.setAttribute("error", error);
                String url = "/error3.jsp";
               
                //Send to view to be displayed
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            try {
                //Make a follow Object id = 0 we don't care about that. 
                // int id, Integer followed_by_user_id, Integer following_user_id
                Follow follow = new Follow(0, user_id, Integer.parseInt(user_id_to_follow));

                //Call function to add follow to the database.
                followingModel.followUser(follow);

                //Bounce them back to our page to list users out.
                response.sendRedirect("followUnfollowTweetsCtrl");

            } catch (Exception ex) {
   //             exceptionPage(ex, request, response);
            }
        } //Unfollow
        else if (action.equalsIgnoreCase("unfollowUser")) {
            
            
            String record_id = request.getParameter("record_id");
            
            //Check data validation
            if (record_id == null || record_id == "") {
                
                String error = "Follow record id is missing";
                request.setAttribute("error", error);
                String url = "/error3.jsp";

                //Send to view to be displayed
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

            try {
               
                //Make a user don't care about id make zero it will be autopopulated
                Follow follow = new Follow(Integer.parseInt(record_id),0,0);

                followingModel.unfollow(follow);

                //Bounce them back to our page to list users out.
                response.sendRedirect("followUnfollowTweetsCtrl");

            } catch (Exception ex) {
           
            //    exceptionPage(ex, request, response);
      
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
