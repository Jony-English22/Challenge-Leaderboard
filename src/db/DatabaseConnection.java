package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gestiona la conexión y ejecución de operaciones contra una base de datos
 * usando JDBC.
 *
 * <p>
 * Esta clase se encarga de establecer y cerrar la conexión con una base de
 * datos
 * PostgreSQL, así como de ejecutar consultas SQL parametrizadas de forma segura
 * y reutilizable.
 * </p>
 *
 * <p>
 * Proporciona métodos para:
 * </p>
 * <ul>
 * <li>Abrir y cerrar la conexión a la base de datos</li>
 * <li>Ejecutar sentencias INSERT, UPDATE y DELETE parametrizadas</li>
 * <li>Ejecutar consultas SELECT y devolver los resultados en una estructura
 * genérica</li>
 * </ul>
 *
 * <p>
 * Todas las consultas se ejecutan mediante {@link java.sql.PreparedStatement}
 * para prevenir inyecciones SQL y garantizar el manejo correcto de parámetros.
 * </p>
 *
 * <p>
 * <b>Ejemplo de uso:</b>
 * </p>
 * 
 * <pre>
 * ConexionDB db = new ConexionDB("localhost", "5432", "mydb", "user", "password");
 * db.connect();
 *
 * String sql = "INSERT INTO participants (name, score) VALUES (?, ?)";
 * db.executeParameterizedUpdate(sql, "Juan", 100);
 *
 * db.disconnect();
 * </pre>
 *
 * <p>
 * <b>Nota:</b> Esta clase no implementa un pool de conexiones.
 * Para aplicaciones de alto rendimiento, se recomienda utilizar un DataSource
 * o un pool de conexiones (por ejemplo, HikariCP).
 * </p>
 *
 * @TwentyDevelop
 * @version 1.0
 */
public class DatabaseConnection {

    private Connection connection;
    private final String URL;
    private final String USER;
    private final String PASSWORD;

    /**
     * Constructor
     * 
     * @param host     Host de la base de datos
     * @param port     Puerto de PostgreSQL
     * @param database Nombre de la base de datos
     * @param user     Usuario de PostgreSQL
     * @param password Constraseña del usuario
     */
    public DatabaseConnection(String host, String port, String database, String user, String password) {
        this.URL = "jdbc:postgresql://" + host + ":" + port + "/" + database;
        this.USER = user;
        this.PASSWORD = password;
    }

    /**
     * Establece la conexión con la base de datos
     *
     * @throws SQLException Si ocurre un error al conectar.
     */
    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a PostgreSQL");
        }
    }

    /**
     * Cierra la conexión con la base de datos.
     *
     * @throws SQLException Si ocurre un error al cerrar.
     */
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Conexión cerrada");
        }
    }

    /**
     * Ejecuta una consulta parametrizada (PreparedStatement) para
     * evitar inyecciones SQL.
     * 
     * @param sql    Consulta SQl con parámetros ('?')
     * @param params Valores de los parámetros (ordenados)
     * @return Número de filas afectadas
     * @throws SQLException Si hay error
     */
    public int executeParameterizedUpdate(String sql, Object... params) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeUpdate();
        }
    }

    /**
     * Ejecuta un SELECT parametrizado y devuelve los resultados.
     * 
     * @param sql    Consulta SQL con '?' como placeholders.
     * @param params Parámentros de la consulta
     * @return Lista de filas (Map<columna, valor>)
     * @throws SQLException SI hay error
     */
    public List<Map<String, Object>> executeParametrizedQuery(String sql, Object... params) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int j = 1; j <= columnCount; j++) {
                        String columnName = metaData.getColumnName(j);
                        Object value = rs.getObject(j);
                        row.put(columnName, value);
                    }
                    results.add(row);
                }
            }
        }
        return results;
    }
}
