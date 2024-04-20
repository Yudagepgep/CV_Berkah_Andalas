<?php

 define('HOST','localhost');
 define('USER','id16866562_berkahandalas');
 define('PASS','Berkah5/6789');
 define('DB','id16866562_berkahmysql');

 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
 
 
$nama       = $_POST['nama_user'] ;
$tgl        = $_POST['tanggal'] ;
$byk        = $_POST['banyak_pesanan'];
$jenis      = $_POST['jenis_pesanan'];
$ket        = $_POST['keterangan'];
error_reporting(E_ALL ^ E_NOTICE);




 
                
                
$sql = "INSERT INTO tb_pemesanan_costumer (  nama_user,  tanggal,  banyak_pesanan,  jenis_pesanan, keterangan)
        VALUES( '$nama','$tgl','$byk','$jenis ','$ket')";
  $query = mysqli_query($con,$sql);
                
        
        if ($query) {
  
            $response['result'] = true;
            $response['Status'] = "Data tersimpan";
            echo json_encode($response);
 
        } else {
            $response['result']= false ;
            $response['Status']="Ada kesalahan";
            echo json_encode($response);
        }
				


 ?>