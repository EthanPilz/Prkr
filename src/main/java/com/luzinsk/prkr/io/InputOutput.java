package com.luzinsk.prkr.io;


import com.luzinsk.prkr.Prkr;
import com.luzinsk.prkr.components.PlayerCheckpoint;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.sql.*;
import java.util.logging.Level;

public class InputOutput {
    public static YamlConfiguration global;
    private static Connection connection;

    public InputOutput() {
        if (!Prkr.instance.getDataFolder().exists()) {
            try {
                (Prkr.instance.getDataFolder()).mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        global = new YamlConfiguration();
    }
    private static Connection createConnection() {

        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection ret = DriverManager.getConnection("jdbc:sqlite:" +  new File(Prkr.instance.getDataFolder().getPath(), "db.sqlite").getPath());
            ret.setAutoCommit(false);
            return ret;
        }
        catch (ClassNotFoundException e)
        {
            Prkr.log.log(Level.SEVERE, Prkr.prkrPrefix + "ClassNotFound while attempting to create database connection");
            e.printStackTrace();
            return null;
        }
        catch (SQLException e)
        {
            Prkr.log.log(Level.SEVERE, Prkr.prkrPrefix + "Encountered SQL exception while attempting to create database connection");
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized Connection getConnection() {
        if (connection == null) connection = createConnection();

        try
        {
            if(connection.isClosed()) connection = createConnection();
        }

        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return connection;
    }

    public static synchronized void freeConnection() {
        Connection conn = getConnection();
        if(conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void prepareDB()
    {
        Connection conn = getConnection();
        Statement st = null;
        try
        {
            st = conn.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS \"prkr_player_checkpoints\" (\"UUID\" VARCHAR PRIMARY KEY NOT NULL, \"X\" DOUBLE, \"Y\" DOUBLE, \"Z\" DOUBLE, \"Pitch\" FLOAT, \"Yaw\" FLOAT, \"World\" VARCHAR, PRIMARY KEY(UUID)");

            conn.commit();
            st.close();

        }
        catch (SQLException e)
        {
            Prkr.log.log(Level.SEVERE, Prkr.prkrPrefix + "Encountered SQL error while attempting to prepare database: " + e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e)
        {
            Prkr.log.log(Level.SEVERE, Prkr.prkrPrefix + "Unknown error encountered while attempting to prepare database.");
        }
    }

    public void storePlayerCheckpoint(PlayerCheckpoint cp) {
        try
        {
            String sql;
            Connection conn = InputOutput.getConnection();

            sql = "INSERT INTO prkr_player_checkpoints (`UUID`, `X`, `Y`, `Z`, `Pitch`, `Yaw`, `World`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, cp.getPlayer().getUniqueId().toString());
            preparedStatement.setDouble(2, cp.getLocation().getX());
            preparedStatement.setDouble(3, cp.getLocation().getY());
            preparedStatement.setDouble(4, cp.getLocation().getZ());
            preparedStatement.setFloat(5, cp.getLocation().getPitch());
            preparedStatement.setFloat(6, cp.getLocation().getYaw());
            preparedStatement.setString(7, cp.getLocation().getWorld().getName());

            preparedStatement.executeUpdate();
            conn.commit();
        }
        catch (SQLException e)
        {
            Prkr.log.log(Level.WARNING, Prkr.prkrPrefix + "Encountered an error while attempting to store a player checkpoint to the database: " + e.getMessage());
            // throw new SaveToDatabaseException();
        }
    }

    public PlayerCheckpoint getPlayerCheckpoint(Player player) throws SQLException {

        Connection conn = InputOutput.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT `X`, `Y`, `Z`, `Pitch`, `Yaw`, `World` FROM `prkr_player_checkpoints` WHERE `UUID`=" + player.getUniqueId().toString());
        ResultSet result = ps.executeQuery();

        Location loc = null;

        while (result.next()) {
            loc = new Location(Bukkit.getWorld(result.getString("World")), result.getDouble("X"), result.getDouble("Y"), result.getDouble("Z"), result.getFloat("Yaw"), result.getFloat("Pitch"));

        }
        if (loc == null)
            return null;
        else
            return (new PlayerCheckpoint(player, loc));
    }

    public void deletePlayerCheckpoint(Player player) throws SQLException {

        Connection conn = InputOutput.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM `prkr_player_checkpoints` WHERE `UUID` = " + player.getUniqueId().toString());
        ResultSet result = ps.executeQuery();

        result.next();
    }
}


