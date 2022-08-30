import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class rep_grafo {

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    System.out.println("Insira o nome do arguivo a ser lido: ");
    String fileName = sc.nextLine();
    InputStreamReader isr = new InputStreamReader(
      new FileInputStream(fileName)
    );

    BufferedReader br = new BufferedReader(isr);
    ArrayList<String> listaGrafo = new ArrayList<String>(); //lista para armazenar os inputs com numero apropriado de espacos
    String initialLine = br.readLine();
    String currentLine = br.readLine();
    while (currentLine != null) {
      String correctLine = currentLine.trim().replaceAll("\\s+", " "); //tirando os espaços a mais e colocando em uma ArrayList.
      listaGrafo.add(correctLine);
      currentLine = br.readLine();
    }
    listaGrafo.trimToSize();
    boolean isOnSecond = false;
    String auxVertices = "";
    String auxArestas = "";
    //metodo desnecessariamente custoso e complicado (porem eu nao consegui pensar numa ideia melhor no tempo q eu tentei fazer isso) pra pegar o numero de vertices e arestas em int
    for (int i = 0; i < initialLine.length(); i++) {
      if (initialLine.charAt(i) == ' ') {
        isOnSecond = true;
      } else if (!isOnSecond) {
        auxVertices += initialLine.charAt(i);
      } else {
        auxArestas += initialLine.charAt(i);
      }
    }
    int numVertices = Integer.parseInt(auxVertices);
    int numArestas = Integer.parseInt(auxArestas);

    isOnSecond = false;

    System.out.println(numVertices + " " + numArestas);

    int[][] matrizdeIncidencia = new int[numVertices][numArestas];
    
    Iterator<String> iterator = listaGrafo.iterator(); //cria um iterator da lista de inputs
    int currentPointing = 0, currentPointed = 0;
    String auxFirst = "";
    String auxSecond = "";
    int arestaIndex = 0;
    while (iterator.hasNext()) { // while loop que para no fim da lista de inputs
      String currentAresta = iterator.next();
      isOnSecond = false;
      currentPointing = 0;
      currentPointed = 0;
      for (int i = 0; i < currentAresta.length(); i++) { // transforma os dados da string em dois ints do mesmo jeito que a funcao la em cima
        if (currentAresta.charAt(i) == ' ') {
          isOnSecond = true;
        } else if (!isOnSecond) {
          auxFirst += currentAresta.charAt(i);
        } else {
          auxSecond += currentAresta.charAt(i);
        }
      }
      currentPointing = Integer.parseInt(auxFirst);
      currentPointed = Integer.parseInt(auxSecond);
      auxFirst = "";
      auxSecond = "";
      for (int i = 0; i < numVertices; i++) { //preenche a coluna da aresta de acordo com a incidencia
        if (i + 1 == currentPointing) {
          matrizdeIncidencia[i][arestaIndex] = -1;
        } else if (i + 1 == currentPointed) {
          matrizdeIncidencia[i][arestaIndex] = 1;
        } else {
          matrizdeIncidencia[i][arestaIndex] = 0;
        }
      }
      arestaIndex += 1;
    }

    System.out.println("\n\n\n\n\n\n\n\n\n");
    System.out.print("Agora, insira um vértice: ");
    int insertedVertice = sc.nextInt();
    int grauDeSaida = 0;
    int grauDeEntrada = 0;
    ArrayList<Integer> conjSucessores = new ArrayList<Integer>();
    ArrayList<Integer> conjPredecessores = new ArrayList<Integer>();
    

    for (int i = 0; i < numArestas; i++) {
        if (matrizdeIncidencia[insertedVertice-1][i] == -1) {
            grauDeSaida += 1;
            for (int j = 0; j < numVertices; j++) {
                if (matrizdeIncidencia[j][i] == 1) {// busca o vertice conectado, iterando pela aresta.
                    conjSucessores.add(j+1);
                }
            }
        }
        if (matrizdeIncidencia[insertedVertice-1][i] == 1) {
            grauDeEntrada += 1;
            for (int j = 0; j < numVertices; j++) {
                if (matrizdeIncidencia[j][i] == -1) {// busca o vertice conectado, da mesma forma que a função acima
                    conjPredecessores.add(j+1);
                }
            }
        }
    }

    System.out.println("1) GRAU DE SAIDA: " + grauDeSaida);
    System.out.println("2) GRAU DE ENTRADA: " + grauDeEntrada);
    System.out.println("3) CONJUNTO DE SUCESSORES: " + conjSucessores);
    System.out.println("4) CONJUNTO DE PREDECESSORES: " + conjPredecessores);
    

    sc.close();
    br.close();
  }
}
