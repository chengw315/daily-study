SET@@Session.tx_isolation='serializable';
BEGIN;
SELECT * FROM myuser WHERE phone='1502256';
SELECT SLEEP(20);
INSERT INTO myuser(phone) VALUES ('1502256');
COMMIT;