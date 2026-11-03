package app;

import modelo.clinica.*;
import modelo.mascotas.*;
import modelo.personas.*;
import modelo.servicios.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que ejecuta el menú de consola del sistema PetCare.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Clinica clinica = new Clinica("PetCare Coatzacoalcos");
        List<Duenio> duenios = new ArrayList<>();
        List<Mascota> mascotas = new ArrayList<>();
        
        int opcion = 0;
        do {
            System.out.println("\n=== CLINICA VETERINARIA PETCARE ===");
            System.out.println("1. Registrar dueño y mascota (Perro, Gato o Ave)");
            System.out.println("2. Registrar veterinario y asignarlo a la clínica");
            System.out.println("3. Agendar una cita");
            System.out.println("4. Atender o cancelar una cita");
            System.out.println("5. Generar comprobante de una cita atendida");
            System.out.println("6. Consultar el historial clínico de una mascota");
            System.out.println("7. Listar todas las mascotas y su sonido (Polimorfismo)");
            System.out.println("8. Ver el total cobrado por veterinario");
            System.out.println("9. Dar de baja una mascota");
            System.out.println("10. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.print("Nombre del dueño: ");
                        String nDuenio = scanner.nextLine();
                        System.out.print("Apellido del dueño: ");
                        String aDuenio = scanner.nextLine();
                        System.out.print("Teléfono: ");
                        String tel = scanner.nextLine();
                        System.out.print("Correo: ");
                        String correo = scanner.nextLine();
                        System.out.print("Dirección: ");
                        String dir = scanner.nextLine();
                        Duenio duenio = new Duenio(nDuenio, aDuenio, tel, correo, dir);
                        duenios.add(duenio);

                        System.out.print("Nombre de la mascota: ");
                        String nMascota = scanner.nextLine();
                        System.out.print("Edad: ");
                        int edad = Integer.parseInt(scanner.nextLine());
                        System.out.print("Peso (kg): ");
                        double peso = Double.parseDouble(scanner.nextLine());

                        System.out.print("Tipo de mascota (1. Perro, 2. Gato, 3. Ave Exótica): ");
                        int tipoMascota = Integer.parseInt(scanner.nextLine());

                        Mascota mascota = null;
                        if (tipoMascota == 1) {
                            System.out.print("Raza: ");
                            String raza = scanner.nextLine();
                            System.out.print("Nivel de energía: ");
                            String energia = scanner.nextLine();
                            mascota = new Perro(nMascota, edad, peso, duenio, raza, energia);
                        } else if (tipoMascota == 2) {
                            System.out.print("¿Es doméstico? (true/false): ");
                            boolean domestico = Boolean.parseBoolean(scanner.nextLine());
                            mascota = new Gato(nMascota, edad, peso, duenio, domestico);
                        } else if (tipoMascota == 3) {
                            System.out.print("Longitud de alas (cm): ");
                            double alas = Double.parseDouble(scanner.nextLine());
                            mascota = new AveExotica(nMascota, edad, peso, duenio, alas);
                        } else {
                            System.out.println("Tipo inválido. Se cancela el registro de la mascota.");
                            break;
                        }
                        mascotas.add(mascota);
                        System.out.println("¡Dueño y mascota registrados con éxito!");
                        break;

                    case 2:
                        System.out.print("Nombre del veterinario: ");
                        String nVet = scanner.nextLine();
                        System.out.print("Apellido: ");
                        String aVet = scanner.nextLine();
                        System.out.print("Teléfono: ");
                        String tVet = scanner.nextLine();
                        System.out.print("Correo: ");
                        String cVet = scanner.nextLine();
                        System.out.print("Cédula profesional: ");
                        String cedula = scanner.nextLine();
                        System.out.print("Especialidad: ");
                        String esp = scanner.nextLine();

                        Veterinario vet = new Veterinario(nVet, aVet, tVet, cVet, cedula, esp);
                        clinica.agregarVeterinario(vet);
                        System.out.println("¡Veterinario registrado y asignado a la clínica!");
                        break;

                    case 3:
                        if (mascotas.isEmpty() || clinica.getVeterinarios().isEmpty()) {
                            System.out.println("Error: Debe registrar al menos una mascota y un veterinario antes de agendar citas.");
                            break;
                        }
                        System.out.println("Seleccione la mascota:");
                        for (int i = 0; i < mascotas.size(); i++) {
                            System.out.println((i + 1) + ". " + mascotas.get(i).getNombre());
                        }
                        int mIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        Mascota mSeleccionada = mascotas.get(mIndex);

                        System.out.println("Seleccione el veterinario:");
                        List<Veterinario> vets = clinica.getVeterinarios();
                        for (int i = 0; i < vets.size(); i++) {
                            System.out.println((i + 1) + ". " + vets.get(i).getNombre() + " (" + vets.get(i).getEspecialidad() + ")");
                        }
                        int vIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        Veterinario vSeleccionado = vets.get(vIndex);

                        System.out.println("Seleccione el consultorio (1 o 2):");
                        List<Consultorio> cons = clinica.getConsultorios();
                        for (Consultorio con : cons) {
                            System.out.println("Consultorio " + con.getNumero() + " - Disponible: " + con.isDisponible());
                        }
                        int cNum = Integer.parseInt(scanner.nextLine());
                        Consultorio cSeleccionado = cons.get(cNum - 1);

                        if (!cSeleccionado.isDisponible()) {
                            System.out.println("Error: El consultorio seleccionado no está disponible.");
                            break;
                        }

                        System.out.print("Fecha y hora de la cita (ej. 2026-11-05 10:00): ");
                        String fechaHora = scanner.nextLine();

                        // Validación de regla de negocio: un veterinario no puede tener dos citas a la misma hora
                        for (Cita citaExistente : clinica.getCitas()) {
                            if (citaExistente.getVeterinario().equals(vSeleccionado) &&
                                citaExistente.getFechaHora().equals(fechaHora) &&
                                !citaExistente.getEstado().equals("CANCELADA")) {
                                throw new IllegalArgumentException("El veterinario ya tiene una cita programada a esta misma hora.");
                            }
                        }

                        System.out.println("Seleccione el servicio (1. Consulta General, 2. Vacunación, 3. Cirugía): ");
                        int tipoServicio = Integer.parseInt(scanner.nextLine());
                        Facturable servicio = null;

                        if (tipoServicio == 1) {
                            System.out.print("Ingrese la tarifa base: ");
                            double tarifa = Double.parseDouble(scanner.nextLine());
                            servicio = new ConsultaGeneral(tarifa);
                        } else if (tipoServicio == 2) {
                            System.out.print("Ingrese el costo de la vacuna: ");
                            double costoVac = Double.parseDouble(scanner.nextLine());
                            servicio = new Vacunacion(costoVac);
                        } else if (tipoServicio == 3) {
                            System.out.print("Ingrese la complejidad (Baja / Alta): ");
                            String comp = scanner.nextLine();
                            System.out.print("Ingrese las horas de quirófano: ");
                            double horas = Double.parseDouble(scanner.nextLine());
                            servicio = new Cirugia(comp, horas);
                        } else {
                            System.out.println("Servicio no válido.");
                            break;
                        }

                        Cita nuevaCita = new Cita(fechaHora, mSeleccionada, vSeleccionado, cSeleccionado, servicio);
                        clinica.agregarCita(nuevaCita);
                        cSeleccionado.setDisponible(false);
                        System.out.println("¡Cita agendada con éxito!");
                        break;

                    case 4:
                        List<Cita> listaCitas = clinica.getCitas();
                        if (listaCitas.isEmpty()) {
                            System.out.println("No hay citas registradas en el sistema.");
                            break;
                        }
                        System.out.println("Listado de citas:");
                        for (int i = 0; i < listaCitas.size(); i++) {
                            Cita ci = listaCitas.get(i);
                            System.out.println((i + 1) + ". Mascota: " + ci.getMascota().getNombre() + 
                                               " | Vet: " + ci.getVeterinario().getNombre() + 
                                               " | Estado: " + ci.getEstado() + " | Fecha: " + ci.getFechaHora());
                        }
                        System.out.print("Seleccione el número de la cita a modificar: ");
                        int indexCita = Integer.parseInt(scanner.nextLine()) - 1;
                        Cita citaSeleccionada = listaCitas.get(indexCita);

                        System.out.print("Ingrese el nuevo estado (ATENDIDA / CANCELADA): ");
                        String nuevoEstado = scanner.nextLine().toUpperCase();
                        
                        // Validará la regla de negocio interna en Cita.java
                        citaSeleccionada.setEstado(nuevoEstado);
                        
                        if (nuevoEstado.equals("ATENDIDA") || nuevoEstado.equals("CANCELADA")) {
                            citaSeleccionada.getConsultorio().setDisponible(true);
                            if (nuevoEstado.equals("ATENDIDA")) {
                                citaSeleccionada.getMascota().getHistorialClinico().agregarEntrada(
                                    "Cita atendida el " + citaSeleccionada.getFechaHora() + 
                                    " con Dr/Dra. " + citaSeleccionada.getVeterinario().getNombre() +
                                    " - Costo: $" + citaSeleccionada.getServicio().calcularCosto()
                                );
                            }
                        }
                        System.out.println("¡Cita actualizada correctamente!");
                        break;

                    case 5:
                        List<Cita> citasClinica = clinica.getCitas();
                        System.out.println("\n--- COMPROBANTES DE CITAS ATENDIDAS ---");
                        boolean hayAtendidas = false;
                        for (Cita ca : citasClinica) {
                            if (ca.getEstado().equals("ATENDIDA")) {
                                hayAtendidas = true;
                                System.out.println("----------------------------------------");
                                System.out.println("COMPROBANTE DE SERVICIO - VETERINARIA PETCARE");
                                System.out.println("Mascota: " + ca.getMascota().getNombre());
                                System.out.println("Dueño: " + ca.getMascota().getDuenio().getNombre() + " " + ca.getMascota().getDuenio().getApellido());
                                System.out.println("Veterinario: " + ca.getVeterinario().getNombre() + " " + ca.getVeterinario().getApellido());
                                System.out.println("Fecha y Hora: " + ca.getFechaHora());
                                System.out.println("Total Cobrado: $" + ca.getServicio().calcularCosto());
                                System.out.println("----------------------------------------");
                            }
                        }
                        if (!hayAtendidas) {
                            System.out.println("No hay citas atendidas registradas para generar comprobantes.");
                        }
                        break;

                    case 6:
                        if (mascotas.isEmpty()) {
                            System.out.println("No hay mascotas registradas.");
                            break;
                        }
                        System.out.println("Seleccione la mascota:");
                        for (int i = 0; i < mascotas.size(); i++) {
                            System.out.println((i + 1) + ". " + mascotas.get(i).getNombre());
                        }
                        int hmIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        Mascota mHistorial = mascotas.get(hmIndex);

                        System.out.println("\n--- HISTORIAL CLÍNICO: " + mHistorial.getNombre().toUpperCase() + " ---");
                        List<String> entradas = mHistorial.getHistorialClinico().getConsultas();
                        if (entradas.isEmpty()) {
                            System.out.println("El historial clínico está vacío.");
                        } else {
                            for (String entrada : entradas) {
                                System.out.println("- " + entrada);
                            }
                        }
                        break;

                    case 7:
                        System.out.println("\n--- DEMOSTRACIÓN DE POLIMORFISMO (SONIDOS) ---");
                        if (mascotas.isEmpty()) {
                            System.out.println("No hay mascotas registradas.");
                        } else {
                            for (Mascota m : mascotas) {
                                System.out.println(m);
                                m.hacerSonido(); // Invoca el método polimórfico de cada hija
                            }
                        }
                        break;

                    case 8:
                        System.out.println("\n--- TOTAL COBRADO POR VETERINARIO ---");
                        List<Veterinario> vetsList = clinica.getVeterinarios();
                        if (vetsList.isEmpty()) {
                            System.out.println("No hay veterinarios registrados.");
                            break;
                        }
                        for (Veterinario v : vetsList) {
                            double totalVet = 0;
                            for (Cita c : clinica.getCitas()) {
                                if (c.getVeterinario().equals(v) && c.getEstado().equals("ATENDIDA")) {
                                    totalVet += c.getServicio().calcularCosto();
                                }
                            }
                            System.out.println("Dr/Dra. " + v.getNombre() + " " + v.getApellido() + 
                                               " | Total cobrado: $" + totalVet);
                        }
                        break;

                    case 9:
                        if (mascotas.isEmpty()) {
                            System.out.println("No hay mascotas para dar de baja.");
                            break;
                        }
                        System.out.println("Seleccione la mascota a dar de baja:");
                        for (int i = 0; i < mascotas.size(); i++) {
                            System.out.println((i + 1) + ". " + mascotas.get(i).getNombre());
                        }
                        int bajaIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        mascotas.remove(bajaIndex);
                        System.out.println("Mascota eliminada del sistema con éxito.");
                        System.out.println("(Nota: Al eliminar la mascota, su historial clínico asociado por composición queda sin referencias y es recolectado por el Garbage Collector).");
                        break;

                    case 10:
                        System.out.println("Saliendo del sistema PetCare. ¡Hasta pronto!");
                        break;

                    default:
                        System.out.println("Opción no válida. Seleccione un número del 1 al 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error de formato: Ingrese un número válido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error de regla de negocio: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: El elemento seleccionado no existe en la lista.");
            }
        } while (opcion != 10);

        scanner.close();
    }
}