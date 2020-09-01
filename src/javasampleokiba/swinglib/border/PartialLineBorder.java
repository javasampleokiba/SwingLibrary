package javasampleokiba.swinglib.border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.EnumSet;

import javax.swing.border.Border;

/**
 * アンダーライン、オーバーライン等、部分的なLineBorderを引くクラス
 * 
 * @author javasampleokiba
 */
public class PartialLineBorder implements Border {

    /**
     * 線を引く位置
     */
    public enum Position {
        /** コンポーネントの上部 */
        OVER,
        /** コンポーネントの下部 */
        UNDER,
        /** コンポーネントの左側 */
        LEFT,
        /** コンポーネントの右側 */
        RIGHT,
    }

    private final EnumSet<Position> positions_;
    private final Color lineColor_;
    private final int thickness_;

    /**
     * 線を引く位置、色、太さを指定して{@code PartialLineBorder}を生成します。
     * 
     * @param position   線を引く位置
     * @param lineColor  線の色
     * @param thickness  線の太さ
     */
    public PartialLineBorder(Position position, Color lineColor, int thickness) {
        this(EnumSet.of(position), lineColor, thickness);
    }

    /**
     * 線を引く位置（複数指定可）、色、太さを指定して{@code PartialLineBorder}を生成します。
     * 
     * @param positions  線を引く位置（複数指定可）
     * @param lineColor  線の色
     * @param thickness  線の太さ
     */
    public PartialLineBorder(EnumSet<Position> positions, Color lineColor, int thickness) {
        positions_ = positions;
        lineColor_ = lineColor;
        thickness_ = thickness;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Color oldColor = g.getColor();
        g.setColor(lineColor_);
        for (Position pos : positions_) {
            switch (pos) {
                case OVER:
                    for(int i = 0; i < thickness_; i++)  {
                        g.drawLine(x, y + i, x + width, y + i);
                    }
                    break;

                case UNDER:
                    for(int i = 0; i < thickness_; i++)  {
                        g.drawLine(x, y + height - i - 1, x + width, y + height - i - 1);
                    }
                    break;

                case LEFT:
                    for(int i = 0; i < thickness_; i++)  {
                        g.drawLine(x + i, y, x + i, y + height - 1);
                    }
                    break;

                case RIGHT:
                    for(int i = 0; i < thickness_; i++)  {
                        g.drawLine(x + width - i - 1, y, x + width - i - 1, y + height - 1);
                    }
                    break;

                default:
                    break;
            }
        }
        g.setColor(oldColor);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        Insets insets = new Insets(0, 0, 0, 0);
        for (Position pos : positions_) {
            switch (pos) {
                case OVER:
                    insets.top = thickness_;
                    break;

                case UNDER:
                    insets.bottom = thickness_;
                    break;

                case LEFT:
                    insets.left = thickness_;
                    break;

                case RIGHT:
                    insets.right = thickness_;
                    break;

                default:
                    break;
            }
        }
        return insets;
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}