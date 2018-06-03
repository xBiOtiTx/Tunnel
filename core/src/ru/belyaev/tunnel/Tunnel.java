package ru.belyaev.tunnel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tunnel extends ApplicationAdapter {
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    Texture img;
    List<Vector2> lines = new ArrayList<>();
    Vector2 t1 = new Vector2(0, 0);
    Vector2 t2 = new Vector2(0, 0);

    ClickListener clickListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            // super.clicked(event, x, y);
            lines.add(new Vector2(x, y));
        }
    };

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                lines.add(new Vector2(screenX, screenY));
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {

                return false;
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(50, 50, 32);
        shapeRenderer.end();

        Gdx.gl.glLineWidth(5);
        //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.line(0,0,100,100);
//        shapeRenderer.line(0,0,200,100);
//        shapeRenderer.line(0,0,200,300);
//        shapeRenderer.end();


        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        t1.set(0,0);
        for (Vector2 v : lines) {
            shapeRenderer.line(t1,v);
            t1.set(v);
        }
        shapeRenderer.end();


        //Line.CalculateBezierPoint(t, p0, p1, p2, p3)
        // Vector2
//        Vector2[] points = {
//                new Vector2(100, 100),
//                new Vector2(200, 100),
//                new Vector2(200, 300)
//        };
//        Vector2 t1 = new Vector2(100, 100);
//        Vector2 t2 = new Vector2(100, 100);
//        Bezier<Vector2> bezier = new Bezier<>(points);
//        for(float f = 0; f< 1; f+=0.1) {
//            bezier.valueAt(t2, f);
//            shapeRenderer.line(t1,t2);
//            t1.set(t2);
//        }
//
//        shapeRenderer.end();
        //Interpolation.linear;

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
