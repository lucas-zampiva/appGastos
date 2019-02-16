package tainersoft.raiz.models;

import java.util.List;

public interface IContaRepository
{
    public Conta Add(Conta conta) throws Exception;

    public Conta update(int id, Conta conta) throws Exception;

    public Conta remove(Conta conta) throws Exception;

    public List<Conta> getAll() throws Exception;

	public Conta getById(int id) throws Exception;
}