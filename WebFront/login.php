<?php
  require_once("includes/common_process.php");

  if (isset($_POST['Connection'])) {
    try {
      $user = new User($_POST['Login'], $_POST['Password']);

      $_SESSION['PROG_VAR__USER'] = $user;
	  header("Location: tricks.php");
	  
    } catch (\Exception $e) {
      echo 'erreur de connexion';
    }
  }

  require_once("includes/begin_html.php");
?>

<h1>Connexion</h1>

<form action="" method="POST">
  <p>Login :</p>
  <input type="text" name="Login" required>
  <p>Mot de passe :</p>
  <input type="password" name="Password" required>
  <button type="submit" name="Connection">Connexion</button>
  <button type="submit" formaction="register.php" formnovalidate>S'inscrire</button>
</form>

<?php
  require_once("includes/end_html.php");
?>