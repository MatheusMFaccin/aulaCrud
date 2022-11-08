package Model;

import ConnectionFactory.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncionarioDAO {
    private Connection con;
    
    //CREATE - Isere pessoas no banco de dados
    //READ - Recupar informações de funcionários
    public void inserirFuncionario(Funcionario funcionario){
        String inserir = "insert into  Funcionarios (nome,email,cargo)-"+ "values(?,?,?)";
        con = new ConnectionFactory().getConnection();
        try{
            PreparedStatement pst = con.prepareStatement(inserir);
            pst.setString(1,funcionario.getNome());
            pst.setString(2,funcionario.getEmail()); 
            pst.setString(3,funcionario.getCargo());
            pst.execute();
            con.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erro tentar inserir dados no banco");
        }
    }
    public ArrayList<Funcionario> listarFuncionarios(){
        //criar lista de funcionario
        ArrayList<Funcionario> listaFuncionario = new ArrayList();
        //declarar a query utilizada para retonar dados de funcioanrios
        String read = "select * from funcionarios;";
        try {
            //abrir a conexão
            con = new ConnectionFactory().getConnection();
            //preparar a query para execução no banco de dados
            PreparedStatement pst = con.prepareStatement(read);
            //executar a query e armazena em um RS
            ResultSet rs = pst.executeQuery();
            //passando os valores contidos no RS para a lista de func.
            while(rs.next()){
                int id =  Integer.parseInt(rs.getString(1));
                String nome = rs.getString(2);
                String email = rs.getString(3);
                String cargo = rs.getString(4);
                //adicionar um novo funcionario a Lista
                listaFuncionario.add(new Funcionario(id, nome, email,cargo));
            }
            con.close();
            return listaFuncionario;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } 
    }
    //UPDATE - Alterar informações de um funcionario
    //DELETE - Remover um registro do banco
    
    public void editarFuncionarios(Funcionario funcionario){
        String update = "update funcionarios set nome = ?, email = ? , cargo = ? "+"where id = ?;";
        con = new ConnectionFactory().getConnection();
        try{
            PreparedStatement pst = con.prepareStatement(update);
            pst.setString(1, funcionario.getNome());
            pst.setString(2, funcionario.getEmail());
            pst.setString(3, funcionario.getCargo());
            pst.setString(4, funcionario.getId()+"");
            pst.execute();
            con.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erro ao fazer update no banco");
        }
    }
    public void deletarFuncionario(Funcionario funcionarios){
        String deletar = "delete from funcionarios where id = ?";
        con = new ConnectionFactory().getConnection();
        try{
            PreparedStatement pst = con.prepareStatement(deletar);
            pst.setString(1, funcionarios.getId()+"");
            pst.execute();
            con.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erro ao deletar funcionario");
        }
    }
    
}
