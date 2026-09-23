import java.awt.*;

public abstract class GameObject{
    protected double x;
    protected double y;
    protected int w;
    protected int h;
    protected boolean alive = true;
    public GameObject(int x,int y,int w,int h){
        this.x =x;
        this.y =y;
        this.w =w;
        this.h =h;

    }
    public abstract void update( );


    public abstract void onCol( GameObject obj);


    public abstract  void draw(Graphics g);



    public   boolean Col(GameObject obj ){
        if(obj.x> x+w){return false; }
        if(x> obj.x + obj.w){return false; }
        if (y> obj.y+obj.h){return false; }
        if( obj.y>  y+h){return false; }
        return true;
    }


}
