package tainersoft.raiz.models;

import java.util.List;

public interface ILancamentoRepository
{
    public Lancamento Add(Lancamento lancamento) throws Exception;

    public Lancamento update(Lancamento lancamento) throws Exception;

    public Lancamento remove(Lancamento lancamento) throws Exception;

    public List<Lancamento> getAll() throws Exception;

	public Lancamento getById(int id) throws Exception;
}