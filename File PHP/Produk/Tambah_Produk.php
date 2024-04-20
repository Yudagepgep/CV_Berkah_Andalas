<?php

 define('HOST','localhost');
 define('USER','id16866562_berkahandalas');
 define('PASS','Berkah5/6789');
 define('DB','id16866562_berkahmysql');

 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

$produk         = $_POST['id_produk'];
$nm_prdk        = $_POST['nama_produk'];
$tg_prdk        = $_POST['tinggi_cetak'];
$lb_prdk        = $_POST['lebar_cetak'];
$bhn            = $_POST['id_bahan'];
$plat           = $_POST['id_plat'];

error_reporting(E_ALL ^ E_NOTICE);

 
 $sql = "INSERT INTO tb_Produk(id_produk, nama_produk, tinggi_cetak, lebar_cetak, id_bahan, id_plat) values ('$produk','$nm_prdk' , '$tg_prdk','$lb_prdk','$bhn','$plat ') ";

    
    $query = mysqli_query($con,$sql);
   
    
 
    if ($query) {
  
            $response['result'] = true;
            $response['Status'] = "Berhasil DiTambahkan";
            echo json_encode($response);
 
        } else {
            $response['result']= false ;
            $response['Status']="Ada kesalahan";
            echo json_encode($response);
        }

     
?>