package javasampleokiba.swinglib;

import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 * シンプルなメニューバー/ポップアップメニュー作成クラス.
 * 
 * <br>
 * <p>[使い方]</p>
 * 例として、以下のメニュー構成にする場合、<br>
 * <br>
 * Menu1<br>
 * ├Item1<br>
 * ├SubMenu1<br>
 * │ ├SubItem1<br>
 * │ └SubItem2<br>
 * └Item2<br>
 * Menu2<br>
 * └Item3<br>
 * <br>
 * コードは以下のようになります。
 * 注意点として、ひとつのメニューの項目追加が完了したら直後に必ずendMenuメソッドを呼び出して下さい。
 * 
 * <pre>{@code
 * SimpleMenuBuilder smb = new SimpleMenuBuilder(actionListener);
 * smb.addMenu("Menu1")
 *     .addMenuItem("Item1")
 *     .addMenu("SubMenu1")
 *         .addMenuItem("SubItem1")
 *         .addMenuItem("SubItem2")
 *         .endMenu()                    // <-SubMenu1作成完了
 *     .addMenuItem("Item2")
 *     .endMenu()                    // <-Menu1作成完了
 * .addMenu("Menu2")
 *     .addMenuItem("Item3")
 *     .endMenu();                   // <-Menu2作成完了
 * }</pre>
 * 
 * <p>[注意点]</p>
 * <ul>
 * <li>アクションコマンドには自動的にメニュー(項目)のテキストが設定されます。</li>
 * <li>同じテキストを持つメニュー(項目)は作成できません。</li>
 * </ul>
 * 
 * @author javasampleokiba
 */
public class SimpleMenuBuilder {

    private static final Object SEPARATOR                   = new Object();
    private final List<ActionListener> actionListeners_     = new ArrayList<ActionListener>();
    private final List<Object> items_                       = new ArrayList<Object>();
    private final Deque<JMenu> menuStack_                   = new ArrayDeque<JMenu>();

    /**
     * {@code JMenuBar}オブジェクトを生成して返します。
     * 
     * @return {@code JMenuBar}オブジェクト
     * @throws UnsupportedOperationException  {@code JMenuBar}オブジェクトにセパレータを追加しようとした場合
     */
    public JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        for (Object item : items_) {
            if (item instanceof JMenuItem) {
                JMenuItem menuItem = (JMenuItem) item;
                attachActionListeners(menuItem);
                menuBar.add(menuItem);
            } else {
                throw new UnsupportedOperationException("Menubar can't have separator.");
            }
        }
        return menuBar;
    }

    /**
     * {@code JPopupMenu}オブジェクトを生成して返します。
     * 
     * @return {@code JPopupMenu}オブジェクト
     */
    public JPopupMenu buildPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        for (Object item : items_) {
            if (item instanceof JMenuItem) {
                JMenuItem menuItem = (JMenuItem) item;
                attachActionListeners(menuItem);
                popupMenu.add(menuItem);
            } else {
                popupMenu.addSeparator();
            }
        }
        return popupMenu;
    }

    /**
     * すべてのメニュー項目に共通のアクションリスナーを追加します。
     * 
     * @param listener  アクションリスナー
     * @return このオブジェクトへの参照
     */
    public SimpleMenuBuilder addActionListener(ActionListener listener) {
        actionListeners_.add(listener);
        return this;
    }

    /**
     * すべてのメニュー項目に共通のアクションリスナーを削除します。
     * 
     * @param listener  アクションリスナー
     * @return このオブジェクトへの参照
     */
    public SimpleMenuBuilder removeActionListener(ActionListener listener) {
        actionListeners_.remove(listener);
        return this;
    }

    /**
     * 現在の位置にメニューを追加します。
     * 
     * @param text  メニューのテキスト
     * @return このオブジェクトへの参照
     */
    public SimpleMenuBuilder addMenu(String text) {
        JMenu m = new JMenu(text);
        if (menuStack_.isEmpty()) {
            items_.add(m);
        } else {
            menuStack_.peek().add(m);
        }
        menuStack_.push(m);
        return this;
    }

    /**
     * 現在のメニューへのメニュー項目追加を終了します。
     * 
     * @return このオブジェクトへの参照
     */
    public SimpleMenuBuilder endMenu() {
        menuStack_.pop();
        return this;
    }

    /**
     * 現在の位置にメニュー項目を追加します。
     * 
     * @param text  メニュー項目のテキスト
     * @return このオブジェクトへの参照
     */
    public SimpleMenuBuilder addMenuItem(String text) {
        addItem(new JMenuItem(text));
        return this;
    }

    /**
     * 現在の位置にチェックボックス型のメニュー項目を追加します。
     * 
     * @param text      メニュー項目のテキスト
     * @param selected  選択状態
     * @return このオブジェクトへの参照
     */
    public SimpleMenuBuilder addCheckBoxMenuItem(String text, boolean selected) {
        addItem(new JCheckBoxMenuItem(text, selected));
        return this;
    }

    /**
     * 現在の位置にラジオボタン型のメニュー項目を追加します。
     * 
     * @param texts          メニュー項目のテキスト
     * @param selectedIndex  選択項目インデックス(非選択にする場合は-1などの負数を指定して下さい)
     * @return このオブジェクトへの参照
     */
    public SimpleMenuBuilder addRadioButtonMenuItem(String[] texts, int selectedIndex) {
        ButtonGroup group = new ButtonGroup();
        int index = 0;
        for (String text : texts) {
            JMenuItem mi = new JRadioButtonMenuItem(text);
            addItem(mi);
            group.add(mi);

            if (index++ == selectedIndex) {
                mi.setSelected(true);
            }
        }
        return this;
    }

    /**
     * 現在の位置にセパレータを追加します。
     * 
     * @return このオブジェクトへの参照
     */
    public SimpleMenuBuilder addSeparator() {
        if (menuStack_.isEmpty()) {
            items_.add(SEPARATOR);
        } else {
            menuStack_.peek().addSeparator();
        }
        return this;
    }

    /**
     * アクションコマンドとして設定する文字列を取得します。
     * メニュー項目のテキスト以外の文字列を設定する場合は、
     * このメソッドをオーバーライドしてください。
     * 
     * @param menuItem  アクションコマンドを設定するメニュー項目
     * @return アクションコマンドとして設定する文字列
     */
    protected String getActionCommand(JMenuItem menuItem) {
        return menuItem.getText();
    }

    private void addItem(JMenuItem menuItem) {
        menuItem.setActionCommand(getActionCommand(menuItem));
        if (menuStack_.isEmpty()) {
            items_.add(menuItem);
        } else {
            menuStack_.peek().add(menuItem);
        }
    }

    private void attachActionListeners(JMenuItem menuItem) {
        if (menuItem == null) {
            return;
        }
        for (ActionListener l : actionListeners_) {
            menuItem.addActionListener(l);
        }
        if (menuItem instanceof JMenu) {
            JMenu menu = (JMenu) menuItem;
            for (int i = 0; i < menu.getItemCount(); i++) {
                attachActionListeners(menu.getItem(i));
            }
        }
    }
}