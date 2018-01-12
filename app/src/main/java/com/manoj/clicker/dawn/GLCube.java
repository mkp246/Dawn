package com.manoj.clicker.dawn;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by mkumar14 on 1/11/2018-3:14 PM.
 */

public class GLCube {
    private float[] vertices = {
            1f, 1f, -1f, //RUF
            1f, -1f, -1f,//RDF
            -1, -1, -1,  //LDF
            -1, 1, -1,   //LUF
            1f, 1f, 1f,  //RUB
            1f, -1f, 1f, //RDB
            -1, -1, 1,   //LDB
            -1, 1, 1,    //LUB
    };
    private FloatBuffer vertBuff;
    private FloatBuffer colorBuff;
    private short[] pIndex = {
            1, 2, 3, 3, 0, 1, 0, 4, 1,
            5, 1, 4, 4, 7, 5, 6, 5, 7,
            7, 3, 6, 1, 6, 3, 4, 0, 7,
            3, 7, 0, 2, 1, 6, 5, 6, 1
    };
    private float[] rgba = {
            1, 1, 1, .5f,
            .25f, 0, .85f, 1,
            0, 1, 1, 1
    };
    private ShortBuffer pBuff;

    public GLCube() {
        ByteBuffer bBuff = ByteBuffer.allocateDirect(vertices.length * 4);
        bBuff.order(ByteOrder.nativeOrder());
        vertBuff = bBuff.asFloatBuffer();
        vertBuff.put(vertices);
        vertBuff.position(0);

        ByteBuffer pbBuff = ByteBuffer.allocateDirect(pIndex.length * 2);
        pbBuff.order(ByteOrder.nativeOrder());
        pBuff = pbBuff.asShortBuffer();
        pBuff.put(pIndex);
        pBuff.position(0);

        ByteBuffer cBuff = ByteBuffer.allocateDirect(rgba.length * 4);
        cBuff.order(ByteOrder.nativeOrder());
        colorBuff = cBuff.asFloatBuffer();
        colorBuff.put(rgba);
        colorBuff.position(0);
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuff);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
