package model.classes;
public class Tenant {
    private int idTenant;

    private String name;
    private int phone;
    private String email;
    private int idApart;

    private int idHouse;



    public Tenant(int idHouse, int idApart,int idTenant, String name, int phone, String email ) {
        this.idTenant = idTenant;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.idApart = idApart;
        this.idHouse = idHouse;
    }

    public int getIdTenant() {
        return idTenant;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getIdApart() {
        return idApart;
    }


    public int getIdHouse() {
        return idHouse;
    }
}
