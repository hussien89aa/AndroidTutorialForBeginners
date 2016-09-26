<?php
 // 1- connect to db
$host="127.0.0.1";
$user="root";
$password="12345";
$database="admins";
$connect=  mysqli_connect($host, $user, $password, $database);
if(mysqli_connect_errno())
{ die("cannot connect to database field:". mysqli_connect_error());   }
 // define quesry 
$query="select * from logins";  // $usename=$_GET['username'];
$result=  mysqli_query($connect, $query);
if(! $result)
{ die("Error in query");}
 //get data from database
//$output=array();
while($row=  mysqli_fetch_assoc($result))
{
 $output[]=$row;  //$row['id']
}
print(json_encode($output));// this will print the output in json
// 4 clear
mysqli_free_result($result);
//5- close connection
mysqli_close($connect);
?>