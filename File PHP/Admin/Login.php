<?php 
 
 define('DB_HOST', 'localhost');
 define('DB_USER', 'id16866562_berkahandalas');
 define('DB_PASS', 'Berkah5/6789');
 define('DB_NAME', 'id16866562_berkahmysql');
 
 $conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
 

if ($_SERVER['REQUEST_METHOD']=='POST') {

    $email = $_POST['email'];
    $password = $_POST['password'];
    
    $sql = "SELECT * FROM tb_admin WHERE email='$email' ";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['login'] = array();
    
    if ( mysqli_num_rows($response) === 1 ) {
        
        $row = mysqli_fetch_assoc($response);

        if ( password_verify($password, $row['password']) ) {
            
          
            $index['email'] = $row['email'];
        

            array_push($result['login'], $index);

            $result['success'] = "1";
            $result['message'] = "success";
            echo json_encode($result);

            mysqli_close($conn);

        } else {

            $result['success'] = "0";
            $result['message'] = "error";
            echo json_encode($result);

            mysqli_close($conn);

        }

    }

}
 
?>
 