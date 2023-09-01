package Scripts;

public class Pessoa extends Thread{
	
	private String tipo; // Tipo da pessoa (canibal ou missionário)
	private Barco barco; // Objeto do tipo Barco que representa o barco que a pessoa pode usar
	public boolean entregue;
	
	public Pessoa (String tipo, Barco barco)
	{
		this.tipo = tipo; // Inicializa o atributo tipo com o valor recebido como parâmetro
		this.barco = barco; // Inicializa o atributo barco com o objeto recebido como parâmetro
		this.entregue = false;
		
		if (tipo == "canibal")
		{
			new Thread((Runnable) this, "Canibal"); // Cria uma nova thread do tipo Canibal
			System.out.println("A thread do tipo " + tipo + " foi iniciado "); // Imprime uma mensagem informando que a thread foi iniciada
		}
		else
		{
			new Thread((Runnable) this, "Missionário"); // Cria uma nova thread do tipo Missionário
			System.out.println("A thread do tipo " + tipo + " foi iniciado"); // Imprime uma mensagem informando que a thread foi iniciada
		}
		
		
	}

	public String getTipoPessoa ()
	{
		return this.tipo; // Retorna o valor do atributo tipo (canibal ou missionário)
	}
	
	@Override
	public void run()
	{
		while (this.entregue == false)
		{
			this.barco.entrar(this); // Chama o método entrar do objeto barco passando a própria pessoa como parâmetro
		}
		
		
	}
	
	
}