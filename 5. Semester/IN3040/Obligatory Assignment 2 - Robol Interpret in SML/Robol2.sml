(* Data Types *)
datatype Direction = CW | CCW
datatype FacingDirection = NORTH | SOUTH | WEST | EAST
datatype Assignment = INC | DECR
datatype BinaryOperation = ADD | SUB | MULT | LESS | GREATER | EQUAL

datatype Expression =
    IdentifierExpression of string
  | NumberExpression of int
  | ArithmeticExpression of BinaryOperation * Expression * Expression
  | BooleanExpression of BinaryOperation * Expression * Expression

type Identifier = string

datatype Binding = Binding of Identifier * Expression

type AssocList = Binding list

datatype Statement =
    Stop
  | Turn of Direction
  | Step of Expression
  | Assignment of Assignment * Identifier
  | Loop of Statement list * Expression

datatype Start = Start of Expression * Expression

datatype Robot = Robot of Start * Statement list * FacingDirection * AssocList
datatype Grid = Size of int * int
datatype Program = Program of Grid * Robot


(* Functions *)

fun lookup _ [] = 0
  | lookup key ((Binding (keyx, valx))::xs) =
    if key = keyx
    then eval valx ((Binding (keyx, valx))::xs)
    else lookup key xs

and add string int [] = [Binding (string, NumberExpression int)]
  | add string int (x::xs) = add string int xs

and change idToChange valToSet list =
    List.map (fn (Binding (id, val)) =>
      if id = idToChange
      then Binding (id, NumberExpression valToSet)
      else Binding (id, val)) list

and eval (IdentifierExpression str) bindings = lookup str bindings
  | eval (NumberExpression number) bindings = number
  | eval (ArithmeticExpression (op, l, r)) bindings =
    let
      val left = eval l bindings
      val right = eval r bindings
    in
      (case op of
           ADD => left + right
         | SUB => left - right
         | MULT => left * right
         | LESS => if left < right then 1 else 0
         | GREATER => if left > right then 1 else 0
         | EQUAL => if left = right then 1 else 0)
    end
  | eval (BooleanExpression (op, l, r)) bindings =
    let
      val left = eval l bindings
      val right = eval r bindings
    in
      (case op of
           ADD => left + right
         | SUB => left - right
         | MULT => left * right
         | LESS => if left < right then 1 else 0
         | GREATER => if left > right then 1 else 0
         | EQUAL => if left = right then 1 else 0)
    end

(* Helper Functions *)

fun errorOutOfBounds (Program (grid, Robot (pos, _, _, _))) = 
  raise Fail ("Robot at position " ^ Int.toString pos ^ " out of bounds in grid " ^ Int.toString grid)

(* Interpretation Functions *)

fun interpretStatement (Robot (start, Stop::xs, dir, list), program) = Robot (start, [], dir, list)
  | interpretStatement (Robot (start, (Turn newDir)::xs, dir, list), program) = 
    (case newDir of
         CW => 
           (case dir of
                NORTH => Robot (start, xs, EAST, list)
              | SOUTH => Robot (start, xs, WEST, list)
              | WEST => Robot (start, xs, NORTH, list)
              | EAST => Robot (start, xs, SOUTH, list))
       | CCW => 
           (case dir of
                NORTH => Robot (start, xs, WEST, list)
              | SOUTH => Robot (start, xs, EAST, list)
              | WEST => Robot (start, xs, SOUTH, list)
              | EAST => Robot (start, xs, NORTH, list))
    ) |> interpretStatement (_, program)
  | interpretStatement (Robot (Start (startx, starty), (Step numSteps)::xs, dir, list), Program (Size (sizeX, sizeY), r)) =
    let
      val stepCount = eval numSteps list
      val newPosition = 
        (case dir of
             NORTH -> Start (startx, ArithmeticExpression (ADD, starty, NumberExpression stepCount))
           | SOUTH -> Start (startx, ArithmeticExpression (SUB, starty, NumberExpression stepCount))
           | WEST -> Start (ArithmeticExpression (SUB, startx, NumberExpression stepCount), starty)
           | EAST -> Start (ArithmeticExpression (ADD, startx, NumberExpression stepCount), starty))
      val newRobot = Robot (newPosition, xs, dir, list)
    in
      if stepCount < 0 orelse stepCount > sizeY then errorOutOfBounds (Program (Size (sizeX, sizeY), newRobot))
      else interpretStatement (newRobot, Program (Size (sizeX, sizeY), r))
    end
  | interpretStatement (Robot (start, (Assignment (assign, key))::xs, dir, list), program) =
    let
      val val = lookup key list
      val newList = 
        (case assign of
             INC -> change key (val + 1) list
           | DECR -> change key (val - 1) list)
    in
      interpretStatement (Robot (start, xs, dir, newList), program)
    end
  | interpretStatement (Robot (start, (Loop (statements, While condition))::xs, dir, list), program) =
    if eval condition list = 1 then
      let
        val newRobot = interpretStatement (Robot (start, statements, dir, list), program)
      in
        interpretStatement (Robot (start, Loop (statements, While condition)::xs, dir, list), program)
      end
    else interpretStatement (Robot (start, xs, dir, list), program)
  | interpretStatement (robot, _) = robot

(* Main Function and Test Cases *)

fun testCode 1 =
    let
      val grid1 = Size (64, 64)
      val start1 = Start (NumberExpression 23, NumberExpression 30)
      val statements1 = [(* ... *)]
      val robot1 = Robot (start1, statements1, EAST, [])
      val program1 = Program (grid1, robot1)
    in
      interpret program1
    end

(* Other test cases follow similar structure... *)

fun main () =
    let
      val tc1 = testCode 1
      val tc2 = testCode 2
      val tc3 = testCode 3
      val tc4 = testCode 4
    in
      print ("Test code 1: " ^ Int.toString tc1 ^ "\n")
      (* Print statements for other test cases... *)
    end
