package io.github.jakethe28th.game;

import io.github.jakethe28th.engine.graphics.Renderer;
import io.github.jakethe28th.engine.graphics.Sprite;
import io.github.jakethe28th.engine.graphics.Window;
import io.github.jakethe28th.engine.graphics.gui.Element;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

import javax.imageio.ImageIO;

import org.lwjgl.glfw.*;

import java.io.File;

public class compileBlocks {
	public static void main() throws Exception {
		
		System.out.println("Initializing...");
		
		int window_size = 256;
		Window window = new Window(window_size, window_size, "ItemSheetCompiler");
		Renderer render = new Renderer();
		
		
		Element gui = new Element();
		gui.set("x_size", new Element());
		gui.set("y_size", new Element());
		gui.set("texture_resolution", new Element());
		//gui.set("directory", new Element());
		gui.set("compile", new Element());
		gui.set("decompile", new Element());
		
		gui.setProperty("x1", "0");
		gui.setProperty("y1", "0");
		gui.setProperty("x2", "" + window_size);
		gui.setProperty("y2", "" + window_size);
		
		gui.setProperty("type", "collection");
		
		gui.get("compile").setProperty("align", "left");
		gui.get("compile").setProperty("string", "Compile");
		gui.get("compile").setProperty("type", "button");
		
		gui.get("decompile").setProperty("align", "left");
		gui.get("decompile").setProperty("string", "Decompile");
		gui.get("decompile").setProperty("type", "button");
		
		gui.get("y_size").setProperty("align", "left");
		gui.get("y_size").setProperty("string", "512");
		gui.get("y_size").setProperty("type", "button");
		
		gui.get("x_size").setProperty("align", "left");
		gui.get("x_size").setProperty("string", "256");
		gui.get("x_size").setProperty("type", "button");
		
		gui.get("texture_resolution").setProperty("align", "left");
		gui.get("texture_resolution").setProperty("string", "16");
		gui.get("texture_resolution").setProperty("type", "button");
		
		System.out.println("Main loop started.");
		
		String numbers = "512";
		long numberscooldown = System.currentTimeMillis();
		int lastnumber = 0;
		
		int prevlastKey = 0;
		boolean endProgram = false;
		int currentsetting = 0;
		while (!endProgram ) {
			int ww = window.getWidth();
			int wh = window.getHeight();
			
			render.clear();		
	
			gui.draw(render, window, null, 0, 0, 0);
			
			if (gui.get("y_size").getProperty("selected")!=null) 
				if (gui.get("y_size").getProperty("selected").equals("true")) {
					if (gui.get("y_size").getProperty("last_state_change").equals("true")) {
						gui.get("x_size").setProperty("selected", "false");
						gui.get("texture_resolution").setProperty("selected", "false");
						numbers = "";}
						gui.get("y_size").setProperty("string", numbers); 
						
						 }
			
			
			
			if (gui.get("x_size").getProperty("selected")!=null) 
				if (gui.get("x_size").getProperty("selected").equals("true")) {
					if (gui.get("x_size").getProperty("last_state_change").equals("true")) {
						gui.get("y_size").setProperty("selected", "false");
						gui.get("texture_resolution").setProperty("selected", "false");
						 numbers = "";}
						gui.get("x_size").setProperty("string", numbers); 
						
						}
			
			if (gui.get("texture_resolution").getProperty("selected")!=null) 
				if (gui.get("texture_resolution").getProperty("selected").equals("true")) {
					if (gui.get("texture_resolution").getProperty("last_state_change").equals("true")) {
						gui.get("y_size").setProperty("selected", "false");
						gui.get("x_size").setProperty("selected", "false");
						 numbers = "";}
						gui.get("texture_resolution").setProperty("string", numbers); 
						
						}
			
			
		
			
			window.loop();
			if (window.shouldClose()) endProgram = true;
			
			if (window.lastKey != prevlastKey || numberscooldown < System.currentTimeMillis()) {
			if (window.isKeyPressed(GLFW.GLFW_KEY_1)) { 
				numbers = numbers + "1"; }
			if (window.isKeyPressed(GLFW.GLFW_KEY_2)) { 
				numbers = numbers + "2"; }
			if (window.isKeyPressed(GLFW.GLFW_KEY_3)) { 
				numbers = numbers + "3"; }
			if (window.isKeyPressed(GLFW.GLFW_KEY_4)) { 
				numbers = numbers + "4"; }
			if (window.isKeyPressed(GLFW.GLFW_KEY_5)) { 
				numbers = numbers + "5"; }
			if (window.isKeyPressed(GLFW.GLFW_KEY_6)) { 
				numbers = numbers + "6"; }
			if (window.isKeyPressed(GLFW.GLFW_KEY_7)) { 
				numbers = numbers + "7"; }
			if (window.isKeyPressed(GLFW.GLFW_KEY_8)) { 
				numbers = numbers + "8"; }
			if (window.isKeyPressed(GLFW.GLFW_KEY_9)) { 
				numbers = numbers + "9"; }
			if (window.isKeyPressed(GLFW.GLFW_KEY_0)) { 
				numbers = numbers + "0"; }
			
			if (window.isKeyPressed(GLFW.GLFW_KEY_BACKSPACE) && numbers.length()>0) {
				numbers = numbers.substring(0, numbers.length()-1); }
				
				
			numberscooldown = System.currentTimeMillis() + 250;
			prevlastKey = window.lastKey;
			}
			
			if (gui.get("compile").getProperty("selected")!=null) 
				if (gui.get("compile").getProperty("selected").equals("true")) {
					endProgram = true;
					System.out.println("Compiling");
					}
			
			if (gui.get("decompile").getProperty("selected")!=null) 
				if (gui.get("decompile").getProperty("selected").equals("true")) {
					endProgram = true;
					System.out.println("Decompiling");
					}
		}
		
		int tile_size = Integer.parseInt(gui.get("texture_resolution").getProperty("string").strip());
		
	if (gui.get("compile").getProperty("selected")!=null) 
	if (gui.get("compile").getProperty("selected").equals("true")) {
				
		Sprite sheet = new Sprite(Integer.parseInt(gui.get("x_size").getProperty("string").strip()), Integer.parseInt(gui.get("y_size").getProperty("string").strip()));		
		
		FileWriter myWriter = new FileWriter("filenames.txt");
		  
		
		File dir = new File("item_textures/");//System.getProperty("user.dir"));
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		      // Do something with child
		      myWriter.write(child.getName() + " \n");
		      sheet.addSprite(child.getPath());
		    }
		  } else {
		    // Handle the case where dir is not really a directory.
		    // Checking dir.isDirectory() above would not be sufficient
		    // to avoid race conditions with another process that deletes
		    // directories.
		  }
		  
