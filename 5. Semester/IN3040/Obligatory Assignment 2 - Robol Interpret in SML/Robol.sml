exception OutOfBounds;
exception InvalidOperation;
exception UndefinedVariable;

(* Set all datatypes necessary for the interpreter *)
datatype rotation = CW | CCW;
datatype direction = NORTH | EAST | SOUTH | WEST;
datatype adjustOp = INC | DECR;
datatype grid = Size of int * int;

datatype exp =
    BiggerThan of exp * exp
  | LessThan of exp * exp
  | Identifier of string
  | Num of int
  | Add of exp * exp
  | Equals of exp * exp
  | Subtract of exp * exp
  | Multiply of exp * exp;

datatype stmt =
    Stop
  | Turn of rotation
  | Step of exp
  | ReportSteps
  | Assignment of string * adjustOp
  | Loop of stmt list * exp;

datatype start = Start of exp * exp;
type vardecl = string * exp;

datatype robot = Robot of vardecl list * start * stmt list;
datatype program = Program of grid * robot;

type assocList = (string * int) list;
type position = int * int;
type state = {pos: position, dir: direction, decls: assocList, steps: int};

(*
 * Function: lookup
 * -----------------
 * Looks up the value associated with a given key in an associative list.
 *
 * key: The key for which to find the value.
 * list: The associative list to search.
 *
 * returns: SOME(value) if the key is found, otherwise NONE.
 *)
fun lookup (key:string) (list:assocList) = 
    List.foldr (fn ((k, v), acc) => if k = key then SOME v else acc) NONE list;

(* Helper function to add value in list *)
fun add (key:string) (value:int) (list:assocList) = 
    (key, value) :: list;

(* Helper function to change value in list *)
fun change (key:string) (value:int) (list:assocList) = 
    List.map (fn (k, v) => if k = key then (k, value) else (k, v));

(*
 * Function: evalExp
 * -----------------
 * Evaluates an expression based on the current state of variable declarations.
 *
 * decls: The current state of variable declarations.
 * exp: The expression to be evaluated.
 *
 * returns: The integer result of the evaluated expression.
 * raises: UndefinedVariable if an identifier is not found in the declarations.
 *)
fun evalExp decls (Num n) = n
  | evalExp decls (Identifier id) =
      (case lookup id decls of
           SOME value => value
         | NONE => raise UndefinedVariable)
  | evalExp decls (BiggerThan (e1, e2)) =
      let val eval = evalExp decls in
          if eval e1 > eval e2 then 1 else 0
      end
  | evalExp decls (LessThan (e1, e2)) =
      let val eval = evalExp decls in
          if eval e1 < eval e2 then 1 else 0
      end
  | evalExp decls (Add (e1, e2)) =
      let val eval = evalExp decls in
          eval e1 + eval e2
      end
  | evalExp decls (Subtract (e1, e2)) =
      let val eval = evalExp decls in
          eval e1 - eval e2
      end
  | evalExp decls (Multiply (e1, e2)) =
      let val eval = evalExp decls in
          eval e1 * eval e2
      end
  | evalExp decls (Equals (e1, e2)) =
      let val eval = evalExp decls in
          if eval e1 = eval e2 then 1 else 0
      end

(*
 * Function: rotate
 * ----------------
 * Determines the new direction of the robot after a rotation.
 *
 * dir: The current direction of the robot.
 * r: The rotation to be applied (CW for clockwise, CCW for counterclockwise).
 *
 * returns: The new direction after applying the rotation.
 *)
fun rotate (dir: direction, r: rotation): direction =
    case (dir, r) of
        (NORTH, CW) => EAST
      | (EAST, CW) => SOUTH
      | (SOUTH, CW) => WEST
      | (WEST, CW) => NORTH
      | (NORTH, CCW) => WEST
      | (WEST, CCW) => SOUTH
      | (SOUTH, CCW) => EAST
      | (EAST, CCW) => NORTH

