package Buscas;

import java.util.LinkedList;
import Estruturas.*;
import Problemas.*;

public interface Busca {

	public LinkedList<No> buscar(Problema problema);

	public LinkedList<No> expandir(No no);

	public LinkedList<No> getBorda();
	
	public void mostrarCaminho ();

}