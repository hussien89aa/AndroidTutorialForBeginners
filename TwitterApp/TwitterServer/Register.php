<?php
 // 1- connect to db
$host="127.0.0.1";
$user="root";
$password="12345";
$database="twitter";
$connect=  mysqli_connect($host, $user, $password, $database);
if(mysqli_connect_errno())
{ die("cannot connect to database field:". mysqli_connect_error());   }
 // define quesry 
$query="insert into login(first_name,email,password,picture_path) values ('" . $_GET['first_name']. "','"  . $_GET['email'] . "','"  . $_GET['password'] . "','"  . $_GET['picture_path'] . "')";  // $usename=$_GET['username'];
$result=  mysqli_query($connect, $query);
if(! $result)
{$output ="{'msg':'fail'}";
}
else {
$output ="{'msg':'user is added'}";
}
 
print( $output);// this will print the output in json
 
//5- close connection
mysqli_close($connect);
?>