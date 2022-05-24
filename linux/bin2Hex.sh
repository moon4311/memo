#!/bin/bash
FILE_PATH='/home/timopass/middleWare/firmware/'
FILE_SIZE=$(ls -al ${FILE_PATH} |grep .bin |awk '{print $5 }')

# version Linux 7 ~
FILE_NAME=$(ls -al $FILE_PATH |grep .bin |awk '{print $9 }')
if [ $FILE_NAME = '']; then
# version Linux 6
  FILE_NAME=$(ls -al $FILE_PATH |grep .bin |awk '{print $8 }')
fi

FILE_NEW_NAME=$(echo "$FILE_NAME" | sed "s/bin/hex/g")

xxd -l $FILE_SIZE -p -c $FILE_SIZE ${FILE_PATH}${FILE_NAME} > ${FILE_PATH}${FILE_NEW_NAME}

# echo $FILE_NAME "move ./backup/"
mv ${FILE_PATH}${FILE_NAME} ${FILE_PATH}backup/
