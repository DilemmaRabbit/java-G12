package play;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import static java.lang.Thread.sleep;

public class Play extends BasicGameState {

    Image character;
    int inix=0;
    int iniy=0;
    int forward=0;
    TiledMap title;

    public  Play(int state){
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        title = new TiledMap("play/map/town.tmx");
        character = new Image("play/character/charactertest.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        title.render(0,0);
        if(forward==0){
            graphics.drawImage(character,inix,iniy,inix+64,iniy+64,0,576,64,640);
        }
        if(forward==1){
            graphics.drawImage(character,inix,iniy,inix+64,iniy+64,0,128,64,192);
        }
        if(forward==2){
            graphics.drawImage(character,inix,iniy,inix+64,iniy+64,0,192,64,256);
        }
        if(forward==3){
            graphics.drawImage(character,inix,iniy,inix+64,iniy+64,0,0,64,64);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        if(input.isKeyPressed(Input.KEY_UP)){
            if(forward==3 && iniy>0){
                iniy-=64;
            }
            forward=3;
        }
        if(input.isKeyPressed(Input.KEY_DOWN)){
            if(forward==1 && iniy<896){
                iniy+=64;
            }
            forward=1;
        }
        if(input.isKeyPressed(Input.KEY_LEFT)){
            if (forward == 0 && inix>0) {
                inix-=64;
            }
            forward=0;
        }
        if(input.isKeyPressed(Input.KEY_RIGHT)){
            if(forward==2 && inix<1216){
                inix+=64;
            }
            forward=2;
        }
    }

    @Override
    public int getID() {
        return 2;
    }
}
