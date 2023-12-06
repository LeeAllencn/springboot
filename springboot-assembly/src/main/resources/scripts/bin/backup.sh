#!/bin/bash
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CURRENT_DIR=`basename $DEPLOY_DIR`

BAK_SUFFIX=-`date +%Y%m%d%H%M%S`.tar.gz
BAK_NAME=$CURRENT_DIR$BAK_SUFFIX
BAK_FULL_NAME=$DEPLOY_DIR$BAK_POSTFIX

cd ..
tar -czf $BAK_NAME $CURRENT_DIR

if [ -f "$BAK_FULL_NAME" ];then
echo "[INFO]: 应用备份成功,备份文件名$BAK_NAME!!"
else
echo "应用备份失败!!"
fi

rm -r $DEPLOY_DIR;
if [ ! -d "$CURRENT_DIR" ];then
echo "[INFO]: 应用[$CURRENT_DIR]删除成功!!"
fi
#移除备份文件名称后面的后缀获取原文件名称
#$($BAK_NAME | sed 's@-[0-9]\{14\}\.tar\.gz@@g')