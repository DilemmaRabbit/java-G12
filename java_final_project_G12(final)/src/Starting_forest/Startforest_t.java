package Starting_forest;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Startforest_t extends BasicGameState {

    Image intro;
    Image control;
    public Startforest_t(int state) {
    }

    @Override
    public int getID() {
        return 14;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        intro = new Image("Control_Introduction/resource/title.jpg");
        control = new Image("Resource/intro.jpg");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(intro,0,0);
        graphics.drawImage(control,120,100);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        if(input.isKeyPressed((Input.KEY_Z)))
            stateBasedGame.enterState(111, new FadeOutTransition(), new FadeInTransition());
    }


}
