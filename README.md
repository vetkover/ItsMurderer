# About plugin
The plugin was created for **internal** use.
The main function is to display notifications to other players about the player's murder, changing the player's nickname in all visible places, displaying his status.

## LuckPerms commands permissions

| commands      | Usage                              | permission            |
| ------------- | ---------------------------------- |-----------------------|
| setmurder     | /setmurder {nickname} {sus/murder} | ismurderer.setmurder  |
| clearmurder   | /clearmurder {nickname}            | ismurderer.clearmurder|

## Configure config 
plugin/ItsMurderer/config.yaml

|parameter           |data type|Usage     |
|--------------------|---------|----------|
|defaultItsMurderer  | boolean |enter a boolean whether to immediately give the player the [murder] status after killing|
|punishMurderDuration| int     |value in seconds duration of show [murder] status|
|punishSusDuration   | int     |value in seconds duration of show [sus] status|
|spawnAreaProtect    | int     |value of zone coverage in blocks from the spawn center to notify all players about the murder near the spawn, show the coordinates and instantly give the killer the [murder] status.|
