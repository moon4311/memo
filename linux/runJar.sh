#!/bin/bash
TYPE=mall

CONFIG_INFO="--spring.config.location=file:../conf/application.yml"
TIME_ZONE="Asia/Seoul"
PROJECT="${TYPE}*.war"
JAVA_OPTS=" ${CONFIG_INFO} -XX:MaxMetaspaceSize=512m -XX:+UseG1GC -Xss2048k -Xms512m -Xmx512m -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true"
JAVA_LIB=""

runPid=$(pgrep -f bin/${TYPE})
if [ -z $runPid ]; then
    echo "No servers are running"
fi

runPortCount=$(ps -ef | grep bin/${TYPE} | grep -v grep | wc -l)
if [ $runPortCount -gt 0 ]; then
   kill -TERM $runPid
   #PORT=5100
   echo "kill $runPid"
fi
echo "Server $TYPE Starting..."


nohup java -jar -Duser.timezone=${TIME_ZONE} ../bin/${PROJECT} ${JAVA_OPTS} < /dev/null > ../log/std.out 2> ../log/std.err &
