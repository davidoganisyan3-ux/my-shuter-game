import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;


public class GamePanel extends JPanel implements KeyListener {


    boolean upKey = false;
    boolean downKey =  false;
    boolean leftKey = false;
    boolean rightKey =  false;


    boolean gameOver = false;

    int allSpawnEnemy = 0;

    JButton but = new JButton("Restart");


     Timer time;
     Random random = new Random();


public void playerDeath(){
    if(plaer.returnDeath()&&!gameOver){
        time.stop();
        gameOver =true;
        but.setVisible(true);
        repaint();
    }
}

     public int randomInt(int max){
         return random.nextInt(max);
     }

     public void spawn(){
          plaer = new Player(randomInt(658),randomInt(638));
         objects.add(plaer);
          objects.add(new Enemy(randomInt(658),randomInt(638),plaer));
         objects.add(new Enemy(randomInt(658),randomInt(638),plaer));
         objects.add(new Enemy(randomInt(658),randomInt(638),plaer));


     }

     public void spawnVoln(){
         plaer.addScore(100);
         allSpawnEnemy++;
         int spawnEnemy;
         spawnEnemy=allSpawnEnemy;
         while (spawnEnemy>0){
             spawnEnemy--;
             objects.add(new Enemy(randomInt(658),randomInt(638),plaer));
         }
     }


     public void restart(){
         time.start();
         gameOver = false;
          plaer.restart(100,100);
         allSpawnEnemy=1;
         but.setVisible(gameOver);
         objects.clear();
         spawn();


     }

     public boolean haveCoin(){
         for(GameObject obj:objects){
             if(obj instanceof Coin){
                 return true;
             }

         }
         return false;
     }

     public boolean haveEnemy(){
         for(GameObject obj:objects){
             if(obj instanceof Enemy){
                 return true;
             }
         }
         return false;
     }


     public void removeDedObj(){

         for(int i = 0;i<objects.size();i++){
             GameObject obj = objects.get(i);
             if(!obj.alive ){
             objects.remove(i);
                 //System.out.println("YYY");
                 }
         }

         if(!haveCoin()){
             objects.add( new Coin(randomInt(658),randomInt(638),plaer));
         }
         if(!haveEnemy()){
             spawnVoln();

         }


     }


//Игрок

    Player plaer = new Player(100,100);


    Coin coin = new Coin(randomInt(658),randomInt(638),plaer);



        public void checkCol(){
        for(GameObject obj1: objects){

            for(GameObject obj2: objects){
                if(obj1==obj2){
                    continue;
                }
                if(obj1.Col(obj2)){


                    obj1.onCol(obj2);
                }

            }
        }

    }

    Mech mech = new Mech((int)plaer.returnX(), (int)plaer.returnY(),plaer);
    Pulya pulya = new Pulya ((int)plaer.returnX()+13,(int)plaer.returnY()   ,plaer.getDirection()) ;



    ArrayList<GameObject> objects = new
           ArrayList<>();



    JButton[] buts = {but};



    public void keyPressed(KeyEvent e){


        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            plaer.kill();

        }

        if(e.getKeyCode()==KeyEvent.VK_SPACE   ){


            if(plaer.returnWeapon()==Weapon.MECH&& mech.canAtac){
            mech = new Mech ( (int) mech.x, (int) mech.y,plaer);
             objects.add(mech);
            }

            else if (plaer.returnWeapon()==Weapon.PULYA) {

                if(plaer.returnTimeFirePulya()<=0){
                pulya=new Pulya((int) plaer.returnX()+13,(int) plaer.returnY() ,plaer.getDirection());
                objects.add(pulya);

                    plaer.settimeFirePulya(30);
                }
            }
        }


        //СМЕНА ОРУЖИЯ
        if(e.getKeyCode()==KeyEvent.VK_SHIFT){
             plaer.switchWeapon();
        }

        //ДВИЖУХА
        if(e.getKeyCode() == KeyEvent.VK_W){
            upKey = true;
             plaer.setDirection(Direction.UP);
         }
        if(e.getKeyCode() == KeyEvent.VK_S){
            downKey = true;
            plaer.setDirection(Direction.DOWN);
         }
        if(e.getKeyCode() == KeyEvent.VK_A){
            leftKey = true;
            plaer.setDirection(Direction.LEFT);
         }
        if(e.getKeyCode() == KeyEvent.VK_D){
            rightKey = true;
            plaer.setDirection(Direction.RIGHT);
         }
        plaer.setMoving(upKey,downKey,leftKey,rightKey);


    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W)
        {upKey = false;}
        if(e.getKeyCode() == KeyEvent.VK_S)
        { downKey = false;}
        if(e.getKeyCode() == KeyEvent.VK_A)
        {leftKey =false;}
        if(e.getKeyCode() == KeyEvent.VK_D)
        {rightKey = false;}

        plaer.setMoving(upKey,downKey,leftKey,rightKey);
    }
    public void keyTyped(KeyEvent e){}


    public  GamePanel(){

        addKeyListener(this);
        setFocusable(true);
        setLayout(null);

//ОБЩЕЕ ДЛЯ ВСЕХ КНОПОК
        for(JButton bats: buts){
            add(bats);
            bats.setFocusable( false);
        }


//кнопка  RESTART
        but.setBounds(225,200,225,75);
        but.setVisible(false);
        but.addActionListener(e -> {
            restart();
            });




        objects.add(plaer);

        objects.add(new Enemy(100,0,plaer));
        objects.add(new Enemy(0,100,plaer));
        objects.add(new Enemy(50,50,plaer));
       // objects.add(new Enemy(150,0));

        objects.add(coin);





          time = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 for(GameObject obj:objects){
                     obj.update();
                 }
                checkCol();
                removeDedObj();
                playerDeath();
                repaint();

            }
        });
        time.start();

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(gameOver){
            g.setColor(Color.RED);
            g. setFont(new Font("Arial" , Font.BOLD,20));
            g.drawString("Score" + plaer.returnScore(),300,75);
            g. setFont(new Font("Arial" , Font.BOLD,50));
            g.drawString("Game Over!",200,150);

        }
        g.setColor(Color.RED);
        g. setFont(new Font("Arial" , Font.BOLD,20));
        g.drawString("Score:"+ plaer.returnScore(),0,30);

        for(GameObject obj:objects){
            obj.draw(g);
        }

    }
}
