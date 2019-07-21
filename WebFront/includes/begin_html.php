<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <!-- IMPORT CSS FILES -->

    <link href="style/font.css" rel="stylesheet" type="text/css">
    <link href="style/constants.css" rel="stylesheet" type="text/css">
    <link href="style/main_patern.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="res/scripts/animations.js" type="text/javascript"></script>

    <!---------------------->
    <title><?php echo $__APP_NAME ?></title>
  </head>
  <body>
    <header>
      <form action="" method="POST">
        <span class="left">
          <button formaction="index.php" type="submit">Accueil</button>
          <?php if (isset($_SESSION['PROG_VAR__USER'])) : ?>
            <button formaction="tricks.php" type="submit">Mes tricks</button>
          <?php endif ?>
        </span>
        <span class="right">
          <?php if (isset($_SESSION['PROG_VAR__USER'])) : ?>
            <span><?php echo $_SESSION['PROG_VAR__USER']->getLogin() ?></span>
            <button formaction="" type="submit" name="Logout">Se déconnecter</button>
          <?php else : ?>
            <button formaction="login.php" type="submit">Se connecter</button>
          <?php endif ?>
        </span>
        <p><?php echo $__APP_NAME ?></p>
      </form>
    </header>