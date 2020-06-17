This directory contains llm.java , setup.sh,and orderRecord.txt

llm.java - store the main function and the class which store the order
setup.sh - complie the java and set up the path and llm command

orderRecord.txt - store the data, using txt instead of sql/db, because i am afraid the setting may not be correct when i submit



### Design

In this test, I have to consider how to create, modifiy, and show the input from the CLI.
From the user input,  there should only the location and the destination we have to store. Other actions are related the status of the orders store in the text file/database.

For the list_orders and take_order action, it depends on the order have been processed or not, if the order have been taken, it shouldnt been shown on the list and user cant take this order again, if the order is not taken, we can list it out and take it. So From my design, there is a boolean to make decision

there is an  record class to store the unique id,  from, to and boolean, 

we can make different actions based on the question have been mentioned,
if user create the order, they should enter the create_order with to and from, otherwise, there is an error message
if we want to list the order, the order list should not be empty or not all of the order have been taken.
To take the order, we should enter the exist id and the order should not be taken, and the id should be greater than one, otherwise, there is an error message


To simplify the task and for fear there is some db connection problem during submission, I  just us the txt file to store the data, in the first line, it stores the number of the order, and the initial value should be zero, as shown below. 
and I assume the orderRecord should not be modified externally.
```sh
totalOrder,0
```
### list_orders and take_order
if we remain one order , the result should be like this
```sh
totalOrder,4
1,Mong Kok,Central,true
2,Mong Kok,Central,true
3,TST,SSP,true
4,aa,bb,false
```
and we call the list_orders, the result should be this
```sh
4,aa,bb
```
if we take the last order, and call it again, it will show this message

```sh
$ llm list_orders
it seems the order list is empty or all of the orders have been taken
```
```sh
$ llm take_order 42                                         order does not exist 
$ llm take_order -1
The id should be greater than 1

```
### create_order
if you miss one parameter from create_order, it should not allow you to do this, if your input is correct,it should be success
```sh
$  llm create_order "aa"
you want to create order but miss some input e.g(to,from)
it seems the order list is empty or all of the orders have been taken
$ llm create_order "ClearWater Bay" "Tuen Mun"                 1
              
```

### setup.sh
I run this shell from git bash, so i am not sure if it is possible to run on other place
I have add the comment on the code ,so i may not explain this program
```sh
#!/bin/sh                                                                                                               #complie the java first, although i should be complied the llm.java                                                                                                                                                                             echo "this shell script should work on git bash or linux terminal"                                                      echo "compiling the llm.java"                                                                                           javac llm.java                                                                                                                                                                                                                                  echo "adding the custom command to ~/.bash_profile"                                                                                                                                                                                             echo "alias llm='java llm.java'" >> ~/.bash_profile                                                                                                                                                                                             echo "updating"                                                                                                                                                                                                                                 source ~/.bashrc                                                                                                                                                                                                                                                                                                                                                        echo "it should be fine if you type llm xxx xxx to run the program in this folder"  
```
### Some notes
I have finished my java in a single file instead of split the class out because I think this is not a very long program and I am rush on other tasks, so I have to finish it as soon as possible. Moreover, I have already written the class from the llm.java, so I dont want to spend time to migrate it, of course, this is bad practise, but it should be easy to find out all of the function there. 
