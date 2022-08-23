package com.claro.gestionrecursosweb.compromisosfabrica.dto;

public class FiltrosDto {
	private String columnaUno;
	private String columnaDos;
	private String columnaTres;
	private String valorUno;
	private String valorDos;
	private String valorTres;
	private Integer codproyecto;
	private Integer codproveedor;
	private Integer codperfiltipo;
	private Integer codperfil;
	private Integer codestructuraorganizacional;
	private Integer codperfilnivel;
	private Integer diasFiltro;

	public Integer getDiasFiltro() {
		return diasFiltro;
	}

	public void setDiasFiltro(Integer diasFiltro) {
		this.diasFiltro = diasFiltro;
	}

	public String getColumnaUno() {
		return columnaUno;
	}
	public void setColumnaUno(String columnaUno) {
		this.columnaUno = columnaUno;
	}
	public String getColumnaDos() {
		return columnaDos;
	}
	public void setColumnaDos(String columnaDos) {
		this.columnaDos = columnaDos;
	}
	public String getColumnaTres() {
		return columnaTres;
	}
	public void setColumnaTres(String columnaTres) {
		this.columnaTres = columnaTres;
	}
	public String getValorUno() {
		return valorUno;
	}
	public void setValorUno(String valorUno) {
		this.valorUno = valorUno;
	}
	public String getValorDos() {
		return valorDos;
	}
	public void setValorDos(String valorDos) {
		this.valorDos = valorDos;
	}
	public String getValorTres() {
		return valorTres;
	}
	public void setValorTres(String valorTres) {
		this.valorTres = valorTres;
	}
	public Integer getCodproyecto() {
		return codproyecto;
	}
	public void setCodproyecto(Integer codproyecto) {
		this.codproyecto = codproyecto;
	}
	public Integer getCodproveedor() {
		return codproveedor;
	}
	public void setCodproveedor(Integer codproveedor) {
		this.codproveedor = codproveedor;
	}
	public Integer getCodperfiltipo() {
		return codperfiltipo;
	}
	public void setCodperfiltipo(Integer codperfiltipo) {
		this.codperfiltipo = codperfiltipo;
	}
	public Integer getCodperfil() {
		return codperfil;
	}
	public void setCodperfil(Integer codperfil) {
		this.codperfil = codperfil;
	}
	public Integer getCodestructuraorganizacional() {
		return codestructuraorganizacional;
	}
	public void setCodestructuraorganizacional(Integer codestructuraorganizacional) {
		this.codestructuraorganizacional = codestructuraorganizacional;
	}
	public Integer getCodperfilnivel() {
		return codperfilnivel;
	}
	public void setCodperfilnivel(Integer codperfilnivel) {
		this.codperfilnivel = codperfilnivel;
	}
}
