<?php

 define('HOST','localhost');
 define('USER','id16866562_berkahandalas');
 define('PASS','Berkah5/6789');
 define('DB','id16866562_berkahmysql');

 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');


$nm_bhn         = $_POST['nama_bahan'];
$tg_bhn         = $_POST['tinggi_bahan'];
$lb_bhn         = $_POST['lebar_bahan'];
$hg_bhn         = $_POST['harga_bahan'];
$satuan         = $_POST['satuan'];

error_reporting(E_ALL ^ E_NOTICE);

 
 $sql = "UPDATE tb_bahan_produksi set nama_bahan='$_POST[nama_bahan]',tinggi_bahan='$_POST[tinggi_bahan]',lebar_bahan='$_POST[lebar_bahan]',harga_bahan='$_POST[harga_bahan]', satuan ='$_POST[satuan]' where id_bahan = '$_POST[id_bahan]'";

    
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