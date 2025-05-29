## Build the project
Use Maven to clean and install all modules an dependencies from the root folder using the Maven command:
`$mvn clean install`

## Run the Game
Launch the game unsing the java command:
`java --module-path mods-mvn --class-path "libs/*" --module=Core/dk.sdu.mmmi.cbse.main.Main`
The command sets the module path to `mods-mvn`, adds required libraries via `libs`folder, and start the `Main`class located in the `Core` component.
