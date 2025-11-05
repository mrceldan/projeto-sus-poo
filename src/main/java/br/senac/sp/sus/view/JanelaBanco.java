package br.senac.sp.sus.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import br.senac.sp.sus.dao.BancoSangueDao;
import br.senac.sp.sus.dao.BancoSangueJpaDao;
import br.senac.sp.sus.dao.ConnectionFactory;
import br.senac.sp.sus.dao.FactoryEm;
import br.senac.sp.sus.dao.InterfaceDao;
import br.senac.sp.sus.model.BancoSangue;
import br.senac.sp.sus.model.Doador;
import br.senac.sp.sus.tablemodel.BancoDeSangueModel;

public class JanelaBanco extends JFrame implements ActionListener {
	private JLabel lbId, lbNome, lbTelefone, lbEmail, lbCnpj, lbEndereco;
	private JTextField tfId, tfNome, tfTelefone, tfEmail;
	private JFormattedTextField tfCnpj;
	private MaskFormatter mskCnpj;
	private JTextArea taEndereco;
	private JButton btSalvar, btExcluir, btLimpar;
	private BancoSangue banco;
	private InterfaceDao<BancoSangue> dao;
	private JTable tbBancos;
	private JScrollPane spBancos;
	private List<BancoSangue> lista;
	private BancoDeSangueModel modelBanco;
	

