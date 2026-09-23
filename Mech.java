import java.awt.*;

public class Mech  extends GameObject {

    @Override
    public void update(){
        if( tic>0) {
            if (player.getDirection()==Direction.UP) {
                y = player.returnY() - 50;
                System.out.println("UP");
                tic--;
            }
            if (player.getDirection()==Direction.DOWN) {
                y = player.returnY()  + 50;
                System.out.println("DOWN");
                tic--;
            }
            if (player.getDirection()==Direction.LEFT) {
                x = player.returnX() - 50;
                System.out.println("LEFT");
                tic--;
            }
            if (player.getDirection()==Direction.RIGHT) {
                x = player.returnX()  + 50;
                System.out.println("RITE");
                tic--;
            }

        }
        tivDelete-=1;
        if(tivDelete<=0){
            alive=false;
            canAtac = true;
        }else{canAtac= false;}
    }
    @Override
    public void draw(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect((int) x,(int)y,w,h);
    }
    @Override
    public void onCol(GameObject obj){


    }
    private int tic = 1;
    private int tivDelete = 20;

    Player player;


    boolean canAtac = true;
    public Mech(int x,int y,Player player){
        this.player=player;
        super( (int)  player.returnX(),(int) player.returnY(),40,25);
    }

}
