DROP PROCEDURE IF EXISTS autoRollback;
create PROCEDURE autoRollback()
BEGIN
DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN ROLLBACK; SELECT -1; END;
START TRANSACTION;
INSERT INTO `nothing` VALUES(1,2);
commit;
END;