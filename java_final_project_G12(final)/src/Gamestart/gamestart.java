package Gamestart;

import Castle.*;
import Control_Introduction.Introduction;
import Town1.Town1_t;
import Menu.Menu;
import Menu.Winner;
import Menu.Lose;
import Starting_forest.Startforest;
import Starting_forest.Startforest_t;
import Statue.Statue;
import Town1.Town1;



import Town1.Town1_s;

import Starting_forest.Startforest_s;

import battle.bossbattle;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class gamestart extends StateBasedGame{

    public static final String rpgtitle = "game.rpg game";
    public static final int menu=0;
    public static final int introduction=1;
    public static final int statue = 2;
    public static final int starting_forest=11;
    public static final int startforest_t=14;
    public static final int town1_t=22;
    public static final int castle1_t=32;
    public static final int town1 = 21;
    public static final int castle1 = 31;
    public static final int starting_forest_s=111;
    public static final int town1_s = 211;
    public static final int castle1_s = 311;

    public static final int finalboss = 500;

    public static final int winner = 666;
    public static final int lose = 555;


    public gamestart(String rpgtitle) {
        super(rpgtitle);

        this.addState(new Menu(menu));
        this.addState(new Introduction(introduction));
        this.addState(new Statue(statue));
        this.addState(new Town1(town1));
        this.addState(new Startforest(starting_forest));
        this.addState(new Startforest_t(startforest_t));
        this.addState(new Castle1(castle1));
        this.addState(new Town1_s(town1_s));
        this.addState(new Town1_t(town1_t));
        this.addState(new Startforest_s(starting_forest_s));
        this.addState(new Castle1_s(castle1_s));
        this.addState(new Castle1_t(castle1_t));
        this.addState(new bossbattle(finalboss));
        this.addState(new Lose(lose));
        this.addState(new Winner(winner));
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.getState(menu).init(gameContainer,this);
        this.getState(introduction).init(gameContainer,this);
        this.getState(statue).init(gameContainer,this);
        this.getState(starting_forest).init(gameContainer,this);
        this.getState(startforest_t).init(gameContainer, this);
        this.getState(town1).init(gameContainer,this);
        this.getState(castle1).init(gameContainer,this);
        this.getState(starting_forest_s).init(gameContainer,this);
        this.getState(town1_s).init(gameContainer,this);
        this.getState(castle1_s).init(gameContainer,this);
        this.getState(castle1_t).init(gameContainer,this);
        this.getState(finalboss).init(gameContainer,this);
        this.getState(winner).init(gameContainer,this);
        this.getState(lose).init(gameContainer,this);
        enterState(menu);
    }

    public static void main(String[] args){
        AppGameContainer rpggc;

        try{
            extractNatives();
            extractResources();
            rpggc = new AppGameContainer(new gamestart(rpgtitle));
            rpggc.setTargetFrameRate(60);
            rpggc.setDisplayMode(1280,960,false);
            rpggc.start();
        }catch (SlickException e){
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    /**
     * Auto extract all needed natives in the jar, and detect the os you are using.
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static void extractNatives() throws NoSuchFieldException, IllegalAccessException {
        String osName = System.getProperty("os.name").toLowerCase();
        String osArch = System.getProperty("os.arch").toLowerCase();

        try {
            File nativesPath = new File("natives/");
            if (!nativesPath.exists() || !nativesPath.isDirectory()) {
                System.out.println(extractFromJar("/natives.zip"));
                zipDecompress("natives.zip", "natives");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (osName.startsWith("windows")) {
            if (osArch.matches("^(x8664|amd64|ia32e|em64t|x64)$")) {
                System.setProperty("java.library.path", "natives/");
                Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
                fieldSysPath.setAccessible(true);
                fieldSysPath.set(null, null);
            } else {
                System.setProperty("java.library.path", "natives/");
                Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
                fieldSysPath.setAccessible(true);
                fieldSysPath.set(null, null);
            }
        } else if (osName.startsWith("linux")) {
            if (osArch.matches("^(x8664|amd64|ia32e|em64t|x64)$")) {
                System.setProperty("java.library.path", "natives/linux_64");
                Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
                fieldSysPath.setAccessible(true);
                fieldSysPath.set(null, null);
            } else {
                System.setProperty("java.library.path", "natives/linux_32");
                Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
                fieldSysPath.setAccessible(true);
                fieldSysPath.set(null, null);
            }
        } else if (osName.endsWith("osx")) {
            System.setProperty("java.library.path", "natives/osx");
            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);
        } else {
            throw new IllegalArgumentException("OS UNKNOWN: " + osName);
        }
    }

    /**
     * Auto extract all game needed resources in the jar file.
     */
    private static void extractResources() {
        try {
            File resPath = new File("Resource/");
            if (!resPath.exists() || !resPath.isDirectory()) {
                System.out.println(extractFromJar("/Resource.zip"));
                zipDecompress("Resource.zip", "Resource");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Export a resource embedded into a Jar file to the local file path.
     *
     * @param resourceName ie.: "/SmartLibrary.dll"
     * @return The path to the exported resource
     * @throws Exception
     * @see "https://stackoverflow.com/questions/10308221/how-to-copy-file-inside-jar-to-outside-the-jar"
     */
    private static String extractFromJar(String resourceName) throws Exception {
        String jarFolder;
        jarFolder = new File(gamestart.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
        try (InputStream stream = gamestart.class.getResourceAsStream(resourceName); OutputStream resStreamOut = new FileOutputStream(jarFolder + resourceName)) {
            byte[] buffer = new byte[4096];
            int readBytes;
            if (stream == null) {
                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }
            //note that each / is a directory down in the "jar tree" been the jar the root of the tree

            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        }
        return jarFolder + resourceName;
    }

    /**
     * Decompress zip file in the directory, must at the same directory.
     *
     * @param zip   The zip file you want to decompress
     * @param start The file that contain in the zip
     * @throws IOException
     */
    private static void zipDecompress(String zip, String start) throws IOException {
        ZipFile zipFile = new ZipFile(zip);
        Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
        while (enumeration.hasMoreElements()) {
            ZipEntry zipEntry = enumeration.nextElement();
            if (zipEntry.getName().startsWith(start)) {
                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                int size;
                byte[] buffer = new byte[2048];

                File f = new File(zipEntry.getName());
                if (!zipEntry.isDirectory()) {
                    f.getParentFile().mkdirs();
                    BufferedOutputStream bos = new BufferedOutputStream(
                            new FileOutputStream(zipEntry.getName()), buffer.length);
                    while ((size = bis.read(buffer, 0, buffer.length)) != -1) {
                        bos.write(buffer, 0, size);
                    }
                    bos.flush();
                    bos.close();
                    bis.close();
                } else {
                    f.mkdirs();
                }
            }
        }
        new File(zip).delete();
    }
}