<?php

 define('HOST','localhost');
 define('USER','id16866562_berkahandalas');
 define('PASS','Berkah5/6789');
 define('DB','id16866562_berkahmysql');

 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');


$nm_prdk        = $_POST['nama_produk'];
$tg_prdk        = $_POST['tinggi_cetak'];
$lb_prdk        = $_POST['lebar_cetak'];
$bhn            = $_POST['id_bahan'];
$plat           = $_POST['id_plat'];

error_reporting(E_ALL ^ E_NOTICE);

 
 $sql = "UPDATE tb_Produk set nama_produk='$_POST[nama_produk]',tinggi_cetak='$_POST[tinggi_cetak]',lebar_cetak='$_POST[lebar_cetak]',id_bahan='$_POST[id_bahan]', id_plat ='$_POST[id_plat]' where id_produk = '$_POST[id_produk]'";

    
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