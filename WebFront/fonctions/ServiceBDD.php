<?php

/* -------------------- SERVICES REQUETES BDD --------------------------*/

class ServiceBDD {
	private static $enteteErrMessage = "ServiceBDD Error ";
	private $server;
	private $bdd;
	private $login;
	private $motdepasse;
	public  $con;
	private $table;

	function __construct($server, $bdd, $login, $psw) {
		$this->server     = $server;
		$this->bdd        = $bdd;
		$this->login      = $login;
		$this->motdepasse = $psw;

		$this->con = new PDO("mysql:host=".$this->server.";dbname=".$this->bdd, $this->login, $this->motdepasse);
		$this->con->exec("SET NAMES utf8");
		$this->con->exec("SET lc_time_names = 'fr_FR'");
		$this->con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	}

	function insertIntoTable($table, $fields) {
		if (is_array($fields) && count($fields) > 0) {
			// Entête de la requête :
			$sql  = "INSERT INTO ".$table." ";
			$sql .= "(".array_keys($fields)[0];
			for ($i = 1; $i < count(array_keys($fields)); $i++)
				$sql .= ", ".array_keys($fields)[$i];
			$sql .= ") ";

			// Suite des instructions :
			$sql .= "VALUES (".$this->getPlaceMarker(array_keys($fields)[0], $fields[array_keys($fields)[0]]);
			for ($i = 1; $i < count($fields); $i++)
				$sql .= ", ".$this->getPlaceMarker(array_keys($fields)[$i], $fields[array_keys($fields)[$i]]);
			$sql .= ");";

			// Préparation de la requête :
			$stmt = $this->con->prepare($sql);
			// Remplacement des valeurs :
			foreach (array_keys($fields) as $key)
				if ($fields[$key] !== null)
					$stmt->bindValue(":".$key, $fields[$key]);

			// Exécution de la requête :
			$stmt->execute();

			// Renvoi de l'id créé :
			return $this->con->lastInsertId();
		} else {
			return false;
		}
	}

	function updateTable($table, $fields, $where = array()) {
		if (is_array($fields) && count($fields) > 0 && is_array($where)) {
			// Entête de la requête :
			$sql  = "UPDATE ".$table." ";

			// Suite des instructions :
			$sql .= "SET ".array_keys($fields)[0]."=".$this->getPlaceMarker(array_keys($fields)[0], $fields[array_keys($fields)[0]]);
			for ($i = 1; $i < count(array_keys($fields)); $i++)
				$sql .= ", ".array_keys($fields)[$i]."=".$this->getPlaceMarker(array_keys($fields)[$i], $fields[array_keys($fields)[$i]]);

			// Clause WHERE si existe :
			if (count($where) > 0) {
				$sql .= " WHERE ".array_keys($where)[0]."=".$this->getPlaceMarker(array_keys($where)[0], $where[array_keys($where)[0]], "w_");
				for ($i = 1; $i < count(array_keys($where)); $i++)
					$sql .= " AND ".array_keys($where)[$i]."=".$this->getPlaceMarker(array_keys($where)[$i], $where[array_keys($where)[$i]], "w_");
			}

			// Préparation de la requête :
			$stmt = $this->con->prepare($sql);

			// Remplacement des valeurs SET :
			foreach (array_keys($fields) as $key)
				if ($fields[$key] !== null)
					$stmt->bindValue(":".$key, $fields[$key]);

			// Remplacement des valeurs WHERE :
			foreach (array_keys($where) as $key)
				if ($where[$key] !== null)
					$stmt->bindValue(":w_".$key, $where[$key]);

			// Exécution de la requête :
			$stmt->execute();
		} else {
			return false;
		}
	}

