import java.awt.*;

public class Pulya extends GameObject{
    private int speed = 4;
    private Direction direction = Direction.UP;
    @Override
    public   void update( ){
        if(direction== Direction.UP){
            y-=speed;}
        else if(direction== Direction.DOWN){
            y+=speed;}
        else if(direction== Direction.LEFT){
            x-=speed;}
        else if(direction== Direction.RIGHT){
            x+=speed;}
        if(x<0||y<0||y>638||x>658){
            alive=false;
        }
    }
    @Override
    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect((int)x,(int)y,w,h);
    }
    @Override
    public void onCol(GameObject obj){
        if(obj instanceof Enemy){
            alive = false;
        }
    }
    public Pulya(int x ,int y,Direction direction){
        super( x,y,5,15);
        this.direction = direction;
    }
}