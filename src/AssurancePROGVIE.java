import java.util.Scanner;

public class AssurancePROGVIE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int NbreClient;
        double[][] Client = new double[10][2];

        System.out.println("=== ASSURANCE PROGVIE INC. ===\n");
        System.out.print("Entrer le nombre de client à traiter: ");
        NbreClient = scanner.nextInt();
        System.out.println();

        for (int i = 0; i < NbreClient; i++) {
            Traitement.Application(i, Client, scanner);
            Client[i][0] = Traitement.Age;
            Client[i][1] = Traitement.Prime;
        }

        double AgeMoyen = 0.0, PrimeMoyen = 0.0;

        for (int j = 0; j < NbreClient; j++) {
            PrimeMoyen += Client[j][1];
            AgeMoyen += Client[j][0];
        }

        PrimeMoyen /= NbreClient;
        AgeMoyen /= NbreClient;

        System.out.println("\n============================================");
        System.out.println("RAPPORT (ASSURANCE PROGVIE INC.)\n");
        System.out.printf("Prime mensuelle moyenne: %.2f $\n", PrimeMoyen);
        System.out.printf("Age moyen des clients: %.2f\n\n", AgeMoyen);
        System.out.println("========================================");

        new PahoTest001Producer().doDemo("Prime Moyenne : " + PrimeMoyen + "\nAge Moyen : " + AgeMoyen);

        scanner.close();
    }

}

    