	function selectFromTable($table, $fields, $where_equals, $where_like, $orderby = array()) {
		if (is_array($fields) && count($fields) > 0) {
			// Entête de la requête :
			$sql  = "SELECT ";

			// Suite des instructions :
			$sql .= $fields[array_keys($fields)[0]];
			for ($i = 1; $i < count(array_keys($fields)); $i++)
				$sql .= ", ".$fields[array_keys($fields)[$i]];

			// Complétion de l'entête :
			$sql .= " FROM ".$table;

			// Clause WHERE si existe :
			if (count($where_equals) + count($where_like) > 0) {
				// Si WHERE equals est renseigné :
				if (count($where_equals) > 0) {
					for ($i = 0; $i < count(array_keys($where_equals)); $i++)
						$sql .= (($i > 0) ? " AND " : " WHERE ").array_keys($where_equals)[$i]."=:eq_".array_keys($where_equals)[$i];
				}

				// Si WHERE like est renseigné :
				if (count($where_like) > 0) {
					// Parcours des différents critères :
					for ($i = 0; $i < count(array_keys($where_like)); $i++)
						// Si plusieurs critères sont renseigné pour une colonne donnée :
						if (is_array($where_like[array_keys($where_like)[$i]]))
							// Parcours des différentes valeurs pour la colonne donnée :
							for ($j = 0; $j < count($where_like[array_keys($where_like)[$i]]); $j++)
								$sql .= (($i > 0 || $j > 0) ? " AND " : " WHERE ").array_keys($where_like)[$i]." LIKE (:lk_".array_keys($where_like)[$i]."_".$j.")";

						// Si une valeur unique est demandé pour la colonne :
						else
							$sql .= (($i > 0) ? " AND " : " WHERE ").array_keys($where_like)[$i]." LIKE (:lk_".array_keys($where_like)[$i].")";
				}
			}

			// Clause ORDER BY si spécifié :
			if (is_array($orderby) && count($orderby) == 1)
				$sql .= " ORDER BY ".array_keys($orderby)[0]." ".(($orderby[array_keys($orderby)[0]]) ? "ASC" : "DESC");

			// Exécution de la requête :
			$stmt = $this->con->prepare($sql);

			// Remplacement des valeurs de la clause WHERE equals :
			foreach (array_keys($where_equals) as $key)
				$stmt->bindValue(":eq_".$key, $where_equals[$key]);

			// Remplacement des valeurs de la clause WHERE like :
			foreach (array_keys($where_like) as $key) {
				// Si plusieurs valeurs pour la colonne donnée :
				if (is_array($where_like[$key]))
					// Parcours et remplacement des différentes valeurs :
					for ($j = 0; $j < count($where_like[$key]); $j++)
						$stmt->bindValue(":lk_".$key."_".$j, "%".$where_like[$key][$j]."%");
				else
					$stmt->bindValue(":lk_".$key, "%".$where_like[$key]."%");
			}

			$stmt->execute();
			$result = $stmt->fetchAll(PDO::FETCH_ASSOC);

			// Gestion des retours de résultats :
			// Si il y a 1 ou plusieurs résultat(s) :
			if (count($result) >= 1)
				return $result;

			// Si il n'y a aucun résultat :
			return array();
		}
	}

	function deleteFromTable($table, $where = array()) {
		if (is_array($where) && count($where) > 0) {
			// Entête de la requête :
			$sql  = "DELETE FROM ".$table;

			// Clause WHERE si existe :
			if (count($where) > 0) {
				$sql .= " WHERE ".array_keys($where)[0]."='".$where[array_keys($where)[0]]."'";
				for ($i = 0; $i < count(array_keys($where)); $i++)
					$sql .= " AND ".array_keys($where)[$i]."='".$where[array_keys($where)[$i]]."'";
			}

			// Exécution de la requête :
			$stmt = $this->con->prepare($sql);
			$stmt->execute();
		}
	}

	function getPlaceMarker($field, $value, $suffix = "") {
		return ($value === null) ? 'NULL' : ":".$suffix.$field;
	}

	function getTablesNames() {
		try {
			$stmt = $this->con->prepare("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA=:db AND TABLE_TYPE='BASE TABLE'");
			$stmt->bindValue(":db", $this->bdd);
			$stmt->execute();
			return $stmt->fetchAll(PDO::FETCH_ASSOC);
		} catch (PDOException $e) {
			echo $this->getErrorString($e);
		}
	}

	function getColumnNames($table) {
		try {
			$stmt = $this->con->prepare("SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA=:db AND TABLE_NAME=:table");
			$stmt->bindValue(":db", $this->bdd);
			$stmt->bindValue(":table", $table);
			$stmt->execute();
			return $stmt->fetchAll(PDO::FETCH_ASSOC);
		} catch (PDOException $e) {
			echo $this->getErrorString($e);
		}
	}

	function executeSQL($sql) {
		try {
			$stmt = $this->con->prepare($sql);
			$stmt->execute();
			return $stmt->fetchAll(PDO::FETCH_ASSOC);
		} catch (PDOException $e) {
			echo $this->getErrorString($e);
		}
	}

	function getErrorString($e) {
		$str = '<div class="section error">';
		$str .= '<h1>PDO Error ['.$e->getCode().'] : '.$e->getFile().' at line '.$e->getLine().'</h1>';
		$str .= $e->getMessage();
		foreach ($e->getTrace() as $row) {
			$str .= '<p class="line">Error in function '.$row['function'].'()';
			if (isset($row['class']))
				$str .= ' of '.$row['class'].' class';
			$str .= '</p>';
			$str .= '<p class="line_describe">In '.$row['file'].' at line '.$row['line'].'</p>';
			if (is_array($row['args'])) {
				if (count($row['args']) > 0){
					$str .= '<table>';
					$str .= '<tr><th>Argument</th><th>Value</th></tr>';

					foreach ($row['args'] as $key => $value) {
						$str .= '<tr><td>'.$key.'</td>';
						if (is_object($row['args']))
							$str .= '<td>[object]</td></tr>';
						else
							$str .= '<td>'.$value.'</td></tr>';
					}
					$str .= '</table>';
				}
			} elseif (is_object($row['args'])) {
				$str .= 'object';
			} else {
				$str .= $row['args'];
			}
		}
		$str .= '</div>';

		return $str;
	}
}

?>
