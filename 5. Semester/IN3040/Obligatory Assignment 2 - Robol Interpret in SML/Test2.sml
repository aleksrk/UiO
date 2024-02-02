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

(* Interpret the program *)
val _ = interpret program;
