# Map folder
## Map class
### Map generation algorithm
We've implemented an algorithm to randomly generate a map per level.

The following algorithm for generating dungeons is implemented in the
src.map.Map class's generateMap() method.

Dungeons are generated over several phases:
1. Create the entrance room
2. Randomly generate doors
3. Create rooms at those doors

## Room class
### Room types
#### Initial
State = 1

#### Easy
State = 2

#### Hard
State = 3

#### Treasure
State = 4

#### Boss Fight
State = 5

### Room generation algorithm
TO DO

## Tile class
### Types
1. Floor
2. Wall
