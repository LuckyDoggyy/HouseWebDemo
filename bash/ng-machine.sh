#!/bin/bash
if [ $1 == "" ];then
#init enviroment
sh /mnt/enc.sh 

#install mysql
yum -y install mysql
yum -y install mysql-server*
service mysqld start

#update glibc for redis
sh /mnt/glibc-2.17.sh
fi

#install nginx
if [ "$1" == "install-nginx" ];then
cp -rf /mnt/nginx* /home
cd /home/nginx-1.12.2
yum -y install pcre*
yum -y install zlib*
./configure
make
make install
#config nginx
cp -f /mnt/nginx.conf /usr/local/nginx/conf
fi

#install redis
if [ "$1" == "install-redis" ];then
cp -rf /mnt/redis-4.0.6 /home
cp /mnt/redis.conf /home/redis-4.0.6
fi
