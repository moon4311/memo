#!/bin/bash

# 파라미터 개수 : $#
# 첫번째 파라미터 : $1
# 모든 파라미터 : $@
# since 2022.01.12 KimJaeMoon

echo "dirname : $(dirname $(realpath $0))"

if test -z "$1"
then
   echo " parameter is empty!!!"
   echo " ex) create_plink.sh plink_1000 "
else
# mysql 실행문 : -e
   mysql -uroot -e "create database $1";
   mysql -uroot $1 < $(dirname $(realpath $0))/plink.sql
fi
