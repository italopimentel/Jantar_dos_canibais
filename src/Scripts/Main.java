package Scripts;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {

        int numDeCanibais = 4; // Número de canibais
        int numDeMissionarios = 4; // Número de missionários

        ArrayList<Pessoa> vetorThreads = new ArrayList<>(); // Lista de threads
        ArrayList<Pessoa> vetorMargemDestino = new ArrayList<>(); // Lista de pessoas na margem de destino

        int tempoDeEntrarNoBarco = 500; // Tempo que leva para um passageiro entrar no barco
        int tempoDePercurso = 500; // Tempo que leva para o barco viajar de uma margem a outra

        Margem margemInicio = new Margem(vetorThreads); // Margem de partida
        Margem margemDestino = new Margem(vetorMargemDestino); // Margem de destino

        Barco barco = new Barco(2, tempoDeEntrarNoBarco, tempoDePercurso, margemInicio, margemDestino); // Cria um novo objeto barco com os parâmetros especificados

        for (int id = 0; id < numDeCanibais; id++) // Para cada canibal
        {
            vetorThreads.add(new Pessoa("canibal", barco)); // Adiciona um novo canibal à lista de threads
        }

        for (int id = numDeCanibais; id < numDeMissionarios + numDeCanibais; id++) // Para cada missionário
        {
            vetorThreads.add(new Pessoa("missionário", barco)); // Adiciona um novo missionário à lista de threads
        }

        for (int id = 0; id < vetorThreads.size(); id++) // Para cada thread na lista de threads
        {
            vetorThreads.get(id).start(); // Inicia a thread
        }


    }

}