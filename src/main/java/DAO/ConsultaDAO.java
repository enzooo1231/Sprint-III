package DAO;

import Model.Consulta;
import Model.Medico;
import Model.Paciente;
import factory.ConnectionFactory;

import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO implements GenericDAO<Consulta, Integer> {

    public void inserir(Consulta entidade){
        String sql = "INSERT INTO CONSULTA (ID_CONSULTA, ID_PACIENTE, ID_MEDICO, DATA_CONSULTA, HORARIO, STATUS) VALUES (SEQ_CONSULTA.NEXTVAL, ?, ?, ?, ?, ?)";

        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, entidade.getPaciente().getIdPaciente());
            ps.setInt(2, entidade.getMedico().getIdMedico());
            ps.setDate(3, Date.valueOf(entidade.getDataConsulta()));

            DateTimeFormatter mascaraHora = DateTimeFormatter.ofPattern("HH:mm");
            ps.setString(4, entidade.getHora().format(mascaraHora));

            ps.setString(5, entidade.getStatus());
            ps.execute();

        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Consulta> listar() {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM consulta";

        try (Connection connection = ConnectionFactory.obterConexao();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                Consulta consulta = new Consulta();
                Paciente paciente = new Paciente();
                Medico medico = new Medico();

                consulta.setIdConsulta(rs.getInt("ID_CONSULTA"));

                paciente.setIdPaciente(rs.getInt("ID_PACIENTE"));
                medico.setIdMedico(rs.getInt("ID_MEDICO"));

                consulta.setPaciente(paciente);
                consulta.setMedico(medico);

                consulta.setDataConsulta(
                        rs.getDate("DATA_CONSULTA").toLocalDate()
                );

                DateTimeFormatter mascaraHora = DateTimeFormatter.ofPattern("HH:mm");
                consulta.setHora(
                        LocalTime.parse(rs.getString("HORARIO"), mascaraHora)
                );

                consulta.setStatus(rs.getString("STATUS"));

                lista.add(consulta);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    public void atualizar(Consulta consulta){

        String sql = "UPDATE consulta SET status = ? WHERE id_consulta = ?";

        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, consulta.getStatus());
            ps.setInt(2, consulta.getIdConsulta());
            ps.execute();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void excluir(Integer id){

        String sql = "DELETE FROM consulta WHERE id_consulta = ?";

        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);
            ps.execute();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}