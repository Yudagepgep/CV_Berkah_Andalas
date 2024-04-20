<?php

 define('HOST','localhost');
 define('USER','id16866562_berkahandalas');
 define('PASS','Berkah5/6789');
 define('DB','id16866562_berkahmysql');

 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
 
$id_pesanan        = $_POST['id_pesanan'];


$sql = "INSERT INTO tb_detail_laporan (id_pesanan)
        VALUES( '$id_pesanan')";

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