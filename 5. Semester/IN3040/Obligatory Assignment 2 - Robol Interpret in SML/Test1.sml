(* Use the interpreter implementation *)
use "Robol.sml";

structure Test1 = struct
    fun runTest() = 
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
            (* Combine grid and robot into a program *)
            val program = Program(grid, Robot([], startExp, statements))
        in
            (* Interpret the program *)
            interpret program
        end
end;
