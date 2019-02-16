package tainersoft.raiz.models;

/**
 * Transeferencia
 */
public class Transferencia {

    private Lancamento deConta;
    private Lancamento paraConta;

    /**
     * @return the deConta
     */
    public Lancamento getDeConta() {
        return deConta;
    }
    /**
     * @param deConta the deConta to set
     */
    public void setDeConta(Lancamento deConta) {
        this.deConta = deConta;
    }
    /**
     * @return the paraConta
     */
    public Lancamento getParaConta() {
        return paraConta;
    }
    /**
     * @param paraConta the paraConta to set
     */
    public void setParaConta(Lancamento paraConta) {
        this.paraConta = paraConta;
    }

}