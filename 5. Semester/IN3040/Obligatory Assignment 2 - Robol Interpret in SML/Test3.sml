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

(* Interpret the program *)
val _ = interpret program;
