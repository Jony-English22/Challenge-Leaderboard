# Challenge Leaderboard

Un sistema de gestión de retos y competiciones desarrollado en Java que permite crear, gestionar y rankear participantes en desafíos programáticos a través de una interfaz de línea de comandos (CLI) intuitiva.

## 📋 Descripción General

Challenge Leaderboard es una aplicación de consola diseñada para organizar y administrar retos de programación o desafíos competitivos. El sistema permite registrar participantes, crear retos con puntuaciones y tiempos límite, gestionar resultados y visualizar rankings en tiempo real. Los datos se persisten automáticamente en formato JSON para mantener la consistencia entre sesiones.

## 🎯 Objetivo

Facilitar la organización de competiciones de programación y retos técnicos, proporcionando una herramienta simple pero efectiva para:
- Gestionar participantes y retos
- Realizar seguimiento de puntuaciones
- Mantener rankings actualizados
- Almacenar datos de manera persistente

## ✨ Funcionalidades Principales

### Gestión de Retos
- **Crear retos**: Define desafíos con nombre, descripción, puntuación máxima y tiempo límite
- **Listar retos**: Visualiza todos los retos disponibles con su estado actual
- **Ver detalles**: Consulta información completa de un reto específico
- **Eliminar retos**: Remueve retos del sistema cuando sea necesario

### Gestión de Participantes
- **Registrar participantes**: Agrega nuevos competidores al sistema
- **Listar participantes**: Muestra todos los participantes registrados
- **Ver perfil**: Consulta detalles de un participante específico
- **Eliminar participantes**: Remueve participantes del sistema

### Sistema de Ranking
- **Ranking general**: Visualiza la clasificación de participantes por puntuación total
- **Puntuación acumulada**: Los puntos se calculan basándose en los resultados de los retos

### Interfaz de Usuario
- **CLI interactiva**: Interfaz de comandos estilo shell con prompts claros
- **Ayuda integrada**: Sistema de ayuda para recordar comandos disponibles
- **Mensajes informativos**: Retroalimentación clara sobre operaciones realizadas

## 🛠 Tecnologías Utilizadas

- **Lenguaje**: Java 17
- **Gestión de dependencias**: Maven 3.9+
- **Persistencia de datos**: JSON (Gson library)
- **Arquitectura**: Modelo-Vista-Controlador (MVC) adaptado para consola

## 📁 Estructura del Proyecto

```
Challenge-Leaderboard/
├── src/
│   ├── Main.java                 # Punto de entrada de la aplicación
│   ├── model/                    # Modelos de datos
│   │   ├── EstadoReto.java       # Estados posibles de un reto
│   │   ├── Participante.java     # Modelo de participante
│   │   ├── Resultado.java        # Modelo de resultado
│   │   └── Reto.java             # Modelo de reto
│   ├── service/                  # Lógica de negocio
│   │   ├── GestorDatos.java      # Gestión de persistencia JSON
│   │   ├── GestorParticipantes.java # Lógica de participantes
│   │   ├── GestorRetos.java      # Lógica de retos
│   │   └── TimeReto.java         # Temporizador para retos
│   └── ui/                       # Interfaz de usuario
│       ├── cli/
│       │   ├── CommandHandler.java # Procesamiento de comandos
│       │   └── InputReader.java   # Lectura de entrada usuario
│       ├── menu/
│       │   └── RankingShell.java  # Shell principal
│       └── view/
│           ├── ConsoleView.java   # Vista de consola
│           └── Formatter.java     # Formateo de salida
├── data/
│   └── datos.json                # Archivo de datos persistentes
├── target/                       # Archivos compilados (generado)
├── pom.xml                       # Configuración Maven
└── README.md                     # Este archivo
```

## 🚀 Instalación y Ejecución

### Prerrequisitos
- Java 17 o superior instalado
- Maven 3.6+ instalado

### Instalación
1. Clona el repositorio:
   ```bash
   git clone <url-del-repositorio>
   cd Challenge-Leaderboard
   ```

2. Compila el proyecto:
   ```bash
   mvn clean compile
   ```

### Ejecución
Ejecuta la aplicación con Maven:
```bash
mvn exec:java
```

O compila y ejecuta manualmente:
```bash
mvn clean package
java -cp target/classes Main
```

## 💡 Ejemplos de Uso

### Sesión de ejemplo:
```
Bienvenido al Sistema de Ranking de Retos

ranking> crear "Algoritmo de Ordenamiento" "Implementar quicksort" 50 45
✅ Reto creado: Algoritmo de Ordenamiento

ranking> participante agregar "Ana García"
✅ Participante creado: Ana García

ranking> participante agregar "Carlos López"
✅ Participante creado: Carlos López

ranking> listar
=== RETOS DISPONIBLES ===
ID  Nombre                    Puntos  Tiempo  Estado
1   Algoritmo de Ordenamiento   50      45     PENDIENTE

ranking> listar participantes
=== PARTICIPANTES REGISTRADOS ===
ID    Nombre
RK001 Ana García
RK002 Carlos López

ranking> ranking
=== RANKING GENERAL ===
Posición  Participante  Puntos
1         Ana García     0
2         Carlos López   0

ranking> ayuda
=== COMANDOS DISPONIBLES ===
crear <nombre> <puntos> <tiempo>     - Crear nuevo reto
listar [retos|participantes]         - Listar elementos
ver <id>                             - Ver detalles de reto
ver participante <id>                - Ver detalles de participante
eliminar reto <id>                   - Eliminar reto
eliminar participante <id>           - Eliminar participante
participante agregar <nombre>        - Registrar participante
ranking                              - Mostrar ranking general
ayuda                                - Mostrar esta ayuda
salir                                - Salir de la aplicación

ranking> salir
Sesión terminada
```

## 🔮 Mejoras Futuras

### Integración con GitHub
**Propósito**: Sincronizar datos de retos y rankings con repositorios de GitHub para compartir resultados públicamente y facilitar colaboraciones.

**Valor**:
- **Transparencia**: Los rankings y resultados serían visibles públicamente
- **Colaboración**: Participantes podrían contribuir con nuevos retos vía pull requests
- **Historial**: Git proporcionaría un registro inmutable de cambios en rankings
- **Integración CI/CD**: Automatizar actualizaciones de rankings tras completar retos

**Implementación propuesta**:
- API de GitHub para crear issues/PRs con resultados
- Webhooks para actualizar rankings automáticamente
- GitHub Actions para validar y procesar submissions
- README dinámico que muestre rankings actualizados

---

