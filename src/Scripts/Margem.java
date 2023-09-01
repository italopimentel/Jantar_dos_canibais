package Scripts;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Margem {
	
	public ArrayList<Pessoa> pessoas; // ArrayList de objetos do tipo Pessoa
	public Semaphore mutex; // Objeto do tipo Semaphore para controlar o acesso ao ArrayList de pessoas
	
	public Margem (ArrayList<Pessoa> vetorDePessoas)
	{
		this.pessoas = vetorDePessoas; // Inicializa o atributo pessoas com o ArrayList recebido como parâmetro
		this.mutex = new Semaphore(1); // Inicializa o atributo mutex com um novo objeto do tipo Semaphore com valor inicial 1
	}
	
	public int[] contarNumPessoas () 
	{
		int numMissionarios = 0; // Contador de missionários
		int numCanibais = 0; // Contador de canibais
		
		for (int id = 0; id < this.pessoas.size(); id++)
		{
			if (pessoas.get(id).getTipoPessoa() == "canibal")
			{
				numCanibais += 1; // Incrementa o contador de canibais se a pessoa for um canibal
			}
			else if(pessoas.get(id).getTipoPessoa() == "missionário")
			{
				numMissionarios += 1; // Incrementa o contador de missionários se a pessoa for um missionário
			}
			
		}
		
		return new int[] {numMissionarios, numCanibais}; // Retorna um array com o número de missionários e canibais na margem	
		
	}
	
	public void removerPessoa(int id)
	{
		try {
			this.mutex.acquire(); // Adquire o mutex para garantir que apenas uma thread possa acessar o ArrayList de pessoas ao mesmo tempo
			this.pessoas.remove(id); // Remove a pessoa do ArrayList de pessoas
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally 
		{
			this.mutex.release(); // Libera o mutex para permitir que outras threads possam acessar o ArrayList de pessoas
		}
	}
	
	public void adicionarPessoasMargem(Pessoa passageiro)
	{
		try {
			this.mutex.acquire(); // Adquire o mutex para garantir que apenas uma thread possa acessar o ArrayList de pessoas ao mesmo tempo
			this.pessoas.add(passageiro); // Adiciona a pessoa ao ArrayList de pessoas
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally 
		{
			this.mutex.release(); // Libera o mutex para permitir que outras threads possam acessar o ArrayList de pessoas
		}
	}
	
	public void mostrarPessoasNaMargem ()
	{
		int numCanibais = 0; // Contador de canibais
		int numMissionarios = 0; // Contador de missionários
		
        // Percorre o ArrayList de pessoas e conta o número de canibais e missionários na margem
        for (int index = 0; index < pessoas.size(); index++)
        {
            if (pessoas.get(index).getTipoPessoa() == "canibal")
            {
                numCanibais += 1;
            }
            else if(pessoas.get(index).getTipoPessoa() == "missionário")
            {
                numMissionarios += 1;
            }
        }
        
        System.out.println("A distribuição na margem é: " + numCanibais + " canibais, " + numMissionarios + " missionários");
    }
}