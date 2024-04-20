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
$sql= "SELECT * FROM tb_pemesanan_costumer ";

        $query = mysqli_query($conn, $sql);
        $data = array();
        $n = 0;
  foreach ($query as $query) {
      
    $data[$n]['id_pemesanan']       = $query['id_pemesanan'];  
    $data[$n]['nama_user']          = $query['nama_user']; 
    $data[$n]['tanggal']            = $query['tanggal'];
    $data[$n]['banyak_pesanan']     = $query['banyak_pesanan'];
    $data[$n]['jenis_pesanan']      = $query['jenis_pesanan'];
    $data[$n]['keterangan']         = $query['keterangan'];
    
        $n++;      
 
 }
 $list['Hasil'] = $data;
        echo json_encode($list);
?>
