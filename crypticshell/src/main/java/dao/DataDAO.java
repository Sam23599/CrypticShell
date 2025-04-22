package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.MyConnection;
import model.Data;

public class DataDAO {
    public static List<Data> getAllFiles(String email) throws SQLException{
        Connection connection = MyConnection.getConnection();
        List<Data> filesList = new ArrayList<>();
        try {
            String query = "SELECT * FROM data WHERE email = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String filePath = rs.getString(3);

                filesList.add(new Data(id, name, filePath));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filesList;
    }

    public static int hideFile(Data file) throws SQLException, IOException, FileNotFoundException{
        Connection connection = MyConnection.getConnection();
        int result = 0;
        try {
            String query = "INSERT INTO data (name, path, email, bin_data) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, file.getFileName());
            ps.setString(2, file.getFilePath());
            ps.setString(3, file.getEmail());

            File bin_file = new File(file.getFilePath());
            FileReader fr = new FileReader(bin_file);

            ps.setCharacterStream(4, fr, (int) bin_file.length());
            result = ps.executeUpdate();
            fr.close();
            bin_file.delete(); 

            System.out.println("File hidden successfully!\n");


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void unHideFile(int id) throws IOException, SQLException{
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT path, bin_data FROM data WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String path = rs.getString("path");
        Clob bin_data = rs.getClob("bin_data");

        Reader reader = bin_data.getCharacterStream();
        FileWriter fw = new FileWriter(path);
        
        int i=0;
        while((i=reader.read())!=-1){
            fw.write(i);
        }
        reader.close();
        fw.close();

        PreparedStatement ps1 = connection.prepareStatement("DELETE FROM data WHERE id = ?");
        ps1.setInt(1, id);
        ps1.executeUpdate();
        System.out.println("File unhidden successfully!\n");

    }
}
