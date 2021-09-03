<?php
include "config.php";

$response = array();
if($_POST['name'] && $_POST['no_hp'] &&  $_POST['gender'] && $_POST['tanggal_lahir'] && $_POST['email'] && $_POST['password']){
    $username = $_POST['name'];
    $nomor = $_POST['no_hp'];
    $gender = $_POST['gender'];
    $tgl = $_POST['tanggal_lahir'];
	$email = $_POST['email'];
	$password = password_hash($_POST['password'], PASSWORD_DEFAULT);
	$sql = $conn->prepare("SELECT * FROM users WHERE email = ?");
	$sql->bind_param("s",$email);
	$sql->execute();
	$sql->store_result();
 
	if($sql->num_rows > 0){
		$response['error'] = false;
		$response['message'] = "User already registered";
	} else{
		$stmt = $conn->prepare("INSERT INTO `users` (`name`, `no_hp`, `gender`, `tanggal_lahir`, `email`, `password`) VALUES(?,?,?)");
		$stmt->bind_param("sss", $username, $nomor, $gender, $tgl, $email, $password);
		$result = $stmt->execute();
		if($result){
			$response['error'] = false;
			$response['message'] = "User Registered Successfully";
		} else {
			$response['error'] = false;
			$response['message'] = "Cannot complete user registration";
		}
	}
} else{
	$response['error'] = true;
	$response['message'] = "Insufficient Parameters";
}
echo json_encode($response);	
?>
