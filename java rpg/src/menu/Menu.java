package menu;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Menu extends BasicGameState {
    int modechange;
    Image play;
    Image exit;

    public  Menu(int state){
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        modechange=0;
        play = new Image("menu/resource/play.png");
        exit = new Image("menu/resource/exit.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        Image titlescreen = new Image("menu/resource/title.jpg");
        graphics.drawImage(titlescreen,0,0);
        graphics.drawImage(play,50,420);
        graphics.drawImage(exit,50,520);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(modechange==1){
            stateBasedGame.enterState(1,new FadeOutTransition(), new FadeInTransition());
        }
        if(modechange==2){
            gameContainer.exit();
        }
    }

    public void mouseClicked(int button, int x, int y, int clickCount) {
        if(button == Input.MOUSE_LEFT_BUTTON){
            if(x>50 && 428>x && y>420 && 498>y){
                modechange = 1;
            }
            if(x>50 && 395>x && y>520 && 599>y){
                modechange = 2;
            }
        }
    }

    @Override
    public int getID() {
        return 0;
    }
}
