package pl.javastart.client;

import pl.javastart.order.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client implements Serializable {
    private static final long serialUID = 12L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long id;
    @Column(name = "firstname",length = 45,nullable = false)
    private String firstName;
    @Column(name = "lastname",length = 45,nullable = false)
    private String lastName;
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "client_details_id")
    private ClientDetails clientDetails;

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    public void addOrderToList(Order order){
        orders.add(order);
        order.setClient(this);
    }
    public List<Order> getOrders() {return orders;}

    public void setOrders(List<Order> orders) { this.orders = orders; }

    public Client() {
    }

    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addClientToOrder(Order order){ order.setClient(this); }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return "Client [id=" + id + ", firstName=" + firstName
                + ", lastName=" + lastName + orders.size()
                + ",\n orders=" + orders + "]";
    }
}
