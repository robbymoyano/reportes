package com.falabella.reportes.domain;

public class Informe {

	private String num_order;
	private String cod_status;
	private String cod_pay;
	private long num_folio;
	private long num_seq;
	private String cod_store;
	private int terminal;
	private String dt_date;
	
	public String getNum_order() {
		return num_order;
	}
	public void setNum_order(String num_order) {
		this.num_order = num_order;
	}
	public String getCod_status() {
		return cod_status;
	}
	public void setCod_status(String cod_status) {
		this.cod_status = cod_status;
	}
	public String getCod_pay() {
		return cod_pay;
	}
	public void setCod_pay(String cod_pay) {
		this.cod_pay = cod_pay;
	}
	public long getNum_folio() {
		return num_folio;
	}
	public void setNum_folio(long num_folio) {
		this.num_folio = num_folio;
	}
	public long getNum_seq() {
		return num_seq;
	}
	public void setNum_seq(long num_seq) {
		this.num_seq = num_seq;
	}
	public String getCod_store() {
		return cod_store;
	}
	public void setCod_store(String cod_store) {
		this.cod_store = cod_store;
	}
	public int getTerminal() {
		return terminal;
	}
	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}
	public String getDt_date() {
		return dt_date;
	}
	public void setDt_date(String dt_date) {
		this.dt_date = dt_date;
	}
	@Override
	public String toString() {
		return "Informe [num_order=" + num_order + ", cod_status=" + cod_status + ", cod_pay=" + cod_pay
				+ ", num_folio=" + num_folio + ", num_seq=" + num_seq + ", cod_store=" + cod_store + ", terminal="
				+ terminal + ", dt_date=" + dt_date + "]";
	}
	
	
}
