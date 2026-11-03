# Sistema de Gestión Veterinaria - PetCare

Sistema de consola desarrollado en Java orientada a objetos para la administración integral de una clínica veterinaria.

## Tecnologías Utilizadas
- **Lenguaje:** Java 25.0.5.0.2+1
- **Modelado UML:** Draw.io
- **IDE / Entorno:** Visual Studio Code (GitHub Codespaces)

## Modelo de la Solución
El sistema se organiza en torno a principios solid y encapsulamiento estricto. Se implementó herencia en `Persona` (`Duenio`, `Veterinario`, `Recepcionista`) y `Mascota` (`Perro`, `Gato`, `AveExotica`), polimorfismo mediante la interfaz `Facturable` y composición para el ciclo de vida de `HistorialClinico` y `Consultorio`.

## Estructura del Proyecto
- `src/app/`: Contiene la clase de ejecución principal (`Main.java`).
- `src/modelo/personas/`: Contiene clases de actores del sistema.
- `src/modelo/mascotas/`: Contiene la jerarquía y gestión biológica de animales.
- `src/modelo/servicios/`: Contiene los cálculos de cobro basados en interfaces.
- `src/modelo/clinica/`: Contiene la lógica de consultorios, citas y administración global.

## Instalación y Ejecución
1. Clona el repositorio:
   ```bash
   git clone [https://github.com/zS25018108/petcare-poo-java.git](https://github.com/zS25018108/petcare-poo-java.git)