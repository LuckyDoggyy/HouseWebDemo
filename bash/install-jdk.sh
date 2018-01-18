#!/bin/bash

cp jdk-8u161-linux-x64.rpm /home
cd /home
rpm -ivh jdk-8u161-linux-x64.rpm

src=/etc/profile
echo "#jdk path set" >> ${src}
echo "JAVA_HOME=/usr/java/jdk1.8.0_161" >> ${src}
echo "JRE_HOME=/usr/java/jdk1.8.0_161/jre" >> ${src}

yum -y remove java-1.?.0-openjdk*
source /etc/profile

echo "CLASS_PATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib" >> ${src}
echo "PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin" >> ${src}
echo "export JAVA_HOME JRE_HOME CLASS_PATH PATH" >> ${src}

source /etc/profile
