# Characters folder
## Character's class
Has attributes:
 - nombre : String
 - hp : int
 - xp : int
 - basicAttack() : void

## Player's class (extends Character)
Has attributes:
 - items : Inventory

### Melee (extends Player)
 - Has stronger attacks
 - 

When created, it has:
 - sword : Sword (Stored inside items)
 - 

### Mage (extends Player)
 - Can debuff enemies
 - Can hit many enemies at a time
 - Limited by mana

When created, it has:
 - book : MagicBook (Stored inside items)

## NPC's class (extends Character)
### Shopkeeper (extends NPC)

### Enemies (extends NPC)

### Boss (extends NPC)


## Ability's class
Each character will have three abilities

### Melee's abilities
 - 

### Mage's abilities
 - 
