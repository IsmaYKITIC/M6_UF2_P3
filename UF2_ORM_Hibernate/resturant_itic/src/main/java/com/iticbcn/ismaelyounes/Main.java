package com.iticbcn.ismaelyounes;

import com.iticbcn.ismaelyounes.dao.ClientDAO;
import com.iticbcn.ismaelyounes.dao.EmpleatDAO;
import com.iticbcn.ismaelyounes.dao.RestaurantDAO;
import com.iticbcn.ismaelyounes.dao.ReservaDAO;
import com.iticbcn.ismaelyounes.model.Empleat;
import com.iticbcn.ismaelyounes.model.Client;
import com.iticbcn.ismaelyounes.model.Reserva;
import com.iticbcn.ismaelyounes.model.Restaurant;
import org.hibernate.SessionFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ClientDAO clientDAO = new ClientDAO(sessionFactory);
        EmpleatDAO empleatsDAO = new EmpleatDAO(sessionFactory);
        RestaurantDAO restaurantDAO = new RestaurantDAO(sessionFactory);
        ReservaDAO reservaDAO = new ReservaDAO(sessionFactory);

        mostraMenu();
        while (true) {
            String resposta = Utilitats.readLine();
            int seleccion = Utilitats.triaOpcio(resposta, 4);

            switch (seleccion) {
                case 1:
                    gestionarEmpleat(empleatsDAO);
                    break; // Se agrega break
                case 2:
                    gestionarClient(clientDAO);
                    break;
                case 3:
                    gestionarRestaurant(restaurantDAO);
                    break;
                case 4:
                    gestionarReserva(reservaDAO, clientDAO);
                    break;
                default:
                    System.out.println("Opció no vàlida. Torna a intentar.");
            }

            mostraMenu();
        }
    }

    public static void mostraMenu() {
        System.out.println("\nSelecciona una opción a gestionar: ");
        System.out.println("1. Empleat");
        System.out.println("2. Client");
        System.out.println("3. Restaurant");
        System.out.println("4. Reserva");
        System.out.println();
    }

    public static void mostraMenu2(String tipus) {
        System.out.println("\nGestións per " + tipus + "s:");
        System.out.println("1. Crear " + tipus);
        System.out.println("2. Obtenir " + tipus);
        System.out.println("3. Obtenir tots els " + tipus + "s");
        System.out.println("4. Actualitzar " + tipus);
        System.out.println("5. Eliminar " + tipus);
        System.out.println("6. Tornar al menú principal");
        System.out.print("Selecciona una opció: ");
    }

    private static void gestionarEmpleat(EmpleatDAO empleatsDAO) {
        while (true) {
            mostraMenu2("Empleat");
            String resposta = Utilitats.readLine();
            int seleccion = Utilitats.triaOpcio(resposta, 5);
            System.out.println();

            switch (seleccion) {
                case 1:
                    String nom = Utilitats.nom(1);
                    String telefon = Utilitats.telClient();
                    System.out.print("Introdueix el correu: ");
                    String correu = Utilitats.readLine();
                    Empleat empleatCreado = new Empleat(nom, telefon, correu);
                    System.out.println("Afegir Restaurant?");
                    String cadena = Utilitats.readLine();

                    if (Utilitats.confirmador(cadena)) {
                        empleatCreado.setRestaurant(DemanarRestaurant());

                    }

                    empleatsDAO.crearEmpleat(empleatCreado);
                    System.out.println("Empleat creat correctament amb ID: " + empleatCreado.getIdEmpleat());
                    break;
                case 2:
                    System.out.print("Introdueix l'ID de l'Empleat: ");
                    String idResposta = Utilitats.readLine();
                    long idBuscar = Integer.parseInt(idResposta);

                    Empleat empleatObtenido = empleatsDAO.obtenirEmpleat(idBuscar);
                    if (empleatObtenido != null) {
                        System.out.println("Empleat trobat: ");
                        System.out.println("Nom: " + empleatObtenido.getNom());
                        System.out.println("Email: " + empleatObtenido.getCorreo());
                        System.out.println("Teléfon: " + empleatObtenido.getTelefon());
                        if (empleatObtenido.getRestaurant() != null) {
                            System.out.println("Restaurant: " + empleatObtenido.getRestaurant().getNom());
                        } else {
                            System.out.println("Restaurant: NULL ");
                        }

                    } else {
                        System.out.println("No s'ha trobat un empleat amb aquest ID.");
                    }
                    break;

                case 3:

                    System.out.println("Llistat de clients:");
                    List<Empleat> empleats = empleatsDAO.obtenirTotsEmpleats();
                    if (empleats.isEmpty()) {
                        System.out.println("No s'ha trobat cap client.");
                    } else {
                        for (Empleat e : empleats) {
                            System.out.println("Empleat trobat: ");
                            System.out.println("Nom: " + e.getNom());
                            System.out.println("Email: " + e.getCorreo());
                            System.out.println("Teléfon: " + e.getTelefon());
                            if (e.getRestaurant() != null) {
                                System.out.println("Restaurant: " + e.getRestaurant().getNom());
                            } else {
                                System.out.println("Restaurant: NULL ");
                            }
                        }
                    }
                    break;

                case 4:

                    System.out.print("Introdueix l'ID de l'Empleat a actualitzar: ");
                    String idRespostaActualizar = Utilitats.readLine();
                    long idActualizar = Integer.parseInt(idRespostaActualizar);

                    Empleat empleatActualizar = empleatsDAO.obtenirEmpleat(idActualizar);

                    if (empleatActualizar != null) {

                        String nouNom = Utilitats.nom(1);

                        String nouTelefon = Utilitats.telClient();

                        System.out.print("Introdueix el nou correu: ");
                        String nouCorreu = Utilitats.readLine();

                        empleatActualizar.setNom(nouNom);
                        empleatActualizar.setTelefon(nouTelefon);
                        empleatActualizar.setCorreo(nouCorreu);

                        empleatsDAO.actualitzarEmpleat(empleatActualizar);
                        System.out.println("Empleat actualitzat correctament!");
                    } else {
                        System.out.println("No s'ha trobat un empleat amb aquest ID.");
                    }
                    break;

                case 5:

                    System.out.print("Introdueix l'ID de l'Empleat a eliminar: ");
                    String idRespostaEliminar = Utilitats.readLine();
                    long idEliminar = Integer.parseInt(idRespostaEliminar);

                    Empleat empleatEliminar = empleatsDAO.obtenirEmpleat(idEliminar);
                    if (empleatEliminar != null) {
                        empleatsDAO.eliminarEmpleat(empleatEliminar);
                        System.out.println("Empleat eliminat correctament!");
                    } else {
                        System.out.println("No s'ha trobat un empleat amb aquest ID.");
                    }
                    break;

                case 6:
                    return;
            }
        }
    }

    private static void gestionarClient(ClientDAO clientDAO) {
        while (true) {
            mostraMenu2("Client");
            String resposta = Utilitats.readLine();
            int seleccion = Utilitats.triaOpcio(resposta, 5);
            System.out.println();

            switch (seleccion) {
                case 1:
                    // Crear Client
                    String nomClient = Utilitats.nom(1);
                    String telefonClient = Utilitats.telClient();
                    System.out.print("Introdueix el correu: ");
                    String correuClient = Utilitats.readLine();
                    Client clientNuevo = new Client(nomClient, telefonClient, correuClient);

                    System.out.println("Vols afegir una Reserva?");
                    String cadena = Utilitats.readLine();

                    if (Utilitats.confirmador(cadena)) {
                        Reserva reserva = DemanarReserva();
                        reserva.setClient(clientNuevo); // 🔹 Asignar cliente a la reserva
                        clientNuevo.addReserva(reserva); // 🔹 Añadir reserva al cliente
                    }

                    clientDAO.crearClient(clientNuevo); // 🔹 Se guarda automáticamente con la reserva

                    System.out.println("Client creat correctament!");
                    break;

                case 2:
                    System.out.print("Introdueix l'ID del Client: ");
                    String idResposta = Utilitats.readLine();
                    long idBuscar = Integer.parseInt(idResposta);

                    Client clientObtingut = clientDAO.obtenirClient(idBuscar);
                    if (clientObtingut != null) {
                        System.out.println("Client trobat: ");
                        System.out.println("Nom: " + clientObtingut.getNom());
                        System.out.println("Email: " + clientObtingut.getCorreo());
                        System.out.println("Teléfon: " + clientObtingut.getTelefon());
                    } else {
                        System.out.println("No s'ha trobat un client amb aquest ID.");
                    }
                    break;
                case 3:
                    System.out.println("Llistat de clients:");
                    List<Client> clients = clientDAO.obtenirTotsClients();
                    if (clients.isEmpty()) {
                        System.out.println("No s'ha trobat cap client.");
                    } else {
                        for (Client c : clients) {
                            System.out.println("Client trobat: ");
                            System.out.println("Nom: " + c.getNom());
                            System.out.println("Email: " + c.getCorreo());
                            System.out.println("Teléfon: " + c.getTelefon());
                            for (Reserva r : c.getReservas()) {
                                if (r.getClient().getIdClient() == c.getIdClient()) {
                                    System.out.println("Hora: " + r.getFecha_hora());
                                }

                            }
                        }
                    }
                    break;

                case 4:
                    // Actualizar Client
                    System.out.print("Introdueix l'ID del client a actualitzar: ");
                    long idClientActualizar = Integer.parseInt(Utilitats.readLine());
                    Client clientAActualizar = clientDAO.obtenirClient(idClientActualizar);

                    if (clientAActualizar != null) {

                        String nouNomClient = Utilitats.nom(1);

                        String nouTelefonClient = Utilitats.telClient();

                        System.out.print("Introdueix el nou correu: ");
                        String nouCorreuClient = Utilitats.readLine();

                        clientAActualizar.setNom(nouNomClient);
                        clientAActualizar.setTelefon(nouTelefonClient);
                        clientAActualizar.setCorreo(nouCorreuClient);

                        clientDAO.actualitzarClient(clientAActualizar);
                        System.out.println("Client actualitzat correctament!");
                    } else {
                        System.out.println("No s'ha trobat un client amb aquest ID.");
                    }
                    break;

                case 5:
                    // Eliminar Client
                    System.out.print("Introdueix l'ID del client a eliminar: ");
                    long idClientEliminar = Integer.parseInt(Utilitats.readLine());
                    Client clientAEliminar = clientDAO.obtenirClient(idClientEliminar);

                    if (clientAEliminar != null) {
                        clientDAO.eliminarClient(clientAEliminar);
                        System.out.println("Client eliminat correctament!");
                    } else {
                        System.out.println("No s'ha trobat un client amb aquest ID.");
                    }
                    break;

                case 6:
                    return;
            }

        }
    }

    private static void gestionarRestaurant(RestaurantDAO restaurantDAO) {
        while (true) {
            mostraMenu2("Restaurant");
            String resposta = Utilitats.readLine();
            int seleccion = Utilitats.triaOpcio(resposta, 5);
            System.out.println();

            switch (seleccion) {
                case 1:

                    restaurantDAO.crearRestaurant(DemanarRestaurant());
                    System.out.println("Restaurant creat correctament!");
                    break;

                case 2:
                    System.out.print("Introdueix l'ID del Restaurant: ");
                    long idResposta = Integer.parseInt(Utilitats.readLine());

                    Restaurant restaurantObtingut = restaurantDAO.obtenirRestaurant(idResposta);
                    if (restaurantObtingut != null) {
                        System.out.println("Nom: " + restaurantObtingut.getNom());
                        System.out.println("nº Taules: " + restaurantObtingut.getCapacitat());
                    } else {
                        System.out.println("No s'ha trobat un restaurant amb aquest ID.");
                    }
                    break;

                case 3:
                    System.out.println("Llistat de restaurants:");
                    List<Restaurant> restaurants = restaurantDAO.obtenirTotsRestaurants();
                    if (restaurants.isEmpty()) {
                        System.out.println("No s'ha trobat cap restaurant.");
                    } else {
                        for (Restaurant r : restaurants) {
                            System.out.println("Nom: " + r.getNom());
                            System.out.println("nº Taules: " + r.getCapacitat());
                        }
                    }
                    break;

                case 4:
                    // Actualitzar Restaurant
                    System.out.print("Introdueix l'ID del restaurant a actualitzar: ");
                    long idRestaurantActualizar = Integer.parseInt(Utilitats.readLine());
                    Restaurant restaurantAActualizar = restaurantDAO.obtenirRestaurant(idRestaurantActualizar);

                    if (restaurantAActualizar != null) {
                        String nouNomRestaurant = Utilitats.nom(1);

                        System.out.print("Introdueix la nova capacitat: ");
                        int novaCapacitat = Integer.parseInt(Utilitats.readLine());

                        restaurantAActualizar.setNom(nouNomRestaurant);
                        restaurantAActualizar.setCapacitat(novaCapacitat);

                        restaurantDAO.actualitzarRestaurant(restaurantAActualizar);
                        System.out.println("Restaurant actualitzat correctament!");
                    } else {
                        System.out.println("No s'ha trobat un restaurant amb aquest ID.");
                    }
                    break;

                case 5:
                    // Eliminar Restaurant
                    System.out.print("Introdueix l'ID del restaurant a eliminar: ");
                    long idRestaurantEliminar = Integer.parseInt(Utilitats.readLine());
                    Restaurant restaurantAEliminar = restaurantDAO.obtenirRestaurant(idRestaurantEliminar);

                    if (restaurantAEliminar != null) {
                        restaurantDAO.eliminarRestaurant(restaurantAEliminar);
                        System.out.println("Restaurant eliminat correctament!");
                    } else {
                        System.out.println("No s'ha trobat un restaurant amb aquest ID.");
                    }
                    break;

                case 6:
                    return;
            }
        }
    }

    private static void gestionarReserva(ReservaDAO reservaDAO, ClientDAO clientDAO) {
        while (true) {
            mostraMenu2("Reserva");
            String resposta = Utilitats.readLine();
            int seleccion = Utilitats.triaOpcio(resposta, 5); // Ajustar el límite

            switch (seleccion) {
                case 1:
                    Reserva novaReserva = DemanarReserva();
                    if (novaReserva != null) {
                        reservaDAO.crearReserva(novaReserva);
                        System.out.println("Reserva creada correctament!");
                    }
                    break;

                case 2:
                    System.out.print("Introdueix l'ID de la Reserva: ");
                    long idReserva = Integer.parseInt(Utilitats.readLine());

                    Reserva reservaObtinguda = reservaDAO.obtenirReserva(idReserva);
                    if (reservaObtinguda != null) {
                        System.out.println("Reserva trobada: ");
                        System.out.println("ID: " + reservaObtinguda.getIdReserva());
                        System.out.println("Nom del Client: " + reservaObtinguda.getClient().getNom());
                        System.out.println("Data: " + reservaObtinguda.getFecha_hora());
                    } else {
                        System.out.println("No s'ha trobat una reserva amb aquest ID.");
                    }
                    break;

                case 3:
                    System.out.println("Llistat de reserves:");
                    List<Reserva> reserves = reservaDAO.obtenirTotesReserves();
                    if (reserves.isEmpty()) {
                        System.out.println("No s'ha trobat cap reserva.");
                    } else {
                        for (Reserva r : reserves) {
                            System.out.println("ID Reserva: " + r.getIdReserva());
                            System.out.println("Client: " + r.getClient().getNom());
                            System.out.println("Data: " + r.getFecha_hora());
                        }
                    }
                    break;

                case 4:
                    System.out.print("Introdueix l'ID de la reserva a actualitzar: ");
                    long idReservaActualizar = Integer.parseInt(Utilitats.readLine());
                    Reserva reservaAActualizar = reservaDAO.obtenirReserva(idReservaActualizar);

                    if (reservaAActualizar != null) {
                        System.out.print("Introdueix la nova data i hora (YYYY-MM-DD HH:MM): ");
                        LocalDateTime novaFechaHora = LocalDateTime.parse(Utilitats.readLine(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                        reservaAActualizar.setFecha_hora(novaFechaHora);

                        reservaDAO.actualitzarReserva(reservaAActualizar);
                        System.out.println("Reserva actualitzada correctament!");
                    } else {
                        System.out.println("No s'ha trobat una reserva amb aquest ID.");
                    }
                    break;

                case 5:
                    System.out.print("Introdueix l'ID de la reserva a eliminar: ");
                    long idReservaEliminar = Integer.parseInt(Utilitats.readLine());
                    Reserva reservaAEliminar = reservaDAO.obtenirReserva(idReservaEliminar);

                    if (reservaAEliminar != null) {
                        reservaDAO.eliminarReserva(reservaAEliminar);
                        System.out.println("Reserva eliminada correctament!");
                    } else {
                        System.out.println("No s'ha trobat una reserva amb aquest ID.");
                    }
                    break;

                case 6:
                    return;
            }
        }
    }

    private static Restaurant DemanarRestaurant() {
        String nomRestaurant = Utilitats.nom(2);
        System.out.print("Introdueix la capacitat(nº Taules): ");
        int capacitatRestaurant = Integer.parseInt(Utilitats.readLine());

        Restaurant restaurantNou = new Restaurant(nomRestaurant, capacitatRestaurant);
        return restaurantNou;
    }

    private static Reserva DemanarReserva() {
        System.out.print("Introdueix l'Nº de la taula: ");
        int idMesa = Integer.parseInt(Utilitats.readLine());
        System.out.print("Introdueix la data i hora (YYYY-MM-DD HH:MM): ");
        LocalDateTime fechaHora = LocalDateTime.parse(Utilitats.readLine(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return new Reserva(idMesa, fechaHora);
    }
}