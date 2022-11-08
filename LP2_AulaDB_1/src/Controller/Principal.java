package Controller;

import Model.FuncionarioDAO;
import Model.Funcionario;
import View.CadastroFuncionario;
import View.ListarFuncionarios;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        ListarFuncionarios lista = new ListarFuncionarios();
        CadastroFuncionario cadastro = new CadastroFuncionario();
        FuncionarioController fc = new FuncionarioController(lista,cadastro);
        telaController tc = new telaController(cadastro,lista);
        fc.initController();
        tc.initController();
        lista.setVisible(true);
        
    }
}
