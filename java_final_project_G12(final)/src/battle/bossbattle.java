package battle;

import java.awt.Font;
import java.util.Random;

import Get_Update_data.Get;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import static java.lang.Thread.sleep;

public class bossbattle extends BasicGameState {

    Font font;
    TrueTypeFont font1;

    Image background;
    Image god;
    Image chabattle;
    Color textcolor;

    int bosshealthmax;
    int bosshealth;

    int round;

    int health;
    int maxhealth;
    int money;
    int lv;
    int ATK;
    int exp;
    int getdata;

    int sword_equip;
    int bow_equip;
    int spear_equip;
    float defmode;
    int md_large;
    int md_small;
    Image attack;
    Image defend;
    Image bow_e;
    Image bow;
    Image spear_e;
    Image spear;
    Image sword_e;
    Image sword;
    Image pilllarge;
    Image pillsmall;

    private SpriteSheet swordeffect;
    private SpriteSheet boweffect;
    private SpriteSheet speareffect;
    private Animation swordanimate;
    private Animation bowanimate;
    private Animation spearanimate;

    int sworddraw;
    int bowdraw;
    int speardraw;
    int delay;

    private int musicplayed;
    private Sound music;

    int bossdelay;


    public bossbattle(int state) {
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        getdata=1;
        round=1;
        defmode=1;
        delay = 0;
        bossdelay=0;
        musicplayed=1;
        background = new Image("Resource/background.jpg");
        god = new Image("Resource/enemy/God.png");
        chabattle = new Image("Resource/character/chabattle.png");
        textcolor = new Color(166,20,20);
        font = new Font("LucidaBrightItalic",Font.BOLD,40);
        font1 = new TrueTypeFont(font,true);
        music = new Sound("Resource/Boss_battle.ogg");
        sword_e = new Image("Resource/status_button/weapon/button_sword_equip.png");
        sword = new Image("Resource/status_button/weapon/button_sword.png");
        bow_e = new Image("Resource/status_button/weapon/button_bow_equip.png");
        bow = new Image("Resource/status_button/weapon/button_bow.png");
        spear_e = new Image("Resource/status_button/weapon/button_spear_equip.png");
        spear = new Image("Resource/status_button/weapon/button_spear.png");
        attack = new Image("Resource/battle/button_attack.png");
        defend = new Image("Resource/battle/button_defend.png");
        pilllarge = new Image("Resource/battle/button_pilllarge.png");
        pillsmall = new Image("Resource/battle/button_pillsmall.png");

        swordeffect = new SpriteSheet("Resource/sword.png",256,256);
        boweffect = new SpriteSheet("Resource/bow.png",256,256);
        speareffect = new SpriteSheet("Resource/spear.png",256,256);

        swordanimate = new Animation(swordeffect,100);
        bowanimate = new Animation(boweffect,100);
        spearanimate = new Animation(speareffect,100);

        bosshealth = 500;
        bosshealthmax = 500;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background,0,0);
        graphics.drawImage(god,470,50);
        graphics.drawImage(chabattle,50,600,50+85,600+85,85*5,0,85*6,85);
        graphics.setColor(textcolor);
        graphics.fillRect(0,700,1280,260);
        graphics.setColor(Color.black);
        graphics.drawImage(pilllarge,660,750);
        graphics.drawImage(pillsmall,660,850);
        graphics.drawString("x"+md_large,790,770);
        graphics.drawString("x"+md_small,790,870);
        graphics.setColor(Color.black);
        graphics.setFont(font1);
        graphics.drawString("Boss:"+bosshealth+"/"+bosshealthmax,870,750);
        graphics.setColor(Color.black);
        graphics.drawString("Hero:"+health+"/"+maxhealth,870,850);
        graphics.drawImage(attack,80,750);
        graphics.drawImage(defend,80,850);
        if(sword_equip==1){
            graphics.drawImage(sword_e,350,750);
            graphics.drawImage(bow,270,850);
            graphics.drawImage(spear,470,850);
        }
        if(bow_equip==1){
            graphics.drawImage(sword,350,750);
            graphics.drawImage(bow_e,270,850);
            graphics.drawImage(spear,470,850);
        }
        if(spear_equip==1){
            graphics.drawImage(sword,350,750);
            graphics.drawImage(bow,270,850);
            graphics.drawImage(spear_e,470,850);
        }
        if(sworddraw==1){
            swordanimate.draw(600,200);
            System.out.println(delay);
            delay++;
            if(delay==201){
                delay=0;
                sworddraw=0;
            }
        }
        if(bowdraw==1){
            spearanimate.draw(600,200);
            delay++;
            System.out.println(delay);
            if(delay==201){
                delay=0;
                bowdraw=0;
            }
        }
        if(speardraw==1){
            bowanimate.draw(600,200);
            System.out.println(delay);
            delay++;
            if(delay==201){
                delay=0;
                speardraw=0;
            }
        }
        if(round==2){
            bossdelay++;
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(musicplayed==1){
            music.play();
            musicplayed=0;
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
                spear_equip=get.getSpear_equip();
                sword_equip=get.getSword_equip();
                bow_equip=get.getBow_equip();
                md_large=get.getMedicine_large();
                md_small=get.getMedicine_small();
                getdata=0;

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(bosshealth<=0){
            if(music.playing()){
                music.stop();
                musicplayed=1;
            }
            stateBasedGame.enterState(666,new FadeOutTransition(),new FadeInTransition());
        }
        if(health<=0){
            if(music.playing()){
                music.stop();
                musicplayed=1;
            }
            stateBasedGame.enterState(555,new FadeOutTransition(),new FadeInTransition());
        }

        if(round==2){
            if(bossdelay==300){
                Random ran = new Random();
                if(ran.nextInt()%50==0){
                    health-=40;
                    round=1;
                }
                if(ran.nextInt()%10==0){
                    health-=30*defmode;
                    round=1;
                }
                if(ran.nextInt()%5==0){
                    health-=20*defmode;
                    round=1;
                }
                health-=10*defmode;
                round=1;
            bossdelay=0;
            }
        }
    }

    public void mouseClicked(int button, int x, int y, int clickCount){
        Random ran = new Random();
        if(round==1){
            if(button ==Input.MOUSE_LEFT_BUTTON){
                if(x > 80&& x < 80+149 && y > 750 && y <750+56){
                    if(sword_equip==1){
                        speardraw=0;
                        bowdraw=0;
                        sworddraw=1;
                        if(ran.nextInt()%4==0){
                            bosshealth-=ATK*2;
                            round=2;
                        }
                        bosshealth-=ATK;
                        round=2;
                    }
                    if (bow_equip==1){
                        speardraw=0;
                        bowdraw=1;
                        sworddraw=0;
                        if(ran.nextInt()%2==0){
                            bosshealth-=ATK*4;
                            round=2;
                        }
                        bosshealth-=ATK;
                        round=2;
                    }
                }
                    if(spear_equip==1){
                        speardraw=1;
                        bowdraw=0;
                        sworddraw=0;
                        if(ran.nextInt()%3==0){
                            bosshealth-=ATK*2;
                            round=2;
                        }
                        bosshealth-=ATK;
                        round=2;
                    }
            }
                    System.out.println("attack");
                }

                if(x > 80&& x < 240&& y > 850&& y <850+56){
                    System.out.println("defend");
                    defmode= (float) 0.5;
                    round=2;
                }
                if(x > 350&& x < 350+144&& y > 750&& y < 750+56){
                    System.out.println("sword");
                    if(spear_equip==1 || bow_equip==1){
                        spear_equip=0;
                        sword_equip=1;
                        bow_equip=0;
                        ATK=10;
                        sworddraw=0;
                        speardraw=0;
                        bowdraw=0;
                        round=2;
                        }
                }
                if(x > 270&& x < 270+110&& y > 850&& y <850+56){
                    System.out.println("bow");
                    if(spear_equip==1 || sword_equip==1){
                        spear_equip=0;
                        sword_equip=0;
                        bow_equip=1;
                        ATK=7;
                        speardraw=0;
                        bowdraw=0;
                        sworddraw=0;
                        round=2;
                    }
                }
                if(x > 470&& x < 470+143&& y > 850&& y <850+56){
                    System.out.println("spear");
                    if(bow_equip==1 || sword_equip==1){
                        spear_equip=1;
                        sword_equip=0;
                        bow_equip=0;
                        ATK=8;
                        speardraw=0;
                        bowdraw=0;
                        sworddraw=0;
                        round=2;
                    }
                }
                if(x > 660&& x < 660+156&& y > 750&& y <750+40){
                    if(health<=50 && md_large>0){
                        health+=50;
                        md_large--;
                        speardraw=0;
                        bowdraw=0;
                        sworddraw=0;
                        round=2;
                    }
                    if(health>=50 && health<100 && md_large>0){
                        health=100;
                        md_large--;
                        speardraw=0;
                        bowdraw=0;
                        sworddraw=0;
                        round=2;
                    }
                    System.out.println("pilllarge");
                }
                if(x > 660&& x < 660+156 && y > 850&& y <850+40){
                    if(health<80 && md_small>0){
                        health+=20;
                        md_small--;
                        speardraw=0;
                        bowdraw=0;
                        sworddraw=0;
                        round=2;
                    }
                    if(health>=80 && health<100 && md_small>0){
                        health=100;
                        md_small--;
                        speardraw=0;
                        bowdraw=0;
                        sworddraw=0;
                        round=2;
                    }
                    System.out.println("pillsmall");
                }
            }

    @Override
    public int getID() {
        return 500;
    }
}
