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
        echo '<div class="pattern-tricks">';
          foreach($tricks['content'] as $trick)
            showTrick($trick);
        echo '</div>';
      echo '</form>';
    } else {
      showEmptyTrickMessage();
    }
  }
?>