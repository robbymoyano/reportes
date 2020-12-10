package com.falabella.reportes.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;

import com.falabella.reportes.domain.Informe;
import com.falabella.reportes.domain.Totales;

@Mapper
public interface ConsultasMapper {
	/**
	 * 
	 * @param fecha Fecha en formato yyyy-MM-dd
	 * @return
	 */
	@Select("select ors.num_order, ors.cod_status,ord.cod_pay, ors.num_folio, ors.num_seq, ors.cod_store, ors.terminal, " + 
			"date_format(ors.dt_date, '%Y-%m-%d %H:%i:%s') as dt_date from pos_co_ors ors, pos_co_ord ord " + 
			"where ors.ord_id = ord.id and ors.tag_fecha = #{fecha} and (ors.cod_status='PROCES' or ors.cod_status='NULA') " + 
			"order by num_seq asc ")
	public List<Informe> getInforme(String fecha);
	
	/**
	 * 
	 * @param fecha Fecha en formato yyyy-MM-dd
	 * @return
	 */
	@Select("select cod_pay, num_cnt, q_amount, cod_store, cod_ter from pos_co_pay where dt_date = #{fecha} order by cod_pay asc, cod_ter asc ")
	public List<Totales> getTotales(String fecha);
	
	
	@Insert("insert into pos_co_test (dt_date) values (#{fecha})")
	public void insertarFecha(Date fecha);
	

	/**
	 * 
	 * @param fecha en formato yyyy-MM-dd
	 * @return
	 */
	@Select("select txt_msg from pos_co_dcm where tag_fecha = #{fecha};")
	public Cursor<String> getLineasByFecha(String fecha);
	
}
