<?php
  require_once("includes/common_process.php");

  require_once("includes/begin_html.php");
?>
<h1><?php echo $__APP_NAME ?></h1>
<div>
  <h2>Application de trucs et astuces</h2>
  <p>Cette application vous permet de poster aux autres utilisateurs des astuces sur tout et n'importe quoi !</p>
  <p>La communauté Trips sera ravie de lire vos fabuleux tips'n'tricks de la vie de tous les jours ah ah ah ! Mais attentions petit sacripant, le respect entre les utilisateurs est de mise ! Pour que tout le monde puisse poster dans la joie et la bonne humeur :D</p>
</div>

<?php if (isset($_SESSION['PROG_VAR__USER'])) showTricks($API) ?>

<?php
  require_once("includes/end_html.php");
?>