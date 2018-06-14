package Castle;

import Get_Update_data.Get;
import Get_Update_data.Update;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Castle1_s extends BasicGameState {

    int mode=5;


    int getdata=1;
    int health;
    int maxhealth;
    int money;
    int lv;
    int ATK;
    int exp;
    Color texture_color;
    Color text_color;

    int medicine_small;
    int medicine_large;

    int sword_equip=1;
    int bow_equip;
    int spear_equip;

    Image character_face;
    Image status_money;
    Image status_exp;
    Image status_ATK;
    Image status_hp;
    Image status_lv;

    Image status_exit;
    Image status_equip;
    Image status_item;
    Image status_music;
    Image status_intro;

    Image sword_e;
    Image sword;
    Image bow_e;
    Image bow;
    Image spear_e;
    Image spear;

    Image medicine_large_ima;
    Image medicine_small_ima;

    public Castle1_s(int state) {
    }

    @Override
    public int getID() {
        return 311;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        texture_color = new Color(30,144,255);
        text_color = new Color(11,23,70);
        character_face = new Image("Resource/character/4.png");

        status_ATK = new Image("Resource/status_button/button_atk.png");
        status_exp = new Image("Resource/status_button/button_exp.png");
        status_hp = new Image("Resource/status_button/button_hp.png");
        status_lv = new Image("Resource/status_button/button_lv.png");
        status_money = new Image("Resource/status_button/button_money.png");

        status_equip = new Image("Resource/status_button/button_equip.png");
        status_item = new Image("Resource/status_button/button_item.png");
        status_exit = new Image("Resource/status_button/button_exit.png");
        status_music = new Image("Resource/status_button/button_music.png");
        status_intro = new Image("Resource/status_button/button_introduce.png");

        sword_e = new Image("Resource/status_button/weapon/button_sword_equip.png");
        sword = new Image("Resource/status_button/weapon/button_sword.png");
        bow_e = new Image("Resource/status_button/weapon/button_bow_equip.png");
        bow = new Image("Resource/status_button/weapon/button_bow.png");
        spear_e = new Image("Resource/status_button/weapon/button_spear_equip.png");
        spear = new Image("Resource/status_button/weapon/button_spear.png");

        medicine_large_ima = new Image("Resource/status_button/button_medicine-large-hp.png");
        medicine_small_ima = new Image("Resource/status_button/button_medicine-small-hp.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(texture_color);
        graphics.fillRect(0,0,1280,960);
        graphics.setLineWidth(10);
        graphics.setColor(text_color);
        graphics.drawRect(0,0,900,400);
        graphics.drawRect(0,400,900,560);
        graphics.drawRect(900,0,380,960);
        graphics.drawImage(character_face,100,50);

        graphics.drawImage(status_hp,550 ,50);
        graphics.drawString( health+"/"+maxhealth,700,65);

        graphics.drawImage(status_lv,550 ,100);
        graphics.drawString("    "+lv,720,115);

        graphics.drawImage(status_exp,550,150);
        graphics.drawString("  "+exp+"/100",700,165);

        graphics.drawImage(status_money,550 ,200);
        graphics.drawString("   "+money,720,215);

        graphics.drawImage(status_ATK,550,300);
        graphics.drawString("   "+ATK,720,315);

        graphics.drawImage(status_equip,1030,100);
        graphics.drawImage(status_item,1030,250);
        graphics.drawImage(status_intro,990,550);
        graphics.drawImage(status_exit,1030,700);
        System.out.println("m"+mode);
        System.out.println("s:"+sword_equip);
        System.out.println("b:"+bow_equip);
        System.out.println("sp"+spear_equip);
        if(mode==1){
            if(sword_equip==1){
                graphics.drawImage(sword_e,370,500);
                graphics.drawImage(bow,270,700);
                graphics.drawImage(spear,470,700);
            }
            if(bow_equip==1){
                graphics.drawImage(sword,370,500);
                graphics.drawImage(bow_e,270,700);
                graphics.drawImage(spear,470,700);
            }
            if(spear_equip==1){
                graphics.drawImage(sword,370,500);
                graphics.drawImage(bow,270,700);
                graphics.drawImage(spear_e,470,700);
            }
        }
        if(mode==2){
            graphics.drawImage(medicine_large_ima,100,500);
            graphics.drawString("x"+ medicine_large,640,540);
            graphics.drawImage(medicine_small_ima,100,600);
            graphics.drawString("x"+ medicine_small,640,640);
        }

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(mode==0){
            gameContainer.exit();
        }

        if(mode==3){

        }

        if(mode==4){
            stateBasedGame.enterState(32,new FadeOutTransition(),new FadeInTransition());
            mode=5;
        }

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
            Update update = new Update();
            try {
                update.ATK_update(ATK);
                update.Exp_update(exp);
                update.Health_update(health);
                update.Lv_update(lv);
                update.MaxHealth_update(maxhealth);
                update.Money_update(money);
                update.Medicine_large_update(medicine_large);
                update.Medicine_small_update(medicine_small);
                update.Sowrd_update(sword_equip);
                update.Bow_update(bow_equip);
                update.Spear_update(spear_equip);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            mode = 5;
            getdata=1;
            stateBasedGame.enterState(31, new FadeOutTransition(), new FadeInTransition());
        }
    }
    public void mouseClicked(int button, int x, int y, int clickCount){
        if(button ==Input.MOUSE_LEFT_BUTTON){
            if(x > 1030 && x < 1150 && y > 100 && y < 156){
                mode = 1;
            }
            if(x > 1030 && x < 1150 && y > 250 && y < 306){
                mode = 2;
            }
            if(x > 1030 && x < 1150 && y > 400 && y < 456){
                mode = 3;
            }
            if(x > 990 && x < 990+198 && y > 550 && y < 550+56){
                mode = 4;
            }
            if(x > 1030 && x < 1150 && y > 700 && y < 756){
                mode = 0;
            }
        }
        if(mode==1){
            if(button ==Input.MOUSE_LEFT_BUTTON) {
                if( x > 370 && x < 514 && y > 500 && y < 556){
                    sword_equip = 1;
                    bow_equip = 0;
                    spear_equip = 0;
                    ATK = 10;
                    Update update = new Update();
                    try {
                        update.Spear_update(sword_equip);
                        update.Bow_update(bow_equip);
                        update.Spear_update(spear_equip);
                        update.ATK_update(ATK);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("press sword");
                }
                if(x > 270 && x < 380 && y > 700 && y < 756){
                    sword_equip = 0;
                    bow_equip = 1;
                    spear_equip = 0;
                    ATK = 7;
                    Update update = new Update();
                    try {
                        update.Spear_update(sword_equip);
                        update.Bow_update(bow_equip);
                        update.Spear_update(spear_equip);
                        update.ATK_update(ATK);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("press bow");
                }
                if(x > 470 && x < 613 && y > 700 && y < 756){
                    sword_equip = 0;
                    bow_equip = 0;
                    spear_equip = 1;
                    ATK = 8;
                    Update update = new Update();
                    try {
                        update.Spear_update(sword_equip);
                        update.Bow_update(bow_equip);
                        update.Spear_update(spear_equip);
                        update.ATK_update(ATK);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("press spear");
                }
            }
        }
        if(mode==2){
            if(button ==Input.MOUSE_LEFT_BUTTON) {
                if(x > 100 && x < 678 && y > 500 && y < 576){
                    if(health!=maxhealth && medicine_large>0){
                        if(health+50>maxhealth){
                            health = maxhealth;
                            medicine_large--;
                        }
                        else{
                            health += 50;
                            medicine_large--;
                        }
                    }
                }
                if(x > 100 && x < 678 && y > 600 && y < 676){
                    if(health!=maxhealth  && medicine_small>0){
                        if(health+20>maxhealth){
                            health = maxhealth;
                            medicine_small--;
                        }
                        else{
                            health += 20;
                            medicine_small--;
                        }
                    }
                }
            }
        }
    }
}
