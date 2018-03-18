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

import com.slifesys.gloja.model.Tamanho;
import com.slifesys.gloja.repository.TamanhoDao;
import com.slifesys.gloja.template.FormularioConsulta;
import com.solutionssoft.desktop.Mensagem;
import com.solutionssoft.desktop.PosicaoRotulo;
import com.solutionssoft.desktop.SSBotao;
import com.solutionssoft.desktop.SSCampoTexto;
import com.solutionssoft.desktop.SSGrade;
import com.solutionssoft.infraestrutura.util.Validacao;

public class TamanhoFrmConsulta extends FormularioConsulta {
	
	// conteudo - topo - filtro
	private SSCampoTexto txtFiltroNome = new SSCampoTexto();
	private SSBotao cmdBuscar = new SSBotao();
	
	private SSGrade grid = new SSGrade();
	private JScrollPane scroll = new JScrollPane();

	// DAOs
	private TamanhoDao dao = new TamanhoJPADao();

	public TamanhoFrmConsulta() {
		init();
	}


	protected void init() {
		super.setTitulo("Tamanhos/Medidas");
		super.setDescricao("Consulta de Tamanhos/Medidas");
		
		cmdBuscar.setText("Buscar");
		cmdBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		
		// Adiciona os botões no formulario
		cmdIncluir.setText("Novo");
		cmdAlterar.setText("Alterar");
		cmdExcluir.setText("Excluir");
		cmdFechar.setText("Fechar");

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

		cmdExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});

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
		grid.getModeloTabela().addColumn("Sigla");
		grid.getModeloTabela().addColumn("Descrição");

		grid.getModeloColuna().getColumn(0).setPreferredWidth(70);
		grid.getModeloColuna().getColumn(1).setPreferredWidth(70);
		grid.getModeloColuna().getColumn(2).setPreferredWidth(280);

		grid.getModeloColuna().setCampo(0, "id");
		grid.getModeloColuna().setCampo(1, "sigla");
		grid.getModeloColuna().setCampo(2, "descr");
		txtFiltroNome.setRotulo("Descrição");
		txtFiltroNome.setRotuloPosicao(PosicaoRotulo.ESQUERDA);


	}
	
	
	@Override
	protected void sair() {
		super.remove(); // remove o frame do container
		
	}

	@Override
	protected void incluir() {
		showCadastro(null);
		
	}

	@Override
	protected void alterar() {
		Tamanho entidade = (Tamanho) grid.getLinhaSelecionada();
		showCadastro(entidade);
	}

	@Override
	protected void showForm() {

	}
	private void showForm(Tamanho tamanho) {
		//Formulario frm = new Tamanhofrm(tamanho);
		//this.show(frm);
		;
	}

	@Override
	protected void buscar() {
		List<Tamanho> lista = new ArrayList<Tamanho>();
		try {
			String nome = txtFiltroNome.getText();
			if (Validacao.vazio(nome)) {
				lista = dao.listar();

			} else {
				lista = dao.findAllByDescr(nome);

			}
			grid.setValue(lista);
		} catch (Exception e) {
			Mensagem.erro(e.getMessage());
		}
		
	}

	@Override
	protected void excluir() {
		Tamanho entidade = (Tamanho) grid.getLinhaSelecionada();
		try {
			if (entidade != null) {
				if (Mensagem.confirma("Deseja excluir a Tamanho/Medida(" + entidade.getDescr()+")?")) {				
					dao.excluir(entidade);
					Mensagem.avisa(null, "Registro excluido com sucesso!");
					buscar();
				}
			} else {
				Mensagem.avisa(null, "Selecione um Registro para exclusão");
			}
		} catch (Exception e) {

			Mensagem.erro(e.getMessage());
		}
	}
		
	private void showCadastro(Tamanho tamanho) {
		//Formulario frm = new TamanhoFrm(tamanho);
		//this.show(frm);
	}

}
