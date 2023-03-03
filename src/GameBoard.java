import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoard extends JPanel implements MouseListener {
    int black = 1;
    int white = -1;
    Point clickedP = new Point(0,0);
    int count = 0;
    int board[][] = new int[8][8];
    int x,y;

    GameBoard(){
        setSize(640,640);
        setBorder(new LineBorder(new Color(91, 78, 75), 4));
        setBackground(new Color(218, 181, 144));
        addMouseListener(this);

        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                board[i][j] = 0;
                if (((i==3)&&(j==3))||((i==4)&&(j==4))) board [i][j] = -1;
                if (((i==4)&&(j==3))||((i==3)&&(j==4)))board [i][j] = 1;
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        int width = 640;
        int height = 640;
        int side = width/8;

        for(int i = 0; i<9; i++){
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(1));
            g2.drawLine(0,80*i, width, 80*i);
            g2.drawLine(80*i,0, 80*i, height);
        }
        // 배열에 있는 0, -1 ,1 보고 돌 그려줌(0은 null 1은 검 -1은 백)
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if (board [i][j] == 1){
                    g2.setColor(Color.black);
                    g2.fillOval(i*80, j*80, side, side);
                }else if (board [i][j] == -1){
                    g2.setColor(Color.white);
                    g2.fillOval(i*80, j*80, side, side);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickedP = e.getPoint();
        x = e.getX();
        y = e.getY();
        if (board[x/80][y/80] != 0) // 더블클릭할때 돌이 사라지는 것을 방지
            return;
        Check check = new Check(board,x,y);
        repaint();

        if(check.passCheck(x,y) == false) {
            check.ccount++;
            check.turn = (-1) * check.turn;
            System.out.println("PASS");
            check.score();
            JOptionPane.showMessageDialog(null, "NO MORE TO MOVE");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
