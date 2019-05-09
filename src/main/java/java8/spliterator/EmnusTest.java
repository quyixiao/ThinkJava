package java8.spliterator;

public enum  EmnusTest {


    ACTIVE_REPAY(1, "主动还款"),
    AUTH_WITHHOLD(2, "自动代扣"),;
    /**
     * 模块名
     */
    private Integer code;
    private String desc;

    EmnusTest(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }


    @Override
    public String toString() {
        String str = "code="+code + ",desc="+desc;
        return str;
    }


    public static void main(String[] args) {
        System.out.println(ACTIVE_REPAY);
    }
}
