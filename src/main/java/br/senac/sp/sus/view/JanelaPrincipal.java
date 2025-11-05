package br.senac.sp.sus.view;

import javax.swing.JFrame;

public class JanelaPrincipal extends JFrame{

	public JanelaPrincipal() {		
		inicializarComponentes();
		definirEventos();
	}
	
	private void inicializarComponentes() {
		
		// janela
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(false);
		setVisible(true);		
	}
	
	private void definirEventos() {
		
	}
}
