package wjs.entity;


import java.util.Date;

/**
 *
 *投资表
 *
 * @author liudong
package models.core.entity;

import java.util.Date;

import javax.persistence.Entity;

import common.enums.Client;
import play.db.jpa.Model;

/**
 *
 *投资表
 *
 * @author liudong
 * @createDate 2015年12月15日
 */
public class t_invest extends Model {	public Date time; public Date gmt_modified;

	/** 用户Id(投资人) */
	public Long user_id;

	/** 投资时间 */
//	public Date time;

	/** 借款标ID */
	public long bid_id;

	/** 投资金额  */
	public double amount;

	/** 纠正本金 */
	public double correct_amount;

	/** 纠偏利息 */
	public double correct_interest;

	/** 分摊到每一笔投资的借款服务费 */
	public double loan_service_fee_divide;

	/** 投资入口*/
	private int client;

	/** 债权转让状态:0 正常(转让入的也是0) -1 已转让出 1 转让中 */
	private int transfer_status;

	/** 债权ID（申请完成之后的债权id）*/
	public long debt_id;//

	/** 业务订单号,投标操作唯一标识 */
	public String service_order_no;

	/** 交易订单号。托管方产生 */
	public String trust_order_no;

	/** 投标方式: 1pc 2自动 3android 4ios 5wechat*/
	private int invest_type;

	/** 退款流水号 */
	public String refundfail_bill_no;

	/** 优惠券类别 **/
	public int vocher_type;

	/** 红包、代金券金额 **/
	public double vocher_amt;

	/**投资次数*/
	public int invest_num;

	/**
	 * w_plan_id
	 */
	public long w_plan_id;


	public long getW_plan_id() {
		return w_plan_id;
	}

	public void setW_plan_id(long w_plan_id) {
		this.w_plan_id = w_plan_id;
	}

	public InvestType getInvest_type() {
		InvestType invest_type = InvestType.getEnum(this.invest_type);

		return invest_type;
	}

	public void setInvest_type(InvestType invest_type) {
		this.invest_type = invest_type.code;
	}



	public TransferStatus getTransfer_status() {
		return TransferStatus.getEnum(this.transfer_status);
	}

	public void setTransfer_status(TransferStatus transfer_status) {
		this.transfer_status = transfer_status.code;
	}

	/**加息券ID**/
	public long rate_id;
	/**加息金额**/
	public double rate_amount;

	/**投标所在区域*/
	public String invest_area;

	/**
	 * 红包投资金额
	 */
	public double red_packet_amount ;

	/**
	 * 红包ID
	 */
	public long red_packet_id ;

	public int trade_status;

	/**
	 * 枚举类型:转让状态
	 *
	 * @description
	 *
	 * @author DaiZhengmiao
	 * @createDate 2015年12月8日
	 */
	public enum TransferStatus {

		/** 正常:0 */
		NORMAL(0,"正常"),

		/** 已转让:-1 */
		TRANSFERED(-1,"已转让"),

		/** 转让中:1 */
		TRANSFERING(1,"转让中");

		public int code;
		public String value;
		private TransferStatus(int code, String value) {
			this.code = code;
			this.value = value;
		}

		/**
		 * 根据code取得客户端类型,没有找到返回null
		 *
		 * @param code
		 * @return
		 *
		 * @author DaiZhengmiao
		 * @createDate 2015年12月8日
		 */
		public static TransferStatus getEnum(int code){
			TransferStatus[] clients = TransferStatus.values();
			for (TransferStatus cli : clients) {
				if (cli.code == code) {

					return cli;
				}
			}

			return null;
		}
	}

	/**
	 * 枚举类型:投标方式
	 *
	 * @description
	 *
	 * @author ZhouYuanZeng
	 * @createDate 2016年3月29日 下午2:32:18  
	 */
	public enum InvestType {
		/** PC:1 */
		PC(1,"PC","Web端","/public/common/investtype-pc.png"),

		/** 自动:2 */
		AUTO(2,"自动","自动","/public/common/investtype-auto.png"),

		/** 安卓:2 */
		ANDROID(3,"Android","安卓客户端","/public/common/investtype-android.png"),

		/** 安卓:2 */
		IOS(4,"IOS","IOS客户端","/public/common/investtype-ios.png"),

		/** 微信:3 */
		WECHAT(5,"WECHAT","微信端","/public/common/investtype-wechat.png");

		public int code;
		public String value;
		public String description;
		public String path;

		private InvestType(int code, String value, String description, String path) {
			this.code = code;
			this.value = value;
			this.description = description;
			this.path = path;
		}

		/**
		 * 根据code取得投标方式,没有找到返回null
		 *
		 * @param code
		 * @return
		 *
		 * @author ZhouYuanZeng
		 * @createDate 2016年3月29日
		 */
		public static InvestType getEnum(int code){
			InvestType[] investTypes = InvestType.values();
			for (InvestType type : investTypes) {
				if (type.code == code) {

					return type;
				}
			}

			return PC;
		}
	}


	public int getInvest_num() {
		return invest_num;
	}

	public void setInvest_num(int invest_num) {
		this.invest_num = invest_num;
	}
}
