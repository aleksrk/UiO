# Design Document: Robol Interpreter

This document describes the design and implementation of the Robol Interpreter.
It is a tool that interprets commands for controlling a robot on a grid, and
the primary objective of this interpreter is to execute a series of commands, effectively maneuvering a robot across
a grid while avoiding moving outside its set boundaries.

We divide the interpreter into two main components:
1. The Robol Interface and Classes are key classes that define the behaviors and properties of the robot, grid and their interactions.
2. Statements and Expressions: Abstractions that represent the commands and operations our robot can perform.

I chose to expand on the given pre-code on GitHub, and therefore decided on an object-oriented solution for this task. I tried to make almost all non-terminals from the provided grammar into classes, in order to have a good structure and easy to read code. Every behaviour of all the grammar is then divided into it's own parts, and making the AST is also easy as we can use abstract classes to define the heads of our AST, and extensions of this abstract class as the nodes. If we were to provide a scanner for the written code instead of hard coding it into TestCode.java, we could do this relatively easy with the current implementation of the interpreter. I tried to make the Identifier class a bit more advanced than necessary, but decided to fall back on a much simpler implementation. I can see from the grammar that bindings are only made once, and we can assume we do not "rebind" a variable to a value later on. Was this the case we would have to change the way the Identifier class is implemented currently.

Before the actual robot is initiated the TestCode saves the grid, all identifiers, the start and a list of all statements. I tried to make the Identifiers a part of the statements so that everything would be done inside the interpretation of the Robot, but could not get it to work so I fell back on a simpler solution. Once everything is created the robot is initiated and the interpret command is called. The robot then goes through all the statements from first to last until it hits the non-terminal symbol Stop or the robot moves outside the grid. Ideally we would code a try/catch block in the TestCode, but I reverted back to just throwing the error in the interpretation of the steps method. Unfortunately this makes it so we cannot run all 6 tests at once, because once a program goes outside the grid the entire program stops.

The <boolean_exp> non-terminal is unfortunately not implemented in my solution. I made a shortcut by just using ArithmeticExp where we would normally use the BooleanExp in the while loops. This implementation is probably not done correctly, but all given test programs work as expected as of now.

## How to run the program

1. Unpack the given zip to your current folder by using your favorite unzipping tool.
2. Compile the code using command `java *.java``
3. Run a test program with `java Oblig1 1|2|3|4|5|6|all`

