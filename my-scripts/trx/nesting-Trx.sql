BEGIN;
  SAVEPOINT a;
  BEGIN;
   INSERT INTO myuser (`name`) VALUES("d2");
  COMMIT;
ROLLBACK to SAVEPOINT a;