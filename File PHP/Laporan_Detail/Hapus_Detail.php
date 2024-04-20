<?php

 define('HOST','localhost');
 define('USER','id16866562_berkahandalas');
 define('PASS','Berkah5/6789');
 define('DB','id16866562_berkahmysql');

 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');


    $sql = "DELETE FROM tb_detail_laporan WHERE id='$_POST[id]'";
 
    $result = mysqli_query($con,$sql);


 if($result){
         echo "Data Terhapus";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($con);

 
     
 ?>