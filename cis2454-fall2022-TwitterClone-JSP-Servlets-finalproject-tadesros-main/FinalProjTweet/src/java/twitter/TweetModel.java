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
public class tweetModel {    
        //Create a tweet
        public static void addTweet(Tweet tweet){
        try{
            Connection connection = DatabaseConnection.getConnection();                   
            
            String query = "insert into tweet ( user_id, text ) "
                    + " values ( ?, ? )";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            // indexing starts with 1, not usual
            statement.setInt(1, tweet.getUser_id());
            statement.setString(2, tweet.getText());
            
            statement.execute();
            
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
    }
        //Get all Tweets  
        public static ArrayList<Tweet> getTweets(){
                
        ArrayList<Tweet> tweets = new ArrayList<>();
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            Statement statement = connection.createStatement();
            
            ResultSet results = statement.executeQuery("select * from tweet");
            
            while ( results.next() ){
                int id = results.getInt("id");
                String text = results.getString("text");
                Timestamp timestamp = results.getTimestamp("timestamp");
                Integer user_id = results.getInt("user_id");
                int like_count = results.getInt("likeCount");
                
                Tweet tweet = new Tweet(id, text, timestamp, user_id, like_count);
                                
                tweets.add(tweet);
            }
            
            results.close();
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
        
        return tweets;
        
    }
        //Get specific users tweets
        public static ArrayList<Tweet> getUserTweets(int user_Id_for_Tweets){
                
        ArrayList<Tweet> tweets = new ArrayList<>();
        try{
            Connection connection = DatabaseConnection.getConnection();
           
             String query = "select * from tweet "
                    + " where user_id = ? ";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user_Id_for_Tweets);
            
            ResultSet results = statement.executeQuery();             
            
            while ( results.next() ){                
                int id = results.getInt("id");
                String text = results.getString("text");
                Timestamp timestamp = results.getTimestamp("timestamp");
                Integer user_id = results.getInt("user_id");
                int like_count = results.getInt("likeCount");
                
                Tweet tweet = new Tweet(id, text, timestamp, user_id, like_count);
                                
                tweets.add(tweet);
            }
            
            results.close();
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
        
        return tweets;
        
    }             
        //Get tweets that I folllow
        public static ArrayList<Tweet> getTweetsYouFollow(int user_Id_for_Tweets){
                
        ArrayList<Tweet> tweets = new ArrayList<>();
        try{
            Connection connection = DatabaseConnection.getConnection();

             String query = "select * from tweet "
                    + " inner join following on following.following_user_id = tweet.user_id"
                    + " where following.followed_by_user_id = ?";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user_Id_for_Tweets);
            
            ResultSet results = statement.executeQuery(); 
            
            
            while ( results.next() ){
                
                int id = results.getInt("id");
                String text = results.getString("text");
                Timestamp timestamp = results.getTimestamp("timestamp");
                Integer user_id = results.getInt("user_id");
                int like_count = results.getInt("likeCount");
                
                Tweet tweet = new Tweet(id, text, timestamp, user_id, like_count);
                                
                tweets.add(tweet);
            }
            
            results.close();
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
        
        return tweets;
        
    }          
        //Like a tweet
        public static void likeTweet(Tweet tweet){
        try{            
            
            Connection connection = DatabaseConnection.getConnection();
                        
            String query = "update tweet set likeCount = ? "
                    + " where id = ? ";
           
            PreparedStatement statement = connection.prepareStatement(query);
            
            // indexing starts with 1, not usual
            statement.setInt(1, tweet.getLike_count()); 
            statement.setInt(2, tweet.getId());       
            
            statement.execute();
            
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
    }
        //Return a single tweet
        public static Tweet getTweet(Integer tweetId){
            
        Tweet tweet = null;
        try{
            
            Connection connection = DatabaseConnection.getConnection();
            
             String query = "select likeCount, text, user_id from tweet "
                    + " where id = ? ";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, tweetId);
            
            ResultSet results = statement.executeQuery();
    
            
       //  Tweet(int id, String text, Timestamp timestamp, int user_id, int like_count)   
       //     public Tweet(int id, String text, int user_id, int like_count)
            
            
            while ( results.next() ){
                int id = tweetId;
                String text = results.getString("text");
                Integer user_id = results.getInt("user_id");
                Integer like_count = results.getInt("likeCount");
                
                tweet = new Tweet(id,text,user_id,like_count);
             }
     
            results.close();
            statement.close();
            connection.close();

        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
        
        return tweet;
    }        
}
