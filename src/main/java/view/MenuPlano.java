package view;

import DAO.PlanoDAO;
import Model.Plano;

import java.util.List;

import static javax.swing.JOptionPane.*;

public class MenuPlano {


    public void menu(){
        String [] item = {"listar", "sair"};
        String opcao;
        do {
            opcao = (String) showInputDialog(null,
                    "Selecione uma opcao",
                    "==== MENU PLANOS ====",
                    INFORMATION_MESSAGE,
                    null,
                    item, item[0]);

            switch(opcao.toLowerCase()){
                case "listar" -> listar();
            }
        }
        while(!opcao.toLowerCase().equals("sair"));
    }

    private void listar() {
        List<Plano> lista = new PlanoDAO().listar();
        String aux = "";


        for (Plano e : lista){
            aux += e.getIdPlano() + " | ";
            aux += e.getNome() + " | ";
            aux += e.getTipo() + " | ";
            aux += e.getValorMensal() + "\n";
        }


        showMessageDialog(null, aux);
    }


}
