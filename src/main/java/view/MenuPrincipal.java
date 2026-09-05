package view;

import static javax.swing.JOptionPane.*;


public class MenuPrincipal {
    public void menu(){
        String [] item = {"plano", "medico", "paciente", "consulta", "finalizar"};
        String opcao;

        do {
            opcao = (String) showInputDialog(null, "Selecione uma opção", "==== Menu Principal ====",
                    INFORMATION_MESSAGE, null,
                    item, item[0]);
            switch (opcao.toLowerCase()){
                case "plano" -> new MenuPlano().menu();
                case "medico" -> new MenuMedico().menu();
                case "paciente" -> new MenuPaciente().menu();
                case "consulta" -> new MenuConsulta().menu();
            }
        }while (!opcao.toLowerCase().equals("finalizar"));

    }
}
