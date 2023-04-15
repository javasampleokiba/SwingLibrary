package javasampleokiba.swinglib;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * シンプルなメッセージダイアログを表示するクラス.
 * 
 * <br>
 * [概要]<br>
 * JOptionPaneによるメッセージダイアログ表示処理をシンプルに書けるようにするクラスです。
 * 
 * @author javasampleokiba
 */
public class SimpleMessageDialog {

    /**
     * メッセージタイプ
     */
    public enum MessageType {
        /** エラー */
        ERROR_MESSAGE         (JOptionPane.ERROR_MESSAGE),
        /** 情報 */
        INFORMATION_MESSAGE   (JOptionPane.INFORMATION_MESSAGE),
        /** 警告 */
        WARNING_MESSAGE       (JOptionPane.WARNING_MESSAGE),
        /** 質問 */
        QUESTION_MESSAGE      (JOptionPane.QUESTION_MESSAGE);

        private final int value_;
        private String title_		= null;
        private OptionType option_	= OptionType.DEFAULT_OPTION;

        private MessageType(int value) { value_ = value; }

        private int getValue() { return value_; }
        private String getTitle() { return title_; }
        private void setTitle(String title) { title_ = title; }
        private OptionType getOption() { return option_; }
        private void setOption(OptionType option) { option_ = option; }
    }

    /**
     * オプションタイプ
     */
    public enum OptionType {
        /** 了解 */
        DEFAULT_OPTION        (JOptionPane.DEFAULT_OPTION),
        /** はい / いいえ */
        YES_NO_OPTION         (JOptionPane.YES_NO_OPTION),
        /** はい / いいえ / 取消し */
        YES_NO_CANCEL_OPTION  (JOptionPane.YES_NO_CANCEL_OPTION),
        /** 了解 / 取消し */
        OK_CANCEL_OPTION      (JOptionPane.OK_CANCEL_OPTION);

        private final int value_;

        private OptionType(int value) { value_ = value; }

        private int getValue() { return value_; }
    }

    private SimpleMessageDialog() {}

    /**
     * 指定されたメッセージタイプのデフォルトのタイトルを設定します。<br>
     * デフォルトのタイトル設定を省略した場合は、以下のタイトルが表示されます。<br>
     * <br>
     * ・エラーダイアログ　->　空文字<br>
     * ・情報ダイアログ　->　Swingコンポーネントのデフォルト値<br>
     * ・警告ダイアログ　->　空文字<br>
     * ・質問ダイアログ　->　Swingコンポーネントのデフォルト値
     * 
     * @param messageType  メッセージタイプ
     * @param title        タイトル
     * 
     * @throws NullPointerException  messageTypeがnullの場合
     */
    public static void setDefaultTitle(MessageType messageType, String title) {
        messageType.setTitle(title);
    }

    /**
     * 各メッセージタイプのデフォルトのタイトルを設定します。
     * 
     * @param errorTitle        エラーダイアログのタイトル
     * @param informationTitle  情報ダイアログのタイトル
     * @param warningTitle      警告ダイアログのタイトル
     * @param questionTitle     質問ダイアログのタイトル
     */
    public static void setDefaultTitles(String errorTitle, String informationTitle,
            String warningTitle, String questionTitle) {
        setDefaultTitle(MessageType.ERROR_MESSAGE, errorTitle);
        setDefaultTitle(MessageType.INFORMATION_MESSAGE, informationTitle);
        setDefaultTitle(MessageType.WARNING_MESSAGE, warningTitle);
        setDefaultTitle(MessageType.QUESTION_MESSAGE, questionTitle);
    }

    /**
     * 全メッセージタイプで共通するデフォルトのタイトルを設定します。
     * 
     * @param title  タイトル
     */
    public static void setDefaultCommonTitle(String title) {
        setDefaultTitles(title, title, title, title);
    }

    /**
     * 指定されたメッセージタイプのデフォルトのオプションタイプを設定します。<br>
     * デフォルトのオプションタイプ設定を省略した場合は、「了解」が表示されます。
     * 
     * @param messageType  メッセージタイプ
     * @param optionType   オプションタイプ
     * 
     * @throws NullPointerException  messageTypeまたはoptionTypeがnullの場合
     */
    public static void setDefaultOption(MessageType messageType, OptionType optionType) {
        if (optionType == null) {
            throw new NullPointerException("optionType is null.");
        }
        messageType.setOption(optionType);
    }

    /**
     * 各メッセージタイプのデフォルトのオプションタイプを設定します。
     * 
     * @param errorOption        エラーダイアログのオプションタイプ
     * @param informationOption  情報ダイアログのオプションタイプ
     * @param warningOption      警告ダイアログのオプションタイプ
     * @param questionOption     質問ダイアログのオプションタイプ
     */
    public static void setDefaultOptions(OptionType errorOption, OptionType informationOption,
            OptionType warningOption, OptionType questionOption) {
        setDefaultOption(MessageType.ERROR_MESSAGE, errorOption);
        setDefaultOption(MessageType.INFORMATION_MESSAGE, informationOption);
        setDefaultOption(MessageType.WARNING_MESSAGE, warningOption);
        setDefaultOption(MessageType.QUESTION_MESSAGE, questionOption);
    }

