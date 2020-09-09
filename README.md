# ReleaseTheMouse
A minecraft mod which fixes a bug in GLFW where it won't release the mouse if the client thread hits a breakpoint or becomes unresponsive, causing some operating systems to become softlocked.

## How to use in Eclipse
If you wish to have this mod installed while you're developing your own mod, you have to add this mod to the classpath at runtime. You can do this easily by modifying the debug configuration used to launch your mod.

1. Create a new Java project in eclipse and git pull this repository.
2. run `./gradlew genEclipseRuns` or `./gradlew eclipse` in the ReleaseTheMouse project you just created
3. Go to [Debug Configurations] and select the debug configuration for ReleaseTheMouse
4. Go to [Environment] and copy the value in `MOD_CLASSES` to your clipboard
5. Go to [Debug Configurations] and select the debug configuration for the mod you are developing
6. Go to [Environment] and at the very end of the value in `MOD_CLASSES`, type a colon (`:`) and then paste your clipboard.
7. Apply Changes
8. If you run the debug configuration for your mod, you should see that ReleaseTheMouse listed under Mods in the main menu.
