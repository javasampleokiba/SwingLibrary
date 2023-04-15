package example.javasampleokiba.swinglib;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import javasampleokiba.swinglib.OptionPaneDefaults;
import javasampleokiba.swinglib.SimpleMessageDialog;

/**
 * SimpleMessageDialogクラスの実行例
 */
public class SimpleMessageDialogExample extends JFrame {

    public static void main(String[] args) {

        /* ---------------------------------
         * SimpleMessageDialogのUIを統一する
         * ---------------------------------
         */
        // デフォルトのタイトルを設定
        SimpleMessageDialog.setDefaultTitles("エラー!", "情報!", "警告!", "質問!");

        /*
         * デフォルトのオプションタイプを設定
         * ・エラーダイアログのボタンを「了解」に統一
         * ・情報ダイアログのボタンを「了解」に統一
         * ・警告ダイアログのボタンを「了解」「取消し」に統一
         * ・質問ダイアログのボタンを「はい」「いいえ」取消し「」に統一
         */
        SimpleMessageDialog.setDefaultOptions(
                SimpleMessageDialog.OptionType.DEFAULT_OPTION,
                SimpleMessageDialog.OptionType.DEFAULT_OPTION,
                SimpleMessageDialog.OptionType.OK_CANCEL_OPTION,
                SimpleMessageDialog.OptionType.YES_NO_CANCEL_OPTION);

        /* ---------------------------------
         * システム全体でメッセージダイアログのUIを統一する
         * ---------------------------------
         */
        /*
         * デフォルトのタイトルを設定
         * （SimpleMessageDialog#setDefaultTitleが設定されていればそちらが優先されます）
         */
        {
            OptionPaneDefaults.setText(OptionPaneDefaults.TextKey.TITLE_TEXT, "質問");
            OptionPaneDefaults.setText(OptionPaneDefaults.TextKey.MSG_DIALOG_TITLE, "情報");
        }
        // デフォルトのボタンのテキスト、ニーモニックを設定
        {
            OptionPaneDefaults.setButtonText("OK!", "CANCEL!", "YES!", "NO!");
            OptionPaneDefaults.setButtonMnemonic(KeyEvent.VK_O, KeyEvent.VK_C, KeyEvent.VK_Y, KeyEvent.VK_N);
        }

        new SimpleMessageDialogExample().setVisible(true);
    }

    public SimpleMessageDialogExample() {
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // エラーダイアログ表示
        SimpleMessageDialog.showError(this, "エラー発生!");
        // 情報ダイアログ表示
        SimpleMessageDialog.showInformation(this, "情報だよ!");
        // 警告ダイアログ表示
        SimpleMessageDialog.showWarning(this, "警告です!");
        // 質問ダイアログ表示
        SimpleMessageDialog.showQuestion(this, "質問だ!");
    }
}