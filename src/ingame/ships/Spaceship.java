package ingame.ships;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import helpers.Sound;
import helpers.Vector;
import ingame.bullets.Bullet;
import ingame.skills.Skill;
import main.SpaceBoot;

public abstract class Spaceship {

    public static int THRUST = 0;
    public static int SHOOT = 1;
    public static int LEFT = 2;
    public static int RIGHT = 3;
    public static int SKILLONE = 4;
    public static int SKILLTWO = 5;

    public static int SHIPSNUM = 3;
    public static int REDSHIP = 0;
    public static int GREENSHIP = 1;
    public static int BLUESHIP = 2;

    // Properties
    BufferedImage image, imageThrust, shield;
    Vector position, speed, friction;
    Skill[] skills = new Skill[2];

    public double acceleration;
    public double omega;

    int cooldown;
    public int health;
    public int maxTemperature;
    public double currentHealth;
    public double currentTemperatue;
    int controls[] = new int[6];
    int shipID;

    boolean alive = true;
    boolean shieldActive = false;

    // Helpers
    ArrayList<Bullet> bullets;
    BufferedImage[] explosion = new BufferedImage[6];

    double theta = 0;
    double frame = 0;
    int cooldownCounter = 20;

    boolean thrust = false;
    boolean dies = false;
    boolean overheat;

    /*
     * Changed the controls to an array rather than single ints and changed the
     * assigning process to use a for loop. Also added a skill array.
     *
     * Skills are undynamically generated inside subclasses.
     */
    public Spaceship(String URL, String URL_thrust, int shipID, ArrayList<Bullet> bullets, int[] controls, int cooldown, int health, int maxTemperature, Skill[] skills) {

        this.bullets = bullets;
        this.shipID = shipID;
        this.cooldown = cooldown;
        this.health = health;
        this.maxTemperature = maxTemperature;

        theta = Math.random() * 2 * Math.PI;
        currentHealth = health;
        // the starting position is generated randomly but in such fashion that the ship wont spawn inside the Stat Display Area (0,0,WIDTH,50)
        position = new Vector(Math.random() * SpaceBoot.WIDTH, Math.random() * (SpaceBoot.HEIGHT - 50) + 50);
        speed = new Vector(0, 0);
        friction = new Vector(0.985, 0.985);

        /*
        Assigns the passed controls to the ship
         */
        for (int i = 0; i < this.controls.length; i++) {
            this.controls[i] = controls[i];
        }
        /*
        Assigns the passed skills to the ship
         */
        for (int i = 0; i < this.skills.length; i++) {
            this.skills[i] = skills[i];
        }

        try {
            image = ImageIO.read(getClass().getResource(URL));
            imageThrust = ImageIO.read(getClass().getResource(URL_thrust));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            explosion[0] = ImageIO.read(getClass().getResource("/exp1.png"));
            explosion[1] = ImageIO.read(getClass().getResource("/exp2.png"));
            explosion[2] = ImageIO.read(getClass().getResource("/exp3.png"));
            explosion[3] = ImageIO.read(getClass().getResource("/exp4.png"));
            explosion[4] = ImageIO.read(getClass().getResource("/exp5.png"));
            explosion[5] = ImageIO.read(getClass().getResource("/exp6.png"));

            shield = ImageIO.read(getClass().getResource("/shipShield.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        position.add(addFriction(speed));
        checkBounce();
        cooldownCounter--;
        if (currentTemperatue > 0) {
            currentTemperatue--;
        } else if (currentTemperatue == 0) {
            overheat = false;
        }

        if (currentHealth <= 0) {
            dies = true;
        }
        for (Skill skill : skills) {
            skill.update();
        }
    }

    /*
     * Reduces the speed by the set friction. Feels better to play with.
     */
    private Vector addFriction(Vector speed) {
        speed.setX(speed.getX() * friction.getX());
        speed.setY(speed.getY() * friction.getY());
        return speed;
    }

    public void draw(Graphics2D g) {

        AffineTransform at = new AffineTransform();
        at.translate(position.getX(), position.getY());

        if (!dies) {
            at.rotate(theta + Math.PI / 2, 20, 15);
            if (thrust) {
                g.drawImage(imageThrust, at, null);
            } else {
                g.drawImage(image, at, null);
            }
            thrust = false;

			/*
             * If shield is active the shield is painted over the spaceship.
			 */
            if (shieldActive) {
                g.drawImage(shield, (int) position.getX(), (int) position.getY() - 5, null);
            }

			/*
             * Draws overheat Letters over the ship
			 */
            if (overheat) {
                g.setColor(Color.RED);
                g.drawString("Overheat!", (int) position.getX() - 6, (int) position.getY() + 40);
            }

        } else if (dies) {
            if (frame < 5) {
                frame += 0.5;
                if (!Sound.isPlaying("EXPLOSION"))
                    Sound.play("EXPLOSION");
                g.drawImage(explosion[(int) Math.round(frame)], (int) position.getX(), (int) position.getY(), null);
            } else {
                dies = false;
                alive = false;
                frame = 0;
            }

        }

    }

    public void reset() {
        alive = true;
        currentHealth = health;
        currentTemperatue = 0;
        position = new Vector(Math.random() * SpaceBoot.WIDTH, Math.random() * SpaceBoot.HEIGHT);
        speed = new Vector(0, 0);
        theta = Math.random() * 2 * Math.PI;
    }

    public void thrust() {
        thrust = true;
        speed.addToX(Math.cos(theta) * acceleration);
        speed.addToY(Math.sin(theta) * acceleration);
    }

    public void rotateLeft() {
        theta -= Math.toRadians(omega);
    }

    public void rotateRight() {
        theta += Math.toRadians(omega);
    }

    public void shoot() {
    }

    public void checkBounce() {
        if (position.getX() <= 0) {
            speed.setX(-0.6 * speed.getX());
            position.setX(0);
        }
        if (position.getX() >= SpaceBoot.WIDTH - 30) {
            speed.setX(-0.6 * speed.getX());
            position.setX(SpaceBoot.WIDTH - 30);
        }
        // Ship now bounces at y<=50 to leave space for the Stat Display Area (0,0,WIDTH,50)
        if (position.getY() <= 50) {
            speed.setY(-0.6 * speed.getY());
            position.setY(50);
        }
        if (position.getY() >= SpaceBoot.HEIGHT - 30) {
            speed.setY(-0.6 * speed.getY());
            position.setY(SpaceBoot.HEIGHT - 30);
        }
    }

    /*
     * Tries to use the certain skill for this ship.
     * (called by PlayState)
     */
    public void useSkill(int skill) {
        try {
            skills[skill].trySkill(this);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Skill not assigned");
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public Vector getPos() {
        return position;
    }

    public double getHealth() {
        return health;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void addHealth(double d) {
        this.currentHealth += d;
    }

    public int getShipID() {
        return shipID;
    }

    public int getControls(int i) {
        return controls[i];
    }

    public boolean isAlive() {
        return alive;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getOmega() {
        return omega;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public void setOmega(double omega) {
        this.omega = omega;
    }

    /*
     * "Getter and Setter" for Shield.
     */
    public void haveShield() {
        shieldActive = true;

    }

    public boolean shieldIsActive() {
        return shieldActive;
    }

    public void destroyShield() {
        shieldActive = false;
    }


}