    /**
     * デフォルトのタイトル、デフォルトのオプションタイプのエラーメッセージダイアログを表示します。
     * 
     * @param parentComponent  オーナーコンポーネント
     * @param message          表示するメッセージ
     * 
     * @return ユーザーが選択したオプションを示すint値
     */
    public static int showError(Component parentComponent, Object message) {
        MessageType type = MessageType.ERROR_MESSAGE;
        return show(type, parentComponent, message, type.getOption(), getDefaultTitle(type));
    }

    /**
     * デフォルトのタイトル、指定したオプションタイプのエラーメッセージダイアログを表示します。
     * 
     * @param parentComponent  オーナーコンポーネント
     * @param message          表示するメッセージ
     * @param optionType       オプションタイプ
     * 
     * @return ユーザーが選択したオプションを示すint値
     */
    public static int showError(Component parentComponent, Object message,
            OptionType optionType) {
        MessageType type = MessageType.ERROR_MESSAGE;
        return show(type, parentComponent, message, optionType, getDefaultTitle(type));
    }

    /**
     * 指定したタイトル、指定したオプションタイプのエラーメッセージダイアログを表示します。
     * 
     * @param parentComponent  オーナーコンポーネント
     * @param message          表示するメッセージ
     * @param optionType       オプションタイプ
     * @param title            タイトル
     * 
     * @return ユーザーが選択したオプションを示すint値
     */
    public static int showError(Component parentComponent, Object message,
            OptionType optionType, String title) {
        return show(MessageType.ERROR_MESSAGE, parentComponent, message, optionType, title);
    }

    /**
     * デフォルトのタイトル、デフォルトのオプションタイプのエラーメッセージダイアログを表示します。
     * 
     * @param parentComponent  オーナーコンポーネント
     * @param message          表示するメッセージ
     * 
     * @return ユーザーが選択したオプションを示すint値
     */
    public static int showInformation(Component parentComponent, Object message) {
        MessageType type = MessageType.INFORMATION_MESSAGE;
        return show(type, parentComponent, message, type.getOption(), getDefaultTitle(type));
    }

    /**
     * デフォルトのタイトル、指定したオプションタイプのエラーメッセージダイアログを表示します。
     * 
     * @param parentComponent  オーナーコンポーネント
     * @param message          表示するメッセージ
     * @param optionType       オプションタイプ
     * 
     * @return ユーザーが選択したオプションを示すint値
     */
    public static int showInformation(Component parentComponent, Object message,
            OptionType optionType) {
        MessageType type = MessageType.INFORMATION_MESSAGE;
        return show(type, parentComponent, message, optionType, getDefaultTitle(type));
    }

    /**
     * 指定したタイトル、指定したオプションタイプのエラーメッセージダイアログを表示します。
     * 
     * @param parentComponent  オーナーコンポーネント
     * @param message          表示するメッセージ
     * @param optionType       オプションタイプ
     * @param title            タイトル
     * 
     * @return ユーザーが選択したオプションを示すint値
     */
    public static int showInformation(Component parentComponent, Object message,
            OptionType optionType, String title) {
        return show(MessageType.INFORMATION_MESSAGE, parentComponent, message, optionType, title);
    }

    /**
     * デフォルトのタイトル、デフォルトのオプションタイプのエラーメッセージダイアログを表示します。
     * 
     * @param parentComponent  オーナーコンポーネント
     * @param message          表示するメッセージ
     * 
     * @return ユーザーが選択したオプションを示すint値
     */
    public static int showWarning(Component parentComponent, Object message) {
        MessageType type = MessageType.WARNING_MESSAGE;
        return show(type, parentComponent, message, type.getOption(), getDefaultTitle(type));
    }

    /**
     * デフォルトのタイトル、指定したオプションタイプのエラーメッセージダイアログを表示します。
     * 
     * @param parentComponent  オーナーコンポーネント
     * @param message          表示するメッセージ
     * @param optionType       オプションタイプ
     * 
     * @return ユーザーが選択したオプションを示すint値
     */
    public static int showWarning(Component parentComponent, Object message,
            OptionType optionType) {
        MessageType type = MessageType.WARNING_MESSAGE;
        return show(type, parentComponent, message, optionType, getDefaultTitle(type));
    }

