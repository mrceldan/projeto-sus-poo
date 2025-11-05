package br.senac.sp.sus.view;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import br.senac.sp.sus.dao.BancoSangueJpaDao;
import br.senac.sp.sus.dao.DoadorDao;
import br.senac.sp.sus.dao.FactoryEm;
import br.senac.sp.sus.model.BancoSangue;
import br.senac.sp.sus.model.Doador;
import br.senac.sp.sus.model.Genero;
import br.senac.sp.sus.model.TipoSanguineo;
import br.senac.sp.sus.tablemodel.DoadorTableModel;

public class JanelaDoador extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField tfId;
	private JLabel lbId;
	private JTextField tfNome;
	private JTextField tfPeso;
	private JTextField tfAltura;
	private JTable tbDoadores;
	private JButton btSalvar;
	private JButton btExcluir;
	private JButton btLimpar;
	private JTextArea taEndereco;
	private JScrollPane spDoadores;
	private JComboBox<BancoSangue> cbBanco;
	private JFormattedTextField tfNascimento;
	private JComboBox<TipoSanguineo> cbSangue;
	private JComboBox<Genero> cbGenero;
	private JFormattedTextField tfCpf;
	private JLabel lbPeso;
	private JLabel lbTipoSang;
	private JLabel lbNascimento;
	private JLabel lbTatuado;
	private JCheckBox chTatuado;
	private JLabel lbBanco;
	private JLabel lbEndereco;
	private JLabel lbNome;
	private JLabel lbGenero;
	private JLabel lbCpf;
	private JLabel lbAltura;
	private JFormattedTextField tfUltima;
	private JLabel lbUltima;
	private MaskFormatter mskData;
	private MaskFormatter mskCpf;
	private DoadorDao daoDoador;
	private BancoSangueJpaDao daoBanco;
	private List<Doador> lista;
	private Doador doador;
	private DoadorTableModel modelDoador;

	/**
	 * Create the frame.
	 */
	public JanelaDoador() {
		inicializarComponentes();
		definirEventos();
	}

	private void inicializarComponentes() {
		try {
			daoDoador = new DoadorDao(FactoryEm.getEntityManager());
			lista = daoDoador.listar();
			daoBanco = new BancoSangueJpaDao(FactoryEm.getEntityManager());
			mskData = new MaskFormatter("##/##/####");
			mskCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException | SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}

		getContentPane().setLayout(null);

		lbId = new JLabel("ID:");
		lbId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbId.setBounds(10, 11, 26, 14);
		getContentPane().add(lbId);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(46, 9, 47, 20);
		getContentPane().add(tfId);
		tfId.setColumns(10);

		lbNome = new JLabel("Nome:");
		lbNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbNome.setBounds(103, 11, 47, 14);
		getContentPane().add(lbNome);

		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(158, 9, 283, 20);
		getContentPane().add(tfNome);

		lbGenero = new JLabel("Gênero:");
		lbGenero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbGenero.setBounds(10, 43, 55, 14);
		getContentPane().add(lbGenero);

		cbGenero = new JComboBox<>(Genero.values());
		cbGenero.setBounds(63, 40, 150, 22);
		cbGenero.setSelectedIndex(-1);
		getContentPane().add(cbGenero);

		lbCpf = new JLabel("CPF:");
		lbCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbCpf.setBounds(223, 44, 35, 14);
		getContentPane().add(lbCpf);

		tfCpf = new JFormattedTextField(mskCpf);
		tfCpf.setHorizontalAlignment(SwingConstants.CENTER);
		tfCpf.setBounds(273, 41, 169, 20);
		getContentPane().add(tfCpf);

		lbTipoSang = new JLabel("Sangue:");
		lbTipoSang.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbTipoSang.setBounds(10, 76, 55, 14);
		getContentPane().add(lbTipoSang);

		cbSangue = new JComboBox<>(TipoSanguineo.values());
		cbSangue.setBounds(63, 73, 150, 22);
		cbSangue.setSelectedIndex(-1);
		getContentPane().add(cbSangue);

		lbPeso = new JLabel("Peso:");
		lbPeso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbPeso.setBounds(223, 77, 35, 14);
		getContentPane().add(lbPeso);

		tfPeso = new JTextField();
		tfPeso.setHorizontalAlignment(SwingConstants.CENTER);
		tfPeso.setColumns(10);
		tfPeso.setBounds(273, 74, 47, 20);
		getContentPane().add(tfPeso);

		lbAltura = new JLabel("Altura:");
		lbAltura.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbAltura.setBounds(339, 76, 47, 14);
		getContentPane().add(lbAltura);

		tfAltura = new JTextField();
		tfAltura.setHorizontalAlignment(SwingConstants.CENTER);
		tfAltura.setColumns(10);
		tfAltura.setBounds(396, 74, 47, 20);
		getContentPane().add(tfAltura);

		lbNascimento = new JLabel("Nascimento:");
		lbNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbNascimento.setBounds(10, 110, 72, 14);
		getContentPane().add(lbNascimento);

		tfNascimento = new JFormattedTextField(mskData);
		tfNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		tfNascimento.setBounds(89, 107, 117, 20);
		getContentPane().add(tfNascimento);

		lbUltima = new JLabel("Última Doação:");
		lbUltima.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbUltima.setBounds(223, 110, 91, 14);
		getContentPane().add(lbUltima);

		tfUltima = new JFormattedTextField(mskData);
		tfUltima.setHorizontalAlignment(SwingConstants.CENTER);
		tfUltima.setBounds(324, 108, 117, 20);
		getContentPane().add(tfUltima);

		lbTatuado = new JLabel("Tatuado:");
		lbTatuado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbTatuado.setBounds(10, 145, 62, 14);
		getContentPane().add(lbTatuado);

		chTatuado = new JCheckBox("");
		chTatuado.setBounds(74, 142, 35, 23);
		getContentPane().add(chTatuado);

		lbBanco = new JLabel("Banco de Sangue:");
		lbBanco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbBanco.setBounds(115, 145, 117, 14);
		getContentPane().add(lbBanco);

		try {
			cbBanco = new JComboBox<>(daoBanco.listar().toArray(new BancoSangue[0]));
			cbBanco.setSelectedIndex(-1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		cbBanco.setBounds(223, 142, 218, 22);
		getContentPane().add(cbBanco);

		lbEndereco = new JLabel("Endereço:");
		lbEndereco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbEndereco.setBounds(10, 177, 83, 14);
		getContentPane().add(lbEndereco);

		taEndereco = new JTextArea();
		taEndereco.setBounds(10, 202, 431, 58);
		getContentPane().add(taEndereco);

		btSalvar = new JButton("Salvar");
		btSalvar.setBounds(10, 275, 89, 23);
		getContentPane().add(btSalvar);

		btExcluir = new JButton("Excluir");
		btExcluir.setBounds(103, 275, 89, 23);
		getContentPane().add(btExcluir);

		btLimpar = new JButton("Limpar");
		btLimpar.setBounds(202, 275, 89, 23);
		getContentPane().add(btLimpar);

		spDoadores = new JScrollPane();
		spDoadores.setBounds(451, 11, 438, 283);
		getContentPane().add(spDoadores);

		modelDoador = new DoadorTableModel(lista);

		tbDoadores = new JTable(modelDoador);
		tbDoadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spDoadores.setViewportView(tbDoadores);

		setBounds(10, 10, 915, 345);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setTitle("Cadastro de Doador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void definirEventos() {
		tfPeso.addKeyListener(keyTf);
		tfAltura.addKeyListener(keyTf);

		btSalvar.addActionListener(e -> {
			if (tfNome.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Informe o nome", "Informe", JOptionPane.INFORMATION_MESSAGE);
				tfNome.requestFocus();
			} else if (tfCpf.getValue() == null) {
				JOptionPane.showMessageDialog(this, "Informe o cpf", "Informe", JOptionPane.INFORMATION_MESSAGE);
				tfCpf.requestFocus();
			} else if (cbGenero.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Informe o genero", "Informe", JOptionPane.INFORMATION_MESSAGE);
				cbGenero.requestFocus();
			} else if (cbSangue.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Informe o tipo sanguíneo", "Informe",
						JOptionPane.INFORMATION_MESSAGE);
				cbSangue.requestFocus();
			} else if (cbBanco.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Informe o banco de sangue", "Informe",
						JOptionPane.INFORMATION_MESSAGE);
				cbBanco.requestFocus();
			} else {
				if (doador == null) {
					doador = new Doador();
				}
				doador.setNome(tfNome.getText());
				doador.setAltura(Double.parseDouble(tfAltura.getText()));
				doador.setPeso(Double.parseDouble(tfPeso.getText()));
				doador.setCpf(tfCpf.getValue().toString());
				doador.setGenero((Genero) cbGenero.getSelectedItem());
				doador.setTipoSanguineo((TipoSanguineo) cbSangue.getSelectedItem());
				doador.setTatuado(chTatuado.isSelected());
				doador.setBanco((BancoSangue) cbBanco.getSelectedItem());
				doador.setEndereco(taEndereco.getText());
				SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Calendar dtNasc = Calendar.getInstance();
					dtNasc.setTime(fmt.parse(tfNascimento.getValue().toString()));
					Calendar dtDoacao = Calendar.getInstance();
					dtDoacao.setTime(fmt.parse(tfUltima.getValue().toString()));
					doador.setNascimento(dtNasc);
					doador.setUltimaDoacao(dtDoacao);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}

				try {
					if (doador.getId() == 0) {
						daoDoador.inserir(doador);
					} else {
						daoDoador.alterar(doador);
					}
					lista = daoDoador.listar();
					modelDoador.setLista(lista);
					modelDoador.fireTableDataChanged();
					limpar();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btLimpar.addActionListener(e -> {
			limpar();
		});

		tbDoadores.getSelectionModel().addListSelectionListener(e -> {
			int linha = tbDoadores.getSelectedRow();
			if (linha >= 0) {
				doador = lista.get(linha);
				tfNome.setText(doador.getNome());
				tfId.setText(doador.getId() + "");
				tfCpf.setValue(doador.getCpf());
				SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
				tfNascimento.setText(fmt.format(doador.getNascimento().getTime()));
				tfUltima.setText(fmt.format(doador.getUltimaDoacao().getTime()));
				cbGenero.setSelectedItem(doador.getGenero());
				cbSangue.setSelectedItem(doador.getTipoSanguineo());
				cbBanco.setSelectedItem(doador.getBanco());
				chTatuado.setSelected(doador.isTatuado());
				taEndereco.setText(doador.getEndereco());
				tfAltura.setText(doador.getAltura() + "");
				tfPeso.setText(doador.getPeso() + "");
			}
		});

		btExcluir.addActionListener(e -> {
			if (doador != null) {
				if (JOptionPane.showConfirmDialog(this, "Deseja excluir o doador ID: " + doador.getId(), "Confirmar",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					try {
						daoDoador.excluir(doador.getId());
						lista = daoDoador.listar();
						modelDoador.setLista(lista);
						modelDoador.fireTableDataChanged();
						limpar();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}

				}
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um doador para excluí-lo", "Erro",
						JOptionPane.WARNING_MESSAGE);
			}
		});
	}

	private void limpar() {
		doador = null;
		tfNome.setText(null);
		tfCpf.setValue(null);
		tfNascimento.setValue(null);
		tfUltima.setValue(null);
		tfId.setText(null);
		tfPeso.setText(null);
		tfAltura.setText(null);
		cbBanco.setSelectedIndex(-1);
		cbGenero.setSelectedIndex(-1);
		cbSangue.setSelectedIndex(-1);
		chTatuado.setSelected(false);
		taEndereco.setText(null);
		tbDoadores.clearSelection();
	}

	private KeyAdapter keyTf = new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {
			JTextField tf = (JTextField) e.getSource();
			if (e.getKeyChar() == '.') {
				if (tf.getText().contains(".")) {
					e.consume();
				}
			} else if (!Character.isDigit(e.getKeyChar())) {
				e.consume();
			}
		}
	};
}
