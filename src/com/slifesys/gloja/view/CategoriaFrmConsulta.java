package com.slifesys.gloja.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.slifesys.gloja.model.Categoria;
import com.slifesys.gloja.repository.CategoriaDao;
import com.slifesys.gloja.repository.jpa.CategoriaJPADao;
import com.slifesys.gloja.template.Formulario;
import com.solutionssoft.desktop.Mensagem;
import com.solutionssoft.desktop.PosicaoRotulo;
import com.solutionssoft.desktop.SSBotao;
import com.solutionssoft.desktop.SSCampoTexto;
import com.solutionssoft.desktop.SSGrade;
import com.solutionssoft.infraestrutura.util.Validacao;

public class CategoriaFrmConsulta extends Formulario {


		// rodape
		private SSBotao cmdIncluir = new SSBotao();
		private SSBotao cmdAlterar = new SSBotao();
		private SSBotao cmdExcluir = new SSBotao();
		private SSBotao cmdFechar = new SSBotao();
		// conteudo - topo - filtro
		private SSCampoTexto txtFiltroNome = new SSCampoTexto();
		private SSBotao cmdBuscar = new SSBotao();
		private SSGrade grid = new SSGrade();
		private JScrollPane scroll = new JScrollPane();
		// DAOs
		private CategoriaDao dao = new CategoriaJPADao();

		public CategoriaFrmConsulta() {
			init();
		}
		private void init() {
			super.setTitulo("Categoria");
			super.setDescricao("Consulta de Categoria");
			super.botoesNaEsquerda();
			super.addBotaoRodape(cmdIncluir);
			super.addBotaoRodape(cmdAlterar);
			super.addBotaoRodape(cmdExcluir);
			super.addBotaoRodape(cmdFechar);
			// implementando o conteudo do formulario
			JPanel conteudo = super.getConteudoTabela();

			// usando o painel de conteudo
			JPanel painelFiltro = new JPanel();
			conteudo.add(painelFiltro, BorderLayout.NORTH);
			scroll.getViewport().add(grid, null);

			conteudo.add(scroll, BorderLayout.CENTER);

			painelFiltro.setLayout(new GridBagLayout());
			GridBagConstraints gbcNome = new GridBagConstraints();
			gbcNome.weightx = 1.0;
			gbcNome.insets = new Insets(5, 5, 5, 5);
			gbcNome.fill = GridBagConstraints.HORIZONTAL;
			gbcNome.gridx = 0;
			gbcNome.gridy = 0;
			painelFiltro.add(txtFiltroNome, gbcNome);

			GridBagConstraints gbcBuscar = new GridBagConstraints();
			gbcBuscar.insets = new Insets(0, 0, 0, 5);
			gbcBuscar.gridx = 1;
			gbcBuscar.gridy = 0;
			painelFiltro.add(cmdBuscar, gbcBuscar);

			// campos da tabela
			grid.getModeloTabela().addColumn("Código");
			grid.getModeloTabela().addColumn("Descrição");

			grid.getModeloColuna().getColumn(0).setPreferredWidth(70);
			grid.getModeloColuna().getColumn(1).setPreferredWidth(280);

			grid.getModeloColuna().setCampo(0, "id");
			grid.getModeloColuna().setCampo(1, "descr");
			txtFiltroNome.setRotulo("Descrição");
			txtFiltroNome.setRotuloPosicao(PosicaoRotulo.ESQUERDA);

			cmdIncluir.setText("Novo");
			cmdAlterar.setText("Alterar");
			cmdExcluir.setText("Excluir");
			cmdFechar.setText("Fechar");
			cmdBuscar.setText("Buscar");

			cmdBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscar();
				}
			});

			cmdFechar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sair();
				}
			});
			cmdIncluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					incluir();
				}
			});
			cmdAlterar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					alterar();
				}
			});
		}

		private void sair() {
			super.remover();
			//super.fechar(); //SE QUISER PERGUTAR ANTES
		}

		private void incluir() {
			showCadastro(null);
		}

		private void alterar() {
			Categoria entidade= (Categoria) grid.getLinhaSelecionada();
			showCadastro(entidade);
		}

		private void showCadastro(Categoria categoria) {
			Formulario frm = new CategoriaFrm(categoria);
			this.show(frm);
		}

		private void buscar() {
			List<Categoria> lista = new ArrayList<Categoria>();
			try {
				String nome = txtFiltroNome.getText();
				if (Validacao.vazio(nome)) {
					lista = dao.listar();

				} else {
					lista = dao.listar(nome);

				}
				grid.setValue(lista);
			} catch (Exception e) {
				Mensagem.erro(e.getMessage());
			}

		}
	}