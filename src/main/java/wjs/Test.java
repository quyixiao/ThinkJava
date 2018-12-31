package wjs;

import com.alibaba.fastjson.JSON;
import wjs.entity.LoanInvest;
import wjs.entity.t_invest;
import wjs.entity.t_loan_record;

import java.util.*;

public class Test {
    public static void main(String[] args) {

        Map<String, Object> resultInfo = new LinkedHashMap<String, Object>();
        resultInfo.put("code", 1);
        resultInfo.put("msg", "成功");
        List<LoanInvest> loanInvests = new ArrayList<LoanInvest>();
        List<t_invest> myInvest = findInvestByWPlanId();

        if (myInvest != null && myInvest.size() > 0) {
            List<t_invest> investRecords = findInvestByWInvestId();
            double sumInvestAmount = getSumInvestAmount(investRecords);
            List<t_loan_record> loanRecords = bindLoanRecordByWplanId();


            double investSum = 0;
            double recordSum = 0;
            int i = 0;
            int j = 0;
            int size = 0;
            while (size < myInvest.size() && sumInvestAmount > investSum  ) {                    //修改
                boolean flag = false;
                List<Integer> ids = new ArrayList<Integer>();
                for (int a = i; a < investRecords.size(); a++) {
                    t_invest invest = investRecords.get(a);
                    i++;
                    investSum += invest.amount;
                    if (isInvest(myInvest, invest)) {
                        flag = true;
                        size++;
                        break;
                    }
                    if (investSum >= recordSum) {
                        break;
                    }
                }
                int z = 0;
                double preRecordSum = 0;
                if (flag) {
                    preRecordSum = recordSum;
                }
                for (int b = j; b < loanRecords.size(); b++) {
                    t_loan_record loan_record = loanRecords.get(b);
                    j ++;
                    recordSum += Double.parseDouble(loan_record.amount);
                    if (flag) {
                        if (z == 0 && b - 1 >= 0) {
                            z ++;
                            ids.add(b - 1);
                        }
                        ids.add(b);
                    }
                    if (recordSum >= investSum) {
                        break;
                    }
                }
                double sumAmount = 0;
                if (ids.size() > 0) {
                    double curentTenderAmount = investRecords.get(i - 1).amount;
                    double preInvestSum = investSum - curentTenderAmount;
                    for (int f = 0; f < ids.size(); f++) {
                        int h = ids.get(f);
                        t_loan_record loanRecord = loanRecords.get(h);
                        t_invest invest = investRecords.get(i - 1);
                        if (f > 0) {
                            preRecordSum = preRecordSum + Double.parseDouble(loanRecord.amount);
                        }
                        double amount = 0;
                        if (preRecordSum >= preInvestSum && preRecordSum <= investSum) {
                            if (f == 0) {                          //如果是第一笔
                                amount = preRecordSum - preInvestSum;
                            } else {                              //如果是中间笔数
                                amount = Double.parseDouble(loanRecords.get(ids.get(f)).amount);
                            }
                        }

                        if (preRecordSum > investSum) {                       //如果是最后一笔
                            amount = curentTenderAmount - sumAmount;
                        }

                        if (amount > 0) {
                            sumAmount += amount;
                            loanInvests.add(getLoanInvest(invest, loanRecord, amount));
                        }
                    }
                }
            }
        }
        resultInfo.put("msg", "成功");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("loanInvests",loanInvests);
        resultInfo.put("data",map);
        System.out.println(JSON.toJSONString(resultInfo));
    }


    /* */


    public static List<t_invest> findInvestByWPlanId() {
        List<t_invest> t_invests = new ArrayList<t_invest>();
        //
        t_invest invest0 = new t_invest();
        invest0.id = 2l;


        t_invests.add(invest0);
        return t_invests;
    }


