package Starting_forest;

import org.newdawn.slick.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

import java.sql.SQLException;
import java.sql.*;

public class Startforest extends BasicGameState {

    Image character;
    Image npc;
    int inix;
    int iniy;
    int forward;
    int musicplayed;
    Image boy;
    Image boyface;

    TiledMap title;
    private SpriteSheet left;
    private SpriteSheet right;
    private SpriteSheet up;
    private SpriteSheet down;

    private Animation leftanimation;
    private Animation rightanimation;
    private Animation upanimation;
    private Animation downanimation;
    private Sound music;
    public Startforest(int state) {
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        inix=640;
        iniy=416;
        forward=1;
        musicplayed=1;
        music = new Sound("Resource/forest_bgm.ogg");
        down = new SpriteSheet("Resource/character/down.png",64,64);
        up = new SpriteSheet("Resource/character/up.png",64,64);
        left= new SpriteSheet("Resource/character/left.png",64,64);
        right = new SpriteSheet("Resource/character/right.png",64,64);

        downanimation = new Animation(down,250);
        upanimation = new Animation(up,250);
        rightanimation = new Animation(right,250);
        leftanimation = new Animation(left,250);

        boy = new Image("Resource/character/boyui.png");
        boyface = new Image("Resource/character/boy.png");

        title = new TiledMap("Resource/starting_forest.tmx");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        title.render(0,0);
        if(forward==0){
            leftanimation.draw(inix,iniy);
        }
        /*LEFT*/
        if(forward==1){
            downanimation.draw(inix,iniy);
        }
        /*DOWN*/
        if(forward==2){
            rightanimation.draw(inix,iniy);
        }
        /*RIGHT*/
        if(forward==3){
            upanimation.draw(inix,iniy);
        }
        /*UP*/
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        if(musicplayed==1){
            music.play();
            musicplayed=0;
        }

        if(input.isKeyPressed(Input.KEY_ENTER)){
            stateBasedGame.enterState(111,new FadeOutTransition(), new FadeInTransition());
            System.out.print("press Statue");
        }
        if(input.isKeyDown(Input.KEY_LEFT)){
            if(inix==0){
                if(music.playing()){
                    music.stop();
                    musicplayed=1;
                }
                stateBasedGame.enterState(21, new FadeOutTransition(),new FadeInTransition());
            }
            if (forward == 0 && inix>0 ) {
                inix -= 4;
            }
            forward=0;
        }
        if(input.isKeyDown(Input.KEY_DOWN)){
            if(forward==1 && iniy<896){
                    iniy+=4;
            }
            forward=1;
        }
        if(input.isKeyDown(Input.KEY_RIGHT)){

            if(forward==2 && inix<1216){
                    inix+=4;
            }
            forward=2;
        }
        if(input.isKeyDown(Input.KEY_UP)){
            if(iniy==0){
                if(music.playing()){
                    music.stop();
                    musicplayed=1;
                }
                stateBasedGame.enterState(31,new FadeOutTransition(),new FadeInTransition());
            }
            if(forward==3 && iniy>0){
                    iniy-=4;
            }
            forward=3;
        }


    }
    @Override
    public int getID() {
        return 11;
    }
}
