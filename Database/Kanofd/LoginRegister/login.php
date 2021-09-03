<?php
include "config.php";

$response = array();
if (isset($_POST['email']) && isset($_POST['password'])){
    $email = $_POST['email'];
    $post_password = $_POST['password'];
    $stmt = $conn->prepare("SELECT*FROM users WHERE email = $email && password = $password");
    $stmt->bind_param("s",$email);
	$stmt->execute();
	$stmt->bind_result($username, $db_password);
	$stmt->fetch();
	if(password_verify($post_password, $db_password)){
		$response['error'] = false;
		$response['message'] = "Login Successful!";
		$response['email'] = $email;
		$response['password'] = $password;
	} else{
		$response['error'] = false;
		$response['message'] = "Invalid Email or Password";
	}
} else {
	$response['error'] = true;
	$response['message'] = "Insufficient Parameters";
}
echo json_encode($response);
?>
