
package twitter;

import java.sql.Timestamp;


public class Tweet {    
    private int id;
    private String text;
    private Timestamp timestamp;
    private int user_id;
    private int like_count;
    
    //Constuctor
    public Tweet(int id, String text, int user_id) {
        this(id,text,null,user_id,0);
    }  
    
   //Constuctor
    public Tweet(int id, String text, int user_id, int like_count) {
        this(id,text,null,user_id,like_count);
    } 
    
    
        
    public Tweet(int id, String text, Timestamp timestamp, int user_id, int like_count) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.user_id = user_id;
        this.like_count = like_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }
           
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getUser_id() {
        return user_id;
    }
}
