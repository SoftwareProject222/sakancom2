package model.classes;
import code.classes.*;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());
    private static final String IN_VALID_INPUT = "Please enter valid input";

    private static String price = "price";
    private static String services = "services";
    private static String username;

    private static String helloS="Hello ";
    public static void displayAllHouses() throws SQLException {
        ConectionClass c=new ConectionClass();
        ResultSet result = c.getStmt().executeQuery("select * from house");
        logger.info("All houses in the system: ");
        logger.info("idHouse\t location\t\t services\t\t price\t\t idOwner");
        while (result.next()) {
            String output=result.getInt("idhouse")+"\t"+result.getString("location")+"\t"+result.getString(services)+"\t"+ result.getDouble(price)+" JD\t"+ result.getInt("id_owner");
            logger.info(output);
        }

    }

    public static void displayHouses(int ownerID) throws SQLException {

        ConectionClass c=new ConectionClass();
        ResultSet result = c.getStmt().executeQuery("select * from house where id_owner='" + ownerID + "'");
        logger.info("idHouse\t location\t\t services\t\t price");
        while (result.next()) {
            String output=result.getInt("idhouse") + "\t" + result.getString("location") + "\t\t" + result.getString(services) + "\t\t" + result.getDouble(price) + " JD";
            logger.info(output);
        }

    }
    public static void main(String[] args) throws URISyntaxException, IOException, SQLException {
        logger.info("------* Welcome to SAKANCOM system *------");
        boolean exitSystem = false;
        Scanner scan = new Scanner(System.in);

        while (!exitSystem) {
            logger.info("-> Please, choose how you want to login to the system:");
            logger.info("1- Admin");
            logger.info("2- Tenant");
            logger.info("3- Owner");
            logger.info("Enter 'exit' to end the system.");

            String choice = scan.nextLine();

            if(choice.equals("exit")){
                exitSystem=true;
                logger.info("Thank you for using SAKANCOM system. Goodbye!");
                System.exit(1);
            }

            if (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
                logger.info("The choice is not valid");
                continue;
            }

            Login log = performLogin(choice);
            if (log != null && log.isLogIn()) {
                logger.info("logged in successfully");

                if (choice.equals("1")) {
                    handleAdminTasks(log, scan);
                } else if (choice.equals("3")) {
                    handleOwnerTasks(log, scan);
                } else if (choice.equals("2")) {
                    handleTenantTasks(log, scan);
                }
            }
        }
    }


    private static Login performLogin(String choice) throws SQLException {
        Scanner scan = new Scanner(System.in);
        Login log = new Login();

        while (true) {
            logger.info("Enter username: ");
             username = scan.nextLine();
            logger.info("Enter password: ");
            String password = scan.nextLine();
            log.logInCheck(username, password, choice);

            if (log.isLogIn()) {
                return log;
            } else {
                log.reasonFalseLogin();
            }
        }
    }

    private static void handleAdminTasks(Login log, Scanner scan) throws URISyntaxException, IOException, SQLException {
        String hello = helloS+ username;
        logger.info(hello);
        House updateHouse = new House();

        while (true) {
            displayAdminOptions();
            String adminChoice = scan.nextLine();

            switch (adminChoice) {
                case "1":
                    handleAdvertisementRequests(scan);
                    break;
                case "2":
                    displayReservations();
                    break;
                case "3":
                    updateHouseInformation(scan, updateHouse);
                    break;
                case "4":
                    log.logout();
                    return;
                default:
                    logger.warning(IN_VALID_INPUT);
                    break;
            }
        }
    }

    private static void displayAdminOptions() {
        logger.info("1- See the requests of advertisement to accept or reject it");
        logger.info("2- See the reservations of houses");
        logger.info("3- Update house information");
        logger.info("4- Log out");
    }

    private static void handleAdvertisementRequests(Scanner scan) throws URISyntaxException, IOException, SQLException {
        /*open web page*/
        Desktop d = Desktop.getDesktop();
        String uri = "http://localhost/sakancom/table.php";
        d.browse(new URI(uri));

        while (true) {
            logger.info("1- Enter house id you want to accept its advertisement");
            logger.info("2- <-Back");
            String advChoice = scan.nextLine();
            if (advChoice.equals("1")) {
                logger.info("house id: ");
                String houseID = scan.nextLine();

                logger.info("acceptance (yes/no): ");
                String acceptance = scan.nextLine();
                AdminPage adminPage = new AdminPage(Integer.parseInt(houseID), acceptance);
                AdminPage.acceptReject(adminPage);
                logger.info("DONE");
            } else if (advChoice.equals("2")) {
                return;
            }
        }
    }

    private static void displayReservations() throws SQLException {
        List<Tenant> tenantList = AdminPage.seeReservations();
        AdminPage.displayReservations(tenantList);
    }

    private static void updateHouseInformation(Scanner scan, House updateHouse) throws SQLException {
        displayAllHouses();
        logger.info("Enter the ID of the house for updating: ");
        String idhouse = scan.nextLine();
        if (!House.findHouseId(Integer.parseInt(idhouse))) {
            House.unupdatedMsg();
            return;
        }

        while (true) {
            displayUpdateOptions();
            String updateOption = scan.nextLine();

            if (updateOption.equals("1")) {
                logger.info("The new services of the house you want to update: ");
                String services = scan.nextLine();
                updateHouse.updateInfo(Main.services, services, Integer.parseInt(idhouse));
                House.updateMsg();
            } else if (updateOption.equals("2")) {
                logger.info("The new price of the house you want to update: ");
                String price = scan.nextLine();
                updateHouse.updateInfo(Main.price, Double.parseDouble(price), Integer.parseInt(idhouse));
                House.updateMsg();
            } else if (updateOption.equals("3")) {
                logger.info("The new ownerId of the house you want to update: ");
                String ownerid = scan.nextLine();
                updateHouse.updateInfo("ownerId", Integer.parseInt(ownerid), Integer.parseInt(idhouse));
                House.updateMsg();
            } else if (updateOption.equals("4")) {
                return;
            } else {
                logger.warning(IN_VALID_INPUT);
            }
        }
    }

    private static void displayUpdateOptions() {
        logger.info("Choose what do you want to update: ");
        logger.info("1- Change Services");
        logger.info("2- Change Price");
        logger.info("3- Change OwnerID");
        logger.info("4- Back");
    }


    private static void handleOwnerTasks(Login log, Scanner scan) throws SQLException {
        String hello = helloS + log.getOwnerName();
        logger.info(hello);

        while (true) {
            displayOwnerOptions();
            String ownerChoice = scan.nextLine();

            switch (ownerChoice) {
                case "1":
                    handleAdvertisement(log, scan);
                    break;
                case "2":
                    handleHouseDetails(log, scan);
                    break;
                case "3":
                    addNewHouse(log, scan);
                    break;
                case "4":
                    log.logout();
                    return;
                default:
                    logger.warning(IN_VALID_INPUT);
                    break;
            }
        }
    }

    private static void displayOwnerOptions() {
        logger.info("1- Add advertisement for an existing house");
        logger.info("2- See all housing with all details");
        logger.info("3- Add a new house");
        logger.info("4- Log out");
    }

    private static void handleAdvertisement(Login log, Scanner scan) throws SQLException {
        displayHouses(log.getOwnerID());
        logger.info("Enter house id: ");
        String houseID = scan.nextLine();
        logger.info("Add photos: ");
        String photo = scan.nextLine();
        logger.info("Enter your name: ");
        String name = scan.nextLine();
        logger.info("Enter your contact info.: ");
        String contact = scan.nextLine();
        logger.info("Enter location: ");
        String location = scan.nextLine();
        logger.info("Enter services: ");
        String services = scan.nextLine();
        logger.info("Enter monthly rent: ");
        String rent = scan.nextLine();
        logger.info("Enter rent notes about inclusive of electricity and water or not: ");
        String rentNote = scan.nextLine();
        logger.info("Enter price: ");
        String price = scan.nextLine();
        AddAdvertisement advertisement = new AddAdvertisement(
                Integer.parseInt(houseID), photo, name, contact, location, services, Double.parseDouble(rent)
        );
        advertisement.setRentNote(rentNote);
        advertisement.setPrice(Double.parseDouble(price));
        AddAdvertisement.addAdv(advertisement);
        if (!AddAdvertisement.isValidHouse()) {
            if (AddAdvertisement.getIsDuplicateHouse()) {
                advertisement.displayReasonSameHouse();
            } else {
                advertisement.displayReasonHouseNotExist();
            }
        } else {
            logger.info("The advertisement is added, but waiting Administrator to accept it ");
        }
    }

    private static void handleHouseDetails(Login log, Scanner scan) throws SQLException {
        List<House> houseList = new ArrayList<>();
        List<Integer> apart = new ArrayList<>();
        List<HouseFloor> apartInfoList = new ArrayList<>();

        logger.info("Your Housing:");
        displayHouses(log.getOwnerID());
        logger.info("Enter house id to see number of tenant and floor of this house: ");
        String houseId = scan.nextLine();
        houseList = OwnerControlPanel.findHouse(Integer.parseInt(houseId));
        OwnerControlPanel.displayNOTenantAndFloors(houseList);

        logger.info("Enter floor number you want to see its apartments: ");
        String floorId = scan.nextLine();
        apart = OwnerControlPanel.findFloor(Integer.parseInt(floorId));
        OwnerControlPanel.displayAparts(apart);

        logger.info("Enter apart number that you want to see info. about it: ");
        String apartId = scan.nextLine();
        apartInfoList = OwnerControlPanel.findApart(Integer.parseInt(apartId));
        OwnerControlPanel.displayApartInformation(apartInfoList);
    }

    private static void addNewHouse(Login log, Scanner scan) throws SQLException {
        boolean shouldAddNewHouse = true;

        while (shouldAddNewHouse) {
            logger.info("Enter the required information for the new home: ");
            logger.info("Enter house id: ");
            String houseID = scan.nextLine();
            logger.info("Add photos: ");
            String photo = scan.nextLine();
            logger.info("Enter location: ");
            String location = scan.nextLine();
            logger.info("Enter services: ");
            String services = scan.nextLine();
            logger.info("Enter price(JD): ");
            String price = scan.nextLine();
            logger.info("Enter number of floors: ");
            String noFloors = scan.nextLine();
            House newHouse = new House(
                    Integer.parseInt(houseID), photo, location, services, Double.parseDouble(price),
                    log.getOwnerID(), Integer.parseInt(noFloors)
            );
            House.addHouse(newHouse);

            if (!HouseFloor.findHouseFloorId(newHouse.getId())) {
                addHouseDetails(scan, newHouse);
            }

            logger.info("Do you want to to try add again? (yes/no)");
            String addAnotherHouse = scan.nextLine();
            shouldAddNewHouse = addAnotherHouse.equalsIgnoreCase("yes");
        }
    }


    private static void addHouseDetails(Scanner scan, House newHouse) throws SQLException {
        while (true) {
            logger.info("Enter the details for this new new home: ");
            logger.info("Enter floor number: ");
            String idfloor = scan.nextLine();
            logger.info("Enter apartment number: ");
            String idapart = scan.nextLine();
            logger.info("Enter number of bathrooms for this apartment: ");
            String noBathrooms = scan.nextLine();
            logger.info("Enter number of bedrooms for this apartment: ");
            String noBedrooms = scan.nextLine();
            logger.info("Is there's a balcony? yes/no: ");
            String balcony = scan.nextLine();
            HouseFloor newHouseFloor = new HouseFloor(
                    newHouse.getId(), Integer.parseInt(idfloor),
                    Integer.parseInt(idapart), Integer.parseInt(noBathrooms),
                    Integer.parseInt(noBedrooms), balcony
            );
            logger.info(newHouseFloor.getIdHouse() + "");
            House.addHouseInfo(newHouseFloor);

            logger.info("1- continue adding details\n2- add another new house\n3- back to my page");
            String x = scan.nextLine();
            if (x.equals("2")) {
                break;
            } else if (x.equals("3")) {
                displayOwnerOptions();
            }
        }
    }


    private static void handleTenantTasks(Login log, Scanner scan) throws SQLException, URISyntaxException, IOException {
        String hello = helloS + log.getTenantName();
        logger.info(hello);
        List<House> houseList = new ArrayList<>();
        List<HouseFloor> apartList = new ArrayList<>();
        List<Tenant> studentList = new ArrayList<>();

        while (true) {
            displayTenantOptions();
            String tenantChoice = scan.nextLine();

            switch (tenantChoice) {
                case "1":
                    Desktop d = Desktop.getDesktop();
                    Tenant.openAdvertisements(d);
                    break;
                case "2":
                    logger.info("**** ALL HOUSES ***");
                    houseList= Tenant.seeHousing();
                    Tenant.displayHousing(houseList);

                    logger.info("**** AVAILABLE APARTMENTS ***");
                    apartList= Tenant.seeAvailableAparts();
                    Tenant.displayAparts(apartList);

                    logger.info("**** STUDENT APARTMENTS ***");
                    studentList= Tenant.seeStudentAparts();
                    Tenant.displayStudentAparts(studentList);
                    break;
                case "3":
                    logger.info("SEE THE HOUSES TO CHOOSE WHICH YOU WANT TO BOOK");
                    logger.info("**** AVAILABLE APARTMENTS ***");
                    apartList= Tenant.seeAvailableAparts();
                    Tenant.displayAparts(apartList);

                    logger.info("**** STUDENT APARTMENTS ***");
                    studentList= Tenant.seeStudentAparts();
                    Tenant.displayStudentAparts(studentList);

                    logger.info("--- ENTER THE REQUIRED INFORMATION FOR RESERVATION --- ");
                    logger.info("Enter your email: ");
                    String email = scan.nextLine();
                    logger.info("Enter your name: ");
                    String name = scan.nextLine();
                    logger.info("Enter your contact information: ");
                    String contact = scan.nextLine();
                    logger.info("Enter house id you chose it: ");
                    String houseId = scan.nextLine();
                    logger.info("Enter apartment id you want to reserve it: ");
                    String apartId = scan.nextLine();

                    Tenant tenant=new Tenant(email,name,contact,Integer.parseInt(houseId),Integer.parseInt(apartId));
                    Tenant.bookAccommodation(tenant);

                    Tenant.printControlPanel(tenant);

                    break;
                case "4":
                    logger.info("--- ENTER THE REQUIRED INFORMATION FOR ADVERTISE FURNITURE --- ");
                    logger.info("Enter furniture name: ");
                    String fName = scan.nextLine();
                    logger.info("Enter description about it: ");
                    String description = scan.nextLine();
                    logger.info("Enter the price: ");
                    String price = scan.nextLine();
                    AdvFurniture furniture=new AdvFurniture(log.getTenantID(), log.getTenantName(),fName,description,Double.parseDouble(price));
                    AdvFurniture.addFurniture(furniture);
                    break;

                case "5":
                    List<AdvFurniture> furnitures;
                    furnitures=AdvFurniture.seeFurniture();
                    AdvFurniture.displayFurnitures(furnitures);
                    break;
                case "6":
                    log.logout();
                    return;
                default:
                    logger.warning(IN_VALID_INPUT);
                    break;
            }
        }
    }

    private static void displayTenantOptions() {
        logger.info("1- See the advertisement of available housing");
        logger.info("2- See the available housing with information");
        logger.info("3- Book accommodation");
        logger.info("4- Advertise used furniture for sale");
        logger.info("5- View furniture for sale");
        logger.info("6- Log out");
    }
}
