<?php
  require_once("includes/common_process.php");

  require_once("includes/begin_html.php");
?>

<form action="new.php" method="POST">
  <button type="submit" name="Add">Nouveau</button>
</form>

<h1>Mes tricks</h1>

<?php showTricks($API) ?>

<?php
  require_once("includes/end_html.php");

  function showTricks($API) {
    $tricks = $API->tricksByUser($_SESSION['PROG_VAR__USER']->getToken(), $_SESSION['PROG_VAR__USER']->getId());

    if (is_array($tricks)) {
      echo '<form action="edit.php" method="GET">';
      foreach($tricks['content'] as $trick)
        showTrick($trick);
      echo '</form>';
    } else {
      echo '<p>Aucun trick pour le moment</p>';
    }
  }

  function showTrick($trick) {
    echo '<div class="trick">';
      echo '<button name="id", value="'.$trick['id'].'">Éditer</button>';
      echo '<h1>'.$trick['wording'].'</h1>';
      echo '<p>'.$trick['description'].'</p>';
      echo '<p>'.$trick['categoryId'].'</p>';
      echo '<p>Créé le '.$trick['creationDate'].'</p>';
    echo '</div>';
  }
?>