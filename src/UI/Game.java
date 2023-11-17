package UI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//主界面

public class Game extends JFrame implements KeyListener, ActionListener {
    int[][] arr=new int[11][11];
    int[][] win={{},{0,1,2,3,4},{0,5,6,7,8},{0,9,10,11,12},{0,13,14,15,16}};
    int x=1,y=1;
    long startTime,endTime;
    int count=0;
    public Game(){//constructor
        initframe();
        initmenu();
        initdata();
        initimg();
        initbuttom();

        this.setVisible(true);//使界面可见
        this.requestFocus();//在Swing中，只有具有焦点的组件才能接收键盘事件。

    }
    public boolean vic(){
        for(int i=1;i<=4;i++){
            for(int j=1;j<=4;j++){
                if(arr[i][j]!=win[i][j])
                    return false;
            }
        }
        return true;
    }

    private void initbuttom() {
        JButton jtb=new JButton("click it!");
        jtb.setBounds(600,800,100,50);
        jtb.addActionListener(e -> System.out.println("clicked!"));
        this.getContentPane().add(jtb);//this表示本类的对象
    }

    private void initdata() {
    //initialize the data.That is,randomly shuffling the data
        int[] temp={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};

        int rr;
        Random r=new Random();
        for(int i=1;i<=16;i++){
            rr=r.nextInt(1,16);
            if(i!=rr) {
                temp[i]^=temp[rr];
                temp[rr]^=temp[i];
                temp[i]^=temp[rr];
            }
        }
        for(int i=0;i<16;i++){//more elegant
            if(temp[i+1]==16){
                x=i/4+1;
                y=i%4+1;
            }
            arr[i/4+1][i%4+1]=temp[i+1];
        }
    }

    private void initimg() {
        this.getContentPane().removeAll();//清除界面
        endTime = System.currentTimeMillis();
        double elapsedTime = (endTime - startTime) / 1000.0; // 转换为秒
        String elapsedTimeString = String.format("%.1f", elapsedTime); // 格式化输出

        if(vic()){
            JLabel winner=new JLabel(new ImageIcon("img/win.png"));
            winner.setBounds(200,280,197,73);
            this.getContentPane().add(winner);
            System.out.printf("耗时 %.1f 秒!\n", elapsedTime);
        }

        JLabel step =new JLabel("Steps:"+count);
        step.setBounds(50,30,100,20);
        this.getContentPane().add(step);

        if(!sign){
            JLabel time =new JLabel("Time:"+elapsedTimeString+"s");
            time.setBounds(460,30,100,20);
            this.getContentPane().add(time);
        }


        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                JLabel jLabel=new JLabel(new ImageIcon("img/"+arr[i+1][j+1]+".png"));
                jLabel.setBounds(1+106*j+80,1+106*i+130,106,106);
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));//设置边框，0为上凸，1为下凹
                this.getContentPane().add(jLabel);//要先调用默认创建的隐藏容器
            }
        }
        //先加载的图片在上层
        ImageIcon bg=new ImageIcon("img/background.png");
        JLabel background=new JLabel(bg);
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);

        this.getContentPane().repaint();//刷新
    }
    JMenuItem replay=new JMenuItem("replay");
    JMenuItem close=new JMenuItem("close");
    JMenuItem account=new JMenuItem("account");
    private void initmenu() {
    //        初始化菜单
        JMenuBar menubar=new JMenuBar();
        JMenu jmenu1=new JMenu("Function");
        JMenu jmenu2=new JMenu("About");

        jmenu1.add(replay);
        jmenu1.add(close);
        jmenu2.add(account);

        replay.addActionListener(this);
        close.addActionListener(this);
        account.addActionListener(this);

        menubar.add(jmenu1);
        menubar.add(jmenu2);

        this.setJMenuBar(menubar);
    }
    boolean sign=true;
    private void initframe() {
        this.setSize(590,680);//设置宽高
        this.setTitle("桌面拼图板 v1.0");//设置标题
        //    this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);//居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭时结束程序
        //取消默认的居中方式
        this.setLayout(null);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override//show the whole image
    public void keyPressed(KeyEvent e) {
        int code2=e.getKeyCode();
        if(code2==KeyEvent.VK_A){
            Game.this.getContentPane().removeAll();
            JLabel all=new JLabel(new ImageIcon("img/all.png"));
            all.setBounds(85,135,420,420);
            Game.this.getContentPane().add(all);

            ImageIcon bg=new ImageIcon("img/background.png");
            JLabel background=new JLabel(bg);
            background.setBounds(40,40,508,560);
            Game.this.getContentPane().add(background);

            Game.this.getContentPane().repaint();//刷新
        }
    }
    int code;
    @Override
    public void keyReleased(KeyEvent e) {
        if(vic())
            return ;//end the method
        if(sign){
            startTime = System.currentTimeMillis();
//            System.out.println("start!!!!");
        }
        sign=false;
//      System.out.println("hello");
        code=e.getKeyCode();//左上右下37-40
        if(code==KeyEvent.VK_X){
            arr[x][y]=arr[x+1][y+1];
            arr[x+1][y+1]=16;
            x++;y++;
            initimg();
        }else if(code==KeyEvent.VK_Q){
            System.exit(0);
        }
        if(code==37&&y<=3){
            arr[x][y]=arr[x][y+1];
            arr[x][y+1]=16;
            y++;count++;
            initimg();
        }else if(code==38&&x<=3){
            arr[x][y]=arr[x+1][y];
            arr[x+1][y]=16;
            x++;count++;
            initimg();
        }else if(code==39&&y>=2){
            arr[x][y]=arr[x][y-1];
            arr[x][y-1]=16;
            y--;count++;
            initimg();
        }else if(code==40&&x>=2){
            arr[x][y]=arr[x-1][y];
            arr[x-1][y]=16;
            x--;count++;
            initimg();
        }else if(code==KeyEvent.VK_W){
            int k=1;
            for(int i=1;i<=4;i++){
                for(int j=1;j<=4;j++){
                    arr[i][j]=k++;
                }
            }
            x=4;y=4;
            initimg();
        }else if(code==KeyEvent.VK_Z){
            initimg();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        System.out.println("there!");
        if(obj==replay){
            sign=true;
            count=0;
            initdata();
            initimg();
        }else if(obj==close){
            System.out.println("close");
            System.exit(0);
        }else if(obj==account){
            System.out.println("come in!!");
            JDialog jd=new JDialog();
            jd.setSize(420,110);
            JLabel jl=new JLabel(new ImageIcon("img/me2.png"));
            jl.setBounds(0,0,420,110);
            jd.getContentPane().add(jl);
            jd.setAlwaysOnTop(true);
            jd.setLocationRelativeTo(null);
            jd.setModal(true);
            jd.setVisible(true);
        }
    }
}
