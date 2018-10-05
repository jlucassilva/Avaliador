package common;

import model.Vaga;

public enum Vagas {

	LADY_WATER(1L, "Lady in the Water", new Vaga()),
	SNAKES_ON_A_PLANE(2L, "Snakes on a Plane", new Vaga()),
	JUST_MY_LUCK(3L, "Just My Luck", new Vaga()),
	SUPERMAN_RETURNS(4L, "Superman Returns", new Vaga()),
	NIGHT_LISTENER(5L, "The Night Listener", new Vaga()),
	YOU_ME_DUPREE(6L, "You, Me and Dupree", new Vaga());

	private final Long id;
	private final String title;
	private final Vaga vaga;

	Vagas(Long id, String title, Vaga vaga) {
		this.id = id;
		this.title = title;
		this.vaga = new Vaga(this.id, title);
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Vaga getVaga() {
		return vaga;
	}
}
