#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "eserial/eserial" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Publishing Javadoc...\n"

  cp -R build/docs/javadoc $HOME/javadoc-latest

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=master https://${GH_TOKEN}@github.com/eserial/eserial.github.io master > /dev/null

  cd master
  git rm -rf .
  cp -Rf $HOME/javadoc-latest/. .
  git add -f .
  git commit -m "Generate Javadoc after successful travis build $TRAVIS_BUILD_NUMBER" 
  git push -fq origin master > /dev/null

  echo -e "Published Javadoc to eserial.github.io.\n"
  
fi