import acm.graphics.GCompound;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GuessBoard extends GCompound {
    public GNumSquare[][] squares;
    public GuessBoard(int dimensions){
        squares = new GNumSquare[dimensions][dimensions];
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                squares[i][j] = new GNumSquare();
                add(squares[i][j],squares[i][j].getWidth()*i,squares[i][j].getHeight()*j);
            }
        }
    }
    public void check(MineGrid m){
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if (m.get(i,j) == squares[i][j].value){
                    squares[i][j].setColor(new Color(0,125,0));
                }
                else {
                    squares[i][j].setColor(Color.gray);
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            if (i+k >= 0 && i+k<squares.length && l+j >= 0 && l+j<squares[i].length){
                                if (m.get(i+k,l+j)==squares[i][j].value) {
                                    squares[i][j].setColor(new Color(125,125,0));
                                }
                            }
                        }
                    }
                }
                squares[i][j].removeMouseListener(squares[i][j].m);
            }
        }
    }
}
