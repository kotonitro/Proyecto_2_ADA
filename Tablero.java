// Paquete al que pertenece la clase
package Proyecto_ADA;

// Clase Tablero que representa el tablero de un juego de barcos
public class Tablero {
  // Generador de números aleatorios para posicionar y disparar barcos
  static java.util.Random generator = new java.util.Random();

  // Matriz de caracteres que representa el tablero de juego
  // Usa una matriz más grande para tener bordes adicionales
  private char[][] t;

  // Contador de intentos (disparos) realizados
  private int intentos = 0;

  // Constructor de la clase Tablero
  // Recibe el tamaño del tablero como parámetro
  public Tablero(int n) {
    // Asegura un tamaño mínimo de 10x10
    if (n < 10) n = 10;

    // Crea una matriz de tamaño n+2 para tener bordes extra
    t = new char[n + 2][n + 2];

    // Inicializa el tablero con '0' (casillas vacías)
    for (int i = 1; i < n + 1; i++)
      for (int j = 1; j < n + 1; j++)
        t[i][j] = '0';

    // Ubicar barcos de diferentes tamaños y tipos
    // A: Portaaviones (5 casillas)
    while (!ubicarNave(5, 'A'));

    // B: Acorazado (4 casillas)
    while (!ubicarNave(4, 'B'));

    // C: Crucero (3 casillas)
    while (!ubicarNave(3, 'C'));

    // S: Submarino (3 casillas)
    while (!ubicarNave(3, 'S'));

    // D: Destructor (2 casillas)
    while (!ubicarNave(2, 'D'));

    // Reinicia el contador de intentos
    intentos = 0;
  }

  // Metodo para realizar un disparo en una coordenada específica
  public char disparo(int x, int y) {
    // Incrementa el contador de intentos
    intentos++;

    // Valida que las coordenadas estén dentro del tablero
    if (x < 1 || x > t.length) return 0;
    if (y < 1 || y > t.length) return 0;

    // Obtiene el contenido de la casilla
    char aux = t[x][y];

    // Si no es una casilla vacía, marca como impactada
    if (aux != '0') t[x][y] = 'X';

    // Devuelve el contenido original de la casilla
    return aux;
  }

  // Metodo privado para ubicar una nave en el tablero
  private boolean ubicarNave(int l, char tipo) {
    // Genera coordenadas aleatorias de inicio
    int x = 1 + generator.nextInt(t.length - 2);
    int y = 1 + generator.nextInt(t.length - 2);

    // Genera una dirección aleatoria (0: arriba, 1: abajo, 2: izquierda, 3: derecha)
    int dir = generator.nextInt(4);

    // Verifica límites del tablero según la dirección
    if (dir == 0 && x - (l - 1) < 1) return false;
    if (dir == 1 && x + (l - 1) > t.length - 1) return false;
    if (dir == 2 && y - (l - 1) < 1) return false;
    if (dir == 3 && y + (l - 1) > t.length - 1) return false;

    // Verifica que no haya colisión con otras naves en las casillas adyacentes
    for (int i = 0; i < l; i++) {
      if (dir == 0 && (t[x - i][y] != '0' || t[x - i][y + 1] != '0' || t[x - i][y - 1] != '0')) return false;
      if (dir == 1 && (t[x + i][y] != '0' || t[x + i][y + 1] != '0' || t[x + i][y - 1] != '0')) return false;
      if (dir == 2 && (t[x][y - i] != '0' || t[x - 1][y - i] != '0' || t[x + 1][y - i] != '0')) return false;
      if (dir == 3 && (t[x][y + i] != '0' || t[x - 1][y + i] != '0' || t[x + 1][y + i] != '0')) return false;
    }

    // Verifica el extremo final de la nave
    if (dir == 0 && t[x - (l)][y] != '0') return false;
    if (dir == 1 && t[x + (l)][y] != '0') return false;
    if (dir == 2 && t[x][y - (l)] != '0') return false;
    if (dir == 3 && t[x][y + (l)] != '0') return false;

    // Coloca la nave en el tablero
    for (int i = 0; i < l; i++) {
      if (dir == 0) t[x - i][y] = tipo;
      if (dir == 1) t[x + i][y] = tipo;
      if (dir == 2) t[x][y - i] = tipo;
      if (dir == 3) t[x][y + i] = tipo;
    }

    return true;
  }

  // Metodo para verificar si se ha ganado el juego
  public int ganar() {
    // Recorre el tablero buscando barcos sin hundir
    for (int i = 1; i < t[0].length - 1; i++)
      for (int j = 1; j < t.length - 1; j++)
        // Si encuentra una casilla de barco que no ha sido impactada, retorna 0
        if (t[i][j] != '0' && t[i][j] != 'X') return 0;

    // Si todos los barcos están hundidos, retorna el número de intentos
    return intentos;
  }

  // Metodo privado para imprimir el tablero (útil para depuración)
  public void Imprimir() {
    for (int i = 1; i < t[0].length - 1; i++) {
      for (int j = 1; j < t.length - 1; j++)
        System.out.print(t[i][j] + " ");
      System.out.println("");
    }
  }
}