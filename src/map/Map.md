# Map folder
## Map class
### Map generation algorithm
We've implemented an algorithm to randomly generate a map per level.

The following algorithm for generating dungeons is implemented in the
src.map.Map class's generateMap() method.

Dungeons are generated over several phases:
1. Create the entrance room
2. Randomly generate doors for that room
3. Create rooms at those doors
4. Add doors to the created rooms
5. Create rooms a the new doors
6. Repeat until there are no more rooms to create / doors open
7. Add boss room and store
8. Close unused doors
9. Delete unconnected rooms and check if the boss room has been deleted
10. Check if room count is more than the minimum numbers of rooms and if it still has a boss room, if it doesn't comply with both conditions, start again

## Room class
### Room generation algorithm
First, tiles fill the room
If the room is either an Easy or Hard room, enemies are generated.
If the room is a Treasure room, a Chest is created in the center.
Else, if the room is a Boss room, a Boss is spawned in the center.

### Room types
#### Initial
state = 1
Player is spawned in this room.

#### Easy
state = 2
From 2 to 4 enemies are spawned randomly.

#### Hard
state = 3
From 3 to 5 enemies are spawned randomly.

#### Treasure
state = 4

#### Boss Fight
state = 5

## Tile class
When created, chooses a random sprite with the first one having the most probability.
Stores either the enemy, boss or enemy.
