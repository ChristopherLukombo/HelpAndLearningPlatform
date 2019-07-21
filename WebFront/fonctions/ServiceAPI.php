<?php

/* -------------------- SERVICES REQUETES BDD --------------------------*/

class ServiceAPI {
  private static $url = "localhost:8080/api/";

  function __construct() {

  }


  /* ------------------- BASIC FUNCTIONS ------------------- */

  function callPostAPI($route, $params, $token = "") {
    // Initialisation de l'entité cURL :
    $curl = curl_init();

    // Encodage des paramètres au format json :
    $params_json = json_encode($params);

    // Paramètres de l'appel cURL :
    $opts = [
      CURLOPT_URL             => self::$url.$route,
      CURLOPT_POST            => true,
      CURLOPT_POSTFIELDS      => $params_json,
      CURLOPT_RETURNTRANSFER  => true,
      CURLOPT_HTTPHEADER      => array("Content-Type: application/json",
                                       "Content-Length: ".strlen($params_json)),
      CURLOPT_SSL_VERIFYPEER  => false
    ];

    // Ajout des paramètres à cURL :
    curl_setopt_array($curl, $opts);

    // Exécution de l'appel à l'API :
    $response = json_decode(curl_exec($curl), true);
    curl_close($curl);

    return $response;
  }

  function callGetAPI($route, $param, $token = "") {
    // Initialisation de l'entité cURL :
    $curl = curl_init();

    if ($param != "")
      $url = self::$url.$route."/".$param;
    else
      $url = self::$url.$route;

    // Paramètres de l'appel cURL :
    $opts = [
      CURLOPT_URL             => $url,
      CURLOPT_RETURNTRANSFER  => true,
      CURLOPT_SSL_VERIFYPEER  => false
    ];

    if ($token != "")
      $opts[CURLOPT_HTTPHEADER] = array("Content-Type: application/json",
                                        "Authorization: Bearer ".$token);
    else
      $opts[CURLOPT_HTTPHEADER] = array("Content-Type: application/json");

    // Ajout des paramètres à cURL :
    curl_setopt_array($curl, $opts);

    // Exécution de l'appel à l'API :
    $response = json_decode(curl_exec($curl), true);
    curl_close($curl);

    return $response;
  }


  /* ------------------- ROUTE FUNCTIONS ------------------- */

  function register($firstName, $lastName, $email, $login, $password) {
    $params = [
      "firstName"           => $firstName,
      "lastName"            => $lastName,
      "email"               => $email,
      "login"               => $login,
      "password"            => $password,
      "activated"           => true,
      "authorityId"         => "1",
      "countryOfResidence"  => "France",
      "langKey"             => "FR",
      "imageUrl"            => ""
    ];

    return $this->callPostAPI("register", $params);
  }

  function authenticate($login, $password) {
    $params = [
      "password"    => $password,
      "username"    => $login,
      "rememberMe"  => true
    ];

    return $this->callPostAPI("authenticate", $params);
  }

  function user($token, $id) {
    return $this->callGetAPI("users", $id, $token);
  }

  function tricks($token) {
    return $this->callGetAPI("tricks", "", $token);
  }

  function tricksByUser($token, $user_id, $page = 0) {
    // Initialisation de l'entité cURL :
    $curl = curl_init();

    $params = array('userId' => $user_id,
                    'page' => $page,
                    'size' => 20);

    $url = self::$url."tricks/owner?".http_build_query($params);

    // Paramètres de l'appel cURL :
    $opts = [
      CURLOPT_URL             => $url,
      CURLOPT_RETURNTRANSFER  => true,
      CURLOPT_SSL_VERIFYPEER  => false,
      CURLOPT_HTTPHEADER      => array("Content-Type: application/json",
                                        "Authorization: Bearer ".$token)
    ];

    // Ajout des paramètres à cURL :
    curl_setopt_array($curl, $opts);

    // Exécution de l'appel à l'API :
    $response = json_decode(curl_exec($curl), true);
    curl_close($curl);

    return $response;
  }

  function trickById($token, $id) {

  }

  function categories($token) {
    // Initialisation de l'entité cURL :
    $curl = curl_init();

    $url = self::$url."categories/all";

    // Paramètres de l'appel cURL :
    $opts = [
      CURLOPT_URL             => $url,
      CURLOPT_RETURNTRANSFER  => true,
      CURLOPT_SSL_VERIFYPEER  => false,
      CURLOPT_HTTPHEADER      => array("Content-Type: application/json",
                                        "Authorization: Bearer ".$token)
    ];

    // Ajout des paramètres à cURL :
    curl_setopt_array($curl, $opts);

    // Exécution de l'appel à l'API :
    $response = json_decode(curl_exec($curl), true);
    curl_close($curl);

    return $response;
  }

