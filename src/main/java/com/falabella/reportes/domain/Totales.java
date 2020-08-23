package com.falabella.reportes.domain;

public class Totales {
	private String cod_pay;
	private int num_cnt;
	private long q_amount;
	private int cod_store;
	private int cod_ter;

	public String getCod_pay() {
		return cod_pay;
	}

	public void setCod_pay(String cod_pay) {
		this.cod_pay = cod_pay;
	}

	public int getNum_cnt() {
		return num_cnt;
	}

	public void setNum_cnt(int num_cnt) {
		this.num_cnt = num_cnt;
	}

	public long getQ_amount() {
		return q_amount;
	}

	public void setQ_amount(long q_amount) {
		this.q_amount = q_amount;
	}

	public int getCod_store() {
		return cod_store;
	}

	public void setCod_store(int cod_store) {
		this.cod_store = cod_store;
	}

	public int getCod_ter() {
		return cod_ter;
	}

	public void setCod_ter(int cod_ter) {
		this.cod_ter = cod_ter;
	}

	@Override
	public String toString() {
		return "Totales [cod_pay=" + cod_pay + ", num_cnt=" + num_cnt + ", q_amount=" + q_amount + ", cod_store="
				+ cod_store + ", cod_ter=" + cod_ter + "]";
	}

}
