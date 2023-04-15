package javasampleokiba.swinglib;

import javax.swing.UIManager;

/**
 * JOptionPane�̊e��V�X�e���f�t�H���g�l���J�X�^�}�C�Y����N���X
 */
public class OptionPaneDefaults {

    /**
     * �e�L�X�g�֘A�̃L�[
     */
    public enum TextKey implements UIKey {
        /** ����_�C�A���O�̃^�C�g�� */
        TITLE_TEXT              ("OptionPane.titleText"),
        /** ���_�C�A���O�̃^�C�g�� */
        MSG_DIALOG_TITLE        ("OptionPane.messageDialogTitle"),
        /** ���̓_�C�A���O�̃^�C�g�� */
        INPUT_DIALOG_TITLE      ("OptionPane.inputDialogTitle"),
        /** �����{�^�� */
        OK_BUTTON_TEXT          ("OptionPane.okButtonText"),
        /** ������{�^�� */
        CANCEL_BUTTON_TEXT      ("OptionPane.cancelButtonText"),
        /** �͂��{�^�� */
        YES_BUTTON_TEXT         ("OptionPane.yesButtonText"),
        /** �������{�^�� */
        NO_BUTTON_TEXT          ("OptionPane.noButtonText"),
        ;

        private String key_;

        private TextKey(String key) { key_ = key; }

        @Override
        public String getKey() { return key_; }
    }

    /**
     * �j�[���j�b�N�֘A�̃L�[
     */
    public enum MnemonicKey implements UIKey {
        /** �����{�^�� */
        OK_BUTTON_MNEMONIC      ("OptionPane.okButtonMnemonic"),
        /** ������{�^�� */
        CANCEL_BUTTON_MNEMONIC  ("OptionPane.cancelButtonMnemonic"),
        /** �͂��{�^�� */
        YES_BUTTON_MNEMONIC     ("OptionPane.yesButtonMnemonic"),
        /** �������{�^�� */
        NO_BUTTON_MNEMONIC      ("OptionPane.noButtonMnemonic"),
        ;

        private String key_;

        private MnemonicKey(String key) { key_ = key; }

        @Override
        public String getKey() { return key_; }
    }

    public interface UIKey {
        abstract String getKey();
    }

    private OptionPaneDefaults() {}

    /**
     * �f�t�H���g�̃e�L�X�g��ݒ肵�܂��B
     * 
     * @param key   �L�[
     * @param text  �f�t�H���g�̃e�L�X�g
     * 
     * @throws NullPointerException    key��null�̏ꍇ
     */
    public static void setText(TextKey key, String text) {
        UIManager.put(key.getKey(), text);
    }

    /**
     * �f�t�H���g�̃{�^���e�L�X�g��ݒ肵�܂��B
     * 
     * @param ok      �����{�^���̃f�t�H���g�̃e�L�X�g
     * @param cancel  ������{�^���̃f�t�H���g�̃e�L�X�g
     * @param yes     �͂��{�^���̃f�t�H���g�̃e�L�X�g
     * @param no      �������{�^���̃f�t�H���g�̃e�L�X�g
     */
    public static void setButtonText(String ok, String cancel, String yes, String no) {
        setText(TextKey.OK_BUTTON_TEXT, ok);
        setText(TextKey.CANCEL_BUTTON_TEXT, cancel);
        setText(TextKey.YES_BUTTON_TEXT, yes);
        setText(TextKey.NO_BUTTON_TEXT, no);
    }

    /**
     * �f�t�H���g�̃j�[���j�b�N�L�[��ݒ肵�܂��B
     * 
     * @param key      �L�[
     * @param keyCode  �f�t�H���g�̃j�[���j�b�N�L�[
     * 
     * @throws NullPointerException    key��null�̏ꍇ
     */
    public static void setMnemonic(MnemonicKey key, int keyCode) {
        UIManager.put(key.getKey(), String.valueOf(keyCode));
    }

    /**
     * �f�t�H���g�̃j�[���j�b�N�L�[��ݒ肵�܂��B
     * 
     * @param ok      �����{�^���̃f�t�H���g�̃j�[���j�b�N�L�[
     * @param cancel  ������{�^���̃f�t�H���g�̃j�[���j�b�N�L�[
     * @param yes     �͂��{�^���̃f�t�H���g�̃j�[���j�b�N�L�[
     * @param no      �������{�^���̃f�t�H���g�̃j�[���j�b�N�L�[
     */
    public static void setButtonMnemonic(int ok, int cancel, int yes, int no) {
        setMnemonic(MnemonicKey.OK_BUTTON_MNEMONIC, ok);
        setMnemonic(MnemonicKey.CANCEL_BUTTON_MNEMONIC, cancel);
        setMnemonic(MnemonicKey.YES_BUTTON_MNEMONIC, yes);
        setMnemonic(MnemonicKey.NO_BUTTON_MNEMONIC, no);
    }
}
