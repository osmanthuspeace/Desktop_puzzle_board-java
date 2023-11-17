package UI;

//注册界面

import javax.swing.*;

public class Register extends JFrame {
    public Register(){
        this.setSize(600,500);
        this.setTitle("Register");//设置标题
        //    this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);//居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭时结束程序

        this.setVisible(true);
    }
}
