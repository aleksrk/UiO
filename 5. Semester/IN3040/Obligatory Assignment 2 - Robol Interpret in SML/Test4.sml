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

(* Interpret the program *)
val _ = interpret program;
