package javasampleokiba.swinglib;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * �V���v���ȃ��b�Z�[�W�_�C�A���O��\������N���X.
 * 
 * <br>
 * [�T�v]<br>
 * JOptionPane�ɂ�郁�b�Z�[�W�_�C�A���O�\���������V���v���ɏ�����悤�ɂ���N���X�ł��B
 * 
 * @author javasampleokiba
 */
public class SimpleMessageDialog {

    /**
     * ���b�Z�[�W�^�C�v
     */
    public enum MessageType {
        /** �G���[ */
        ERROR_MESSAGE         (JOptionPane.ERROR_MESSAGE),
        /** ��� */
        INFORMATION_MESSAGE   (JOptionPane.INFORMATION_MESSAGE),
        /** �x�� */
        WARNING_MESSAGE       (JOptionPane.WARNING_MESSAGE),
        /** ���� */
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
     * �I�v�V�����^�C�v
     */
    public enum OptionType {
        /** ���� */
        DEFAULT_OPTION        (JOptionPane.DEFAULT_OPTION),
        /** �͂� / ������ */
        YES_NO_OPTION         (JOptionPane.YES_NO_OPTION),
        /** �͂� / ������ / ����� */
        YES_NO_CANCEL_OPTION  (JOptionPane.YES_NO_CANCEL_OPTION),
        /** ���� / ����� */
        OK_CANCEL_OPTION      (JOptionPane.OK_CANCEL_OPTION);

        private final int value_;

        private OptionType(int value) { value_ = value; }

        private int getValue() { return value_; }
    }

    private SimpleMessageDialog() {}

    /**
     * �w�肳�ꂽ���b�Z�[�W�^�C�v�̃f�t�H���g�̃^�C�g����ݒ肵�܂��B<br>
     * �f�t�H���g�̃^�C�g���ݒ���ȗ������ꍇ�́A�ȉ��̃^�C�g�����\������܂��B<br>
     * <br>
     * �E�G���[�_�C�A���O�@->�@�󕶎�<br>
     * �E���_�C�A���O�@->�@Swing�R���|�[�l���g�̃f�t�H���g�l<br>
     * �E�x���_�C�A���O�@->�@�󕶎�<br>
     * �E����_�C�A���O�@->�@Swing�R���|�[�l���g�̃f�t�H���g�l
     * 
     * @param messageType  ���b�Z�[�W�^�C�v
     * @param title        �^�C�g��
     * 
     * @throws NullPointerException  messageType��null�̏ꍇ
     */
    public static void setDefaultTitle(MessageType messageType, String title) {
        messageType.setTitle(title);
    }

    /**
     * �e���b�Z�[�W�^�C�v�̃f�t�H���g�̃^�C�g����ݒ肵�܂��B
     * 
     * @param errorTitle        �G���[�_�C�A���O�̃^�C�g��
     * @param informationTitle  ���_�C�A���O�̃^�C�g��
     * @param warningTitle      �x���_�C�A���O�̃^�C�g��
     * @param questionTitle     ����_�C�A���O�̃^�C�g��
     */
    public static void setDefaultTitles(String errorTitle, String informationTitle,
            String warningTitle, String questionTitle) {
        setDefaultTitle(MessageType.ERROR_MESSAGE, errorTitle);
        setDefaultTitle(MessageType.INFORMATION_MESSAGE, informationTitle);
        setDefaultTitle(MessageType.WARNING_MESSAGE, warningTitle);
        setDefaultTitle(MessageType.QUESTION_MESSAGE, questionTitle);
    }

    /**
     * �S���b�Z�[�W�^�C�v�ŋ��ʂ���f�t�H���g�̃^�C�g����ݒ肵�܂��B
     * 
     * @param title  �^�C�g��
     */
    public static void setDefaultCommonTitle(String title) {
        setDefaultTitles(title, title, title, title);
    }

    /**
     * �w�肳�ꂽ���b�Z�[�W�^�C�v�̃f�t�H���g�̃I�v�V�����^�C�v��ݒ肵�܂��B<br>
     * �f�t�H���g�̃I�v�V�����^�C�v�ݒ���ȗ������ꍇ�́A�u�����v���\������܂��B
     * 
     * @param messageType  ���b�Z�[�W�^�C�v
     * @param optionType   �I�v�V�����^�C�v
     * 
     * @throws NullPointerException  messageType�܂���optionType��null�̏ꍇ
     */
    public static void setDefaultOption(MessageType messageType, OptionType optionType) {
        if (optionType == null) {
            throw new NullPointerException("optionType is null.");
        }
        messageType.setOption(optionType);
    }

    /**
     * �e���b�Z�[�W�^�C�v�̃f�t�H���g�̃I�v�V�����^�C�v��ݒ肵�܂��B
     * 
     * @param errorOption        �G���[�_�C�A���O�̃I�v�V�����^�C�v
     * @param informationOption  ���_�C�A���O�̃I�v�V�����^�C�v
     * @param warningOption      �x���_�C�A���O�̃I�v�V�����^�C�v
     * @param questionOption     ����_�C�A���O�̃I�v�V�����^�C�v
     */
    public static void setDefaultOptions(OptionType errorOption, OptionType informationOption,
            OptionType warningOption, OptionType questionOption) {
        setDefaultOption(MessageType.ERROR_MESSAGE, errorOption);
        setDefaultOption(MessageType.INFORMATION_MESSAGE, informationOption);
        setDefaultOption(MessageType.WARNING_MESSAGE, warningOption);
        setDefaultOption(MessageType.QUESTION_MESSAGE, questionOption);
    }

    /**
     * �f�t�H���g�̃^�C�g���A�f�t�H���g�̃I�v�V�����^�C�v�̃G���[���b�Z�[�W�_�C�A���O��\�����܂��B
     * 
     * @param parentComponent  �I�[�i�[�R���|�[�l���g
     * @param message          �\�����郁�b�Z�[�W
     * 
     * @return ���[�U�[���I�������I�v�V����������int�l
     */
    public static int showError(Component parentComponent, Object message) {
        MessageType type = MessageType.ERROR_MESSAGE;
        return show(type, parentComponent, message, type.getOption(), getDefaultTitle(type));
    }

    /**
     * �f�t�H���g�̃^�C�g���A�w�肵���I�v�V�����^�C�v�̃G���[���b�Z�[�W�_�C�A���O��\�����܂��B
     * 
     * @param parentComponent  �I�[�i�[�R���|�[�l���g
     * @param message          �\�����郁�b�Z�[�W
     * @param optionType       �I�v�V�����^�C�v
     * 
     * @return ���[�U�[���I�������I�v�V����������int�l
     */
    public static int showError(Component parentComponent, Object message,
            OptionType optionType) {
        MessageType type = MessageType.ERROR_MESSAGE;
        return show(type, parentComponent, message, optionType, getDefaultTitle(type));
    }

    /**
     * �w�肵���^�C�g���A�w�肵���I�v�V�����^�C�v�̃G���[���b�Z�[�W�_�C�A���O��\�����܂��B
     * 
     * @param parentComponent  �I�[�i�[�R���|�[�l���g
     * @param message          �\�����郁�b�Z�[�W
     * @param optionType       �I�v�V�����^�C�v
     * @param title            �^�C�g��
     * 
     * @return ���[�U�[���I�������I�v�V����������int�l
     */
    public static int showError(Component parentComponent, Object message,
            OptionType optionType, String title) {
        return show(MessageType.ERROR_MESSAGE, parentComponent, message, optionType, title);
    }

    /**
     * �f�t�H���g�̃^�C�g���A�f�t�H���g�̃I�v�V�����^�C�v�̃G���[���b�Z�[�W�_�C�A���O��\�����܂��B
     * 
     * @param parentComponent  �I�[�i�[�R���|�[�l���g
     * @param message          �\�����郁�b�Z�[�W
     * 
     * @return ���[�U�[���I�������I�v�V����������int�l
     */
    public static int showInformation(Component parentComponent, Object message) {
        MessageType type = MessageType.INFORMATION_MESSAGE;
        return show(type, parentComponent, message, type.getOption(), getDefaultTitle(type));
    }

    /**
     * �f�t�H���g�̃^�C�g���A�w�肵���I�v�V�����^�C�v�̃G���[���b�Z�[�W�_�C�A���O��\�����܂��B
     * 
     * @param parentComponent  �I�[�i�[�R���|�[�l���g
     * @param message          �\�����郁�b�Z�[�W
     * @param optionType       �I�v�V�����^�C�v
     * 
     * @return ���[�U�[���I�������I�v�V����������int�l
     */
    public static int showInformation(Component parentComponent, Object message,
            OptionType optionType) {
        MessageType type = MessageType.INFORMATION_MESSAGE;
        return show(type, parentComponent, message, optionType, getDefaultTitle(type));
    }

    /**
     * �w�肵���^�C�g���A�w�肵���I�v�V�����^�C�v�̃G���[���b�Z�[�W�_�C�A���O��\�����܂��B
     * 
     * @param parentComponent  �I�[�i�[�R���|�[�l���g
     * @param message          �\�����郁�b�Z�[�W
     * @param optionType       �I�v�V�����^�C�v
     * @param title            �^�C�g��
     * 
     * @return ���[�U�[���I�������I�v�V����������int�l
     */
    public static int showInformation(Component parentComponent, Object message,
            OptionType optionType, String title) {
        return show(MessageType.INFORMATION_MESSAGE, parentComponent, message, optionType, title);
    }

    /**
     * �f�t�H���g�̃^�C�g���A�f�t�H���g�̃I�v�V�����^�C�v�̃G���[���b�Z�[�W�_�C�A���O��\�����܂��B
     * 
     * @param parentComponent  �I�[�i�[�R���|�[�l���g
     * @param message          �\�����郁�b�Z�[�W
     * 
     * @return ���[�U�[���I�������I�v�V����������int�l
     */
    public static int showWarning(Component parentComponent, Object message) {
        MessageType type = MessageType.WARNING_MESSAGE;
        return show(type, parentComponent, message, type.getOption(), getDefaultTitle(type));
    }

    /**
     * �f�t�H���g�̃^�C�g���A�w�肵���I�v�V�����^�C�v�̃G���[���b�Z�[�W�_�C�A���O��\�����܂��B
     * 
     * @param parentComponent  �I�[�i�[�R���|�[�l���g
     * @param message          �\�����郁�b�Z�[�W
     * @param optionType       �I�v�V�����^�C�v
     * 
     * @return ���[�U�[���I�������I�v�V����������int�l
     */
    public static int showWarning(Component parentComponent, Object message,
            OptionType optionType) {
        MessageType type = MessageType.WARNING_MESSAGE;
        return show(type, parentComponent, message, optionType, getDefaultTitle(type));
    }

    /**
     * �w�肵���^�C�g���A�w�肵���I�v�V�����^�C�v�̃G���[���b�Z�[�W�_�C�A���O��\�����܂��B
     * 
     * @param parentComponent  �I�[�i�[�R���|�[�l���g
     * @param message          �\�����郁�b�Z�[�W
     * @param optionType       �I�v�V�����^�C�v
     * @param title            �^�C�g��
     * 
     * @return ���[�U�[���I�������I�v�V����������int�l
     */
    public static int showWarning(Component parentComponent, Object message,
            OptionType optionType, String title) {
        return show(MessageType.WARNING_MESSAGE, parentComponent, message, optionType, title);
    }

    /**
     * �f�t�H���g�̃^�C�g���A�f�t�H���g�̃I�v�V�����^�C�v�̃G���[���b�Z�[�W�_�C�A���O��\�����܂��B
     * 
     * @param parentComponent  �I�[�i�[�R���|�[�l���g
     * @param message          �\�����郁�b�Z�[�W
     * 
     * @return ���[�U�[���I�������I�v�V����������int�l
     */
    public static int showQuestion(Component parentComponent, Object message) {
        MessageType type = MessageType.QUESTION_MESSAGE;
        return show(type, parentComponent, message, type.getOption(), getDefaultTitle(type));
    }

    /**
     * �f�t�H���g�̃^�C�g���A�w�肵���I�v�V�����^�C�v�̃G���[���b�Z�[�W�_�C�A���O��\�����܂��B
     * 
     * @param parentComponent  �I�[�i�[�R���|�[�l���g
     * @param message          �\�����郁�b�Z�[�W
     * @param optionType       �I�v�V�����^�C�v
     * 
     * @return ���[�U�[���I�������I�v�V����������int�l
     */
    public static int showQuestion(Component parentComponent, Object message,
            OptionType optionType) {
        MessageType type = MessageType.QUESTION_MESSAGE;
        return show(type, parentComponent, message, optionType, getDefaultTitle(type));
    }

    /**
     * �w�肵���^�C�g���A�w�肵���I�v�V�����^�C�v�̃G���[���b�Z�[�W�_�C�A���O��\�����܂��B
     * 
     * @param parentComponent  �I�[�i�[�R���|�[�l���g
     * @param message          �\�����郁�b�Z�[�W
     * @param optionType       �I�v�V�����^�C�v
     * @param title            �^�C�g��
     * 
     * @return ���[�U�[���I�������I�v�V����������int�l
     */
    public static int showQuestion(Component parentComponent, Object message,
            OptionType optionType, String title) {
        return show(MessageType.QUESTION_MESSAGE, parentComponent, message, optionType, title);
    }

    /**
     * �w�肵�����b�Z�[�W�^�C�v�A�w�肵���^�C�g���A�w�肵���I�v�V�����^�C�v�̃��b�Z�[�W�_�C�A���O��\�����܂��B
     * 
     * @param messageType      ���b�Z�[�W�^�C�v
     * @param parentComponent  �I�[�i�[�R���|�[�l���g
     * @param message          �\�����郁�b�Z�[�W
     * @param optionType       �I�v�V�����^�C�v
     * @param title            �^�C�g��
     * 
     * @return ���[�U�[���I�������I�v�V����������int�l
     * 
     * @throws NullPointerException  messageType��null�̏ꍇ
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
     * ���[�U�[���_�C�A���O�Łu�͂��v��I�����������肵�܂��B
     * 
     * @param option  ���[�U�[���I�������I�v�V����������int�l
     * 
     * @return ���[�U�[���u�͂��v��I�������ꍇ��true��Ԃ�
     */
    public static boolean isYesSelected(int option) {
        return option == JOptionPane.YES_OPTION;
    }

    /**
     * ���[�U�[���_�C�A���O�Łu�������v��I�����������肵�܂��B
     * 
     * @param option  ���[�U�[���I�������I�v�V����������int�l
     * 
     * @return ���[�U�[���u�������v��I�������ꍇ��true��Ԃ�
     */
    public static boolean isNoSelected(int option) {
        return option == JOptionPane.NO_OPTION;
    }

    /**
     * ���[�U�[���_�C�A���O�Łu�����v��I�����������肵�܂��B
     * 
     * @param option  ���[�U�[���I�������I�v�V����������int�l
     * 
     * @return ���[�U�[���u�����v��I�������ꍇ��true��Ԃ�
     */
    public static boolean isOkSelected(int option) {
        return option == JOptionPane.OK_OPTION;
    }

    /**
     * ���[�U�[���_�C�A���O�Łu������v��I�����������肵�܂��B
     * 
     * @param option  ���[�U�[���I�������I�v�V����������int�l
     * 
     * @return ���[�U�[���u������v��I�������ꍇ��true��Ԃ�
     */
    public static boolean isCancelSelected(int option) {
        return option == JOptionPane.CANCEL_OPTION;
    }

    /**
     * ���[�U�[���_�C�A���O����������肵�܂��B
     * 
     * @param option  ���[�U�[���I�������I�v�V����������int�l
     * 
     * @return ���[�U�[���_�C�A���O������ꍇ��true��Ԃ�
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
