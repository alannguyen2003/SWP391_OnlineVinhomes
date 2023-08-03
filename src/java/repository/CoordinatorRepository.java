/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConfig;
import entity.CoordinatorEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Quoc
 */
public class CoordinatorRepository {

    // This method to get All Coordinator which is available for assign to Order
    public ArrayList<CoordinatorEntity> getAvailableCoordinator() throws Exception {
        ArrayList<CoordinatorEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = ("select * from Coordinator c \n"
                    + "JOIN dbo.Account a \n"
                    + "ON a.AID = c.ID\n"
                    + "WHERE c.enabled = 1");
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            CoordinatorEntity entity = new CoordinatorEntity();
            entity.setCID(rs.getInt(1));
            entity.setAvailable(rs.getBoolean(2));
            entity.setPhone(rs.getString(4));
            entity.setEmail(rs.getString(5));
            entity.setName(rs.getString(7));
            entity.setGender(rs.getString(8));
            list.add(entity);
        }
        return list;
    }

    // This method to get All Coordinator for Admin Manage Coordinator
    public ArrayList<CoordinatorEntity> getAllCoordinator() throws Exception {
        ArrayList<CoordinatorEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = ("select * from Coordinator c \n"
                    + "JOIN dbo.Account a \n"
                    + "ON a.AID = c.ID");
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            CoordinatorEntity entity = new CoordinatorEntity();
            entity.setCID(rs.getInt(1));
            entity.setAvailable(rs.getBoolean(2));
            entity.setPhone(rs.getString(4));
            entity.setEmail(rs.getString(5));
            entity.setName(rs.getString(7));
            entity.setGender(rs.getString(8));
            list.add(entity);
        }
        return list;
    }

    // This method to get one Coordinator by his/her CID
    public CoordinatorEntity getCoordinatorByID(int CID) throws Exception {
        CoordinatorEntity coordinator = new CoordinatorEntity();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = ("select * from Coordinator c\n"
                    + "JOIN dbo.Account a\n"
                    + "ON a.AID = c.ID\n"
                    + "WHERE c.ID = ?");
            pst = cn.prepareStatement(query);
            pst.setInt(1, CID);
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            coordinator.setCID(rs.getInt(1));
            coordinator.setAvailable(rs.getBoolean(2));
            coordinator.setPhone(rs.getString(4));
            coordinator.setEmail(rs.getString(5));
            coordinator.setName(rs.getString(7));
            coordinator.setGender(rs.getString(8));
        }

        return coordinator;
    }

    // This method to update Available or Unavailable for Coordinator
    public void updateEnableCoordinattor(boolean enable, int id) throws Exception {
        ArrayList<CoordinatorEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = ("UPDATE dbo.Coordinator \n"
                    + "SET enabled = ?\n"
                    + "WHERE ID = ?");
            pst = cn.prepareStatement(query);
            pst.setBoolean(1, enable);
            pst.setInt(2, id);
            int count = pst.executeUpdate();
        }

    }

    public int autoAssignCoordinator() throws Exception {
        int CID = 0;
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            LocalDate now = LocalDate.now();
            LocalDate firstDayOfMonth = now.withDayOfMonth(1);
            int minOrderNumber = 0;
            int maxOrderNumber = 0;
            int minPendingOrderNumber = 0;
            //This query is use to get the Min Number Of Order in Month
            String query = ("SELECT MIN(n.OrderNumber) AS MinOrderNumber\n"
                    + "FROM (SELECT o.CID, COUNT(o.OID) AS OrderNumber\n"
                    + "		FROM dbo.Coordinator c JOIN Orders o\n"
                    + "		ON o.CID = c.ID\n"
                    + "		WHERE CONVERT(DATE, o.delivery_time) BETWEEN ? AND ?\n"
                    + "		GROUP BY o.CID) AS n");

            pst = cn.prepareStatement(query);
            pst.setString(1, firstDayOfMonth.toString());
            pst.setString(2, now.toString());

            rs = pst.executeQuery();
            if (rs.next()) {
                minOrderNumber = rs.getInt(1);
            }

            //This query is use to get the Max Number Of Order in Month
            query = ("SELECT MAX(n.OrderNumber) AS MinOrderNumber\n"
                    + "FROM (SELECT o.CID, COUNT(o.OID) AS OrderNumber\n"
                    + "		FROM dbo.Coordinator c JOIN Orders o\n"
                    + "		ON o.CID = c.ID\n"
                    + "		WHERE CONVERT(DATE, o.delivery_time) BETWEEN ? AND ?\n"
                    + "		GROUP BY o.CID) AS n");

            pst = cn.prepareStatement(query);
            pst.setString(1, firstDayOfMonth.toString());
            pst.setString(2, now.toString());

            rs = pst.executeQuery();
            if (rs.next()) {
                maxOrderNumber = rs.getInt(1);
            }

            //This part is use to get the Best Coordinator to assign to Order
            if (minOrderNumber == 0) {
                // The First order of Month this query can be instead of query to get all Coordinator
                query = ("SELECT ca.ID, d.OrderNumber FROM dbo.Coordinator ca\n"
                        + "LEFT JOIN (SELECT o.CID, COUNT(o.OID) OrderNumber\n"
                        + "			FROM dbo.Coordinator c LEFT JOIN dbo.Orders o\n"
                        + "			ON o.CID = c.ID\n"
                        + "			WHERE CONVERT(DATE, o.delivery_time) BETWEEN ? AND ?\n"
                        + "			GROUP BY o.CID) AS d\n"
                        + "ON d.CID = ca.ID\n"
                        + "WHERE d.OrderNumber IS NULL\n"
                        + "AND ca.enabled = 1");
                pst = cn.prepareStatement(query);
                pst.setString(1, firstDayOfMonth.toString());
                pst.setString(2, now.toString());

                rs = pst.executeQuery();

                ArrayList<Integer> list = new ArrayList<Integer>();
                while (rs.next()) {
                    list.add(rs.getInt(1));
                }

                //Check if have 1 Coordinator suitable
                if (list.size() == 1) {
                    CID = list.get(0);
                } else {
                    // Randomly choose anyone who Available
                    Random random = new Random();
                    CID = list.get(random.nextInt(0, list.size()));
                }

            } else if (minOrderNumber == 1) {
                // This case is for the System has at least 1 order for 1 Coordinator. Other coordinator may be assigned or not. So 
                // we need to test 2 case of this case
                query = ("SELECT ca.ID, d.OrderNumber FROM dbo.Coordinator ca\n"
                        + "LEFT JOIN (SELECT o.CID, COUNT(o.OID) OrderNumber\n"
                        + "			FROM dbo.Coordinator c LEFT JOIN dbo.Orders o\n"
                        + "			ON o.CID = c.ID\n"
                        + "			WHERE CONVERT(DATE, o.delivery_time) BETWEEN ? AND ?\n"
                        + "			GROUP BY o.CID) AS d\n"
                        + "ON d.CID = ca.ID\n"
                        + "WHERE d.OrderNumber IS NULL\n"
                        + "AND ca.enabled = 1");
                pst = cn.prepareStatement(query);
                pst.setString(1, firstDayOfMonth.toString());
                pst.setString(2, now.toString());
                rs = pst.executeQuery();
                //This case mean Some coordinators haven't applied yet even though others have been. Priority for those who have not worked
                if (rs.next()) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(rs.getInt(1));
                    while (rs.next()) {
                        list.add(rs.getInt(1));
                    }

                    //Check if have 1 Coordinator suitable
                    if (list.size() == 1) {
                        CID = list.get(0);
                    } else {
                        // Randomly choose anyone who Available
                        Random random = new Random();
                        CID = list.get(random.nextInt(0, list.size()));
                    }

                } else {
                    //This mean All coordinators They all made at least one application this month. Randomly choose anyone who Available
                    query = ("SELECT ca.ID, d.OrderNumber FROM dbo.Coordinator ca\n"
                            + "LEFT JOIN (SELECT o.CID, COUNT(o.OID) OrderNumber\n"
                            + "			FROM dbo.Coordinator c LEFT JOIN dbo.Orders o\n"
                            + "			ON o.CID = c.ID\n"
                            + "			WHERE CONVERT(DATE, o.delivery_time) BETWEEN ? AND ?\n"
                            + "			GROUP BY o.CID) AS d\n"
                            + "ON d.CID = ca.ID\n"
                            + "WHERE d.OrderNumber = ?\n"
                            + "AND ca.enabled = 1");
                    pst = cn.prepareStatement(query);
                    pst.setString(1, firstDayOfMonth.toString());
                    pst.setString(2, now.toString());
                    pst.setInt(3, minOrderNumber);
                    rs = pst.executeQuery();

                    //If has Available
                    if (rs.next()) {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(rs.getInt(1));
                        while (rs.next()) {
                            list.add(rs.getInt(1));
                        }

                        //Check if have 1 Coordinator suitable
                        if (list.size() == 1) {
                            CID = list.get(0);
                        } else {
                            // Randomly choose anyone who Available
                            Random random = new Random();
                            CID = list.get(random.nextInt(0, list.size()));
                        }
                    } else {
                        // If no one is Available
                        // This method get MinimumPendingOrderNumber of all disable Coordinator
                        query = ("SELECT MIN(pon.PendingOrderNumber) AS MinimumPendingOrderNumber\n"
                                + "FROM (SELECT ca.ID, COUNT(od.OID) AS PendingOrderNumber FROM dbo.Coordinator ca\n"
                                + "                        LEFT JOIN (SELECT o.CID, COUNT(o.OID) OrderNumber\n"
                                + "                        			FROM dbo.Coordinator c LEFT JOIN dbo.Orders o\n"
                                + "                       				ON o.CID = c.ID\n"
                                + "                        			WHERE CONVERT(DATE, o.delivery_time) BETWEEN ? AND ?\n"
                                + "                        			GROUP BY o.CID) AS d\n"
                                + "                        ON d.CID = ca.ID\n"
                                + "						JOIN dbo.Orders od\n"
                                + "						ON od.CID = ca.ID\n"
                                + "                        WHERE d.OrderNumber = 2\n"
                                + "						AND od.status = 'Pending'\n"
                                + "						GROUP BY ca.ID) AS pon");
                        pst = cn.prepareStatement(query);
                        pst.setString(1, firstDayOfMonth.toString());
                        pst.setString(2, now.toString());
                        rs = pst.executeQuery();

                        if (rs.next()) {
                            minPendingOrderNumber = rs.getInt(1);
                        }

                        // This method get List of Coordinator has minimumPendingOrder of all disable Coordinator
                        query = "SELECT pon.ID\n"
                                + "FROM (SELECT ca.ID, COUNT(od.OID) AS PendingOrderNumber FROM dbo.Coordinator ca\n"
                                + "                        LEFT JOIN (SELECT o.CID, COUNT(o.OID) OrderNumber\n"
                                + "                        			FROM dbo.Coordinator c LEFT JOIN dbo.Orders o\n"
                                + "                       				ON o.CID = c.ID\n"
                                + "                        			WHERE CONVERT(DATE, o.delivery_time) BETWEEN ? AND ?\n"
                                + "                        			GROUP BY o.CID) AS d\n"
                                + "                        ON d.CID = ca.ID\n"
                                + "						JOIN dbo.Orders od\n"
                                + "						ON od.CID = ca.ID\n"
                                + "                        WHERE d.OrderNumber = 2\n"
                                + "						AND od.status = 'Pending'\n"
                                + "						GROUP BY ca.ID) AS pon\n"
                                + "JOIN dbo.Orders odc\n"
                                + "ON odc.CID = pon.ID\n"
                                + "WHERE pon.PendingOrderNumber = ?\n"
                                + "AND odc.status = 'Pending'";
                        pst = cn.prepareStatement(query);
                        pst.setString(1, firstDayOfMonth.toString());
                        pst.setString(2, now.toString());
                        pst.setInt(3, minPendingOrderNumber);
                        rs = pst.executeQuery();

                        if (rs.next()) {
                            ArrayList<Integer> list = new ArrayList<Integer>();
                            list.add(rs.getInt(1));
                            while (rs.next()) {
                                list.add(rs.getInt(1));
                            }

                            //Check if have 1 Coordinator suitable
                            if (list.size() == 1) {
                                CID = list.get(0);
                            } else {
                                // Randomly choose anyone who Available
                                Random random = new Random();
                                CID = list.get(random.nextInt(0, list.size()));
                            }

                        }
                    }

                }
            } else {
                // The munimum Order Number >= 2
                // This mean All coordinators are also be assigned at least 1 orders. Choosing in the list of Coordinator who has 
                // minimum Order Number. If it has more than 1 Coordinator same minimun Order. Choosing ramdomly in list
                query = ("SELECT ca.ID, d.OrderNumber FROM dbo.Coordinator ca\n"
                        + "LEFT JOIN (SELECT o.CID, COUNT(o.OID) OrderNumber\n"
                        + "			FROM dbo.Coordinator c LEFT JOIN dbo.Orders o\n"
                        + "			ON o.CID = c.ID\n"
                        + "			WHERE CONVERT(DATE, o.delivery_time) BETWEEN ? AND ?\n"
                        + "			GROUP BY o.CID) AS d\n"
                        + "ON d.CID = ca.ID\n"
                        + "WHERE d.OrderNumber = ?\n"
                        + "AND ca.enabled = 1");
                pst = cn.prepareStatement(query);
                pst.setString(1, firstDayOfMonth.toString());
                pst.setString(2, now.toString());
                pst.setInt(3, minOrderNumber);
                rs = pst.executeQuery();

                //If has Available
                if (rs.next()) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(rs.getInt(1));
                    while (rs.next()) {
                        list.add(rs.getInt(1));
                    }

                    //Check if have 1 Coordinator suitable
                    if (list.size() == 1) {
                        CID = list.get(0);
                    } else {
                        // Randomly choose anyone who Available
                        Random random = new Random();
                        CID = list.get(random.nextInt(0, list.size()));
                    }
                } else {
                    // If no one is Available
                    // This method get MinimumPendingOrderNumber of all disable Coordinator
                    query = ("SELECT MIN(pon.PendingOrderNumber) AS MinimumPendingOrderNumber\n"
                            + "FROM (SELECT ca.ID, COUNT(od.OID) AS PendingOrderNumber FROM dbo.Coordinator ca\n"
                            + "                        LEFT JOIN (SELECT o.CID, COUNT(o.OID) OrderNumber\n"
                            + "                        			FROM dbo.Coordinator c LEFT JOIN dbo.Orders o\n"
                            + "                       				ON o.CID = c.ID\n"
                            + "                        			WHERE CONVERT(DATE, o.delivery_time) BETWEEN ? AND ?\n"
                            + "                        			GROUP BY o.CID) AS d\n"
                            + "                        ON d.CID = ca.ID\n"
                            + "						JOIN dbo.Orders od\n"
                            + "						ON od.CID = ca.ID\n"
                            + "                        WHERE d.OrderNumber = 2\n"
                            + "						AND od.status = 'Pending'\n"
                            + "						GROUP BY ca.ID) AS pon");
                    pst = cn.prepareStatement(query);
                    pst.setString(1, firstDayOfMonth.toString());
                    pst.setString(2, now.toString());
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        minPendingOrderNumber = rs.getInt(1);
                    }

                    // This method get List of Coordinator has minimumPendingOrder of all disable Coordinator
                    query = "SELECT pon.ID\n"
                            + "FROM (SELECT ca.ID, COUNT(od.OID) AS PendingOrderNumber FROM dbo.Coordinator ca\n"
                            + "                        LEFT JOIN (SELECT o.CID, COUNT(o.OID) OrderNumber\n"
                            + "                        			FROM dbo.Coordinator c LEFT JOIN dbo.Orders o\n"
                            + "                       				ON o.CID = c.ID\n"
                            + "                        			WHERE CONVERT(DATE, o.delivery_time) BETWEEN ? AND ?\n"
                            + "                        			GROUP BY o.CID) AS d\n"
                            + "                        ON d.CID = ca.ID\n"
                            + "						JOIN dbo.Orders od\n"
                            + "						ON od.CID = ca.ID\n"
                            + "                        WHERE d.OrderNumber = 2\n"
                            + "						AND od.status = 'Pending'\n"
                            + "						GROUP BY ca.ID) AS pon\n"
                            + "JOIN dbo.Orders odc\n"
                            + "ON odc.CID = pon.ID\n"
                            + "WHERE pon.PendingOrderNumber = ?\n"
                            + "AND odc.status = 'Pending'";
                    pst = cn.prepareStatement(query);
                    pst.setString(1, firstDayOfMonth.toString());
                    pst.setString(2, now.toString());
                    pst.setInt(3, minPendingOrderNumber);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(rs.getInt(1));
                        while (rs.next()) {
                            list.add(rs.getInt(1));
                        }

                        //Check if have 1 Coordinator suitable
                        if (list.size() == 1) {
                            CID = list.get(0);
                        } else {
                            // Randomly choose anyone who Available
                            Random random = new Random();
                            CID = list.get(random.nextInt(0, list.size()));
                        }

                    }
                }

            }
        }
        return CID;
    }

    public static void main(String[] args) throws Exception {
        CoordinatorRepository cdr = new CoordinatorRepository();
    }
}
