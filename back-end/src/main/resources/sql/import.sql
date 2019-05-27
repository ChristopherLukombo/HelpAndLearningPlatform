-- Ce script permet d'importer les données dans la base de données "helpandlearningplatform".

use helpandlearningplatform;

-- authority
SELECT '[INFO] Insertion dans la table "authority"' as '';
INSERT INTO authority (id, name) VALUES(1, 'ROLE_USER');
INSERT INTO authority (id, name) VALUES(2, 'ROLE_ADMIN');

-- category
SELECT '[INFO] Insertion dans la table "category"' as '';
INSERT INTO category (id, wording) VALUES(1, 'Informatique');
INSERT INTO category (id, wording) VALUES(2, 'Mathematiques');
INSERT INTO category (id, wording) VALUES(3, 'Francais');
INSERT INTO category (id, wording) VALUES(4, 'Anglais');
INSERT INTO category (id, wording) VALUES(5, 'Histoire');

-- user
SELECT '[INFO] Insertion dans la table "user"' as '';
INSERT INTO user (`id`, `activated`, `country_of_residence`, `date_of_last_connection`, `email`, `first_name`, `image_url`, `lang_key`, `last_name`, `login`, `password_hash`, `authority_id`) 
VALUES (1, b'1', 'France', NULL, 'chris.bendoza@gmail.com', 'Chris', NULL, 'fr', 'Bendoza', 'chris.bendoza', '$2a$10$dba3UXV/xG.bMM88z40msetMy4lJuvOz96z8d7z0pCsjC3ma1a1Pu', '1');
INSERT INTO user (`id`, `activated`, `country_of_residence`, `date_of_last_connection`, `email`, `first_name`, `image_url`, `lang_key`, `last_name`, `login`, `password_hash`, `authority_id`) 
VALUES (2, b'1', 'France', NULL, 'denis.bosc@gmail.com', 'Denis', NULL, 'fr', 'Bosc', 'denis.bosc', '$2a$10$dba3UXV/xG.bMM88z40msetMy4lJuvOz96z8d7z0pCsjC3ma1a1Pu', '2');
INSERT INTO user (`id`, `activated`, `country_of_residence`, `date_of_last_connection`, `email`, `first_name`, `image_url`, `lang_key`, `last_name`, `login`, `password_hash`, `authority_id`) 
VALUES (3, b'1', 'France', NULL, 'cindy.katalina@gmail.com', 'Cindy', NULL, 'fr', 'Katalina', 'cindy.katalina', '$2a$10$dba3UXV/xG.bMM88z40msetMy4lJuvOz96z8d7z0pCsjC3ma1a1Pu', '1');
INSERT INTO user (`id`, `activated`, `country_of_residence`, `date_of_last_connection`, `email`, `first_name`, `image_url`, `lang_key`, `last_name`, `login`, `password_hash`, `authority_id`) 
VALUES (4, b'1', 'France', NULL, 'maryse.benalla@gmail.com', 'Maryse', NULL, 'fr', 'Benalla', 'maryse.benalla', '$2a$10$dba3UXV/xG.bMM88z40msetMy4lJuvOz96z8d7z0pCsjC3ma1a1Pu', '2');
INSERT INTO user (`id`, `activated`, `country_of_residence`, `date_of_last_connection`, `email`, `first_name`, `image_url`, `lang_key`, `last_name`, `login`, `password_hash`, `authority_id`) 
VALUES (5, b'1', 'France', NULL, 'melissa.goness@gmail.com', 'Melissa', NULL, 'fr', 'Goness', 'melissa.goness', '$2a$10$dba3UXV/xG.bMM88z40msetMy4lJuvOz96z8d7z0pCsjC3ma1a1Pu', '1');

-- trick
SELECT '[INFO] Insertion dans la table "trick"' as '';
INSERT INTO trick (id, description, wording, category_id, own_user_id_trick, creation_date, view_number, content)
VALUES (1, 'ok', 'les structures', 1, 1, NULL, 1, 'une structure');
INSERT INTO trick (id, description, wording, category_id, own_user_id_trick, creation_date, view_number, content)
VALUES (2, 'ok', 'les equations', 2, 1, NULL, 1, 'bien');
INSERT INTO trick (id, description, wording, category_id, own_user_id_trick, creation_date, view_number, content)
VALUES (3, 'ok', 'la grammaire', 3, 1, NULL, 1, 'bien');
INSERT INTO trick (id, description, wording, category_id, own_user_id_trick, creation_date, view_number, content)
VALUES (4, 'ok', 'le gerondif', 4, 1, NULL, 1, 'bien');
INSERT INTO trick (id, description, wording, category_id, own_user_id_trick, creation_date, view_number, content)
VALUES (5, 'ok', 'la prehistoire', 5, 1, NULL, 1, 'bien');

