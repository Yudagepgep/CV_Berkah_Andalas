<?php 

 
 define('DB_HOST', 'localhost');
 define('DB_USER', 'id16866562_berkahandalas');
 define('DB_PASS', 'Berkah5/6789');
 define('DB_NAME', 'id16866562_berkahmysql');
 
 $conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
            
$costumer  = $_GET['nama_costumer'];
 
$sql = "select * from tb_detail_laporan where nama_costumer like '%$costumer%'";
 
$res = mysqli_query($conn,$sql);
 
	$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,array('tanggal' =>  $row['1'],
'nama_costumer' =>  $row['2'],
'nama_produk'   => $row['3'],
'nama_bahan'   => $row['4'],
'nama_plat'   => $row['5'],
 'quantity' =>   $row['6'],
 'ukuran_bahan' =>   $row['7'],
 'ukuran_cetak' =>   $row['8'],
 'banyak_bahan' =>   $row['9'],
 'satuan' =>   $row['10'],
 'total_harga' => $row['11'],
 'status' => $row['13'],

'id'=>$row[0]

));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
	
?>