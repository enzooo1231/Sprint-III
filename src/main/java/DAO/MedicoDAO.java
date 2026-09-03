package DAO;

import Model.Medico;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO implements GenericDAO<Medico, Integer> {


    public void inserir(Medico entidade){
        String sql = "INSERT INTO medico (id_medico, nome, crm, especialidade, telefone, email) values(?, ?, ?, ?, ?, ?)";

        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1,entidade.getIdMedico());
            ps.setString(2, entidade.getNome());
            ps.setString(3, entidade.getCrm());
            ps.setString(4, entidade.getEspecialidade());
            ps.setString(5, entidade.getTelefone());
            ps.setString(6, entidade.getEmail());
            ps.execute();

        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public void atualizar(Medico medico){

        String sql = "UPDATE medico SET telefone = ? WHERE id_Medico = ?";
        try(Connection connection = ConnectionFactory.obterConexao();
        PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, medico.getTelefone());
            ps.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void excluir(Integer id){

        String sql = "DELETE FROM medico WHERE ID_MEDICO = ?";
        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Medico> listar() {

        List<Medico> listar = new ArrayList<>();
        String sql = "SELECT * FROM medico";

        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();){

            while (rs.next()){
                Medico medico = new Medico();
                medico.setIdMedico(rs.getInt("ID_MEDICO"));
                medico.setNome(rs.getString("NOME"));
                medico.setCrm(rs.getString("CRM"));
                medico.setCrm(rs.getString("ESPECIALIDADE"));
                medico.setCrm(rs.getString("TELEFONE"));
                medico.setCrm(rs.getString("EMAIL"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return listar;
    }
}
