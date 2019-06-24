<?php

/* -------------------- SERVICES REQUETES BDD --------------------------*/

class User {
  private $API;

  private $login;
  private $password;
  private $user_id;
  private $token;
  private $firstName;
  private $lastName;
  private $email;

  function __construct($login, $password) {
    $this->API = new ServiceAPI();
    $this->login    = $login;
    $this->password = $password;

    $auth = $this->API->authenticate($login, $password);

    if (isset($auth['error'])) {
      throw new Exception('Erreur de connexion');
    } else {
      $this->user_id  = $auth['id_user'];
      $this->token    = $auth['id_token'];

      $user = $this->API->user($this->token, $this->user_id);

      $this->firstName = $user['firstName'];
      $this->lastName = $user['lastName'];
      $this->email = $user['email'];
    }
  }

  function refreshToken() {
    $auth = $this->API->authenticate($this->login, $this->password);

    $this->user_id  = $auth['id_user'];
    $this->token    = $auth['id_token'];
  }

  function getId() { return $this->user_id; }

  function getLogin() { return $this->login; }

  function getToken() {
    $this->refreshToken();
    return $this->token;
  }
}