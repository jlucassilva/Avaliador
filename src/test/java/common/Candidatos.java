package common;

import model.Candidato;

public enum Candidatos {

	Rose(1L, "Lisa Rose", new Candidato()),
	Seymour(2L, "Gene Seymour", new Candidato()),
	Phillips(3L, "Michael Phillips", new Candidato()),
	Puig(4L, "Claudia Puig", new Candidato()),
	LaSalle(5L, "Mick LaSalle", new Candidato()),
	Matthews(6L, "Jack Matthews", new Candidato()),
	Toby(7L, "Toby", new Candidato());

	private Long id;
	private final String name;
	private final Candidato candidato;

	Candidatos(Long id, String name, Candidato candidato) {
		this.id = id;
		this.name = name;
		this.candidato = new Candidato(this.id, this.name);
	}

	public String getName() {
		return name;
	}

	public Candidato getCandidato() {
		return candidato;
	}
}
