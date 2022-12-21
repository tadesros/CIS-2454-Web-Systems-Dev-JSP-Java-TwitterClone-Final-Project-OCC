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
public class homePageCtrl extends HttpServlet {

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
        
        //Display My Name and Profile Image
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        User user = userModel.getUser(username);
        Integer user_id = (Integer) session.getAttribute("id");        

        //Check Action
        /*
         Run the appropriate action
         Always list the users first
        */
        if (action == null) {
            action = "listTweetsFollowed";
        }
        //List Users
        if (action.equalsIgnoreCase("listTweetsFollowed")) {
            
        //Get the Tweets I follow                       
            //Ask model for the data
            ArrayList<Tweet> tweets= tweetModel.getTweetsYouFollow(user_id);
            //Add to the request
            request.setAttribute("tweets", tweets);                
        //This is where we are going JSP page
          String url = "/homePage.jsp";                  
          request.setAttribute("filename", user.getFilename());
          getServletContext().getRequestDispatcher(url).forward(request, response);
        }   
        else if (action.equalsIgnoreCase("likeTweet")) {
            
            String tweetId= request.getParameter("tweetId");
            
            //Check data validation
            if (tweetId == null || tweetId == "") {
                String error = "Id for Tweet Id is missing";
                request.setAttribute("error", error);
                String url = "/error5.jsp";

                //Send to view to be displayed
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            try {
                  String tweetText = "Test";                
                 //Make a tweet don't care about id make zero it will be autopopulated
                 Tweet tweetObject = null;
                 
                 //Get current like count
                 tweetObject = tweetModel.getTweet(Integer.parseInt(tweetId));
                 
                 //Get Current Like Count
                 Integer currLikeCount = tweetObject.getLike_count();
                 
                 //Increment Like Count
                 Integer updatedLikeCount = currLikeCount + 1;                 
                 
                 //Write to the database to update the like count
                 tweetObject.setLike_count(updatedLikeCount);
                 
                 tweetModel.likeTweet(tweetObject);                      

                //Bounce them back to our control page.
                response.sendRedirect("homePageCtrl");

            } catch (Exception ex) {
//               exceptionPage(ex, request, response);
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
