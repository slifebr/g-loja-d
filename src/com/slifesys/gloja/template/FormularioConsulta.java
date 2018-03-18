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


	protected abstract void sair();

	protected abstract void incluir();

	protected abstract void alterar();

	protected abstract void showForm();

	protected abstract void buscar();

	protected abstract void excluir();

}
