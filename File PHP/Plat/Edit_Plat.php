<?php

 define('HOST','localhost');
 define('USER','id16866562_berkahandalas');
 define('PASS','Berkah5/6789');
 define('DB','id16866562_berkahmysql');

 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');



$nm_plt         = $_POST['nama_plat'];
$hg_plat        = $_POST['harga_plat'];
$up_ctk         = $_POST['upah_cetak'];


error_reporting(E_ALL ^ E_NOTICE);

 
 $sql = "UPDATE tb_plat set nama_plat='$_POST[nama_plat]',harga_plat='$_POST[harga_plat]', upah_cetak ='$_POST[upah_cetak]' where id_plat = '$_POST[id_plat]'";

    
    $query = mysqli_query($con,$sql);
   
    
 
    if ($query) {
  
            $response['result'] = true;
            $response['Status'] = "Data terupdate";
            echo json_encode($response);
 
        } else {
            $response['result']= false ;
            $response['Status']="Ada kesalahan";
            echo json_encode($response);
        }

     
?>