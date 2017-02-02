package com.geodash;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import static com.geodash.GamGeoDash.bBoat;
import static com.geodash.GamGeoDash.bFlipGrav;
import static com.geodash.GamGeoDash.bJump;
import static com.geodash.GamGeoDash.bPlayerDead;
import static com.geodash.GamGeoDash.sBoatPortal;
import static com.geodash.GamGeoDash.sGravPortal;
import static com.geodash.GamGeoDash.sPlatform;
import static com.geodash.GamGeoDash.sPlayer;
import static com.geodash.GamGeoDash.sSpike;
import static com.geodash.GamGeoDash.world;
import static com.geodash.ScrPlay.player;

/**
 * Created by hafiz on 2/2/2017.
 */

public class ContactListener1 implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if (a == null || b == null) {
            return;
        }
        if (a.getUserData() == null || b.getUserData() == null) {
            return;
        }
        if (CheckContact(a, b, sPlayer, sSpike)) {
            bPlayerDead = true;
        }

        if (CheckContact(a, b, sPlayer, sPlatform) && bJump == false) {
            bJump = true;
        }

        if (CheckContact(a, b, sPlayer, sBoatPortal)) {
            if (bBoat == false) {
                player.changeImage(7, 3);
                bBoat = true;
            } else if (bBoat == true) {
                player.changeImage(2, 1);
                bBoat = false;
            }
            System.out.println("Boat Time");
        }
        if (CheckContact(a, b, sPlayer, sGravPortal)) {
            if (bFlipGrav == false) {
                world.setGravity(new Vector2(0, world.getGravity().y * -1));
                bFlipGrav = true;
            } else if (bFlipGrav == true) {
                world.setGravity(new Vector2(0, world.getGravity().y * -1));
                bFlipGrav = false;
            }
            System.out.println("Boat Time");
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public boolean CheckContact(Fixture a, Fixture b, String sDef1, String sDef2) {
        if (a.getUserData() == sDef1 || b.getUserData() == sDef1) {
            if (a.getUserData() == sDef2 || b.getUserData() == sDef2) {
                return true;
            }
        }
        return false;
    }
}