<?php

 define('HOST','localhost');
 define('USER','id16866562_berkahandalas');
 define('PASS','Berkah5/6789');
 define('DB','id16866562_berkahmysql');

 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
 
 $pesanan    = $_POST['id_user'] ;
$produk     = $_POST['id_produk'] ;
$tgl        = $_POST['tanggal'];
$quan       = $_POST['quantity'];
$byk_bhn    = $_POST['banyak_bahan'];
$lb         = $_POST['laba'];
$jwb        = $_POST['penanggung_jawab'];
$sts        = $_POST['status'];
error_reporting(E_ALL ^ E_NOTICE);

 if(isset($_POST['quantity']) && (float)$_POST['quantity'] != 0){

$a=mysqli_query($con, "SELECT tb_bahan_produksi.harga_bahan FROM tb_Produk, tb_bahan_produksi  WHERE tb_Produk.id_bahan=tb_bahan_produksi.id_bahan AND tb_Produk.id_produk='$_POST[id_produk]'");
$b = mysqli_fetch_array($a);
$c=mysqli_query($con, "SELECT tb_plat.harga_plat FROM tb_Produk, tb_plat  WHERE tb_Produk.id_plat=tb_plat.id_plat AND tb_Produk.id_produk='$_POST[id_produk]'");
$d = mysqli_fetch_array($c);
$e=mysqli_query($con, "SELECT tb_plat.upah_cetak FROM tb_Produk, tb_plat  WHERE tb_Produk.id_plat=tb_plat.id_plat AND tb_Produk.id_produk='$_POST[id_produk]'");
$f = mysqli_fetch_array($e);


$tot_hrg = ((( $quan / $byk_bhn)* $b['harga_bahan'])  + $d['harga_plat'] + $f['upah_cetak'] + $lb);
}
 
 $sql = "UPDATE tb_pesanan_antrian set id_user='$_POST[id_user]' ,id_produk='$_POST[id_produk]',tanggal='$_POST[tanggal]',quantity='$_POST[quantity]',banyak_bahan='$_POST[banyak_bahan]',laba='$_POST[laba]',total_harga='$tot_hrg ', penanggung_jawab='$_POST[penanggung_jawab]', status ='$_POST[status]' where id_pesanan='$_POST[id_pesanan]'";

    
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