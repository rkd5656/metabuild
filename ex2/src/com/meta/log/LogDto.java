package com.meta.log;



/**
 * keyWord의 data를 담는 클래스
 * 
 * @author meta
 *
 */
public class LogDto{
	
	private String start;
	private String esbId;
	private String conLen;
	private String callTime;
	private String befM;
	private String nowM;
	private String invoGal;
	private String unM;
	private String end;
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEsbId() {
		return esbId;
	}
	public void setEsbId(String esbId) {
		this.esbId = esbId;
	}
	public String getConLen() {
		return conLen;
	}
	public void setConLen(String conLen) {
		this.conLen = conLen;
	}
	public String getCallTime() {
		return callTime;
	}
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	public String getBefM() {
		return befM;
	}
	public void setBefM(String befM) {
		this.befM = befM;
	}
	public String getNowM() {
		return nowM;
	}
	public void setNowM(String nowM) {
		this.nowM = nowM;
	}
	public String getInvoGal() {
		return invoGal;
	}
	public void setInvoGal(String invoGal) {
		this.invoGal = invoGal;
	}
	public String getUnM() {
		return unM;
	}
	public void setUnM(String unM) {
		this.unM = unM;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "LogDto [start=" + start + ", esbId=" + esbId + ", conLen=" + conLen + ", callTime=" + callTime
				+ ", befM=" + befM + ", nowM=" + nowM + ", invoGal=" + invoGal + ", unM=" + unM + ", end=" + end + "]";
	}
	
	
}