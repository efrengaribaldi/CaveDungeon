# Characters folder
## Character's class abstract
Has attributes:
 - name : String
 - healthPoints : int

### Player's class abstract (extends Character)
Has attributes:
 - items : Inventory
 - experience : int
 - sex : int
 - abilities : Ability[]

Has methods:
 Methods in map:
  - openInventory() : Inventory
  - closeInventory() : void
  - startBattle() : void

 Methods in battle:
  Non abstract methods:
   - useItem() : void
  Abstract methods:
   - useAbility() : void
   - attack(enemy : NPC) : void

#### Melee (extends Player)
 - Ability  
  - Can hit with %15 extra damage
  - Can hit a critical attack (with probability)
  - Can use counter (return the attack)

- Weapon
 - Sword
 - Bow

When created, it has:
 - sword : Sword (Stored inside items)

#### Mage (extends Player)
 - Ability
  - Can debuff enemies
  - Can hit many enemies at a time
  - Can create a magic shield

 - Weapon
  - EnchantedBook
  - Wand

### NPC's class abstract (extends Character)
Has attributes:
 - ability : Ability

Has methods:
  Methods in battle:
   Non abstract methods:
    - basicAttack(enemy : Player) : void
   Abstract methods:
    - useAbility() : void

#### Shopkeeper (extends NPC)

Has methods:
  Methods in battle:
   - useAbilityShopkeeper() : void

#### Enemies (extends NPC)

Has methods:
  Methods in battle:
   - useAbilityEnemies() : void

#### Boss (extends NPC)

Has methods:
  Methods in battle:
   - useAbilityBoss() : void
   - specialAttackBoss(enemy : Player) : void

## Ability's class
Each character will have three abilities

### Melee's abilities
 -

### Mage's abilities
 -
