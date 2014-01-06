<?php

foreach ($_POST as $key => $value) {
    switch ($key) {
        case 'firstKey':
            $firstKey = $value;
            break;
        case 'secondKey':
            $secondKey = $value;
            break;
		case 'playername';
			$playername = $value;
			break;
		case 'server';
			$server = $value;
			break;
        default:
            break;
    }
}
$dbhost = 'localhost:3036';
$dbuser = 'user';
$dbpass = 'password';
$conn = mysql_connect($dbhost, $dbuser, $dbpass);
if(! $conn )
{
  die('Could not connect: ' . mysql_error());
}
$sql = " INSERT INTO kicked (playername,server,date) VALUES ( '$playername', '$server', now() ) ";
mysql_select_db('db');
$retval = mysql_query( $sql, $conn );
if(! $retval)
{
die('Could not enter data: ' . mysql_error());
}
//echo "Test enzo!";
mysql_close($conn);
?>