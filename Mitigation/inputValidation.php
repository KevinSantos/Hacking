

____________________________________________________________________

<?php
// Define these as constants so that they can’t be changed
DEFINE (‘DBUSER’, ‘mysqladm’);
DEFINE (‘DBPW’, ‘Turtle2Dove’);
DEFINE (‘DBHOST’, ‘localhost’);
DEFINE (‘DBNAME’, ‘hamdb’);

if ($dbc = mysql_connect (DBHOST, DBUSER, DBPW)) {

	if (!mysql_select_db (DBNAME)) { // If it can’t select the database.

		trigger_error(“Could not select the database!<br />”);

		exit();

	}

} else {

	trigger_error(“Could not connect to MySQL!<br /> “);

	exit();

}


function escape_data ($data) {

if (function_exists(‘mysql_real_escape_string’)) {
	global $dbc; // Need the connection.
	$data = mysql_real_escape_string (trim($data), $dbc);
	$data = strip_tags($data);
} else {
	$data = mysql_escape_string (trim($data));
	$data = strip_tags($data);
}
	return $data;
}

?>

____________________________________________________________________

Regular Expression Code

<?php

require_once(“./includes/confighamdb.php”);

if (isset($_POST[‘submitted’])) { // Handle the form.

// Check for a first name.
// Unquote a quoted string with stripslashes

if (preg_match (‘%^[A-Za-z\.\’ \-]{2,15}$%’, stripslashes(trim($_POST[‘first_name’])))) {

	$fn = escape_data($_POST[‘first_name’]);

} else {

	$fn = FALSE;

	echo ‘<p><font color=”red” size=”+1″>Please enter your first name!</font></p>’;

}

// Check for a last name.

if (preg_match (‘%^[A-Za-z\.\’ \-]{2,30}$%’, stripslashes(trim($_POST[‘last_name’])))) {

	$ln = escape_data($_POST[‘last_name’]);

} else {

	$ln = FALSE;

	echo ‘<p><font color=”red” size=”+1″>Please enter your last name!</font></p>’;

}

// Check for an email address.

if (preg_match (‘%^[A-Za-z0-9._\%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$%’, stripslashes(trim($_POST[’email’])))) {

	$e = escape_data($_POST[’email’]);

} else {

	$e = FALSE;

	echo ‘<p><font color=”red” size=”+1″>Please enter a valid email address!</font></p>’;

}

// Check for a street.

if (preg_match (‘%^[A-Za-z0-9\.\’ \-]{5,30}$%’, stripslashes(trim($_POST[‘street’])))) {

	$s = escape_data($_POST[‘street’]);

} else {

	$s = FALSE;

	echo ‘<p><font color=”red” size=”+1″>Please enter your street address!</font></p>’;

}

// Check for a city.

if (preg_match (‘%^[A-Za-z\.\’ \-]{2,25}$%’, stripslashes(trim($_POST[‘city’])))) {

	$c = escape_data($_POST[‘city’]);

} else {

	$c = FALSE;

	echo ‘<p><font color=”red” size=”+1″>Please enter a valid city!</font></p>’;

}

// Check for a state.

if (preg_match (‘%^(A[KLRZ]|C[AOT]|D[CE]|FL|GA|HI|I[ADLN]|K[SY]|LA|M[ADEINOST]|N{CDEHJMVY]|O[HKR]|PA|RI|S[CD]|T[NX]|UT|V[AT]|W[AIVY])$%’, stripslashes(trim($_POST[‘state’])))) {

	$st = escape_data($_POST[‘state’]);

} else {

	$st = FALSE;

	echo ‘<p><font color=”red” size=”+1″>Please enter a valid state!</font></p>’;

}

// Check for a zip code.

if (preg_match (‘%^[0-9]{5}$%’, stripslashes(trim($_POST[‘zip’])))) {

	$z = escape_data($_POST[‘zip’]);

} else {

	$z = FALSE;

	echo ‘<p><font color=”red” size=”+1″>Please enter a valid 5 digit zip code!</font></p>’;

}

// Check for a phone number.

if (preg_match (‘%^([0-9]( |-)?)?(\(?[0-9]{3}\)?|[0-9]{3})( |-)?([0-9]{3}( |-)?[0-9]{4}|[a-zA-Z0-9]{7})$%’, stripslashes(trim($_POST[‘work_phone’])))) {

	$ph = escape_data($_POST[‘work_phone’]);

} else {

	$ph = FALSE;

	echo ‘<p><font color=”red” size=”+1″>Please enter a valid phone number!</font></p>’;

}

// Check for a password and match against the confirmed password.

if (preg_match (‘%^[A-za-z0-9]{4,20}$%’, stripslashes(trim($_POST[‘password1’])))) {

	if ($_POST[‘password1’] == $_POST[‘password2’]) {

		$p = escape_data($_POST[‘password1’]);

	} else {

		$p = FALSE;

		echo ‘<p><font color=”red” size=”+1″>Your password did not match the confirmed password!</font></p>’;

	}

} else {

	$p = FALSE;

	echo ‘<p><font color=”red” size=”+1″>Please enter a valid password!</font></p>’;

}