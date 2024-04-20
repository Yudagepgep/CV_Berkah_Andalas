<?php 
 
 define('DB_HOST', 'localhost');
 define('DB_USER', 'id16866562_berkahandalas');
 define('DB_PASS', 'Berkah5/6789');
 define('DB_NAME', 'id16866562_berkahmysql');
 
 $conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
 
 if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $name = $_POST['nama_lengkap'];
    $instan = $_POST['instansi_organisasi'];
    $email = $_POST['email_user'];
    $password = $_POST['password_user'];
    $handphone = $_POST['no_handphone'];
    
    $password = password_hash($password, PASSWORD_DEFAULT);
    
    $sql = "INSERT INTO tb_user (nama_lengkap,instansi_organisasi, email_user, password_user,no_handphone) VALUES ('$name','$instan', '$email', '$password','$handphone')";

    if ( mysqli_query($conn, $sql) ) {
        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        mysqli_close($conn);

    } else {

        $result["success"] = "0";
        $result["message"] = "error";

        echo json_encode($result);
        mysqli_close($conn);
    }
}

?>