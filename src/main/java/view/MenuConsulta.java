package view;

import DAO.ConsultaDAO;
import Model.Consulta;
import Model.Medico;
import Model.Paciente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.*;

public class MenuConsulta {

    public void menu() {

        String[] item = {"inserir", "listar", "atualizar", "excluir", "sair"};
        String opcao;

        do {
            opcao = (String) showInputDialog(
                    null,
                    "Selecione uma opcao",
                    "==== MENU CONSULTA ====",
                    INFORMATION_MESSAGE,
                    null,
                    item,
                    item[0]
            );

            switch (opcao.toLowerCase()) {

                case "inserir" -> inserir();
                case "listar" -> listar();
                case "atualizar" -> atualizar();
                case "excluir" -> excluir();

            }

        } while (!opcao.toLowerCase().equals("sair"));
    }


    private void inserir() {

        DateTimeFormatter mascara = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int idPaciente = parseInt(showInputDialog("Id do paciente"));
        int idMedico = parseInt(showInputDialog("Id do médico"));

        String data = showInputDialog("Data da consulta (dd/mm/aaaa)");
        String horario = showInputDialog("Horário (HH:mm)");
        String status = showInputDialog("Status");

        Paciente paciente = new Paciente();
        paciente.setIdPaciente(idPaciente);

        Medico medico = new Medico();
        medico.setIdMedico(idMedico);

        Consulta consulta = new Consulta();

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setDataConsulta(LocalDate.parse(data, mascara));
        consulta.setHora(LocalTime.parse(horario));
        consulta.setStatus(status);

        new ConsultaDAO().inserir(consulta);

        showMessageDialog(null, "Consulta cadastrada");

    }


    private void listar() {

        List<Consulta> lista = new ConsultaDAO().listar();

        String aux = "";

        for (Consulta e : lista) {

            aux += "ID:Consulta " + e.getIdConsulta() + " | \n";
            aux += "ID:Paciente " + e.getPaciente().getIdPaciente() + " | \n";
            aux += "ID:Medico " +e.getMedico().getIdMedico() + " | \n";
            aux += "Data: "+e.getDataConsulta() + " | \n";
            aux += "Hora "+ e.getHora() + " | \n";
            aux += "Status "+ e.getStatus() + "\n\n";

        }

        showMessageDialog(null, aux);
    }


    private void atualizar() {

        String status = showInputDialog("Novo status");
        int id = parseInt(showInputDialog("Id da consulta"));

        Consulta consulta = new Consulta();

        consulta.setStatus(status);
        consulta.setIdConsulta(id);

        new ConsultaDAO().atualizar(consulta);

        showMessageDialog(null, "Status atualizado");

    }


    private void excluir() {

        int id = parseInt(showInputDialog("Id da consulta"));

        Consulta consulta = new Consulta();
        consulta.setIdConsulta(id);

        new ConsultaDAO().excluir(consulta.getIdConsulta());

        showMessageDialog(null, "Consulta deletada");

    }

}
