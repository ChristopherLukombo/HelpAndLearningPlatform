<?php
  require_once("includes/common_process.php");

  if (isset($_POST['Connection'])) {
    $user = $BDD->selectFromTable("user", array("*"), array("login" => $_POST['Login'], "password" => $_POST['Password']), array());
    if (count($user) == 1) {
      $_SESSION['USER'] = $user[0];
      header("Location: home.php");
    }
  }

  require_once("includes/begin_html.php");
?>


<form action="" method="POST">
  <p>Login :</p>
  <input type="text" name="Login">
  <p>Mot de passe :</p>
  <input type="password" name="Password">
  <button type="submit" name="Connection"></button>
</form>



<?php
  require_once("includes/end_html.php");
?>