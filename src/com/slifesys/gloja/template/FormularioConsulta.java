package com.slifesys.gloja.template;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.solutionssoft.desktop.SSBotao;

public abstract class FormularioConsulta extends Formulario {
	// rodape
	protected SSBotao cmdIncluir = new SSBotao();
	protected SSBotao cmdAlterar = new SSBotao();
	protected SSBotao cmdExcluir = new SSBotao();
	protected SSBotao cmdFechar = new SSBotao();

	protected void init() {
		super.botoesNaEsquerda();

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
	}

	protected abstract void sair();

	protected abstract void incluir();

	protected abstract void alterar();

	protected abstract void showForm();

	protected abstract void buscar();

	protected abstract void excluir();

}
