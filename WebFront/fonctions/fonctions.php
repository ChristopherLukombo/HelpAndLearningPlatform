<?php
  function getDateForBDD() {
    return date('Y-m-d');
  }

  function showTrick($trick, $categories, $stats = false) {
    echo '<div class="trick">';
      echo '<div class="trick-header">';
        echo '<button class="trick-button-edit" name="id" value="'.$trick['id'].'">Éditer</button>';
        echo '<h1 class="trick-title">'.$trick['wording'].'</h1>';
      echo '</div>';
      echo '<div class="trick-body">';
        echo '<p class="trick-category">'.$categories[$trick['categoryId']]['wording'].'</p>';
        echo '<p class="trick-description">'.$trick['description'].'</p>';
        echo '<p class="trick-content">'.$trick['content'].'</p>';
        if ($stats !== false) {
          echo '<span class="trick-stats-mark">Note: '.$stats['mark'].'</span>';
          echo '<span class="trick-stats-subscribers">Abonnés: '.$stats['numberOfSubscribedUsers'].'</span>';
          echo '<span class="trick-stats-comments">Commentaires: '.$stats['numberOfComments'].'</span>';
        }
        echo '<p class="trick-creation-date">Créé le '.$trick['creationDate'].'</p>';
      echo '</div>';
    echo '</div>';
  }

  function showEmptyTrickMessage() {
    echo '<p>Aucun trick pour le moment</p>';
  }

?>