package javavm;


/***
 *
 *
 *
 * 实验结果表明，在单个线程下，无论是由于栈帧太太，还是虚拟机栈容量太小，当内存无法
 * 分配时候，虚拟机抛出都是StackOverflowError 异常
 *
 * 如果测试时不限于单个线程，原因其实不难理解，操作系统分配给每个进程的内存是有限制的，
 *
 *
 *
 *
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength ++;
        stackLeak();
    }


    public static void main(String[] args) {
        try {
            JavaVMStackSOF oom = new JavaVMStackSOF();
            oom.stackLeak();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
