(* Import the Robol interpreter implementation *)
use "Robol.sml";

(* Define your test cases as separate functions *)
fun test1 () =
    let
        (* Define the grid *)
        val grid = Size(64, 64);

        (* Define the starting position *)
        val startExp = Start(Num 23, Num 30);
        (* Define the list of statements in the program *)
        val statements = [
            Turn CW,
            Turn CW,
            Step (Num 15),
            Turn CCW,
            Step (Num 15),
            Turn CCW,
            Step (Add (Num 2, Num 3)),
            Turn CCW,
            Step (Add (Num 17, Num 20)),
            Stop
        ];
        (* Combine grid, variables and robot in*)
        val program = Program(grid, Robot([], startExp, statements));
    in
        (* Interpret the program *)
        interpret program
    end

fun test2 () =
    let
        (* Define the grid *)
        val grid = Size(64, 64);

        (* Define variable declarations *)
        val varDecls = [("i", Num 5), ("j", Num 3)];

        (* Define the starting position *)
        val startExp = Start(Num 23, Num 6);

        (* Define the list of statements in the program *)
        val statements = [
            Turn CCW,
            Step (Multiply (Num 3, Identifier "i")),
            Turn CW,
            Step (Num 15),
            Turn CW,
            Step (Subtract (Num 12, Add (Identifier "i", Identifier "j"))),
            Turn CW,
            Step (Add (Add (Multiply (Num 2, Identifier "i"), Multiply (Num 3, Identifier "j")), Num 1)),
            Stop
        ];
        (* Combine grid, variables, and robot into a program *)
        val program = Program(grid, Robot(varDecls, startExp, statements));
    in
        (* Interpret the program *)
        interpret program
    end;

fun test3 () =
    let
        (* Define the grid *)
        val grid = Size(64, 64);

        (* Define variable declarations *)
        val varDecls = [("i", Num 5), ("j", Num 3)];

        (* Define the starting position *)
        val startExp = Start(Num 23, Num 6);

        (* Define the list of statements in the program *)
        val statements = [
            Turn CCW,                    (* Turns to face North *)
            Step (Multiply (Num 3, Identifier "i")),   (* Steps North: 3 * 5 *)
            Turn CCW,                    (* Turns to face West *)
            Step (Num 15),               (* Steps West: 15 *)
            Turn CW,                     (* Turns to face North *)
            Turn CW,                     (* Turns to face South *)
            Step (Num 4),                (* Steps South: 4 *)
            Turn CW,                     (* Turns to face West *)
            Loop([                       (* Loop starts *)
                Step (Identifier "j"),   (* Steps West: value of j *)
                Assignment("j", DECR)    (* Decrements j *)
            ], BiggerThan(Identifier "j", Num 1)), (* Loop condition: j > 1 *)
            Stop
        ];

        (* Combine grid, variables, and robot into a program *)
        val program = Program(grid, Robot(varDecls, startExp, statements));
    in
        (* Interpret the program *)
        interpret program
    end;

fun test4 () =
    let
        (* Define the grid *)
        val grid = Size(64, 64);

        (* Define variable declarations *)
        val varDecls = [("i", Num 8)];  (* i initialized to 8 *)

        (* Define the starting position *)
        val startExp = Start(Num 1, Num 1);

        (* Define the list of statements in the program *)
        val statements = [
            Loop([                        (* Loop starts *)
                Step (Identifier "i"),    (* Steps: value of i *)
                Assignment("i", INC)      (* Increments i *)
            ], LessThan(Identifier "i", Num 100)), (* Loop condition: i < 100 *)
            Stop
        ];

        (* Combine grid, variables, and robot into a program *)
        val program = Program(grid, Robot(varDecls, startExp, statements));
    in
        (* Interpret the program *)
        interpret program       
    end;

fun test5 () =
    let
        (* Define the grid *)
        val grid = Size(64, 64);

        (* Define the starting position *)
        val startExp = Start(Num 23, Num 30);

        (* Define the list of statements in the program *)
        val statements = [
            Turn CW,           (* Turns clockwise *)
            Turn CW,           (* Turns clockwise again, facing South *)
            Step (Num 15),     (* Steps 15 units South *)
            Turn CCW,          (* Turns counterclockwise, facing East *)
            Step (Num 15),     (* Steps 15 units East *)
            ReportSteps,       (* Reports the total steps taken *)
            Stop               (* Stops the program *)
        ];

        (* Combine grid and robot into a program *)
        val program = Program(grid, Robot([], startExp, statements));
    in
        (* Interpret the program *)
        interpret program
    end;

(* Main function to run a specified test *)
fun main () =
    let
        val args = CommandLine.arguments()
        fun runTest "1" = test1 ()
          | runTest "2" = test2 ()
          | runTest "3" = test3 ()
          | runTest "4" = test4 ()
          | runTest "5" = test5 ()
          | runTest _ = print "Invalid argument. Please use 1, 2, 3, 4, or 5.\n"
    in
        if List.length args > 0 then
            runTest (List.nth(args, 0))
        else
            print "No argument provided.\n"
    end;

(* Call the main function *)
val _ = main ();
