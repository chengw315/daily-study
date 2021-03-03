DROP EVENT IF EXISTS clearOldData;

CREATE EVENT clearOldData
  ON SCHEDULE EVERY 1 DAY STARTS TIMESTAMP '2020-08-28 09:09:00'
  ON COMPLETION PRESERVE
DO
  DELETE FROM bump_dvc_history WHERE unix_timestamp(GMT_START) < unix_timestamp(CURRENT_TIMESTAMP)-2592000;