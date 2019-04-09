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
 - equipWeapon : boolean

#### Sword (extends Weapon)
Attacks:
 - Stinger : Ability
 - Sword Storm : Ability
 - Lightning Blade : Ability

#### Bow (extends Weapon)
Attacks:
 - Single Shot : Ability
 - Triple Shot : Ability
 - Critical Shot : Ability

#### Enchanted book (extends Weapon)
Attacks:
 - Thunder : Ability
 - Dark Bane : Ability
 - Life Drain : Ability

#### Wand (extends Weapon)
Attacks:
 - Fire Attack : Ability
 - Shattering Strike : Ability
 - Apocalypse : Ability

### Potion (extends Item)
Has attributes:

#### Health potions (extends Potion)
Has attributes:
 - recoveredHealth : int

Small Health potions
 Recover 15 points of health
Great Health potions
 Recover 50 points of health

#### Stamina potions (extends Potion)

### Armor (extends Item)
 - baseDefense : int

#### Necklace (extends Armor)

#### Shield (extends Armor)

#### Ring (extends Armor)

