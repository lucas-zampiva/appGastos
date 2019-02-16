package tainersoft.raiz;

import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tainersoft.raiz.models.Conta;
import tainersoft.raiz.models.IContaRepository;
import tainersoft.raiz.models.ILancamentoRepository;
import tainersoft.raiz.models.Lancamento;
import tainersoft.raiz.models.Transferencia;

@RestController
@RequestMapping("/lancamentos")
public class LancamentosController {
    
    @Autowired // Isso irá carregar o objeto repository automaticamente
    private ILancamentoRepository repository;

    @Autowired
    private IContaRepository repConta;

    @RequestMapping("")
    List<Lancamento> getAll() throws Exception {
        return repository.getAll();
    }

    @RequestMapping("/{id}")
    Lancamento getLancamento(@PathVariable int id) throws Exception {
        return repository.getById(id);
    }
    @RequestMapping(value="/{id}" , method = RequestMethod.DELETE)
    Lancamento remove(@PathVariable int id) throws Exception {
        Lancamento lancamento = repository.getById(id);
        repository.remove(lancamento);
        return lancamento;
    }

    @RequestMapping(value = "", method= RequestMethod.POST)
    Lancamento adicionarLancamento(@RequestBody Lancamento lancamento) throws Exception { 
        repository.Add(lancamento);
        Conta conta =repConta.getById(lancamento.getId_conta());
        repConta.update(lancamento.getId_conta(), conta);
        Float saldoAtual = conta.getSaldo();
        conta.setSaldo(saldoAtual-(-lancamento.getValor()));
        repConta.update(lancamento.getId_conta(), conta);      
        return lancamento;
    }
    @RequestMapping(value="/{id}" , method = RequestMethod.PUT)
    Lancamento atualizar (@RequestBody Lancamento lancamento, @PathVariable int id ) throws Exception {
        
        Conta conta =repConta.getById(repository.getById(id).getId_conta());
        Float saldoAtual = conta.getSaldo();
        conta.setSaldo(saldoAtual - repository.getById(id).getValor());
        repConta.update(conta.getId(), conta);

        lancamento.setId(id);
        repository.update(lancamento);
        Conta conta2 =repConta.getById(lancamento.getId_conta());
        repConta.update(lancamento.getId_conta(), conta2);
        Float saldoAtual2 = conta2.getSaldo();
        conta2.setSaldo(saldoAtual2-(-lancamento.getValor()));
        repConta.update(lancamento.getId_conta(), conta2);      
        
        return lancamento;
    }
    @RequestMapping(value = "/trans", method= RequestMethod.POST)
    Transferencia  adicionarTransferencia(@RequestBody Transferencia transferencia) throws Exception {
        adicionarLancamento(transferencia.getDeConta());
        transferencia.getParaConta().setValor(transferencia.getParaConta().getValor()*-1);
        adicionarLancamento(transferencia.getParaConta());        
        return transferencia;
    }
}
