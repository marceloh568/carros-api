package com.example.carros.api.carros;

import com.example.carros.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public List<CarroDTO> getCarros(Pageable pageable) {
        return rep.findAll(pageable).stream().map(CarroDTO::create).collect(Collectors.toList());

//        List<CarroDTO> list = new ArrayList<>();
//        for(Carro c : carros){
//            list.add(new CarroDTO(c));
//        }
    }

//    public List<Carro> getCarrosFake(){
//        List<Carro> carros = new ArrayList<>();
//
//        carros.add(new Carro(1L, "Fusca"));
//        carros.add(new Carro(2L, "Brasilia"));
//        carros.add(new Carro(3L, "Chevette"));
//
//        return carros;
//    }

    public CarroDTO getCarrosById(Long id) {
        Optional<Carro> carro = rep.findById(id);
        return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }

    public List<CarroDTO> getCarrosByTipo(String tipo, Pageable pageable) {
        return rep.findByTipo(tipo, pageable).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO insert(Carro carro) {
        //Assert.isNull(carro.getId(), "Não foi possível inserar o registro");
        return CarroDTO.create(rep.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Carro> optional = rep.findById(id);
        if (optional.isPresent()) {
            Carro db = new Carro();
            db.setNome(optional.get().getNome());
            db.setTipo(optional.get().getTipo());
            db.setId(optional.get().getId());
            //Copiar as propriedades
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id " + db.getId());

            //Atualiza o carro
            rep.save(db);
            return CarroDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }

//        getCarrosById(id).map(db -> {
//            //Copiar as propriedades
//            db.setNome(carro.getNome());
//            db.setTipo(carro.getTipo());
//            System.out.println("Carro id " + db.getId());
//
//            //Atualiza o carro
//            rep.save(db);
//            return db;
//        }).orElseThrow(() -> new RuntimeException("Não foi possível atualizar o registro"));
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }
}
