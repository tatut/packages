#!/bin/bash

if [[ -z $1 ]]; then
    echo "Use $0 [new-version]"
    exit
fi

sed -i 's/boot-cljsjs\(\s*\)"[0-9.]*\(-SNAPSHOT\)\?"/boot-cljsjs\1"'$1'"/' $(find -name 'build.boot')
