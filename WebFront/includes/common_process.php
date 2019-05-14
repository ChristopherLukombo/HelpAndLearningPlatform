<?php
  require_once("includes/dependencies.php");

  // Initialisation du fuseau horraire:
	date_default_timezone_set('Europe/Paris');

  // Initialisation de la session si inexistante:
  if (session_status() == PHP_SESSION_NONE)
    session_start();

	// Redirection des erreurs pour Try Catch:
	set_error_handler('exception_error_handler');

  $BDD = new ServiceBDD("localhost", "projet_annuel", "root", "root");

  if (isset($_POST['Logout'])) {
    unset($_SESSION['USER']);
  }
?>