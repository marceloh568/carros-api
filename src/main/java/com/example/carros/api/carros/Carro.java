package com.example.carros.api.carros;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipo;
    private String descricao;
    private String url_foto;
    private String url_video;
    private String latitude;
    private String longitude;

}
