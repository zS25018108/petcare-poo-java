# Parte 1: Análisis y Diseño con UML - Sistema PetCare

## 1.1 Identificación de Clases y Objetos

### Tabla de Análisis de Clases Candidatas

| Clase candidata | Tipo (concreta, abstracta o interfaz) | Atributos principales | Responsabilidades (métodos con constructores explícitos) | Justificación |
| :--- | :--- | :--- | :--- | :--- |
| `Persona` | Abstracta | `nombre`, `apellido`, `telefono`, `correo` | `+ Persona(nombre: String, apellido: String, telefono: String, correo: String)`<br>`+ getNombre(): String`<br>`+ setNombre(nombre: String): void` | Clase base que encapsula los atributos y métodos comunes a todas las personas en el sistema. |
| `Duenio` | Concreta | `direccion` | `+ Duenio(nombre: String, apellido: String, telefono: String, correo: String, direccion: String)`<br>`+ getDireccion(): String`<br>`+ toString(): String` | Extiende de `Persona`. Modela al dueño del paciente con su domicilio para contacto. |
| `Veterinario` | Concreta | `cedulaProfesional`, `especialidad` | `+ Veterinario(nombre: String, apellido: String, telefono: String, correo: String, cedula: String, esp: String)`<br>`+ getCedulaProfesional(): String`<br>`+ toString(): String` | Extiende de `Persona`. Modela al médico veterinario que atiende las consultas. |
| `Recepcionista` | Concreta | `turno` | `+ Recepcionista(nombre: String, apellido: String, telefono: String, correo: String, turno: String)`<br>`+ getTurno(): String`<br>`+ setTurno(turno: String): void` | Extiende de `Persona`. Modela al personal administrativo encargado de la agenda. |
| `Mascota` | Abstracta | `nombre`, `edad`, `peso`, `duenio`, `historialClinico` | `+ Mascota(nombre: String, edad: int, peso: double, duenio: Duenio)`<br>`+ hacerSonido(): void {abstract}`<br>`+ setEdad(edad: int): void`<br>`+ setPeso(peso: double): void` | Clase base para los animales. Contiene validaciones de negocio e historial. |
| `Perro` | Concreta | `raza`, `nivelEnergia` | `+ Perro(nombre: String, edad: int, peso: double, duenio: Duenio, raza: String, nivelEnergia: String)`<br>`+ hacerSonido(): void` (@Override) | Extiende de `Mascota`. Incorpora características específicas de perros. |
| `Gato` | Concreta | `esDomestico` | `+ Gato(nombre: String, edad: int, peso: double, duenio: Duenio, esDomestico: boolean)`<br>`+ hacerSonido(): void` (@Override) | Extiende de `Mascota`. Incorpora el atributo de comportamiento doméstico. |
| `AveExotica` | Concreta | `longitudAlas` | `+ AveExotica(nombre: String, edad: int, peso: double, duenio: Duenio, longitudAlas: double)`<br>`+ hacerSonido(): void` (@Override) | Extiende de `Mascota`. Modela aves registrando la envergadura de sus alas. |
| `HistorialClinico` | Concreta | `idHistorial`, `consultas` | `+ HistorialClinico()`<br>`+ agregarConsulta(obs: String): void`<br>`+ mostrarHistorial(): String` | Administra las consultas médicas. Vive en composición estricta con la mascota. |
| `Consultorio` | Concreta | `numero`, `disponible` | `+ Consultorio(numero: int)`<br>`+ isDisponible(): boolean`<br>`+ setDisponible(d: boolean): void` | Espacio físico para las citas. Existe por composición dentro de la clínica. |
| `Clinica` | Concreta | `nombre`, `consultorios`, `veterinarios` | `+ Clinica(nombre: String)`<br>`+ agregarVeterinario(v: Veterinario): void`<br>`+ agregarConsultorio(c: Consultorio): void` | Entidad central que administra la infraestructura y al equipo médico. |
| `Cita` | Concreta | `fechaHora`, `estado`, `mascota`, `veterinario`, `consultorio` | `+ Cita(m: Mascota, v: Veterinario, c: Consultorio, f: LocalDateTime, s: Facturable)`<br>`+ atender(): void`<br>`+ cancelar(): void` | Asocia mascota, veterinario y consultorio en un tiempo determinado. |
| `Facturable` | Interfaz | N/A | `+ calcularCosto(): double` | Interfaz polimórfica que garantiza la implementación del cálculo del costo. |
| `ConsultaGeneral` | Concreta | `tarifaBase` | `+ ConsultaGeneral(tarifaBase: double)`<br>`+ calcularCosto(): double` (@Override) | Implementa `Facturable`. Cobro fijo por la atención médica general. |
| `Vacunacion` | Concreta | `costoVacuna` | `+ Vacunacion(costoVacuna: double)`<br>`+ calcularCosto(): double` (@Override) | Implementa `Facturable`. Suma la tarifa base y el costo del biológico. |
| `Cirugia` | Concreta | `complejidad`, `horasQuirofano` | `+ Cirugia(complejidad: String, horasQuirofano: double)`<br>`+ calcularCosto(): double` (@Override) | Implementa `Facturable`. Calcula el costo según el riesgo y el tiempo transcurrido. |

