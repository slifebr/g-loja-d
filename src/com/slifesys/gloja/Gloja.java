package com.slifesys.gloja;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.slifesys.gloja.template.Formulario;
import com.slifesys.gloja.view.CategoriaFrmConsulta;
import com.slifesys.gloja.view.TamanhoFrmConsulta;

public class Gloja extends JFrame {

	private static final long serialVersionUID = 1L;

	private JDesktopPane desktop;

	/**
	 * Create the application.
	 */
	public Gloja() {
		initialize();

		montaMenu();

		desktop = new JDesktopPane();
		desktop.setBackground(Color.LIGHT_GRAY);
		setContentPane(desktop);

	}

	private void montaMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);

		// ------------Cadastro: Categoria ---------------------------------
		JMenuItem mntmCategoria = new JMenuItem("Categoria");
		mntmCategoria.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showWindow(new CategoriaFrmConsulta());

			}
		});
		mnCadastros.add(mntmCategoria);

		// ------------Cadastro: Marca--------------------------------------
		JMenuItem mntmMarca = new JMenuItem("Marca");
		mnCadastros.add(mntmMarca);

		// ------------Cadastro: Tamanho -----------------------------------
		JMenuItem mntmTamanho = new JMenuItem("Tamanho");
		mntmTamanho.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showWindow(new TamanhoFrmConsulta());
			}
		});
		mnCadastros.add(mntmTamanho);

		// ------------Cadastro: Classificação ------------------------------
		JMenuItem mntmClassificacao = new JMenuItem("Classificação");
		mnCadastros.add(mntmClassificacao);

	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 640);
	}

	public JDesktopPane getDesktop() {
		return desktop;
	}

	private void showWindow(Formulario formulario) {
		formulario.setApp(this);
		formulario.show();
	}

	// ----------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------
	// Método MAIN
	// ----------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*
		 * Gloja gloja = new Gloja(); gloja.setVisible(true);
		 * 
		 * //gloja.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 */

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gloja window = new Gloja();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
