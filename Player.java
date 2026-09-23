import java.awt.*;

public class Player extends GameObject {
    @Override
    public void update(){

        move();
        timeFirePulya--;



        if(timeInvs>=0){
            timeInvs--;
        }

        canDamage = (timeInvs<=0);

    }
    @Override
    public  void onCol( GameObject obj){
        if(obj instanceof Enemy){
            if(canDamage){
                hp-=1;
                timeInvs = 15;
            }

        }

    }
    @Override
    public void draw(Graphics g ){
        g.setColor(Color.GREEN);
        g.fillRect((int)x,(int)y,w,h);
    }

    private static final int SPEED = 5;
    private static final int MAX_HP = 3;
    private static final int INVINSIBILITY_TIME = 15;


    private int hp = MAX_HP;

    private int timeFirePulya;

    private int coins = 0;
    private int score = 0;
    private int timeInvs = 0;



   private boolean canDamage;
   private boolean up,down,left,right;


   private Direction direction = Direction.UP;
   private Weapon weapon  = Weapon.MECH;

   public void switchWeapon(){
       Weapon[] allWeapons = Weapon.values();
       int nextIndex = (weapon.ordinal() + 1) % allWeapons.length;
       weapon = allWeapons[nextIndex];
   }
   public Weapon returnWeapon(){
       return weapon;
   }

   public void settimeFirePulya(int timeFirePulya){
       this.timeFirePulya=timeFirePulya;

   }

   public int returnTimeFirePulya(){
       return timeFirePulya;
   }

   public void setDirection(Direction direction){
       this.direction = direction;
   }

   public Direction getDirection(){
       return direction;
   }

   public boolean returnDeath(){
       return  hp<=0;
   }

    public void takeDamage(int damage){
        hp-=damage;
    }

    private void move(){
        if( up&& y>0){
            y -=  SPEED;
        }
        if( down&& y<638){
            y +=  SPEED;
        }
        if(left&& x>0){
            x -=  SPEED;
        }
        if(right&& x< 658){
            x +=  SPEED;
        }
    }

    public double returnX(){
        return x;
    }

    public double returnY(){
        return y;
    }

    public void setMoving(boolean up, boolean down,boolean left,boolean right){
       this.up=up;
       this.down=down;
       this.left=left;
       this.right=right;

    }

    public void restart(int x,int y){
        this.x=x;
        this.y=y;
        hp=MAX_HP;
        coins=0;
        score=0;
        timeInvs = 0;

    }
    public void kill(){
        hp=0;
        System.out.println("Kill!");
    }

    public void addScore(int addScore){
        score+=addScore;
    }

    public void addCoins(int addCoins){
        score+=addCoins;
    }
    public int returnScore(){
        return score;
    }
    public int returnCoin(){
        return coins;
    }

    public Player(int x,int y){
        super(x,y,25,25);

    }
}

