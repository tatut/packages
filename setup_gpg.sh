#!/bin/bash

set -e
# Important to not use -x here, or secrets will be exposed...

if [[ $CIRCLE_BRANCH == "master" ]]; then
    mkdir -p ~/.gnupg
    chmod 700 ~/.gnupg
    echo -e "batch\nyes\npinentry-mode loopback\ndefault-key cljsjs.robot@gmail.com\npassphrase $GPG_PASSPHRASE" >> ~/.gnupg/gpg.conf
    # CircleCI doesn't support newlines in vars so newlines are coded as "|"
    echo -e "$GPG_KEY" | tr "|" "\n" > gpg.key
    gpg --allow-secret-key-import --import gpg.key
    rm gpg.key
fi
