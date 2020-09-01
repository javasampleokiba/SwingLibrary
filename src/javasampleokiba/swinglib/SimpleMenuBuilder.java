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
 * �V���v���ȃ��j���[�o�[/�|�b�v�A�b�v���j���[�쐬�N���X.
 * 
 * <br>
 * <p>[�g����]</p>
 * ��Ƃ��āA�ȉ��̃��j���[�\���ɂ���ꍇ�A<br>
 * <br>
 * Menu1<br>
 * ��Item1<br>
 * ��SubMenu1<br>
 * �� ��SubItem1<br>
 * �� ��SubItem2<br>
 * ��Item2<br>
 * Menu2<br>
 * ��Item3<br>
 * <br>
 * �R�[�h�͈ȉ��̂悤�ɂȂ�܂��B
 * ���ӓ_�Ƃ��āA�ЂƂ̃��j���[�̍��ڒǉ������������璼��ɕK��endMenu���\�b�h���Ăяo���ĉ������B
 * 
 * <pre>{@code
 * SimpleMenuBuilder smb = new SimpleMenuBuilder(actionListener);
 * smb.addMenu("Menu1")
 *     .addMenuItem("Item1")
 *     .addMenu("SubMenu1")
 *         .addMenuItem("SubItem1")
 *         .addMenuItem("SubItem2")
 *         .endMenu()                    // <-SubMenu1�쐬����
 *     .addMenuItem("Item2")
 *     .endMenu()                    // <-Menu1�쐬����
 * .addMenu("Menu2")
 *     .addMenuItem("Item3")
 *     .endMenu();                   // <-Menu2�쐬����
 * }</pre>
 * 
 * <p>[���ӓ_]</p>
 * <ul>
 * <li>�A�N�V�����R�}���h�ɂ͎����I�Ƀ��j���[(����)�̃e�L�X�g���ݒ肳��܂��B</li>
 * <li>�����e�L�X�g�������j���[(����)�͍쐬�ł��܂���B</li>
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
     * {@code JMenuBar}�I�u�W�F�N�g�𐶐����ĕԂ��܂��B
     * 
     * @return {@code JMenuBar}�I�u�W�F�N�g
     * @throws UnsupportedOperationException  {@code JMenuBar}�I�u�W�F�N�g�ɃZ�p���[�^��ǉ����悤�Ƃ����ꍇ
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
     * {@code JPopupMenu}�I�u�W�F�N�g�𐶐����ĕԂ��܂��B
     * 
     * @return {@code JPopupMenu}�I�u�W�F�N�g
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
     * ���ׂẴ��j���[���ڂɋ��ʂ̃A�N�V�������X�i�[��ǉ����܂��B
     * 
     * @param listener  �A�N�V�������X�i�[
     * @return ���̃I�u�W�F�N�g�ւ̎Q��
     */
    public SimpleMenuBuilder addActionListener(ActionListener listener) {
        actionListeners_.add(listener);
        return this;
    }

    /**
     * ���ׂẴ��j���[���ڂɋ��ʂ̃A�N�V�������X�i�[���폜���܂��B
     * 
     * @param listener  �A�N�V�������X�i�[
     * @return ���̃I�u�W�F�N�g�ւ̎Q��
     */
    public SimpleMenuBuilder removeActionListener(ActionListener listener) {
        actionListeners_.remove(listener);
        return this;
    }

    /**
     * ���݂̈ʒu�Ƀ��j���[��ǉ����܂��B
     * 
     * @param text  ���j���[�̃e�L�X�g
     * @return ���̃I�u�W�F�N�g�ւ̎Q��
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
     * ���݂̃��j���[�ւ̃��j���[���ڒǉ����I�����܂��B
     * 
     * @return ���̃I�u�W�F�N�g�ւ̎Q��
     */
    public SimpleMenuBuilder endMenu() {
        menuStack_.pop();
        return this;
    }

    /**
     * ���݂̈ʒu�Ƀ��j���[���ڂ�ǉ����܂��B
     * 
     * @param text  ���j���[���ڂ̃e�L�X�g
     * @return ���̃I�u�W�F�N�g�ւ̎Q��
     */
    public SimpleMenuBuilder addMenuItem(String text) {
        addItem(new JMenuItem(text));
        return this;
    }

    /**
     * ���݂̈ʒu�Ƀ`�F�b�N�{�b�N�X�^�̃��j���[���ڂ�ǉ����܂��B
     * 
     * @param text      ���j���[���ڂ̃e�L�X�g
     * @param selected  �I�����
     * @return ���̃I�u�W�F�N�g�ւ̎Q��
     */
    public SimpleMenuBuilder addCheckBoxMenuItem(String text, boolean selected) {
        addItem(new JCheckBoxMenuItem(text, selected));
        return this;
    }

    /**
     * ���݂̈ʒu�Ƀ��W�I�{�^���^�̃��j���[���ڂ�ǉ����܂��B
     * 
     * @param texts          ���j���[���ڂ̃e�L�X�g
     * @param selectedIndex  �I�����ڃC���f�b�N�X(��I���ɂ���ꍇ��-1�Ȃǂ̕������w�肵�ĉ�����)
     * @return ���̃I�u�W�F�N�g�ւ̎Q��
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
     * ���݂̈ʒu�ɃZ�p���[�^��ǉ����܂��B
     * 
     * @return ���̃I�u�W�F�N�g�ւ̎Q��
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
     * �A�N�V�����R�}���h�Ƃ��Đݒ肷�镶������擾���܂��B
     * ���j���[���ڂ̃e�L�X�g�ȊO�̕������ݒ肷��ꍇ�́A
     * ���̃��\�b�h���I�[�o�[���C�h���Ă��������B
     * 
     * @param menuItem  �A�N�V�����R�}���h��ݒ肷�郁�j���[����
     * @return �A�N�V�����R�}���h�Ƃ��Đݒ肷�镶����
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