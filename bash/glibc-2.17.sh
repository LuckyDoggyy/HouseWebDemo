#!/bin/bash
ver=2.17
cd /home
wget http://ftp.gnu.org/gnu/glibc/glibc-${ver}.tar.gz
tar -zxvf glibc-${ver}.tar.gz
cd glibc-${ver}
mkdir build
cd build
../configure --prefix=/usr --disable-profile --enable-add-ons --with-headers=/usr/include --with-binutils=/usr/bin
make -j4
make install
