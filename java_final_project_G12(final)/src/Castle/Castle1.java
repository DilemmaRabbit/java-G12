package Castle;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

public class Castle1 extends BasicGameState {

    Image character;
    Image npc;
    int inix;
    int iniy;
    int forward;
    private int musicplayed;
    private Sound music;
    private SpriteSheet left;
    private SpriteSheet right;
    private SpriteSheet up;
    private SpriteSheet down;
    private SpriteSheet boss;

    private Animation leftanimation;
    private Animation rightanimation;
    private Animation upanimation;
    private Animation downanimation;

    private Animation bossanimate;

    TiledMap castle;
    public Castle1(int state) {
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        inix=640;
        iniy=960-64;
        forward=3;
        musicplayed=1;
        down = new SpriteSheet("Resource/character/down.png",64,64);
        up = new SpriteSheet("Resource/character/up.png",64,64);
        left= new SpriteSheet("Resource/character/left.png",64,64);
        right = new SpriteSheet("Resource/character/right.png",64,64);
        boss = new SpriteSheet("Resource/enemy/boss.png",160,160);

        downanimation = new Animation(down,250);
        upanimation = new Animation(up,250);
        rightanimation = new Animation(right,250);
        leftanimation = new Animation(left,250);

        bossanimate = new Animation(boss,250);
        music = new Sound("Resource/boss_bgm.ogg");
        castle = new TiledMap("Resource/castle.tmx");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        castle.render(0,0);
        bossanimate.draw(560,300);
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
            stateBasedGame.enterState(311,new FadeOutTransition(), new FadeInTransition());
            System.out.print("press Statue");
        }
        if(input.isKeyDown(Input.KEY_LEFT)){
            if (forward == 0 && inix>0 ) {
                inix -= 4;
            }
            forward=0;
        }
        if(input.isKeyDown(Input.KEY_DOWN)){
            if(iniy==960-64){
                if(music.playing()){
                    music.stop();
                    musicplayed=1;
                }
                stateBasedGame.enterState(11,new FadeOutTransition(),new FadeInTransition());
            }
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
            if(inix > 560 && inix < 720  && iniy > 300 && iniy < 460){
                if(music.playing()){
                    music.stop();
                    musicplayed=1;
                }
                stateBasedGame.enterState(500,new FadeOutTransition(),new FadeInTransition());
            }
            if(forward==3 && iniy>0){
                iniy-=4;
            }
            forward=3;
        }
    }

    @Override
    public int getID() {
        return 31;
    }
}
