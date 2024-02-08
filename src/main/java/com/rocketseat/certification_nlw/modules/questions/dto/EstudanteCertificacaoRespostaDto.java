package com.rocketseat.certification_nlw.modules.questions.dto;

import com.rocketseat.certification_nlw.modules.students.dto.PerguntasERespostasDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstudanteCertificacaoRespostaDto {

    private String email;
    private String tecnologia;
    private List<PerguntasERespostasDto> perguntasERespostasDto;
}
