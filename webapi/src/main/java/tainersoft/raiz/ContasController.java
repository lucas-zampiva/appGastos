package tainersoft.raiz;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tainersoft.raiz.models.Conta;
import tainersoft.raiz.models.IContaRepository;

@RestController
@RequestMapping("/contas")
public class ContasController {
    @Autowired // Isso irá carregar o objeto repository automaticamente
    private IContaRepository repository;

    @RequestMapping("")
    List<Conta> getAll() throws Exception {
        return repository.getAll();
    }

    @RequestMapping(value="/{id}")
    Conta getById(@PathVariable int id) throws Exception {
        return repository.getById(id);
    
    }
    
    @RequestMapping(value="/{id}" , method = RequestMethod.DELETE)
    Conta remove(@PathVariable int id) throws Exception {
        Conta conta = repository.getById(id);
        repository.remove(conta);
        return conta;
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    Conta adicionar (@RequestBody Conta conta) throws Exception {
        repository.Add(conta);
        return conta;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    Conta atualizar (@PathVariable int id, @RequestBody Conta conta) throws Exception {
        repository.update(id, conta);
        return conta;
    }
}
