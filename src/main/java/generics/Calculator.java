package generics; /**
 * @author luxgql
 */

import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    String lastOption;//保存运算符号
    double a, result, k, b;//保存计算结果
    boolean start, one;//是否开始输入数字
    int fh;
    JTextField text1;//显示文本框
    //构成计算器的按钮用按钮数组来实现
    JButton[] btn;
    JButton[] btn1;
    String p = "";

    public void go() {
        start = true;
        lastOption = "=";
        JFrame myWindow = new JFrame("简单计算器");
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        text1 = new JTextField(26);
        btn = new JButton[12];
        btn1 = new JButton[8];
        Container cp = myWindow.getContentPane();
//设置面板的布局方式GridLayout 
        p1.setLayout(new GridLayout(4, 3, 12, 13));
        p2.setLayout(new GridLayout(4, 2, 12, 13));
        text1.setEditable(false);
        text1.setFont(new Font("黑体", Font.PLAIN, 21));
        text1.setForeground(Color.RED);
        cp.add(text1, BorderLayout.NORTH);
//构成面板 
// for(int i=0;i<8;i++){ 
// btn1[i]=new JButton(i+""); 
// } 
//构建符号并加入到按钮中 
        btn1[7] = new JButton("显示");
        btn1[6] = new JButton("/");
        btn1[5] = new JButton("C");
        btn1[4] = new JButton("*");
        btn1[3] = new JButton("退格");
        btn1[2] = new JButton("-");
        btn1[1] = new JButton("+/-");
        btn1[0] = new JButton("+");//采用for循环添加组件
        cp.add(p1, "West"); //放置中间容器p1
        cp.add(p2, "East"); ////放置中间容器p2
        for (int i = 0; i < 8; i++) {
            p2.add(btn1[i]);
        }
        for (int i = 0; i < 10; i++) {
            btn[i] = new JButton(i + "");
            p1.add(btn[i]);
        }
        btn[11] = new JButton(".");
        p1.add(btn[11]);
        btn[10] = new JButton("=");
        p1.add(btn[10]);//采用for 循环为组件注册事件监听器
        for (int i = 0; i < 10; i++) {
            btn[i].addActionListener(new NumHandle());
        }
        btn[10].addActionListener(new OPtionHandle());
        btn[11].addActionListener(new OPtionHandle());
        for (int j = 0; j < 8; j++) {
            btn1[j].addActionListener(new OPtionHandle());
        }
        myWindow.pack();
        myWindow.setVisible(start);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //创建内部类NumHandle监听数字键动作
    class NumHandle implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String num = e.getActionCommand();//得到数字键上的数字
            for (int i = 0; i < 10; i++) {
                if (num.equals(i + "")) {
                    if (text1.getText().equals("0")) //判断文本域中显示的数字是否为0
                        text1.setText(i + "");
                    else
                        text1.setText(text1.getText() + i);
                }
            }
        }
    }

    //内部类OPtionHandle监听符号键的动作
    class OPtionHandle implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String option = e.getActionCommand();
            if (option.equals(".")) {
                if (!text1.getText().contains(".")) {
                    text1.setText(text1.getText() + ".");
                }
            }