-- notation
SELECT '[INFO] Insertion dans la table "notation"' as '';
INSERT INTO notation (id, note, trick_id_notation) VALUES(1, 10.0, 1);
INSERT INTO notation (id, note, trick_id_notation) VALUES(2, 8.0, 2);
INSERT INTO notation (id, note, trick_id_notation) VALUES(3, 17.0, 3);
INSERT INTO notation (id, note, trick_id_notation) VALUES(4, 18.0, 4);
INSERT INTO notation (id, note, trick_id_notation) VALUES(5, 11.0, 5);

-- subscription
SELECT '[INFO] Insertion dans la table "subscription"' as '';
INSERT INTO subscription (id, subscription_date, category_id, user_id)
VALUES (1, NOW(), 1, 1);
INSERT INTO subscription (id, subscription_date, category_id, user_id)
VALUES (2, NOW(), 2, 2);
INSERT INTO subscription (id, subscription_date, category_id, user_id)
VALUES (3, NOW(), 3, 3);
INSERT INTO subscription (id, subscription_date, category_id, user_id)
VALUES (4, NOW(), 4, 4);
INSERT INTO subscription (id, subscription_date, category_id, user_id)
VALUES (5, NOW(), 5, 5);

-- qcm
SELECT '[INFO] Insertion dans la table "qcm"' as '';
INSERT INTO qcm (id, question)
VALUES (1, 'Quelle etait votre recenti ?');
INSERT INTO qcm (id, question)
VALUES (2, 'Que voulez-vous avoir de plus ?');
INSERT INTO qcm (id, question)
VALUES (3, 'Etes-vous satisfait ?');
INSERT INTO qcm (id, question)
VALUES (4, 'Y a t il des choses a ameliorer selon-vous ?');
INSERT INTO qcm (id, question)
VALUES (5, 'Recommanderiez-vous cette astuce ?');

-- qcmanswers
SELECT '[INFO] Insertion dans la table "qcmanswers"' as '';
INSERT INTO qcmanswers (id, answer, qcm_id, trick_id)
VALUES (1, 'Je suis degoute, je m attendais a une meilleure explication', 1, 1);
INSERT INTO qcmanswers (id, answer, qcm_id, trick_id)
VALUES (2, 'Des photos', 2, 2);
INSERT INTO qcmanswers (id, answer, qcm_id, trick_id)
VALUES (3, 'Oui !', 3, 3);
INSERT INTO qcmanswers (id, answer, qcm_id, trick_id)
VALUES (4, 'Les animations', 4, 4);
INSERT INTO qcmanswers (id, answer, qcm_id, trick_id)
VALUES (5, 'Non !', 5, 5);

-- comment
SELECT '[INFO] Insertion dans la table "comment"' as '';
INSERT INTO comment (id, name, trick_id_comment)
VALUES (1, 'Bien', 1);
INSERT INTO comment (id, name, trick_id_comment)
VALUES (2, 'Tres bonne astuce', 2);
INSERT INTO comment (id, name, trick_id_comment)
VALUES (3, 'Very good !', 3);
INSERT INTO comment (id, name, trick_id_comment)
VALUES (4, 'Excellent', 4);
INSERT INTO comment (id, name, trick_id_comment)
VALUES (5, 'Nul !', 5);

-- user_friends
SELECT '[INFO] Insertion dans la table "user_friends"' as '';
INSERT INTO user_friends (user_id, friends_id)
VALUES (1, 2);
INSERT INTO user_friends (user_id, friends_id)
VALUES (1, 3);
INSERT INTO user_friends (user_id, friends_id)
VALUES (5, 4);
INSERT INTO user_friends (user_id, friends_id)
VALUES (2, 5);
INSERT INTO user_friends (user_id, friends_id)
VALUES (3, 1);