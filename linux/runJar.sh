#!/bin/bash
TYPE=colander

CONFIG_INFO="--spring.config.location=file:../conf/application.yml"
TIME_ZONE="Asia/Seoul"
PROJECT="${TYPE}*.war"
JAVA_OPTS=" ${CONFIG_INFO} -XX:MaxMetaspaceSize=512m -XX:+UseG1GC -Xss4096k -Xms512m -Xmx512m -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true"
JAVA_LIB=""


runPid=$(pgrep -f $TYPE)
runPortCount=$(ps -ef | grep $TYPE | grep -v grep | wc -l)
if [ -z $runPid ]; then
    echo "[$TYPE] server not running"
else
  SLEEP=5
  echo "[$TYPE] kill pid : $runPid "
  kill -TERM $runPid
  while [ $runPortCount -gt 0 ]; do
    runPortCount=$(ps -ef | grep $TYPE | grep -v grep | wc -l)
    if [ $SLEEP -ge 0 ]; then
      sleep 1
    else
      break;
    fi
    SLEEP=`expr $SLEEP - 1 `
  done
fi

if [ $runPortCount -gt 0 ]; then
  echo "[$TYPE] $runPid kill fail"
else
  echo "[$TYPE] Starting..."
  nohup java -jar -Du=$TYPE -Duser.timezone=${TIME_ZONE} ../bin/${PROJECT} ${JAVA_OPTS} < /dev/null > ../log/std.out 2> ../log/std.err &
fi
