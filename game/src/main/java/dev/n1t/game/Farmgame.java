package dev.n1t.game;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.scene.Geometry;
import com.jme3.app.state.AppState;
import dev.n1t.game.mesh.TerrainMesh;

/**
 * The JMonkeyEngine game entry, you should only do initializations for your game here, game logic is handled by
 * Custom states {@link com.jme3.app.state.BaseAppState}, Custom controls {@link com.jme3.scene.control.AbstractControl}
 * and your custom entities implementations of the previous.
 *
 */
public class Farmgame extends SimpleApplication {

    public Farmgame() {
    }

    public Farmgame(AppState... initialStates) {
        super(initialStates);
    }

    @Override
    public void simpleInitApp() {
        TerrainMesh terrainMesh = new TerrainMesh(3, 20);
        Geometry geom = new Geometry("Box", terrainMesh);

        geom.setLocalTranslation(-8, -4, 5);
        geom.rotate(0, 0.25f, 0);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Yellow);
        mat.getAdditionalRenderState().setWireframe(true);
        geom.setMaterial(mat);
        rootNode.attachChild(geom);
    }

}
