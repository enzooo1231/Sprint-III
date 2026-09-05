package view;

import DAO.MedicoDAO;
import DAO.PacienteDAO;
import Model.Medico;
import Model.Paciente;
import Model.Plano;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class MenuPaciente {
    public void menu(){
        String [] item = {"inserir", "listar", "atualizar", "excluir", "sair"};
        String opcao;

        do {
            opcao = (String) showInputDialog(null,
                    "Selecione uma opcao",
                    "==== MENU PACIENTE ====",
                    INFORMATION_MESSAGE,
                    null,
                    item, item[0]);

            switch(opcao.toLowerCase()){
                case "inserir" -> inserir();
                case "listar" -> listar();
                case "atualizar" -> atualizar();
                case "excluir" -> excluir();

            }
        }
        while(!opcao.toLowerCase().equals("sair"));
    }

    private void excluir() {

        int id = parseInt(showInputDialog("id"));

        Paciente paciente = new Paciente();
        paciente.setIdPaciente(id);

        new PacienteDAO().excluir(paciente.getIdPaciente());
        showMessageDialog(null, "Usuário deletado");
    }

    private void atualizar() {

        String telefone = showInputDialog("Telefone");
        int id =  parseInt(showInputDialog("id"));

        Paciente paciente = new Paciente();

        paciente.setTelefone(telefone);
        paciente.setIdPaciente(id);

        new PacienteDAO().atualizar(paciente);
        showMessageDialog(null, "Telefone atualizado");
    }


    private void inserir() {

        DateTimeFormatter mascara = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String nome = showInputDialog("Nome paciente");
        String cpf = showInputDialog("CPF");
        String data = showInputDialog("Data nascimento (dd/mm/aaaa)");
        String telefone = showInputDialog("Telefone");
        String email = showInputDialog("Email");
        int id = parseInt(showInputDialog("Id do plano (9 - Basico | 10 - Intermediário | 11 - Premium | 12 - Executivo)"));

        Plano plano = new Plano();
        plano.setIdPlano(id);

        Paciente paciente = new Paciente();

        paciente.setNome(nome);
        paciente.setCpf(cpf);
        paciente.setData(LocalDate.parse(data, mascara));
        paciente.setTelefone(telefone);
        paciente.setEmail(email);
        paciente.setPlano(plano);

        new PacienteDAO().inserir(paciente);

        showMessageDialog(null, "Paciente cadastrado");

    }

    private void listar() {

        List<Paciente> lista = new PacienteDAO().listar();

        String aux = "";

        for (Paciente e : lista){
            aux += "ID:Paciente "+ e.getIdPaciente() + " | \n";
            aux += "Nome: " + e.getNome() + " | \n";
            aux += "CPF:" + e.getCpf() + " | \n";
            aux += "Data nascimento: " + e.getData() + " | \n";
            aux += "Telefone: " + e.getTelefone() + " | \n";
            aux += "Email: " + e.getEmail() + " | \n";
            aux += "ID:Paciente " + e.getPlano().getIdPlano()+"\n";
        }
        showMessageDialog(null, aux);
    }

}