---

### Objetos de Ejemplo

1. **`Duenio`**: `nombre` = "Roman", `apellido` = "Perez", `telefono` = "9211234567", `correo` = "rmnprz13@gmail.com", `direccion` = "Av. Universidad 1200".
2. **`Perro`**: `nombre` = "Firulais", `edad` = 3, `peso` = 14.5, `duenio` = (instancia de Roman Perez), `raza` = "Pitbull", `nivelEnergia` = "Alto".
3. **`Cita`**: `fechaHora` = "2026-11-05T10:00", `estado` = "PROGRAMADA", `mascota` = (instancia de Firulais), `veterinario` = (instancia de Dr. Ramírez), `consultorio` = (Consultorio 1).

---

### Clases Descartadas

* **`BoutiqueDeMascotas`**: Se descartó debido a que el alcance del sistema está estrictamente acotado a la atención médica veterinaria y agendamiento de citas, no a la venta de accesorios ni comercio minorista.
* **`HistorialDePagos`**: Se descartó porque la gestión de cobros y comprobantes se resuelve de forma directa a través de las consultas a la entidad `Cita` e implementación de la interfaz `Facturable`, evitando complejidad innecesaria.

---

## 1.2 Diagrama de Clases

![Diagrama de Clases Uml](diagramas/diagrama_clases.png)

---

## 1.3 Justificación de Relaciones

* **Asociación (`Cita` con `Mascota`, `Veterinario` y `Consultorio`)**:
  Se utiliza asociación directa porque `Cita` actúa como un conector temporal independiente. Si una `Cita` se elimina o cancela, la `Mascota`, el `Veterinario` y el `Consultorio` continúan existiendo de manera autónoma en el sistema.

* **Agregación (`Clinica` y `Veterinario`)**:
  Es una relación de agregación (todo-parte débil). Los veterinarios son contratados por la clínica, pero su ciclo de vida no depende de ella. Si la clínica cierra o deja de existir, el médico veterinario conserva su existencia física e institucional y puede laborar en otro centro.

* **Composición (`Mascota` con `HistorialClinico`)**:
  Es una relación de composición estricta (todo-parte fuerte). El `HistorialClinico` se crea dentro del constructor de la `Mascota` y no se comparte con ninguna otra entidad. Si la mascota se da de baja del sistema, su historial clínico completo se elimina permanentemente de la memoria junto con ella.

* **Composición (`Clinica` con `Consultorio`)**:
  Es una relación de composición estricta. Los consultorios representan espacios físicos creados y administrados exclusivamente por la `Clinica`. No tienen razón de ser ni existencia independiente sin la clínica contenedora.