import java.util.ArrayList;

class TestCode {

    // Create the AST based on testing code 1
    // This code is just to help you understand how to create an AST
    void runProgram1() { 
        Grid grid = new Grid(new Number(64), new Number(64)); //size(64*64)
        Start start = new Start(new Number(23), new Number(30)); //start(23,30)
        ArrayList <Statement> statements = new ArrayList<>();
        statements.add(new Turn(Rotation.CLOCKWISE)); // turn clockwise
        statements.add(new Turn(Rotation.CLOCKWISE)); // turn clockwise
        statements.add(new Step(new Number(15))); // step 15
        statements.add(new Turn(Rotation.COUNTERCLOCKWISE)); // turn counterclockwise
        statements.add(new Step(new Number(15))); // step 15
        statements.add(new Turn(Rotation.COUNTERCLOCKWISE));// turn counterclockwise
        statements.add(new Step(new ArithmeticExp(BinaryOp.ADD,new Number(2),new Number(3)))); // step + 2 3
        statements.add(new Turn(Rotation.COUNTERCLOCKWISE));// turn counterclockwise
        statements.add(new Step(new ArithmeticExp(BinaryOp.ADD, new Number(17), new Number(20))));// step + 17 20
        statements.add(new Stop());// stop
        Robot robot = new Robot(start, statements, grid);
        Program program = new Program(grid, robot);

        // Run the interpreter
        program.interpret(robot);
    }

    // same as runProgram1 but with the AST based on the other example programs
    void runProgram2() {
        Grid grid = new Grid(new Number(64), new Number(64));
        Identifier i = new Identifier("i", new Number(5));
        Identifier j = new Identifier("j", new Number(3));
        Start start = new Start(new Number(23), new Number(6));
        ArrayList <Statement> statements = new ArrayList<>();

        statements.add(new Turn(Rotation.COUNTERCLOCKWISE));
        statements.add(new Step(new ArithmeticExp(BinaryOp.MUL, new Number(3), i)));
        statements.add(new Turn(Rotation.CLOCKWISE));
        statements.add(new Step(new Number(15)));
        statements.add(new Turn(Rotation.CLOCKWISE));
        statements.add(new Step(new ArithmeticExp(BinaryOp.SUB, new ArithmeticExp(BinaryOp.SUB, 
                                new Number(12), i), j)));
        statements.add(new Turn(Rotation.CLOCKWISE));
        statements.add(new Step(new ArithmeticExp(BinaryOp.ADD, new ArithmeticExp(BinaryOp.ADD, 
                                new ArithmeticExp(BinaryOp.MUL, new Number(2), i), 
                                new ArithmeticExp(BinaryOp.MUL, new Number(3), j)), 
                                new Number(1))));
        statements.add(new Stop());


        Robot robot = new Robot(start, statements, grid);
        Program program = new Program(grid, robot);
        program.interpret(robot);
    }
    void runProgram3() {
        Grid grid = new Grid(new Number(64), new Number(64));
        Identifier i = new Identifier("i", new Number(5));
        Identifier j = new Identifier("j", new Number(3));
        Start start = new Start(new Number(23), new Number(6));
        ArrayList <Statement> statements = new ArrayList<>();
        statements.add(new Turn(Rotation.COUNTERCLOCKWISE));
        statements.add(new Step(new ArithmeticExp(BinaryOp.MUL, new Number(3), i)));
        statements.add(new Turn(Rotation.COUNTERCLOCKWISE));
        statements.add(new Step(new Number(15)));
        statements.add(new Turn(Rotation.CLOCKWISE));
        statements.add(new Turn(Rotation.CLOCKWISE));
        statements.add(new Step(new Number(4)));
        statements.add(new Turn(Rotation.CLOCKWISE));
        
        ArrayList<Statement> loopStatements = new ArrayList<>();
        loopStatements.add(new Step(j));
        loopStatements.add(new Assignment(j, Operation.DECREMENT));
        statements.add(new Loop(loopStatements, new ArithmeticExp(BinaryOp.GREATER, j, new Number(1))));
        statements.add(new Stop());

        Robot robot = new Robot(start, statements, grid);
        Program program = new Program(grid, robot);
        program.interpret(robot);

        // Result should be (12,16)
    }
    void runProgram4() {
        Grid grid = new Grid(new Number(64), new Number(64));
        Identifier i = new Identifier("i", new Number(8));
        Start start = new Start(new Number(1), new Number(1));
        ArrayList <Statement> statements = new ArrayList<>();
        ArrayList <Statement> loopStatements = new ArrayList<>();

        loopStatements.add(new Step(i));
        statements.add(new Loop(loopStatements, new ArithmeticExp(BinaryOp.LESS, i, new Number(100))));
        statements.add(new Stop());

        Robot robot = new Robot(start, statements, grid);
        Program program = new Program(grid, robot);
        program.interpret(robot);


    }
    void runProgram5() {
        Grid grid = new Grid(new Number(64), new Number(64));
        Start start = new Start(new Number(23), new Number(30));
        ArrayList <Statement> statements = new ArrayList<>();

        statements.add(new Turn(Rotation.CLOCKWISE));
        statements.add(new Turn(Rotation.CLOCKWISE));
        statements.add(new Step(new Number(15)));
        statements.add(new Turn(Rotation.COUNTERCLOCKWISE));
        statements.add(new Step(new Number(15)));
        statements.add(new ReportSteps());
        statements.add(new Stop());

        Robot robot = new Robot(start, statements, grid);
        Program program = new Program(grid, robot);
        program.interpret(robot);
    }
    void runProgram6() {

    }
    void runAll() {
        runProgram1();
        runProgram2();
        runProgram3();
        runProgram4();
        runProgram5();
        runProgram6();
    }
}