package com.example.pt13_fruitatemps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class SplashScreen implements Screen {
    private final FruitesDelTemps game;
    private float timer = 0;
    private BitmapFont font;
    private String text = "Fruit Ninja DAM";
    private GlyphLayout layout;

    public SplashScreen(FruitesDelTemps game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Generem la font gran per al títol
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pixel.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80; // Mida gran
        parameter.color = Color.BLACK; // Text en negre sobre fons blanc
        font = generator.generateFont(parameter);
        generator.dispose();

        layout = new GlyphLayout();
        layout.setText(font, text);
    }

    @Override
    public void render(float delta) {
        // Fons blanc
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        // Dibuixem el text al centre de la pantalla
        font.draw(game.batch, text, 
            Gdx.graphics.getWidth() / 2f - layout.width / 2f, 
            Gdx.graphics.getHeight() / 2f + layout.height / 2f);
        game.batch.end();

        timer += delta;
        if (timer > 3f) { // Augmentat a 3 segons per a que es pugui llegir bé
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override
    public void dispose() {
        if (font != null) font.dispose();
    }
}