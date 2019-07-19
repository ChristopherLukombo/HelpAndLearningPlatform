<?php
  require_once("includes/common_process.php");

  require_once("includes/begin_html.php");
?>

<form action="new.php" method="POST">
  <button type="submit" name="Add">Nouveau</button>
</form>

<?php showTricks($API) ?>

<?php
  require_once("includes/end_html.php");

  function showTricks($API) {
    $tricks = $API->tricksByUser($_SESSION['PROG_VAR__USER']->getToken(), $_SESSION['PROG_VAR__USER']->getId());
    $categories = $API->categories($_SESSION[PROG_VAR__USER]->getToken());

    if (is_array($tricks)) {
      echo '<form action="edit.php" method="GET">';
        echo '<div class="pattern-tricks">';
          foreach($tricks['content'] as $trick) {
            $stats = $API->getStatsByTrickId($_SESSION[PROG_VAR__USER]->getToken(), $trick['id']);
            showTrick($trick, $categories, $stats);
          }
        echo '</div>';
      echo '</form>';
    } else {
      showEmptyTrickMessage();
    }
  }
?>