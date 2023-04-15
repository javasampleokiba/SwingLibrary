package javasampleokiba.swinglib;

import javax.swing.UIManager;

/**
 * JOptionPaneの各種システムデフォルト値をカスタマイズするクラス
 */
public class OptionPaneDefaults {

    /**
     * テキスト関連のキー
     */
    public enum TextKey implements UIKey {
        /** 質問ダイアログのタイトル */
        TITLE_TEXT              ("OptionPane.titleText"),
        /** 情報ダイアログのタイトル */
        MSG_DIALOG_TITLE        ("OptionPane.messageDialogTitle"),
        /** 入力ダイアログのタイトル */
        INPUT_DIALOG_TITLE      ("OptionPane.inputDialogTitle"),
        /** 了解ボタン */
        OK_BUTTON_TEXT          ("OptionPane.okButtonText"),
        /** 取消しボタン */
        CANCEL_BUTTON_TEXT      ("OptionPane.cancelButtonText"),
        /** はいボタン */
        YES_BUTTON_TEXT         ("OptionPane.yesButtonText"),
        /** いいえボタン */
        NO_BUTTON_TEXT          ("OptionPane.noButtonText"),
        ;

        private String key_;

        private TextKey(String key) { key_ = key; }

        @Override
        public String getKey() { return key_; }
    }

    /**
     * ニーモニック関連のキー
     */
    public enum MnemonicKey implements UIKey {
        /** 了解ボタン */
        OK_BUTTON_MNEMONIC      ("OptionPane.okButtonMnemonic"),
        /** 取消しボタン */
        CANCEL_BUTTON_MNEMONIC  ("OptionPane.cancelButtonMnemonic"),
        /** はいボタン */
        YES_BUTTON_MNEMONIC     ("OptionPane.yesButtonMnemonic"),
        /** いいえボタン */
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
     * デフォルトのテキストを設定します。
     * 
     * @param key   キー
     * @param text  デフォルトのテキスト
     * 
     * @throws NullPointerException    keyがnullの場合
     */
    public static void setText(TextKey key, String text) {
        UIManager.put(key.getKey(), text);
    }

    /**
     * デフォルトのボタンテキストを設定します。
     * 
     * @param ok      了解ボタンのデフォルトのテキスト
     * @param cancel  取消しボタンのデフォルトのテキスト
     * @param yes     はいボタンのデフォルトのテキスト
     * @param no      いいえボタンのデフォルトのテキスト
     */
    public static void setButtonText(String ok, String cancel, String yes, String no) {
        setText(TextKey.OK_BUTTON_TEXT, ok);
        setText(TextKey.CANCEL_BUTTON_TEXT, cancel);
        setText(TextKey.YES_BUTTON_TEXT, yes);
        setText(TextKey.NO_BUTTON_TEXT, no);
    }

    /**
     * デフォルトのニーモニックキーを設定します。
     * 
     * @param key      キー
     * @param keyCode  デフォルトのニーモニックキー
     * 
     * @throws NullPointerException    keyがnullの場合
     */
    public static void setMnemonic(MnemonicKey key, int keyCode) {
        UIManager.put(key.getKey(), String.valueOf(keyCode));
    }

    /**
     * デフォルトのニーモニックキーを設定します。
     * 
     * @param ok      了解ボタンのデフォルトのニーモニックキー
     * @param cancel  取消しボタンのデフォルトのニーモニックキー
     * @param yes     はいボタンのデフォルトのニーモニックキー
     * @param no      いいえボタンのデフォルトのニーモニックキー
     */
    public static void setButtonMnemonic(int ok, int cancel, int yes, int no) {
        setMnemonic(MnemonicKey.OK_BUTTON_MNEMONIC, ok);
        setMnemonic(MnemonicKey.CANCEL_BUTTON_MNEMONIC, cancel);
        setMnemonic(MnemonicKey.YES_BUTTON_MNEMONIC, yes);
        setMnemonic(MnemonicKey.NO_BUTTON_MNEMONIC, no);
    }
}
