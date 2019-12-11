package ingame.bullets;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import helpers.Vector;
import main.SpaceBoot;

public abstract class Bullet {

    // static properties
    double speedABS;
    String textureURL;
    double recoil;
    double damage;

    // variable properties
    Vector position;
    Vector speed;
    BufferedImage image;
    boolean alive = true;
    int ownerID;

    // helpers
    double theta;
    boolean offScreen = false;

    public Bullet(Vector position, double theta, int ownerID, double speedABS, String textureURL, double recoil, double damage) {

        this.speedABS = speedABS;
        this.textureURL = textureURL;
        this.recoil = recoil;
        this.damage = damage;

        try {
            image = ImageIO.read(getClass().getResource(textureURL));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.ownerID = ownerID;
        this.speed = new Vector(Math.cos(theta) * speedABS, Math.sin(theta) * speedABS);
        this.position = new Vector(position.getX() + 20 - image.getWidth() / 2, position.getY() + 15 - image.getHeight() / 2);
        this.theta = theta;

    }

    public void update() {
        if (alive) {
            position.add(speed);
            checkOnScreen();
        }
    }

    public void draw(Graphics2D g) {
        AffineTransform at = new AffineTransform().getTranslateInstance(position.getX(), position.getY());
        at.rotate(theta + Math.PI / 2, image.getWidth() / 2, image.getHeight() / 2);
        g.drawImage(image, at, null);

    }

    public void checkOnScreen() {
            if (position.getX() > -10 && position.getX() < SpaceBoot.WIDTH && position.getY() > 50 && position.getY() < SpaceBoot.HEIGHT) {
                alive = true;
            } else {
                alive = false;
            }

    }

    public Vector getPos() {
        return position;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void destroy() {
        alive = false;
    }

    public double getDamage() {
        return damage;
    }


}
