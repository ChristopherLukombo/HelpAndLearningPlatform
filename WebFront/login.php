<?php
  require_once("includes/common_process.php");

  if (isset($_POST['Connection'])) {
    try {
      $user = new User($_POST['Login'], $_POST['Password']);

      $_SESSION['PROG_VAR__USER'] = $user;
    } catch (\Exception $e) {
      echo 'erreur de connexion';
    }
  }

  require_once("includes/begin_html.php");
?>


<form action="" method="POST">
  <p>Login :</p>
  <input type="text" name="Login" required>
  <p>Mot de passe :</p>
  <input type="password" name="Password" required>
  <button type="submit" name="Connection">Connexion</button>
</form>

<?php
  require_once("includes/end_html.php");
?>