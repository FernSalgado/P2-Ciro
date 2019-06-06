package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaConexao {
    static String INSERT_PET = "insert into Pet (nome,sexo,porte,tipo,castrado) values(?,?,?,?,?)";
    static String DELETE_PET = "delete from Pet where id = ?";
    static String SELECT_PET = "select * from Pet";
    static String PROCURA_PET = "select * from Pet where nome = ?";
    
    public static void main(String[] args)throws SQLException{
        //String de conexão do banco, user e senha
    lista();
    }
    public static void deleta(long id)throws SQLException{
        try(Connection connection = new Database().getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement st = connection.prepareStatement(DELETE_PET)){
                st.setLong(1, id);
                st.execute();
                connection.commit();
            }catch(Exception ex){
                ex.printStackTrace();
                connection.rollback();
            }
        }
        
        
    }
    
    public static void lista() throws SQLException{
        //String de conexão, user e senha
        Connection connection = new Database().getConnection();
        //Prepara um statement para processar comandos SQL
        Statement statement = connection.createStatement();    
        //Define se tem ou não dados na tabela
        boolean resultado = statement.execute(SELECT_PET);
        //Pega os dados do comando
        ResultSet rs = statement.getResultSet();
        while(rs.next()){
        String id = rs.getString("id");
        String nome = rs.getString("nome");
        String sexo = rs.getString("sexo");
        String porte = rs.getString("porte");
        String tipo = rs.getString("tipo");
        String castrado = rs.getString("castrado");
        System.out.println("Id:"+id+" Nome:"+nome+" Sexo:"+sexo+" Porte:"+porte+" Tipo:"+tipo+" Castrado:" + castrado);
        }
        rs.close();
        statement.close();
        connection.close();
    }
    
    public static void procura(String nome) throws SQLException{
        Connection connection = new Database().getConnection();
        PreparedStatement stmt = connection.prepareStatement(PROCURA_PET);
        stmt.setString(1,nome);
        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        while(rs.next()){
        String id = rs.getString("id");
        String nm = rs.getString("nome");
        String sexo = rs.getString("sexo");
        String porte = rs.getString("porte");
        String tipo = rs.getString("tipo");
        String castrado = rs.getString("castrado");
        System.out.println("Id:"+id+" Nome:"+nm+" Sexo:"+sexo+" Porte:"+porte+" Tipo:"+tipo+" Castrado:" + castrado);
        }
        rs.close();
        stmt.close();
        connection.close();
    }
    
    public static void adiciona(String nome, String sexo, String porte, String tipo, String castrado) throws SQLException{
            Connection connection = new Database().getConnection();
            //Prepara um statement para processar comandos SQL
            PreparedStatement stmt = connection.prepareStatement(INSERT_PET);
            stmt.setString(1, nome);
            stmt.setString(2, sexo);
            stmt.setString(3, porte);
            stmt.setString(4, tipo);
            stmt.setString(5, castrado);
            stmt.execute();

            stmt.close();
            connection.close();
    }
                    //String de conexão, user e senha
                   //String de conexão, user e senha

}
