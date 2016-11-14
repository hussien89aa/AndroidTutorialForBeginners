<?php
 // 1- connect to db
require("DBInfo.php");
 // define quesry 
//print($_GET['tweet_picture'] );
$query="insert into tweets(user_id,tweet_text,tweet_picture) values (" . $_GET['user_id']. ",'"  . $_GET['tweet_text'] . "','".  $_GET['tweet_picture'] .  "')";  // $usename=$_GET['username'];
$result=  mysqli_query($connect, $query);
if(! $result)
{$output ="{'msg':'fail'}";
}
else {
$output ="{'msg':'tweet is added'}";
}
 
print( $output);// this will print the output in json
 
//5- close connection
mysqli_close($connect);
?>