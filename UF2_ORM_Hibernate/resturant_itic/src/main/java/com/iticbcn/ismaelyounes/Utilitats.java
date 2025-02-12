package com.iticbcn.ismaelyounes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.*;

/*
 * Classe que simplifica la lectura per entrada estàndard.
 */
public class Utilitats {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // Mètode per llegir una línia des de l'entrada estàndard
    public static String readLine() {
        try {
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                throw new RuntimeException("No deixis la cadena buida");
            }
            return line;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Mètode per confirmar amb una resposta de "sí" o "no"
    public static boolean confirmador(String resposta) {
        resposta = resposta.trim().toLowerCase();

        if (resposta.equals("s") || resposta.equals("si")) {
            return true;
        }

        return false;
    }

    public static int triaOpcio(String resposta, int cas) {
        while (true) {
            try {
                int opcio = Integer.parseInt(resposta); // Convertimos la respuesta a int
                if ((cas == 4 && opcio >= 1 && opcio <= 4) || (cas == 5 && opcio >= 1 && opcio <= 6)) {
                    return opcio;
                }
            } catch (NumberFormatException e) {

            }

            System.out.println("Opció no disponible, torna a introduir la resposta");
            resposta = Utilitats.readLine(); // Pedimos una nueva entrada
        }
    }

    // Metodes per rebre dades
    public static String nom(int i) {
        String text;
        boolean esDigit = false;
        if (i == 1) {
            System.out.println("Introueix el teu Nom:");
            text = Utilitats.readLine();
            while (true) {
                esDigit = false;
                if (text.isBlank()) {
                    System.out.println("El camp és obligatori:");
                    text = Utilitats.readLine();
                } else {
                    for (int j = 0; j < text.length(); j++) {
                        if (Character.isDigit(text.charAt(j))) {
                            esDigit = true;
                            break;
                        } else {
                            esDigit = false;
                        }
                    }
                    if (esDigit) {
                        System.out.println("El nom no pot tenir Digits:");
                        text = Utilitats.readLine();
                    } else {
                        break;
                    }
                }

            }
        } else if (i == 2) {
            System.out.println("Introueix el nom del restaurant:");
            text = Utilitats.readLine();
            while (true) {
                esDigit = false;
                if (text.isBlank()) {
                    System.out.println("El camp és obligatori:");
                    text = Utilitats.readLine();
                } else {
                    for (int j = 0; j < text.length(); j++) {
                        if (Character.isDigit(text.charAt(j))) {
                            esDigit = true;
                            break;
                        } else {
                            esDigit = false;
                        }
                    }
                    if (esDigit) {
                        System.out.println("El nom no pot tenir Digits:");
                        text = Utilitats.readLine();
                    } else {
                        break;
                    }
                }

            }

        } else {
            System.out.println("Introueix el Nom del producte:");
            text = Utilitats.readLine();
            while (true) {
                if (text.isBlank()) {
                    System.out.println("El camp és obligatori:");
                    text = Utilitats.readLine();
                } else {
                    for (int j = 0; j < text.length(); j++) {
                        if (Character.isDigit(text.charAt(j))) {
                            esDigit = true;
                            break;
                        } else {
                            esDigit = false;
                        }
                    }
                    if (esDigit) {
                        System.out.println("El nom del producte no pot tenir Digits:");
                        text = Utilitats.readLine();
                    } else {
                        break;
                    }
                }

            }
        }
        return text;
    }

    public static String telClient() {
        String text;
        String regex = "^[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);

        System.out.println("Introueix el teu numero sense prefix :");
        text = Utilitats.readLine();

        while (true) {
            if (text.isBlank()) {
                System.out.println("El camp és obligatori:");
            } else {
                Matcher matcher = pattern.matcher(text);
                if (matcher.matches()) {
                    break;
                } else {
                    System.out.println(
                            "El Numero no pot tenir lletres ni caràcters especials i ha tenir 9 digits. Introdueix un número vàlid:");
                }
            }
            text = Utilitats.readLine();
        }
        return text;
    }

    public static String dataClient() {
        String text;

        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})$";
        Pattern pattern = Pattern.compile(regex);

        System.out.println("Introueix la Data actual (DD/MM/AAAA):");
        text = Utilitats.readLine();

        while (true) {
            if (text.isBlank()) {
                System.out.println("El camp és obligatori:");
            } else {
                Matcher matcher = pattern.matcher(text);
                if (matcher.matches()) {
                    break;
                } else {
                    System.out.println("Format de data incorrecte. Torna a introduir la data en format (DD/MM/AAAA):");
                }
            }
            text = Utilitats.readLine();
        }

        return text;
    }

    public static String unitatsArticle() {
        String text;
        boolean esDigit = false;
        System.out.println("Introdueix les unitats de l'article (ex. kg, unitats, litres, etc.):");
        text = Utilitats.readLine();
        while (true) {
            if (text.isBlank()) {
                System.out.println("El camp és obligatori:");
                text = Utilitats.readLine();
            } else {
                for (int j = 0; j < text.length(); j++) {
                    if (Character.isDigit(text.charAt(j))) {
                        esDigit = true;
                        break;
                    } else {
                        esDigit = false;
                    }
                }
                if (esDigit) {
                    System.out.println("Les unitats no poden ser Numeriques:");
                    text = Utilitats.readLine();
                } else {
                    break;
                }
            }
        }
        return text.toUpperCase();
    }

    public static double quantitat() {
        String text;
        boolean esDigit = false;
        System.out.println("Introdueix la quantitat :");
        text = Utilitats.readLine();
        while (true) {
            if (text.isBlank()) {
                System.out.println("El camp és obligatori:");
                text = Utilitats.readLine();
            } else {
                for (int j = 0; j < text.length(); j++) {
                    if (Character.isLetter(text.charAt(j))) {
                        esDigit = true;
                        break;
                    } else {
                        esDigit = false;
                    }
                }
                if (esDigit) {
                    System.out.println("La quantitat ha de ser numerica:");
                    text = Utilitats.readLine();
                } else {
                    break;
                }
            }
        }
        return Double.parseDouble(text);
    }

    public static double preuArticle() {
        String text;
        boolean esDigit = false;
        System.out.println("Introdueix El preu del Article :");
        text = Utilitats.readLine();
        while (true) {
            if (text.isBlank()) {
                System.out.println("El camp és obligatori:");
                text = Utilitats.readLine();
            } else {
                for (int j = 0; j < text.length(); j++) {
                    if (Character.isLetter(text.charAt(j))) {
                        esDigit = true;
                        break;
                    } else {
                        esDigit = false;
                    }
                }
                if (esDigit) {
                    System.out.println("La quantitat ha de ser numerica:");
                    text = Utilitats.readLine();
                } else {
                    break;
                }
            }
        }
        return Double.parseDouble(text);
    }
}
