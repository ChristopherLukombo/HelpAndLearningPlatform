<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <!-- IMPORT CSS FILES -->

    <link href="style/constants.css" rel="stylesheet" type="text/css">
    <link href="style/main_patern.css" rel="stylesheet" type="text/css">

    <!---------------------->
    <title><?php echo $__APP_NAME ?></title>
  </head>
  <header>
    <form action="" method="POST">
      <button formaction="home.php" type="submit">Accueil</button>
      <?php if (isset($_SESSION['PROG_VAR__USER'])) : ?>
        <span><?php echo $_SESSION['PROG_VAR__USER']->getLogin() ?></span>
        <button formaction="" type="submit" name="Logout">Déconnexion</button>
      <?php else : ?>
        <button formaction="login.php" type="submit">Connexion</button>
      <?php endif ?>
    </form>
  </header>
  <hr>
  <body>