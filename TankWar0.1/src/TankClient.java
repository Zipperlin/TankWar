import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankClient extends Frame{

    public static final int GAME_WIDTH=800;
    public static final int GAME_HEIGHT=600;



    Tank myTank=new Tank(50,50,this);
    Missile m=null;

    Image offScreenImage =null;

    private class KeyMonitor extends KeyAdapter {
        public void keyPressed(KeyEvent event) {
            myTank.KeyPressed(event);
        }

        public void keyReleased(KeyEvent event) {
            myTank.KeyReleased(event);
        }
    }

    public void update(){

    }

    public void paint(Graphics g){
        if(m!=null) {
            m.draw(g);
        }
        myTank.draw(g);
    }

    public void update(Graphics g){
        if(offScreenImage==null)
        {
            offScreenImage=this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffscreen=offScreenImage.getGraphics();
        Color c=gOffscreen.getColor();
        gOffscreen.setColor(Color.green);
        gOffscreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        paint(gOffscreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    public void launchFrame(){
        this.setLocation(400,300);
        this.setSize(800,600);
        this.setTitle("TankWar");
        //短小不会扩展使用匿名类
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyMonitor());
        setVisible(true);
        setResizable(false);
        setBackground(Color.green);

        new Thread(new PaintThread()).start();
    }

    public static void main(String[] args){
        TankClient tankClient=new TankClient();
        tankClient.launchFrame();

    }

    private class PaintThread implements Runnable {
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}