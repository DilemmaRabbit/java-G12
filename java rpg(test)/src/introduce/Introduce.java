package introduce;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Introduce extends BasicGameState {

    public String aside = "Hello there \n" +
                          "I am the designer \n" +
                          "I wish you have a good time \n" +
                          "If you get trouble in this game \n" +
                          "Please let me know\n" +
                          "Thank you for playing!\n" +
                          "Hello there \n" +
                          "Hello there \n" +
                          "Hello there \n" +
                          "Hello there \n" +
                          "This is a fucking game!!!\n";

    public  Introduce(int state){
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        Image titlescreen = new Image("introduce/resource/title.jpg");
        graphics.drawImage(titlescreen,0,0);
        graphics.drawString(aside,20,200);
        graphics.drawString("press Z to continue",500,900);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        if(input.isKeyPressed((Input.KEY_Z))){
            stateBasedGame.enterState(2,new FadeOutTransition(), new FadeInTransition());
        }
    }

    @Override
    public int getID() {
        return 1;
    }

    private class PLAIN {
    }
}
