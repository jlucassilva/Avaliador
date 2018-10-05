package common;

import model.Avaliacao;

import java.util.ArrayList;
import java.util.List;

public class Util {

	public static List<Avaliacao> inicializaAvaliacaos() {
		long u = 1;
		u++;
		List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>() {{
			add(new Avaliacao(1L, Candidatos.Rose.getCandidato(), Vagas.LADY_WATER.getVaga(), 2.5));
			add(new Avaliacao(2L, Candidatos.Rose.getCandidato(), Vagas.SNAKES_ON_A_PLANE.getVaga(), 3.5));
			add(new Avaliacao(3L, Candidatos.Rose.getCandidato(), Vagas.JUST_MY_LUCK.getVaga(), 3.0));
			add(new Avaliacao(4L, Candidatos.Rose.getCandidato(), Vagas.SUPERMAN_RETURNS.getVaga(), 3.5));
			add(new Avaliacao(5L, Candidatos.Rose.getCandidato(), Vagas.YOU_ME_DUPREE.getVaga(), 2.5));
			add(new Avaliacao(6L, Candidatos.Rose.getCandidato(), Vagas.NIGHT_LISTENER.getVaga(), 3.0));

			add(new Avaliacao(7L, Candidatos.Seymour.getCandidato(), Vagas.LADY_WATER.getVaga(), 3.0));
			add(new Avaliacao(8L, Candidatos.Seymour.getCandidato(), Vagas.SNAKES_ON_A_PLANE.getVaga(), 3.5));
			add(new Avaliacao(9L, Candidatos.Seymour.getCandidato(), Vagas.JUST_MY_LUCK.getVaga(), 1.5));
			add(new Avaliacao(10L, Candidatos.Seymour.getCandidato(), Vagas.SUPERMAN_RETURNS.getVaga(), 5.0));
			add(new Avaliacao(11L, Candidatos.Seymour.getCandidato(), Vagas.YOU_ME_DUPREE.getVaga(), 3.5));
			add(new Avaliacao(12L, Candidatos.Seymour.getCandidato(), Vagas.NIGHT_LISTENER.getVaga(), 3.0));

			add(new Avaliacao(13L, Candidatos.Phillips.getCandidato(), Vagas.LADY_WATER.getVaga(), 2.5));
			add(new Avaliacao(14L, Candidatos.Phillips.getCandidato(), Vagas.SNAKES_ON_A_PLANE.getVaga(), 3.0));
			add(new Avaliacao(15L, Candidatos.Phillips.getCandidato(), Vagas.SUPERMAN_RETURNS.getVaga(), 3.5));
			add(new Avaliacao(16L, Candidatos.Phillips.getCandidato(), Vagas.NIGHT_LISTENER.getVaga(), 4.0));

			add(new Avaliacao(17L, Candidatos.Puig.getCandidato(), Vagas.SNAKES_ON_A_PLANE.getVaga(), 3.5));
			add(new Avaliacao(18L, Candidatos.Puig.getCandidato(), Vagas.JUST_MY_LUCK.getVaga(), 3.0));
			add(new Avaliacao(19L, Candidatos.Puig.getCandidato(), Vagas.SUPERMAN_RETURNS.getVaga(), 4.0));
			add(new Avaliacao(20L, Candidatos.Puig.getCandidato(), Vagas.YOU_ME_DUPREE.getVaga(), 2.5));
			add(new Avaliacao(21L, Candidatos.Puig.getCandidato(), Vagas.NIGHT_LISTENER.getVaga(), 4.5));

			add(new Avaliacao(22L, Candidatos.LaSalle.getCandidato(), Vagas.LADY_WATER.getVaga(), 3.0));
			add(new Avaliacao(23L, Candidatos.LaSalle.getCandidato(), Vagas.SNAKES_ON_A_PLANE.getVaga(), 4.0));
			add(new Avaliacao(24L, Candidatos.LaSalle.getCandidato(), Vagas.JUST_MY_LUCK.getVaga(), 2.0));
			add(new Avaliacao(25L, Candidatos.LaSalle.getCandidato(), Vagas.SUPERMAN_RETURNS.getVaga(), 3.0));
			add(new Avaliacao(26L, Candidatos.LaSalle.getCandidato(), Vagas.YOU_ME_DUPREE.getVaga(), 2.0));
			add(new Avaliacao(27L, Candidatos.LaSalle.getCandidato(), Vagas.NIGHT_LISTENER.getVaga(), 3.0));

			add(new Avaliacao(28L, Candidatos.Matthews.getCandidato(), Vagas.LADY_WATER.getVaga(), 3.0));
			add(new Avaliacao(29L, Candidatos.Matthews.getCandidato(), Vagas.SNAKES_ON_A_PLANE.getVaga(), 4.0));
			add(new Avaliacao(30L, Candidatos.Matthews.getCandidato(), Vagas.SUPERMAN_RETURNS.getVaga(), 5.0));
			add(new Avaliacao(31L, Candidatos.Matthews.getCandidato(), Vagas.YOU_ME_DUPREE.getVaga(), 3.5));
			add(new Avaliacao(32L, Candidatos.Matthews.getCandidato(), Vagas.NIGHT_LISTENER.getVaga(), 3.0));

			add(new Avaliacao(33L, Candidatos.Toby.getCandidato(), Vagas.SNAKES_ON_A_PLANE.getVaga(), 4.5));
			add(new Avaliacao(34L, Candidatos.Toby.getCandidato(), Vagas.SUPERMAN_RETURNS.getVaga(), 4.0));
			add(new Avaliacao(35L, Candidatos.Toby.getCandidato(), Vagas.YOU_ME_DUPREE.getVaga(), 1.0));
		}};

		return avaliacoes;
	}
}
