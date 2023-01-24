package com.mifmodaintima.domain.entity;

import java.time.Instant;
import java.util.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

@Data
@Document(collection = "clientes")
public class Cliente {

	@Id
	private String _id;
	private String nome;
	@Indexed(unique = true)
	private String telefone;
	@Indexed(unique = true)
	private String email;

	private Date dataDeNascimento;

	@DateTimeFormat
	private Date cadastradoEm;
	@DateTimeFormat
	private Date AtualizadoEm;
	@JsonIgnore
	private String senha;

}
