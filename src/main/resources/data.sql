INSERT INTO MEMBERS(id, role, name, password, user_id) 
VALUES(
0
, 'ROLE_ADMIN'
, '기범'
, '{bcrypt}$2a$10$Q08i77omtCh6igdqGZPbF.YU.b2uRp2xplyjNKjbrL0g1O1FX/FhS' --qawsedrf12
, 'coca');

INSERT INTO AUTHS(id, user_id, url, auth_sp) VALUES(0, 0, 'coca', 'example/test1', 'C');
INSERT INTO AUTHS(id, user_id, url, auth_sp) VALUES(1, 0, 'coca', 'example/test1', 'R');
INSERT INTO AUTHS(id, user_id ,url, auth_sp) VALUES(2, 0, 'coca', 'example/test1', 'U');
INSERT INTO AUTHS(id, user_id, url, auth_sp) VALUES(3, 0, 'coca', 'example/test1', 'D');

