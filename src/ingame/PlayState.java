package ingame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import helpers.Keys;
import helpers.Sound;
import helpers.Vector;
import ingame.bullets.Bullet;
import ingame.pickUps.Buff;
import ingame.pickUps.PickUp;
import ingame.pickUps.PickUpSpawner;
import ingame.ships.BlueShip;
import ingame.ships.GreenShip;
import ingame.ships.RedShip;
import ingame.ships.Spaceship;
import main.GameState;
import main.GameStateManager;
import main.SpaceBoot;

public class PlayState extends GameState {

    ArrayList<Bullet> bullets;
    ArrayList<Spaceship> ships;
    ArrayList<PickUp> pickUps;

    BufferedImage bgimage;

    GameStateManager gsm;
    Player[] players;
    PickUpSpawner spawner;

    String loser;

    int maxDeaths, time, pickUpSpawnFrequency;

    public PlayState(GameStateManager gsm, Player[] player, int maxDeaths) {
        super(gsm);
        this.gsm = gsm;
        this.players = player;
        this.maxDeaths = maxDeaths;

        spawner = new PickUpSpawner();
        pickUpSpawnFrequency = 300;

        time = 0;
    }

    @Override
    public void init() {
        try {
            bgimage = ImageIO.read(getClass().getResource("/background800x600.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sound.load("/explosion.wav", "EXPLOSION");
        Sound.load("/shoot.wav", "SHOOT");
        Sound.load("/shortShoot.wav", "SHORTSHOOT");

        ships = new ArrayList<Spaceship>();
        bullets = new ArrayList<Bullet>();
        pickUps = new ArrayList<PickUp>();

        for (Player p : players) {
            if (p.getShiptype() == Spaceship.BLUESHIP)
                ships.add(new BlueShip(p.getPlayerID(), bullets, p.getControls()));
            if (p.getShiptype() == Spaceship.REDSHIP)
                ships.add(new RedShip(p.getPlayerID(), bullets, p.getControls()));
            if (p.getShiptype() == Spaceship.GREENSHIP)
                ships.add(new GreenShip(p.getPlayerID(), bullets, p.getControls()));

        }

    }

    private void beginNewRound() {
        for (Spaceship s : ships) {
            s.reset();
        }
    }

    public void update() {
        if (time++ % pickUpSpawnFrequency == 0) {
            pickUps.add(spawner.spawnRandomPickUp());
        }
        for (PickUp p : pickUps) {
            p.update();
        }
        for (Bullet b : bullets) {
            b.update();
        }
        for (Spaceship s : ships) {
            if (s.isAlive()) {
                if (players[s.getShipID()].getDeaths() >= maxDeaths) {
                    gsm.loadPlayEndState(players);
                }
                s.update();
            }
            if (!s.isAlive()) {
                players[s.getShipID()].addToDeaths(1);
                beginNewRound();
            }

			/*
             * If PickUp is picked up it affects the ship that picked it up.
			 */
            for (int i = 0; i < pickUps.size(); i++) {
                if (pickUps.get(i).getsPickedUp(s, 20)) {
                    players[s.getShipID()].addPickUps();
                    pickUps.get(i).affect(ships.get(pickUps.get(i).getOwnerID()));

                }
                /*
                 * If PickUp is dead and it is a buff it unAffects the ship that
				 * picked it up.
				 */

                // TODO fix this --> triggers all the time
                if (!pickUps.get(i).isActive() && pickUps.get(i) instanceof Buff && pickUps.get(i).isPickedUp()) {
                    pickUps.get(i).unAffect(ships.get(pickUps.get(i).getOwnerID()));
                    pickUps.remove(i);
                }
            }

            java.util.Iterator<Bullet> iter = bullets.iterator();
            while (iter.hasNext()) {
                Bullet b = iter.next();
                if (b.isAlive()) {
                    if (new Vector(b.getPos().getX() + 5, b.getPos().getY() + 5).getDistanceTo(new Vector(s.getPos().getX() + 20, s.getPos().getY() + 15)) < 15 && s.getShipID() != b.getOwnerID()) {
                        /*
						 * Ship is only damaged when shield is not active. If
						 * ship is hit while shield is active the shield is
						 * destroyed instead.
						 */
                        if (!s.shieldIsActive()) {
                            s.addHealth(-b.getDamage());
                        } else if (s.shieldIsActive()) {
                            s.destroyShield();
                        }
                        b.destroy();
                    }
                } else {
                    iter.remove();
                }
            }

        }
        handleInput();
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.drawImage(bgimage, 0, 0, null);
        //g.drawString("Bullets:" + bullets.size(), 80, 15);
        for (Bullet b : bullets) {
            if (b.isAlive()) {
                b.draw(g);
            }
        }
        for (PickUp p : pickUps) {
            if (p.isAlive()) {
                p.draw(g);
            }
        }
        for (Spaceship s : ships) {
            if (s.isAlive()) {
                s.draw(g);
            }
            g.setColor(Color.WHITE);


        }

        drawStats(g);
    }

    private void drawStats(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, SpaceBoot.WIDTH, 50);
        g.setColor(Color.WHITE);
        g.drawLine(0, 50, SpaceBoot.WIDTH, 50);

        for (int i = 0; i < ships.size(); i++) {
            g.drawImage(ships.get(i).getImage(), (((SpaceBoot.WIDTH - 100) / ships.size()) * i) + 100, 10, null);

            g.setColor(Color.WHITE);
            g.drawString("Deaths: " + players[ships.get(i).getShipID()].getDeaths(), (((SpaceBoot.WIDTH - 100) / ships.size()) * i) + 150, 20);

            g.setColor(Color.WHITE);
            g.drawRect((((SpaceBoot.WIDTH - 100) / ships.size()) * i) + 150, 25, 102, 5);
            g.drawRect((((SpaceBoot.WIDTH - 100) / ships.size()) * i) + 150, 30, 102, 5);

            g.setColor(Color.GREEN);
            g.fillRect((((SpaceBoot.WIDTH - 100) / ships.size()) * i) + 150 + 1, 25 + 1, (int) (ships.get(i).currentHealth / ships.get(i).health * 100) + 1, 4);

            g.setColor(Color.RED);
            g.fillRect((((SpaceBoot.WIDTH - 100) / ships.size()) * i) + 150 + 1, 30 + 1, (int) (ships.get(i).currentTemperatue / ships.get(i).maxTemperature * 100) + 1, 4);


        }
    }

    public void handleInput() {

        for (Spaceship ship : ships) {
            if (Keys.isDown(ship.getControls(Spaceship.LEFT)))
                ship.rotateLeft();
            if (Keys.isDown(ship.getControls(Spaceship.RIGHT)))
                ship.rotateRight();
            if (Keys.isDown(ship.getControls(Spaceship.THRUST)))
                ship.thrust();
            if (Keys.isDown(ship.getControls(Spaceship.SHOOT)) && ship.isAlive())
                ship.shoot();
            if (Keys.isDown(ship.getControls(Spaceship.SKILLONE)) && ship.isAlive())
                ship.useSkill(0);
            if (Keys.isDown(ship.getControls(Spaceship.SKILLTWO)) && ship.isAlive())
                ship.useSkill(1);

        }
        if (Keys.isPressed(Keys.ENTER) && loser != null) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if (Keys.isPressed(Keys.ESC)) {
            gsm.pauseState();
        }

    }

}
