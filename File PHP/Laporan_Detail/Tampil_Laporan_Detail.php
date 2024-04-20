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
$sql= "SELECT tb_detail_laporan.id, tb_pesanan_antrian.tanggal,tb_user.nama_lengkap,tb_Produk.nama_produk, tb_bahan_produksi.nama_bahan,tb_plat.nama_plat,tb_pesanan_antrian.quantity, tb_bahan_produksi.lebar_bahan,tb_bahan_produksi.tinggi_bahan, tb_Produk.lebar_cetak,tb_Produk.tinggi_cetak,tb_pesanan_antrian.banyak_bahan,tb_bahan_produksi.satuan,tb_pesanan_antrian.total_harga, tb_pesanan_antrian.penanggung_jawab, tb_pesanan_antrian.status FROM tb_pesanan_antrian, tb_Produk, tb_bahan_produksi, tb_plat,  tb_user, tb_detail_laporan WHERE tb_detail_laporan.id_pesanan = tb_pesanan_antrian.id_pesanan AND tb_pesanan_antrian.id_produk=tb_Produk.id_produk AND tb_Produk.id_bahan=tb_bahan_produksi.id_bahan AND tb_Produk.id_plat=tb_plat.id_plat AND tb_pesanan_antrian.id_user=tb_user.id_user ORDER BY tb_detail_laporan.id ";

        $query = mysqli_query($conn, $sql);
        $data = array();
        $n = 0;
  foreach ($query as $query) {
 $data[$n]['id'] = $query['id'];  
 $data[$n]['tanggal'] = $query['tanggal']; 
  $data[$n]['nama_lengkap'] = $query['nama_lengkap']; 
 $data[$n]['nama_produk'] =$query['nama_produk'];
  $data[$n]['nama_bahan'] = $query['nama_bahan'];
  $data[$n]['nama_plat'] = $query['nama_plat'];
  $data[$n]['quantity'] = $query['quantity'];
 $data[$n]['lebar_bahan'] = $query['lebar_bahan'];
 $data[$n]['tinggi_bahan'] = $query['tinggi_bahan'];
  $data[$n]['lebar_cetak'] = $query['lebar_cetak'];
   $data[$n]['tinggi_cetak'] = $query['tinggi_cetak'];
  $data[$n]['banyak_bahan'] =$query['banyak_bahan'];
  $data[$n]['satuan'] = $query['satuan'];
 $data[$n]['total_harga'] = $query['total_harga'];
  $data[$n]['penanggung_jawab'] = $query['penanggung_jawab'];
  $data[$n]['status'] = $query['status'];
        $n++;      
 
 }
 $list['Hasil'] = $data;
        echo json_encode($list);
?>
