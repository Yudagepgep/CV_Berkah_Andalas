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
$sql= "SELECT * FROM tb_bahan_produksi ";

        $query = mysqli_query($conn, $sql);
        $data = array();
        $n = 0;
  foreach ($query as $query) {
      
    
    $data[$n]['id_bahan']           = $query['id_bahan'];
    $data[$n]['nama_bahan']         = $query['nama_bahan'];
    $data[$n]['tinggi_bahan']       = $query['tinggi_bahan'];
    $data[$n]['lebar_bahan']        = $query['lebar_bahan'];
    $data[$n]['harga_bahan']        = $query['harga_bahan'];
    $data[$n]['satuan']             = $query['satuan'];
    
        $n++;      
 
 }
 $list['Hasil'] = $data;
        echo json_encode($list);
?>
