package demo.service;

import demo.entity.Acticle;
import demo.repository.ActicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActicleService {

    @Autowired
    private ActicleRepository acticleRepository;

    public List<Acticle> findAll() {
        List<Acticle> acticles = new ArrayList<>();
        acticleRepository.findAll().forEach(x -> {
            acticles.add(x);
        });
        return acticles;
    }
}
