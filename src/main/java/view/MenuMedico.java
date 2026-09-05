package view;

import DAO.MedicoDAO;
import Model.Medico;

import java.util.List;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.*;

public class MenuMedico {

    public void menu(){
        String [] item = {"inserir", "listar", "atualizar", "excluir", "sair"};
        String opcao;

        do {
            opcao = (String) showInputDialog(null,
                    "Selecione uma opcao",
                    "==== MENU MEDICO ====",
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

        Medico medico = new Medico();
        medico.setIdMedico(id);

        new MedicoDAO().excluir(medico.getIdMedico());
        showMessageDialog(null, "Usuário deletado");
    }

    private void atualizar() {

        String telefone = showInputDialog("Telefone");
        int id =  parseInt(showInputDialog("id"));

        Medico medico = new Medico();

        medico.setTelefone(telefone);
        medico.setIdMedico(id);

        new MedicoDAO().atualizar(medico);
        showMessageDialog(null, "Telefone atualizado");
    }


    private void inserir() {
        String nome = showInputDialog("Nome do médico");
        String crm = showInputDialog("CRM do médico");
        String especialidade = showInputDialog("Especialidade");
        String telefone = showInputDialog("Telefone");
        String email = showInputDialog("Email");

        Medico medico = new Medico();

        medico.setNome(nome);
        medico.setCrm(crm);
        medico.setEspecialidade(especialidade);
        medico.setTelefone(telefone);
        medico.setEmail(email);

        new MedicoDAO().inserir(medico);

        showMessageDialog(null, "Médico cadastrado com sucesso!");

    }

    private void listar() {

        List<Medico> lista = new MedicoDAO().listar();

        String aux = "";

        for (Medico e : lista){
            aux += "ID:Medico " + e.getIdMedico() + " | ";
            aux += "Nome: " + e.getNome() + " | ";
            aux += "CRM: " +e.getCrm() + " | ";
            aux += "Especialidade: " + e.getEspecialidade() + " | ";
            aux += "Telefone: " + e.getTelefone() + " | ";
            aux += "Email: " + e.getEmail();
        }
        showMessageDialog(null, aux);
    }
}