(*
 * Function: step
 * --------------
 * Calculates the new position of the robot after moving a number of steps in the current direction.
 *
 * pos: The current position of the robot.
 * dir: The current direction of the robot.
 * steps: The number of steps to move.
 * grid: The grid on which the robot is moving, defining its boundaries.
 *
 * returns: The new position after moving.
 * raises: OutOfBounds if the movement goes beyond the grid boundaries.
 *)
fun step (pos: position, dir: direction, steps: int, grid: grid): position =
    let
        val (x, y) = pos
        val (Size(gx, gy)) = grid
    in
        case dir of
            NORTH => if y + steps <= gy then (x, y + steps) else raise OutOfBounds
          | SOUTH => if y - steps >= 0 then (x, y - steps) else raise OutOfBounds
          | EAST => if x + steps <= gx then (x + steps, y) else raise OutOfBounds
          | WEST => if x - steps >= 0 then (x - steps, y) else raise OutOfBounds
    end

(*
 * Function: interpretStmt
 * -----------------------
 * Interprets and executes a single statement, updating the robot's state.
 *
 * stmt: The statement to be interpreted.
 * state: The current state of the robot.
 * grid: The grid on which the robot is operating.
 *
 * returns: The updated state of the robot after executing the statement.
 *)
fun interpretStmt (stmt:stmt, state:state, grid:grid): state =
    case stmt of 
      Stop => state
    | Turn r =>
        {pos = #pos state, dir = rotate(#dir state,r), decls = #decls state, steps = #steps state}
    | Step e =>
        let
            val steps = evalExp(#decls state) e
        in
            {pos = step(#pos state, #dir state, steps, grid), dir = #dir state, decls = #decls state, steps = #steps state + steps}
        end
    | ReportSteps =>
          (print("Total Steps Taken: " ^ Int.toString (#steps state) ^ "\n");
           state)
    | Assignment (key, adjustOp) =>
        let
            val currentVal = case lookup key (#decls state) of
                                SOME v => v
                            | NONE => raise UndefinedVariable
            val newDecls = case adjustOp of
                            INC => change key (currentVal + 1) (#decls state)
                            | DECR => change key (currentVal - 1) (#decls state)
        in
            {decls = #decls state, dir = #dir state, pos = #pos state, steps = #steps state}
            (* So this does not currently return newDecls. An error is thrown when attempting to return it as if newDecls and #decls state isn't the same type I think?
            I've tried forever, but cannot figure out exactly what is wrong and why it behaves like this. 
            The program runs by just returning the state of the list from before, but currently does not update any values with the assignment ++ or -- operators*)
        end
    | Loop (stmts, condition) =>
          let
              fun loop (state, stmts) =
                if evalExp (#decls state) condition <> 0
                then 
                  let
                    val newState = List.foldl (fn (s, st) => interpretStmt (s, st, grid)) state stmts
                  in
                    loop (newState, stmts)
                  end
                else state
          in
              loop (state, stmts)
          end

(*
 * Function: interpret
 * -------------------
 * Interprets and executes a program, simulating the robot's actions on the grid.
 *
 * program: The program to be executed, including the grid and robot configurations.
 *
 * returns: (void) Outputs the final position and total steps taken by the robot.
 *)
fun interpret (Program(grid, Robot(decls, Start(sx, sy), statements))) =
    let
        val initialDecls = List.foldl 
          (fn ((key, e), d) => add key (evalExp d e) d) [] decls

        val initialState = {
            pos = (evalExp initialDecls sx, evalExp initialDecls sy),
            dir = EAST, (* Default location *)
            decls = initialDecls,
            steps = 0
        }

        fun interpretProgram (state, []) = state
          | interpretProgram (state, stmt::rest) =
                interpretProgram (interpretStmt(stmt, state, grid), rest)

        val finalState = interpretProgram (initialState, statements)
    in
        (* Print the final position and total steps *)
        let
            val (x, y) = #pos finalState
            val steps = #steps finalState
        in
            print("Final Position: (" ^ Int.toString x ^ ", " ^ Int.toString y ^ ")\n");
            print("Total Steps Taken: " ^ Int.toString steps ^ "\n")
        end
    end;
