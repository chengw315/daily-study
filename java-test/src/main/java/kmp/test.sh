dumpStart=`date +%s`
echo "DumpMigrator start $dumpStart"
sleep 10
dumpEnd=`date +%s`
dumpDif=$[ dumpEnd - dumpStart ]
echo "dump start time $dumpStart"
echo "dump end time $dumpEnd"
echo "dump use $dumpDif seconds"
sleep 10