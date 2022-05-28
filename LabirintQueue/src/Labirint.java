import java.util.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Labirint {
    Object[][] map = new Map().map;
    int line = 1;
    int column = 1;

    Queue<String> queue = new LinkedList<String>();

    public boolean isWall() {
        var position = map[line][column];
        return (position == "#");
    }

    public boolean isExit() {
        var position = map[line][column];
        return (position == "S");
    }

    public void showMap() {
        for (Object[] line: map) {
            StringBuilder lineArray = new StringBuilder("[");

            for (Object o : line) {
                lineArray.append(o);
            }

            lineArray.append("]");

            System.out.println(lineArray);
        }
        System.out.println("\n");
    }

    public void findEntrance() {
        for (int l = 0; l < map.length; l++) {
            for (int c = 0; c < map[l].length; c++) {
                if (Objects.equals(map[l][c], "E")) {
                    line = l;
                    column = c;
                }
            }
        }
    }

    public String addCommand() {
        Scanner scanner = new Scanner(System.in);
        var command = scanner.next();
        queue.add(command);
        return command;
    }

    public void move( String direction) {
        var directionUper = direction.toUpperCase(Locale.ROOT);
        switch (directionUper) {
            case "A":
                column--;
                break;
            case "D":
                column++;
                break;
            case "W":
                line--;
                break;
            case "S":
                line++;
                break;
            default:
                System.out.println("Comando Invalido");
        }
        System.out.println(" ");
        showMap();
    }


    public static void main(String[] args) throws InterruptedException {
        Labirint labirint = new Labirint();
        String lastCommand = "";
        labirint.showMap();
        labirint.findEntrance();

        System.out.println("INSIRA OS COMANDOS EM ORDEM ATÉ CHEGAR AO 'S'");
        while (!lastCommand.toUpperCase().equals("INICIAR")) {
            System.out.println("COMANDOS");
            System.out.println("W (cima)");
            System.out.println("S (baixo)");
            System.out.println("D (direita)");
            System.out.println("A (esquerda)");
            System.out.println("iniciar");
            System.out.println("reiniciar");
            System.out.println("Comandos adicionados: " + labirint.queue.size());

            lastCommand = labirint.addCommand();
            if (Objects.equals(lastCommand.toUpperCase(), "INICIAR")) break;
            if (Objects.equals(lastCommand.toUpperCase(), "REINICIAR")) {
                System.out.println("REINICIANDO...");
                labirint.queue.clear();
            }
            System.out.println("");
            System.out.println("================================================");
            System.out.println("");
        }
        while (!labirint.queue.isEmpty()) {
            MILLISECONDS.sleep(1000);
            var oldLine = labirint.line;
            var oldColumn = labirint.column;
            labirint.move(labirint.queue.remove());
            if (labirint.isWall()) {
                System.out.println("PAREDE DETECTADA \n FIM DE JOGO");
                break;
            }
            if (labirint.isExit()) {
                System.out.println("VOCÊ ACHOU A SAIDA!");
                break;
            }
            labirint.map[oldLine][oldColumn] = " ";
            labirint.map[labirint.line][labirint.column] = "O";
        }
    }
}

class Map {
    String[][] map = {
            {"#","#","#","#","#","#","#","#","#","#","#","#"},
            {"#","E","S"," "," ","#"," "," "," "," ","#","#"},
            {"#"," "," "," ","#","#","#"," ","#","#","#","#"},
            {"#"," ","#"," ","#","#","#"," ","#","#","#","#"},
            {"#"," ","#","#","#","#","#"," ","#","#","#","#"},
            {"#"," ","#"," ","#"," ","#"," ","#","#","#","#"},
            {"#"," "," "," ","#"," "," "," "," "," ","S","#"},
            {"#"," ","#","#","#"," ","#"," ","#","#","#","#"},
            {"#"," ","#","#","#"," ","#"," ","#","#","#","#"},
            {"#"," ","#","#","#","#","#"," ","#","#","#","#"},
            {"#"," "," "," "," "," "," "," ","#","#","#","#"},
            {"#","#","#","#","#","#","#","#","#","#","#","#"}
    };


}