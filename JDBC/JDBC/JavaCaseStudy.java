import java.sql.*;

public class JavaCaseStudy {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Management";
        String username = "root";
        String password = "Ankit@1255";

        String query = "select * from employees";

        try{
            Connection con = DriverManager.getConnection(url, username , password);

            System.out.println("Connection established");

            Statement ss = con.createStatement();
            ResultSet rs = ss.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getNString("name");
                String email = rs.getNString("email");
                int phone = rs.getInt("phone_no");
                double salary = rs.getDouble("salary");

                System.out.println("===================");
                System.out.println("Id : "+ id );
                System.out.println("Name : "+ name );
                System.out.println("Email : "+ email );
                System.out.println("Phone : "+ phone );
                System.out.println("Salary : "+ salary );
                System.out.println("===================");

            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