/*if(option.equals("sin")){ 
a=Double.parseDouble(text1.getText());//将文本域上的字符串转换为double型 
double b=Math.sin(a/180*Math.PI); //现将a转换为弧度制,再进行运算 
text1.setText(String.valueOf(b)); //输出运算结果 
} 
if(option.equals("cos")){ 
a=Double.parseDouble(text1.getText()); 
double b=Math.cos(a/180*Math.PI); 
text1.setText(String.valueOf(b)); 
} 
if(option.equals("tan")){ 
a=Double.parseDouble(text1.getText()); 
double b=Math.tan(a/180*Math.PI); 
text1.setText(String.valueOf(b)); 
} 
if(option.equals("log")){ 
a=Double.parseDouble(text1.getText()); 
double b=Math.log(a)/Math.log(10.0); 
text1.setText(String.valueOf(b)); 
} 
if(option.equals("sqrt")){ 
a=Double.parseDouble(text1.getText()); 
double b=Math.sqrt(a); 
text1.setText(String.valueOf(b)); 
} 
if(option.equals("pow")){ //该运算含有两个操作数 
a=Double.parseDouble(text1.getText()); 
fh=4; // 
text1.setText(""); //将文本域的内容清空 
} 
if(option.equals("exp")){ 
a=Double.parseDouble(text1.getText()); 
b=a; 
double b=Math.exp(a); 
text1.setText(String.valueOf(b)); 
} 
if(option.equals("求倒")){ 
a=Double.parseDouble(text1.getText()); 
text1.setText(String.valueOf(1/a)); 
}*/
            if (option.equals("+/-")) {
                if (!"0".equals(text1.getText())) { //判断文本域的数字是否为0
                    a = Double.parseDouble(text1.getText());
                    a = -a; //求相反数运算
                    text1.setText(String.valueOf(a));
                }
            }
            if (option.equals("退格")) {
                int i = text1.getText().length();
                text1.setText(text1.getText().substring(0, i - 1));
            }
            if (option.equals("C")) {
                text1.setText(""); //清空文本域中的内容
                a = 0;
                result = 0;
                p = "";
                for (JButton b : btn) {
                    b.setEnabled(true);
                }
                for (JButton b : btn1) {
                    b.setEnabled(true);
                }
            }
            if (option.equals("+")) {
                a = Double.parseDouble(text1.getText());
                fh = 0;
                text1.setText("");
                one = true;
                p = option;
            }
            if (option.equals("-")) {
                a = Double.parseDouble(text1.getText());
                fh = 1;
                text1.setText("");
                one = true;
                p = option;
            }
            if (option.equals("*")) {
                a = Double.parseDouble(text1.getText());
                fh = 2;
                text1.setText("");
                one = true;
                p = option;
            }
            if (option.equals("/")) {
                a = Double.parseDouble(text1.getText());
                fh = 3;
                text1.setText("");
                one = true;
                p = option;
            }
//有两个操作的运算 
            if (option.equals("=")) {
                double c = 0;
                if (text1.getText().equals("")) {
                    c = a;
                } else {
                    c = Double.parseDouble(text1.getText());
                    if (one) {
                        k = c;
                    }
                }
                switch (fh) {
                    case 0: //执行加法运算
                        result = a + c;
                        fh = 8;
                        one = false;
                        break;
                    case 1: //执行减法运算
                        result = a - c;
                        fh = 9;
                        one = false;
                        break;
                    case 2: //执行乘法运算
                        result = a * c;
                        fh = 10;
                        one = false;
                        break;
                    case 3: //执行除法运算
                        result = a / c;
                        fh = 11;
                        one = false;
                        break;
                    case 8:
                        result = c + k;
                        break;
                    case 9:
                        result = c - k;
                        break;
                    case 10:
                        result = c * k;
                        break;
                    case 11:
                        result = c / k;
                        break;
                    default:
                        result = a;
                        break;
                }
                text1.setText(getIn(result));//将运算结果转换为字符串形式输出
            }
            if (option.equals("显示")) {
                String o = text1.getText();
                text1.setText(getIn(a) + p + getIn(k) + "=" + o);
                for (JButton b : btn) {
                    b.setEnabled(false);
                }
                for (JButton b : btn1) {
                    b.setEnabled(false);
                }
                btn1[5].setEnabled(true);
            }
        }
    }

    public String getIn(Double d) {
        String r = d + "";
        if (r.split("/.").length > 1) {
            if (r.split("/.")[1].equals("0")) {
                r = r.split("/.")[0];
            }
        }
        return r;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calculator window = new Calculator();
        window.go();
// TODO code application logic here 
    }
}