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
state = 1

Player is spawned in this room.

#### Easy
state = 2

#### Hard
state = 3

#### Treasure
state = 4

#### Boss Fight
state = 5

#### Store
state = 6

### Room generation algorithm
TO DO

## Tile class
### Types
1. Floor
2. Wall
3. Door
4. Chesta
