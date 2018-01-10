package Buscas;

import java.util.LinkedList;
import Estruturas.*;
import Problemas.*;

public interface Busca {

	public LinkedList<No> busca(Problema problema);

	public LinkedList<No> expandir(No no);

	public LinkedList<No> getBorda();

}