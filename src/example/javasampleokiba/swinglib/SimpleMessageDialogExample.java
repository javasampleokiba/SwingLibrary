package example.javasampleokiba.swinglib;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import javasampleokiba.swinglib.OptionPaneDefaults;
import javasampleokiba.swinglib.SimpleMessageDialog;

/**
 * SimpleMessageDialog�N���X�̎��s��
 */
public class SimpleMessageDialogExample extends JFrame {

    public static void main(String[] args) {

        /* ---------------------------------
         * SimpleMessageDialog��UI�𓝈ꂷ��
         * ---------------------------------
         */
        // �f�t�H���g�̃^�C�g����ݒ�
        SimpleMessageDialog.setDefaultTitles("�G���[!", "���!", "�x��!", "����!");

        /*
         * �f�t�H���g�̃I�v�V�����^�C�v��ݒ�
         * �E�G���[�_�C�A���O�̃{�^�����u�����v�ɓ���
         * �E���_�C�A���O�̃{�^�����u�����v�ɓ���
         * �E�x���_�C�A���O�̃{�^�����u�����v�u������v�ɓ���
         * �E����_�C�A���O�̃{�^�����u�͂��v�u�������v������u�v�ɓ���
         */
        SimpleMessageDialog.setDefaultOptions(
                SimpleMessageDialog.OptionType.DEFAULT_OPTION,
                SimpleMessageDialog.OptionType.DEFAULT_OPTION,
                SimpleMessageDialog.OptionType.OK_CANCEL_OPTION,
                SimpleMessageDialog.OptionType.YES_NO_CANCEL_OPTION);

        /* ---------------------------------
         * �V�X�e���S�̂Ń��b�Z�[�W�_�C�A���O��UI�𓝈ꂷ��
         * ---------------------------------
         */
        /*
         * �f�t�H���g�̃^�C�g����ݒ�
         * �iSimpleMessageDialog#setDefaultTitle���ݒ肳��Ă���΂����炪�D�悳��܂��j
         */
        {
            OptionPaneDefaults.setText(OptionPaneDefaults.TextKey.TITLE_TEXT, "����");
            OptionPaneDefaults.setText(OptionPaneDefaults.TextKey.MSG_DIALOG_TITLE, "���");
        }
        // �f�t�H���g�̃{�^���̃e�L�X�g�A�j�[���j�b�N��ݒ�
        {
            OptionPaneDefaults.setButtonText("OK!", "CANCEL!", "YES!", "NO!");
            OptionPaneDefaults.setButtonMnemonic(KeyEvent.VK_O, KeyEvent.VK_C, KeyEvent.VK_Y, KeyEvent.VK_N);
        }

        new SimpleMessageDialogExample().setVisible(true);
    }

    public SimpleMessageDialogExample() {
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �G���[�_�C�A���O�\��
        SimpleMessageDialog.showError(this, "�G���[����!");
        // ���_�C�A���O�\��
        SimpleMessageDialog.showInformation(this, "��񂾂�!");
        // �x���_�C�A���O�\��
        SimpleMessageDialog.showWarning(this, "�x���ł�!");
        // ����_�C�A���O�\��
        SimpleMessageDialog.showQuestion(this, "���₾!");
    }
}