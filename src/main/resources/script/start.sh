#!/bin/sh

application_name=spring-boot-cometd
server_port=9011
socketio_port=9021

echo "Starting application [$application_name], Environment [$1] Instance [$2]"

for (( i = 0; i < $2; i++ )); do
    logpath=../logs/process_$((i + 1))
    outfile="${logpath}/process.log"
    if [ -f $outfile ]; then
        rm $outfile
    fi
    mkdir -p ${logpath} && touch $outfile
    java -Djava.ext.dirs=../lib -jar ../lib/$application_name-*.jar --server.port=${server_port} --socketio.port=${socketio_port} --logging.file.path=${logpath} --spring.config.location=file:../env/application-$1.yml >> $outfile 2>&1 &
    ((server_port = server_port + 1))
    ((socketio_port = socketio_port + 1))
done

sleep 5s
ps -fu $USER | grep -w $application_name | grep -v grep | awk '{print "Running Instance PID ["$2"] StartTime ["$5"] ["$11"] ["$12"]"}'