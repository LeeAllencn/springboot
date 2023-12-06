#!/bin/bash
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf

sh $BIN_DIR/stop.sh


COUNT=0
NUM=0
TOTAL=36
while [ $COUNT -lt 1 ]; do    
    echo -e ".\c"
    sleep 1
    COUNT=`ps -ef | grep java | grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l`
    NUM=`expr $NUM + 1`
    if [ $NUM -eq $TOTAL ]; then
        printf "\n"
        echo "[ERROR]: 应用在${TOTAL}s内未能成功停止，请手工检查！！"
        break
    fi
    if [ $COUNT -gt 0 ]; then
        echo -e "Application is restarting !"
        sh start.sh
        break
    fi
done

