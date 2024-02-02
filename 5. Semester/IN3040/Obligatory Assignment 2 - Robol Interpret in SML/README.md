# Design Document: Robol Interpreter in SML

This document describes the design and implementation of an interpreter for a grid-based robot simulation written in Standard ML (SML). The interpreter allows the simulation of a robot moving and performing actions on a grid based on a set of instructions defined in a program.

We divide the task into two files:
1. 'Robol.sml' This is where we have the interpreter for the Robot
2. 'Main.sml' Main file used to run the test programs

I chose again to expand on the given pre-code on GitHub, and have therefore made a series of datatypes and functions in SML. To deal with the criteria from the task I've implemented several types that will store values throughout the execution of the program. The most important type 'state' will save all the necessary information about the robot during execution, such as position, direction, and amount of steps taken. Everything else is split into some functions and some helper functions. As we have no way of making classes or inheritance all instances of a 'group' of commands are put into one function, with different signatures to differentate between them.

The main function 'interpret' will run through the entire list of commands and execute them on the robot one by one. the main execution function 'interpretStmt' will choose the correct command given in the program and call the relevant functions in order to manipulate the robot on the grid. This seemed like the most straight forward approach, although maybe not the best.

Expression evaluation is done with the 'evalExp' function. It evaluates expressions based on the operator used in the program. Movement and rotation is done with 'rotate' and 'step' functions, which return the updated rotation or position. 

The test programs is ran through the 'Main.sml' where we have set up a simple function for all the tests. The main method itself will take an argument from the console when the program is run in SML/NJ. The argument will decide which test case to run.

The Assigment case in the 'interpretStmt' does not work as expected. When attempting to return the 'newDecls' value an error is thrown. I have therefore made this case just return the original state, so it makes no changes to the values.

## How to run the program

1. Unpack the given zip to your current folder by using your favorite unzipping tool.
2. In an enviornment where SML/NJ is installed, simply use the command 'sml Main.sml 1|2|3|4|5'
