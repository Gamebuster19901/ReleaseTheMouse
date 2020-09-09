package com.gamebuster19901.releasethemouse;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;

public class MouseReleaseManager {

	private static final Thread MAIN_THREAD;
	private static List<StackTraceElement> lastStackTrace; //Unfortunetly, Thread.getState() is not updated if a breakpoint is hit, so we must analyze the stacktrace
	private static int suspend_time = 0;
	
	static {
		Thread mainThread = null;
		for(Thread thread : Thread.getAllStackTraces().keySet()) {
			if(thread.getName().equals("Render thread")) {
				mainThread = thread;
				break;
			}
		}
		
		if(mainThread == null) {
			throw new AssertionError("Could not find the client thread!");
		}
		
		MAIN_THREAD = mainThread;
		lastStackTrace = Arrays.asList(MAIN_THREAD.getStackTrace());
	}
	
	private MouseReleaseManager(Minecraft minecraft) {
		Thread mouseManagerThread = new Thread() {
			@Override
			public void run() {
				while(MAIN_THREAD.isAlive()) {
					List<StackTraceElement> stacktrace = Arrays.asList(MAIN_THREAD.getStackTrace());

					if(stacktrace.equals(lastStackTrace)) {
						suspend_time++;
					}
					else {
						lastStackTrace = stacktrace;
						suspend_time = 0;
					}
					
					if(suspend_time > 200) {
						releaseMouse(minecraft);
					}
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						break;
					}
				}
			}
		};
		
		mouseManagerThread.setPriority(4);
		mouseManagerThread.setName("Mouse Releaser");
		mouseManagerThread.setDaemon(true);
		
		mouseManagerThread.start();
	}
	
	public static void init() {
		new MouseReleaseManager(Minecraft.getInstance());
	}
	
	private void releaseMouse(Minecraft minecraft) {
		GLFW.glfwSetInputMode(minecraft.mainWindow.getHandle(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
	}
	
}
