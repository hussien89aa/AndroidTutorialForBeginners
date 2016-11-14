<?php
 // 1- connect to db
require("DBInfo.php");
 
 // define quesry 
$query="select * from login where email= '". $_GET['email'] ."' and password= '". $_GET['password']."'";  // $usename=$_GET['username'];
$result=  mysqli_query($connect, $query);
if(! $result)
{ die("Error in query");}
 //get data from database
 $output=array();
while($row=  mysqli_fetch_assoc($result))
{
 $output[]=$row;  //$row['id']
 break;
}
 if ($output) {
print( "{'msg':'Pass Login'". ",'info':'". json_encode($output) ."'}");// this will print the output in json
 
 }
 else{
 	print("{'msg':' cannot login'}");
 }

// 4 clear
mysqli_free_result($result);
//5- close connection
mysqli_close($connect);
?>