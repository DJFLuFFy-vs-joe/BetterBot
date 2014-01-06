<?php
$dbhost = 'localhost:3036';
$dbuser = 'user';
$dbpass = 'password';
$conn = mysql_connect($dbhost, $dbuser, $dbpass);
if(! $conn )
{
  die('Could not connect: ' . mysql_error());
}
$sql = 'SELECT * FROM kicked';
$column = array();

mysql_select_db('db');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not get data: ' . mysql_error());
}
while($row = mysql_fetch_array($retval, MYSQL_ASSOC))
{
    echo "Key :{$row['key']}  <br> ".
         "Naam Speler : {$row['playername']} <br> ".
         "Server : {$row['server']} <br> ".
		 "Datum : {$row['date']} <br> ".
         "--------------------------------<br>";
} 
//echo "Fetched data successfully\n";
mysql_close($conn);
?>