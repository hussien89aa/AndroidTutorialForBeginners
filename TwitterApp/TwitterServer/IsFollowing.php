<?php
 // 1- connect to db
require("DBInfo.php");

 $query="select * from following where user_id=" . $_GET['user_id']. 
 " and following_user_id="  . $_GET['following_user_id'] ;  // $usename=$_GET['username'];
 
$result=  mysqli_query($connect, $query);
if(! $result)
{ die("Error in query");}
 //get data from database
$output=array();
while($row=  mysqli_fetch_assoc($result))
{
 $output[]=$row;  //$row['id']
}
 
 if ($output) {
print("{'msg':'is subscriber'}");

}
else {
print("{'msg':'is not subscriber'}");
}
 
//5- close connection
mysqli_close($connect);
?>