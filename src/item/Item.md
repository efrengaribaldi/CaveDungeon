# Items folder
## Inventory's class
Has attributes:
 - items : Items[]

Has methods:
 - addItemInventory(item : Item, index : int) : void
 - removeItemInventory(index : int) : void

## Item's class
Has attributes:
 - name : String

### Weapon (extends Item)
Has attributes:
 - baseDamage : int

#### Sword (extends Weapon)
Attacks:
 - dashAttack : int
 - stormOfSword : int

#### Bow (extends Weapon)
Attacks:
 - shot : int
 - tripleShot : int

#### Magic book (extends Weapon)
Attacks:
 - wishOfMoon : int
 - lyckaHimlen : int

#### Magic staff (extends Weapon)
Attacks:
 - baneOfDarkness : int
 - baneOfLight : int

### Potion (extends Item)
Has attributes:

#### Health potions (extends Potion)
Has attributes:
 - recoveredHealth : int

Small Health potions
 Recover 15 points of health
Great Health potions
 Recover 50 points of health

#### Mana potions (extends Potion)
Only affects mages

#### Shield potions (extends Potion)
