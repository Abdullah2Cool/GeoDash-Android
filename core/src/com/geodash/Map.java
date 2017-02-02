package com.geodash;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;

import static com.geodash.GamGeoDash.sBoatPortal;
import static com.geodash.GamGeoDash.sGravPortal;
import static com.geodash.GamGeoDash.sPlatform;
import static com.geodash.GamGeoDash.sSpike;

/**
 * Created by hafiz on 2/2/2017.
 */

public class Map {
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private TiledMap map;
    private World world;

    public Map(String sPath, World world, boolean bObstacles, boolean bPortals, GamGeoDash game) {
        map = new TmxMapLoader().load(sPath);
        this.world = world;
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        game.loadObstacles(map.getLayers().get("platforms").getObjects(), sPlatform, false);
        if (bObstacles) {
//            spikes = new Spikes(map, world, "spike");
            game.loadObstacles(map.getLayers().get("death").getObjects(), sSpike, true);
        }
        if (bPortals) {
            game.loadObstacles(map.getLayers().get("boat").getObjects(), sBoatPortal, true);
            game.loadObstacles(map.getLayers().get("gravity").getObjects(), sGravPortal, true);
        }
    }

    public void draw(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    public TiledMap getMap() {
        return map;
    }

    public OrthogonalTiledMapRenderer getTiledMapRenderer() {
        return tiledMapRenderer;
    }

}
