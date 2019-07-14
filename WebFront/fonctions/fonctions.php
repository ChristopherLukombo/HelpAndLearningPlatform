<?php
  function getDateForBDD() {
    return date('Y-m-d');
  }

  function showTrick($trick) {
    echo '<div class="trick">';
      echo '<div class="trick-header">';
        echo '<button class="trick-button-edit" name="id" value="'.$trick['id'].'">Éditer</button>';
        echo '<h1 class="trick-title">'.$trick['wording'].'</h1>';
      echo '</div>';
      echo '<div class="trick-body">';
        echo '<span class="trick-category">'.$trick['categoryId'].'</span>';
        echo '<p class="trick-description">'.$trick['description'].'</p>';
        echo '<p class="trick-content">'.$trick['content'].'</p>';
        echo '<p class="trick-creation-date">Créé le '.$trick['creationDate'].'</p>';
      echo '</div>';
    echo '</div>';
  }

  function showEmptyTrickMessage() {
    echo '<p>Aucun trick pour le moment</p>';
  }

?>