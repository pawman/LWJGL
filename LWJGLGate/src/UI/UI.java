package UI;


import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import static helpers.Artist.*;
public class UI {
	
	private ArrayList<Button> buttonList;
	
	public UI(){
		buttonList = new ArrayList<Button>();
	}
	
	public void addButton(String name, String textureName, int x, int y){
		buttonList.add(new Button(name, QuickLoad(textureName), x, y));
	}
	
	public boolean isButtonClicked(String buttonName){
		Button button = getButton(buttonName);
		float mouseY = HEIGHT - Mouse.getY() - 1;
		
		if(Mouse.getX() > button.getX() && Mouse.getX() < button.getX() + button.getWidth() && 
				mouseY > button.getY() && mouseY < button.getY() + button.getHeight()){
			return true;
		}
		return false;
	}
	
	private Button getButton(String buttonName){
		for (Button button : buttonList) {
			if(button.getName().equals(buttonName)){
				return button;
			}
		}
		return null;
	}
	
	public void draw(){
		for (Button button : buttonList) {
			DrawQuadTex(button.getTexture(), button.getX(), button.getY(), button.getWidth(), button.getHeight());
		}
	}
}
