package java8.spliterator;

import com.alibaba.fastjson.JSON;

public class NumCounter {
    private int num;
    private int sum;
    // 是否当前是个完整的数字
    private boolean isWholeNum;

    public NumCounter(int num, int sum, boolean isWholeNum) {
        this.num = num;
        this.sum = sum;
        this.isWholeNum = isWholeNum;
    }

    public NumCounter accumulate(Character c) {
        System.out.println(Thread.currentThread().getName() + ",c=" + c);
        NumCounter numCounter = null;
        if (Character.isDigit(c)) {
            if(isWholeNum){
                numCounter = new NumCounter(Integer.parseInt("" + c), sum, false);
                System.out.println("===============11111111=========num="+num+",c="+c + ",sum="+sum + ",result"+JSON.toJSONString(numCounter));
            }else{
                numCounter= new NumCounter(Integer.parseInt("" + num + c), sum, false);
                System.out.println("+++++++++++++22222++++++++++++num="+num+",c="+c + ",sum="+sum + ",result"+JSON.toJSONString(numCounter));
            }
        } else {
            numCounter= new NumCounter(0, sum + num, true);
            System.out.println("--------------333333----------num="+num+",c="+c + ",sum="+sum + ",result"+JSON.toJSONString(numCounter));
        }

        return numCounter;

    }
    public NumCounter combine(NumCounter numCounter){
        return new NumCounter(0, this.getSum() + numCounter.getSum(), numCounter.isWholeNum);
    }

    public int getSum() {
        return sum + num;
    }
}