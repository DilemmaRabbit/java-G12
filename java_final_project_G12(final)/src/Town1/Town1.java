package Town1;

import Get_Update_data.Get;
import Get_Update_data.Update;
import org.lwjgl.openal.AL;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

public class Town1 extends BasicGameState {
    private int inix;
    private int iniy;
    private int forward;
    private int getmoney;
    private int shopmode;
    private int money;
    private int musicplayed;
    int pill_l;
    int pill_s;
    TiledMap title;
    Image pill_large;
    Image pill_small;
    java.awt.Font font;
    TrueTypeFont font1;
    private SpriteSheet left;
    private SpriteSheet right;
    private SpriteSheet up;
    private SpriteSheet down;

    private Animation leftanimation;
    private Animation rightanimation;
    private Animation upanimation;
    private Animation downanimation;


    Image shop;
    Image shopface;
    Image loli;
    Image loliface;
    Image loli2;
    Image loli2face;

    private Sound music;
    public Town1(int state) {
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        title = new TiledMap("/Resource/town.tmx");

        music = new Sound("Resource/town_bgm.ogg");
        musicplayed=1;
        forward = 0;
        inix=1216;
        iniy=480;
        shopmode=0;
        getmoney=0;
        font = new java.awt.Font("LucidaBrightItalic", java.awt.Font.BOLD,30);
        font1 = new TrueTypeFont(font,true);
        down = new SpriteSheet("Resource/character/down.png",64,64);
        up = new SpriteSheet("Resource/character/up.png",64,64);
        left= new SpriteSheet("Resource/character/left.png",64,64);
        right = new SpriteSheet("Resource/character/right.png",64,64);

        downanimation = new Animation(down,250);
        upanimation = new Animation(up,250);
        rightanimation = new Animation(right,250);
        leftanimation = new Animation(left,250);

        shop = new Image("Resource/character/town_ui.png");
        shopface = new Image("Resource/character/town.png");
        loli = new Image("Resource/character/loli1ui.png");
        loliface = new Image("Resource/character/loli1.png");
        loli2 = new Image("Resource/character/loli2ui.png");
        loli2face = new Image("Resource/character/loli2.png");


        pill_large = new Image("Resource/status_button/button_medicine-large-hp.png");
        pill_small = new Image("Resource/status_button/button_medicine-small-hp.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        title.render(0,0);
        graphics.drawImage(shop,3*64,6*64,4*64,7*64,64,0,128,64);
        graphics.drawImage(loli,3*64,64*12,4*64,64*13,64,2*64,2*64,3*64);
        graphics.drawImage(loli2,9*64,6*64,10*64,7*64,64,0,64*2,64);
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
        if(shopmode==1){
            graphics.setColor(Color.gray);
            graphics.fillRect(0,700,1280,260);
            graphics.drawImage(shopface,5,704);
            graphics.drawImage(pill_large,660,750);
            graphics.drawImage(pill_small,660,850);
            graphics.setFont(font1);
            graphics.setColor(Color.black);
            graphics.drawString("x"+pill_l,1100,770);
            graphics.drawString("x"+pill_s,1100,870);
            graphics.setColor(Color.yellow);
            graphics.drawString("Your money ="+money,800,700);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

        Input input = gameContainer.getInput();

        if(musicplayed==1){
            music.play();
            musicplayed=0;
        }
        
        if(getmoney==0){
            try {
                Get get = new Get();
                money = get.getMoney();
                pill_l = get.getMedicine_large();
                pill_s = get.getMedicine_small();
                System.out.print(money);
                getmoney=1;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }



        if(shopmode==0){
            if(input.isKeyPressed(Input.KEY_ENTER)){
                stateBasedGame.enterState(211,new FadeOutTransition(), new FadeInTransition());
                System.out.print("press Statue");
            }
            if(input.isKeyDown(Input.KEY_LEFT)){
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
                if(inix==1280-64 && iniy > 480-64 &&iniy < 480+64){
                    if(music.playing()){
                        music.stop();
                        musicplayed=1;
                    }
                    stateBasedGame.enterState(11,new FadeOutTransition(),new FadeInTransition());
                }
                if(forward==2 && inix<1216){
                    inix+=4;
                }
                forward=2;
            }
            if(input.isKeyDown(Input.KEY_UP)){
                if(inix >= 3*64 && inix <= 4*64 && iniy > 6*64 && iniy < 7*64 ){
                    shopmode=1;
                }
                if(forward==3 && iniy>0){
                    iniy-=4;
                }
                forward=3;
            }

        }
        if (shopmode == 1) {
            if(input.isKeyDown(Input.KEY_Z)){
                Update up = new Update();
                try {
                    up.Money_update(money);
                    up.Medicine_large_update(pill_l);
                    up.Medicine_small_update(pill_s);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if(shopmode==1){
                    shopmode=0;
                }
            }
        }
    }

    public void mouseClicked(int button, int x, int y, int clickCount){
        if(shopmode==1){
            if(x >= 660&& x <= 660+568&& y >= 750&& y<= 750+56){
                if(money>=200){
                    money -=200;
                    pill_l++;
                }
            }
            if(x >= 660&& x <= 660+577&& y >= 850&& y<= 850+56){
                if(money>=100){
                    money -=100;
                    pill_s++;
                }
            }
        }
    }

    @Override
    public int getID() {
        return 21;
    }
}
