<?php 
 
 define('DB_HOST', 'localhost');
 define('DB_USER', 'id16866562_berkahandalas');
 define('DB_PASS', 'Berkah5/6789');
 define('DB_NAME', 'id16866562_berkahmysql');
 
 $conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
 

 if (mysqli_connect_errno()) {
 echo "Failed to connect to MySQL: " . mysqli_connect_error();
 die();
 }
 
 
 //create query
$sql= "SELECT tb_Produk.id_produk,tb_bahan_produksi.tinggi_bahan,tb_bahan_produksi.lebar_bahan, tb_Produk.tinggi_cetak, tb_Produk.lebar_cetak FROM tb_Produk,tb_bahan_produksi where tb_Produk.id_bahan=tb_bahan_produksi.id_bahan order by tb_Produk.id_produk  ";

        $query = mysqli_query($conn, $sql);
        $data = array();
        $n = 0;
  foreach ($query as $query) {
      
    
    $data[$n]['id_produk']          = $query['id_produk'];
    $data[$n]['tinggi_bahan']       = $query['tinggi_bahan'];
    $data[$n]['lebar_bahan']        = $query['lebar_bahan'];
    $data[$n]['tinggi_cetak']       = $query['tinggi_cetak'];
    $data[$n]['lebar_cetak']        = $query['lebar_cetak'];
    
        $n++;      
 
 }
 $list['Hasil'] = $data;
        echo json_encode($list);
?>
