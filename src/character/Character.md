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

 - Ability  
  - Can hit with %15 extra damage
  - Can hit a critical attack (with probability)
  - Can use counter (return the attack)

- Weapon
 - sword
 - bow

When created, it has:
 - sword : Sword (Stored inside items)

### Mage (extends Player)
 - Ability
  - Can debuff enemies
  - Can hit many enemies at a time
  -

 - Weapon
  - book
  - staff

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
