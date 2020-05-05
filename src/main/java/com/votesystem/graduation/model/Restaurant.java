package com.votesystem.graduation.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseIdEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Menu> menu;

    public Restaurant() {}

    public Restaurant(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Restaurant(Integer id, String name, List<Menu> menu) {
        super(id);
        this.name = name;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
