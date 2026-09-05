package DAO;

import Model.Plano;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO implements GenericDAO<Plano,Integer>{


    @Override
    public List<Plano> listar() {

        List<Plano> lista = new ArrayList<>();
        String sql = "SELECT * FROM PLANO";

        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();){

            while(rs.next()){
                Plano plano = new Plano();
                plano.setIdPlano(rs.getInt("ID_PLANO"));
                plano.setNome(rs.getString("NOME"));
                plano.setTipo(rs.getString("TIPO"));
                plano.setValorMensal(rs.getDouble("VALOR_MENSAL"));
                lista.add(plano);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return lista;
    }
}
