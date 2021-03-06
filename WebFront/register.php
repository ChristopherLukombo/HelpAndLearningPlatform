<?php
  require_once("includes/common_process.php");

  if (isset($_POST['Register'])) {
    $API->register($_POST['FirstName'], $_POST['LastName'], $_POST['Email'], $_POST['Login'], $_POST['Password']);
	header("Location: login.php");
  }

  require_once("includes/begin_html.php");
?>

<div class="login">
  <img src="res/img/trips_logo.png" alt="logo">
  <div class="separator"></div>
  <form action="" method="POST">
    <p>Prénom</p>
    <input type="text" name="FirstName" required>
    <p>Nom</p>
    <input type="text" name="LastName" required>
    <p>Adresse mail</p>
    <input type="text" name="Email" required>
    <p>Login</p>
    <input type="text" name="Login" required>
    <p>Mot de passe</p>
    <input type="password" name="Password" required>
    <div class="separator"></div>
    <button type="submit" name="Register">S'inscrire</button>
  </form>
</div>

<?php
  require_once("includes/end_html.php");
?>