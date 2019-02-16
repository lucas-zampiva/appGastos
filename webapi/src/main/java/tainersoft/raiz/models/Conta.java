package tainersoft.raiz.models;

/**
 * Conta
 */
public class Conta {

    private int id;
    private String descricao;
    private Float saldo;
    
    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the saldo
     */
    public Float getSaldo() {
        return saldo;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
}