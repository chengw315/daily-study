DROP PROCEDURE IF EXISTS register;
CREATE PROCEDURE register(IN myphone VARCHAR(11))
BEGIN
DECLARE mycount INT DEFAULT 0;
SELECT id FROM myuser WHERE id = 1 FOR UPDATE;
SELECT count(*) FROM myuser WHERE `phone` = myphone INTO @mycount;
SELECT SLEEP(10);
IF @mycount = 0 THEN
  INSERT INTO myuser(`name`,phone) VALUES('test',myphone);
END IF;
COMMIT;
END;
