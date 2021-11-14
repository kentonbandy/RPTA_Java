# RPTA
Role Playing Text Adventure
(in progress)

I've been wanting to make a text adventure since I learned to code in 2019, and I think I finally have the skills to
do it (fingers crossed). I'm going for some advanced TA mechanics with RPG style elements like battle mechanics and
shops, etc.

The short term goal is to make a game while writing loosely coupled, extendable code so that I can eventually (long term
goal) build an RPTA maker that works with the game engine I'm working on now.

AS OF 11/31/21:
- All game objects are built based on custom formatted text in a CSV file. I would like to move to JSON format in the future, and will revisit that when the engine is functioning.
- An Adventure Loop runs, displaying the current location description and prompting the user for input.
- All user input is case insensitive.
- The user can currently MOVE to an adjacent location (let's say to the west, in this example) by typing "move west", "travel west", or "w". The direction commands are currently hard-coded, but I would like to allow for custom direction commands in the future.
- The player can GET items, which places the item in the player's inventory and removes it from the Location item list, with the command "get {itemName}". The parser supports multi-word object names.
- The player can view their INVENTORY with commands "inventory" or simply "i".
- The "look" or "l" command is currently used for manual testing and prints out the Location item list. However, in the future the look command will repeat the location description.
- The player can EXAMINE an item by typing "examine {itemName}" or simply "x {itemName}". This outputs the item description.
- The player may choose to QUIT the application at any time by typing "quit" or "q". A y/n promt verifies the player's wish to quit.
