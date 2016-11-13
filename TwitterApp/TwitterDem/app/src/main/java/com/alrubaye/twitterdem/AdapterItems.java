package com.alrubaye.twitterdem;

/**
 * Created by hussienalrubaye on 11/13/16.
 */

public class AdapterItems {
    public   String tweet_id;
    public  String tweet_text;
    public  String tweet_picture;
    public  String tweet_date;
    public  String user_id;
    public  String first_name;
    public  String picture_path;
    //for news details
    AdapterItems(  String tweet_id,  String tweet_text,String tweet_picture,
                   String tweet_date,String user_id,String first_name ,String picture_path)
    {
        this. tweet_id=tweet_id;
        this. tweet_text=tweet_text;
        this. tweet_picture=tweet_picture;
        this. user_id=user_id;
        this. first_name=first_name;
        this. picture_path=picture_path;
        this.tweet_date=tweet_date;
    }
}
