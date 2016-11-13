drop database twitter;
create database twitter;
use twitter;

create table login(
	user_id  int AUTO_INCREMENT PRIMARY KEY,
	first_name varchar(50),
	email varchar(50),
	password varchar(50),
	picture_path varchar(350)
	);
describe login;
 
create table following(
		user_id  int ,
		following_user_id int,
		FOREIGN KEY (user_id) REFERENCES login(user_id),
		FOREIGN KEY (following_user_id) REFERENCES login(user_id)
	);
describe following;

create table tweets(
	tweet_id  int AUTO_INCREMENT PRIMARY KEY,
	user_id  int ,
	tweet_text varchar(50),
	tweet_picture varchar(350),
	tweet_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES login(user_id)
	);
describe tweets;

CREATE VIEW user_tweets AS
SELECT tweets.tweet_id ,tweets.tweet_text,tweets.tweet_picture,
tweets.tweet_date,tweets.user_id,login.first_name,login.picture_path
FROM tweets
inner join login
on tweets.user_id =login.user_id;
describe user_tweets;
 