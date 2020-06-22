package com.app.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.app.enums.DayWeek;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class HorarioAtencion {

	@Id
	@GeneratedValue
	@Column(name = "horarioId")
	private int id;
	
	@Column(name = "Days")
	@Enumerated(EnumType.STRING)
	private DayWeek dia;
	
	private String apertura;
	private String cierre;
	
}
