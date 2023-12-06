#!/bin/bash
#########################################
## ！！！main-class. Replace it with yourself. 替换为自己的主类！！！
#########################################
MAIN_CLASS="com.rocky.boot.assembly.SpringbootAssemblyApplication"
SERVER_NAME=$MAIN_CLASS
source /etc/profile
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf
LOGS_FILE=""
STDOUT_FILE="stdout.log"

echo SERVER_NAME=$SERVER_NAME


PIDS=`ps -ef | grep java | grep "$CONF_DIR" |awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME already started!"
    echo "PID: $PIDS"
    exit 1
fi

LOGS_DIR=""
if [ -n "$LOGS_FILE" ]; then
    LOGS_DIR=`dirname $LOGS_FILE`
else
    LOGS_DIR=$DEPLOY_DIR/logs
fi
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/$STDOUT_FILE

echo STDOUT_FILE=$STDOUT_FILE 

LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi

JAVA_JMX_OPTS=""
if [ "$1" = "jmx" ]; then
    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi

BITS=`java -version 2>&1 | grep -i 64-bit`

echo BITS=$BITS

JAVA_MEM_OPTS=""
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -server -Xmx2g -Xms2g -Xmn256m -Xss512k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
else
    JAVA_MEM_OPTS=" -server -Xms1g -Xmx1g -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi


echo -e "[INFO]: Application[$MAIN_CLASS] is starting  \n"

REMOAT_DEBUG_OPTS=" -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1601 "

nohup java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -classpath $CONF_DIR:$LIB_JARS  $MAIN_CLASS > $STDOUT_FILE 2>&1 &


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
        echo "[ERROR]: 应用[$MAIN_CLASS]在${TOTAL}s内未能成功启动，请手工检查！！"
        break
    fi
    if [ $COUNT -gt 0 ]; then
        echo "OK!"
        PIDS=`ps -ef | grep java | grep "$DEPLOY_DIR" | awk '{print $2}'`
        echo "PIDS:$PIDS"
        break
    fi
done

echo "STDOUT: $STDOUT_FILE"
