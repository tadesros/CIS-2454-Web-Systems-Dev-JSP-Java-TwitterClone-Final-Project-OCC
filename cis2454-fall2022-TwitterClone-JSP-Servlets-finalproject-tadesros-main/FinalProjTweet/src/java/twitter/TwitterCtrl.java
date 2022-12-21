//This is our controller 
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

//Servlet for Twitter
public class twitterCtrl extends HttpServlet {

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

        if(!loginCtrl.ensureUserIsLoggedIn(request))
        {            
            request.setAttribute("message","You must login");
            //Go back to login page
            response.sendRedirect("Login");
            return;            
        }       
        
        /*
          Run the appropriate action        
          If no action then list users
          Runs on page load
        */
        if (action == null) {
            action = "listUsers";
        }
        //List Users
        if (action.equalsIgnoreCase("listUsers")) {
            //Ask model for the data
            ArrayList<User> users = userModel.getUsers();
            //Add to the request
            request.setAttribute("users", users);
            //This is where we are going JSP page
            String url = "/users.jsp";
            //Send to view to be displayed
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } 
       //Add User 
        else if (action.equalsIgnoreCase("createUser")) {
            
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            //Check data validation
            if (username == null || password == null || username == "" || password == "") {
                
                String error = "username or password missing";
                request.setAttribute("error", error);
                String url = "/error1.jsp";
               
                //Send to view to be displayed
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

            try {
                //https://www.geeksforgeeks.org/sah-256-hash-in-java/
                String hashedPassword = toHexString(getSHA(password));

                //Make a user don't care about id make zero it will be autopopulated
                User user = new User(0, username, hashedPassword);

                userModel.addUser(user);

                //Bounce them back to our page to list users out.
                response.sendRedirect("Twitter");

            } catch (Exception ex) {
                exceptionPage(ex, request, response);
            }
        } //Update User
        else if (action.equalsIgnoreCase("updateUser")) {
            
            String id = request.getParameter("id");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            //Check data validation
            if (id == null || username == null || password == null) {
                String error = "id username or password missing";
                request.setAttribute("error", error);
                String url = "/error1.jsp";

                //Send to view to be displayed
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

            try {
                //https://www.geeksforgeeks.org/sah-256-hash-in-java/
                String hashedPassword = toHexString(getSHA(password));

                //Make a user don't care about id make zero it will be autopopulated
                User user = new User(Integer.parseInt(id), username, hashedPassword);

                userModel.updateUser(user);

                //Bounce them back to our page to list users out.
                response.sendRedirect("Twitter");

            } catch (Exception ex) {
               exceptionPage(ex, request, response);
            }
        }//Delete User
        else if (action.equalsIgnoreCase("deleteUser")) {
            
            
            String id = request.getParameter("id");
            
            //Check data validation
            if (id == null) {
                
                String error = "id is missing";
                request.setAttribute("error", error);
                String url = "/error1.jsp";

                //Send to view to be displayed
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

            try {
               
                //Make a user don't care about id make zero it will be autopopulated
                User user = new User(Integer.parseInt(id), "","");

                userModel.deleteUser(user);

                //Bounce them back to our page to list users out.
                response.sendRedirect("Twitter");

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

    //Hash Start
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
    //End Hash    

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
