/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package twitter;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class createTweetCtrl extends HttpServlet {

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
        
        
         /*
          Run the appropriate action        
          If no action then tweet form loads
          Runs on page load
        */
        if (action == null) {
            action = "listTweets";
        }
    
       
        
        //List Users Tweets
        if (action.equalsIgnoreCase("listTweets")) {
                //w sessions
                HttpSession session = request.getSession();
                //Get username and id from session
                String username = (String) session.getAttribute("username");
                Integer user_id = (Integer) session.getAttribute("id");
                        
            //Ask model for the data
            ArrayList<Tweet> tweets= tweetModel.getUserTweets(user_id);
            //Add to the request
            request.setAttribute("tweets", tweets);
            //This is where we are going JSP page
            String url = "/createTweet.jsp";
            //Send to view to be displayed
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }  
            
        
        //Get tweet           
        String tweetText = request.getParameter("tweetText");
       

       //Create a Tweet
       if (action.equalsIgnoreCase("createTweet")) {            
       
            //Check data validation
            if (tweetText == null || tweetText == "") {
                
                String error = "The Tweet is empty!";
                request.setAttribute("error", error);
                String url = "/error2.jsp";
               
                //Send to view to be displayed
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

            try {              
                //Get sessions
                HttpSession session = request.getSession();
                //Get username and id from session
                String username = (String) session.getAttribute("username");
                Integer user_id = (Integer) session.getAttribute("id");
                
                
                //Make a tweet don't care about id make zero it will be autopopulated
                 Tweet tweetObject = new Tweet(0, tweetText, user_id);
   
              
                tweetModel.addTweet(tweetObject);
                
                
                //Bounce them back to our page to list users out.
                response.sendRedirect("createTweetCtrl");

            } catch (Exception ex) {
  //             exceptionPage(ex, request, response);
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
