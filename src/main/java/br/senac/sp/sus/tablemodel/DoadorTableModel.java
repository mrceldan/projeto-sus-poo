package br.senac.sp.sus.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senac.sp.sus.model.BancoSangue;
import br.senac.sp.sus.model.Doador;

public class DoadorTableModel extends AbstractTableModel {
	private List<Doador> lista;
	private String[] cabecalho = { "Nome", "Gênero", "Banco", "Tatuado" };

	public DoadorTableModel(List<Doador> lista) {
		this.lista = lista;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return cabecalho.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Doador d = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return d.getNome();
		case 1:
			return d.getGenero();
		case 2:
			return d.getBanco();
		default:
			return d.isTatuado() ? "Sim" : "Não";
		}
	}

	@Override
	public String getColumnName(int column) {
		return cabecalho[column];
	}

	public void setLista(List<Doador> lista) {
		this.lista = lista;
	}

	public List<Doador> getLista() {
		return lista;
	}
}