		  myWriter.close();
		  sheet.save("itemSheet.png");
		}
	
	if (gui.get("decompile").getProperty("selected")!=null) 
	if (gui.get("decompile").getProperty("selected").equals("true")) {
			//BufferedImage b = new BufferedImage(Integer.parseInt(gui.get("x_size").getProperty("string").strip()), Integer.parseInt(gui.get("y_size").getProperty("string").strip()), BufferedImage.TYPE_INT_ARGB);		
			BufferedImage item = new BufferedImage(tile_size, tile_size, BufferedImage.TYPE_INT_ARGB);
			
			BufferedImage b = ImageIO.read(new File("itemSheet.png"));
			
			int x = 0, y = 0;
			
			try(BufferedReader br = new BufferedReader(new FileReader("filenames.txt"))) {
			    StringBuilder sb = new StringBuilder();
			    String line = "";// br.readLine();

			    while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			        
			        item = b.getSubimage(x, y, tile_size, tile_size);
			        try {
						ImageIO.write(item, "png", new File("item_textures/" + line));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Failed to save Sprite");
					}
			        
			        x+=tile_size;
			        if (x >= Integer.parseInt(gui.get("x_size").getProperty("string").strip())) {
			        x=0;
			        y+=tile_size;
			        }
			    }
			    String everything = sb.toString();
			}
			
		}
	}
}
