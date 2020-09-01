package example.javasampleokiba.swinglib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import javasampleokiba.swinglib.SimpleMenuBuilder;

/**
 * SimpleMenuBuilderクラスの実行例
 */
public class SimpleMenuBuilderExample extends JFrame {

    public static void main(String[] args) {
        new SimpleMenuBuilderExample().setVisible(true);
    }

    public SimpleMenuBuilderExample() {
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * メニューバー作成
         */
        SimpleMenuBuilder menuBarBuilder = new SimpleMenuBuilder();
        menuBarBuilder.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand() + "が押されたよ！");
            }
        });

        menuBarBuilder.addMenu("File")
            .addMenuItem("New")
            .addMenuItem("Save")
            .addCheckBoxMenuItem("Auto Save", true)
            .addSeparator()
            .addMenuItem("Close")
        .endMenu()
        .addMenu("Show")
            .addMenu("Zoom")
                .addRadioButtonMenuItem(new String[]{"200%", "100%", "50%"}, 1)
                .endMenu()
        .endMenu();

        setJMenuBar(menuBarBuilder.buildMenuBar());

        /*
         * ポップアップメニュー作成
         */
        SimpleMenuBuilder popupMenuBuilder = new SimpleMenuBuilder();
        popupMenuBuilder.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand() + "が押されたよ！");
            }
        });

        popupMenuBuilder.addMenu("Edit")
            .addMenuItem("Copy")
            .addMenuItem("Cut")
            .addMenuItem("Paste")
        .endMenu()
        .addSeparator()
        .addMenuItem("Properties");

        final JPopupMenu menu = popupMenuBuilder.buildPopupMenu();

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
}