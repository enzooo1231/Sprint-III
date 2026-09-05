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
        String sql = "INSERT INTO paciente (id_paciente, nome, cpf, data_nascimento, telefone, email, id_plano) values(SEQ_PACIENTE.NEXTVAL, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)){


            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getCpf());
            ps.setDate(3, Date.valueOf(entidade.getData()));
            ps.setString(4, entidade.getTelefone());
            ps.setString(5, entidade.getEmail());
            ps.setInt(6,entidade.getPlano().getIdPlano());
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
                plano.setIdPlano(rs.getInt("ID_PLANO"));
                paciente.setPlano(plano);
                lista.add(paciente);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    public void atualizar(Paciente paciente){

        String sql = "UPDATE paciente SET telefone = ? WHERE id_paciente = ?";
        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, paciente.getTelefone());
            ps.setInt(2, paciente.getIdPaciente());
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
