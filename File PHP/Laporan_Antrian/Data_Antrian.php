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
$sql= "SELECT * FROM tb_pesanan_antrian ";

        $query = mysqli_query($conn, $sql);
        $data = array();
        $n = 0;
  foreach ($query as $query) {
      
    $data[$n]['id_pesanan']         = $query['id_pesanan'];  
    $data[$n]['id_user']            = $query['id_user']; 
    $data[$n]['id_produk']          = $query['id_produk'];
    $data[$n]['tanggal']            = $query['tanggal'];
    $data[$n]['quantity']           = $query['quantity'];
    $data[$n]['banyak_bahan']       = $query['banyak_bahan'];
    $data[$n]['laba']               = $query['laba'];
    $data[$n]['total_harga']        = $query['total_harga'];
    $data[$n]['penanggung_jawab']   = $query['penanggung_jawab'];
    $data[$n]['status']             = $query['status'];
        $n++;      
 
 }
 $list['Hasil'] = $data;
        echo json_encode($list);
?>
