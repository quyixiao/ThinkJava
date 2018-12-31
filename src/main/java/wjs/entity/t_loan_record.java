package wjs.entity;


/**
 * 借款人信息
 */
public class t_loan_record extends Model {

	/**
	 * 借款编号
	 */
	public String loan_number;

	/**
	 * 借款期限
	 */
	public String loan_time_limit;

	/**
	 * 借款金额
	 */
	public double loan_amount;

	/**
	 * 放款时间
	 */
	public String loan_time;

	/**
	 * 应还款日期
	 */
	public String expire_time;

	/**
	 * 用户姓名
	 */
	public String name;

	/**
	 * 联系方式
	 */
	public String contact_info;


	/**
	 * 用户身份证号
	 */
	public String id_number;

	/**
	 * 用户身份证号认证
	 */
	public int identity_approve;

	/**
	 * 用户银行卡认证
	 */
	public int bankcard_approve;

	/**
	 * 手机认证
	 */
	public int phone_approve;

	/**
	 * 社保认证
	 */
	public int social_approve;

	/**
	 * 公积金认证
	 */
	public int accumulation_fund_approve;

	/**
	 * 芝麻信用认证
	 */
	public int sesame_credit_approve;

	/**
	 * 工作认证
	 */
	public int work_approve;

	/**
	 * 借款用途
	 */
	public String loan_use;

	/**
	 * 支付渠道订单编号
	 */
	public String pay_source_orderNo;

	/**
	 * 关联标的ID
	 */
	public long bid;

	public String request_no;

	public String getRequest_no() {
		return request_no;
	}

	public void setRequest_no(String request_no) {
		this.request_no = request_no;
	}

	/**
	 * H5 显示借款金额使用
	 */
	public String amount;

	public String getLoan_number() {
		return loan_number;
	}

	public void setLoan_number(String loan_number) {
		this.loan_number = loan_number;
	}

	public String getLoan_time_limit() {
		return loan_time_limit;
	}

	public void setLoan_time_limit(String loan_time_limit) {
		this.loan_time_limit = loan_time_limit;
	}

	public double getLoan_amount() {
		return loan_amount;
	}

	public void setLoan_amount(double loan_amount) {
		this.loan_amount = loan_amount;
	}

	public String getLoan_time() {
		return loan_time;
	}

	public void setLoan_time(String loan_time) {
		this.loan_time = loan_time;
	}

	public String getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(String expire_time) {
		this.expire_time = expire_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact_info() {
		return contact_info;
	}

	public void setContact_info(String contact_info) {
		this.contact_info = contact_info;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public int getIdentity_approve() {
		return identity_approve;
	}

	public void setIdentity_approve(int identity_approve) {
		this.identity_approve = identity_approve;
	}

	public int getBankcard_approve() {
		return bankcard_approve;
	}

	public void setBankcard_approve(int bankcard_approve) {
		this.bankcard_approve = bankcard_approve;
	}

	public int getPhone_approve() {
		return phone_approve;
	}

	public void setPhone_approve(int phone_approve) {
		this.phone_approve = phone_approve;
	}

	public int getSocial_approve() {
		return social_approve;
	}

	public void setSocial_approve(int social_approve) {
		this.social_approve = social_approve;
	}

	public int getAccumulation_fund_approve() {
		return accumulation_fund_approve;
	}

	public void setAccumulation_fund_approve(int accumulation_fund_approve) {
		this.accumulation_fund_approve = accumulation_fund_approve;
	}

	public int getSesame_credit_approve() {
		return sesame_credit_approve;
	}

	public void setSesame_credit_approve(int sesame_credit_approve) {
		this.sesame_credit_approve = sesame_credit_approve;
	}

	public int getWork_approve() {
		return work_approve;
	}

	public void setWork_approve(int work_approve) {
		this.work_approve = work_approve;
	}

	public String getLoan_use() {
		return loan_use;
	}

	public void setLoan_use(String loan_use) {
		this.loan_use = loan_use;
	}

	public String getPay_source_orderNo() {
		return pay_source_orderNo;
	}

	public void setPay_source_orderNo(String pay_source_orderNo) {
		this.pay_source_orderNo = pay_source_orderNo;
	}

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}
