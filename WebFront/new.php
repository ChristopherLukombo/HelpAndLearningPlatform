<?php
  require_once("includes/common_process.php");

  if (isset($_POST['Create'])) {
    $API->newTrick($_POST['wording'], $_POST['description'], $_POST['content'], $_POST['categoryId'], $_SESSION['PROG_VAR__USER']->getId(), $_SESSION['PROG_VAR__USER']->getToken());
    header("Location: tricks.php");
  } elseif (isset($_POST['Back'])) {
    header("Location: tricks.php");
  }


  require_once("includes/begin_html.php");
?>

<form action="" method="POST">
  <button type="submit" name="Back" formnovalidate>Retour</button>
  <button type="submit" name="Create">Enregistrer</button>
  <hr>
  <input type="text" name="wording" placeholder="Titre du Trick" required>
  <select name="categoryId" required>
    <?php getCategoryList($API) ?>
  </select>
  <textarea name="description" rows="8" cols="80" placeholder="Description" required></textarea>
  <textarea name="content" rows="8" cols="80" placeholder="Contenu" required></textarea>
</form>



<?php
  require_once("includes/end_html.php");

  function getCategoryList($API) {
    $categories = $API->categories($_SESSION['PROG_VAR__USER']->getToken());
    var_dump($categories);

    foreach ($categories as $category)
      echo '<option value="'.$category['id'].'">'.$category['wording'].'</option>';
  }
?>