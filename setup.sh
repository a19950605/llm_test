#!/bin/sh
#complie the java first, although i should be complied the llm.java

echo "this shell script should work on git bash or linux terminal"
echo "compiling the llm.java"
javac llm.java

echo "adding the custom command to ~/.bash_profile"

echo "alias llm='java llm.java'" >> ~/.bash_profile

echo "updating"

source ~/.bash_profile


echo "it should be fine if you type llm xxx xxx to run the program in this folder"



