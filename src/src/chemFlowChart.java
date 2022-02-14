import java.util.Scanner;

public class chemFlowChart {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        boolean a;


        System.out.println("Conductive?");
        a = input.nextBoolean();


        if (a) { //conductive
            System.out.println("Brittle ");
            a = input.nextBoolean();

            if (a) { //brittle
                System.out.println("metallic bond");
            }

            else { //non-brittle
                System.out.println("network covalent compound");
            }
        }

        else { //non-conductive
            System.out.println("dissolves in water");
            a = input.nextBoolean();

            if (a) { //dissolves in water
                System.out.println("conducts in water");
                a = input.nextBoolean();

                if (a) { //conducts in water
                    System.out.println("ionic");
                } else { //doesn't conduct water
                    System.out.println("Covalent bond");
                }
            } else { //doesn't dissolve in water
                System.out.println("dissolves in oil ");
                a = input.nextBoolean();
                if (a)
                    System.out.println("non-poor covalent bond");
            }
        }
    }
}