  function newTrick($wording, $description, $content, $categoryId, $ownUserId, $token) {
    $params = [
      "categoryId"    => $categoryId,
      "content"       => $content,
      "description"   => $description,
      "ownUserId"     => $ownUserId,
      "wording"       => $wording,
      "creationDate"  => getDateForBDD(),
      "viewNumber"    => "0"
    ];

    $params_json = json_encode($params);

    // Initialisation de l'entité cURL :
    $curl = curl_init();

    $url = self::$url."tricks";

    // Paramètres de l'appel cURL :
    $opts = [
      CURLOPT_URL             => $url,
      CURLOPT_RETURNTRANSFER  => true,
      CURLOPT_SSL_VERIFYPEER  => false,
      CURLOPT_POST            => true,
      CURLOPT_POSTFIELDS      => $params_json,
      CURLOPT_HTTPHEADER      => array("Content-Type: application/json",
                                        "Authorization: Bearer ".$token)
    ];

    // Ajout des paramètres à cURL :
    curl_setopt_array($curl, $opts);

    // Exécution de l'appel à l'API :
    $response = json_decode(curl_exec($curl), true);
    curl_close($curl);

    return $response;
  }

  function updateTrick($id, $wording, $description, $content, $categoryId, $token) {
    $trick = $this->getTrickById($token, $id);

    $params = [
      "id"            => $id,
      "wording"       => $wording,
      "description"   => $description,
      "content"       => $content,
      "categoryId"    => $categoryId,
      "ownUserId"     => $trick['ownUserId'],
      "creationDate"  => $trick['creationDate'],
      "viewNumber"    => $trick['viewNumber']
    ];

    $params_json = json_encode($params);

    // Initialisation de l'entité cURL :
    $curl = curl_init();

    $url = self::$url."tricks";

    // Paramètres de l'appel cURL :
    $opts = [
      CURLOPT_URL             => $url,
      CURLOPT_RETURNTRANSFER  => true,
      CURLOPT_SSL_VERIFYPEER  => false,
      CURLOPT_CUSTOMREQUEST   => "PUT",
      CURLOPT_POSTFIELDS      => $params_json,
      CURLOPT_HTTPHEADER      => array("Content-Type: application/json",
                                        "Authorization: Bearer ".$token)
    ];

    // Ajout des paramètres à cURL :
    curl_setopt_array($curl, $opts);

    // Exécution de l'appel à l'API :
    $response = json_decode(curl_exec($curl), true);
    curl_close($curl);

    return $response;
  }

  function deleteTrick($token, $id) {
    // Initialisation de l'entité cURL :
    $curl = curl_init();

    $url = self::$url."tricks/".$id;

    // Paramètres de l'appel cURL :
    $opts = [
      CURLOPT_URL             => $url,
      CURLOPT_RETURNTRANSFER  => true,
      CURLOPT_SSL_VERIFYPEER  => false,
      CURLOPT_CUSTOMREQUEST   => "DELETE",
      CURLOPT_HTTPHEADER      => array("Content-Type: application/json",
                                        "Authorization: Bearer ".$token)
    ];

    // Ajout des paramètres à cURL :
    curl_setopt_array($curl, $opts);

    // Exécution de l'appel à l'API :
    $response = json_decode(curl_exec($curl), true);
    curl_close($curl);

    return $response;
  }

  function getTrickById($token, $id) {
    // Initialisation de l'entité cURL :
    $curl = curl_init();

    $url = self::$url."tricks/".$id;

    // Paramètres de l'appel cURL :
    $opts = [
      CURLOPT_URL             => $url,
      CURLOPT_RETURNTRANSFER  => true,
      CURLOPT_SSL_VERIFYPEER  => false,
      CURLOPT_HTTPHEADER      => array("Content-Type: application/json",
                                        "Authorization: Bearer ".$token)
    ];

    // Ajout des paramètres à cURL :
    curl_setopt_array($curl, $opts);

    // Exécution de l'appel à l'API :
    $response = json_decode(curl_exec($curl), true);
    curl_close($curl);

    return $response;
  }

  function getStatsByTrickId($token, $id) {
    // Initialisation de l'entité cURL :
    $curl = curl_init();

    $url = self::$url."stats/trick?trickId=".$id;

    // Paramètres de l'appel cURL :
    $opts = [
      CURLOPT_URL             => $url,
      CURLOPT_RETURNTRANSFER  => true,
      CURLOPT_SSL_VERIFYPEER  => false,
      CURLOPT_HTTPHEADER      => array("Content-Type: application/json",
                                        "Authorization: Bearer ".$token)
    ];

    // Ajout des paramètres à cURL :
    curl_setopt_array($curl, $opts);

    // Exécution de l'appel à l'API :
    $response = json_decode(curl_exec($curl), true);
    curl_close($curl);

    return $response;
  }
}