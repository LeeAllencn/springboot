#!/bin/bash
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf

sh $BIN_DIR/stop.sh
#获取最后一级目录名称
LAST_DIR=$(basename $DEPLOY_DIR)
#删除部署目录
rm -rf $DEPLOY_DIR
