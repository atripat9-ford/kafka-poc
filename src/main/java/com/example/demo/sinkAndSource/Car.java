package com.example.demo.sinkAndSource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import scala.util.Random;

public class Car {
	
	private final int id;
	private final String year;
	private final String make;
	private final String model;
	private final String engine;
	
	public int getId() {
		return id;
	}

	public String getYear() {
		return year;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public String getEngine() {
		return engine;
	}
	
	@JsonCreator
	public Car (@JsonProperty("year") String year, @JsonProperty("make") String make,
				@JsonProperty("model") String model, @JsonProperty("engine") String engine)
	{
		this.year = year;
		this.make = make;
		this.model = model;
		this.engine = engine;
		this.id = new Random().nextInt();
	}
}
