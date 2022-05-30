<?php
			$host="localhost";
			$uname="root";
			$pass="";
			$dbname="omada 18";
			$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
			$dbh->set_charset("utf8");

			mysqli_select_db($dbh, $dbname);
			
			$sql = "select * from league";
	
			$result = mysqli_query($dbh, $sql);
			$teams = array();
			
			while ($row = $result->fetch_assoc()) {
				 $field1name = $row["Team"];
				 
				 array_push($teams, $field1name);
			}
			
			if (count($teams)%2!=0){
				echo "The number of teams is odd. Removing last team.";
				unset($teams[count($teams)-1]);
			}
	
$allMatches = create_round_robin_tournament($teams);
$t = 0;
for($i=0; $i<count($allMatches);$i=$i+2){	

	echo $allMatches[$i] ."-". $allMatches[$i+1];

	echo "<br>";
}


function create_round_robin_tournament($teams)
{
$sik= array();
for($round = 0 ; $round < count($teams)-1 ; ++$round)
{

for($i = 0 ; $i < count($teams)/2 ; ++$i)
{
$opponent = count($teams) - 1 - $i ;


array_push($sik,$teams[$i],$teams[$opponent]);

}

$teams = rotateCompetitors($teams);
}
return $sik;
}


// rotate all competitors but the first one
function rotateCompetitors($teams)
{
$result = $teams ;

$tmp = $result[ count($result) - 1 ] ;
for($i = count($result)-1 ; $i > 1 ; --$i)
{
$result[$i] = $result[$i-1] ;
}
$result[1] = $tmp ;

return $result ;

}


$conn = new mysqli($host, $uname, $pass, $dbname);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

for($i=0; $i<count($allMatches);$i=$i+2){
$omada1 = $allMatches[$i];
$omada2 = $allMatches[$i+1];
$sql = "INSERT INTO matches (HOME,AWAY)
VALUES ('$omada1','$omada2')";
if ($conn->query($sql) === TRUE) {
  echo "New record created successfully";
} else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}


}
$conn->close();
?>