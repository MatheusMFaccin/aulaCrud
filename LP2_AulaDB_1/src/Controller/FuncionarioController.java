/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Funcionario;
import Model.FuncionarioDAO;
import View.CadastroFuncionario;
import View.ListarFuncionarios;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author laboratorio
 */
public class FuncionarioController {
    private ListarFuncionarios lista;
    private FuncionarioDAO dao = new FuncionarioDAO();
    private CadastroFuncionario cadastro;
    public FuncionarioController(ListarFuncionarios lista,CadastroFuncionario cadastro){
        this.lista = lista;
        this.cadastro = cadastro;
    }
    public void initController(){
        lista.getListar_button().addActionListener(e-> listarFuncionarios());
        lista.getButton_deletar().addActionListener(e-> deletarFuncionarios());
        cadastro.getButton_cadastro().addActionListener(e->inserirFuncionario());
        
    }
    
    
    private void listarFuncionarios(){
        
        ArrayList<Funcionario> listaFuncionarios = dao.listarFuncionarios();
        DefaultTableModel model = ((DefaultTableModel) lista.getjTable1().getModel());
        
        for(int i= 0; i<listaFuncionarios.size();i++){
            model.addRow(new String[] {listaFuncionarios.get(i).getId()+"",
            listaFuncionarios.get(i).getNome(),
            listaFuncionarios.get(i).getEmail(),
            listaFuncionarios.get(i).getCargo()});
        }
    }
    private void deletarFuncionarios(){
        JTable tabela = lista.getjTable1();
        int linha = tabela.getSelectedRow();
        System.out.println("linha: "+linha);
        if(linha != -1){
            int id = Integer.parseInt(tabela.getValueAt(linha, 0)+"");
            String nome = tabela.getValueAt(linha, 1)+"";
            String email = tabela.getValueAt(linha, 2)+"";
            String cargo = tabela.getValueAt(linha, 3)+"";
            Funcionario funcionario = new Funcionario(id,nome,email,cargo);
            int confirmDialog = 
                    JOptionPane.showConfirmDialog(null, "voce deseja mesmo deletar o funcionario:"+toString());
            if(confirmDialog == 0){
               dao.deletarFuncionario(funcionario); 
               listarFuncionarios();
            }
            
        }else{
            JOptionPane.showMessageDialog(null,"selecione o funcionario para ser deletado");
        }
    }
    private void inserirFuncionario(){
        String nome = cadastro.getInput_nome().getText();
        String email = cadastro.getInput_email().getText();
        String cargo = cadastro.getInput_cargo().getText();
        Funcionario funcionario = new Funcionario(nome,email,cargo);        
        dao.inserirFuncionario(funcionario);
        JOptionPane.showMessageDialog(null, "dados salvos com sucesso");
        cadastro.limparCampos();
    }
    
        
    
}
