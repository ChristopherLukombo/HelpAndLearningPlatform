<?php
  require_once("includes/common_process.php");

  if (isset($_POST['Create'])) {
    $response = $API->newTrick($_POST['wording'], $_POST['description'], $_POST['content'], $_POST['categoryId'], $_SESSION['PROG_VAR__USER']->getId(), $_SESSION['PROG_VAR__USER']->getToken());
    header("Location: tricks.php");
  } elseif (isset($_POST['Back'])) {
    header("Location: tricks.php");
  }


  require_once("includes/begin_html.php");
?>

<form action="" method="POST">
  <div class="tool-bar">
    <button type="submit" name="Back" formnovalidate>Retour</button>
    <button type="submit" name="Create">Enregistrer</button>
  </div>
  <div class="formulaire">
    <input type="text" minLength="10" maxlength="255" name="wording" placeholder="Titre du Trick" required>
    <select name="categoryId" required>
      <?php getCategoryList($API) ?>
    </select>
    <textarea name="description" minLength="10" maxlength="255" rows="8" placeholder="Description" required></textarea>
    <textarea name="content" minLength="10" rows="16" placeholder="Contenu" required></textarea>
  </div>
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