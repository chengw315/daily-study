echo Migrating Start-----%time%

cd %MYSQL_HOME%\bin
# 实际执行结果select into的结果存储在 数据库容器/var/lib/msqyl/数据库/E:dvcHistoryDump20.csv下
echo First dump-----%time%
call mysql -h106.15.251.107  -P3306 -uroot -pchengw -Dcolumbia_limadw -f --default-character-set=utf8 -e "select * from history_copy into outfile 'E:\dvcHistoryDump20.csv'"
echo -----dump end-----%time%

#清表
call mysql -h106.15.251.107  -P3306 -uroot -pchengw -Dcolumbia_limadw -f --default-character-set=utf8 -e "truncate history_copy"

echo Second read dump-----%time%
call mysql -h106.15.251.107  -P3306 -uroot -pchengw -Dcolumbia_limadw -f --default-character-set=utf8 -e "load data infile 'E:\dvcHistoryDump20.csv' into table history_copy"
echo -----read dump end-----%time%

echo Migrating end-----%time%

msg %username% your dumpAndRead bat exec over
pause