<?php
  function showTricks($API) {
    $trips = getAllTricks($API);


  }

  function getAllTricks($API) {
    $tricks = $API->tricks($_SESSION['PROG_VAR__USER']->getToken());
    var_dump($tricks);
  }

?>