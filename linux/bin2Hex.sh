#!/bin/bash
FILE_PATH='/home/timopass/middleWare/firmware/'
FILE_SIZE=$(ls -al ${FILE_PATH} |grep .bin |awk '{print $5 }')
#FILE_SIZE=$(ls -al /home/timopass/middleWare/firmware/ |grep .bin |awk '{print $5 }')

# if $8 isn't fileName ,  choice A or B
# A.  add .bash_profile  ->  alias ls='ls --color=auto --time-style=long-iso'
# B.  $8 -> $9
FILE_NAME=$(ls -al $FILE_PATH |grep .bin |awk '{print $9 }')
if [ $FILE_NAME = '']; then
  FILE_NAME=$(ls -al $FILE_PATH |grep .bin |awk '{print $8 }')
fi
echo "[$FILE_NAME]"
#FILE_NAME=$(ls -al /home/timopass/middleWare/firmware/ |grep .bin |awk '{print $9 }')
FILE_NEW_NAME=$(echo "$FILE_NAME" | sed "s/bin/hex/g")
#echo $FILE_NAME " -> " $FILE_NEW_NAME
echo $FILE_PATH
echo $FILE_NAME
echo $FILE_NEW_NAME

xxd -l $FILE_SIZE -p -c $FILE_SIZE ${FILE_PATH}${FILE_NAME} > ${FILE_PATH}${FILE_NEW_NAME}
mv ${FILE_PATH}${FILE_NAME} ${FILE_PATH}backup/
echo $FILE_NAME "move ./backup/"
