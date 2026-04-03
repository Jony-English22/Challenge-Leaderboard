# Contribuir a Challenge Leaderboard

> Un sistema de gestión de retos y competiciones desarrollado en Java con interfaz de línea de comandos (CLI).  
> Stack: Java 17, Maven, Gson  
> Tipo: CLI Application  
> Madurez: En desarrollo

¡Bienvenido/a! 👋  

Nos entusiasma tu interés en colaborar con **Challenge Leaderboard**. Este documento explica cómo trabajar con el código, reportar problemas, sugerir mejoras y enviar contribuciones de calidad.

Tanto si eres principiante como experimentado, aquí encontrarás una guía clara para contribuir de manera efectiva al proyecto.

---

## 1. Introducción

### ¿Por qué contribuir?

Challenge Leaderboard es un proyecto open source que busca ser una herramienta útil, robusta y accesible para gestionar retos y competiciones de programación. Tu contribución (código, documentación, tests, reportes de bugs) es invaluable para mejorar el proyecto.

### Propósito de este documento

Este archivo te proporciona:
- Instrucciones claras para configurar el entorno local.
- Estándares de código y buenas prácticas.
- Flujo de trabajo (fork, branch, commit, PR).
- Cómo reportar bugs y proponer features.
- Criterios de aceptación para Pull Requests.

---

## 2. Código de Conducta

Esperamos que todos los participantes se comportenn con respeto y profesionalismo.

### Nuestros valores:
- **Respeto mutuo**: Valoramos todas las perspectivas e ideas.
- **Inclusión**: Bienvenidas personas de cualquier edad, género, identidad, experiencia.
- **Comunicación clara**: Entiende antes de criticar. Sé constructivo.
- **Sin tolerancia a acoso**: No permitimos lenguaje hostil, discriminatorio o abusivo.

### Consecuencias
Violaciones graves pueden resultar en:
- Cierre de PRs o issues.
- Baneo temporal o permanente del repositorio.

> Para más detalles, consulta `CODE_OF_CONDUCT.md` (si existe en el repo).

---

## 3. Cómo empezar

### 3.1 Prerrequisitos

Antes controlar, asegúrate de tener:

- **Java Development Kit (JDK) 17+**
  - Descarga: https://www.oracle.com/java/technologies/downloads/
  - Verifica: `java --version`
  
- **Apache Maven 3.6+**
  - Descarga: https://maven.apache.org/download.cgi
  - Verifica: `mvn --version`
  
- **Git**
  - Descarga: https://git-scm.com/
  - Verifica: `git --version`

- **Editor/IDE** (recomendado):
  - IntelliJ IDEA Community (ideal para Java)
  - VSCode + Extension Pack for Java
  - Eclipse

### 3.2 Instalación local paso a paso

**Paso 1: Fork y clona el repositorio**
```bash
# 1. Ve a https://github.com/<owner>/Challenge-Leaderboard
# 2. Click en "Fork" (crea tu propia copia)
# 3. Clona tu fork:

git clone https://github.com/<tu-usuario>/Challenge-Leaderboard.git
cd Challenge-Leaderboard
```

**Paso 2: Agrega remoto upstream**
```bash
# Permite sincronizar con el repositorio original
git remote add upstream https://github.com/<owner>/Challenge-Leaderboard.git
git fetch upstream
```

**Paso 3: Limpia y compila**
```bash
# Limpia compilaciones anteriores
mvn clean

# Descarga dependencias e instala
mvn install
```

**Paso 4: Verifica que todo funciona**
```bash
# Ejecuta la aplicación
mvn exec:java

# Deberías ver el prompt de la CLI
```

### 3.3 Configuración del entorno (opcional)

Si usas **IntelliJ IDEA**:
1. Open Project → selecciona la carpeta del proyecto.
2. Espera a que indexe.
3. Right-click en `pom.xml` → "Add as Maven Project".

Si usas **VSCode**:
1. Instala "Extension Pack for Java" (Microsoft).
2. Abre la carpeta del proyecto.
3. Maven debería detectar automáticamente el `pom.xml`.

