package tainersoft.raiz.models;

public class Lancamento {
    private Integer id;
    private String descricao;
    private Float valor;
    private Integer id_conta;

    /**
     * @return the id_conta
     */
    public Integer getId_conta() {
        return id_conta;
    }

    /**
     * @param f the id_conta to set
     */
    public void setId_conta(Integer f) {
        this.id_conta = f;
    }
  


    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the valor
     */
    public Float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Float valor) {
        this.valor = valor;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}