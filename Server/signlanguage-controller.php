<?php
header('Content-Type: text/html; charset=utf-8');
  $connect=mysqli_connect("localhost", "potter", "muggles" ,"handypotter");

  if(mysqli_connect_errno()){
    die( "SQL server can not connect to SQL server.".mysqli_connect_errno());
  }

  $connect->set_charset('utf8');

    mysqli_query("set names utf8");

    session_start();

    $q1 = "SELECT SL_ID, SL_WORD, Theme from SignLanguage";
    $result = mysqli_query($connect, $q1);


  $total_record = mysqli_num_rows($result);
  echo "{\"results\":[";

  for ($i=0; $i < $total_record; $i++)
  {
      // ������ ���ڵ��� ��ġ(������) �̵�
      mysqli_data_seek($result, $i);

      $row = mysqli_fetch_array($result);
      echo "{\"SL_ID\":\"$row[0]\",\"SL_WORD\":\"$row[1]\",\"Theme\":\"$row[2]\"}";

      if($i<$total_record-1){
          echo ",";
      }
  }

  echo "]}";
  mysqli_free_result($result);
  mysqli_close($connect);
?>
