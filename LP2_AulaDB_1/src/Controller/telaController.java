/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.CadastroFuncionario;
import View.ListarFuncionarios;

/**
 *
 * @author laboratorio
 */
public class telaController {
    private CadastroFuncionario cadastro;
    private ListarFuncionarios listar;
    public telaController(CadastroFuncionario cadastro,ListarFuncionarios listar){
        this.cadastro = cadastro;
        this.listar = listar;
    }
    public void initController(){
        listar.getButton_novo().addActionListener(e-> exibirCadastro());
        cadastro.getButton_voltar().addActionListener(e-> exibirListar());
    }
    public void exibirCadastro(){
        cadastro.setVisible(true);
        listar.setVisible(false);
    }
    public void exibirListar(){
        listar.setVisible(true);
        cadastro.setVisible(false);
        
    }
}
