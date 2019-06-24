<?php
  require_once("includes/common_process.php");

  $element = null;

  if (isset($_GET['id'])) {
    $element = $API->getTrickById($_SESSION['PROG_VAR__USER']->getToken(), $_GET['id']);
  }

  if (isset($_POST['Save'])) {
    $API->updateTrick($_GET['id'], $_POST['wording'], $_POST['description'], $_POST['content'], $_POST['categoryId'], $_SESSION['PROG_VAR__USER']->getToken());
    $element = $API->getTrickById($_SESSION['PROG_VAR__USER']->getToken(), $_GET['id']);
  } elseif (isset($_POST['Delete'])) {
    $API->deleteTrick($_SESSION['PROG_VAR__USER']->getToken(), $_GET['id']);
    header("Location: tricks.php");
  } elseif (isset($_POST['Back'])) {
    header("Location: tricks.php");
  }

  require_once("includes/begin_html.php");
?>

<form action="" method="POST">
  <button type="submit" name="Back" formnovalidate>Retour</button>
  <button type="submit" name="Save">Enregistrer</button>
  <button type="submit" name="Delete">Supprimer</button>
  <hr>
  <input type="text" name="wording" placeholder="Titre du Trick" value="<?php echo $element['wording'] ?>" required>
  <select name="categoryId" required>
    <?php getCategoryList($API, $element['categoryId']) ?>
  </select>
  <textarea name="description" rows="8" cols="80" placeholder="Description" required><?php echo $element['description'] ?></textarea>
  <textarea name="content" rows="8" cols="80" placeholder="Contenu" required><?php echo $element['content'] ?></textarea>
</form>



<?php
  require_once("includes/end_html.php");

  function getCategoryList($API, $selected) {
    $categories = $API->categories($_SESSION['PROG_VAR__USER']->getToken());

    foreach ($categories as $category) {
      echo '<option value="'.$category['id'].'"';
      if ($category['id'] == $selected)
        echo ' selected="selected"';
      echo '>'.$category['wording'].'</option>';
    }
  }
?>