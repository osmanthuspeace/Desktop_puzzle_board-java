package UI;

import javax.swing.*;

//登陆界面

public class Login extends JFrame {

    public Login(){
        this.setSize(480,430);
        this.setTitle("Login");//设置标题
        //    this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);//居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭时结束程序

        this.setVisible(true);
    }
}
