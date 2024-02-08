package com.rocketseat.certification_nlw.modules.questions.controllers;

import com.rocketseat.certification_nlw.modules.questions.dto.AlternativaResultadoDto;
import com.rocketseat.certification_nlw.modules.questions.dto.PerguntaResultadoDto;
import com.rocketseat.certification_nlw.modules.questions.entities.AlternativasEntidade;
import com.rocketseat.certification_nlw.modules.questions.entities.PerguntasEntidade;
import com.rocketseat.certification_nlw.modules.questions.repositories.PerguntaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

    @Autowired
    private PerguntaRepositorio perguntaRepositorio;

    @GetMapping("/tecnologia/{tecnologia}")
    public List<PerguntaResultadoDto> findByTechnology(@PathVariable String tecnologia) {
        var result = this.perguntaRepositorio.findByTecnologia(tecnologia);

        var toMap = result.stream().map(pergunta -> mapPerguntaDto(pergunta)).collect(Collectors.toList());
        return toMap;
    }

    static PerguntaResultadoDto mapPerguntaDto(PerguntasEntidade pergunta) {
        var perguntaResultadoDto = PerguntaResultadoDto.builder()
                .id(pergunta.getId())
                .tecnologia(pergunta.getTecnologia())
                .descricao(pergunta.getDescricao()).build();

        List<AlternativaResultadoDto> alternativaResultadoDto = pergunta.getAlternativasEntidade().stream().map(alternativasEntidade -> mapAlternativaDto(alternativasEntidade)).collect(Collectors.toList());
        perguntaResultadoDto.setAlternativaResultadoDto(alternativaResultadoDto);

        return perguntaResultadoDto;
    }

    static AlternativaResultadoDto mapAlternativaDto(AlternativasEntidade alternativa) {
        return AlternativaResultadoDto.builder()
                .id(alternativa.getId())
                .descricao(alternativa.getDescricao()).build();
    }
}
