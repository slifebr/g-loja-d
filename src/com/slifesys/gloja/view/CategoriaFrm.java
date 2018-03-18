package com.slifesys.gloja.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.slifesys.gloja.model.Categoria;
import com.slifesys.gloja.repository.CategoriaDao;
import com.slifesys.gloja.repository.jpa.CategoriaJPADao;
import com.slifesys.gloja.template.Formulario;
import com.solutionssoft.desktop.Mensagem;
import com.solutionssoft.desktop.SSBotao;
import com.solutionssoft.desktop.SSCampoTexto;
import com.solutionssoft.infraestrutura.util.Validacao;

public class CategoriaFrm extends Formulario {

	private static final long serialVersionUID = 7205030838123877422L;

	// Atributos
	private SSCampoTexto txtId = new SSCampoTexto();
	private SSCampoTexto txtDescr = new SSCampoTexto();

	private SSBotao btnSalvar = new SSBotao();
	private SSBotao btnSair = new SSBotao();

	private CategoriaDao dao = new CategoriaJPADao();
	private Categoria entidade;

	public CategoriaFrm(Categoria entidade) {
		this.entidade = entidade;
		if (entidade != null) {
			txtId.setText(entidade.getId().toString());
			preencher();
		}
		init();
	}

	private void init() {

		btnSair.setText("Sair");
		btnSalvar.setText("Salvar");

		// Listners = Comandos = Eventos
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvar();
			}
		});
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});

		txtId.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			};

			public void focusLost(FocusEvent e) {
				preencher();
			}
		});

		// heranca
		super.setTitulo("Categoria");
		super.setDescricao("Cadastro de Categorias");

		super.addBotaoRodape(btnSalvar);
		super.addBotaoRodape(btnSair);
		// IMPORTANTE
		JPanel panelConteudo = super.getConteudoGrid();
		panelConteudo.setLayout(new GridBagLayout());

		// Campo - ID
		GridBagConstraints gbcTxtId = new GridBagConstraints();
		gbcTxtId.fill = GridBagConstraints.HORIZONTAL;
		gbcTxtId.anchor = GridBagConstraints.NORTH;
		gbcTxtId.insets = new Insets(0, 5, 5, 5);
		gbcTxtId.gridx = 0;
		gbcTxtId.gridy = 0;
		panelConteudo.add(txtId, gbcTxtId);
		txtId.setColunas(5);
		txtId.setRotulo("Código");

		// Campo - Descr
		GridBagConstraints gbcTxtDescr = new GridBagConstraints();
		gbcTxtDescr.gridwidth = 2;
		gbcTxtDescr.fill = GridBagConstraints.HORIZONTAL;
		gbcTxtDescr.anchor = GridBagConstraints.NORTH;
		gbcTxtDescr.weightx = 2.0;
		gbcTxtDescr.weighty = 1.0;

		gbcTxtDescr.insets = new Insets(0, 0, 5, 5);
		gbcTxtDescr.gridx = 1;
		gbcTxtDescr.gridy = 0;
		txtDescr.setTamanhoMaximo(50);
		panelConteudo.add(txtDescr, gbcTxtDescr);
		txtDescr.setRotulo("Descrição");
		txtDescr.setColunas(40);
	}

	private void preencher() {
		try {
			if (!Validacao.nuloOuVazio(txtId.getText())) {
				Long pk = Long.valueOf(txtId.getText());
				entidade = dao.buscar(pk);
				if (entidade == null) {
					entidade = new Categoria();
				}
				txtDescr.setValue(entidade.getDescr());
			}

		} catch (NumberFormatException e) {
			txtId.setValue(null);
			Mensagem.erro("O valor informado não é um número válido");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void salvar() {
		try {
			entidade.setId(Long.valueOf(txtId.getText()));
			entidade.setDescr(txtDescr.getText());
			dao.alterar(entidade);
			Mensagem.informa("Categoria registrado com sucesso!!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void sair() {
		super.fechar();
	}
}
