## Build the project
Use Maven to clean and install all modules an dependencies from the root folder using the Maven command: <br>
`$ mvn clean install`

## Run the Game
Launch the game unsing the java command: <br>
`$ java --module-path mods-mvn --class-path "libs/*" --module=Core/dk.sdu.mmmi.cbse.main.Main` <br> <br>
The command sets the module path to `mods-mvn`, adds required libraries via `libs`folder, and start the `Main`class located in the `Core` component.
