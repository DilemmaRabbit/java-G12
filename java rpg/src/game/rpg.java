package game;

import introduce.Introduce;
import menu.Menu;
import play.Play;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class rpg extends StateBasedGame {

    public static final String rpgtitle = "game.rpg game";
    public static final int menu=0;
    public static final int introduce=1;
    public static final int play=2;

    public rpg(String rpgtitle) {
        super(rpgtitle);
        this.addState(new Menu(menu));
        this.addState(new Introduce(introduce));
        this.addState(new Play(play));

    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.getState(menu).init(gameContainer,this);
        this.getState(introduce).init(gameContainer,this);
        this.getState(play).init(gameContainer,this);
        this.enterState(menu);
    }

    public static void main(String[] args){
        AppGameContainer rpggc;
        try{
            rpggc = new AppGameContainer(new rpg(rpgtitle));
            rpggc.setDisplayMode(1280,960,false);
            rpggc.start();
        }catch (SlickException e){
            e.printStackTrace();
        }
    }
}