---

## 4. Flujo de trabajo de contribución

### 4.1 Fork y branches

**Crea una rama para tu contribución:**
```bash
# Actualiza main primero
git fetch upstream
git checkout main
git merge upstream/main

# Crea tu rama (naming convention)
git checkout -b feature/456-mejorar-ranking-display
```

### 4.2 Convención de ramas

Usa prefijos claros según el tipo:

| Tipo | Ejemplo | Descripción |
|------|---------|-------------|
| `feature/` | `feature/456-agregar-filtro-participantes` | Nuevas funcionalidades |
| `bugfix/` | `bugfix/123-error-ranking-nulo` | Correcciones de bugs |
| `refactor/` | `refactor/mejorar-gestor-datos` | Reestructuración sin cambio funcional |
| `docs/` | `docs/mejorar-readme` | Documentación |
| `test/` | `test/cobertura-gestor-retos` | Tests y cobertura |
| `chore/` | `chore/actualizar-dependencias` | Tareas administrativas |

### 4.3 Convenciones de commits

Usa **Conventional Commits** para claridad:

```
<tipo>(<scope>): <descripción corta>

<cuerpo opcional>

<footer opcional>
```

**Tipos recomendados:**
- `feat`: Nuevas funcionalidades
- `fix`: Correcciones de bugs
- `docs`: Documentación
- `test`: Tests y cobertura
- `refactor`: Cambios de código sin cambia comportamiento
- `chore`: Tareas, dependencias, configuración
- `perf`: Mejoras de rendimiento

**Ejemplos reales:**

```bash
# Feature simple
git commit -m "feat(cli): agregar comando 'ranking top10'"

# Con más detalle
git commit -m "feat(service): mejorar algoritmo de cálculo de ranking

- Usa búsqueda binaria para mejor performance
- Factoriza cálculo de empates
- Redondea puntuaciones a 2 decimales"

# Bugfix
git commit -m "fix(model): evitar NullPointerException en Participante.getScorePrueba"

# Documentación
git commit -m "docs: agregar instrucciones de instalación en WSL"

# Test
git commit -m "test(service): agregar cobertura para GestorRetos"
```

### 4.4 Pull Requests

**Antes de abrir un PR:**

1. Asegúrate que el código compila y los tests pasan:
   ```bash
   mvn clean test
   ```

2. Sincroniza con main:
   ```bash
   git fetch upstream
   git rebase upstream/main
   ```
   (Si hay conflictos, resuélvelos y `git rebase --continue`)

3. Push a tu fork:
   ```bash
   git push origin feature/456-mejorar-ranking-display
   ```

4. En GitHub, abre un **New Pull Request**.

**En la descripción del PR, incluye:**

```markdown
## Descripción
Breve resumen de qué hace el PR y por qué.

## Tipo de cambio
- [ ] Bug fix
- [x] Feature nueva
- [ ] Breaking change
- [ ] Documentación

## Relacionado con
Closes #456  (reemplaza "#456" con el número del issue)

## Cambios realizados
- Agrega comando `ranking top10`
- Refactoriza cálculo de puntuación
- Añade 5 nuevos tests

## Testing
- [x] Tests nuevos pasan
- [x] Tests existentes pasan
- [x] Cobertura ≥ 70%

## Checklist
- [x] Mi código sigue el style del proyecto
- [x] He ejecutado `mvn clean test` localmente
- [x] He revisado mi propio código
- [x] He agregado comentarios en partes complejas
- [x] He actualizado la documentación relevante
- [x] No hay cambios rotos (breaking changes)
```

**Esperamos que:**
- El código compile sin errores.
- Todos los tests pasen.
- Haya descripción clara del cambio.
- Se respete el style de código del proyecto.

---

## 5. Reportar issues

### 5.1 Antes de reportar

Chequea:
- ¿Ya existe un issue similar? (usa la búsqueda)
- ¿Es reproducible con la versión actual?
- ¿Leíste `README.md` y la documentación?

