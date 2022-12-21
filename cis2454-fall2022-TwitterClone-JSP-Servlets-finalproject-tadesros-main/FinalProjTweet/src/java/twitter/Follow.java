/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package twitter;

import java.sql.Timestamp;

/**
 *
 * @author TomD
 */
public class Follow {
    
    
    private int id;
    private Integer followed_by_user_id;   //Person who is following ME i want to follow following_user_id
    private Integer following_user_id;

    public Follow(int id, Integer followed_by_user_id, Integer following_user_id) {
        this.id = id;
        this.followed_by_user_id = followed_by_user_id;
        this.following_user_id = following_user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFollowed_by_user_id() {
        return followed_by_user_id;
    }

    public void setFollowed_by_user_id(Integer followed_by_user_id) {
        this.followed_by_user_id = followed_by_user_id;
    }

    public Integer getFollowing_user_id() {
        return following_user_id;
    }

    public void setFollowing_user_id(Integer following_user_id) {
        this.following_user_id = following_user_id;
    }

  
    
    
}
