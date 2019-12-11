package helpers;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Textures {

	static Map<String, BufferedImage> textures;
	static Map<String, String> paths;
	static String resourceFolder = "/";

	public static void init() {

		textures = new HashMap<String, BufferedImage>();
		paths = new HashMap<String, String>();

		paths.put("BACKGROUND", "/backgroung.png");

		//loadTextures();

	}

	private static void loadTextures() {
		for (Map.Entry<String, String> entry : paths.entrySet()) {
			try {
				System.out.println("Loading Texture:" + entry.getValue());
				textures.put(entry.getKey(), ImageIO.read(Textures.class.getResourceAsStream(entry.getValue())));

			} catch (IOException e) {
				System.out.println("Couldn't find Texture: " + entry.getValue());
				e.printStackTrace();
			} 
		}

	}
	
	public static BufferedImage get(String name){
		return textures.get(name);
	}

}
