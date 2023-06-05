package dev.n1t.game.mesh;

import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer;
import com.jme3.util.BufferUtils;

public class TerrainMesh extends Mesh {

    private final int squareResolution;
    private final Vector3f[] vertices;

    public TerrainMesh(int squareSideLength, int squareResolution){
        super();

        this.squareResolution = squareResolution;

        //generate vertices
        this.vertices = new Vector3f[(int) Math.pow(squareResolution, 2)];

        for(int x = 0; x < squareResolution; x++){
            for(int y = 0; y < squareResolution; y++){
                vertices[x * squareResolution + y] = new Vector3f(
                    squareSideLength * (float) x / (float) (squareResolution - 1),
                    squareSideLength * (float) y / (float) (squareResolution - 1),
                    1f
                );
            }
        }

        //generate faces
        final int squareFaceResolution = squareResolution - 1;
        int[] indexes = new int[6 * (int) Math.pow(squareFaceResolution, 2)];
        int[] facePointOffsets = {1, 0, squareResolution, squareResolution, squareResolution + 1, 1};

        for(int x = 0; x < squareFaceResolution; x++){
            for(int y = 0; y < squareFaceResolution; y++){
                int operatingIndex = (x * squareFaceResolution + y) * 6;
                for(int offset = 0; offset < facePointOffsets.length; offset++){
                    indexes[operatingIndex + offset] = x * squareResolution + y + facePointOffsets[offset];
                }
            }
        }

        this.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        this.setBuffer(VertexBuffer.Type.Index,    3, BufferUtils.createIntBuffer(indexes));
        this.updateBound();
    }

    public void sphericalDepress(int radius, int centerLocationIndex, float centerDepth){

        if(centerLocationIndex > vertices.length || centerLocationIndex < 0)
            throw new RuntimeException("Diamond depression center index out of vertex buffer bounds");

        if(radius <= 0)
            throw new RuntimeException("Radius must be positive");

        for (int x = 0; x < squareResolution; x++) {
            for (int y = 0; y < squareResolution; y++) {
                int index = x * squareResolution + y;

                final int xDistFromCenter = x - centerLocationIndex % squareResolution;
                final int yDistFromCenter = y - centerLocationIndex / squareResolution;
                final float distancesSquaredSum = (float) (Math.pow(yDistFromCenter, 2) + Math.pow(xDistFromCenter, 2));

                if (distancesSquaredSum <= Math.pow(radius, 2)) {
                    float depthFactor = 1.0f - (distancesSquaredSum / (float) (Math.pow(radius, 2)));
                    vertices[index].setZ(vertices[index].getZ() - centerDepth * depthFactor);
                }
            }
        }

        this.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        this.updateBound();
    }
}
