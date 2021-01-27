#!/bin/sh

application_name=spring-boot-cometd

ps -fu $USER | grep -w $application_name | grep -v grep | awk '{print "Stopping Instance PID ["$2"] StartTime ["$5"] ["$11"] ["$12"]"}'

declare -a pids
file="pids.tmp"

ps -fu $USER | grep -w $application_name | grep -v grep | awk '{print $2}' > $file

if [ -f "$file" ]; then
    pids=$(cat $file);
    for pid in ${pids}
    do
        kill -15 $pid
    done
fi

rm $file

sleep 5s
ps -fu $USER | grep -w $application_name | grep -v grep | awk '{print "Running Instance PID ["$2"] StartTime ["$5"] ["$11"] ["$12"]"}'