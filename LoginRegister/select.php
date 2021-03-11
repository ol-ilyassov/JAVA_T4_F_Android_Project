<?php
require "DataBase.php";
$db = new DataBase();
if ($db->dbConnect()) {
    echo $db->select("users", $_POST['email']);
} else echo "Error: Database connection";
?>
