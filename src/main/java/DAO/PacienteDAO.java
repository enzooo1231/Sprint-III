package DAO;

import Model.Medico;
import Model.Paciente;
import Model.Plano;
import factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO implements GenericDAO <Paciente, Integer> {



    public void inserir(Paciente entidade){
        String sql = "INSERT INTO paciente (id_paciente, nome, cpf, data_nascimento, telefone, email, id_plano) values(?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1,entidade.getIdPaciente());
            ps.setString(2, entidade.getNome());
            ps.setString(3, entidade.getCpf());
            ps.setDate(4, Date.valueOf(entidade.getData()));
            ps.setString(5, entidade.getTelefone());
            ps.setString(6, entidade.getEmail());
            ps.setInt(7,entidade.getPlano().getidPlano());
            ps.execute();

        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public List<Paciente> listar() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente";

        try (Connection connection = ConnectionFactory.obterConexao();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                Paciente paciente = new Paciente();
                Plano plano = new Plano();
                paciente.setIdPaciente(rs.getInt("ID_PACIENTE"));
                paciente.setNome(rs.getString("NOME"));
                paciente.setCpf(rs.getString("CPF"));
                paciente.setData(rs.getDate("DATA_NASCIMENTO").toLocalDate());
                paciente.setTelefone(rs.getString("TELEFONE"));
                paciente.setEmail(rs.getString("EMAIL"));
                paciente.setPlano(plano);
                lista.add(paciente);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    public void atualizar(Paciente paciente){

        String sql = "UPDATE medico SET telefone = ? WHERE id_paciente = ?";
        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, paciente.getTelefone());
            ps.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void excluir(Integer id){

        String sql = "DELETE FROM paciente WHERE id_paciente = ?";
        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }


}
