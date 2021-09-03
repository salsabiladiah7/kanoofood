<?php
require_once 'config.php';

$sql = "SELECT * FROM users";

$result = mysqli_query($conn,$sql);
$users = array();

while($row = mysqli_fetch_array($result)){
    $index['name'] = $row['name'];
    $index['level'] = $row['level'];
    $index['gender'] = $row['name'];
    $index['tanggal_lahir'] = $row['tanggal_lahir'];
    $index['no_hp'] = $row['no_hp'];
    $index['email'] = $row['email'];

    array_push($users, $index);
}
echo json_encode($users);

?>