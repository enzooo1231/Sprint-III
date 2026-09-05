package Model;

public class Plano {

    private Integer idPlano;
    private String nome;
    private String tipo;
    private double valorMensal;


    public Integer getidPlano() {
        return idPlano;
    }

    public void setidPlano(Integer idPlano) {
        idPlano = idPlano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    @Override
    public String toString() {
        return  "ID: " + getidPlano() + " | " + "Nome: " + getNome() +
                " | " + "Tipo: " + getTipo() + " | " + "Valor Mensal: " + getValorMensal();
    }
}
