-- Ce script permet de supprimer les données dans les tables de la base de données "helpandlearningplatform".

use helpandlearningplatform;

-- user_friends
SELECT '[INFO] Suppression des données dans les tables de la base de données "helpandlearningplatform"' as '';
DELETE FROM authority;
DELETE FROM category;
DELETE FROM user;
DELETE FROM trick;
DELETE FROM notation;
DELETE FROM subscription;
DELETE FROM qcm;
DELETE FROM qcmanswers;
DELETE FROM comment;
DELETE FROM user_friends;