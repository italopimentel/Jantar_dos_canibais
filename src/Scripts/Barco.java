package Scripts;

import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class Barco{
	
	Semaphore passageiros; // Semáforo para controlar o acesso dos passageiros ao barco
	int tempoDeEntrar; // Tempo que leva para um passageiro entrar no barco
	int tempoDePercurso; // Tempo que leva para o barco viajar de uma margem a outra
	Margem margemInicio; // Margem de partida
	Margem margemDestino; // Margem de destino
	ArrayList<Pessoa> passageirosAbordo; // Lista de passageiros a bordo
	int[] quantPessoasMargem; // Array contendo o número de pessoas na margem
	int selectMode; // Variável de seleção de modo
	
	public Barco(int numMaxDePassageiros, int tempoDeEntrarNoBarco, int tempoDePercurso, Margem margemInicio, Margem margemDestino) {
		this.passageiros = new Semaphore(1); // Inicializa o semáforo com 1 permissão
		this.tempoDeEntrar = tempoDeEntrarNoBarco;
		this.tempoDePercurso = tempoDePercurso;
		this.margemInicio = margemInicio;
		this.passageirosAbordo = new ArrayList<Pessoa>();
		this.margemDestino = margemDestino;
		this.quantPessoasMargem = margemInicio.contarNumPessoas();
		//pos[0] = numMissonarios, pos[1] = numCanibal
		if(this.quantPessoasMargem[0] == this.quantPessoasMargem[1])
		{
			this.selectMode = 1;
		}
		else if (this.quantPessoasMargem[0] > this.quantPessoasMargem[1])
		{
			this.selectMode = 2;
		}
		
	}
	
	public void entrar(Pessoa passageiro)
	{
		try {
			this.passageiros.acquire(); // Adquire uma permissão do semáforo
			
			if (this.selectMode == 1){ // Se selectMode for 1
				if (passageirosAbordo.size() == 0) // Se não houver passageiros a bordo
				{
					this.passageirosAbordo.add(passageiro); // Adiciona o passageiro à lista de passageiros a bordo
	
					this.margemInicio.removerPessoa(this.margemInicio.pessoas.indexOf(passageiro)); // Remove o passageiro da margem de partida
					this.quantPessoasMargem = this.margemInicio.contarNumPessoas(); // Atualiza o número de pessoas na margem de partida
		
					System.out.println("O " + passageiro.getTipoPessoa() + " foi removida da margem inicial"); // Imprime uma mensagem indicando que o passageiro foi removido da margem de partida
					
				}
				
				if (passageirosAbordo.size() == 1) // Se houver um passageiro a bordo
				{
					if (passageiro.getTipoPessoa() != this.passageirosAbordo.get(0).getTipoPessoa()) // Se o tipo de pessoa do passageiro for diferente do tipo de pessoa do passageiro já a bordo
					{
						this.passageirosAbordo.add(passageiro); // Adiciona o passageiro à lista de passageiros a bordo
	
						this.margemInicio.removerPessoa(this.margemInicio.pessoas.indexOf(passageiro)); // Remove o passageiro da margem de partida
						this.quantPessoasMargem = this.margemInicio.contarNumPessoas(); // Atualiza o número de pessoas na margem de partida
			
						System.out.println("O " + passageiro.getTipoPessoa() + " foi removida da margem inicial"); // Imprime uma mensagem indicando que o passageiro foi removido da margem de partida
						
					}
				}
				
				if (passageirosAbordo.size() == 2) // Se houver dois passageiros a bordo
				{
					Thread.sleep(this.tempoDePercurso); // A thread dorme por um tempo igual a tempoDePercurso
					System.out.println("A margem de destino era antes: " + this.margemDestino.pessoas.size() + " Pessoas"); // Imprime uma mensagem indicando quantas pessoas estavam na margem de destino antes de adicionar novos passageiros
					for (int id = 0; id < this.passageirosAbordo.size(); id++) 
					{
						this.margemDestino.adicionarPessoasMargem(this.passageirosAbordo.get(id)); // Adiciona cada passageiro a bordo à margem de destino
						this.passageirosAbordo.get(id).entregue = true;
					}
					System.out.println("A margem de destino está agora com: " + this.margemDestino.pessoas.size() + " Pessoas"); // Imprime uma mensagem indicando quantas pessoas estão na margem de destino após adicionar novos passageiros
					this.passageirosAbordo.clear(); // Limpa a lista de passageiros a bordo
				}
			
			Thread.sleep(this.tempoDeEntrar); // A thread dorme por um tempo igual a tempoDeEntrar
			
			}
			
			if (this.selectMode == 2) // Se selectMode for 2
			{
				//Código destinado a condição na qual o número de missionários é maior do que
				//o numero de canibais
			}
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally {
				this.passageiros.release(); // Libera uma permissão para o semáforo
			}
	}
}