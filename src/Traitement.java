import java.util.Scanner;

public class Traitement {

    public static    int Age, Coef;
    public static    double Assurance = 0.0, Prime = 0.0;
    public static    char Sexe, Fume;

    public Traitement(){

    }

     public static void Application (int i, double[][] Client, Scanner scanner) {

        

        System.out.println("\n--- CLIENT # " + (i + 1));
        System.out.print("L'âge du client en entier ? ");
        Age = scanner.nextInt();
        System.out.print("Sexe du client (F pour féminin, M pour masculin) ? ");
        Sexe = scanner.next().charAt(0);
        System.out.print("Fumer (O - oui, N - non): ");
        Fume = scanner.next().charAt(0);
        System.out.print("Entrer le montant d'assurance vie (10,000$ le plus près): ");
        Assurance = scanner.nextDouble();

        Coef = (int) (Assurance / 10000); // Le coefficient de l'assurance

        if (Age <= 25) {
            if (Sexe == 'M') {
                Prime = 0.05;
            }
            Prime = (Prime + 1.32) * Coef;
        }

        if (Age > 25 && Age <= 55) {
            if (Sexe == 'M') {
                Prime = 0.07;
            }
            Prime = (Prime + 2.15) * Coef;
        }

        if (Age > 55) {
            if (Sexe == 'M') {
                Prime = 0.09;
            }
            Prime = (Prime + 3.80) * Coef;
        }

        if (Age >= 45 && Age <= 55 && Sexe == 'M' && Fume == 'O') {
            Prime = Prime + (0.25 * Coef);
        }

        if (Sexe == 'F' && (Age < 30 || Assurance <= 20000)) {
            Prime = Prime + 0.13 * Coef;
        }

        System.out.printf("Prime mensuelle: %.2f $\n", Prime);

        

        
    }
}


