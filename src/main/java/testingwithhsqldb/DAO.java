package testingwithhsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DAO {

    private final DataSource myDataSource;

    public DAO(DataSource dataSource) {
        myDataSource = dataSource;
    }

    /**
     * Renvoie le nom d'un client à partir de son ID
     *
     * @param id la clé du client à chercher
     * @return le nom du client (LastName) ou null si pas trouvé
     * @throws SQLException
     */
    public String nameOfCustomer(int id) throws SQLException {
        String result = null;

        String sql = "SELECT LastName FROM Customer WHERE ID = ?";
        try (Connection myConnection = myDataSource.getConnection();
                PreparedStatement statement = myConnection.prepareStatement(sql)) {
            statement.setInt(1, id); // On fixe le 1° paramètre de la requête
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // est-ce qu'il y a un résultat ? (pas besoin de "while", 
                    // il y a au plus un enregistrement)
                    // On récupère les champs de l'enregistrement courant
                    result = resultSet.getString("LastName");
                }
            }
        }
        // dernière ligne : on renvoie le résultat
        return result;
    }

    public void createProduct(ProductEntity product) throws SQLException {

        String sql = "INSERT INTO PRODUCT VALUES(?, ?, ?)";

        try (Connection myConnection = myDataSource.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setInt(1, product.getProductId());
            stmt.setString(2, product.getName());
            stmt.setFloat(3, product.getPrice());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new SQLException(ex.getMessage());
        }
    }

    public ProductEntity findProduct(int productID) throws SQLException {
        ProductEntity result = null;

        String sql = "SELECT * FROM PRODUCT WHERE ID = ?";

        try (Connection myConnection = myDataSource.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setInt(1, productID);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                result = new ProductEntity(productID, resultSet.getString("Name"), resultSet.getFloat("Price"));
            }
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new SQLException(ex.getMessage());
        }
        return result;	
    }
    
}
