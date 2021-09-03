<?php
require_once 'config.php';

$sql = "SELECT * FROM stores";

$result = mysqli_query($conn,$sql);
$users = array();

while($row = mysqli_fetch_array($result)){
    $index['name'] = $row['name'];
    $index['jam_buka'] = $row['jam_buka'];
    $index['jam_tutup'] = $row['jam_tutup'];
    $index['alamat'] = $row['alamat'];
    $index['deskripsi'] = $row['deskripsi'];

    array_push($users, $index);
}
echo json_encode($users);

?>