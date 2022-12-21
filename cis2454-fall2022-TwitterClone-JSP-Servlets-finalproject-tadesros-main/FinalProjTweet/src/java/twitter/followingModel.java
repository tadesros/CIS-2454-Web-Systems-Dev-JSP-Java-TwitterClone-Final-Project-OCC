/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package twitter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author TomD
 */
public class followingModel {    
    //Follow a User
     public static void followUser(Follow follow){
        try{
            Connection connection = DatabaseConnection.getConnection();
                   
            
            String query = "insert into following ( followed_by_user_id, following_user_id ) "
                    + " values ( ?, ? )";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            // indexing starts with 1, not usual
            statement.setInt(1, follow.getFollowed_by_user_id());
            statement.setInt(2, follow.getFollowing_user_id());
            
            statement.execute();
            
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
    }

    //Unfollow remove = Follow record
    public static void unfollow(Follow follow){
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            String query = "delete from following where id = ? ";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            // indexing starts with 1, why? it's ok to cry
            statement.setInt(1, follow.getId());
            
            statement.execute();
            
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
    }
       
    //Get list of follows current user is following
    public static ArrayList<Follow> getFollowsForUser(int user_id){
        ArrayList<Follow> follows = new ArrayList<>();
        try{
            Connection connection = DatabaseConnection.getConnection();      
            
            String query = "select id, following_user_id from following where followed_by_user_id = ?";
            
            PreparedStatement statement = connection.prepareStatement(query);            
            // indexing starts with 1, why? it's ok to cry
            statement.setInt(1, user_id);
            
            
            ResultSet results = statement.executeQuery();
                  
            while ( results.next() ){
                int id = results.getInt("id");
                int followingUserId = results.getInt("following_user_id");
            
                
                Follow follow = new Follow(id,user_id,followingUserId);
                
                follows.add(follow);
            }
            
            results.close();
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
        
        return follows;
        
    }   
}
