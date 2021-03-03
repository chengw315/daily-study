echo Migrating Start-----%time%

cd %MYSQL_HOME%\bin

# 实际执行结果，转储文件保存到本机
echo First dump-----%time%
call mysqldump -h106.15.251.107  -P3306 -uroot -pchengw -c --compact --no-create-info --skip-add-locks columbia_limadw history_copy > E:\dvcHistoryDump20k.sql
echo -----dump end—%time%

echo Second read dump-----%time%
call mysql -h106.15.251.107  -P3306 -uroot -pchengw -Dcolumbia_limadw -f --default-character-set=utf8 -e "source E:\dvcHistoryDump20k.sql"
echo -----read dump end—%time%

echo Migrating end—%time%

msg %username% your dumpAndRead bat exec over
pause