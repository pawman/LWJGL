package helpers;

import org.lwjgl.Sys;

public class Clock {
	private static boolean paused = false;
	public static long lastFrame, totalTime;
	public static float d = 0, multiplayer = 1;

	public static long getTime(){
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
	
	public static float getDelta(){
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		if(delta * 0.001f > 0.03f){
			return 0.03f;
		}
		return delta * 0.001f;
	}
	
	public static float Delta(){
		if(paused){
			return 0;
		}else{
			return d * multiplayer;
		}
	}
	
	public static float TotalTime(){
		return totalTime;
	}
	
	public static float Multiplayer(){
		return multiplayer;
	}
	
	public static void update(){
		d = getDelta();
		totalTime += d;
	}
	
	public static void ChangeMultiplayer(float change){
		if(multiplayer + change < -1 && multiplayer + change > 7){
			multiplayer = change;
		}else{
			multiplayer += change;
		}
	}
	
	public static void Pause(){
		if(paused){
			paused = false;
		}else{
			paused = true;
		}
	}
}
