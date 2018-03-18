package com.slifesys.gloja.template;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.slifesys.gloja.Gloja;
import com.solutionssoft.desktop.Mensagem;
import com.solutionssoft.desktop.SSBotao;
import com.solutionssoft.desktop.SSCabecalho;
import com.solutionssoft.desktop.SSConteudo;
import com.solutionssoft.desktop.SSRodape;

public class Formulario extends JPanel {

	private SSCabecalho cabecalho = new SSCabecalho();
	//private SSConteudo conteudo = new SSConteudo();
	private JPanel conteudo = new JPanel();
	private SSRodape rodape = new SSRodape();

	private Gloja app;

	public void setApp(Gloja app) {
		this.app = app;
	}

	public Gloja getApp() {
		return app;
	}
    
	private JDesktopPane getDesktop() {
		return getApp().getDesktop();
	}

	public void setTitulo(String titulo) {
		this.cabecalho.setTitulo(titulo);
	}

	public void setDescricao(String descricao) {
		this.cabecalho.setDescricao(descricao);
	}

	public void addBotaoRodape(SSBotao botao) {
		this.rodape.add(botao);
	}

	public void botoesNaEsquerda() {
		this.rodape.setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	public JPanel getConteudoTabela() {
		this.conteudo.setLayout(new BorderLayout());
		return conteudo;
	}

	public JPanel getConteudoGrid() {
		return conteudo;
	}

	public Formulario() {
		init();
	}

	private void init() {
		this.setLayout(new BorderLayout());

		this.setTitulo("Informe um titulo");
		this.setDescricao("Informe uma descrição");

		this.add(cabecalho, BorderLayout.NORTH);
		this.conteudo.setLayout(new GridBagLayout());
		this.add(conteudo, BorderLayout.CENTER);
		this.add(rodape, BorderLayout.SOUTH);

	}

	public void show() {
		this.show(this);
	}

	public void show(Formulario frm) {
		if (frm != this) {
			frm.setApp(this.getApp());
		}
		JInternalFrame internal = new JInternalFrame("GLoja - Gestão Comercial");
		internal.setContentPane(frm);
		internal.pack();
		centralizar(internal);
		try {
			internal.setSelected(true);

		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		getDesktop().add(internal);
		getDesktop().moveToFront(internal);
	}

	public void remover() {
		JInternalFrame iframe = (JInternalFrame) SwingUtilities.getAncestorOfClass(JInternalFrame.class, this);
		getDesktop().remove(iframe);
		getDesktop().repaint();
	}

	public void fechar() {
		boolean resposta = Mensagem.pergunta("Deseja cancelar esta operação");
		if (resposta) {
			remover();
		}
	}

	private void centralizar(JInternalFrame componente) {
		Dimension dim = app.getSize(); // Toolkit.getDefaultToolkit().getScreenSize();
		int x = dim.width / 2 - componente.getSize().width / 2;
		int y = dim.height / 2 - componente.getSize().height / 2;
		y = y - 60;
		componente.setLocation(x, y);
		componente.setVisible(true);
	}
}