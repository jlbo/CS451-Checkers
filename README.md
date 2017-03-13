CS-451 Group Members: Lukasz Woch, Mark Cohen, Joel Borneman, Nick Brady
Draught Kings README
Last Updated: 3/12/17



The game Draught Kings will be contained within a JAR file, which the users will be able to download from our Github repository.
This file will then be placed into a folder of the user's choosing and can be run through the commandline by passing it specific arguements. The host game must be started before the client can connect.

The command "java -jar ./DraughtsKingsCR01.jar" will run the game as a host.
The command "java -jar ./DraughtsKingsCR01.jar <IP>" will run the game as a client with connects to the <IP> of the host.

Upon starting the game the game will be initialized as either a host or a client.
Then the gameboard will appear and populate with checkers pieces.

The host is always red, and the client is always black. At this point the match may begin.
Moves are made when a player uses their mouse to select one of their pieces, and then clicks again to a valid move location.
The match ends and both users' board windows will close when one player's pieces are all captured.
If there are pieces still on the board but no moves can be made (as in the case of checker pieces reaching the opposite end of the board),
at this time the players will have to manually close the game windows and quit the game.
