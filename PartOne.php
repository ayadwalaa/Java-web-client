<?php
$ip="127.0.0.1";
print (" <br> -------------------- Networks II - SNMP HW - WALA AYAD -- PART ONE  -------------------------- <br>");
print("<br>-------System Groups -----------<br>");
echo "</br>";
// snmp2_walk() function is used to read all the values from an SNMP agent specified by the hostname .


echo "1 System Description :" .snmp2_get("127.0.0.1", "SNMP", ".1.3.6.1.2.1.1.1.0")."<br>";
echo "2 Object ID :". snmp2_get("127.0.0.1", "SNMP", ".1.3.6.1.2.1.1.2.0")."<br>";
echo "3 Up Time :". snmp2_get("127.0.0.1", "SNMP", ".1.3.6.1.2.1.1.3.0")."<br>";
echo "4 Contact :". snmp2_get("127.0.0.1", "SNMP", ".1.3.6.1.2.1.1.4.0")."<br>";
echo "5 Name :". snmp2_get("127.0.0.1", "SNMP", ".1.3.6.1.2.1.1.5.0")."<br>";
echo "6 Loaction :". snmp2_get("127.0.0.1", "SNMP", ".1.3.6.1.2.1.1.6.0")."<br>";
echo "7 Service:". snmp2_get("127.0.0.1", "SNMP", ".1.3.6.1.2.1.1.7.0")."<br>";

print ("<br> -------------------- PART A IS DONE, LET'S SEE PART B ----------- <br>");

print("<br>-------(ARP) table-----------<br>");
echo "</br>";
$a = snmp2_walk($ip, "SNMP", ".1.3.6.1.2.1.4.22.1.2");
$b = snmp2_walk($ip, "SNMP", ".1.3.6.1.2.1.4.22.1.3");
$c = snmp2_walk($ip, "SNMP", ".1.3.6.1.2.1.4.22.1.4");
$i=0;
echo"<table>";
echo "<tr> <td > Index </td><td> Mac </td> <td > IP </td><td> type </td>  </tr>";
foreach ($a as $k=>$val){
	echo "<tr> <td> $i </td><td> $a[$i] </td><td> $b[$i] </td><td> $c[$i] </td>  </tr>";
	$i++;
}
echo"</table>";


print("<br>-------Interface table-----------<br>");
echo "</br>";
$x = snmp2_walk($ip, "SNMP",".1.3.6.1.2.1.4.20.1.1");
$y = snmp2_walk($ip, "SNMP", ".1.3.6.1.2.1.4.20.1.2");
$w = snmp2_walk($ip, "SNMP", ".1.3.6.1.2.1.4.20.1.3");
$q = snmp2_walk($ip, "SNMP", ".1.3.6.1.2.1.4.20.1.4");
$t = snmp2_walk($ip, "SNMP", ".1.3.6.1.2.1.4.20.1.5");
$i =0;
echo"<table>";
foreach ($t as $k=>$val) {
	//ipAdEntAddr , ipAdEntIfIndex , ipAdEntNetMask , ipAdEntBcastAddr , ipAdEntReasmMaxSize
	echo "<tr> <td> $i </td><td> $x[$i] </td><td> $y[$i] </td><td> $w[$i] </td><td> $q[$i] </td><td> $t[$i] </td></tr>";
	$i++;
}
echo"</table>";


print("<br>-------Routing Table-----------<br>");
echo "</br>";
$z = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.4.21.1.1");
$v = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.4.21.1.2");
$a = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.4.21.1.3");
$w = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.4.21.1.4");
$r = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.4.21.1.5");
$p = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.4.21.1.6");
$l = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.4.21.1.7");
$k = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.4.21.1.8");
$m = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.4.21.1.9");
$n = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.4.21.1.10");
$c = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.4.21.1.11");
$g = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.4.21.1.12");
$s = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.4.21.1.13");
$i =0;
echo"<table>";
foreach ($z as $k=>$val) {
	//ipAdEntAddr , ipAdEntIfIndex , ipAdEntNetMask , ipAdEntBcastAddr , ipAdEntReasmMaxSize
	echo "<tr> <td> $i </td><td> $z[$i] </td><td> $v[$i] </td><td> $a[$i] </td><td> $w[$i] </td><td> $r[$i] </td>
<td> $p[$i] </td><td> $l[$i] </td><td> $k[$i] </td><td> $m[$i] </td><td> $n[$i] </td><td> $c[$i] </td><td> $g[$i] </td><td> $s[$i] </td></tr>";
	$i++;
}
echo"</table>";

print (" <br> -------------------- PART C LADIES AND GENTLEMEN!  -------------------------- <br>");


print("<br>-------TCP Group-----------<br>");
$q = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.6.13.1.1");
$t = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.6.13.1.2");
$o = snmp2_walk("127.0.0.1", "SNMP", ".1.3.6.1.2.1.6.13.1.3");
$y = snmp2_walk("127.0.0.1", "SNMP", "1.3.6.1.2.1.6.13.1.4");
$e = snmp2_walk("127.0.0.1", "SNMP", "1.3.6.1.2.1.6.13.1.5");

$i =0;
echo"<table>";
foreach ($q as $k=>$val) {
	//ipAdEntAddr , ipAdEntIfIndex , ipAdEntNetMask , ipAdEntBcastAddr , ipAdEntReasmMaxSize
	echo "<tr> <td> $i </td><td> $q[$i] </td><td> $t[$i] </td><td> $o[$i] </td><td> $y[$i] </td><td> $e[$i] </td></tr>";
	$i++;
}
echo"</table>";
?>
