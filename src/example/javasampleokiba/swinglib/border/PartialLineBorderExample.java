package example.javasampleokiba.swinglib.border;

import java.awt.Color;
import java.awt.Dimension;
import java.util.EnumSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javasampleokiba.swinglib.border.PartialLineBorder;

/**
 * PartialLineBorderクラスの実行例
 */
public class PartialLineBorderExample extends JFrame {

    public static void main(String[] args) {
        new PartialLineBorderExample().setVisible(true);
    }

    public PartialLineBorderExample() {
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        {
            JLabel l = new JLabel("This is an underline border.");
            // 太さ5のピンクのアンダーラインを引く
            l.setBorder(new PartialLineBorder(PartialLineBorder.Position.UNDER,
                    Color.pink, 5));
            panel.add(l);
        }

        {
            JButton b = new JButton("こんな使い方もできます。");
            b.setPreferredSize(new Dimension(200, 30));
            EnumSet<PartialLineBorder.Position> set = EnumSet.of(
                    PartialLineBorder.Position.LEFT,
                    PartialLineBorder.Position.RIGHT);
            // 太さ10の緑の左右のラインを引く
            b.setBorder(new PartialLineBorder(set, Color.green, 10));
            panel.add(b);
        }
    }
}