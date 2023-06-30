# Challenges
Fabric minecraft mod to track a set challenge for multiple players.

# Commands

### `/challenges settings`

View current challenge settings.

***

### `/challenges settings category [category] [challenge]`

- category: `string` Select one of the 10 categories: Miscellaneous, Inventory, Mined, Broken, Crafted, Used, Picked_Up, Dropped, Killed, Killed_By
- challenge: `string` Choose the item, block, or entity for the challenge corresponding to the selected category.

Set the challenge type to play.

***

### `/challenges settings category random`

Picks a random possible challenge from the various categories.

***

### `/challenges settings timer [minutes]`

- minutes: `integer` Specify the time limit for the game. Setting to 0 means no time limit.

Set a time limit in minutes for the game. Set to 0 minutes for no timer.

***

### `/challenges settings effects add [effect] [duration] [ambient] [particles] [icon] [respawn]`

- effect: `status effect` Specify a minecraft status effect to apply.
- duration: `integer` Specify the length in seconds the effect will apply for.
- amplifier: `integer` Specify the strength between 0-255 that the effect will have.
- ambient: `boolean` Particles are less visible if set to true.
- particles: `boolean` Sets whether particles are shown or not.
- icon: `boolean` Sets whether the status effect icon is shown or not.
- respawn: `boolean` Sets whether to give the effect on respawning or just at initial spawn.

Add a status effect to play the game with.

***

### `/challenges effects remove [effect]`

- effect: `status effect` Specify a minecraft status effect to remove.

Remove a status effect from those that would apply when starting a game.

***

### `/challenges effects clear`

Remove all status effects that would apply when starting a game.

***

### `/challenges settings equipment add [item] [amount] [respawn] [equip]`

- item: `item` Set a minecraft item to give.
- amount: `integer` Set how many of the item to give.
- respawn: `boolean` Set whether to give the item on respawn or just on initial spawn.
- equip: `boolean` Set whether to auto equip the item. Mostly useful with automatically equiping armor on game start.

Add starting equipment to begin the game with. Enchantments can be added but currently this must be done as part of a preset config file.

***

### `/challenges settings equipment remove [item]`

- item: `item` Set a minecraft item to remove.

Remove an item from the list of starting equipment to give at game start.

***

### `/challenges settings equipment clear`

Remove all items from the list of starting equipment to give at game start.

***

### `/challenges settings equipment stone | iron | diamond | food`

Presets to make changing tools and giving bread easier than manually changing the equipment an item at a time.

***

### `/challenges pvp [enabled]`

- enabled: `boolean` Set whether pvp is enabled or disabled.

Enable or disable pvp for the server.

***

### `/challenges keepInventory [enabled]`

- enabled: `boolean` Set whether keep inventory is enabled or disabled.

Enable or disable keep inventory for the server.

***

### `/challenges settings dimension [dimension]`

- dimension: `string` Specify the dimension to start the game in. This list corresponds to the ChallengesDimensions setting in the challenges.json config file.

Set the dimension the game will start in.

***

### `/challenges start`

Start a challenge based off the current settings.

***

### `/challenges end`

Manually end a challenge and teleport players back to hub.
