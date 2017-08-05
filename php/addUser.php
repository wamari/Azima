<?php
 if($_SERVER['REQUEST_METHOD']=='POST'){

 //Getting values
 $fullname = $_POST['fullname'];
 $email = $_POST['email'];
 $idno = $_POST['idno'];
 $telno = $_POST['telno'];
 $imei = $_POST['imei'];
 $loan_limit = $_POST['loan_limit'];

 //Creating an sql query
 $sql = "INSERT INTO tblusers (fullname, email, idno, telno, imei, loan_limit) VALUES ('$fullname','$email','$idno', '$telno', '$imei','$loan_limit')";

  //Importing our db connection script
 require_once('dbConnect.php');

 //Executing query to database
 if(mysqli_query($con,$sql)){
   echo 'Account created Successfully';
 }else{
   echo 'Could not create Account';
 }

 //Closing the database
 mysqli_close($con);
 }