### 5.2 Reportar un bug

Usa esta plantilla:

```markdown
### Descripción
[Descripción clara del bug]

### Pasos para reproducir
1. Ejecuta: `mvn exec:java`
2. Ingresa comando: `crear-reto`
3. Ingresa valores: [valores específicos]
4. Observa error

### Comportamiento esperado
[Qué debería suceder]

### Comportamiento actual
[Qué sucede realmente]
[Incluye stacktrace si hay excepción]

### Logs / Error
```
java.lang.NullPointerException: Cannot read field "score" because "participante" is null
    at GestorRetos.calcularRanking(GestorRetos.java:87)
```

### Entorno
- OS: Windows 11 / macOS 12 / Ubuntu 22.04
- Java: `java --version` output
- Maven: `mvn --version` output
- Rama: main (o qué rama usas)
```

### 5.3 Proponer una feature

Estructura clara:

```markdown
### Descripción
[Explica la idea de forma clara]

### Problema que resuelve
[Qué problema tiene el usuario actualmente]

### Solución propuesta
[Cómo la feature resuelve el problema]

### Ejemplo de uso
Command:
```bash
ranking filter --Estado=completado
```

Output:
```
[Ranking de retos completados]
```

### Alternativas consideradas
[Otros enfoques que consideraste]

### Impacto
- [ ] Breaking change (cambia comportamiento existente)
- [ ] Requiere actualizar documentación
- [ ] Afecta el almacenamiento de datos
```

### 5.4 Labels (etiquetas)

Cuando reportes, usa labels para categorizar:

| Label | Descripción |
|-------|-------------|
| `bug` | Comportamiento inesperado |
| `enhancement` | Mejora o feature nueva |
| `documentation` | Documentación |
| `good first issue` | Ideal para principiantes |
| `help wanted` | Necesita contribución |
| `question` | Pregunta sobre el proyecto |
| `duplicate` | Duplicado de otro issue |

---

## 6. Estándares de código

### 6.1 Convenciones de Java

Sigue estas prácticas:

**Naming:**
```java
// ✅ Bueno
class GestorParticipantes { }
private int puntuacionMaxima;
public boolean estaActivo() { }

// ❌ Evitar
class gestor_participantes { }
private int pMax;
public boolean is() { }
```

**Estructura:**
```java
// Orden recomendado en una clase:
public class Participante {
    // 1. Constantes estáticas
    private static final int DEFAULT_SCORE = 0;
    
    // 2. Atributos estáticos
    private static int totalParticipantes = 0;
    
    // 3. Atributos de instancia
    private String nombre;
    private int puntuacion;
    
    // 4. Constructor(es)
    public Participante(String nombre) { }
    
    // 5. Métodos públicos
    public int getPuntuacion() { }
    
    // 6. Métodos privados/helpers
    private void validar() { }
}
```

**Documentación (JavaDoc):**
```java
/**
 * Calcula la puntuación final del participante.
 *
 * @param resultados lista de resultados del participante
 * @return puntuación total acumulada
 * @throws IllegalArgumentException si resultados es null
 */
public int calcularPuntuacion(List<Resultado> resultados) {
    // ...
}
```

### 6.2 Estilo de código

- **Indentación**: 4 espacios (no tabs)
- **Línea máxima**: 120 caracteres (evitar líneas muy largas)
- **Encapsulación**: Usa `private`/`protected` por defecto
- **Null safety**: Valida entradas y usa `Optional` cuando sea apropiado

**Validación de inputs:**
```java
// ✅ Bueno
public Participante(String nombre) {
    if (nombre == null || nombre.isBlank()) {
        throw new IllegalArgumentException("Nombre no puede ser vacío");
    }
    this.nombre = nombre.trim();
}

// ❌ Evitar
public Participante(String nombre) {
    this.nombre = nombre; // ¿Y si es null?
}
```

### 6.3 Testing requerido

Para cada cambio:

