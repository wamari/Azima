<?php 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 //Getting values
 $fullname = $_POST['fullname'];
 $email = $_POST['email'];
 $idno = $_POST['idno'];
 $telno = $_POST['telno'];
 $imei = $_POST['imei'];

 $limit = 1000;
 
 //Creating an sql query
 $sql = "INSERT INTO tblusers (fullname, email, idno, telno, imei) VALUES ('$fullname','$email','$idno', '$telno', '$imei')";


 $stmt = "INSERT INTO tbllimits (limit, tblusers_id) VALUES ($limit, $tblusers_id)";
 
 //Importing our db connection script
 require_once('dbConnect.php');
 
 $tblusers_id = mysql_insert_id($con);
//do some validation before inserting data

 //Executing query to database
 if(mysqli_query($con,$sql)){
 echo 'Account created Successfully';
 	mysql_query($con, $stmt);
 }else{
 echo 'Could not create Account';
 }
 
 //Closing the database 
 mysqli_close($con);
 }