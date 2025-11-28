# Ranking-Retos

<dependencies>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.10.1</version>
    </dependency>
</dependencies>
```

---

# 🎯 ESTRUCTURA COMPLETA DEL PROYECTO
```
RankingRetos/
├── src/
│   ├── Main.java
│   ├── modelo/
│   │   ├── Participante.java
│   │   ├── Reto.java
│   │   ├── Resultado.java
│   │   └── EstadoReto.java (enum)
│   ├── servicio/
│   │   ├── GestorDatos.java
│   │   ├── GestorParticipantes.java
│   │   ├── GestorRetos.java
│   │   └── TimerReto.java
│   ├── vista/
│   │   ├── VistaConsola.java
│   │   └── MenuPrincipal.java
│   └── util/
│       └── Utilidades.java
└── datos/
    └── datos.json (se crea automáticamente)
```

---

# 📋 ORDEN DE IMPLEMENTACIÓN (COPY-PASTE READY)

## 🔢 Orden de Codificación
```
1. EstadoReto.java (enum) ← EMPEZAR AQUÍ
2. Resultado.java
3. Participante.java
4. Reto.java
5. Utilidades.java
6. GestorDatos.java
7. GestorParticipantes.java
8. GestorRetos.java
9. VistaConsola.java
10. TimerReto.java
11. MenuPrincipal.java
12. Main.java ← TERMINAR AQUÍ
```

---

# 🏗️ BLUEPRINTS DE CADA CLASE

## **1️⃣ EstadoReto.java**
```
UBICACIÓN: src/modelo/EstadoReto.java
PROPÓSITO: Enum para estados del reto
CONTENIDO:
- PENDIENTE
- EN_CURSO
- COMPLETADO
```

## **2️⃣ Resultado.java**
```
UBICACIÓN: src/modelo/Resultado.java
PROPÓSITO: Representa el resultado de un participante en un reto
ATRIBUTOS:
- nombreParticipante: String
- puntosObtenidos: int
- posicion: int
MÉTODOS:
- Constructor completo
- Getters/Setters
- toString()
```

## **3️⃣ Participante.java**
```
UBICACIÓN: src/modelo/Participante.java
PROPÓSITO: Representa un participante del sistema
ATRIBUTOS:
- nombre: String (único, identificador)
MÉTODOS:
- Constructor
- Getters/Setters
- equals() y hashCode() basados en nombre
- toString()
NOTA: Los puntos se calculan dinámicamente desde los retos
```

## **4️⃣ Reto.java**
```
UBICACIÓN: src/modelo/Reto.java
PROPÓSITO: Representa un reto/challenge
ATRIBUTOS:
- id: int
- nombre: String
- puntosMaximos: int
- tiempoMinutos: int
- estado: EstadoReto
- resultados: List<Resultado>
MÉTODOS:
- Constructor completo
- agregarResultado(Resultado)
- Getters/Setters
- toString()
```

## **5️⃣ Utilidades.java**
```
UBICACIÓN: src/util/Utilidades.java
PROPÓSITO: Métodos utilitarios estáticos
MÉTODOS:
- limpiarPantalla(): void
- emitirSonido(): void
- formatearTabla(String[][], String[]): String
- pausar(int segundos): void
```

## **6️⃣ GestorDatos.java**
```
UBICACIÓN: src/servicio/GestorDatos.java
PROPÓSITO: Persistencia con JSON (Gson)
ATRIBUTOS:
- RUTA_DATOS: String = "datos/datos.json"
- gson: Gson
CLASE INTERNA:
- DatosApp {
    List<Participante> participantes
    List<Reto> retos
    int ultimoIdReto
  }
MÉTODOS:
- cargarDatos(): DatosApp
- guardarDatos(DatosApp): void
- crearArchivoSiNoExiste(): void
```

## **7️⃣ GestorParticipantes.java**
```
UBICACIÓN: src/servicio/GestorParticipantes.java
PROPÓSITO: CRUD y lógica de participantes
ATRIBUTOS:
- participantes: List<Participante>
- gestorDatos: GestorDatos
- gestorRetos: GestorRetos (para calcular puntos)
MÉTODOS:
- registrarParticipante(String nombre): boolean
- eliminarParticipante(String nombre): boolean
- obtenerParticipante(String nombre): Participante
- listarParticipantes(): List<Participante>
- calcularPuntosTotales(String nombre): int
- existeParticipante(String nombre): boolean
- obtenerRankingOrdenado(): List<Map.Entry<Participante, Integer>>
```

## **8️⃣ GestorRetos.java**
```
UBICACIÓN: src/servicio/GestorRetos.java
PROPÓSITO: CRUD y lógica de retos
ATRIBUTOS:
- retos: List<Reto>
- gestorDatos: GestorDatos
- ultimoId: int
MÉTODOS:
- crearReto(String nombre, int puntosMax, int tiempo): Reto
- obtenerReto(int id): Reto
- listarRetosPendientes(): List<Reto>
- listarRetosCompletados(): List<Reto>
- iniciarReto(int id): boolean
- agregarParticipanteAlReto(int retoId, String nombreParticipante): boolean
- finalizarReto(int id): void
- calcularPuntosDecrecientes(int puntosMax, int posicion): int
- obtenerPuntosParticipanteEnReto(int retoId, String nombreParticipante): int
```

## **9️⃣ VistaConsola.java**
```
UBICACIÓN: src/vista/VistaConsola.java
PROPÓSITO: Formateo y visualización
MÉTODOS ESTÁTICOS:
- mostrarRanking(List<Map.Entry<Participante, Integer>>): void
- mostrarRetoEnCurso(Reto, String tiempoRestante): void
- mostrarHistorial(List<Reto>): void
- mostrarListaParticipantes(List<Participante>): void
- mostrarListaRetos(List<Reto>): void
- dibujarBorde(String titulo): void
- centrarTexto(String texto, int ancho): String
```

## **🔟 TimerReto.java**
```
UBICACIÓN: src/servicio/TimerReto.java
PROPÓSITO: Thread para countdown
ATRIBUTOS:
- minutos: int
- segundos: int
- corriendo: boolean
- thread: Thread
MÉTODOS:
- iniciar(): void
- detener(): void
- getTiempoRestante(): String (formato "MM:SS")
- haTerminado(): boolean
- run(): void (Runnable)
```

## **1️⃣1️⃣ MenuPrincipal.java**
```
UBICACIÓN: src/vista/MenuPrincipal.java
PROPÓSITO: Navegación y control de flujo
ATRIBUTOS:
- gestorParticipantes: GestorParticipantes
- gestorRetos: GestorRetos
- scanner: Scanner
MÉTODOS:
- ejecutar(): void (ciclo principal)
- mostrarMenuPrincipal(): void
- menuVerRanking(): void
- menuGestionarParticipantes(): void
- menuCrearReto(): void
- menuIniciarReto(): void
- menuHistorial(): void
- procesarRetoEnCurso(Reto): void
```

## **1️⃣2️⃣ Main.java**
```
UBICACIÓN: src/Main.java
PROPÓSITO: Punto de entrada
CONTENIDO:
- Inicializar GestorDatos
- Cargar datos
- Crear instancias de gestores
- Crear MenuPrincipal
- Ejecutar aplicación
- Guardar datos al salir


