<?php
header('Content-Type: text/html; charset=utf-8');
  $connect=mysqli_connect("localhost", "potter", "muggles" ,"handypotter");

  if(mysqli_connect_errno()){
    die( "SQL server can not connect to SQL server.".mysqli_connect_errno());
  }

  $connect->set_charset('utf-8');

    mysqli_query("set names utf8");

    session_start();

    $q1 = "SELECT SL_ID, SL_WORD, Theme from SignLanguage";
    $result = mysqli_query($connect, $q1);

    $response = array();


  while($row = mysqli_fetch_array($result)){

    array_push($response, array("SL_ID"=>$row[0], "SL_WORD"=>$row[1], "Theme"=>$row[2]));

  }

  echo json_encode(array("response"=>$response));

  mysqli_close($connect);
?>
