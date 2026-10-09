import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaReproduccion playlist = new ListaReproduccion();
        int opcion;

        do {
            System.out.println("\n--- GESTOR DE PLAYLIST CIRCULAR ---");
            System.out.println("\n1. Agregar al inicio | \n2. Agregar al final | \n3. Buscar por ID |");
            System.out.println("\n4. Seleccionar como actual | \n5. Eliminar por ID | \n6. Eliminar actual |");
            System.out.println("\n7. Mostrar actual | \n8. Avanzar | \n9. Retroceder | \n10. Mostrar lista (Ambos sentidos) |");
            System.out.println("\n11. Cantidad de canciones | \n12. Simular K reproducciones | \n13. Salir |");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                case 2:
                    System.out.print("Título: "); String t = sc.nextLine();
                    System.out.print("Artista: "); String a = sc.nextLine();
                    System.out.print("Duración (seg): "); int d = sc.nextInt();
                    Cancion c = new Cancion(t, a, d);
                    if (opcion == 1) playlist.agregarAlInicio(c);
                    else playlist.agregarAlFinal(c);
                    break;
                case 3:
                    System.out.print("ID a buscar: ");
                    Nodo n = playlist.buscarPorId(sc.nextInt());
                    System.out.println(n != null ? "Encontrada: " + n.cancion : "No existe.");
                    break;
                case 4:
                    System.out.print("ID a seleccionar: "); playlist.seleccionarPorId(sc.nextInt()); break;
                case 5:
                    System.out.print("ID a eliminar: "); playlist.eliminarPorId(sc.nextInt()); break;
                case 6: playlist.eliminarActual(); break;
                case 7: playlist.mostrarActual(); break;
                case 8: playlist.avanzar(); break;
                case 9: playlist.retroceder(); break;
                case 10: playlist.mostrarLista(); break;
                case 11: System.out.println("Total de canciones: " + playlist.getTamaño()); break;
                case 12:
                    System.out.print("Cantidad K: "); playlist.simularReproduccion(sc.nextInt()); break;
                case 13: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 13);
        sc.close();
    }
}