- ✅ Agrega tests unitarios en `src/test/java/...`
- ✅ Cobertura mínima: **70%** de las líneas nuevas
- ✅ Incluye casos límite (null, valores vacíos, números negativos)
- ✅ Nómbralo descriptivamente: `test<Método><Caso>`

**Ejemplo:**
```java
@Test
public void testCalcularRankingConParticipantesVacios() {
    // Arrange
    GestorRetos gestor = new GestorRetos();
    
    // Act
    List<Participante> ranking = gestor.obtenerRanking();
    
    // Assert
    assertTrue(ranking.isEmpty(), "Ranking debe estar vacío");
}

@Test
public void testAgregarParticipanteNullLanzaExcepcion() {
    GestorParticipantes gestor = new GestorParticipantes();
    
    assertThrows(IllegalArgumentException.class, 
        () -> gestor.agregarParticipante(null));
}
```

**Ejecuta tests:**
```bash
# Todos
mvn test

# Un test específico
mvn test -Dtest=GestorRetosTest

# Con cobertura (JaCoCo)
mvn test jacoco:report
# Reporte en: target/site/jacoco/index.html
```

### 6.4 Anti-patrones a evitar

```java
// ❌ No hagas esto:
public class GestorRetos {
    // Variables globales compartidas
    static List<String> retos = new ArrayList<>();
    
    // Métodos demasiado largos (>50 líneas)
    public void procesarTodo() { /* 200 líneas */ }
    
    // Ignorar excepciones
    try {
        leerArchivo();
    } catch (Exception e) {
        // silence
    }
    
    // Magic numbers
    if (score > 42 && tiempo < 8640000) { }
}

// ✅ En su lugar:
public class GestorRetos {
    private List<Reto> retos = new ArrayList<>();
    
    // Métodos pequeños y enfocados
    public void procesarResultado(Resultado resultado) { }
    
    // Manejo claro de errores
    try {
        leerArchivo();
    } catch (IOException e) {
        logger.error("Error leyendo archivo", e);
        throw new RuntimeException(e);
    }
    
    // Constantes nombradas
    private static final int SCORE_MINIMO = 42;
    private static final long TIEMPO_MAXIMO_MS = 8640000;
}
```

---

## 7. Documentación

### 7.1 Mejoras a README.md

Si cambias funcionalidades de CLI, actualiza la sección de Uso:

```markdown
## 📖 Uso

```bash
mvn exec:java
> help              # Muestra todos los comandos
> crear-reto        # Crea un nuevo reto
> ranking top10     # Mostrador top 10 (NEW)
```
```

### 7.2 Comentar cambios complejos

En el código mismo:

```java
// Algoritmo de ranking mejorado: O(n log n) usando TreeMap
TreeMap<Integer, List<Participante>> rankingPorPuntuacion = 
    new TreeMap<>(Collections.reverseOrder());
```

### 7.3 Actualizar CHANGELOG (si existe)

Cuando hagas un PR importante, agrega tu cambio:

```markdown
## [Unreleased]

### Added
- Comando `ranking top10` para ver top 10 participantes
- Validación de email en Participante

### Fixed
- Bug en cálculo de promedio con puntuaciones negativas
- NPE en GestorRetos cuando lista de retos está vacía
```

---

## 8. Revisión de código

### 8.1 Qué esperar

Cuando abras un PR:
- Uno o dos mantenedores revisarán tu código.
- Podrían solicitar cambios.
- Sé abierto a feedback constructivo.

### 8.2 Criterios de aceptación

Tu PR será aceptado si:

- ✅ Compila sin errores: `mvn clean compile`
- ✅ Todos los tests pasan: `mvn test`
- ✅ Cobertura ≥ 70% (en líneas nuevas)
- ✅ Código sigue el style del proyecto
- ✅ No introduce breaking changes sin discusión
- ✅ Descripción clara en el PR
- ✅ Commits bien estructurados (Conventional Commits)
- ✅ Documentación actualizada (si es necesario)

### 8.3 Hacer cambios después de feedback

Si se solicitan cambios:

