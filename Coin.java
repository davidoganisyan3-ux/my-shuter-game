import java.awt.*;

public class Coin extends GameObject{
    public void update(){

    }
    public void draw(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillOval((int)x,(int)y,w,h);

    }
    public void onCol(GameObject obj){
        if(obj instanceof Player){
            alive=false;
            player.addCoins(1);
            player.addScore(100);

        }
    }

     private Player player;

    public Coin(int x,int y,Player player){
        this.player = player;
        super(x,y,25,25);
    }
}