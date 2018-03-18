package com.slifesys.gloja.template;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.solutionssoft.desktop.SSBotao;

public abstract class FormularioCRUD extends Formulario {

	protected SSBotao cmdSalvar = new SSBotao();
	protected SSBotao cmdSair = new SSBotao();

	protected void init () {
		
		cmdSair.setText("Sair");
		cmdSalvar.setText("Salvar");
	
		// Listners = Comandos = Eventos
		cmdSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvar();
			}
		});
		cmdSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
	

	}
	
	protected abstract void preencher();
	protected abstract void salvar() ;
	protected abstract void sair();
}
