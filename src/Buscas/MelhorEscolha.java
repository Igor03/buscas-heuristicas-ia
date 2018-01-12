package Buscas;

import java.util.LinkedList;

import Estruturas.Estado;
import Estruturas.No;
import Problemas.Problema;
import Problemas.Romenia;

public class MelhorEscolha implements Busca {

	public No no;
	public Problema problema;
	public int profundidadeGeral = -1;
	public LinkedList<No> borda = new LinkedList<>();
	public LinkedList<No> caminho = new LinkedList<>();
	public LinkedList<Estado> explorados = new LinkedList<>();

	@Override
	public LinkedList<No> buscar(Problema problema) {
		// TODO Auto-generated method stub

		this.problema = problema;
		no = new No(problema.getEstadoInicial());
		borda.add(no);

		while (true) {

			if (borda.isEmpty()) {
				System.out.println("Ocorreu um erro!");
				return null;
			}

			no = getMenorDLR(borda);
			borda.remove(no);
			explorados.add(no.estado);

			if (problema.testeDeObjetivo(no.estado)) {
				mostrarCaminho();
				return caminho;
			}

			LinkedList<No> expandidos = expandir(no);

			for (int i = 0; i < expandidos.size(); i++) {
				if (!this.explorados.contains(expandidos.get(i).estado))
					this.borda.add(expandidos.get(i));
			}
		}
	}

	@Override
	public LinkedList<No> expandir(No no) {
		// TODO Auto-generated method stub

		LinkedList<No> sucessores = new LinkedList<>();

		for (int i = 0; i < problema.funcaoSucessora(no.estado).size(); i++) {
			No s = new No(problema.funcaoSucessora(no.estado).get(i));
			s.estado = problema.funcaoSucessora(no.estado).get(i);
			s.pai = no;
			s.acao = problema.funcaoSucessora(no.estado).get(i);
			s.profundidade = profundidadeGeral + 1;
			sucessores.add(s);
		}

		return sucessores;

	}

	@Override
	public void mostrarCaminho() {
		// TODO Auto-generated method stub

		No noAux = no;

		while (noAux != null) {
			caminho.addFirst(noAux);
			noAux = noAux.pai;
		}

		for (int i = 0; i < caminho.size(); i++) {
			if (i + 1 < caminho.size())
				System.out.println(caminho.get(i).estado.nome + " --> " + caminho.get(i + 1).estado.nome + " #DLR "
						+ problema.getDLR(problema.getObejetivo(), caminho.get(i).estado));
		}
		System.out.println("Profundidade: " + no.profundidade);

	}

	@Override
	public LinkedList<No> getBorda() {
		// TODO Auto-generated method stub

		return borda;
	}

	private No getMenorDLR(LinkedList<No> borda) {

		int menor = problema.getDLR(problema.getObejetivo(), borda.get(0).estado);
		No noAux = borda.get(0);

		for (int i = 0; i < borda.size(); i++) {
			if (problema.getDLR(problema.getObejetivo(), borda.get(i).estado) < menor) {
				menor = problema.getDLR(problema.getObejetivo(), borda.get(i).estado);
				noAux = borda.get(i);
			}
		}

		return noAux;
	}

	public static void main(String[] args) {

		Problema problema = new Romenia("Arad", "Bucareste");
		Busca agente = new MelhorEscolha();
		agente.buscar(problema);

	}
}