    /**
     * 指定したタイトル、指定したオプションタイプのエラーメッセージダイアログを表示します。
     * 
     * @param parentComponent  オーナーコンポーネント
     * @param message          表示するメッセージ
     * @param optionType       オプションタイプ
     * @param title            タイトル
     * 
     * @return ユーザーが選択したオプションを示すint値
     */
    public static int showWarning(Component parentComponent, Object message,
            OptionType optionType, String title) {
        return show(MessageType.WARNING_MESSAGE, parentComponent, message, optionType, title);
    }

    /**
     * デフォルトのタイトル、デフォルトのオプションタイプのエラーメッセージダイアログを表示します。
     * 
     * @param parentComponent  オーナーコンポーネント
     * @param message          表示するメッセージ
     * 
     * @return ユーザーが選択したオプションを示すint値
     */
    public static int showQuestion(Component parentComponent, Object message) {
        MessageType type = MessageType.QUESTION_MESSAGE;
        return show(type, parentComponent, message, type.getOption(), getDefaultTitle(type));
    }

    /**
     * デフォルトのタイトル、指定したオプションタイプのエラーメッセージダイアログを表示します。
     * 
     * @param parentComponent  オーナーコンポーネント
     * @param message          表示するメッセージ
     * @param optionType       オプションタイプ
     * 
     * @return ユーザーが選択したオプションを示すint値
     */
    public static int showQuestion(Component parentComponent, Object message,
            OptionType optionType) {
        MessageType type = MessageType.QUESTION_MESSAGE;
        return show(type, parentComponent, message, optionType, getDefaultTitle(type));
    }

    /**
     * 指定したタイトル、指定したオプションタイプのエラーメッセージダイアログを表示します。
     * 
     * @param parentComponent  オーナーコンポーネント
     * @param message          表示するメッセージ
     * @param optionType       オプションタイプ
     * @param title            タイトル
     * 
     * @return ユーザーが選択したオプションを示すint値
     */
    public static int showQuestion(Component parentComponent, Object message,
            OptionType optionType, String title) {
        return show(MessageType.QUESTION_MESSAGE, parentComponent, message, optionType, title);
    }

    /**
     * 指定したメッセージタイプ、指定したタイトル、指定したオプションタイプのメッセージダイアログを表示します。
     * 
     * @param messageType      メッセージタイプ
     * @param parentComponent  オーナーコンポーネント
     * @param message          表示するメッセージ
     * @param optionType       オプションタイプ
     * @param title            タイトル
     * 
     * @return ユーザーが選択したオプションを示すint値
     * 
     * @throws NullPointerException  messageTypeがnullの場合
     */
    public static int show(MessageType messageType, Component parentComponent,
            Object message, OptionType optionType, String title) {
        return JOptionPane.showOptionDialog(parentComponent, message,
                title,
                optionType.getValue(),
                messageType.getValue(),
                null, null, null);
    }

    /**
     * ユーザーがダイアログで「はい」を選択したか判定します。
     * 
     * @param option  ユーザーが選択したオプションを示すint値
     * 
     * @return ユーザーが「はい」を選択した場合はtrueを返す
     */
    public static boolean isYesSelected(int option) {
        return option == JOptionPane.YES_OPTION;
    }

    /**
     * ユーザーがダイアログで「いいえ」を選択したか判定します。
     * 
     * @param option  ユーザーが選択したオプションを示すint値
     * 
     * @return ユーザーが「いいえ」を選択した場合はtrueを返す
     */
    public static boolean isNoSelected(int option) {
        return option == JOptionPane.NO_OPTION;
    }

    /**
     * ユーザーがダイアログで「了解」を選択したか判定します。
     * 
     * @param option  ユーザーが選択したオプションを示すint値
     * 
     * @return ユーザーが「了解」を選択した場合はtrueを返す
     */
    public static boolean isOkSelected(int option) {
        return option == JOptionPane.OK_OPTION;
    }

    /**
     * ユーザーがダイアログで「取消し」を選択したか判定します。
     * 
     * @param option  ユーザーが選択したオプションを示すint値
     * 
     * @return ユーザーが「取消し」を選択した場合はtrueを返す
     */
    public static boolean isCancelSelected(int option) {
        return option == JOptionPane.CANCEL_OPTION;
    }

    /**
     * ユーザーがダイアログを閉じたか判定します。
     * 
     * @param option  ユーザーが選択したオプションを示すint値
     * 
     * @return ユーザーがダイアログを閉じた場合はtrueを返す
     */
    public static boolean isClosed(int option) {
        return option == JOptionPane.CLOSED_OPTION;
    }

    private static String getDefaultTitle(MessageType messageType) {
        String title = messageType.getTitle();
        if (title == null) {
            switch (messageType) {
            case INFORMATION_MESSAGE:
            {
                title = UIManager.getString("OptionPane.messageDialogTitle");
            }
            break;
            case QUESTION_MESSAGE:
            {
                title = UIManager.getString("OptionPane.titleText");
            }
            break;
            default:
                break;
            }
        }
        return title;
    }
}
