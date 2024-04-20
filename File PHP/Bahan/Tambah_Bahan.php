<?php

 define('HOST','localhost');
 define('USER','id16866562_berkahandalas');
 define('PASS','Berkah5/6789');
 define('DB','id16866562_berkahmysql');

 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

$bahan              = $_POST['id_bahan'];
$nm_bhn             = $_POST['nama_bahan'];
$tg_bhn             = $_POST['tinggi_bahan'];
$lb_bhn             = $_POST['lebar_bahan'];
$hg_bhn             = $_POST['harga_bahan'];
$satuan             = $_POST['satuan'];

error_reporting(E_ALL ^ E_NOTICE);

 
 $sql = "INSERT INTO tb_bahan_produksi(id_bahan, nama_bahan, tinggi_bahan, lebar_bahan, harga_bahan, satuan) values ('$bahan','$nm_bhn' , '$tg_bhn','$lb_bhn','$hg_bhn ','$satuan ') ";

    
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