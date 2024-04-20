<?php

 define('HOST','localhost');
 define('USER','id16866562_berkahandalas');
 define('PASS','Berkah5/6789');
 define('DB','id16866562_berkahmysql');

 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

$plat               = $_POST['id_plat'];
$nm_plat            = $_POST['nama_plat'];
$hg_plt             = $_POST['harga_plat'];
$up_ctk             = $_POST['upah_cetak'];

error_reporting(E_ALL ^ E_NOTICE);

 
 $sql = "INSERT INTO tb_plat(id_plat, nama_plat,  harga_plat, upah_cetak) values ('$plat','$nm_plat' , '$hg_plt ','$up_ctk ') ";

    
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