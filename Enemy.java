import java.awt.*;

public class Enemy extends GameObject{
    @Override
    public void update(){
        dx=player.returnX()-x;
        dy=player.returnY()-y;
        //теорема пифагора
        distante = Math.sqrt(dx*dx+dy*dy);

        x+=dx/distante * speed;
        y+=dy/distante * speed;


        if(hp<=0 ){
            alive = false;
            player.addScore(10);
        }

        timeInvs-=1;
        //canDamage=(timeInvs<0);
    }

    @Override
    public void draw(Graphics g ){
        g.setColor(Color.RED);
        g.fillRect((int)x,(int)y,w,h);
    }
    @Override
    public void onCol(GameObject obj){
        if(timeInvs<=0) {
            if (obj instanceof Player || obj instanceof    Mech || obj instanceof  Pulya) {
                timeInvs = 21;
                hp -= 1;

            }
        }

    }

    private Player player;

   final static private int speed = 3;
    private int hp =1;

    private int timeInvs;
    //КАТЕТЫ
   private double dx;
   private double dy;
    //ГИПОТЕНУЗА
   private double distante;

    public Enemy(int x,int y,Player player){

        this.player = player;
        super(x,y,20,20);
    }
}
