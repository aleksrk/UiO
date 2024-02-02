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

(* Interpret the program *)
val _ = interpret program;
