/*
 *@Copyright (c) 2016, 浙江阿拉丁电子商务股份有限公司 All Rights Reserved. 
 */
package java8.stream;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal计算类
 * @author xiaotianjian
 * @description
 * @email xiaotianjian@ldxinyong.com
 * @date 2018年12月24日 12:24
 * @注意：本内容仅限于杭州霖梓网络科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class BigDecimalUtil {

    public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    /**
     * 加法,保留小数点两位
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        v1 = v1 == null ? new BigDecimal(0) : v1;
        v2 = v2 == null ? new BigDecimal(0) : v2;
        v1 = v1.add(v2).setScale(2, RoundingMode.HALF_UP);
        return v1;
    }




    /**
     * 多个加法想家
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal add(BigDecimal... array) {
        BigDecimal result = BigDecimal.ZERO;
        if (array == null || array.length == 0) {
            return BigDecimal.ZERO;
        }
        for (int i = 0; i < array.length; i++) {
            result = add(result, array[i]);
        }
        return result;
    }

    /**
     * 加法,保留小数点两位
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal add(double v1, double v2) {
        BigDecimal value = new BigDecimal(v1).add(new BigDecimal(v2)).setScale(2, RoundingMode.HALF_UP);
        return value;
    }

    /**
     * 减法v1-v2
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal subtract(BigDecimal v1, BigDecimal v2) {
        v1 = v1 == null ? new BigDecimal(0) : v1;
        v2 = v2 == null ? new BigDecimal(0) : v2;
        v1 = v1.subtract(v2).setScale(2, RoundingMode.HALF_UP);
        return v1;
    }

    /**
     * 减法v1-v2
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal subtract(double v1, double v2) {
        BigDecimal value = new BigDecimal(v1).subtract(new BigDecimal(v2)).setScale(2, RoundingMode.HALF_UP);
        return value;
    }

    /**
     * 乘法 v1*v2
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal multiply(double v1, double v2) {
        BigDecimal value = new BigDecimal(v1).multiply(new BigDecimal(v2)).setScale(2, RoundingMode.HALF_UP);
        return value;
    }

    /**
     * 乘法 v1*v2
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal multiply(BigDecimal v1, BigDecimal v2) {
        v1 = v1 == null ? new BigDecimal(0) : v1;
        v2 = v2 == null ? new BigDecimal(0) : v2;
        v1 = v1.multiply(v2).setScale(2, RoundingMode.HALF_UP);
        return v1;
    }

    /**
     * 多个Bigdecimal相乘
     *
     * @param array
     * @return
     */
    public static BigDecimal multiply(BigDecimal... array) {
        BigDecimal result = BigDecimal.ONE;
        if (array == null || array.length == 0) {
            return BigDecimal.ZERO;
        }
        for (int i = 0; i < array.length; i++) {
            result = multiply(result, array[i]);
        }
        return result;
    }

    /**
     * 除法 v1/v2(v2为0时未抛异常，注意不传空)
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal divide(double v1, double v2) {
        BigDecimal value = new BigDecimal(v1).divide(new BigDecimal(v2), 2, RoundingMode.HALF_UP);
        return value;
    }

    /**
     * 除法 v1/v2(v2为0时未抛异常，注意不传空)
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal divide(BigDecimal v1, BigDecimal v2) {
        v1 = v1 == null ? new BigDecimal(0) : v1;
        v2 = v2 == null ? new BigDecimal(0) : v2;
        v1 = v1.divide(v2, 2, RoundingMode.HALF_UP);
        return v1;
    }

    /**
     * 计算分期还款金额
     *
     * @param amount    --借款本金
     * @param num       --分期数
     * @param monthRate --月利率
     * @param poundage  --总还款手续费
     * @return
     */
    public static BigDecimal getConsumeAmount(BigDecimal amount, int num, BigDecimal monthRate, BigDecimal poundage) {
        amount = amount == null ? new BigDecimal(0) : amount;
        monthRate = monthRate == null ? new BigDecimal(0) : monthRate;
        poundage = poundage == null ? new BigDecimal(0) : poundage;
        BigDecimal v1 = (amount.multiply(monthRate).multiply((BigDecimal.ONE.add(monthRate)).pow(num)))
                .divide((BigDecimal.ONE.add(monthRate)).pow(num).subtract(BigDecimal.ONE), 6, RoundingMode.HALF_UP).add(poundage.divide(new BigDecimal(num), 2, RoundingMode.HALF_UP));
        return v1;
    }

    /**
     * 计算总账单金额
     *
     * @param v1        --借款本金
     * @param num       --分期数
     * @param monthRate --月利率
     * @param poundage  --总还款手续费
     * @return
     */
    public static BigDecimal getConsumeTotalAmount(BigDecimal amount, int num, BigDecimal monthRate, BigDecimal poundage) {
        amount = amount == null ? new BigDecimal(0) : amount;
        monthRate = monthRate == null ? new BigDecimal(0) : monthRate;
        poundage = poundage == null ? new BigDecimal(0) : poundage;
        BigDecimal v1 = (new BigDecimal(num).multiply(amount).multiply(monthRate).multiply((BigDecimal.ONE.add(monthRate)).pow(num)))
                .divide((BigDecimal.ONE.add(monthRate)).pow(num).subtract(BigDecimal.ONE), 6, RoundingMode.HALF_UP).add(poundage);
        return v1;
    }

    /**
     * 获取每期手续费
     * 每期手续费=   (手续费最小值<总手续费-免息期数*手续费<手续费最大值)/期数
     *
     * @param amount       --借款本金
     * @param nper          --分期期数
     * @param poundageRate --手续费率
     * @param min          --手续费下限
     * @param max          --手续费上限
     * @param freeNper     --免息期数
     * @return
     */
    public static BigDecimal getPerPoundage(BigDecimal amount, Integer nper, BigDecimal poundageRate, BigDecimal min, BigDecimal max, Integer freeNper) {
    	//总手续费
    	BigDecimal totalPoundage = amount.multiply(poundageRate).setScale(2, RoundingMode.CEILING);
    	//每一期手续费
    	BigDecimal perAmount = totalPoundage.divide(new BigDecimal(nper),2, RoundingMode.CEILING);
        //总手续费-免息的费用
    	BigDecimal freeAmount = BigDecimalUtil.multiply(perAmount, new BigDecimal(freeNper));
       
    	BigDecimal finalAmount = BigDecimalUtil.subtract(totalPoundage, freeAmount);
     
    	 if (min.compareTo(finalAmount) > 0) {
    		 finalAmount = min;
         } else if (finalAmount.compareTo(max) > 0) {
        	 finalAmount = max;
         }
    	 //最后计算的期数
    	 Integer finalNper =  nper - freeNper;
    	 return finalNper.equals(0) ? BigDecimal.ZERO : finalAmount.divide(new BigDecimal(finalNper), 2, RoundingMode.CEILING);
    }
    /* Old
	 * @param num --分期期数
	 * @param poundageRate --手续费率
	 * @param min --手续费下限
	 * @param max --手续费上限
	 * @return
	 */
	public static BigDecimal getTotalPoundage(BigDecimal amount, int num, BigDecimal poundageRate, BigDecimal min, BigDecimal max){
		amount = amount==null?new BigDecimal(0):amount;
		poundageRate = poundageRate ==null?new BigDecimal(0):poundageRate;
		BigDecimal v1 = amount.multiply(poundageRate);
		if(min.compareTo(v1)>0){
			v1 = min;
		}else if(v1.compareTo(max)>0){
			v1 = max;
		}
		return v1;
	}
	 
    public static int getCreditScore(BigDecimal zmScore, BigDecimal fqzScore, BigDecimal tdScore,
                                     BigDecimal zmRate, BigDecimal fqzRate, BigDecimal tzRate) {
    	//如果同盾分少于80全部 不给信用分
    	if (tdScore.intValue() >= 80) {
    		return 0;
    	}
        BigDecimal v1 = zmScore.divide(new BigDecimal(950), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(10)).multiply(zmRate);
        return v1.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 除法，针对除不尽的情况做进一位处理
     * 例如：10/3 = 3.33 进位处理 3.34
     * @param d1    除数
     * @param d2    被除数
     * @param digit 保留小数点(默认两位小数)
     * @return
     * @author hantao
     */
    public static BigDecimal divHalfUp(BigDecimal d1, BigDecimal d2, Integer digit) {
        if( null == digit){
            digit = 2;
        }
        BigDecimal result = d1.divide(d2,digit, BigDecimal.ROUND_UP);
        return result;
    }

    /**
     * 除法，针对除不尽的情况做进一位处理
     * 例如：10/3 = 3.33 进位处理 3.34
     * @param d1    除数
     * @param d2    被除数
     * @param digit 保留小数点(默认两位小数)
     * @return
     * @author hantao
     */
    public static BigDecimal divHalfDown(BigDecimal d1, BigDecimal d2, Integer digit) {
        if( null == digit){
            digit = 2;
        }
        BigDecimal result = d1.divide(d2,digit, BigDecimal.ROUND_DOWN);
        return result;
    }

    
    public static BigDecimal removeNull(BigDecimal v1) {
    	if(v1 == null){
    		return new BigDecimal(0);
    	}
        return v1;
    }

    public static Long removeNull(Long v1) {
    	if(v1 == null){
    		return 0l;
    	}
        return v1;
    }
    public static Integer removeNull(Integer v1) {
    	if(v1 == null){
    		return 0;
    	}
        return v1;
    }

    /**
     * 返回比较值
     *
     * @param v1
     * @param v2
     * @return -1 v1<v2， 0 v1==v2, 1 v1>v2
     */
    public static int compareTo(final BigDecimal v1, final BigDecimal v2) {
        return v1.compareTo(v2);
    }


    public static BigDecimal set2Scale(BigDecimal bigDecimal) {
        if(bigDecimal == null){

            return new BigDecimal(0.00);

        }
        return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);

    }
}
