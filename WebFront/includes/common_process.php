<?php
  require_once("includes/dependencies.php");

  // Initialisation du fuseau horraire:
	date_default_timezone_set('Europe/Paris');

  // Initialisation de la session si inexistante:
  if (session_status() == PHP_SESSION_NONE)
    session_start();

  // Création du service API :
  $API = new ServiceAPI();

  if (isset($_POST['Logout'])) {
    unset($_SESSION['PROG_VAR__USER']);
    header("Location: home.php");
  }
?>