import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GNumSquare extends GCompound {
    public static int dimension = 30;
    GLabel num;
    GRect rect;
    int value = -1;
    public MouseListener m;
    public GNumSquare(){
        rect = new GRect(dimension,dimension);
        add(rect);
        num = new GLabel("");
        add(num,5,getHeight()-5);
        num.setFont(new Font(num.getFont().getFontName(),Font.BOLD,30));
    }
    public void setNum(int num){
        value = num;
        this.num.setLabel(num+"");

    }
    public void setNum(String num){
        this.num.setLabel(num);
    }
    public void setColor(Color c){
        rect.setFilled(true);
        rect.setFillColor(c);
    }

//    @Override
//    public void addMouseListener(MouseListener mouseListener) {
//        rect.addMouseListener(mouseListener);
//        num.addMouseListener(mouseListener);
//    }


    @Override
    public void addMouseListener(MouseListener mouseListener) {
        super.addMouseListener(mouseListener);
        m = mouseListener;
    }
}
