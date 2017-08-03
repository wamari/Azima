<?php 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 //Getting values
 $fullname = $_POST['fullname'];
 $email = $_POST['email'];
 $idno = $_POST['idno'];
 $telno = $_POST['telno'];
 $imei = $_POST['imei'];
 
 //Creating an sql query
 $sql = "INSERT INTO tblusers (fullname, email, idno, telno, imei) VALUES ('$fullname','$email','$idno', '$telno', '$imei')";
 
 //Importing our db connection script
 require_once('dbConnect.php');
 
//do some validation before inserting data

 //Executing query to database
 if(mysqli_query($con,$sql)){
 echo 'Account created Successfully';
 }else{
 echo 'Could not create Account';
 }
 
 //Closing the database 
 mysqli_close($con);
 }