```bash
# 1. Actualiza tu rama
git fetch upstream
git rebase upstream/main

# 2. Haz los cambios solicitados
# 3. Commit (força si es necesario, mantén commit limpio)
git commit --amend

# 4. Push (usa --force-with-lease)
git push --force-with-lease origin feature/...
```

El PR se actualiza automáticamente.

---

## 9. Comunicación

### Canales principales

- **GitHub Issues**: Reportes de bugs, features, preguntas.
- **GitHub Discussions** (si está habilitado): Conversaciones generales.
- **Pull Requests**: Discusión de código y cambios.

### Cómo preguntar

- Sé específico: incluye contexto y ejemplos.
- Busca respuestas existentes primero.
- Sé respetuoso y paciente.

**Buen ejemplo:**
```
Título: Cómo agregar un filtro personalizado al ranking?

Descripción:
Quiero mostrar un ranking solo de los retos completados. 
¿Hay una forma existente de hacerlo? 
Revisé el README pero no encontré info.

En caso de que haya que desarrollarlo, ¿cuál sería el enfoque recomendado?
```

**Mal ejemplo:**
```
Título: No funciona el ranking

Descripción:
El ranking está roto. Arreglenlo.
```

---

## 10. Reconocimiento

### Contribuidores destacados

Incluimos en `README.md` una sección de **Contribuidores**:

```markdown
## 👥 Contribuidores

Gracias a todas las personas que colaboran en este proyecto:

- [@usuario1](https://github.com/usuario1) - Features y documentación
- [@usuario2](https://github.com/usuario2) - Bugfixes y tests
- [@usuario3](https://github.com/usuario3) - Optimizaciones
```

### Mención en releases

Para cada release, publicamos:

```markdown
## v1.1.0 - 2024-06-15

### Agradecimientos especiales
- @usuario1 por el comando `ranking top10`
- @usuario2 por mejorar la validación de datos
```

### Oportunidades adicionales

Contribuidores frecuentes pueden:
- Acceder a conversaciones privadas de roadmap.
- Ser promovidos a **Collaborator** (mantener permisos).
- Ser mencionados en redes sociales del proyecto.

---

## 11. Checklist rápida

Antes de abrir un PR:

- [ ] Clonaste desde tu fork
- [ ] Creaste rama con `feature/`, `bugfix/`, etc.
- [ ] `mvn clean test` pasa (sin errores)
- [ ] Código sigue convención de naming
- [ ] JavaDoc en métodos públicos
- [ ] Tests nuevos ≥ 70% cobertura
- [ ] Commits usan Conventional Commits
- [ ] Descripción de PR es clara
- [ ] Documentación actualizada (si aplica)
- [ ] Sincronizaste con `upstream/main`

---

## 12. Preguntas frecuentes

### ¿Necesito firma de contribuidor (CLA)?
No, Challenge Leaderboard no requiere CLA. Tu contribución es bienvenida tal como está.

### ¿Cuánto tarda la revisión?
Intentamos revisar PRs en **3-7 días hábiles**. Sé paciente y no hagas push reiterados.

### ¿Puedo contribuir si tengo poca experiencia en Java?
¡Sí! Aquí hay issues marcadas como `good first issue` que son perfectas para aprender.

### ¿Puedo trabajar en feature X sin abrir issue primero?
Es recomendable abrir un issue o comentar en uno existente para evitar conflictos. Si es fix pequeño, directo al PR está bien.

### ¿Qué si mi PR es rechazado?
No es personal. Agradecemos el esfuerzo y queremos ayudarte a mejorar. Pide feedback específico y está abierto a iterar.

---

## 13. Próximos pasos

1. **Busca "good first issue"** en Issues para empezar.
2. **Comenta en el issue**: "Quiero trabajar en esto".
3. **Crea tu fork y rama**.
4. **¡Haz tu primer PR!**

Estamos aquí para ayudarte. ¡Bienvenido a la comunidad! 💙

---

**Última actualización**: Abril 2026  
**Mantenedor principal**: [Tu nombre]  
**Licencia**: [Tu licencia]
