import java.util.List;
import java.util.ArrayList;

interface Robol {
    void interpret(Robot robot);
}

// Main class to wrap the robot and grid
class Program implements Robol {
    Grid grid;
    Robot robot;
    public Program(Grid grid, Robot robot) {
        this.grid = grid;
        this.robot = robot;
    }
    public void interpret(Robot robot) {
        robot.interpret(robot);
    }
}

// Robot class that holds state and executes the commands in the program
class Robot implements Robol {
    // Direction enum to handle the robots orientation, could be moved to it's own class
    private enum Direction {
        NORTH, EAST, SOUTH, WEST;
    }
    // Setting space in memory for all the variables the robot will store duringe execution
    ArrayList <Statement> statements;
    private Direction direction;
    private int x, y;
    private int steps;
    int size_x, size_y;

    // Robot constructor
    public Robot(Start startPos, ArrayList <Statement> statements, Grid grid) {
        this.direction = Direction.EAST;
        this.x = startPos.x.interpret();
        this.y = startPos.y.interpret();
        this.size_x = grid.size_x.interpret();
        this.size_y = grid.size_y.interpret();
        this.statements = statements;
    }

    // Execute interpretation 
    public void interpret(Robot robot) {
        for (Statement statement: statements) {
            statement.interpret(robot);
        }
    }

    // Rotate the robot 90 degrees in either direction
    public void turn(Rotation rotation){
        switch(rotation) {
            case CLOCKWISE:
                direction = Direction.values()[(direction.ordinal() + 1) % 4];
                break;
            case COUNTERCLOCKWISE:
                direction = Direction.values()[(direction.ordinal() - 1 + 4) % 4];
                break;
        }
    }
    // Move the robot 'steps' steps into the current direction it's facing
    void step(int steps) {
        int newX = x, newY = y;
        
        switch (direction){
            case NORTH:
                newY += steps;
                break;
            case SOUTH:
                newY -= steps;
                break;
            case EAST:
                newX += steps;
                break;
            case WEST:
                newX -= steps;
                break;
        }
        if (newX < 0 || newY < 0 || newX > size_x || newY > size_y) {
            throw new IllegalStateException("Robot stepped out of the grid at position: (" + x + "," + y + ")");
        }

        this.x = newX;
        this.y = newY;
        this.steps += steps;            
    }
    // stop the robot and end the program
    void stop(){
        System.out.println("Robot stopped at x=" + this.x + ", y=" + this.y);
        System.out.println("Total steps taken was: " + this.steps);
    }
    // Helper method for reportSteps to obtain the total amount of steps taken
    int getSteps() {
        return steps;
    }
}

// Grid class to initialize the grid for the robot
class Grid{
    Expression size_x;
    Expression size_y;

    public Grid(Expression size_x, Expression size_y) {
        this.size_x = size_x;
        this.size_y = size_y;
    }
}

// Abstract class for all statements in the grammar, the top node of the AST
abstract class Statement implements Robol {
    public abstract void interpret(Robot robot);
}

// Turn class to store rotation
class Turn extends Statement {
    private Rotation rotation;
    
    public Turn (Rotation rotation) {
        this.rotation = rotation;
    }
    // Interpret the rotation by calling the robot.turn method
    public void interpret(Robot robot) {
        robot.turn(rotation);
    }
}

// Step class to store steps to take
class Step extends Statement {
    private Expression steps;

    public Step(Expression steps) {
        this.steps = steps;
    }

    // Interpret the steps by calling the robot step method
    @Override
    public void interpret(Robot robot) {
        robot.step(steps.interpret());
    }
}

// Store identifier and operetion for adjusting a value
class Assignment extends Statement {
    private Identifier identifier;
    private Operation operation;

    public Assignment(Identifier identifier, Operation operation) {
        this.identifier = identifier;
        this.operation = operation;
    }
    // Interpret by incrementing or decrementing the value stored in the identifier 
    public void interpret(Robot robot) { 
        int currentVal = identifier.interpret();
        try{
            switch(operation) {
                case INCREMENT: currentVal++;
                case DECREMENT: currentVal--;
        }
        } catch (IllegalArgumentException e) {
            System.out.println("Unsupported operand: " + operation);
        }
        identifier.val = new Number(currentVal);
    }
}

// Loop class for repeating block statements, stores the list of statements and the condition as an expression
class Loop extends Statement {
    private List<Statement> statements;
    private Expression condition;

    public Loop(List<Statement> statements, Expression condition) {
        this.statements = statements;
        this.condition = condition;
    }
    // Interpret the loop by executing the body(statements) until the condition is true
    @Override
    public void interpret(Robot robot) { 
        do {
            for (Statement stmt : statements) {
                stmt.interpret(robot);
            }
        } while (condition.interpret() != 0);
    }
}

// Store the starting position
class Start {
    Expression x;
    Expression y;

    public Start(Expression x, Expression y) {
        this.x = x;
        this.y = y;
    }
}

// Stop the robot
class Stop extends Statement {
    public void interpret(Robot robot) {
        robot.stop();
    }
}
// Abstract class for all expressions
abstract class Expression {
    public abstract int interpret();
}
// Store ints as an extension of expression
class Number extends Expression{
    private int num;

    public Number(int num) {
        this.num = num;
    }
    // Interpret by returning the value of the number
    @Override
    public int interpret() {
        return num;
    }
}
// Store arithmetic expression
class ArithmeticExp extends Expression {
    private BinaryOp op;
    private Expression a;
    private Expression b;

    public ArithmeticExp(BinaryOp op, Expression a, Expression b) {
        this.op = op;
        this.a = a;
        this.b = b;
    }
    // Interpret the arithmetic exp and return the value generated from it
    @Override
    public int interpret() {
        // switch case for all binary operators
        switch(op){
            case ADD: return a.interpret() + b.interpret();
            case SUB: return a.interpret() - b.interpret();
            case MUL: return a.interpret() * b.interpret();
            case LESS: return (a.interpret() < b.interpret()) ? 1 : 0;
            case GREATER: return (a.interpret() > b.interpret()) ? 1 : 0;
            case EQ: return (a.interpret() == b.interpret()) ? 1 : 0;
            default: throw new IllegalArgumentException("Unsupported operator: " + op);
        }
    }
}
// Identifier to store variables with values
class Identifier extends Expression {
    // String name is never used, is it necessary for this implementation?
    private String name;
    protected Expression val;

    public Identifier(String name, Expression val) {
        this.name = name;
        this.val = val;
    }
    public int interpret() {
        return val.interpret();
    }
}

// Return the amount of steps taken to the user
class ReportSteps extends Statement {
    @Override
    public void interpret(Robot robot) {
        System.out.println("Number of steps taken: " + robot.getSteps());
    }
}

// Return log statements to the user
class Log extends Statement {
    private String message;

    public Log(String message) {
        this.message = message;
    }
    @Override
    public void interpret(Robot robot) {
        System.out.println(message);
    }
}
// This is also an extension of expression, 
// but BoolExp work the same way so we excluded the implementation of BoolExp
/*
abstract class BoolExp extends Expression {
    protected Expression left;
    protected Expression right;
}
*/