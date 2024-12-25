package com.example.configInicialEcomponentes.services.animes;

import java.util.List;


import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;


import com.example.configInicialEcomponentes.database.Anime;
import com.example.configInicialEcomponentes.database.AnimeRepository;
import com.example.configInicialEcomponentes.mapper.animeMapper;
import com.example.exeptions.BadRequestExeption;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class animeServise {

    final AnimeRepository animeRepository;
    
    
     public List<Anime> listarAnime(){
        return animeRepository.findAll();
    }

    public Anime listarId(Long ids){
        return animeRepository.findById(ids)
        .orElseThrow(()-> new BadRequestExeption("bad request exeption"));
    }

    public List<Anime> listarName(String name){
        return animeRepository.findByname(name);
    }

    //ESSA ANOTAÇÃO IMPEDE QUE OCORRA ERRO NO BANCO DE DADOS
    //CASO OCORRA ALGUM ERRO NO BANCO DE DADOS ELE FAZ UM ROLLBACK
    //isso quer dizer que ele desfaz as alterações feitas no banco de dados
    //oque evita que o banco de dados fique em um estado inconsistente.
    //é uportante notar que ela, por padrão, não funciona em exeções do tipo checked
    //para que ela funcione em exeções do tipo checked é necessario usar a anotação @Transactional(rollbackFor = {Exeption.class})

    @Transactional
    public Anime createAnime(animeDTO anime){

        //EXEMPLO USANDO UM BUILDER
        // Anime newAnime = Anime
        // .builder()
        // .id(anime.id())
        // .name(anime.name())
        // .build();

        //EXEMPLO USANDO MAPSTRUCT
        return animeRepository.save(animeMapper.intanse.toAnime(anime));

        
    }

    public void deletAnime(long id) {
        animeRepository.delete(listarId(id));
    }

    public void replaceAnime(animeDTO anime) {
       Anime animeAtt = listarId(anime.id());
       animeAtt.setName(anime.name());
       animeRepository.save(animeAtt); 
    }

}
