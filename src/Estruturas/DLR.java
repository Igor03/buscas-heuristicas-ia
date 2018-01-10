package Estruturas;

/*
 * Distancia em linha reta de um ponto a outro qualquaer
 * */
public class DLR {
	
	public Estado objetivo;
	public Estado estadoInicial;
	public Integer distancia;
	
	public DLR(Estado objetivo, Estado estadoInicial, Integer distancia) {
		super();
		this.objetivo = objetivo;
		this.estadoInicial = estadoInicial;
		this.distancia = distancia;
	}
}