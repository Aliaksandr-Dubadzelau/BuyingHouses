package by.buyinghouses.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Integer amountOfRooms;

    @Column(nullable = false)
    private Float rate;
    @Column(nullable = false)
    private Float square;

    @Column(nullable = false)
    private BigDecimal cost;

    @Column(nullable = false)
    private Boolean isWaited;
    @Column(nullable = false)
    private Boolean isFurniture;
    @Column(nullable = false)
    private Boolean isInternet;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    private User owner;

    @Enumerated(EnumType.STRING)
    private AccommodationType type;

    private String fileName;

    public Accommodation() {
    }

    public Accommodation(String name, String city, Integer amountOfRooms, Float square, BigDecimal cost, Boolean isFurniture, Boolean isInternet, AccommodationType type) {
        this.name = name;
        this.city = city;
        this.amountOfRooms = amountOfRooms;
        this.square = square;
        this.cost = cost;
        this.isFurniture = isFurniture;
        this.isInternet = isInternet;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAmountOfRooms() {
        return amountOfRooms;
    }

    public void setAmountOfRooms(Integer amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Float getSquare() {
        return square;
    }

    public void setSquare(Float square) {
        this.square = square;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Boolean isWaited() {
        return isWaited;
    }

    public void setWaited(Boolean waited) {
        isWaited = waited;
    }

    public Boolean isFurniture() {
        return isFurniture;
    }

    public void setFurniture(Boolean furniture) {
        isFurniture = furniture;
    }

    public Boolean isInternet() {
        return isInternet;
    }

    public void setInternet(Boolean internet) {
        isInternet = internet;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getType() {
        return type.name();
    }

    public void setType(String type) {
        this.type = AccommodationType.valueOf(type);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