    public static List<t_invest> findInvestByWInvestId() {
        List<t_invest> t_invests = new ArrayList<t_invest>();

        t_invest invest1 = new t_invest();
        invest1.id = 1l;
        invest1.amount = 1000;

        t_invest invest2 = new t_invest();
        invest2.id = 2l;
        invest2.amount = 2000;

        t_invest invest3 = new t_invest();
        invest3.id = 3l;
        invest3.amount = 3000;

        t_invest invest4 = new t_invest();
        invest4.id = 4l;
        invest4.amount = 4000;


        t_invest invest5 = new t_invest();
        invest5.id = 5l;
        invest5.amount = 7910;


        t_invest invest6 = new t_invest();
        invest6.id = 6l;
        invest6.amount = 5000;


        t_invest invest7 = new t_invest();
        invest7.id = 7l;
        invest7.amount = 6000;



        t_invest invest8 = new t_invest();
        invest8.id = 8l;
        invest8.amount = 8000;



        t_invests.add(invest1);
        t_invests.add(invest2);
        t_invests.add(invest3);
        t_invests.add(invest4);

        t_invests.add(invest5);
        t_invests.add(invest6);
        t_invests.add(invest7);
        t_invests.add(invest8);

        return t_invests;
    }

    public static List<t_loan_record> bindLoanRecordByWplanId() {
        List<t_loan_record> loan_records = new ArrayList<t_loan_record>();


        t_loan_record loan_record1 = new t_loan_record();
        loan_record1.id = 1l;
        loan_record1.amount = "500";

        t_loan_record loan_record2 = new t_loan_record();
        loan_record2.id = 2l;
        loan_record2.amount = "800";


        t_loan_record loan_record3 = new t_loan_record();
        loan_record3.id = 3l;
        loan_record3.amount = "9600";


        t_loan_record loan_record4 = new t_loan_record();
        loan_record4.id = 4l;
        loan_record4.amount = "1200";


        t_loan_record loan_record5 = new t_loan_record();
        loan_record5.id = 5l;
        loan_record5.amount = "2500";


        t_loan_record loan_record6 = new t_loan_record();
        loan_record6.id = 6l;
        loan_record6.amount = "1000";


        t_loan_record loan_record7 = new t_loan_record();
        loan_record7.id = 7l;
        loan_record7.amount = "3100";



        t_loan_record loan_record8 = new t_loan_record();
        loan_record8.id = 8l;
        loan_record8.amount = "2500";


        t_loan_record loan_record9 = new t_loan_record();
        loan_record9.id = 9l;
        loan_record9.amount = "400";



        t_loan_record loan_record10 = new t_loan_record();
        loan_record10.id = 10l;
        loan_record10.amount = "2800";



        t_loan_record loan_record11 = new t_loan_record();
        loan_record11.id = 11l;
        loan_record11.amount = "9600";


        t_loan_record loan_record12 = new t_loan_record();
        loan_record12.id = 12l;
        loan_record12.amount = "1600";


        t_loan_record loan_record13 = new t_loan_record();
        loan_record13.id = 13l;
        loan_record13.amount = "5600";




        t_loan_record loan_record14 = new t_loan_record();
        loan_record14.id = 14l;
        loan_record14.amount = "8900";


        t_loan_record loan_record15 = new t_loan_record();
        loan_record15.id = 15l;
        loan_record15.amount = "30000";


        loan_records.add(loan_record1);
        loan_records.add(loan_record2);
        loan_records.add(loan_record3);
        loan_records.add(loan_record4);
        loan_records.add(loan_record5);
        loan_records.add(loan_record6);
        loan_records.add(loan_record7);
        loan_records.add(loan_record8);
        loan_records.add(loan_record9);
        loan_records.add(loan_record10);
        loan_records.add(loan_record11);
        loan_records.add(loan_record12);
        loan_records.add(loan_record13);
        loan_records.add(loan_record14);
        loan_records.add(loan_record15);


        return loan_records;
    }


    public static LoanInvest getLoanInvest(t_invest invest, t_loan_record loanRecord, double amount) {
        LoanInvest loanInvest = new LoanInvest();
        loanInvest.setInvestId(invest.id);
        loanInvest.setLoanId(loanRecord.id);
        loanInvest.setAmount(amount);
        return loanInvest;
    }

    public static double getSumInvestAmount(List<t_invest> investRecords) {
        double sumAmount = 0;
        for (t_invest invest : investRecords) {
            sumAmount += invest.amount;
        }
        return sumAmount;
    }


    public static boolean isInvest(List<t_invest> myInvests, t_invest invest) {
        for (t_invest myInvest : myInvests) {
            if (invest.id.equals(myInvest.id)) {
                return true;
            }
        }
        return false;
    }

}
