#!/bin/bash

set -e
search_ak='ak_value'
search_sk='sk_value'
search_pid='pid'
replace_ak='XXXXXXXXXXXXXXXXXXXXXX'
replace_sk='XXXXXXXXXXXXXXXXXXXXXXXX'
replace_pid='XXXXXXXXXXXXXXXXXXXXXXXX'

BASEDIR=$PWD

for file in `find -maxdepth 1 -name 'blueprint.yaml'`; do
  grep "$search" $file &> /dev/null
  if [ $? -ne 0 ]; then
    echo "Search string not found in $file!"
  else
    sed -i "s/$search_ak/$replace_ak/" $file
    sed -i "s/$search_sk/$replace_sk/" $file
    sed -i "s/$search_pid/$replace_pid/" $file
  fi  
done
