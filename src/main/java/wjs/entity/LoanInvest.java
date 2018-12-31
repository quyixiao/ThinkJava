package wjs.entity;

public class LoanInvest {
    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 投资id
     */
    private Long investId;
    /**
     * 放款id
     */
    private Long loanId;

    /**
     * 金额
     */
    private double amount;

    /**
     * 编号
     */
    private String orderNo;

    /**
     * 状态 ， 撮合中，计息中，己结清
     */
    private String status;

    /**
     * 匹配时间
     */
    private String matchTime;


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getInvestId() {
        return investId;
    }

    public void setInvestId(Long investId) {
        this.investId = investId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }
}
