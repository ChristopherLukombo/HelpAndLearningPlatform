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
INSERT INTO category (id, wording) VALUES(6, 'Cuisine');

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
VALUES (1, 'Déroulez, étalez et piquez la pâte dans un moule à tarte.', 'Recette de la tarte aux pommes', 6, 1, '2019-06-07', 1, 'Déroulez, étalez et piquez la pâte dans un moule à tarte. Pelez, videz et coupez en fines tranches les pommes. Posez-les sur la pâte en rosace.Dans un saladier, battez les œufs avec le sucre, puis ajoutez la crème et la cannelle. Versez le mélange sur les pommes.Mettez au four à 210°C (thermostat 7) pour 40 minutes environ. Vers la fin de la cuisson, répartissez sur la tarte le sucre vanillé et remettez au four pour caramélisé.');
INSERT INTO trick (id, description, wording, category_id, own_user_id_trick, creation_date, view_number, content)
VALUES (2, 'On appelle discriminant du trinôme ax2 + bx + c, le nombre réel, noté delta, égal b2 - 4AC.', 'les equations du second degré', 2, 1, '2019-06-07', 1, '');
INSERT INTO trick (id, description, wording, category_id, own_user_id_trick, creation_date, view_number, content)
VALUES (3, 'Les accents aigu et grave sont des signes placés sur les voyelles pour en modifier la prononciation ou pour le différencier des homonymes.', 'Les accents', 3, 1, '2019-06-07', 1, 'Les accents aigu et grave sont des signes placés sur les voyelles pour en modifier la prononciation ou pour le différencier des homonymes. L\'accent aigu se place uniquement sur la lettre E (é) et il se prononce (et). amitié - ténacité - prévenir - médecin');
INSERT INTO trick (id, description, wording, category_id, own_user_id_trick, creation_date, view_number, content)
VALUES (4, 'La forme verbale en -ant précédée de en est appelée gérondif', 'le gerondif', 4, 1, '2019-07-21', 1, 'La forme verbale en -ant précédée de en est appelée gérondif. EXEMPLE : Le plombier chante en travaillant.(en travaillant équivaut à:pendant qu\'il travaille, ou à: pendant son travail).Le gérondif est l\'équivalent d\'une subordonnée circonstancielle ayant même sujet que la principale, d\'un groupe prépositionnel circonstanciel ou parfois, d\'un adverbe de manière.');
INSERT INTO trick (id, description, wording, category_id, own_user_id_trick, creation_date, view_number, content)
VALUES (5, 'La Préhistoire est généralement définie comme la période comprise entre l\'apparition du genre humain', 'la prehistoire', 5, 1, '2019-07-21', 1, 'La Préhistoire est généralement définie comme la période comprise entre l\'apparition du genre humain et l\'apparition des premiers documents écrits. Cette définition laisse cependant la place à des interprétations divergentes selon les auteurs.');

-- notation
SELECT '[INFO] Insertion dans la table "notation"' as '';
INSERT INTO notation (id, note, trick_id_notation, user_id) VALUES(1, 2.0, 1, 1);
INSERT INTO notation (id, note, trick_id_notation, user_id) VALUES(2, 4.0, 2, 2);
INSERT INTO notation (id, note, trick_id_notation, user_id) VALUES(3, 3.0, 3, 3);
INSERT INTO notation (id, note, trick_id_notation, user_id) VALUES(4, 5.0, 4, 4);
INSERT INTO notation (id, note, trick_id_notation, user_id) VALUES(5, 1.0, 5, 5);

-- subscription
SELECT '[INFO] Insertion dans la table "subscription"' as '';
INSERT INTO subscription (id, subscription_date, trick_id, user_id, finished)
VALUES (1, NOW(), 1, 1, 1);
INSERT INTO subscription (id, subscription_date, trick_id, user_id, finished)
VALUES (2, NOW(), 2, 2, 0);
INSERT INTO subscription (id, subscription_date, trick_id, user_id, finished)
VALUES (3, NOW(), 3, 3, 1);
INSERT INTO subscription (id, subscription_date, trick_id, user_id, finished)
VALUES (4, NOW(), 4, 4, 1);
INSERT INTO subscription (id, subscription_date, trick_id, user_id, finished)
VALUES (5, NOW(), 5, 5, 0);

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
INSERT INTO comment (id, name, trick_id_comment, user_id)
VALUES (1, 'Bien', 1, 1);
INSERT INTO comment (id, name, trick_id_comment, user_id)
VALUES (2, 'Tres bonne astuce', 2, 2);
INSERT INTO comment (id, name, trick_id_comment, user_id)
VALUES (3, 'Very good !', 3, 2);
INSERT INTO comment (id, name, trick_id_comment, user_id)
VALUES (4, 'Excellent', 4, 3);
INSERT INTO comment (id, name, trick_id_comment, user_id)
VALUES (5, 'Nul !', 5, 4);

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