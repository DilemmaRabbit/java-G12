package Statue;

import Get_Update_data.Get;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Statue extends BasicGameState {

    int getdata=1;
    int health;
    int maxhealth;
    int money;
    int lv;
    int ATK;
    int exp;

    public Statue(int state) {
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        System.out.println(health);
        System.out.println(money);
        System.out.println(lv);
        System.out.println(maxhealth);
        System.out.println(ATK);
        System.out.println(exp);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(getdata==1) {
            try {
                Get get = new Get();
                health =get.getHealth();
                maxhealth = get.getMaxhealth();
                money = get.getMoney();
                lv = get.getLv();
                ATK = get.getATK();
                exp = get.getExp();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        Input input = gameContainer.getInput();
        if(input.isKeyPressed(Input.KEY_ENTER)){

        }
    }

    @Override
    public int getID() {
        return 2;
    }
}
