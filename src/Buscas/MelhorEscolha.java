package Buscas;

import java.util.LinkedList;

import Estruturas.Estado;
import Estruturas.No;
import Problemas.Problema;
import Problemas.Romenia;

/*
 * Ou busca gulosa
 * */
public class MelhorEscolha implements Busca {

	public No no;
	public Problema problema;
	public LinkedList<No> borda = new LinkedList<>();
	public LinkedList<No> caminho = new LinkedList<>();
	public LinkedList<Estado> explorados = new LinkedList<>();


	@Override
	public LinkedList<No> busca(Problema problema) {
		// TODO Auto-generated method stub

		this.problema = problema;
		no = new No(problema.getEstadoInicial());
		borda.add(no);

		while (true) {
			
			if (borda.isEmpty()) {
				System.out.println("Ocorreu um erro!");
				return null;
			}
			// no = borda.remove();
			no = getMenorDLR(borda);
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

		// return null;
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
			// s.custoDePasso = problema.getCustoCaminho(no.estado, s.estado) +
			// no.custoDePasso;
			// s.profundidade = profundidadeGeral + 1;
			sucessores.add(s);
		}

		return sucessores;

	}

	public void mostrarCaminho() {

		No noAux = no;

		while (noAux != null) {
			caminho.addFirst(noAux);
			noAux = noAux.pai;
		}

		for (int i = 0; i < caminho.size(); i++) {
			if (i + 1 < caminho.size()) {
				System.out.println(caminho.get(i).estado.nome + " --> " + caminho.get(i + 1).estado.nome);
				// custoTotal = custoTotal +
				// problema.getCustoCaminho(caminho.get(i).estado, caminho.get(i
				// + 1).estado);
			}
		}

	}

	@Override
	public LinkedList<No> getBorda() {
		// TODO Auto-generated method stub
		return borda;
	}

	public No getMenorDLR(LinkedList<No> borda) {

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
	
	public static void main (String[] args) {
		
		System.out.println("Testando commit");
		
		Problema problema = new Romenia("Arad", "Bucareste");
		Busca agente = new MelhorEscolha();
		
//		LinkedList<No> teste = new LinkedList<>();
//		teste.add(new No (problema.Arad.acoes.get(1)));
//		teste.add(new No (problema.Arad.acoes.get(0)));
//		teste.add(new No (problema.Arad.acoes.get(2)));
		
		agente.busca(problema);
		
//		System.out.println("..............................");
//		System.out.println(agente.getMenorDLR(teste).estado.nome);
//		
//		int[] vetor = {134, 213, 32143214, 46, 43214, 4324, 134, 1231, 1231, 434, 231};
//		int menor = vetor[0];
//		System.out.println(vetor.length);
//		
//		for (int i = 0; i < vetor.length; i++) {
//			if (vetor[i]<menor)
//				menor = vetor[i];
//		}
//		
//		System.out.println("FREWFRE: "+menor);
		
	}
}