	public JanelaBanco() {
		// inicializar a dao		
		try {
			dao = new BancoSangueJpaDao(FactoryEm.getEntityManager());
			lista = dao.listar();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro na conexão", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		inicializarComponentes();
		definirEventos();
	}

	private void inicializarComponentes() {
		// lbId
		lbId = new JLabel("ID:");
		lbId.setBounds(10, 10, 30, 30);
		lbId.setForeground(Color.blue);

		// tfId
		tfId = new JTextField();
		tfId.setBounds(70, 10, 50, 30);
		tfId.setHorizontalAlignment(SwingConstants.CENTER);
		tfId.setEditable(false);

		// lbCnpj
		lbCnpj = new JLabel("CNPJ:");
		lbCnpj.setBounds(140, 10, 50, 30);
		lbCnpj.setForeground(Color.blue);

		// mskCnpj
		try {
			mskCnpj = new MaskFormatter("##.###.###/####-##");
			mskCnpj.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// tfCnpj
		tfCnpj = new JFormattedTextField(mskCnpj);
		tfCnpj.setBounds(200, 10, 120, 30);
		tfCnpj.setHorizontalAlignment(SwingConstants.CENTER);

		// lbNome
		lbNome = new JLabel("Nome:");
		lbNome.setBounds(10, 50, 50, 30);
		lbNome.setForeground(Color.blue);

		// tfNome
		tfNome = new JTextField();
		tfNome.setBounds(70, 50, 300, 30);

		// lbEmail
		lbEmail = new JLabel("E-mail:");
		lbEmail.setBounds(10, 90, 50, 30);
		lbEmail.setForeground(Color.blue);

		// tfEmail
		tfEmail = new JTextField();
		tfEmail.setBounds(70, 90, 300, 30);

		// lbTelefone
		lbTelefone = new JLabel("Tel.:");
		lbTelefone.setBounds(10, 130, 50, 30);
		lbTelefone.setForeground(Color.blue);

		// tfTelefone
		tfTelefone = new JTextField();
		tfTelefone.setBounds(70, 130, 100, 30);
		tfTelefone.setHorizontalAlignment(SwingConstants.CENTER);

		// lbEndereco
		lbEndereco = new JLabel("End.:");
		lbEndereco.setBounds(10, 170, 50, 30);
		lbEndereco.setForeground(Color.blue);

		// taEndereco
		taEndereco = new JTextArea();
		taEndereco.setBounds(70, 170, 300, 60);
		taEndereco.setBorder(BorderFactory.createLineBorder(Color.gray));

		// btSalvar
		btSalvar = new JButton("Salvar");
		btSalvar.setBounds(10, 240, 80, 30);

		// btExcluir
		btExcluir = new JButton("Excluir");
		btExcluir.setBounds(100, 240, 80, 30);

		// btLimpar
		btLimpar = new JButton("Limpar");
		btLimpar.setBounds(190, 240, 80, 30);

		// modelBanco
		modelBanco = new BancoDeSangueModel(lista);

		// tbBancos
		tbBancos = new JTable(modelBanco);
		tbBancos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// spBancos
		spBancos = new JScrollPane(tbBancos);
		spBancos.setBounds(390, 10, 480, 260);

		// adicionando
		getContentPane().setLayout(null);
		getContentPane().add(lbId);
		getContentPane().add(tfId);
		getContentPane().add(lbCnpj);
		getContentPane().add(lbNome);
		getContentPane().add(tfNome);
		getContentPane().add(lbEmail);
		getContentPane().add(tfEmail);
		getContentPane().add(lbTelefone);
		getContentPane().add(tfTelefone);
		getContentPane().add(lbEndereco);
		getContentPane().add(tfCnpj);
		getContentPane().add(taEndereco);
		getContentPane().add(btSalvar);
		getContentPane().add(btExcluir);
		getContentPane().add(btLimpar);
		getContentPane().add(spBancos);

		// frame
		setBounds(100, 100, 900, 320);
		setTitle("Cadastro de Banco de Sangue");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.white);
		setVisible(true);
	}

	private void definirEventos() {
		// btSalvar
		btSalvar.addActionListener(this);
		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou no botão - 2");
			}
		});
		btSalvar.addActionListener(e -> {
			// validação
			if (tfNome.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Informe o nome", "Erro", JOptionPane.WARNING_MESSAGE);
				tfNome.requestFocus();
			} else if (tfCnpj.getValue() == null) {
				JOptionPane.showMessageDialog(this, "Informe o CNPJ", "Erro", JOptionPane.WARNING_MESSAGE);
				tfCnpj.requestFocus();
			} else if (tfEmail.getText().isEmpty() && tfTelefone.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Informe o telefone ou o e-mail", "Erro",
						JOptionPane.WARNING_MESSAGE);
				tfEmail.requestFocus();
			} else {
				// cria o objeto BancoSangue
				if (banco == null) {
					banco = new BancoSangue();
				}
				banco.setNome(tfNome.getText());
				banco.setCnpj(tfCnpj.getValue().toString());
				banco.setEmail(tfEmail.getText());
				banco.setTelefone(tfTelefone.getText());
				banco.setEndereco(taEndereco.getText());
				try {
					if (banco.getId() == 0) {
						dao.inserir(banco);
					} else {
						dao.alterar(banco);
					}
					lista = dao.listar();
					modelBanco.setLista(lista);
					modelBanco.fireTableDataChanged();
					limpar();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Erro ao inserir", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});

		// btLimpar
		btLimpar.addActionListener(e -> {
			limpar();
		});

		// tfTelefone
		tfTelefone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()) || tfTelefone.getText().length() == 11) {
					e.consume();
				}
			}
		});

		// tbBanco
		tbBancos.getSelectionModel().addListSelectionListener(e -> {
			int linha = tbBancos.getSelectedRow();
			if (linha >= 0) {
				banco = lista.get(linha);
				tfId.setText(banco.getId() + "");
				tfNome.setText(banco.getNome());
				tfEmail.setText(banco.getEmail());
				tfTelefone.setText(banco.getTelefone());
				taEndereco.setText(banco.getEndereco());
				tfCnpj.setValue(banco.getCnpj());
			}
		});
		
		// btExcluir
		btExcluir.addActionListener(e -> {
			if(banco == null) {
				JOptionPane.showMessageDialog(this, "Selecione um item na lista para excluí-lo", "Selecione",
						JOptionPane.INFORMATION_MESSAGE);
			}else {
				if(JOptionPane.showConfirmDialog
						(this, "Deseja excluir o item ID: "+banco.getId(), 
								"Confirmar", JOptionPane.YES_NO_OPTION, 
								JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {					
					try {
						dao.excluir(banco.getId());
						lista = dao.listar();
						modelBanco.setLista(lista);
						modelBanco.fireTableDataChanged();
						limpar();
					} catch (SQLException e1) {	
						JOptionPane.showMessageDialog(this, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Clicou no botão");
	}

	private void limpar() {
		// "resetando o formulário"
		tfId.setText(null);
		tfNome.setText(null);
		tfEmail.setText(null);
		tfTelefone.setText(null);
		taEndereco.setText(null);
		tfCnpj.setValue(null);
		tfNome.requestFocus();
		banco = null;
		tbBancos.clearSelection();
	}

}
