import java.sql.*;
import java.util.Date;

class JDBC {
    Connection conn;
    String url, sqlstat;
    Statement smt;
    PreparedStatement ps;
    ResultSet rset;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("CONNECTED SUCCCESSFULLY ");
        } catch (Exception c) {
            c.getStackTrace();
        }

    }

    public void showDb(String startdate,String enddate) {

        try {
            url = "jdbc:mysql://localhost:3306/firstDB";
            conn = DriverManager.getConnection(url, "root", "root");
            
            
            sqlstat = "Select *from expenses where date between ? and ?";

            ps = conn.prepareStatement(sqlstat);
            // ps = smt.execute(sqlstat);
            // System.out.println(b);
            ps.setString(1, startdate);
            ps.setString(2, enddate);

            rset = ps.executeQuery();
            while (rset.next()) {
                java.sql.Date date;
                int smarks;
                date = rset.getDate(1);
                String strname = rset.getString(2);
                smarks = rset.getInt(3);
                System.out.println(date + "\t" + strname + "\t" + smarks);

            }

            System.out.println("\nDATA RETRIVE SUCCESSFULLY \n");
            smt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void Insert(Date date, String name, String price) {
        long l = date.getTime();
        java.sql.Date sdate = new java.sql.Date(l);
        try {
            url = "jdbc:mysql://localhost/firstDB";
            conn = DriverManager.getConnection(url, "root", "root");
            sqlstat = "insert into expenses values(?,?,?)";
            ps = conn.prepareStatement(sqlstat);
            // ps = smt.execute(sqlstat);
            // System.out.println(b);
            ps.setString(1, sdate.toString());
            ps.setString(2, name);
            ps.setString(3, price);
            ps.executeUpdate();

            smt.close();
            conn.close();

        }

        catch (Exception Ec) {
            System.out.println(Ec);
        }
    }
}

class main {

    public static void main(String[] args) {
        JDBC obj = new JDBC();
        obj.connect();
        new Expenses();
        }
    }

